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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.CosmosExecuteException;
import com.microbrain.cosmos.core.command.CosmosMetaArgument;
import com.microbrain.cosmos.core.command.CosmosResult;
import com.microbrain.cosmos.core.domain.CosmosDomain;
import com.microbrain.cosmos.core.domain.db.CosmosSqlDomain;

/**
 * <p>
 * 执行存储过程的执行器，扩展自<code>AbstractCosmosDbExecuter</code>，用来执行存储过程，并将结果转换为
 * <code>CosmosResult</code>。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.db.AbstractCosmosDbExecuter
 * @see com.microbrain.cosmos.core.command.db.CosmosCallableExecuter
 * @since CFDK 1.0
 */
public class CosmosCallExecuter extends AbstractCosmosDbExecuter implements
		CosmosCallableExecuter {

	/**
	 * 构造函数。
	 * 
	 * @param name
	 *            执行器名称。
	 * @param label
	 *            执行器标签。
	 * @param description
	 *            执行器说明。
	 * @param category
	 *            执行器所属的分类。
	 */
	public CosmosCallExecuter(String name, String label, String description,
			String category) {
		super(name, label, description, category);
	}

	/*
	 * 执行存储过程的封装方法。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosExecuter#execute(com.microbrain
	 * .cosmos.core.command.CosmosCommand, java.util.Map)
	 */
	public CosmosResult execute(CosmosCommand command, Map<String, Object> args)
			throws CosmosExecuteException {
		CosmosDomain domain = command.getDomain();
		if (!(domain instanceof CosmosSqlDomain)) {
			throw new CosmosExecuteException(
					"this domain may not be category '" + this.category + "'.");
		}

		Connection conn = null;
		try {
			conn = ((CosmosSqlDomain) domain).getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CallableStatement cstmt = null;
		CosmosResult result = null;
		try {
			cstmt = conn.prepareCall(command.getCommand());

			Collection<CosmosMetaArgument> arguments = command
					.listMetaArguments();
			int i = 0;
			for (CosmosMetaArgument argument : arguments) {
				CosmosCallableMetaArgument callArg = (CosmosCallableMetaArgument) argument;
				try {
					switch (argument.getInOutType()) {
					case IN:
						cstmt.setObject(i + 1, args.get(argument.getName()));
						break;
					case INOUT:
						cstmt.setObject(i + 1, args.get(argument.getName()));
					case OUT:
						cstmt.registerOutParameter(i + 1, callArg.getSqlType());
						break;
					default:
						cstmt.setObject(i + 1, args.get(argument.getName()));
						break;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				i++;
			}

			cstmt.execute();

			result = convertResult(cstmt, command);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (cstmt != null) {
					cstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

}
