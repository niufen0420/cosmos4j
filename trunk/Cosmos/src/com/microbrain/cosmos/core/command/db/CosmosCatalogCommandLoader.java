/*
 * Copyright (c) 2006 Microbrain Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.microbrain.cosmos.core.command.db;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.CosmosExecuter;
import com.microbrain.cosmos.core.command.CosmosMetaArgument;
import com.microbrain.cosmos.core.command.CosmosMetaCommand;
import com.microbrain.cosmos.core.command.config.CosmosConfigLocalCommandLoader;
import com.microbrain.cosmos.core.command.loaders.CosmosCommandLoaderException;
import com.microbrain.cosmos.core.config.Configuration;
import com.microbrain.cosmos.core.constants.ArgumentInOutType;
import com.microbrain.cosmos.core.constants.CosmosCommandSource;
import com.microbrain.cosmos.core.domain.CosmosDomain;
import com.microbrain.cosmos.core.domain.db.CosmosSqlDomain;

/**
 * <p>
 * <code>CosmosCatalogCommandLoader</code>
 * Catalog型数据库的存储过程本地装载器，用来从数据库系统中装载可供调用的存储过程和函数。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.config.CosmosConfigLocalCommandLoader
 * @see com.microbrain.cosmos.core.command.loaders.CosmosLocalCommandLoader
 * @see com.microbrain.cosmos.core.command.loaders.AbstractCosmosLocalCommandLoader
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @since CFDK 1.0
 */
public class CosmosCatalogCommandLoader extends CosmosConfigLocalCommandLoader {

	/**
	 * Cosmos框架配置类。
	 */
	private Configuration config = null;

	/**
	 * 构造函数。
	 * 
	 * @param factory
	 *            工厂类。
	 * @param domain
	 *            所属数据库域。
	 */
	public CosmosCatalogCommandLoader(CosmosFactory factory,
			CosmosSqlDomain domain) {
		super(factory, domain);
		this.config = factory.lookupConfig();
	}

