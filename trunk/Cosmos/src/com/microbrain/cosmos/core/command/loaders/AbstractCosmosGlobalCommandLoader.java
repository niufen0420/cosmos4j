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
 * <code>CosmosGlobalCommandLoader</code>�ӿڵĳ���ʵ�֣����е�װ������Ӧ�ü̳��Ա��ࡣ
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
	 * Cosmos�����ࡣ
	 */
	protected CosmosFactory factory = null;

	/**
	 * Cosmos�����ࡣ
	 */
	protected Configuration config = null;

	/**
	 * װ����������
	 */
	protected CosmosDomain domain = null;

	/**
	 * ���캯����
	 * 
	 * @param factory
	 *            Cosmos�����ࡣ
	 * @param domain
	 *            ������
	 */
	public AbstractCosmosGlobalCommandLoader(CosmosFactory factory,
			CosmosDomain domain) {
		this.factory = factory;
		this.config = factory.lookupConfig();
		this.domain = domain;
	}

	/*
	 * װ�����е����
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
	 * װ��ĳ�����
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
	 * װ��ĳ�����µ��������
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
	 * װ�����и����
	 * 
	 * @return ���������б�
	 * @throws CosmosCommandLoaderException
	 *             װ�ع������׳����쳣��
	 */
	protected abstract Collection<CosmosCommand> loadRootCommands()
			throws CosmosCommandLoaderException;

	/**
	 * װ��ĳ���������и����
	 * 
	 * @param domain
	 *            ������
	 * @return ���ظ����������б�
	 * @throws CosmosCommandLoaderException
	 *             װ�ع������׳����쳣��
	 */
	protected abstract Collection<CosmosCommand> loadDomainRootCommands(
			CosmosDomain domain) throws CosmosCommandLoaderException;

	/**
	 * װ��һ�����
	 * 
	 * @param domain
	 *            ������������
	 * @param name
	 *            ���������ơ�
	 * @return ���ظ����
	 * @throws CosmosCommandLoaderException
	 *             װ�ع������׳����쳣��
	 */
	protected abstract CosmosCommand loadSingleCommand(CosmosDomain domain,
			String name) throws CosmosCommandLoaderException;

	/**
	 * ���װ��ĳ�������������������γ���������νṹ��
	 * 
	 * @param command
	 *            Ҫ���װ�ص����
	 * @param loadedCommands
	 *            �Ѿ�װ�ص��������
	 * @return ���װ��֮��ĸ����
	 * @throws CosmosCommandLoaderException
	 *             װ�ع������׳����쳣��
	 */
	protected abstract CosmosCompositeCommand deepLoadCommand(
			CosmosCompositeCommand command,
			Collection<CosmosCommand> loadedCommands)
			throws CosmosCommandLoaderException;

}
