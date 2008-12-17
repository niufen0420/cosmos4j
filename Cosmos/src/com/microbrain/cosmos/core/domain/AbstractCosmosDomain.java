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
 * <code>CosmosDomain</code>的抽象实现，实现了绝大多数功能，增加了两个抽象方法：<code>initLoader</code>和
 * <code>initDomain</code>分别用来初始化装载器和自定义初始化域。各类具体实现的域应该要实现这两个方法。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @since CFDK 1.0
 */
public abstract class AbstractCosmosDomain implements CosmosDomain {

	/**
	 * Cosmos工厂类实例。
	 */
	protected CosmosFactory factory = null;

	/**
	 * 配置类实例。
	 */
	protected Configuration config = null;

	/**
	 * 域名称。
	 */
	protected String name = null;

	/**
	 * 域类型。
	 */
	protected CosmosDomainType type = null;

	/**
	 * 域分类。
	 */
	protected String category = null;

	/**
	 * 域是否缓存。
	 */
	protected Boolean cachable = Boolean.FALSE;

	/**
	 * 域是否重载。
	 */
	protected Boolean reloadable = Boolean.FALSE;

	/**
	 * 域调试级别。
	 */
	protected String debug = null;

	/**
	 * 域序号。
	 */
	protected int index = -1;

	/**
	 * 域是否已经初始化。
	 */
	private Boolean inited = Boolean.FALSE;

	/**
	 * 域全局装载器。
	 */
	protected CosmosGlobalCommandLoader globalLoader = null;

	/**
	 * 域本地装载器。
	 */
	protected CosmosLocalCommandLoader localLoader = null;

	/**
	 * 域可用执行器映射。
	 */
	protected Map<String, CosmosExecuter> executerMap = new LinkedHashMap<String, CosmosExecuter>();

	/**
	 * 域所有命令映射。
	 */
	protected Map<String, CosmosCommand> commandMap = new LinkedHashMap<String, CosmosCommand>();

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
	public AbstractCosmosDomain(String name, CosmosDomainType type, int index) {
		this.name = name;
		this.type = type;
		this.index = index;
	}

	/*
	 * 获得可用的执行器。
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#availableExecuters()
	 */
	public Collection<CosmosExecuter> availableExecuters()
			throws CosmosDomainException {
		return executerMap.values();
	}

	/*
	 * 初始化域。
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
	 * 销毁域。
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#destroy()
	 */
	public void destroy() throws CosmosDomainException {
		executerMap.clear();
	}

	/*
	 * 获得一个命令。
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
	 * 列出所有的命令。
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
	 * 列出所有的全局命令。
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
	 * 列出本域下所有的本地命令。
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
	 * 获得域名称。
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#getName()
	 */
	public String getName() {
		return this.name;
	}

	/*
	 * 获得域类型。
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#getType()
	 */
	public CosmosDomainType getType() {
		return this.type;
	}

	/*
	 * 获得域分类。
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#getCategory()
	 */
	public String getCategory() {
		return this.category;
	}

	/*
	 * 获得域是否缓存。
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#isCachable()
	 */
	public Boolean isCachable() {
		return this.cachable;
	}

	/*
	 * 获得域是否重载。
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#isReloadable()
	 */
	public Boolean isReloadable() {
		return this.reloadable;
	}

	/*
	 * 获得域序号。
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#getIndex()
	 */
	public int getIndex() {
		return index;
	}

	/*
	 * 获得域的调试级别。
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
	 * 获得全局命令装载器。
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#getGlobalLoader()
	 */
	public CosmosGlobalCommandLoader getGlobalLoader() {
		return this.globalLoader;
	}

	/*
	 * 获得域本地命令装载器。
	 * 
	 * @see com.microbrain.cosmos.core.domain.CosmosDomain#getLocalLoader()
	 */
	public CosmosLocalCommandLoader getLocalLoader() {
		return this.localLoader;
	}

	/**
	 * 重新构建命令映射。
	 * 
	 * @throws CosmosDomainException
	 *             在构建命令映射时抛出的异常。
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
	 * 获得某个执行器。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.domain.CosmosDomain#getExecuter(java.lang.
	 * String)
	 */
	public CosmosExecuter getExecuter(String name) throws CosmosDomainException {
		return this.executerMap.get(name);
	}

	/**
	 * 初始化所有的执行器。
	 * 
	 * @throws CosmosDomainException
	 *             初始化执行器时可能出现的异常。
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
	 * 初始化装载器，根据不同域的特点，进行不同的初始化，每个具体实现的域都应该要实现这个方法。
	 * 
	 * @throws CosmosDomainException
	 *             初始化装载器时可能抛出的异常。
	 */
	protected abstract void initLoader() throws CosmosDomainException;

	/**
	 * 自定义初始化域，在各个具体域需要有特殊的内容需要初始化时实现本方法。
	 * 
	 * @throws CosmosDomainException
	 *             自定义初始化域时可能抛出的异常。
	 */
	protected abstract void initDomain() throws CosmosDomainException;

}
