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
package com.microbrain.cosmos.core;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.logging.Log;

import com.microbrain.cosmos.core.auth.Authorization;
import com.microbrain.cosmos.core.command.CosmosArgumentConverter;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.CosmosExecuter;
import com.microbrain.cosmos.core.config.Configuration;
import com.microbrain.cosmos.core.config.ConfigurationException;
import com.microbrain.cosmos.core.config.Plugin;
import com.microbrain.cosmos.core.domain.CosmosDomain;
import com.microbrain.cosmos.core.domain.CosmosDomainException;
import com.microbrain.cosmos.core.domain.CosmosDomainType;
import com.microbrain.cosmos.core.log.CosmosLogFactory;
import com.microbrain.cosmos.dev.CosmosCommandManager;

/**
 * <p>
 * <code>CosmosFactory</code> 是cosmos框架的核心工厂类，通过<code>CosmosFactory</code>的
 * <code>initFactory()</code>方法初始化读取系统配置文件， 并获得<code>CosmosFactory</code>
 * 工厂类的实例。一旦经过<code>initFactory</code>方法进行了初始化，以后就通过<code>getInstance()</code>
 * 这个静态方法， 来获取<code>CosmosFactory</code>在内存中的单一实例了。注意在一个应用运行的过程中，
 * <code>initFactory</code>方法只能被调用一次。 得到实例之后，可以获得对应的配置类
 * <code>com.microbrain.cosmos.core.config.Configuration</code>。最重要的是， 可以通过
 * <code>CosmosFactory</code>的实例利用名称和域来查找一个配置在配置文件，或者在数据库中的<code>Command</code>
 * 。因此，一个典型的调用过程看起来像这样：
 * </p>
 * <p>
 * <blockquote>
 * 
 * <pre>
 * Map&lt;String, Object&gt; args = new HashMap&lt;String, Object&gt;();
 * CosmosFactory factory = CosmosFactory.getInstance();
 * CosmosCommand command = factory.getCommand(&quot;domain&quot;, &quot;command&quot;);
 * command.execute(args);
 * ...
 * </pre>
 * 
 * </blockquote>
 * </p>
 * <p>
 * 需要二次开发的可以通过扩展<code>CosmosFactory</code>类来实现自己的工厂类，使用者可以增加自定义的功能， 将自定义实现的
 * <code>CosmosFactory</code>类配置在cosmos-config.xml文件中就可以使用该自定义工厂类了。<blockquote>
 * 
 * <pre>
 * &lt;cosmos factory=&quot;com.microbrain.cosmos.core.factory.DefaultCosmosFactoryImpl&quot;
 * 	configuration=&quot;com.microbrain.cosmos.core.config.DefaultConfigurationImpl&quot;&gt;
 * &lt;/cosmos&gt;
 * </pre>
 * 
 * </blockquote> 系统默认的CosmosFactory实现类是
 * <code>com.microbrain.cosmos.core.factory.DefaultCosmosFactoryImpl</code>
 * ，因此，也可以直接扩展这个默认实现，来达到自定义的目的。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.factory.DefaultCosmosFactoryImpl
 * @see com.microbrain.cosmos.core.factory.JndiCosmosFactory
 * @see com.microbrain.cosmos.core.config.Configuration
 * @since CFDK 1.0
 */
public abstract class CosmosFactory {

	/**
	 * 单例下保存的唯一实例的引用，在初始化的时候建立，以后直接通过<code>getInstance()</code>方法来获取。
	 */
	private static CosmosFactory instance = null;

	/**
	 * 默认的COSMOS主目录键
	 */
	private static final String HOME_KEY = "COSMOS_HOME";

	/**
	 * 单例使用的同步锁，在多线程环境中确保内存中仅有一个实例
	 */
	private static final Object locked = new Object();

	/**
	 * 日志记录器
	 */
	protected static final Log log = CosmosLogFactory.getLog();

	/**
	 * 私有化构造函数，确保在內存中单一实例。
	 */
	protected CosmosFactory() {
	}

	/**
	 * 本方法用来返回一个CosmosFactory的实例，在初始化CosmosFactory之后，
	 * 通常都是用这个方法来获得CosmosFactory的一个实例的。
	 * 
	 * @return CosmosFactory的实例。
	 * @throws CosmosFactoryException
	 *             当没有初始化或者初始化失败时，调用本方法试图获取一个CosmosFactory的实例会抛出此异常。
	 */
	public static CosmosFactory getInstance() throws CosmosFactoryException {
		if (instance == null) {
			synchronized (locked) {
				if (instance == null) {
					log.fatal("Cosmos factory initialize error.");
					throw new CosmosFactoryException(
							"Cosmos factory initialize error.");
				}
			}
		}

		return instance;
	}

