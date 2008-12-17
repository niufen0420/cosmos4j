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
package com.microbrain.cosmos.core.config;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationFactory;
import org.apache.commons.logging.Log;

import com.microbrain.cosmos.core.command.CosmosArgumentConverter;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.CosmosExecuter;
import com.microbrain.cosmos.core.command.CosmosMetaCommand;
import com.microbrain.cosmos.core.constants.Constants;
import com.microbrain.cosmos.core.domain.CosmosDomain;
import com.microbrain.cosmos.core.domain.CosmosDomainType;
import com.microbrain.cosmos.core.log.CosmosLogFactory;

/**
 * <p>
 * <code>Configuration</code>是Cosmos框架的配置文件解析类。用来读取配置文件，解析，并获得系统所需的各种配置。
 * 实现基于apache的common-configuration框架。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.config.Plugin
 * @see com.microbrain.cosmos.core.config.ConfigurationException
 * @since CFDK 1.0
 */
public abstract class Configuration {

	/**
	 * common-configuration框架的配置类实例。
	 */
	protected org.apache.commons.configuration.Configuration config = null;

	/**
	 * 所有已配置的插件映射。
	 */
	protected Map<String, Plugin> plugins = null;

	/**
	 * 所有已配置的域映射。
	 */
	protected Map<String, CosmosDomain> domainMap = null;

	/**
	 * 所有已配置的类型转换器映射。
	 */
	protected Map<Object, CosmosArgumentConverter> converterMap = null;

	/**
	 * 所有已配置的类型转换器列表。
	 */
	protected List<CosmosArgumentConverter> converters = null;

	/**
	 * 所有已配置的执行器。
	 */
	protected Collection<CosmosExecuter> executers = null;

	/**
	 * 所有已配置的命令类型映射。
	 */
	protected Map<String, CosmosMetaCommand> commandTypeMap = null;

	/**
	 * master域引用。
	 */
	protected CosmosDomain master = null;

	/**
	 * 默认的命令类型。
	 */
	protected CosmosMetaCommand defaultCommandType = null;

	/**
	 * Cosmos框架的主目录。
	 */
	private String homePath = null;

	/**
	 * 认证工厂类实现。
	 */
	private String authFactory = null;

	/**
	 * 认证类实现。
	 */
	private String authClass = null;

	/**
	 * 权限工厂类实现。
	 */
	private String permFactory = null;

	/**
	 * 权限类实现。
	 */
	private String permClass = null;

	/**
	 * 日志记录对象。
	 */
	private static final Log log = CosmosLogFactory.getLog();

	/**
	 * 保护默认构造函数，使其他人无法调用。
	 */
	protected Configuration() {
	}

	/**
	 * 装载配置文件的方法。
	 * 
	 * @param file
	 *            装载的配置文件路径。
	 * @return 已经解析好的配置类。
	 * @throws IOException
	 *             读写IO时抛出的异常。
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	public static Configuration load(String file) throws IOException,
			ConfigurationException {
		ConfigurationFactory factory = new ConfigurationFactory(file);
		org.apache.commons.configuration.Configuration conf = null;
		try {
			conf = factory.getConfiguration();
		} catch (org.apache.commons.configuration.ConfigurationException e) {
			log.error("initializing config file. ", e);
			throw new ConfigurationException("initializing config file. ", e);
		}

		String configClass = conf.getString(Constants.CONFIGURATION_CLASS_KEY);
		Configuration configuration = null;
		try {
			configuration = (Configuration) Class.forName(configClass)
					.newInstance();
		} catch (Exception e) {
			log.error("initializing config instance. ", e);
			throw new ConfigurationException("initializing config instance. ",
					e);
		}

		configuration.setConfig(conf);

		configuration.initEnvironment();

		return configuration;
	}

	/**
	 * 通过File类来装载一个配置文件。
	 * 
	 * @param file
	 *            配置文件。
	 * @return 装载好的配置文件类。
	 * @throws IOException
	 *             读写IO时抛出的异常。
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	public static Configuration load(File file) throws IOException,
			ConfigurationException {
		return load(file.getPath());
	}

	/**
	 * 通过配置文件的URL来装载一个配置文件。
	 * 
	 * @param file
	 *            配置文件URL。
	 * @return 装载好的配置文件类。
	 * @throws IOException
	 *             读写IO时抛出的异常。
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	public static Configuration load(URL file) throws IOException,
			ConfigurationException {
		return load(file.toString());
	}

	/**
	 * 初始化所有的环境。
	 * 
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	private void initEnvironment() throws ConfigurationException {
		loadAuthAndPerm();

		loadConverters();

		loadCommandTypes();

		loadExecuters();

		loadDomains();

		loadPlugins();

		loadExtensions();
	}

	/**
	 * 获得Cosmos工厂类的具体实现类名称。
	 * 
	 * @return Cosmos工厂类的具体实现类名称。
	 */
	public String getCosmosFactoryClass() {
		return config.getString(Constants.FACTORY_CLASS_KEY);
	}

