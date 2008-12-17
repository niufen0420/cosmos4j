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
 * ��׼�����Ա༭��ʵ�֣�������Ҫ�˹��ܵ�����Լ̳д���ﵽ��չ��Ŀ�ġ�
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
	 * �����������Ե��࣬��Ϊ�������ƣ�ֵΪ���Ե���š�
	 */
	protected Map<String, Integer> properties = new HashMap<String, Integer>();

	/*
	 * ����һ�����ԡ�
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
	 * ����һ�����ԡ�
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
