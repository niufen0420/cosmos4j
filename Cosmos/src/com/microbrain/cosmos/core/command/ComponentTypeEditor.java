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
package com.microbrain.cosmos.core.command;

/**
 * <p>
 * 动态对象的属性编辑器接口<code>ComponentTypeEditor</code>
 * ，使用这个接口可以为一个对象里添加一个属性，删除一个属性，从而达到动态产生对象的目的。
 * </p>
 * <p>
 * 在cosmos框架中，<code>com.microbrain.cosmos.core.command.CosmosDynamicList</code>、
 * <code>com.microbrain.cosmos.core.command.StandardCosmosResult</code>以及
 * <code>com.microbrain.cosmos.core.command.StandardComponentTypeEditor</code>
 * 都扩展了本类的功能。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosDynamicList
 * @see com.microbrain.cosmos.core.command.StandardCosmosResult
 * @see com.microbrain.cosmos.core.command.StandardComponentTypeEditor
 * @since CFDK 1.0
 */
public interface ComponentTypeEditor {

	/**
	 * 为一个动态对象添加一个属性。
	 * 
	 * @param name
	 *            属性的名称。
	 */
	public void pushProperty(String name);

	/**
	 * 删除一个动态对象所拥有的某种属性。
	 * 
	 * @param name
	 *            属性的名称。
	 */
	public void removeProperty(String name);

}
