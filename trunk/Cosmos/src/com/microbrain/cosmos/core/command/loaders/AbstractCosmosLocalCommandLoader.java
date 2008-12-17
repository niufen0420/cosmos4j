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
package com.microbrain.cosmos.core.command.loaders;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.config.Configuration;
import com.microbrain.cosmos.core.domain.CosmosDomain;

/**
 * <p>
 * <code>CosmosLocalCommandLoader</code>接口的抽象实现，所有的本地装载器都应该扩展自本抽象类。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.loaders.CosmosLocalCommandLoader
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @since CFDK 1.0
 */
public abstract class AbstractCosmosLocalCommandLoader implements
		CosmosLocalCommandLoader {

	/**
	 * Cosmos工厂类。
	 */
	protected CosmosFactory factory = null;

	/**
	 * 框架配置类。
	 */
	protected Configuration config = null;

	/**
	 * 所属域。
	 */
	protected CosmosDomain domain = null;

	/**
	 * 构造函数。
	 * 
	 * @param factory
	 *            Cosmos工厂类。
	 * @param domain
	 *            所属域。
	 */
	public AbstractCosmosLocalCommandLoader(CosmosFactory factory,
			CosmosDomain domain) {
		this.factory = factory;
		this.config = factory.lookupConfig();
		this.domain = domain;
	}

}
