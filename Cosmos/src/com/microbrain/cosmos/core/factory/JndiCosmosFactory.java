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

import java.util.Collection;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.CosmosFactoryException;
import com.microbrain.cosmos.core.auth.Authorization;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.config.Configuration;
import com.microbrain.cosmos.dev.CosmosCommandManager;

/**
 * <p>
 * 这是<code>com.microbrain.cosmos.core.CosmosFactory</code>
 * 的另外一个实现，试图将配置文件存储在JNDI中，以便于可以随时获取。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.CosmosFactory
 * @see com.microbrain.cosmos.core.factory.DefaultCosmosFactoryImpl
 * @see com.microbrain.cosmos.core.config.Configuration
 * @since CFDK 1.0
 */
public class JndiCosmosFactory extends CosmosFactory {

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
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 通过命令名称从主域中获得命令。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.CosmosFactory#getCommand(java.lang.String)
	 */
	@Override
	public CosmosCommand getCommand(String name) throws CosmosFactoryException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 获得一个<code>SAL</code>接口的实例。
	 * 
	 * @see com.microbrain.cosmos.core.CosmosFactory#getService(java.lang.Class)
	 */
	@Override
	public <T> T getService(Class<?> clazz) throws CosmosFactoryException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub

	}

	/*
	 * 查找注入的配置类实例。
	 * 
	 * @see com.microbrain.cosmos.core.CosmosFactory#lookupConfig()
	 */
	@Override
	public Configuration lookupConfig() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 获得所有的命令列表。
	 * 
	 * @see com.microbrain.cosmos.core.CosmosFactory#listAllCommands()
	 */
	@Override
	public Collection<CosmosCommand> listAllCommands()
			throws CosmosFactoryException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 获得所有的全局命令列表。
	 * 
	 * @see com.microbrain.cosmos.core.CosmosFactory#listAllGlobalCommands()
	 */
	@Override
	public Collection<CosmosCommand> listAllGlobalCommands()
			throws CosmosFactoryException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 获得所有的本地命令列表。
	 * 
	 * @see com.microbrain.cosmos.core.CosmosFactory#listAllLocalCommands()
	 */
	@Override
	public Collection<CosmosCommand> listAllLocalCommands()
			throws CosmosFactoryException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 获得一个命令的管理者。
	 * 
	 * @see com.microbrain.cosmos.core.CosmosFactory#getManager()
	 */
	@Override
	public CosmosCommandManager getManager() throws CosmosFactoryException {
		// TODO Auto-generated method stub
		return null;
	}

}
