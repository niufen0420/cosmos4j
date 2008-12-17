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
package com.microbrain.cosmos.core.config.plugins;

import java.util.Collection;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.config.AbstractPlugin;
import com.microbrain.cosmos.core.config.Configuration;

/**
 * <p>
 * 启动Hibernate的插件，用来读取Hibernate配置文件，并配置Hibernate相应参数。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.config.Plugin
 * @see com.microbrain.cosmos.core.config.AbstractPlugin
 * @since CFDK 1.0
 */
public class HibernatePlugin extends AbstractPlugin {

	/**
	 * 配置文件名称键。
	 */
	public static final String HIBERNATE_CONFIG_KEY = "hibernate";

	/*
	 * 初始化插件。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.config.Plugin#init(com.microbrain.cosmos.core
	 * .config.Configuration, com.microbrain.cosmos.core.CosmosFactory)
	 */
	public void init(Configuration config, CosmosFactory factory) {
		try {
			Collection<String> hibernates = config.getInitParameters(this.name,
					HIBERNATE_CONFIG_KEY);
			for (String hibernate : hibernates) {
				System.out.println(hibernate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
