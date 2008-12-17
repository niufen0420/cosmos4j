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
package com.microbrain.cosmos.core.auth;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.CosmosFactoryException;
import com.microbrain.cosmos.core.config.Configuration;

/**
 * <p>
 * <code>com.microbrain.cosmos.core.auth.AuthorizationFactory</code>
 * ��֤�Ĺ����࣬ͨ��������Դ���һ����֤֮�����֤����Ҳ���Դ���һ����������֤����
 * </p>
 * <p>
 * �û��ھ�����֤֮����Ҫ���ű��ഴ������֤�������´β���ʱ������֤�Ƿ����֤�����Ѿ�ʧЧ��
 * </p>
 * <p>
 * �������һ�������࣬ȷ����ÿһ����֤�����У���ֻ��һ��ʵ�����á�
 * </p>
 * <p>
 * �������һ�������࣬ʵ�ʵ���֤��������Ҫ��չ������࣬ʵ�ַ���<code>loadAuthorization</code>��������ν�����֤���̡�
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.auth.AuthorizationFactoryImpl
 * @see com.microbrain.cosmos.core.auth.Authorization
 * @since CFDK 1.0
 */
public abstract class AuthorizationFactory {

	/**
	 * ��֤�������Ĭ��ʵ�֡�
	 */
	private static final String FACTORY_CLASS = "com.microbrain.cosmos.core.auth.AuthorizationFactoryImpl";

	/**
	 * ��������֤�ࡣ
	 */
	private static final Authorization ANONYMOUS_AUTHOTIZATION = new Authorization(
			null, null);

	/**
	 * ��֤������ʵ�����á�
	 */
	private static AuthorizationFactory factory = null;

	/**
	 * ͬ������
	 */
	private static final Object locked = new Object();

	/**
	 * ����һ����֤����
	 * 
	 * @param name
	 *            �û�����
	 * @param password
	 *            �û����롣
	 * @param service
	 *            �û���¼�ķ���
	 * @return ��֤����
	 * @throws AuthorizationException
	 *             ��֤�������׳����쳣��
	 */
	public static Authorization createAuthorization(String name,
			String password, String service) throws AuthorizationException {
		loadFactory();
		return factory.loadAuthorization(name, password, service);
	}

	/**
	 * ����һ����������֤����
	 * 
	 * @return ������֤����
	 * @throws AuthorizationException
	 *             ��֤�������׳����쳣��
	 */
	public static Authorization createAnonymousAuthorization()
			throws AuthorizationException {
		return ANONYMOUS_AUTHOTIZATION;
	}

	/**
	 * װ�������ļ��������õ���֤����ʵ�֡�
	 */
	private static void loadFactory() {
		if (factory == null) {
			synchronized (locked) {
				if (factory == null) {
					String factoryClass = null;
					CosmosFactory cf = null;
					try {
						cf = CosmosFactory.getInstance();
					} catch (CosmosFactoryException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Configuration config = cf.lookupConfig();
					if ((factoryClass = config.getAuthFactory()) == null) {
						factoryClass = FACTORY_CLASS;
					}

					Class<?> clazz = null;
					try {
						clazz = Class.forName(factoryClass);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					try {
						factory = (AuthorizationFactory) clazz.newInstance();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * װ����֤����
	 * 
	 * @param name
	 *            �û�����
	 * @param password
	 *            �û����롣
	 * @param service
	 *            �û���֤�ķ���
	 * @return �û���֤����
	 * @throws AuthorizationException
	 *             ��֤�������׳����쳣��
	 */
	protected abstract Authorization loadAuthorization(String name,
			String password, String service) throws AuthorizationException;

}
