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

import java.sql.Connection;
import java.sql.PreparedStatement;
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
 * ִ����ͨSQL����ִ��������չ��<code>AbstractCosmosDbExecuter</code>��
 * ����ִ����ͨ��SQL��䣬û�������֧�֣��������ת��Ϊ <code>CosmosResult</code>��
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosExecuter
 * @see com.microbrain.cosmos.core.command.AbstractCosmosExecuter
 * @see com.microbrain.cosmos.core.command.db.AbstractCosmosDbExecuter
 * @since CFDK 1.0
 */
public class CosmosSqlExecuter extends AbstractCosmosDbExecuter {

	/**
	 * ���캯����
	 * 
	 * @param name
	 *            ִ�������ơ�
	 * @param label
	 *            ִ������ǩ��
	 * @param description
	 *            ִ����˵����
	 * @param category
	 *            ִ�����������ࡣ
	 */
	public CosmosSqlExecuter(String name, String label, String description,
			String category) {
		super(name, label, description, category);
	}

	/*
	 * ִ��һ��SQL��䡣
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

		PreparedStatement pstmt = null;
		CosmosResult result = null;
		try {
			pstmt = conn.prepareStatement(command.getCommand());

			Collection<CosmosMetaArgument> arguments = command
					.listMetaArguments();
			int i = 0;
			for (CosmosMetaArgument argument : arguments) {
				try {
					pstmt.setObject(i + 1, args.get(argument.getName()));
				} catch (SQLException e) {
					throw e;
				}
				i++;
			}

			pstmt.execute();

			result = convertResult(command, pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
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
