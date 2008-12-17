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

/**
 * <p>
 * <code>Configuration</code>的默认实现，提供空的读取扩展配置文件内容的方法。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.config.Configuration
 * @since CFDK 1.0
 */
public class DefaultConfigurationImpl extends Configuration {

	/*
	 * 装载配置文件中其余的内容。
	 * 
	 * @see com.microbrain.cosmos.core.config.Configuration#loadExtensions()
	 */
	protected void loadExtensions() throws ConfigurationException {
	}

}
