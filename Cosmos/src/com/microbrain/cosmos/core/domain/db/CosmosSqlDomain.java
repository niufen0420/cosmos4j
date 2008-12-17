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
package com.microbrain.cosmos.core.domain.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.microbrain.cosmos.core.command.db.CosmosCallableExecuter;
import com.microbrain.cosmos.core.domain.CosmosDomain;

/**
 * <p>
 * ���ݿ���ӿڣ���չ��Ĭ�ϵ�<code>CosmosDomain</code>���ṩ�˷������ݿ����ӵķ����ȡ�
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.domain.db.AbstractCosmosSqlDomain
 * @see com.microbrain.cosmos.core.domain.AbstractCosmosDomain
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @since CFDK 1.0
 */
public interface CosmosSqlDomain extends CosmosDomain {

	/**
	 * ��÷�����
	 * 
	 * @return ������
	 */
	public String getSchema();

	/**
	 * ��ô洢����ִ������
	 * 
	 * @return �洢����ִ������
	 */
	public CosmosCallableExecuter findCallExecuter();

	/**
	 * ������ݿ����ӡ�
	 * 
	 * @return ���ݿ����ӡ�
	 * @throws SQLException
	 *             �׳����ݿ������쳣��
	 */
	public Connection getConnection() throws SQLException;

}
