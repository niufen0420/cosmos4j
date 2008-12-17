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
package com.microbrain.cosmos.web.el.system;

import java.util.ArrayList;
import java.util.Collection;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.CosmosFactoryException;
import com.microbrain.cosmos.core.command.CosmosArgumentConverter;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.CosmosExecuter;
import com.microbrain.cosmos.core.command.CosmosMetaCommand;
import com.microbrain.cosmos.core.config.Configuration;
import com.microbrain.cosmos.core.config.ConfigurationException;
import com.microbrain.cosmos.core.domain.CosmosDomain;
import com.microbrain.cosmos.core.domain.CosmosDomainException;

/**
 * <p>
 * 系统提供的<code>EL</code>表达式，提供了访问一些系统对象的方法。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.CosmosFactory
 * @see com.microbrain.cosmos.core.config.Configuration
 * @since CFDK 1.0
 */
public class SystemUtil {

	/**
	 * 私有化构造函数。
	 */
	private SystemUtil() {
	}

	/**
	 * 获得系统配置的所有域。
	 * 
	 * @return 所有域。
	 */
	public static Collection<CosmosDomain> domains() {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			e.printStackTrace();
		}

		Configuration config = factory.lookupConfig();
		try {
			return config.getDomains().values();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		return new ArrayList<CosmosDomain>(0);
	}

	/**
	 * 获得所有可用的执行器。
	 * 
	 * @return 所有可用的执行器。
	 */
	public static Collection<CosmosExecuter> executers() {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			e.printStackTrace();
		}

		Configuration config = factory.lookupConfig();
		try {
			return config.getExecuters();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		return new ArrayList<CosmosExecuter>(0);
	}

	/**
	 * 获得域中可用的执行器。
	 * 
	 * @param domain
	 *            域名称。
	 * @return 域中可用的执行器。
	 */
	public static Collection<CosmosExecuter> executers(String domain) {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			e.printStackTrace();
		}

		Configuration config = factory.lookupConfig();
		try {
			CosmosDomain cosmosDomain = config.getDomains().get(domain);
			return cosmosDomain.availableExecuters();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (CosmosDomainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ArrayList<CosmosExecuter>(0);
	}

	/**
	 * 获得所有类型转换器。
	 * 
	 * @return 所有的类型转换器。
	 */
	public static Collection<CosmosArgumentConverter> converters() {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			e.printStackTrace();
		}

		Configuration config = factory.lookupConfig();
		try {
			return config.getConverters();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		return new ArrayList<CosmosArgumentConverter>(0);
	}

	/**
	 * 获得所有的命令类型。
	 * 
	 * @return 所有的命令类型。
	 */
	public static Collection<CosmosMetaCommand> commandTypes() {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			e.printStackTrace();
		}

		Configuration config = factory.lookupConfig();
		try {
			return config.getCommandTypes().values();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		return new ArrayList<CosmosMetaCommand>(0);
	}

	/**
	 * 列出系统中有的所有命令。
	 * 
	 * @return 所有命令列表。
	 */
	public static Collection<CosmosCommand> commands() {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			e.printStackTrace();
		}

		try {
			return factory.listAllCommands();
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ArrayList<CosmosCommand>(0);
	}

	/**
	 * 获得域中所有的命令，包括全局命令和本地命令。
	 * 
	 * @param domain
	 *            域名称。
	 * @return 所有命令列表。
	 */
	public static Collection<CosmosCommand> commands(String domain) {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			e.printStackTrace();
		}

		Configuration config = factory.lookupConfig();
		try {
			CosmosDomain cosmosDomain = config.getDomains().get(domain);
			return cosmosDomain.listAllCommands();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (CosmosDomainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ArrayList<CosmosCommand>(0);
	}

	/**
	 * 列出系统中有的所有全局命令，全局命令是指存储在主Domain中的命令。
	 * 
	 * @return 返回所有的全局命令。
	 */
	public static Collection<CosmosCommand> globalCommands() {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			e.printStackTrace();
		}

		try {
			return factory.listAllGlobalCommands();
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ArrayList<CosmosCommand>(0);
	}

	/**
	 * 获得域中所有的全局命令。
	 * 
	 * @param domain
	 *            域名称。
	 * @return 所有全局命令列表。
	 */
	public static Collection<CosmosCommand> globalCommands(String domain) {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			e.printStackTrace();
		}

		Configuration config = factory.lookupConfig();
		try {
			CosmosDomain cosmosDomain = config.getDomains().get(domain);
			return cosmosDomain.listGlobalCommands();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (CosmosDomainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ArrayList<CosmosCommand>(0);
	}

	/**
	 * 列出系统中有的所有本地命令，本地命令是指无法存储在主<code>Domain</code>中，只能存储在各自
	 * <code>Domain</code>中的命令，比如：存储过程等。
	 * 
	 * @return 返回所有的本地命令。
	 */
	public static Collection<CosmosCommand> localCommands() {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			e.printStackTrace();
		}

		try {
			return factory.listAllLocalCommands();
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ArrayList<CosmosCommand>(0);
	}

	/**
	 * 获得域中所有的本地命令。
	 * 
	 * @param domain
	 *            域名称。
	 * @return 域中所有本地命令列表。
	 */
	public static Collection<CosmosCommand> localCommands(String domain) {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			e.printStackTrace();
		}

		Configuration config = factory.lookupConfig();
		try {
			CosmosDomain cosmosDomain = config.getDomains().get(domain);
			return cosmosDomain.listLocalCommands();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (CosmosDomainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ArrayList<CosmosCommand>(0);
	}

}
