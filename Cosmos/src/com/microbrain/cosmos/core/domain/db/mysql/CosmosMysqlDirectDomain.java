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
package com.microbrain.cosmos.core.domain.db.mysql;

import com.microbrain.cosmos.core.command.db.CosmosCatalogCommandLoader;
import com.microbrain.cosmos.core.command.db.CosmosDbGlobalCommandLoader;
import com.microbrain.cosmos.core.domain.CosmosDomainException;
import com.microbrain.cosmos.core.domain.CosmosDomainType;
import com.microbrain.cosmos.core.domain.db.AbstractCosmosDirectDomain;

/**
 * <p>
 * 采用JDBC直接链接MySQL数据库的域。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @see com.microbrain.cosmos.core.domain.db.CosmosSqlDomain
 * @see com.microbrain.cosmos.core.domain.AbstractCosmosDomain
 * @see com.microbrain.cosmos.core.domain.db.AbstractCosmosSqlDomain
 * @see com.microbrain.cosmos.core.domain.db.AbstractCosmosDirectDomain
 * @since CFDK 1.0
 */
public class CosmosMysqlDirectDomain extends AbstractCosmosDirectDomain {

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
	public CosmosMysqlDirectDomain(String name, CosmosDomainType type, int index) {
		super(name, type, index);
	}

	/*
	 * 初始化命令装载器。
	 * 
	 * @see com.microbrain.cosmos.core.domain.AbstractCosmosDomain#initLoader()
	 */
	@Override
	protected void initLoader() throws CosmosDomainException {
		this.globalLoader = new CosmosDbGlobalCommandLoader(this.factory, this);
		this.localLoader = new CosmosCatalogCommandLoader(this.factory, this);
	}

}