	/**
	 * 返回Cosmos框架的配置实例类Configuration的一个引用。
	 * 
	 * @return Cosmos框架的配置类实例。
	 */
	public abstract Configuration lookupConfig();

	/**
	 * 设置Cosmos框架的配置实例类。
	 * 
	 * @param config
	 *            Cosmos框架的配置类实例。
	 */
	protected abstract void injectConfig(Configuration config);

	/**
	 * 通过一个配置类实例Configuration来初始化Cosmos框架。
	 * 
	 * @param config
	 *            Cosmos框架的配置类实例。
	 * @return 已经初始化好的CosmosFactory类实例。
	 * @throws CosmosFactoryException
	 *             初始化过程CosmosFactory的各种异常。
	 */
	public static CosmosFactory initFactory(Configuration config)
			throws CosmosFactoryException {
		String classString = config.getCosmosFactoryClass();
		Class<?> clazz = null;
		try {
			clazz = Class.forName(classString);

			instance = (CosmosFactory) clazz.newInstance();
		} catch (Exception e) {
			throw new CosmosFactoryException(
					"Initializing the cosmos factory has some error.", e);
		}

		instance.injectConfig(config);

		instance.initConverters(config);

		instance.initExecuters(config);

		instance.initDomains(config);

		instance.initPlugins(config);

		return instance;
	}

	/**
	 * 通过一个主目录和配置文件目录来初始化Cosmos框架。
	 * 
	 * @param homePath
	 *            Cosmos框架使用的主目录。
	 * @param configFileName
	 *            Cosmos框架的配置文件相对路径，相对于主目录而言
	 * @return 初始化之后的CosmosFactory实例。
	 * @throws IOException
	 *             读取配置文件的IO异常。
	 * @throws ConfigurationException
	 *             分析配置文件时候抛出的异常。
	 * @throws CosmosFactoryException
	 *             解析配置文件，以及初始化系统各种实例时抛出的异常。
	 */
	public static CosmosFactory initFactory(String homePath,
			String configFileName) throws IOException, ConfigurationException,
			CosmosFactoryException {
		Configuration config = Configuration.load(homePath + configFileName);
		config.setHomePath(homePath);
		System.setProperty(HOME_KEY, homePath);

		return initFactory(config);
	}

	/**
	 * 私有方法，在初始化框架时调用，初始化所有的Converter。
	 * 
	 * @param config
	 *            Cosmos框架的配置类实例。
	 * @throws CosmosFactoryException
	 *             初始化Converter时抛出该异常。
	 */
	private void initConverters(Configuration config)
			throws CosmosFactoryException {
		try {
			for (CosmosArgumentConverter converter : config.getConverters()) {
				converter.init(config, this);
			}
		} catch (ConfigurationException e) {
			throw new CosmosFactoryException("Factory Exception. ", e);
		}
	}

	/**
	 * 私有方法，在初始化框架时调用，初始化所有的Executer。
	 * 
	 * @param config
	 *            Cosmos框架的配置类实例。
	 * @throws CosmosFactoryException
	 *             初始化Converter时抛出该异常。
	 */
	private void initExecuters(Configuration config)
			throws CosmosFactoryException {
		try {
			for (CosmosExecuter executer : config.getExecuters()) {
				executer.init(this, config);
			}
		} catch (ConfigurationException e) {
			throw new CosmosFactoryException("Factory Exception. ", e);
		}
	}

	/**
	 * 私有方法，在初始化框架时调用，初始化所有的Domain。
	 * 
	 * @param config
	 *            Cosmos框架的配置类实例。
	 * @throws CosmosFactoryException
	 *             初始化Domain时抛出该异常。
	 */
	private void initDomains(Configuration config)
			throws CosmosFactoryException {
		try {
			config.getMasterDomain().init(config, this);

			for (CosmosDomain domain : config.getDomains().values()) {
				if (domain.getType() != CosmosDomainType.master) {
					domain.init(config, this);
				}
			}
		} catch (ConfigurationException e) {
			throw new CosmosFactoryException("Factory Exception. ", e);
		} catch (CosmosDomainException e) {
			throw new CosmosFactoryException("Factory Exception. ", e);
		}
	}

	/**
	 * 私有方法，在初始化框架时调用，初始化所有的Plugin。
	 * 
	 * @param config
	 *            Cosmos框架的配置类实例。
	 * @throws CosmosFactoryException
	 *             初始化Plugin时抛出该异常。
	 */
	private void initPlugins(Configuration config)
			throws CosmosFactoryException {
		try {
			for (Plugin plugin : config.getPlugins().values()) {
				plugin.init(config, this);
			}
		} catch (ConfigurationException e) {
			throw new CosmosFactoryException("Factory Exception. ", e);
		}
	}

