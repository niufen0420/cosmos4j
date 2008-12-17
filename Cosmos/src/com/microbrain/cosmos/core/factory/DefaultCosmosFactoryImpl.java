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
package com.microbrain.cosmos.core.factory;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.CosmosFactoryException;
import com.microbrain.cosmos.core.auth.Authorization;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.config.Configuration;
import com.microbrain.cosmos.core.config.ConfigurationException;
import com.microbrain.cosmos.core.constants.Constants;
import com.microbrain.cosmos.core.domain.CosmosDomain;
import com.microbrain.cosmos.core.domain.CosmosDomainException;
import com.microbrain.cosmos.core.sal.ServiceObjectManager;
import com.microbrain.cosmos.dev.CosmosCommandManager;

/**
 * <p>
 * 这是<code>com.microbrain.cosmos.core.CosmosFactory</code>
 * 的一个默认实现，通常在配置文件中配置本工厂类即可。当开发人员希望系统可以初始化更多的内容，或者在配置文件中需要启动更多信息时，
 * </p>
 * <p>
 * 可以通过扩展此类，来达到扩展功能的目的。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.CosmosFactory
 * @see com.microbrain.cosmos.core.factory.JndiCosmosFactory
 * @see com.microbrain.cosmos.core.config.Configuration
 * @since CFDK 1.0
 */
public class DefaultCosmosFactoryImpl extends CosmosFactory {

	/**
	 * 配置类实例。
	 */
	private Configuration config = null;

	/**
	 * 管理命令的管理者键。
	 */
	private static final String COMMAND_MANAGER_KEY = "command-manager";

	/*
	 * 通过域名称和命令名称获得命令。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.CosmosFactory#getCommand(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public CosmosCommand getCommand(String domain, String name)
			throws CosmosFactoryException {
		CosmosDomain cosmosDomain = null;

		if (domain == null) {
			cosmosDomain = getMasterDomain();
		} else {
			try {
				cosmosDomain = this.config.getDomains().get(domain);
			} catch (ConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (cosmosDomain == null) {
			return null;
		}

		try {
			return cosmosDomain.getCommand(name);
		} catch (CosmosDomainException e) {
			throw new CosmosFactoryException(e);
		}
	}

	/*
	 * 通过命令名称从主域中获得命令。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.CosmosFactory#getCommand(java.lang.String)
	 */
	@Override
	public CosmosCommand getCommand(String name) throws CosmosFactoryException {
		return getCommand(null, name);
	}

	/*
	 * 获得所有的命令列表。
	 * 
	 * @see com.microbrain.cosmos.core.CosmosFactory#listAllCommands()
	 */
	@Override
	public Collection<CosmosCommand> listAllCommands()
			throws CosmosFactoryException {
		Collection<CosmosCommand> commands = new ArrayList<CosmosCommand>();
		try {
			for (CosmosDomain domain : this.config.getDomains().values()) {
				for (CosmosCommand command : domain.listAllCommands()) {
					commands.add(command);
				}
			}
		} catch (ConfigurationException e) {
			throw new CosmosFactoryException(e);
		} catch (CosmosDomainException e) {
			throw new CosmosFactoryException(e);
		}

		return commands;
	}

	/*
	 * 获得所有的全局命令列表。
	 * 
	 * @see com.microbrain.cosmos.core.CosmosFactory#listAllGlobalCommands()
	 */
	@Override
	public Collection<CosmosCommand> listAllGlobalCommands()
			throws CosmosFactoryException {
		Collection<CosmosCommand> commands = new ArrayList<CosmosCommand>();
		try {
			for (CosmosDomain domain : config.getDomains().values()) {
				commands.addAll(domain.listGlobalCommands());
			}
		} catch (Exception e) {
			throw new CosmosFactoryException(e);
		}

		return commands;
	}

	/*
	 * 获得所有的本地命令列表。
	 * 
	 * @see com.microbrain.cosmos.core.CosmosFactory#listAllLocalCommands()
	 */
	@Override
	public Collection<CosmosCommand> listAllLocalCommands()
			throws CosmosFactoryException {
		Collection<CosmosCommand> commands = new ArrayList<CosmosCommand>();
		try {
			for (CosmosDomain domain : config.getDomains().values()) {
				commands.addAll(domain.listLocalCommands());
			}
		} catch (Exception e) {
			throw new CosmosFactoryException(e);
		}

		return commands;
	}

	/*
	 * 获得一个<code>SAL</code>接口的实例。
	 * 
	 * @see com.microbrain.cosmos.core.CosmosFactory#getService(java.lang.Class)
	 */
	@Override
	public <T> T getService(Class<?> clazz) throws CosmosFactoryException {
		return ServiceObjectManager.getServiceObject(clazz, this);
	}

	/*
	 * 向Cosmos工厂中注入一个配置类实例。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.CosmosFactory#injectConfig(com.microbrain.
	 * cosmos.core.config.Configuration)
	 */
	@Override
	protected void injectConfig(Configuration config) {
		this.config = config;
	}

	/*
	 * 查找注入的配置类实例。
	 * 
	 * @see com.microbrain.cosmos.core.CosmosFactory#lookupConfig()
	 */
	@Override
	public Configuration lookupConfig() {
		return this.config;
	}

	/*
	 * 带有认证信息地获得一个<code>SAL</code>接口的实例。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.CosmosFactory#getService(com.microbrain.cosmos
	 * .core.auth.Authorization, java.lang.Class)
	 */
	@Override
	public <T> T getService(Authorization auth, Class<?> clazz)
			throws CosmosFactoryException {
		return ServiceObjectManager.getServiceObject(auth, clazz, this);
	}

	/*
	 * 获得一个命令的管理者。
	 * 
	 * @see com.microbrain.cosmos.core.CosmosFactory#getManager()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public CosmosCommandManager getManager() throws CosmosFactoryException {
		CosmosDomain master = getMasterDomain();
		CosmosCommandManager manager = null;
		try {
			String managerClass = config.getElementInitParameter(String.format(
					Constants.COSMOS_DOMAINS_DOMAIN_INDEX, master.getIndex()),
					COMMAND_MANAGER_KEY);
			Class<CosmosCommandManager> clazz = (Class<CosmosCommandManager>) Class
					.forName(managerClass);
			Constructor<CosmosCommandManager> constructor = clazz
					.getConstructor(CosmosFactory.class);
			manager = constructor.newInstance(this);
		} catch (Exception e) {
			throw new CosmosFactoryException(e);
		}

		return manager;
	}

}
