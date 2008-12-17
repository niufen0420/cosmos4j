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
 * ����<code>Map<String, Object></code>ʵ��һ���ɹ��༭���ԵĶ�̬�����࣬��������࣬����Ϊһ������������ԣ�ɾ��һ�����ԡ�
 * </p>
 * <p>
 * �������Եı༭Ӧ����һ��ʵ����<code>ComponentTypeEditor</code>�ӿڵ�������ɡ�
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
	 * �����������Ե�ӳ���ࡣ
	 */
	private Map<String, Integer> properties = null;

	/**
	 * �����������Ե�ֵ��
	 */
	private Object[] values = null;

	/**
	 * Ĭ�ϼ���Ĺ��캯����ȷ���ڰ����޷���������
	 * 
	 * @param properties
	 *            �ö�̬������ӵ�е�����ӳ�䡣
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
	 * ��������̬������������Ե�ֵ��
	 * 
	 * @see java.util.Map#clear()
	 */
	public void clear() {
		if (properties != null) {
			values = new Object[properties.size()];
		}
	}

	/*
	 * �ж��Ƿ����ĳ�����ԡ�
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
	 * �ж��Ƿ����ĳ��ֵ��
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
	 * �õ���̬��������Ժ�ֵ�ĶԼ��ϣ��˷����ڵ�ǰ�汾�в����á�
	 * 
	 * @see java.util.Map#entrySet()
	 */
	public Set<Entry<String, Object>> entrySet() {
		throw new IllegalArgumentException(
				"CosmosDynamicObject can't use this method.");
	}

	/*
	 * ͨ��ĳ����������ø����Ե�ֵ��
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
	 * �жϸö�̬����������Ƿ�Ϊ�ա�
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
	 * �õ���̬����������������Ƽ��ϡ�
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
	 * Ϊ��̬�������һ������ֵ����������ڸ����ԣ����׳��쳣��
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
	 * ��Ӷ������ֵ��
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
	 * �õ���������Ը�����
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
	 * �õ���������ֵ�ļ��ϡ�
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
	 * ɾ��ĳ�����Ե�ֵ��
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
	 * ͨ����ŵõ�ĳ�����Ե�ֵ��
	 * 
	 * @param <T>
	 *            ����ֵ���͡�
	 * @param index
	 *            ������š�
	 * @return ���ظ����Ե�ֵ��
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
