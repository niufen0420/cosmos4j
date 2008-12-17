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
package com.microbrain.cosmos.core.log;

import org.apache.log4j.PropertyConfigurator;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.config.AbstractPlugin;
import com.microbrain.cosmos.core.config.Configuration;
import com.microbrain.cosmos.core.config.ConfigurationException;
import com.microbrain.cosmos.core.constants.Constants;

/**
 * <p>
 * 初始化Log4J的一个插件，可以用来配置到系统中，并初始化Log4J实例。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.config.Plugin
 * @see com.microbrain.cosmos.core.config.AbstractPlugin
 * @since CFDK 1.0
 */
public class Log4jPlugin extends AbstractPlugin {

	/**
	 * 配置文件键。
	 */
	private static final String CONFIG = "config";

	/*
	 * 初始化本插件。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.config.Plugin#init(com.microbrain.cosmos.core
	 * .config.Configuration, com.microbrain.cosmos.core.CosmosFactory)
	 */
	public void init(Configuration config, CosmosFactory factory) {
		String log4jConfig = null, homePath = config.getHomePath();

		try {
			log4jConfig = config.getPlugInitParameter(name, CONFIG);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!homePath.endsWith("/")) {
			homePath = homePath + "/";
		}

		System.setProperty(Constants.COSMOS_HOME, homePath);
		System.out.println("homePath + log4jConfig: " + homePath + log4jConfig);
		PropertyConfigurator.configure(homePath + log4jConfig);
	}

}
