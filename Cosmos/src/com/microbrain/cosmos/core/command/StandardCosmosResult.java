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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.microbrain.cosmos.core.command.utils.StringUtils;

/**
 * <p>
 * <code>CosmosResult</code>�ı�׼ʵ�֡�
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @see com.microbrain.cosmos.core.command.StandardCosmosMetaArgument
 * @since CFDK 1.0
 */
public class StandardCosmosResult extends StandardComponentTypeEditor implements
		CosmosResult {

	/**
	 * ִ�н������ӳ�䣬��Ϊ�������ơ�
	 */
	private Map<String, CosmosDynamicObject> objectMap = null;

	/**
	 * ִ�н���б�ӳ�䡣
	 */
	private Map<String, List<CosmosDynamicList<CosmosDynamicObject>>> listMap = null;

	/**
	 * ������󶨵����
	 */
	private CosmosCommand command = null;

	/**
	 * ���캯����
	 * 
	 * @param command
	 *            ������������
	 */
	public StandardCosmosResult(CosmosCommand command) {
		this.objectMap = new HashMap<String, CosmosDynamicObject>();
		this.listMap = new HashMap<String, List<CosmosDynamicList<CosmosDynamicObject>>>();
		this.command = command;
	}

	/*
	 * ��ñ���������������
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosResult#getCommand()
	 */
	public CosmosCommand getCommand() {
		return this.command;
	}

	/*
	 * ������Ż���б�
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosResult#getList(int)
	 */
	public Collection<CosmosDynamicObject> getList(int index) {
		return getList(this.command.getDomain().getName(), this.command
				.getName(), index);
	}

	/*
	 * �������ƻ���б�
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosResult#getList(java.lang.String)
	 */
	public Collection<CosmosDynamicObject> getList(String name)
			throws IllegalArgumentException {
		return getList(this.command.getDomain().getName(), this.command
				.getName(), name);
	}

	/*
	 * ��ý������
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosResult#getVarObject()
	 */
	public CosmosDynamicObject getVarObject() {
		return getVarObject(this.command.getDomain().getName(), this.command
				.getName());
	}

	/*
	 * ��װ�������
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosResult#getVarObject(java.lang
	 * .Class, java.lang.String[])
	 */
	public <T> T getVarObject(Class<?> clazz, String... fields) {
		return getVarObject(this.command.getDomain().getName(), this.command
				.getName(), clazz, fields);
	}

	/*
	 * ���ĳ�����������
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosResult#getVar(java.lang.String)
	 */
	public <T> T getVar(String name) {
		return getVar(this.command.getDomain().getName(), this.command
				.getName(), name);
	}

	/*
	 * ������Ż��ĳ�����������
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosResult#getVar(int)
	 */
	public <T> T getVar(int index) {
		return getVar(this.command.getDomain().getName(), this.command
				.getName(), index);
	}

	/**
	 * ����һ��������
	 * 
	 * @param object
	 *            ���ñ�����
	 */
	public void setVar(CosmosDynamicObject object) {
		String commandName = this.command.getDomain().getName() + "."
				+ this.command.getName();
		this.objectMap.put(commandName, object);
	}

	/*
	 * �õ�����������
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosResult#getVarCount()
	 */
	public Integer getVarCount() {
		return getVarCount(this.command.getDomain().getName(), this.command
				.getName());
	}

	/*
	 * ��ñ��������б�
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosResult#getVarNames()
	 */
	public Collection<String> getVarNames() {
		return getVarNames(this.command.getDomain().getName(), this.command
				.getName());
	}

	/**
	 * ���һ���б�
	 * 
	 * @param list
	 *            ���ӵ��б�
	 */
	public void addList(CosmosDynamicList<CosmosDynamicObject> list) {
		addList(this.command.getDomain().getName(), this.command.getName(),
				list);
	}

	/*
	 * ����һ���������
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosResult#newObject()
	 */
	public CosmosDynamicObject newObject() {
		return new CosmosDynamicObject(properties);
	}

	/*
	 * ���һ�����ԡ�
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.StandardComponentTypeEditor#pushProperty
	 * (java.lang.String)
	 */
	@Override
	public void pushProperty(String name) {
		super.pushProperty(StringUtils.decorate(name));
	}

	/*
	 * ������Ż��һ���б�
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosResult#getList(int,
	 * java.lang.Class, java.lang.String[])
	 */
	public <T> Collection<T> getList(int index, Class<?> componentType,
			String... fields) {
		return getList(this.command.getDomain().getName(), this.command
				.getName(), index, componentType, fields);
	}

	/*
	 * �������ƻ��һ���б�
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosResult#getList(java.lang.String,
	 * java.lang.Class, java.lang.String[])
	 */
	public <T> Collection<T> getList(String name, Class<?> componentType,
			String... fields) throws IllegalArgumentException {
		return getList(this.command.getDomain().getName(), this.command
				.getName(), name, componentType, fields);
	}

	/*
	 * ��õ�һ���б�
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosResult#getList()
	 */
	public Collection<CosmosDynamicObject> getList() {
		return getList(0);
	}

	/*
	 * ����б�����װ��
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosResult#getList(java.lang.Class,
	 * java.lang.String[])
	 */
	public <T> Collection<T> getList(Class<?> componentType, String... fields) {
		return getList(0, componentType, fields);
	}

	/*
	 * ����б�������
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosResult#getListCount()
	 */
	public Integer getListCount() {
		return getListCount(this.command.getDomain().getName(), this.command
				.getName());
	}

	/*
	 * ���ĳ��������б�
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosResult#getList(java.lang.String,
	 * java.lang.String)
	 */
	public Collection<CosmosDynamicObject> getList(String domain, String command) {
		return getList(domain, command, 0);
	}

	/*
	 * ���ĳ��������б�����װ��
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosResult#getList(java.lang.String,
	 * java.lang.String, java.lang.Class, java.lang.String[])
	 */
	public <T> Collection<T> getList(String domain, String command,
			Class<?> componentType, String... fields) {
		return getList(domain, command, 0, componentType, fields);
	}

	/*
	 * ������Ż��ĳ��������б�
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosResult#getList(java.lang.String,
	 * java.lang.String, int)
	 */
	public Collection<CosmosDynamicObject> getList(String domain,
			String command, int index) {
		String commandName = domain + "." + command;
		List<CosmosDynamicList<CosmosDynamicObject>> lists = this.listMap
				.get(commandName);
		if (lists == null) {
			return null;
		}

		return lists.get(index);
	}

	/**
	 * ���ĳ��������б�
	 * 
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @param list
	 *            �����б�
	 */
	private void addList(String domain, String command,
			CosmosDynamicList<CosmosDynamicObject> list) {
		String commandName = domain + "." + command;
		List<CosmosDynamicList<CosmosDynamicObject>> lists = this.listMap
				.get(commandName);
		if (lists == null) {
			lists = new ArrayList<CosmosDynamicList<CosmosDynamicObject>>();
			this.listMap.put(commandName, lists);
		}

		lists.add(list);
	}

	/*
	 * �������ƻ���б�
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosResult#getList(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public Collection<CosmosDynamicObject> getList(String domain,
			String command, String name) throws IllegalArgumentException {
		if (name == null) {
			return null;
		}

		String commandName = domain + "." + command;
		List<CosmosDynamicList<CosmosDynamicObject>> lists = this.listMap
				.get(commandName);
		if (lists == null) {
			return null;
		}

		CosmosDynamicList<CosmosDynamicObject> list = null;
		for (CosmosDynamicList<CosmosDynamicObject> li : lists) {
			if (name.equals(li.getName())) {
				list = li;
				break;
			}
		}

		return list;
	}

	/*
	 * ������Ż��ĳ���б�����װ��
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosResult#getList(java.lang.String,
	 * java.lang.String, int, java.lang.Class, java.lang.String[])
	 */
	public <T> Collection<T> getList(String domain, String command, int index,
			Class<?> componentType, String... fields) {
		String commandName = domain + "." + command;
		List<CosmosDynamicList<CosmosDynamicObject>> lists = this.listMap
				.get(commandName);
		if (lists == null) {
			return null;
		}

		CosmosDynamicList<CosmosDynamicObject> list = lists.get(index);
		return new CosmosDynamicList<T>(list.getName(), list, componentType,
				fields);
	}

	/*
	 * �������ƻ��ĳ���б�����װ��
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosResult#getList(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.Class, java.lang.String[])
	 */
	public <T> Collection<T> getList(String domain, String command,
			String name, Class<?> componentType, String... fields)
			throws IllegalArgumentException {
		if (name == null) {
			return null;
		}

		String commandName = domain + "." + command;
		List<CosmosDynamicList<CosmosDynamicObject>> lists = this.listMap
				.get(commandName);
		if (lists == null) {
			return null;
		}

		CosmosDynamicList<CosmosDynamicObject> list = null;
		for (CosmosDynamicList<CosmosDynamicObject> li : lists) {
			if (name.equals(li.getName())) {
				list = li;
				break;
			}
		}

		return new CosmosDynamicList<T>(list.getName(), list, componentType,
				fields);
	}

	/*
	 * ���ĳ��������б�������
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosResult#getListCount(java.lang
	 * .String, java.lang.String)
	 */
	public Integer getListCount(String domain, String command) {
		String commandName = domain + "." + command;
		List<CosmosDynamicList<CosmosDynamicObject>> lists = this.listMap
				.get(commandName);
		if (lists == null) {
			return 0;
		}

		return lists.size();
	}

	/*
	 * ����б����Ƶ��б�
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosResult#getListNames(java.lang
	 * .String, java.lang.String)
	 */
	public Collection<String> getListNames(String domain, String command)
			throws IllegalArgumentException {
		String commandName = domain + "." + command;
		List<CosmosDynamicList<CosmosDynamicObject>> lists = this.listMap
				.get(commandName);
		if (lists == null) {
			return new ArrayList<String>(0);
		}

		Collection<String> names = new ArrayList<String>(lists.size());
		for (CosmosDynamicList<CosmosDynamicObject> list : lists) {
			names.add(list.getName());
		}

		return names;
	}

	/*
	 * ��ñ���ֵ��
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosResult#getVar(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public <T> T getVar(String domain, String command, String name) {
		String commandName = domain + "." + command;
		CosmosDynamicObject object = this.objectMap.get(commandName);
		if (object == null) {
			return null;
		}

		return (T) object.get(name);
	}

	/*
	 * ������Ż�ñ���ֵ��
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosResult#getVar(java.lang.String,
	 * java.lang.String, int)
	 */
	@SuppressWarnings("unchecked")
	public <T> T getVar(String domain, String command, int index) {
		String commandName = domain + "." + command;
		CosmosDynamicObject object = this.objectMap.get(commandName);
		if (object == null) {
			return null;
		}

		return (T) object.get(index);
	}

	/*
	 * ��ñ���������
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosResult#getVarCount(java.lang
	 * .String, java.lang.String)
	 */
	public Integer getVarCount(String domain, String command) {
		String commandName = this.command.getDomain().getName() + "."
				+ this.command.getName();
		CosmosDynamicObject object = this.objectMap.get(commandName);
		if (object == null) {
			return 0;
		}

		return object.size();
	}

	/*
	 * ��ñ��������б�
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosResult#getVarNames(java.lang
	 * .String, java.lang.String)
	 */
	public Collection<String> getVarNames(String domain, String command) {
		String commandName = this.command.getDomain().getName() + "."
				+ this.command.getName();
		CosmosDynamicObject object = this.objectMap.get(commandName);
		if (object == null) {
			return new ArrayList<String>(0);
		}

		return object.keySet();
	}

	/*
	 * ��ñ�������
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosResult#getVarObject(java.lang
	 * .String, java.lang.String)
	 */
	public CosmosDynamicObject getVarObject(String domain, String command) {
		String commandName = domain + "." + command;
		return this.objectMap.get(commandName);
	}

	/*
	 * ��ñ������󣬲���װ��
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosResult#getVarObject(java.lang
	 * .String, java.lang.String, java.lang.Class, java.lang.String[])
	 */
	@SuppressWarnings("unchecked")
	public <T> T getVarObject(String domain, String command, Class<?> clazz,
			String... fields) {
		String commandName = domain + "." + command;
		CosmosDynamicObject object = this.objectMap.get(commandName);
		T t = null;
		try {
			t = (T) clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			BeanUtils.populate(t, object);

			if (fields != null) {
				int index = 0;
				for (String field : fields) {
					BeanUtils.setProperty(t, field, object.getVar(index));
					index++;
				}
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return t;
	}

	/*
	 * ����б������б�
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosResult#getListNames()
	 */
	public Collection<String> getListNames() throws IllegalArgumentException {
		return getListNames(this.command.getDomain().getName(), this.command
				.getName());
	}

	/*
	 * ��Ͻ����
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosResult#combine(com.microbrain
	 * .cosmos.core.command.CosmosResult)
	 */
	public CosmosResult combine(CosmosResult result) {
		if (result == null) {
			return this;
		}

		Map<String, CosmosDynamicObject> otherObjectMap = result.getObjectMap();
		for (String key : otherObjectMap.keySet()) {
			objectMap.put(key, otherObjectMap.get(key));
		}

		Map<String, List<CosmosDynamicList<CosmosDynamicObject>>> otherListMap = result
				.getListMap();
		for (String key : otherListMap.keySet()) {
			listMap.put(key, otherListMap.get(key));
		}

		return this;
	}

	/*
	 * ��ý������ӳ�䡣
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosResult#getObjectMap()
	 */
	public Map<String, CosmosDynamicObject> getObjectMap() {
		return objectMap;
	}

	/*
	 * ����б�ӳ�䡣
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosResult#getListMap()
	 */
	public Map<String, List<CosmosDynamicList<CosmosDynamicObject>>> getListMap() {
		return listMap;
	}

}
