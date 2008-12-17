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
 * ����<code>com.microbrain.cosmos.core.CosmosFactory</code>
 * ��һ��Ĭ��ʵ�֣�ͨ���������ļ������ñ������༴�ɡ���������Աϣ��ϵͳ���Գ�ʼ����������ݣ������������ļ�����Ҫ����������Ϣʱ��
 * </p>
 * <p>
 * ����ͨ����չ���࣬���ﵽ��չ���ܵ�Ŀ�ġ�
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
	 * ������ʵ����
	 */
	private Configuration config = null;

	/**
	 * ��������Ĺ����߼���
	 */
	private static final String COMMAND_MANAGER_KEY = "command-manager";

	/*
	 * ͨ�������ƺ��������ƻ�����
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
	 * ͨ���������ƴ������л�����
	 * 
	 * @see
	 * com.microbrain.cosmos.core.CosmosFactory#getCommand(java.lang.String)
	 */
	@Override
	public CosmosCommand getCommand(String name) throws CosmosFactoryException {
		return getCommand(null, name);
	}

	/*
	 * ������е������б�
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
	 * ������е�ȫ�������б�
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
	 * ������еı��������б�
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
	 * ���һ��<code>SAL</code>�ӿڵ�ʵ����
	 * 
	 * @see com.microbrain.cosmos.core.CosmosFactory#getService(java.lang.Class)
	 */
	@Override
	public <T> T getService(Class<?> clazz) throws CosmosFactoryException {
		return ServiceObjectManager.getServiceObject(clazz, this);
	}

	/*
	 * ��Cosmos������ע��һ��������ʵ����
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
	 * ����ע���������ʵ����
	 * 
	 * @see com.microbrain.cosmos.core.CosmosFactory#lookupConfig()
	 */
	@Override
	public Configuration lookupConfig() {
		return this.config;
	}

	/*
	 * ������֤��Ϣ�ػ��һ��<code>SAL</code>�ӿڵ�ʵ����
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
	 * ���һ������Ĺ����ߡ�
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
