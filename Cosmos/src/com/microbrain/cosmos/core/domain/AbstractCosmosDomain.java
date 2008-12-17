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
package com.microbrain.cosmos.core.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.CosmosExecuter;
import com.microbrain.cosmos.core.command.loaders.CosmosCommandLoaderException;
import com.microbrain.cosmos.core.command.loaders.CosmosGlobalCommandLoader;
import com.microbrain.cosmos.core.command.loaders.CosmosLocalCommandLoader;
import com.microbrain.cosmos.core.config.Configuration;
import com.microbrain.cosmos.core.config.ConfigurationException;
import com.microbrain.cosmos.core.constants.Constants;
import com.microbrain.cosmos.core.constants.CosmosCommandSource;
import com.microbrain.cosmos.core.constants.DebugLevel;

/**
 * <p>
 * <code>CosmosDomain</code>�ĳ���ʵ�֣�ʵ���˾���������ܣ��������������󷽷���<code>initLoader</code>��
 * <code>initDomain</code>�ֱ�������ʼ��װ�������Զ����ʼ���򡣸������ʵ�ֵ���Ӧ��Ҫʵ��������������
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @since CFDK 1.0
 */
public abstract class AbstractCosmosDomain implements CosmosDomain {

	/**
	 * Cosmos������ʵ����
	 */
	protected CosmosFactory factory = null;

	/**
	 * ������ʵ����
	 */
	protected Configuration config = null;

	/**
	 * �����ơ�
	 */
	protected String name = null;

	/**
	 * �����͡�
	 */
	protected CosmosDomainType type = null;

	/**
	 * ����ࡣ
	 */
	protected String category = null;

	/**
	 * ���Ƿ񻺴档
	 */
	protected Boolean cachable = Boolean.FALSE;

	/**
	 * ���Ƿ����ء�
	 */
	protected Boolean reloadable = Boolean.FALSE;

	/**
	 * ����Լ���
	 */
	protected String debug = null;

	/**
	 * ����š�
	 */
	protected int index = -1;

	/**
	 * ���Ƿ��Ѿ���ʼ����
	 */
	private Boolean inited = Boolean.FALSE;

	/**
	 * ��ȫ��װ������
	 */
	protected CosmosGlobalCommandLoader globalLoader = null;

	/**
	 * �򱾵�װ������
	 */
	protected CosmosLocalCommandLoader localLoader = null;

	/**
	 * �����ִ����ӳ�䡣
	 */
	protected Map<String, CosmosExecuter> executerMap = new LinkedHashMap<String, CosmosExecuter>();

	/**
	 * ����������ӳ�䡣
	 */
	protected Map<String, CosmosCommand> commandMap = new LinkedHashMap<String, CosmosCommand>();

	/**
	 * ���캯����
	 * 
	 * @param name
	 *            �����ơ�
	 * @param type
	 *            �����͡�
	 * @param index
	 *            ����š�
	 */
	public AbstractCosmosDomain(String name, CosmosDomainType type, int index) {
		this.name = name;
		this.type = type;
		this.index = index;
	}

	/*
	 * ��ÿ��õ�ִ������
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#availableExecuters()
	 */
	public Collection<CosmosExecuter> availableExecuters()
			throws CosmosDomainException {
		return executerMap.values();
	}

	/*
	 * ��ʼ����
	 * 
	 * @see
	 * com.microbrain.cosmos.core.domain.CosmosDomain#init(com.microbrain.cosmos
	 * .core.config.Configuration, com.microbrain.cosmos.core.CosmosFactory)
	 */
	public void init(Configuration config, CosmosFactory factory)
			throws CosmosDomainException {
		if (!inited) {
			this.factory = factory;
			this.config = config;
			this.category = config.getString(String.format(
					Constants.COSMOS_DOMAINS_DOMAIN_CATEGORY, this.index));
			this.cachable = config.getBoolean(String.format(
					Constants.COSMOS_DOMAINS_DOMAIN_CACHABLE, this.index));
			this.reloadable = config.getBoolean(String.format(
					Constants.COSMOS_DOMAINS_DOMAIN_RELOADABLE, this.index));
			this.debug = config.getString(String.format(
					Constants.COSMOS_DOMAINS_DOMAIN_DEBUG, this.index));

			initLoader();

			initExecuters();

			initDomain();

			if (this.cachable) {
				rebuildCommandMap();
			}

			inited = Boolean.TRUE;
		}
	}

	/*
	 * ������
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#destroy()
	 */
	public void destroy() throws CosmosDomainException {
		executerMap.clear();
	}

	/*
	 * ���һ�����
	 * 
	 * @see
	 * com.microbrain.cosmos.core.domain.CosmosDomain#getCommand(java.lang.String
	 * )
	 */
	public CosmosCommand getCommand(String name) throws CosmosDomainException {
		if (this.cachable && this.reloadable) {
			rebuildCommandMap();
		}

		if (this.cachable) {
			return commandMap.get(name);
		}

		CosmosDomain master = factory.getMasterDomain();
		if (master != null) {
			try {
				return master.getGlobalLoader().loadCommand(this, name);
			} catch (CosmosCommandLoaderException e) {
				throw new CosmosDomainException(e);
			}
		}

		return null;
	}

	/*
	 * �г����е����
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#listAllCommands()
	 */
	public Collection<CosmosCommand> listAllCommands()
			throws CosmosDomainException {
		if (this.cachable && this.reloadable) {
			rebuildCommandMap();
		}

		if (this.cachable) {
			return commandMap.values();
		}

		CosmosDomain master = factory.getMasterDomain();
		if (master != null) {
			try {
				return master.getGlobalLoader().loadDomainCommands(this);
			} catch (CosmosCommandLoaderException e) {
				throw new CosmosDomainException(e);
			}
		}

		return null;
	}

