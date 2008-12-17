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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 标准的属性编辑器实现，其他需要此功能的类可以继承此类达到扩展的目的。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.ComponentTypeEditor
 * @see com.microbrain.cosmos.core.command.CosmosDynamicList
 * @see com.microbrain.cosmos.core.command.StandardCosmosResult
 * @since CFDK 1.0
 */
public class StandardComponentTypeEditor implements ComponentTypeEditor {

	/**
	 * 保存所有属性的类，键为属性名称，值为属性的序号。
	 */
	protected Map<String, Integer> properties = new HashMap<String, Integer>();

	/*
	 * 增加一个属性。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.ComponentTypeEditor#pushProperty(java
	 * .lang.String)
	 */
	public void pushProperty(String name) {
		if (!properties.containsKey(name)) {
			properties.put(name, properties.size());
		}
	}

	/*
	 * 减少一个属性。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.ComponentTypeEditor#removeProperty
	 * (java.lang.String)
	 */
	public void removeProperty(String name) {
		if (!properties.containsKey(name)) {
			return;
		}

		int current = properties.get(name);
		for (String key : properties.keySet()) {
			int next = properties.get(key);
			if (next > current) {
				properties.put(key, next - 1);
			}
		}

		properties.remove(name);
	}

}
