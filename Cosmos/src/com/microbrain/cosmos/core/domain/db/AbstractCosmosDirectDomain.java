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
import java.sql.DriverManager;
import java.sql.SQLException;

import com.microbrain.cosmos.core.config.ConfigurationException;
import com.microbrain.cosmos.core.domain.CosmosDomainException;
import com.microbrain.cosmos.core.domain.CosmosDomainType;

/**
 * <p>
 * 直接连接数据库的抽象域，扩展自<code>AbstractCosmosSqlDomain</code>提供了直接访问数据库连接的方法。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.domain.db.AbstractCosmosSqlDomain
 * @see com.microbrain.cosmos.core.domain.AbstractCosmosDomain
 * @see com.microbrain.cosmos.core.domain.db.CosmosSqlDomain
 * @since CFDK 1.0
 */
public abstract class AbstractCosmosDirectDomain extends
		AbstractCosmosSqlDomain {

	/**
	 * 域初始化参数driver。
	 */
	private static final String DOMAIN_DRIVER = "driver";

	/**
	 * 域初始化参数url。
	 */
	private static final String DOMAIN_URL = "url";

	/**
	 * 域初始化参数user。
	 */
	private static final String DOMAIN_USER = "user";

	/**
	 * 域初始化参数password。
	 */
	private static final String DOMAIN_PASSWORD = "password";

	/**
	 * 域初始化参数url值。
	 */
	private String url = null;

	/**
	 * 域初始化参数user值。
	 */
	private String user = null;

	/**
	 * 域初始化参数password值。
	 */
	private String password = null;

	/**
	 * 构造函数。
	 * 
	 * @param name
	 *            域名称。
	 * @param type
	 *            域类型。
	 * @param index
	 *            域序号。
	 */
	public AbstractCosmosDirectDomain(String name, CosmosDomainType type,
			int index) {
		super(name, type, index);
	}

	/*
	 * 初始化域。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.domain.db.AbstractCosmosSqlDomain#initDomain()
	 */
	protected void initDomain() throws CosmosDomainException {
		super.initDomain();
		String driver = null;
		try {
			driver = config.getElementInitParameter(entryPath, DOMAIN_DRIVER);
			this.url = config.getElementInitParameter(entryPath, DOMAIN_URL);
			this.user = config.getElementInitParameter(entryPath, DOMAIN_USER);
			this.password = config.getElementInitParameter(entryPath,
					DOMAIN_PASSWORD);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 获得数据库连接。
	 * 
	 * @see com.microbrain.cosmos.core.domain.db.CosmosSqlDomain#getConnection()
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			if (user == null && password == null) {
				conn = DriverManager.getConnection(url);
			} else {
				conn = DriverManager.getConnection(url, user, password);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return conn;
	}

}
