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
package com.microbrain.cosmos.dev;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.config.Configuration;

/**
 * <p>
 * <code>com.microbrain.cosmos.dev.CosmosCommandManager</code>的抽象实现类，封装了
 * <code>Cosmos</code>工厂类和配置类。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.dev.CosmosCommandManager
 * @see com.microbrain.cosmos.core.CosmosFactory
 * @since CFDK 1.0
 */
public abstract class AbstractCosmosCommandManager implements
		CosmosCommandManager {

	/**
	 * <code>Cosmos</code>工厂类。
	 */
	protected CosmosFactory factory = null;

	/**
	 * 配置类。
	 */
	protected Configuration config = null;

	/**
	 * 构造函数。
	 * 
	 * @param factory
	 *            工厂类。
	 */
	public AbstractCosmosCommandManager(CosmosFactory factory) {
		this.factory = factory;
		this.config = factory.lookupConfig();
	}

}
