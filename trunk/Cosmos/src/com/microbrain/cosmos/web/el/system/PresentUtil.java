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
package com.microbrain.cosmos.web.el.system;

import java.util.Collection;

import com.microbrain.cosmos.core.command.CosmosDynamicObject;
import com.microbrain.cosmos.core.command.CosmosResult;

/**
 * <p>
 * ��ʾ��<code>EL</code>���ʽ���������ӵײ�����в����Ľ��ת��Ϊҳ���ܹ���ʾ�Ķ���
 * </p>
 * <p>
 * Ĭ�ϵأ�<code>Servlet</code>�Ὣִ�н��������<code>request</code>�У�Ĭ�ϵأ�ȡ��Ϊ��
 * <code>result</code>������ҳ����ͨ�������ƶԽ�����в�����
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.CosmosFactory
 * @see com.microbrain.cosmos.core.command.CosmosResult
 * @since CFDK 1.0
 */
public class PresentUtil {

	/**
	 * ˽�л����캯����
	 */
	private PresentUtil() {
	}

	/**
	 * ��÷�װ�ɶ���Ľ����
	 * 
	 * @param result
	 *            ִ�еĽ����
	 * @return ��װ�ɶ�̬����Ľ����
	 */
	public static CosmosDynamicObject varObject(CosmosResult result) {
		return result.getVarObject();
	}

	/**
	 * ��÷�װ֮���ĳ�����ԡ�
	 * 
	 * @param <T>
	 *            �������͡�
	 * @param name
	 *            �������ơ�
	 * @param result
	 *            ִ�еĽ����
	 * @return ���Ե�ֵ��
	 */
	public static <T> T var(String name, CosmosResult result) {
		return result.getVar(name);
	}

	/**
	 * ������ŷ���ĳ�����ԡ�
	 * 
	 * @param <T>
	 *            �������͡�
	 * @param index
	 *            ���Ե���š�
	 * @param result
	 *            ִ�еĽ����
	 * @return ����ֵ��
	 */
	public static <T> T var(Integer index, CosmosResult result) {
		return result.getVar(index);
	}

	/**
	 * ������е��������ơ�
	 * 
	 * @param result
	 *            ִ�еĽ����
	 * @return �����������ơ�
	 */
	public static Collection<String> varNames(CosmosResult result) {
		return result.getVarNames();
	}

	/**
	 * �������Ը�����
	 * 
	 * @param result
	 *            ִ�еĽ����
	 * @return ���Ը�����
	 */
	public static Integer varCount(CosmosResult result) {
		return result.getVarCount();
	}

	/**
	 * ��õ�һ����װ���б��ִ�н����
	 * 
	 * @param result
	 *            ִ�еĽ����
	 * @return ��̬����ļ��Ͻ����
	 */
	public static Collection<CosmosDynamicObject> list(CosmosResult result) {
		return result.getList();
	}

	/**
	 * ��õ�index����װ���б��ִ�н����
	 * 
	 * @param index
	 *            �б���š�
	 * @param result
	 *            ִ�еĽ����
	 * @return ��̬����ļ��Ͻ����
	 */
	public static Collection<CosmosDynamicObject> list(Integer index,
			CosmosResult result) {
		return result.getList(index);
	}

	/**
	 * �������Ϊname�ķ�װ���б��ִ�н����
	 * 
	 * @param name
	 *            �б����ơ�
	 * @param result
	 *            ִ�еĽ����
	 * @return ��̬����ļ��Ͻ����
	 * @throws IllegalArgumentException
	 *             ���������֧�ֶ��б��Ͻ��������Ļ������׳����쳣��
	 */
	public static Collection<CosmosDynamicObject> list(String name,
			CosmosResult result) throws IllegalArgumentException {
		return result.getList(name);
	}

	/**
	 * ��÷�װ���б�������б�
	 * 
	 * @param result
	 *            ִ�еĽ����
	 * @return �����б�
	 * @throws IllegalArgumentException
	 *             ���������֧�ֶ��б��Ͻ��������Ļ������׳����쳣��
	 */
	public static Collection<String> listNames(CosmosResult result)
			throws IllegalArgumentException {
		return result.getListNames();
	}

	/**
	 * ����б��������
	 * 
	 * @param result
	 *            ִ�еĽ����
	 * @return �б��������
	 */
	public static Integer listCount(CosmosResult result) {
		return result.getListCount();
	}

