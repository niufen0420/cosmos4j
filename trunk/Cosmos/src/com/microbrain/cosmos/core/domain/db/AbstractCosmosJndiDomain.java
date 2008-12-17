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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.microbrain.cosmos.core.config.ConfigurationException;
import com.microbrain.cosmos.core.domain.CosmosDomainException;
import com.microbrain.cosmos.core.domain.CosmosDomainType;

/**
 * <p>
 * ʵ�������ݿ���ĳ����򣬸������JNDI�ķ�ʽ�������ݿ⡣
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.domain.db.AbstractCosmosSqlDomain
 * @see com.microbrain.cosmos.core.domain.AbstractCosmosDomain
 * @see com.microbrain.cosmos.core.domain.db.CosmosSqlDomain
 * @since CFDK 1.0
 */
public abstract class AbstractCosmosJndiDomain extends AbstractCosmosSqlDomain {

	/**
	 * JNDI�󶨵����ơ�
	 */
	private static final String DOMAIN_BINDING = "binding";

	/**
	 * JNDI�󶨵�����ֵ��
	 */
	private String binding = null;

	/**
	 * ����Դ��
	 */
	private DataSource ds = null;

	/**
	 * ���캯����
	 * 
	 * @param name
	 *            �����ơ�
	 * @param type
	 *            �����͡�
	 * @param index
	 *            ����š�
	 */
	public AbstractCosmosJndiDomain(String name, CosmosDomainType type,
			int index) {
		super(name, type, index);
	}

	/*
	 * ��ʼ����
	 * 
	 * @see
	 * com.microbrain.cosmos.core.domain.db.AbstractCosmosSqlDomain#initDomain()
	 */
	protected void initDomain() throws CosmosDomainException {
		super.initDomain();
		try {
			this.binding = config.getElementInitParameter(entryPath,
					DOMAIN_BINDING);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Context context = new InitialContext();
			Context env = (Context) context.lookup("java:/comp/env");
			this.ds = (DataSource) env.lookup(binding);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * ������ݿ����ӡ�
	 * 
	 * @see com.microbrain.cosmos.core.domain.db.CosmosSqlDomain#getConnection()
	 */
	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

}