	/**
	 * 获得master域的引用。
	 * 
	 * @return master域引用。
	 */
	public CosmosDomain getMasterDomain() {
		return this.master;
	}

	/**
	 * 根据path获得配置文件中的一个字符串。
	 * 
	 * @param path
	 *            路径。
	 * @return 该路径对应的字符串。
	 */
	public String getString(String path) {
		return this.config.getString(path);
	}

	/**
	 * 根据path获得配置文件中的一个布尔值。
	 * 
	 * @param path
	 *            路径。
	 * @return 该路径对应的布尔值。
	 */
	public Boolean getBoolean(String path) {
		return this.config.getBoolean(path);
	}

	/**
	 * 获得系统的所有插件。
	 * 
	 * @return 系统配置的所有插件。
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	public Map<String, Plugin> getPlugins() throws ConfigurationException {
		return this.plugins;
	}

	/**
	 * 获得系统配置的所有域。
	 * 
	 * @return 所有域。
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	public Map<String, CosmosDomain> getDomains() throws ConfigurationException {
		return this.domainMap;
	}

	/**
	 * 获得系统配置的所有类型转换器。
	 * 
	 * @return 系统配置的所有类型转换器。
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	public Map<Object, CosmosArgumentConverter> getConvertersMap()
			throws ConfigurationException {
		return this.converterMap;
	}

	/**
	 * 获得所有类型转换器。
	 * 
	 * @return 所有的类型转换器。
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	public Collection<CosmosArgumentConverter> getConverters()
			throws ConfigurationException {
		return this.converters;
	}

	/**
	 * 获得所有的命令类型。
	 * 
	 * @return 所有的命令类型。
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	public Map<String, CosmosMetaCommand> getCommandTypes()
			throws ConfigurationException {
		return this.commandTypeMap;
	}

	/**
	 * 获得默认的命令类型。
	 * 
	 * @return 默认的命令类型。
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	public CosmosMetaCommand getDefaultCommandType()
			throws ConfigurationException {
		return this.defaultCommandType;
	}

	/**
	 * 获得所有可用的执行器。
	 * 
	 * @return 所有可用的执行器。
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	public Collection<CosmosExecuter> getExecuters()
			throws ConfigurationException {
		return this.executers;
	}

	/**
	 * 获得某个元素的某个初始化参数。
	 * 
	 * @param elementPath
	 *            元素路径。
	 * @param key
	 *            初始化参数键。
	 * @return 返回key对应的初始化参数值。
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	public String getElementInitParameter(String elementPath, String key)
			throws ConfigurationException {
		Collection<String> names = getByName(elementPath + ".init-param");

		String value = null;
		int i = 0;
		for (String name : names) {
			if (key.equals(name)) {
				value = this.config.getString(elementPath + ".init-param(" + i
						+ ").[@value]");
				break;
			}

			i++;
		}

		return value;
	}

	/**
	 * 获得某个元素的所有初始化参数。
	 * 
	 * @param elementPath
	 *            元素路径。
	 * @param key
	 *            初始化参数键。
	 * @return 返回key对应的初始化参数值。
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	public Collection<String> getElementInitParameters(String elementPath,
			String key) throws ConfigurationException {
		Collection<String> names = getByName(elementPath + ".init-param");
		List<String> values = new ArrayList<String>();
		String value = null;
		int i = 0;
		for (String name : names) {
			if (key.equals(name)) {
				value = this.config.getString(elementPath + ".init-param(" + i
						+ ").[@value]");
				values.add(value);
			}

			i++;
		}

		return values;
	}

	/**
	 * 获得某个插件的某个初始化参数。
	 * 
	 * @param plugin
	 *            插件名称。
	 * @param key
	 *            初始化参数键。
	 * @return 返回key对应的初始化参数值。
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	public String getPlugInitParameter(String plugin, String key)
			throws ConfigurationException {
		Plugin plug = this.plugins.get(plugin);
		int index = plug.getIndex();
		String elementPath = "cosmos.plugins.plugin(" + index + ")";
		return getElementInitParameter(elementPath, key);
	}

	/**
	 * 获得某个插件的所有初始化参数。
	 * 
	 * @param plugin
	 *            插件名称。
	 * @param key
	 *            初始化参数键。
	 * @return 返回key对应的初始化参数值。
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	public Collection<String> getInitParameters(String plugin, String key)
			throws ConfigurationException {
		Plugin plug = this.plugins.get(plugin);
		int index = plug.getIndex();
		String elementPath = "cosmos.plugins.plugin(" + index + ")";
		return getElementInitParameters(elementPath, key);
	}

	/**
	 * 通过名称name来获得某个元素的列表。
	 * 
	 * @param elementPath
	 *            元素路径。
	 * @return 元素列表。
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	public Collection<String> getByName(String elementPath)
			throws ConfigurationException {
		return getByAttribute(elementPath, "name");
	}

	/**
	 * 通过某个属性来获得元素列表。
	 * 
	 * @param elementPath
	 *            元素路径。
	 * @param attribute
	 *            属性。
	 * @return 元素列表。
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	@SuppressWarnings("unchecked")
	public Collection<String> getByAttribute(String elementPath,
			String attribute) throws ConfigurationException {
		Collection<String> attributes = new ArrayList<String>();
		Object elementAttributes = this.config.getProperty(elementPath + ".[@"
				+ attribute + "]");
		if (elementAttributes == null) {
			return attributes;
		}
		if (!(elementAttributes instanceof Collection)) {
			attributes.add((String) elementAttributes);
		} else {
			attributes = (Collection<String>) elementAttributes;
		}
		return attributes;
	}

	/**
	 * 获得Cosmos的主目录。
	 * 
	 * @return Cosmos框架的主目录。
	 */
	public String getHomePath() {
		return homePath;
	}

