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
package com.microbrain.cosmos.core.sal;

import java.lang.reflect.Proxy;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.auth.Authorization;

/**
 * <p>
 * �������Ĺ����ߣ����𴴽�һ������ӿڵĶ���ͨ��Java��̬������ƴﵽ��Ŀ�ġ�
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.sal.ServiceDelegate
 * @see com.microbrain.cosmos.core.sal.ServiceException
 * @see com.microbrain.cosmos.core.sal.annotation.Command
 * @see com.microbrain.cosmos.core.sal.annotation.Domain
 * @since CFDK 1.0
 */
public final class ServiceObjectManager {

	/**
	 * ���һ��û����֤��Ϣ�ķ������
	 * 
	 * @param <T>
	 *            ģ�������ơ�
	 * @param clazz
	 *            ģ���ࡣ
	 * @param factory
	 *            Cosmos�����ࡣ
	 * @return �������
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getServiceObject(Class<?> clazz, CosmosFactory factory) {
		return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
				new Class[] { clazz }, new ServiceDelegate(factory));
	}

	/**
	 * ���һ��������֤��Ϣ�ķ������
	 * 
	 * @param <T>
	 *            ģ�������ơ�
	 * @param auth
	 *            ��֤����
	 * @param clazz
	 *            ģ���ࡣ
	 * @param factory
	 *            Cosmos�����ࡣ
	 * @return �������
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getServiceObject(Authorization auth, Class<?> clazz,
			CosmosFactory factory) {
		return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
				new Class[] { clazz }, new ServiceDelegate(auth, factory));
	}

}
