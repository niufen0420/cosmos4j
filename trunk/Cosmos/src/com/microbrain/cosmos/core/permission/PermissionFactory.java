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
package com.microbrain.cosmos.core.permission;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.CosmosFactoryException;
import com.microbrain.cosmos.core.auth.Authorization;
import com.microbrain.cosmos.core.config.Configuration;

/**
 * <p>
 * Ȩ�޹����࣬ͨ��������Ի��ϵͳ�в���ĳ����������Ҫ��Ȩ�ޣ�Ҳ���Ի�ø������ӵ�е�Ȩ�ޡ�
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.permission.JacePermission
 * @since CFDK 1.0
 */
public abstract class PermissionFactory {

	/**
	 * Ȩ�޹������Ĭ��ʵ�֡�
	 */
	private static final String FACTORY_CLASS = "com.jace.system.logic.permissions.impl.DefaultPermissionFactory";

	/**
	 * Ȩ�޹�����ʵ����
	 */
	private static PermissionFactory factory = null;

	/**
	 * ͬ������
	 */
	private static final Object locked = new Object();

	/**
	 * ˽�л����캯����
	 */
	protected PermissionFactory() {
	}

	/**
	 * ���Ȩ�޹������ʵ����
	 * 
	 * @return Ȩ�޹������ʵ����
	 */
	public static PermissionFactory getInstance() {
		if (factory == null) {
			synchronized (locked) {
				if (factory == null) {
					String factoryClass = null;
					CosmosFactory f = null;
					try {
						f = CosmosFactory.getInstance();
					} catch (CosmosFactoryException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					Configuration config = f.lookupConfig();
					if ((factoryClass = config.getPermFactory()) == null) {
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
						factory = (PermissionFactory) clazz.newInstance();
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

		return factory;
	}

	/**
	 * ��ò���ĳ����������Ҫ��Ȩ�ޡ�
	 * 
	 * @param object
	 *            ϵͳ��ĳ�������ID��
	 * @param operation
	 *            ��Ըö���ɽ��е�ĳ�ֲ�����
	 * @return ���ز����ö�������Ҫ��Ȩ�ޡ�
	 */
	public abstract JacePermission getNeededPermission(String object,
			String operation);

	/**
	 * ���ĳ����֤������ӵ�е�Ȩ�ޡ�
	 * 
	 * @param auth
	 *            ��֤��Ϣ��
	 * @return ����֤������ӵ�е�Ȩ�ޡ�
	 */
	public abstract JacePermission getOwnedPermission(Authorization auth);

	/**
	 * ɾ��������ĳ����֤������ӵ�е�Ȩ�ޡ�
	 * 
	 * @param auth
	 *            ��֤��Ϣ��
	 */
	public abstract void removeOwnedPermission(Authorization auth);

	/**
	 * ɾ��ĳ����֤ID��ӵ�е�Ȩ�ޡ�
	 * 
	 * @param passportId
	 *            ��֤����ID��
	 */
	public abstract void removeOwnedPermission(String passportId);

	/**
	 * ���������֤���󻺴��Ȩ�ޡ�
	 */
	public abstract void clearOwnedPermission();

}
