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
 * <code>AbstractCosmosDbExecuter</code>��һ���������ݵ�<code>CosmosExecuter</code>
 * ����ʵ�֣���Ҫ�ṩ��<code>PreparedStatement</code>��<code>CallableStatement</code>ת����
 * <code>CosmosResult</code>���ظ��û���ϵͳ�еĸ����й����ݿ��<code>CosmosResult</code>���̳��Դ��ࡣ
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
	 * ���캯������ʼ��װ������ϵͳʱʹ�á�
	 * 
	 * @param name
	 *            ִ�������ơ�
	 * @param label
	 *            ִ������ǩ��
	 * @param description
	 *            ִ����������Ϣ��
	 * @param category
	 *            ִ�������ࡣ
	 */
	public AbstractCosmosDbExecuter(String name, String label,
			String description, String category) {
		super(name, label, description, category);
	}

	/**
	 * ��װִ�����ݿ����֮��Ľ��������ִ�н��<code>CosmosResult</code>�ࡣ
	 * 
	 * @param command
	 *            ִ�е����
	 * @param pstmt
	 *            ִ�н�����αꡣ
	 * @return ����ִ�н����
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
	 * ��װִ�����ݿ����֮��Ľ��������ִ�н��<code>CosmosResult</code>�ࡣ
	 * 
	 * @param cstmt
	 *            �洢����ִ�н����
	 * @param command
	 *            Ҫ�ռ�������
	 * @return �任֮��Ľ����
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