	/*
	 * �г����е�ȫ�����
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#listGlobalCommands()
	 */
	public Collection<CosmosCommand> listGlobalCommands()
			throws CosmosDomainException {
		if (this.cachable && this.reloadable) {
			rebuildCommandMap();
		}

		Collection<CosmosCommand> commands = new ArrayList<CosmosCommand>();
		if (this.cachable) {
			for (CosmosCommand command : commandMap.values()) {
				if (command.getSource() == CosmosCommandSource.GLOBAL) {
					commands.add(command);
				}
			}

			return commands;
		}

		CosmosDomain master = factory.getMasterDomain();
		if (master != null) {
			try {
				CosmosGlobalCommandLoader loader = master.getGlobalLoader();
				for (CosmosCommand command : loader.loadDomainCommands(this)) {
					if (command.getSource() == CosmosCommandSource.GLOBAL) {
						commands.add(command);
					}
				}
			} catch (CosmosCommandLoaderException e) {
				throw new CosmosDomainException(e);
			}
		}

		return commands;
	}

	/*
	 * �г����������еı������
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#listLocalCommands()
	 */
	public Collection<CosmosCommand> listLocalCommands()
			throws CosmosDomainException {
		if (this.cachable && this.reloadable) {
			rebuildCommandMap();
		}

		Collection<CosmosCommand> commands = new ArrayList<CosmosCommand>();
		if (this.cachable) {
			for (CosmosCommand command : commandMap.values()) {
				if (command.getSource() == CosmosCommandSource.LOCAL) {
					commands.add(command);
				}
			}

			return commands;
		}

		CosmosDomain master = factory.getMasterDomain();
		if (master != null) {
			try {
				CosmosGlobalCommandLoader loader = master.getGlobalLoader();
				for (CosmosCommand command : loader.loadDomainCommands(this)) {
					if (command.getSource() == CosmosCommandSource.LOCAL) {
						commands.add(command);
					}
				}
			} catch (CosmosCommandLoaderException e) {
				throw new CosmosDomainException(e);
			}
		}

		return commands;
	}

	/*
	 * ��������ơ�
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#getName()
	 */
	public String getName() {
		return this.name;
	}

	/*
	 * ��������͡�
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#getType()
	 */
	public CosmosDomainType getType() {
		return this.type;
	}

	/*
	 * �������ࡣ
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#getCategory()
	 */
	public String getCategory() {
		return this.category;
	}

	/*
	 * ������Ƿ񻺴档
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#isCachable()
	 */
	public Boolean isCachable() {
		return this.cachable;
	}

	/*
	 * ������Ƿ����ء�
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#isReloadable()
	 */
	public Boolean isReloadable() {
		return this.reloadable;
	}

	/*
	 * �������š�
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#getIndex()
	 */
	public int getIndex() {
		return index;
	}

	/*
	 * �����ĵ��Լ���
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#getDebugLevel()
	 */
	public DebugLevel getDebugLevel() {
		if (this.debug == null) {
			return DebugLevel.INFO;
		}

		return DebugLevel.valueOf(this.debug);
	}

	/*
	 * ���ȫ������װ������
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#getGlobalLoader()
	 */
	public CosmosGlobalCommandLoader getGlobalLoader() {
		return this.globalLoader;
	}

	/*
	 * ����򱾵�����װ������
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#getLocalLoader()
	 */
	public CosmosLocalCommandLoader getLocalLoader() {
		return this.localLoader;
	}

	/**
	 * ���¹�������ӳ�䡣
	 * 
	 * @throws CosmosDomainException
	 *             �ڹ�������ӳ��ʱ�׳����쳣��
	 */
	private void rebuildCommandMap() throws CosmosDomainException {
		commandMap.clear();

		CosmosDomain master = factory.getMasterDomain();
		if (master != null) {
			try {
				for (CosmosCommand command : master.getGlobalLoader()
						.loadDomainCommands(this)) {
					commandMap.put(command.getName(), command);
				}
			} catch (CosmosCommandLoaderException e) {
				throw new CosmosDomainException(e);
			}
		}
	}

	/*
	 * ���ĳ��ִ������
	 * 
	 * @see
	 * com.microbrain.cosmos.core.domain.CosmosDomain#getExecuter(java.lang.
	 * String)
	 */
	public CosmosExecuter getExecuter(String name) throws CosmosDomainException {
		return this.executerMap.get(name);
	}

	/**
	 * ��ʼ�����е�ִ������
	 * 
	 * @throws CosmosDomainException
	 *             ��ʼ��ִ����ʱ���ܳ��ֵ��쳣��
	 */
	private void initExecuters() throws CosmosDomainException {
		try {
			for (CosmosExecuter executer : this.config.getExecuters()) {
				String exeCategory = executer.getCategory();
				if (this.category == null || this.category.equals(exeCategory)) {
					this.executerMap.put(executer.getName(), executer);
				}
			}
		} catch (ConfigurationException e) {
			throw new CosmosDomainException(e);
		}
	}

	/**
	 * ��ʼ��װ���������ݲ�ͬ����ص㣬���в�ͬ�ĳ�ʼ����ÿ������ʵ�ֵ���Ӧ��Ҫʵ�����������
	 * 
	 * @throws CosmosDomainException
	 *             ��ʼ��װ����ʱ�����׳����쳣��
	 */
	protected abstract void initLoader() throws CosmosDomainException;

	/**
	 * �Զ����ʼ�����ڸ�����������Ҫ�������������Ҫ��ʼ��ʱʵ�ֱ�������
	 * 
	 * @throws CosmosDomainException
	 *             �Զ����ʼ����ʱ�����׳����쳣��
	 */
	protected abstract void initDomain() throws CosmosDomainException;

}
