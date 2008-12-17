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

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * ��װ��������ִ�еĽ����������Щ������Թ��ϲ���ʹ�á�
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @see com.microbrain.cosmos.core.command.StandardCosmosResult
 * @see com.microbrain.cosmos.core.command.ComponentTypeEditor
 * @since CFDK 1.0
 */
public interface CosmosResult extends ComponentTypeEditor {

	/**
	 * ���������н�������������
	 * 
	 * @return ���ع��������
	 */
	public CosmosCommand getCommand();

	/**
	 * ��÷�װ�ɶ���Ľ����
	 * 
	 * @return ��װ�ɶ�̬����Ľ����
	 */
	public CosmosDynamicObject getVarObject();

	/**
	 * ����̬�����װ�ɶ�Ӧ���ࡣ
	 * 
	 * @param <T>
	 *            ���ظö�Ӧ��Ķ���
	 * @param clazz
	 *            Ҫ��װ�ɵĶ�Ӧ�ࡣ
	 * @param fields
	 *            ���Զ�Ӧ��ϵ��
	 * @return ���ط�װ֮��Ķ���
	 */
	public <T> T getVarObject(Class<?> clazz, String... fields);

	/**
	 * ��÷�װ֮���ĳ�����ԡ�
	 * 
	 * @param <T>
	 *            �������͡�
	 * @param name
	 *            �������ơ�
	 * @return ���Ե�ֵ��
	 */
	public <T> T getVar(String name);

	/**
	 * ������ŷ���ĳ�����ԡ�
	 * 
	 * @param <T>
	 *            �������͡�
	 * @param index
	 *            ���Ե���š�
	 * @return ����ֵ��
	 */
	public <T> T getVar(int index);

	/**
	 * ������е��������ơ�
	 * 
	 * @return �����������ơ�
	 */
	public Collection<String> getVarNames();

	/**
	 * �������Ը�����
	 * 
	 * @return ���Ը�����
	 */
	public Integer getVarCount();

	/**
	 * ��õ�һ����װ���б��ִ�н����
	 * 
	 * @return ��̬����ļ��Ͻ����
	 */
	public Collection<CosmosDynamicObject> getList();

	/**
	 * ��õ�һ����װ���б��ִ�н��������ÿ�������װ��componentType�������࣬���ԵĶ�Ӧ��ϵ����fields������������
	 * 
	 * @param <T>
	 *            �б�ÿһ����������͡�
	 * @param componentType
	 *            �б�������͡�
	 * @param fields
	 *            �����б�
	 * @return �б�����
	 */
	public <T> Collection<T> getList(Class<?> componentType, String... fields);

	/**
	 * ��õ�index����װ���б��ִ�н����
	 * 
	 * @param index
	 *            �б���š�
	 * @return ��̬����ļ��Ͻ����
	 */
	public Collection<CosmosDynamicObject> getList(int index);

	/**
	 * �������Ϊname�ķ�װ���б��ִ�н����
	 * 
	 * @param name
	 *            �б����ơ�
	 * @return ��̬����ļ��Ͻ����
	 * @throws IllegalArgumentException
	 *             ���������֧�ֶ��б��Ͻ��������Ļ������׳����쳣��
	 */
	public Collection<CosmosDynamicObject> getList(String name)
			throws IllegalArgumentException;

	/**
	 * ��õ�index����װ���б��ִ�н��������ÿ�������װ��componentType�������࣬���ԵĶ�Ӧ��ϵ����fields������������
	 * 
	 * @param <T>
	 *            �б�ÿһ����������͡�
	 * @param index
	 *            �б���š�
	 * @param componentType
	 *            �б�������͡�
	 * @param fields
	 *            �����б�
	 * @return �б�����
	 */
	public <T> Collection<T> getList(int index, Class<?> componentType,
			String... fields);

	/**
	 * �������Ϊname�ķ�װ���б��ִ�н��������ÿ�������װ��componentType�������࣬���ԵĶ�Ӧ��ϵ����fields������������
	 * 
	 * @param <T>
	 *            �б�ÿһ����������͡�
	 * @param name
	 *            �б����ơ�
	 * @param componentType
	 *            �б�������͡�
	 * @param fields
	 *            �����б�
	 * @return �б�����
	 * @throws IllegalArgumentException
	 *             ���������֧�ֶ��б��Ͻ��������Ļ������׳����쳣��
	 */
	public <T> Collection<T> getList(String name, Class<?> componentType,
			String... fields) throws IllegalArgumentException;

	/**
	 * ��÷�װ���б�������б�
	 * 
	 * @return �����б�
	 * @throws IllegalArgumentException
	 *             ���������֧�ֶ��б��Ͻ��������Ļ������׳����쳣��
	 */
	public Collection<String> getListNames() throws IllegalArgumentException;

	/**
	 * ����б��������
	 * 
	 * @return �б��������
	 */
	public Integer getListCount();

	/**
	 * ������������ĳ��ԭ�������װ�ɶ���Ľ����
	 * 
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @return �������
	 */
	public CosmosDynamicObject getVarObject(String domain, String command);

