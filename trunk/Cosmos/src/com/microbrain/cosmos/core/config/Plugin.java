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

import com.microbrain.cosmos.core.CosmosFactory;

/**
 * <p>
 * 提供一个可插入式的插件接口，使得开发人员可以在系统中扩展自己的一些功能。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.config.AbstractPlugin
 * @since CFDK 1.0
 */
public interface Plugin {

	/**
	 * 初始化插件。
	 * 
	 * @param config
	 * @param factory
	 */
	public void init(Configuration config, CosmosFactory factory);

	/**
	 * 获得插件名称。
	 * 
	 * @return 插件名称。
	 */
	public String getName();

	/**
	 * 设置插件名称。
	 * 
	 * @param name
	 *            插件名称。
	 */
	public void setName(String name);

	/**
	 * 获得插件序号。
	 * 
	 * @return 插件序号。
	 */
	public int getIndex();

	/**
	 * 设置插件序号。
	 * 
	 * @param index
	 *            插件序号。
	 */
	public void setIndex(int index);

}