	/**
	 * ������������ĳ��ԭ�������װ�ɶ���Ľ����
	 * 
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @param result
	 *            ִ�еĽ����
	 * @return �������
	 */
	public static CosmosDynamicObject varObject(String domain, String command,
			CosmosResult result) {
		return result.getVarObject(domain, command);
	}

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
	 *            �������ơ�
	 * @param result
	 *            ִ�еĽ����
	 * @return ����ֵ��
	 */
	public static <T> T var(String domain, String command, String name,
			CosmosResult result) {
		return result.getVar(domain, command, name);
	}

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
	 * @param result
	 *            ִ�еĽ����
	 * @return ����ֵ��
	 */
	public static <T> T var(String domain, String command, Integer index,
			CosmosResult result) {
		return result.getVar(domain, command, index);
	}

	/**
	 * ������������ĳ��ԭ������ִ�н�����е��������ơ�
	 * 
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @param result
	 *            ִ�еĽ����
	 * @return �������������б�
	 */
	public static Collection<String> varNames(String domain, String command,
			CosmosResult result) {
		return result.getVarNames(domain, command);
	}

	/**
	 * ������������ĳ��ԭ������ִ�н��������������
	 * 
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @param result
	 *            ִ�еĽ����
	 * @return ��������������
	 */
	public static Integer varCount(String domain, String command,
			CosmosResult result) {
		return result.getVarCount(domain, command);
	}

	/**
	 * ������������ĳ��ԭ������ִ�н����һ����װ���б�ļ��ϡ�
	 * 
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @param result
	 *            ִ�еĽ����
	 * @return �б�����
	 */
	public static Collection<CosmosDynamicObject> list(String domain,
			String command, CosmosResult result) {
		return result.getList(domain, command);
	}

	/**
	 * ���ĳ�������index����װ���б��ִ�н����
	 * 
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @param index
	 *            �б���š�
	 * @param result
	 *            ִ�еĽ����
	 * @return �б�����
	 */
	public static Collection<CosmosDynamicObject> list(String domain,
			String command, Integer index, CosmosResult result) {
		return result.getList(domain, command, index);
	}

	/**
	 * ���ĳ����������Ϊname�ķ�װ���б��ִ�н����
	 * 
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @param name
	 *            �б����ơ�
	 * @param result
	 *            ִ�еĽ����
	 * @return �б�����
	 * @throws IllegalArgumentException
	 *             ���������֧�ֶ��б��Ͻ��������Ļ������׳����쳣��
	 */
	public static Collection<CosmosDynamicObject> list(String domain,
			String command, String name, CosmosResult result)
			throws IllegalArgumentException {
		return result.getList(domain, command, name);
	}

	/**
	 * ���ĳ�������װ���б�������б�
	 * 
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @param result
	 *            ִ�еĽ����
	 * @return �����б�
	 * @throws IllegalArgumentException
	 *             ���������֧�ֶ��б��Ͻ��������Ļ������׳����쳣��
	 */
	public static Collection<String> listNames(String domain, String command,
			CosmosResult result) throws IllegalArgumentException {
		return result.getListNames(domain, command);
	}

	/**
	 * ����б��������
	 * 
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @param result
	 *            ִ�еĽ����
	 * @return �б�������
	 */
	public static Integer listCount(String domain, String command,
			CosmosResult result) {
		return result.getListCount(domain, command);
	}

	/**
	 * ����б��еĵ�һ������
	 * 
	 * @param list
	 *            Ҫ�������б�
	 * @return �б��еĵ�һ������
	 */
	public static Object first(Collection<?> list) {
		if (list.size() < 1) {
			return null;
		}

		return list.toArray()[0];
	}

	/**
	 * ����б��е����һ������
	 * 
	 * @param list
	 *            Ҫ�������б�
	 * @return �б��е����һ������
	 */
	public static Object last(Collection<?> list) {
		int size = list.size();
		if (size < 1) {
			return null;
		}

		return list.toArray()[size - 1];
	}

	/**
	 * ����б��е�ĳ������
	 * 
	 * @param index
	 *            �ö������š�
	 * @param list
	 *            Ҫ�������б�
	 * @return ����Ŷ�Ӧ�Ķ���
	 */
	public static Object get(Integer index, Collection<?> list) {
		int size = list.size();
		if (size < 1 || index >= size) {
			return null;
		}

		return list.toArray()[index];
	}

}