	/*
	 * 装载所有的本地存储过程。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.config.CosmosConfigLocalCommandLoader
	 * #loadAllLocalRootCommands()
	 */
	@Override
	public Collection<CosmosCommand> loadAllLocalRootCommands()
			throws CosmosCommandLoaderException {
		Collection<CosmosCommand> commands = super.loadAllLocalRootCommands();
		if (!(this.domain instanceof CosmosSqlDomain)) {
			return commands;
		}

		CosmosSqlDomain domain = (CosmosSqlDomain) this.domain;
		ResultSet rs = null, ars = null;
		CosmosCallableExecuter callableExecuter = domain.findCallExecuter();

		Connection conn = null;
		DatabaseMetaData dbmd = null;
		try {
			if (callableExecuter != null) {
				Map<String, Collection<CosmosMetaArgument>> argsMap = new HashMap<String, Collection<CosmosMetaArgument>>();
				try {
					conn = domain.getConnection();
					dbmd = conn.getMetaData();
					ars = dbmd.getProcedureColumns(domain.getSchema(), null,
							"%", "%");

					while (ars.next()) {
						String spName = ars.getString(3);
						Collection<CosmosMetaArgument> args = null;
						if (argsMap.containsKey(spName)) {
							args = argsMap.get(spName);
						} else {
							args = new ArrayList<CosmosMetaArgument>();
							argsMap.put(spName, args);
						}

						ArgumentInOutType inOutType = ArgumentInOutType.IN;
						short inOutTypeValue = ars.getShort(5);
						if (DatabaseMetaData.procedureColumnIn == inOutTypeValue) {
							inOutType = ArgumentInOutType.IN;
						} else if (DatabaseMetaData.procedureColumnInOut == inOutTypeValue) {
							inOutType = ArgumentInOutType.INOUT;
						} else if ((DatabaseMetaData.procedureColumnOut == inOutTypeValue)
								|| (DatabaseMetaData.procedureColumnReturn == inOutTypeValue)
								|| (DatabaseMetaData.procedureColumnResult == inOutTypeValue)) {
							inOutType = ArgumentInOutType.OUT;
						}

						int dataType = ars.getInt(6);
						CosmosMetaArgument arg = new CosmosCallableMetaArgument(
								this.factory.getConverter(dataType), ars
										.getString(4), inOutType, null,
								dataType);

						args.add(arg);
					}
				} catch (Exception e) {
					throw new CosmosCommandLoaderException(e);
				} finally {
					if (ars != null) {
						ars.close();
					}
				}

				rs = dbmd.getProcedures(domain.getSchema(), null, "%");
				try {
					while (rs.next()) {
						Collection<CosmosMetaArgument> args = argsMap.get(rs
								.getString(3));

						if (args == null) {
							args = new ArrayList<CosmosMetaArgument>();
						}

						short typeValue = rs.getShort(8);
						CallableType type = null;
						if (typeValue == DatabaseMetaData.procedureResultUnknown) {
							type = CallableType.PROCEDURE;
						} else if (typeValue == DatabaseMetaData.procedureReturnsResult) {
							type = CallableType.FUNCTION;
						}

						String name = rs.getString(3);

						CosmosMetaCommand commandType = config
								.getDefaultCommandType();

						Constructor<CosmosCommand> constructor = commandType
								.getClazz().getConstructor(String.class,
										CosmosDomain.class, String.class,
										CosmosExecuter.class, Collection.class,
										CosmosMetaCommand.class,
										CosmosCommandSource.class);

						CosmosCommand command = constructor.newInstance(name,
								domain, generateCallSql(name, type, args),
								callableExecuter, args, config
										.getDefaultCommandType(),
								CosmosCommandSource.LOCAL);
						command.setDebugLevel(domain.getDebugLevel());

						commands.add(command);
					}
				} catch (Exception e) {
					throw new CosmosCommandLoaderException(e);
				} finally {
					if (rs != null) {
						rs.close();
					}
				}
			}
		} catch (Exception e) {
			throw new CosmosCommandLoaderException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return commands;
	}

	/*
	 * 装载某个存储过程。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.config.CosmosConfigLocalCommandLoader
	 * #loadLocalRootCommand(java.lang.String)
	 */
	@Override
	public CosmosCommand loadLocalRootCommand(String name)
			throws CosmosCommandLoaderException {
		CosmosCommand command = super.loadLocalRootCommand(name);
		if (command != null) {
			return command;
		}

		CosmosSqlDomain domain = (CosmosSqlDomain) this.domain;
		ResultSet rs = null, ars = null;
		CosmosCallableExecuter callableExecuter = domain.findCallExecuter();

		Connection conn = null;
		DatabaseMetaData dbmd = null;
		try {
			if (callableExecuter != null) {
				conn = domain.getConnection();
				dbmd = conn.getMetaData();
				ars = dbmd.getProcedureColumns(domain.getSchema(), null, name,
						"%");

				Collection<CosmosMetaArgument> args = new ArrayList<CosmosMetaArgument>();
				try {
					while (ars.next()) {
						ArgumentInOutType inOutType = ArgumentInOutType.IN;
						short inOutTypeValue = ars.getShort(5);
						if (DatabaseMetaData.procedureColumnIn == inOutTypeValue) {
							inOutType = ArgumentInOutType.IN;
						} else if (DatabaseMetaData.procedureColumnInOut == inOutTypeValue) {
							inOutType = ArgumentInOutType.INOUT;
						} else if ((DatabaseMetaData.procedureColumnOut == inOutTypeValue)
								|| (DatabaseMetaData.procedureColumnReturn == inOutTypeValue)
								|| (DatabaseMetaData.procedureColumnResult == inOutTypeValue)) {
							inOutType = ArgumentInOutType.OUT;
						}

						int dataType = ars.getInt(6);
						CosmosMetaArgument arg = new CosmosCallableMetaArgument(
								this.factory.getConverter(dataType), ars
										.getString(4), inOutType, null,
								dataType);

						args.add(arg);
					}
				} catch (Exception e) {
					throw new CosmosCommandLoaderException(e);
				} finally {
					if (ars != null) {
						ars.close();
					}
				}

				rs = dbmd.getProcedures(domain.getSchema(), null, name);
				try {
					if (rs.next()) {
						short typeValue = rs.getShort(8);
						CallableType type = null;
						if (typeValue == DatabaseMetaData.procedureResultUnknown) {
							type = CallableType.PROCEDURE;
						} else if (typeValue == DatabaseMetaData.procedureReturnsResult) {
							type = CallableType.FUNCTION;
						}

						CosmosMetaCommand commandType = config
								.getDefaultCommandType();

						Constructor<CosmosCommand> constructor = commandType
								.getClazz().getConstructor(String.class,
										CosmosDomain.class, String.class,
										CosmosExecuter.class, Collection.class,
										CosmosMetaCommand.class,
										CosmosCommandSource.class);

						command = constructor.newInstance(name, domain,
								generateCallSql(name, type, args),
								callableExecuter, args, config
										.getDefaultCommandType(),
								CosmosCommandSource.LOCAL);
						command.setDebugLevel(domain.getDebugLevel());
					}
				} catch (Exception e) {
					throw new CosmosCommandLoaderException(e);
				} finally {
					if (rs != null) {
						rs.close();
					}
				}
			}
		} catch (Exception e) {
			command = null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return command;
	}

	/**
	 * 产生存储过程和函数的调用语句的方法。
	 * 
	 * @param name
	 *            存储过程名称。
	 * @param type
	 *            存储过程或者函数类型。
	 * @param args
	 *            参数列表。
	 * @return 产生的SQL调用语句。
	 */
	private String generateCallSql(String name, CallableType type,
			Collection<CosmosMetaArgument> args) {
		StringBuffer sql = new StringBuffer();
		switch (type) {
		case PROCEDURE:
			sql.append("{");
			break;
		case FUNCTION:
			sql.append("{? = ");
			break;
		default:
			break;
		}
		sql.append("call ").append(name).append("(");
		if (args != null && args.size() > 0) {
			for (int i = 0; i < args.size(); i++) {
				sql.append("?,");
			}

			sql.deleteCharAt(sql.length() - 1);
		}
		sql.append(")}");

		return sql.toString();
	}

}
