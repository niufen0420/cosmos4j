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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.microbrain.cosmos.core.command.utils.StringUtils;

/**
 * <p>
 * 利用<code>Map<String, Object></code>实现一个可供编辑属性的动态对象类，利用这个类，可以为一个对象添加属性，删除一个属性。
 * </p>
 * <p>
 * 对于属性的编辑应该由一个实现了<code>ComponentTypeEditor</code>接口的类来完成。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.ComponentTypeEditor
 * @see com.microbrain.cosmos.core.command.CosmosDynamicList
 * @see com.microbrain.cosmos.core.command.StandardCosmosResult
 * @since CFDK 1.0
 */
public class CosmosDynamicObject implements Map<String, Object> {

	/**
	 * 保存所有属性的映射类。
	 */
	private Map<String, Integer> properties = null;

	/**
	 * 保存所有属性的值。
	 */
	private Object[] values = null;

	/**
	 * 默认级别的构造函数，确保在包外无法被创建。
	 * 
	 * @param properties
	 *            该动态类型所拥有的属性映射。
	 */
	CosmosDynamicObject(Map<String, Integer> properties) {
		this.properties = properties;
		int size = 0;
		if (this.properties != null) {
			size = this.properties.size();
		}

		values = new Object[size];
	}

	/*
	 * 清楚这个动态对象的所有属性的值。
	 * 
	 * @see java.util.Map#clear()
	 */
	public void clear() {
		if (properties != null) {
			values = new Object[properties.size()];
		}
	}

	/*
	 * 判断是否包含某个属性。
	 * 
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	public boolean containsKey(Object key) {
		if (properties == null) {
			return false;
		}

		return properties.containsKey(key);
	}

	/*
	 * 判断是否包含某个值。
	 * 
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	public boolean containsValue(Object value) {
		for (Object orignal : values) {
			if (orignal != null && orignal.equals(value)) {
				return true;
			}
		}

		return false;
	}

	/*
	 * 得到动态对象的属性和值的对集合，此方法在当前版本中不可用。
	 * 
	 * @see java.util.Map#entrySet()
	 */
	public Set<Entry<String, Object>> entrySet() {
		throw new IllegalArgumentException(
				"CosmosDynamicObject can't use this method.");
	}

	/*
	 * 通过某个属性来获得该属性的值。
	 * 
	 * @see java.util.Map#get(java.lang.Object)
	 */
	public Object get(Object key) {
		if (properties == null || !properties.containsKey(key)) {
			return null;
		}

		return values[properties.get(key)];
	}

	/*
	 * 判断该动态对象的属性是否为空。
	 * 
	 * @see java.util.Map#isEmpty()
	 */
	public boolean isEmpty() {
		if (properties == null) {
			return true;
		}

		return properties.isEmpty();
	}

	/*
	 * 得到动态对象的所有属性名称集合。
	 * 
	 * @see java.util.Map#keySet()
	 */
	public Set<String> keySet() {
		if (properties == null) {
			return new HashSet<String>(0);
		}

		return properties.keySet();
	}

	/*
	 * 为动态对象添加一个属性值，如果不存在该属性，则抛出异常。
	 * 
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public Object put(String key, Object value) {
		String name = StringUtils.decorate(key);
		if (properties == null || !properties.containsKey(name)) {
			throw new IllegalArgumentException("This properties '" + key
					+ "' doesn't exists. Please added it firstly.");
		}

		values[properties.get(name)] = value;
		return value;
	}

	/*
	 * 添加多个属性值。
	 * 
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	public void putAll(Map<? extends String, ? extends Object> m) {
		if (properties == null) {
			return;
		}

		for (String key : m.keySet()) {
			String name = StringUtils.decorate(key);
			if (!properties.containsKey(name)) {
				throw new IllegalArgumentException("This properties '" + key
						+ "' doesn't exists. Please added it firstly.");
			}

			values[properties.get(name)] = m.get(key);
		}
	}

	/*
	 * 得到对象的属性个数。
	 * 
	 * @see java.util.Map#size()
	 */
	public int size() {
		if (properties == null) {
			return 0;
		}

		return properties.size();
	}

	/*
	 * 得到所有属性值的集合。
	 * 
	 * @see java.util.Map#values()
	 */
	public Collection<Object> values() {
		Collection<Object> vals = new ArrayList<Object>();
		if (values != null) {
			for (Object value : values) {
				vals.add(value);
			}
		}

		return vals;
	}

	/*
	 * 删除某个属性的值。
	 * 
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public Object remove(Object key) {
		if (properties == null) {
			return null;
		}

		int index = properties.get(key);
		Object ret = values[index];
		values[index] = null;

		return ret;
	}

	/**
	 * 通过序号得到某个属性的值。
	 * 
	 * @param <T>
	 *            返回值类型。
	 * @param index
	 *            属性序号。
	 * @return 返回该属性的值。
	 */
	@SuppressWarnings("unchecked")
	public <T> T getVar(int index) {
		if (values == null) {
			return null;
		}

		if (index > values.length) {
			throw new IllegalArgumentException("This properties '" + index
					+ "' exceeded total: " + values.length);
		}

		return (T) values[index];
	}

}
