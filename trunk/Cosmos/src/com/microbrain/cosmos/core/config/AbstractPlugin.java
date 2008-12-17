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
 * 系统插件的抽象类实现。实现了<code>Plugin</code>的部分方法。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.config.Plugin
 * @since CFDK 1.0
 */
public abstract class AbstractPlugin implements Plugin {

	/**
	 * 插件名称。
	 */
	protected String name;

	/**
	 * 插件在配置文件中的序号。
	 */
	protected int index;

	/*
	 * 获得插件名称。
	 * 
	 * @see com.microbrain.cosmos.core.config.Plugin#getName()
	 */
	public String getName() {
		return this.name;
	}

	/*
	 * 设置插件名称。
	 * 
	 * @see com.microbrain.cosmos.core.config.Plugin#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * 获得插件序号。
	 * 
	 * @see com.microbrain.cosmos.core.config.Plugin#getIndex()
	 */
	public int getIndex() {
		return this.index;
	}

	/*
	 * 设置插件序号。
	 * 
	 * @see com.microbrain.cosmos.core.config.Plugin#setIndex(int)
	 */
	public void setIndex(int index) {
		this.index = index;
	}

}
