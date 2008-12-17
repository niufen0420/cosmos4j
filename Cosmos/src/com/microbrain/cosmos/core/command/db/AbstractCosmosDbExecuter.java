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

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.microbrain.cosmos.core.command.AbstractCosmosExecuter;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.CosmosDynamicList;
import com.microbrain.cosmos.core.command.CosmosDynamicObject;
import com.microbrain.cosmos.core.command.CosmosMetaArgument;
import com.microbrain.cosmos.core.command.CosmosResult;
import com.microbrain.cosmos.core.command.StandardCosmosResult;

/**
 * <p>
 * <code>AbstractCosmosDbExecuter</code>是一个基于数据的<code>CosmosExecuter</code>
 * 抽象实现，主要提供将<code>PreparedStatement</code>和<code>CallableStatement</code>转化成
 * <code>CosmosResult</code>返回给用户。系统中的各种有关数据库的<code>CosmosResult</code>都继承自此类。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosExecuter
 * @see com.microbrain.cosmos.core.command.AbstractCosmosExecuter
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @since CFDK 1.0
 */
public abstract class AbstractCosmosDbExecuter extends AbstractCosmosExecuter {

	/**
	 * 构造函数，初始化装载整个系统时使用。
	 * 
	 * @param name
	 *            执行器名称。
	 * @param label
	 *            执行器标签。
	 * @param description
	 *            执行器描述信息。
	 * @param category
	 *            执行器分类。
	 */
	public AbstractCosmosDbExecuter(String name, String label,
			String description, String category) {
		super(name, label, description, category);
	}

	/**
	 * 封装执行数据库操作之后的结果，返回执行结果<code>CosmosResult</code>类。
	 * 
	 * @param command
	 *            执行的命令。
	 * @param pstmt
	 *            执行结果的游标。
	 * @return 返回执行结果。
	 */
	protected CosmosResult convertResult(CosmosCommand command,
			PreparedStatement pstmt) {
		StandardCosmosResult result = new StandardCosmosResult(command);
		try {
			ResultSet rs = null;
			ResultSetMetaData rsmd = null;
			Integer index = 0;
			do {
				CosmosDynamicList<CosmosDynamicObject> list = null;
				try {
					rs = pstmt.getResultSet();
					if (rs == null) {
						break;
					}

					rsmd = rs.getMetaData();

					String name = null;
					try {
						name = rs.getCursorName();
					} catch (SQLException e) {
						name = index.toString();
					}

					list = new CosmosDynamicList<CosmosDynamicObject>(name);

					for (int i = 0; i < rsmd.getColumnCount(); i++) {
						list.pushProperty(rsmd.getColumnName(i + 1));
					}

					while (rs.next()) {
						CosmosDynamicObject row = list.newObject();

						for (int i = 0; i < rsmd.getColumnCount(); i++) {
							row.put(rsmd.getColumnName(i + 1), rs
									.getObject(i + 1));
						}

						list.add(row);
					}
				} finally {
					if (rs != null) {
						rs.close();
					}
				}

				if (list != null) {
					result.addList(list);
				}

				index++;
			} while (pstmt.getMoreResults());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 封装执行数据库操作之后的结果，返回执行结果<code>CosmosResult</code>类。
	 * 
	 * @param cstmt
	 *            存储过程执行结果。
	 * @param command
	 *            要收集结果命令。
	 * @return 变换之后的结果。
	 */
	protected CosmosResult convertResult(CallableStatement cstmt,
			CosmosCommand command) {
		StandardCosmosResult result = (StandardCosmosResult) convertResult(
				command, cstmt);

		Collection<CosmosMetaArgument> outVars = new ArrayList<CosmosMetaArgument>();
		for (CosmosMetaArgument argument : command.listMetaArguments()) {
			switch (argument.getInOutType()) {
			case OUT:
			case INOUT:
				result.pushProperty(argument.getName());
				outVars.add(argument);
				break;
			default:
				break;
			}
		}

		CosmosDynamicObject out = result.newObject();
		try {
			ResultSet rs = null;
			ResultSetMetaData rsmd = null;
			Integer index = 0;
			for (CosmosMetaArgument outVar : outVars) {
				Object ret = cstmt.getObject(outVar.getName());
				if (ret instanceof ResultSet) {
					CosmosDynamicList<CosmosDynamicObject> list = null;
					try {
						rs = (ResultSet) ret;
						rsmd = rs.getMetaData();

						String name = null;
						try {
							name = rs.getCursorName();
						} catch (SQLException e) {
							name = index.toString();
						}

						list = new CosmosDynamicList<CosmosDynamicObject>(name);

						for (int i = 0; i < rsmd.getColumnCount(); i++) {
							list.pushProperty(rsmd.getColumnName(i + 1));
						}

						while (rs.next()) {
							CosmosDynamicObject row = list.newObject();

							for (int i = 0; i < rsmd.getColumnCount(); i++) {
								row.put(rsmd.getColumnName(i + 1), rs
										.getObject(i + 1));
							}

							list.add(row);
						}
					} finally {
						if (rs != null) {
							rs.close();
						}
					}

					if (list != null) {
						result.addList(list);
						out.put(outVar.getName(), list);
					}

					index++;
				} else {
					out.put(outVar.getName(), ret);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		result.setVar(out);
		return result;
	}

}