	/**
	 * 设置Cosmos框架的主目录。
	 * 
	 * @param homePath
	 *            Cosmos框架的主目录。
	 */
	public void setHomePath(String homePath) {
		this.homePath = homePath;
	}

	/**
	 * 获得认证工厂类的实现。
	 * 
	 * @return 认证工厂类的实现。
	 */
	public String getAuthFactory() {
		return authFactory;
	}

	/**
	 * 获得认证类的实现。
	 * 
	 * @return 认证类的实现。
	 */
	public String getAuthClass() {
		return authClass;
	}

	/**
	 * 获得权限工厂类的实现。
	 * 
	 * @return 权限工厂类的实现。
	 */
	public String getPermFactory() {
		return permFactory;
	}

	/**
	 * 获得权限类的实现。
	 * 
	 * @return 权限类的实现。
	 */
	public String getPermClass() {
		return permClass;
	}

	/**
	 * 设置配置文件类。
	 * 
	 * @param config
	 *            配置文件类。
	 */
	private void setConfig(org.apache.commons.configuration.Configuration config) {
		this.config = config;
	}

	/**
	 * 装载认证和权限部分的配置文件。
	 * 
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	private void loadAuthAndPerm() throws ConfigurationException {
		this.authFactory = this.config
				.getString(Constants.COSMOS_AUTHENTICATION_FACTORY);
		this.authClass = this.config
				.getString(Constants.COSMOS_AUTHENTICATION_TOKEN);
		this.permFactory = this.config
				.getString(Constants.COSMOS_PERMISSION_FACTORY);
		this.permClass = this.config
				.getString(Constants.COSMOS_PERMISSION_CLASS);
	}

	/**
	 * 装载所有的类型转换器。
	 * 
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	@SuppressWarnings("unchecked")
	private void loadConverters() throws ConfigurationException {
		this.converterMap = new LinkedHashMap<Object, CosmosArgumentConverter>();
		this.converters = new ArrayList<CosmosArgumentConverter>();
		Collection<String> names = getByName(Constants.COSMOS_CONVERTERS_CONVERTER);
		try {
			int converterIndex = 0;
			for (String name : names) {
				String clazz = this.config.getString(String.format(
						Constants.COSMOS_CONVERTERS_CONVERTER_CLASS,
						converterIndex));
				String label = this.config.getString(String.format(
						Constants.COSMOS_CONVERTERS_CONVERTER_LABEL,
						converterIndex));
				Constructor<CosmosArgumentConverter> constructor = ((Class<CosmosArgumentConverter>) Class
						.forName(clazz)).getConstructor(String.class,
						String.class);
				CosmosArgumentConverter converter = constructor.newInstance(
						name, label);

				Collection<String> jdbcTypeValues = this
						.getByAttribute(
								String
										.format(
												Constants.COSMOS_CONVERTERS_CONVERTER_JDBC_TYPE,
												converterIndex), "value");

				Collection<Integer> jdbcTypes = new ArrayList<Integer>();
				if (jdbcTypeValues != null) {
					for (String jdbcTypeValue : jdbcTypeValues) {
						Integer jdbcType = Integer.valueOf(jdbcTypeValue);
						jdbcTypes.add(jdbcType);
						this.converterMap.put(jdbcType, converter);
					}
				}
				converter.setMappedJdbcTypes(jdbcTypes);

				this.converterMap.put(name, converter);
				this.converters.add(converter);
				converterIndex++;
			}
		} catch (Exception e) {
			throw new ConfigurationException(
					"Loading converters has some errors. ", e);
		}
	}

	/**
	 * 装载所有的命令类型。
	 * 
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	@SuppressWarnings("unchecked")
	private void loadCommandTypes() throws ConfigurationException {
		this.commandTypeMap = new LinkedHashMap<String, CosmosMetaCommand>();
		Collection<String> names = getByName(Constants.COSMOS_COMMAND_TYPES_TYPE);
		try {
			int commandTypeIndex = 0;
			for (String name : names) {
				String classString = this.config.getString(String.format(
						Constants.COSMOS_COMMAND_TYPES_TYPE_CLASS,
						commandTypeIndex));
				Class<CosmosCommand> command = (Class<CosmosCommand>) Class
						.forName(classString);
				String label = this.config.getString(String.format(
						Constants.COSMOS_COMMAND_TYPES_TYPE_LABEL,
						commandTypeIndex));
				Boolean composite = this.config.getBoolean(String.format(
						Constants.COSMOS_COMMAND_TYPES_TYPE_COMPOSITE,
						commandTypeIndex));
				Boolean defaultCommand = this.config.getBoolean(String.format(
						Constants.COSMOS_COMMAND_TYPES_TYPE_DEFAULT,
						commandTypeIndex));
				String description = this.config.getString(String.format(
						Constants.COSMOS_COMMAND_TYPES_TYPE_DESCRIPTION,
						commandTypeIndex));
				CosmosMetaCommand type = new CosmosMetaCommand(command,
						composite, defaultCommand, description, label, name);

				if (defaultCommand) {
					defaultCommandType = type;
				}

				this.commandTypeMap.put(name, type);
				commandTypeIndex++;
			}

			if (defaultCommandType == null) {
				throw new ConfigurationException(
						"There is no default command type.");
			}
		} catch (Exception e) {
			throw new ConfigurationException(
					"Loading converters has some errors. ", e);
		}
	}

	/**
	 * 装载所有的命令执行器。
	 * 
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	@SuppressWarnings("unchecked")
	private void loadExecuters() throws ConfigurationException {
		this.executers = new ArrayList<CosmosExecuter>();
		Collection<String> names = getByName(Constants.COSMOS_EXECUTERS_EXECUTER);
		try {
			int executerIndex = 0;
			for (String name : names) {
				String clazz = this.config.getString(String.format(
						Constants.COSMOS_EXECUTERS_EXECUTER_CLASS,
						executerIndex));
				String label = this.config.getString(String.format(
						Constants.COSMOS_EXECUTERS_EXECUTER_LABEL,
						executerIndex));
				String category = this.config.getString(String.format(
						Constants.COSMOS_EXECUTERS_EXECUTER_CATEGORY,
						executerIndex));
				String description = this.config.getString(String.format(
						Constants.COSMOS_EXECUTERS_EXECUTER_DESCRIPTION,
						executerIndex));
				Constructor<CosmosExecuter> constructor = ((Class<CosmosExecuter>) Class
						.forName(clazz)).getConstructor(String.class,
						String.class, String.class, String.class);
				CosmosExecuter converter = constructor.newInstance(name, label,
						description, category);

				this.executers.add(converter);
				executerIndex++;
			}
		} catch (Exception e) {
			throw new ConfigurationException(
					"Loading converters has some errors. ", e);
		}
	}

	/**
	 * 装载所有的域。
	 * 
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	@SuppressWarnings("unchecked")
	private void loadDomains() throws ConfigurationException {
		this.domainMap = new LinkedHashMap<String, CosmosDomain>();
		Collection<String> names = getByName(Constants.COSMOS_DOMAINS_DOMAIN);
		try {
			int domainIndex = 0;
			for (String name : names) {
				String clazz = this.config.getString(String.format(
						Constants.COSMOS_DOMAINS_DOMAIN_CLASS, domainIndex));
				String type = this.config.getString(String.format(
						Constants.COSMOS_DOMAINS_DOMAIN_TYPE, domainIndex));
				CosmosDomainType domainType = CosmosDomainType.valueOf(type);
				Constructor<CosmosDomain> constructor = ((Class<CosmosDomain>) Class
						.forName(clazz)).getConstructor(String.class,
						CosmosDomainType.class, int.class);
				CosmosDomain domain = constructor.newInstance(name, domainType,
						domainIndex);

				if (domainType == CosmosDomainType.master) {
					this.master = domain;
				}

				this.domainMap.put(name, domain);
				domainIndex++;
			}
		} catch (Exception e) {
			throw new ConfigurationException(
					"Loading domains has some errors. ", e);
		}
	}

	/**
	 * 装在所有的插件。
	 * 
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	private void loadPlugins() throws ConfigurationException {
		this.plugins = new LinkedHashMap<String, Plugin>();
		Collection<String> names = getByName(Constants.COSMOS_PLUGIN);

		try {
			int i = 0;
			for (String name : names) {
				String clazz = this.config.getString(String.format(
						Constants.COSMOS_PLUGIN_CLASS, i));
				Plugin plugin = (Plugin) Class.forName(clazz).newInstance();
				plugin.setName(name);
				plugin.setIndex(i);
				this.plugins.put(name, plugin);

				i++;
			}
		} catch (Exception e) {
			throw new ConfigurationException(
					"Initializing plugins has some errors. ", e);
		}
	}

	/**
	 * 装载配置文件中扩展出来的信息。
	 * 
	 * @throws ConfigurationException
	 *             分析配置文件时抛出的异常。
	 */
	protected abstract void loadExtensions() throws ConfigurationException;

}