	/**
	 * ����̬�����װ�ɶ�Ӧ���ࡣ
	 * 
	 * @param <T>
	 *            ���ظö�Ӧ��Ķ���
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @param clazz
	 *            Ҫ��װ�ɵĶ�Ӧ�ࡣ
	 * @param fields
	 *            �����б�
	 * @return �������
	 */
	public <T> T getVarObject(String domain, String command, Class<?> clazz,
			String... fields);

	/**
	 * ������������ĳ��ԭ������ִ�н����װ֮���ĳ�����ԡ�
	 * 
	 * @param <T>
	 *            �������͡�
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @param name
	 *            ��������
	 * @return ����ֵ��
	 */
	public <T> T getVar(String domain, String command, String name);

	/**
	 * ������Ż�����������ĳ��ԭ������ִ�н����װ֮���ĳ�����ԡ�
	 * 
	 * @param <T>
	 *            �������͡�
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @param index
	 *            �������
	 * @return ����ֵ��
	 */
	public <T> T getVar(String domain, String command, int index);

	/**
	 * ������������ĳ��ԭ������ִ�н�����е��������ơ�
	 * 
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @return �������������б�
	 */
	public Collection<String> getVarNames(String domain, String command);

	/**
	 * ������������ĳ��ԭ������ִ�н��������������
	 * 
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @return ��������������
	 */
	public Integer getVarCount(String domain, String command);

	/**
	 * ������������ĳ��ԭ������ִ�н����һ����װ���б�ļ��ϡ�
	 * 
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @return �б�����
	 */
	public Collection<CosmosDynamicObject> getList(String domain, String command);

	/**
	 * ���ĳ�������һ����װ���б��ִ�н��������ÿ�������װ��componentType�������࣬���ԵĶ�Ӧ��ϵ����fields������������
	 * 
	 * @param <T>
	 *            �б�ÿһ����������͡�
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @param componentType
	 *            �б�������͡�
	 * @param fields
	 *            �����б�
	 * @return �б�����
	 */
	public <T> Collection<T> getList(String domain, String command,
			Class<?> componentType, String... fields);

	/**
	 * ���ĳ�������index����װ���б��ִ�н����
	 * 
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @param index
	 *            �б����
	 * @return �б�����
	 */
	public Collection<CosmosDynamicObject> getList(String domain,
			String command, int index);

	/**
	 * ���ĳ����������Ϊname�ķ�װ���б��ִ�н����
	 * 
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @param name
	 *            �б����ơ�
	 * @return �б�����
	 * @throws IllegalArgumentException
	 *             ���������֧�ֶ��б��Ͻ��������Ļ������׳����쳣��
	 */
	public Collection<CosmosDynamicObject> getList(String domain,
			String command, String name) throws IllegalArgumentException;

	/**
	 * ���ĳ�������index����װ���б��ִ�н��������ÿ�������װ��componentType�������࣬���ԵĶ�Ӧ��ϵ����fields������������
	 * 
	 * @param <T>
	 *            �б�ÿһ����������͡�
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @param index
	 * @param componentType
	 *            �б�������͡�
	 * @param fields
	 *            �����б�
	 * @return �б�����
	 */
	public <T> Collection<T> getList(String domain, String command, int index,
			Class<?> componentType, String... fields);

	/**
	 * ���ĳ����������Ϊname�ķ�װ���б��ִ�н��������ÿ�������װ��componentType�������࣬���ԵĶ�Ӧ��ϵ����fields������������
	 * 
	 * @param <T>
	 *            �б�ÿһ����������͡�
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @param name
	 *            �б����ơ�
	 * @param componentType
	 *            �б�������͡�
	 * @param fields
	 *            �����б�
	 * @return �б�����
	 * @throws IllegalArgumentException
	 *             ���������֧�ֶ��б��Ͻ��������Ļ������׳����쳣��
	 */
	public <T> Collection<T> getList(String domain, String command,
			String name, Class<?> componentType, String... fields)
			throws IllegalArgumentException;

	/**
	 * ���ĳ�������װ���б�������б�
	 * 
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @return �����б�
	 * @throws IllegalArgumentException
	 *             ���������֧�ֶ��б��Ͻ��������Ļ������׳����쳣��
	 */
	public Collection<String> getListNames(String domain, String command)
			throws IllegalArgumentException;

	/**
	 * ����б��������
	 * 
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @return �б�������
	 */
	public Integer getListCount(String domain, String command);

	/**
	 * �ϲ������������
	 * 
	 * @param result
	 *            ���ϲ��Ľ������
	 * @return �ϲ�֮��Ľ������
	 */
	public CosmosResult combine(CosmosResult result);

	/**
	 * ������ָ������н������ӳ�䡣
	 * 
	 * @return �������ӳ�䡣
	 */
	public Map<String, CosmosDynamicObject> getObjectMap();

	/**
	 * �г���������Ľ���б�ӳ�䡣
	 * 
	 * @return ����б�ӳ�䡣
	 */
	public Map<String, List<CosmosDynamicList<CosmosDynamicObject>>> getListMap();

	/**
	 * ����һ���µĽ������
	 * 
	 * @return �������
	 */
	public CosmosDynamicObject newObject();

}