	/**
	 * 返回配置文件中的所有Plugin。
	 * 
	 * @return 返回配置文件中所有的Plugin。
	 */
	public Map<String, Plugin> getPlugins() throws ConfigurationException {
		return lookupConfig().getPlugins();
	}

	/**
	 * 通过名称获得配置文件中的某一个Converter。
	 * 
	 * @param name
	 *            Converter的名字。
	 * @return 这个名称的Converter。
	 * @throws ConfigurationException
	 *             在读取配置文件时如果存在错误，则获取Converter时抛出该异常。
	 */
	public CosmosArgumentConverter getConverter(String name)
			throws ConfigurationException {
		return lookupConfig().getConvertersMap().get(name);
	}

	/**
	 * 通过jdbc type获得配置文件中的某一个Converter。
	 * 
	 * @param jdbcType
	 *            某一种jdbc类型，可以从java.sql.Types中获得。
	 * @return 关联这个jdbc类型的Converter。
	 * @throws ConfigurationException
	 *             在读取配置文件时如果存在错误，则获取Converter时抛出该异常。
	 */
	public CosmosArgumentConverter getConverter(int jdbcType)
			throws ConfigurationException {
		return lookupConfig().getConvertersMap().get(jdbcType);
	}

	/**
	 * 返回主Domain，在配置文件中只能配置一个主Domain，其他都应该是从Domain。
	 * 
	 * @return 返回配置文件中的主Domain。
	 */
	public CosmosDomain getMasterDomain() {
		return lookupConfig().getMasterDomain();
	}

	/**
	 * 通过domain和name来获得某个domain下的某个命令。
	 * 
	 * @param domain
	 *            配置文件中的某个domain名称。
	 * @param name
	 *            某个命令的名称。
	 * @return 返回该domain和name关联的命令。
	 * @throws CosmosFactoryException
	 *             获得一个Command时抛出该异常。
	 */
	public abstract CosmosCommand getCommand(String domain, String name)
			throws CosmosFactoryException;

	/**
	 * 通过name来获得主domain下的某个命令。
	 * 
	 * @param name
	 *            某个命令的名称。
	 * @return 返回该name关联的命令。
	 * @throws CosmosFactoryException
	 *             获得一个Command时抛出该异常。
	 */
	public abstract CosmosCommand getCommand(String name)
			throws CosmosFactoryException;

	/**
	 * 获得Cosmos框架中命令的管理类。
	 * 
	 * @return 命令管理类的实例。
	 * @throws CosmosFactoryException
	 *             获得一个CosmosCommandManager时抛出该异常。
	 */
	public abstract CosmosCommandManager getManager()
			throws CosmosFactoryException;

	/**
	 * 列出系统中有的所有命令。
	 * 
	 * @return 所有命令列表。
	 * @throws CosmosFactoryException
	 *             获得CosmosCommand列表时抛出该异常。
	 */
	public abstract Collection<CosmosCommand> listAllCommands()
			throws CosmosFactoryException;

	/**
	 * 列出系统中有的所有全局命令，全局命令是指存储在主Domain中的命令。
	 * 
	 * @return 返回所有的全局命令。
	 * @throws CosmosFactoryException
	 *             获得全局CosmosCommand列表时抛出该异常。
	 */
	public abstract Collection<CosmosCommand> listAllGlobalCommands()
			throws CosmosFactoryException;

	/**
	 * 列出系统中有的所有本地命令，本地命令是指无法存储在主Domain中，只能存储在各自Domain中的命令，比如：存储过程等。
	 * 
	 * @return 返回所有的本地命令。
	 * @throws CosmosFactoryException
	 *             获得本地CosmosCommand列表时抛出该异常。
	 */
	public abstract Collection<CosmosCommand> listAllLocalCommands()
			throws CosmosFactoryException;

	/**
	 * 获得一个SAL接口的实现Service实例。
	 * 
	 * @param <T>
	 *            该实例所描述的接口。
	 * @param clazz
	 *            该Service对应的接口。
	 * @return 返回该实例。
	 * @throws CosmosFactoryException
	 *             获得Service时抛出该异常。
	 */
	public abstract <T> T getService(Class<?> clazz)
			throws CosmosFactoryException;

	/**
	 * 获得一个SAL接口的实现Service实例，并加入用户信息，以便于底层确定该用户是否能调用某个方法。
	 * 
	 * @param <T>
	 *            该实例所描述的接口。
	 * @param auth
	 *            描述一个用户信息的认证类。
	 * @param clazz
	 *            该Service对应的接口。
	 * @return 返回该实例。
	 * @throws CosmosFactoryException
	 *             获得Service时抛出该异常。
	 */
	public abstract <T> T getService(Authorization auth, Class<?> clazz)
			throws CosmosFactoryException;

}
