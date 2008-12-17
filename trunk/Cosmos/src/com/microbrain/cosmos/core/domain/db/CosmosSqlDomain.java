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
 * 数据库域接口，扩展自默认的<code>CosmosDomain</code>，提供了访问数据库链接的方法等。
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
	 * 获得方案。
	 * 
	 * @return 方案。
	 */
	public String getSchema();

	/**
	 * 获得存储过程执行器。
	 * 
	 * @return 存储过程执行器。
	 */
	public CosmosCallableExecuter findCallExecuter();

	/**
	 * 获得数据库连接。
	 * 
	 * @return 数据库连接。
	 * @throws SQLException
	 *             抛出数据库连接异常。
	 */
	public Connection getConnection() throws SQLException;

}
