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
package com.microbrain.cosmos.core.command.config;

import java.util.ArrayList;
import java.util.Collection;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.composite.CosmosCompositeCommand;
import com.microbrain.cosmos.core.command.loaders.AbstractCosmosGlobalCommandLoader;
import com.microbrain.cosmos.core.command.loaders.CosmosCommandLoaderException;
import com.microbrain.cosmos.core.domain.CosmosDomain;

/**
 * <p>
 * 读取配置文件的全局命令装载器，实现了<code>CosmosGlobalCommandLoader</code>接口，主要的功能是从各个域的配置文件中
 * 读取相应的命令，并组装成可以执行的单元。
 * </p>
 * <p>
 * <code>CosmosConfigGlobalCommandLoader</code>实现了深度装载一个命令的所有树形子命令的方法，
 * 装载某个域下所有命令的方法，装载系统所有命令的方法，装载某个域下单个命令的方法。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.loaders.CosmosGlobalCommandLoader
 * @see com.microbrain.cosmos.core.command.loaders.AbstractCosmosGlobalCommandLoader
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @since CFDK 1.0
 */
public class CosmosConfigGlobalCommandLoader extends
		AbstractCosmosGlobalCommandLoader {

	/**
	 * 构造函数。
	 * 
	 * @param factory
	 *            Cosmos工厂类。
	 * @param domain
	 *            本装载器所属域。
	 */
	public CosmosConfigGlobalCommandLoader(CosmosFactory factory,
			CosmosDomain domain) {
		super(factory, domain);
	}

	/*
	 * 深度装载一个命令的所有子命令，目前不支持子命令，因此，直接返回该命令。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.loaders.AbstractCosmosGlobalCommandLoader
	 * #deepLoadCommand(com.microbrain.cosmos.core.command.composite.
	 * CosmosCompositeCommand, java.util.Collection)
	 */
	@Override
	protected CosmosCompositeCommand deepLoadCommand(
			CosmosCompositeCommand command,
			Collection<CosmosCommand> loadedCommands)
			throws CosmosCommandLoaderException {
		return command;
	}

	/*
	 * 装载某个域下所有的命令，目前版本不支持本方法。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.loaders.AbstractCosmosGlobalCommandLoader
	 * #loadDomainRootCommands(com.microbrain.cosmos.core.domain.CosmosDomain)
	 */
	@Override
	protected Collection<CosmosCommand> loadDomainRootCommands(
			CosmosDomain domain) throws CosmosCommandLoaderException {
		return new ArrayList<CosmosCommand>(0);
	}

	/*
	 * 装载所有的命令，目前版本不支持本方法。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.loaders.AbstractCosmosGlobalCommandLoader
	 * #loadRootCommands()
	 */
	@Override
	protected Collection<CosmosCommand> loadRootCommands()
			throws CosmosCommandLoaderException {
		return new ArrayList<CosmosCommand>(0);
	}

	/*
	 * 装载某个命令，目前版本不支持此方法。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.loaders.AbstractCosmosGlobalCommandLoader
	 * #loadSingleCommand(com.microbrain.cosmos.core.domain.CosmosDomain,
	 * java.lang.String)
	 */
	@Override
	protected CosmosCommand loadSingleCommand(CosmosDomain domain, String name)
			throws CosmosCommandLoaderException {
		return null;
	}

}
