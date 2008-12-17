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

import java.util.Collection;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.composite.CosmosCompositeCommand;
import com.microbrain.cosmos.core.config.Configuration;
import com.microbrain.cosmos.core.config.ConfigurationException;
import com.microbrain.cosmos.core.domain.CosmosDomain;

/**
 * <p>
 * <code>CosmosGlobalCommandLoader</code>接口的抽象实现，所有的装载器都应该继承自本类。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.loaders.CosmosGlobalCommandLoader
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @since CFDK 1.0
 */
public abstract class AbstractCosmosGlobalCommandLoader implements
		CosmosGlobalCommandLoader {

	/**
	 * Cosmos工厂类。
	 */
	protected CosmosFactory factory = null;

	/**
	 * Cosmos配置类。
	 */
	protected Configuration config = null;

	/**
	 * 装载器所属域。
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
	public AbstractCosmosGlobalCommandLoader(CosmosFactory factory,
			CosmosDomain domain) {
		this.factory = factory;
		this.config = factory.lookupConfig();
		this.domain = domain;
	}

	/*
	 * 装载所有的命令。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.loaders.CosmosGlobalCommandLoader#
	 * loadAllCommands()
	 */
	public Collection<CosmosCommand> loadAllCommands()
			throws CosmosCommandLoaderException {
		Collection<CosmosCommand> commands = loadRootCommands();
		try {
			for (CosmosDomain domain : config.getDomains().values()) {
				CosmosLocalCommandLoader localLoader = domain.getLocalLoader();
				commands.addAll(localLoader.loadAllLocalRootCommands());
			}
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (CosmosCommand command : commands) {
			if (command instanceof CosmosCompositeCommand) {
				deepLoadCommand((CosmosCompositeCommand) command, commands);
			}
		}

		return commands;
	}

	/*
	 * 装载某个命令。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.loaders.CosmosGlobalCommandLoader#
	 * loadCommand(com.microbrain.cosmos.core.domain.CosmosDomain,
	 * java.lang.String)
	 */
	public CosmosCommand loadCommand(CosmosDomain domain, String name)
			throws CosmosCommandLoaderException {
		Collection<CosmosCommand> commands = loadRootCommands();
		try {
			for (CosmosDomain cosmosDomain : config.getDomains().values()) {
				CosmosLocalCommandLoader localLoader = cosmosDomain
						.getLocalLoader();
				commands.addAll(localLoader.loadAllLocalRootCommands());
			}
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CosmosCommand command = loadSingleCommand(domain, name);
		if (command == null) {
			command = domain.getLocalLoader().loadLocalRootCommand(name);
		}

		if (command != null && command instanceof CosmosCompositeCommand) {
			deepLoadCommand((CosmosCompositeCommand) command, commands);
		}

		return command;
	}

	/*
	 * 装载某个域下的所有命令。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.loaders.CosmosGlobalCommandLoader#
	 * loadDomainCommands(com.microbrain.cosmos.core.domain.CosmosDomain)
	 */
	public Collection<CosmosCommand> loadDomainCommands(CosmosDomain domain)
			throws CosmosCommandLoaderException {
		Collection<CosmosCommand> commands = loadDomainRootCommands(domain);
		Collection<CosmosCommand> loadedCommands = loadRootCommands();
		try {
			for (CosmosDomain cosmosDomain : config.getDomains().values()) {
				CosmosLocalCommandLoader localLoader = cosmosDomain
						.getLocalLoader();
				Collection<CosmosCommand> localCommands = localLoader
						.loadAllLocalRootCommands();
				loadedCommands.addAll(localCommands);
				if (cosmosDomain == domain) {
					commands.addAll(localCommands);
				}
			}
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (CosmosCommand command : commands) {
			if (command instanceof CosmosCompositeCommand) {
				deepLoadCommand((CosmosCompositeCommand) command,
						loadedCommands);
			}
		}

		return commands;
	}

	/**
	 * 装载所有根命令。
	 * 
	 * @return 返回命令列表。
	 * @throws CosmosCommandLoaderException
	 *             装载过程中抛出的异常。
	 */
	protected abstract Collection<CosmosCommand> loadRootCommands()
			throws CosmosCommandLoaderException;

	/**
	 * 装载某个域下所有根命令。
	 * 
	 * @param domain
	 *            所属域。
	 * @return 返回该域下命令列表。
	 * @throws CosmosCommandLoaderException
	 *             装载过程中抛出的异常。
	 */
	protected abstract Collection<CosmosCommand> loadDomainRootCommands(
			CosmosDomain domain) throws CosmosCommandLoaderException;

	/**
	 * 装载一个命令。
	 * 
	 * @param domain
	 *            该命令所属域。
	 * @param name
	 *            该命令名称。
	 * @return 返回该命令。
	 * @throws CosmosCommandLoaderException
	 *             装载过程中抛出的异常。
	 */
	protected abstract CosmosCommand loadSingleCommand(CosmosDomain domain,
			String name) throws CosmosCommandLoaderException;

	/**
	 * 深度装载某个命令的所有子命令，并形成命令的树形结构。
	 * 
	 * @param command
	 *            要深度装载的命令。
	 * @param loadedCommands
	 *            已经装载的所有命令。
	 * @return 深度装载之后的该命令。
	 * @throws CosmosCommandLoaderException
	 *             装载过程中抛出的异常。
	 */
	protected abstract CosmosCompositeCommand deepLoadCommand(
			CosmosCompositeCommand command,
			Collection<CosmosCommand> loadedCommands)
			throws CosmosCommandLoaderException;

}
