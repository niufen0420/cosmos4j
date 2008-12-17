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
package com.microbrain.cosmos.core.domain.config;

import com.microbrain.cosmos.core.command.config.CosmosConfigGlobalCommandLoader;
import com.microbrain.cosmos.core.command.config.CosmosConfigLocalCommandLoader;
import com.microbrain.cosmos.core.command.loaders.CosmosGlobalCommandLoader;
import com.microbrain.cosmos.core.command.loaders.CosmosLocalCommandLoader;
import com.microbrain.cosmos.core.domain.AbstractCosmosDomain;
import com.microbrain.cosmos.core.domain.CosmosDomainException;
import com.microbrain.cosmos.core.domain.CosmosDomainType;

/**
 * <p>
 * 配置文件域，读取配置文件，在后续的版本中将提供访问配置文件的执行器。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @see com.microbrain.cosmos.core.domain.AbstractCosmosDomain
 * @since CFDK 1.0
 */
public class CosmosConfigurationDomain extends AbstractCosmosDomain {

	/**
	 * 全局命令装载器。
	 */
	private CosmosGlobalCommandLoader globalLoader = null;

	/**
	 * 本地命令装载器。
	 */
	private CosmosLocalCommandLoader localLoader = null;

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
	public CosmosConfigurationDomain(String name, CosmosDomainType type,
			int index) {
		super(name, type, index);
	}

	/*
	 * 初始化域。
	 * 
	 * @see com.microbrain.cosmos.core.domain.AbstractCosmosDomain#initDomain()
	 */
	@Override
	protected void initDomain() throws CosmosDomainException {
	}

	/*
	 * 初始化装载器。
	 * 
	 * @see com.microbrain.cosmos.core.domain.AbstractCosmosDomain#initLoader()
	 */
	@Override
	protected void initLoader() throws CosmosDomainException {
		this.globalLoader = new CosmosConfigGlobalCommandLoader(this.factory,
				this);
		this.localLoader = new CosmosConfigLocalCommandLoader(this.factory,
				this);
	}

	/*
	 * 获得全局装载器。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.domain.AbstractCosmosDomain#getGlobalLoader()
	 */
	public CosmosGlobalCommandLoader getGlobalLoader() {
		return this.globalLoader;
	}

	/*
	 * 获得本地装载器。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.domain.AbstractCosmosDomain#getLocalLoader()
	 */
	public CosmosLocalCommandLoader getLocalLoader() {
		return this.localLoader;
	}

}
