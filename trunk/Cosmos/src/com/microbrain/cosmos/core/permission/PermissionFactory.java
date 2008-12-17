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
 * 权限工厂类，通过本类可以获得系统中操作某个对象所需要的权限，也可以获得该身份所拥有的权限。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.permission.JacePermission
 * @since CFDK 1.0
 */
public abstract class PermissionFactory {

	/**
	 * 权限工厂类的默认实现。
	 */
	private static final String FACTORY_CLASS = "com.jace.system.logic.permissions.impl.DefaultPermissionFactory";

	/**
	 * 权限工厂类实例。
	 */
	private static PermissionFactory factory = null;

	/**
	 * 同步锁。
	 */
	private static final Object locked = new Object();

	/**
	 * 私有化构造函数。
	 */
	protected PermissionFactory() {
	}

	/**
	 * 获得权限工厂类的实例。
	 * 
	 * @return 权限工厂类的实例。
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
	 * 获得操作某个对象所需要的权限。
	 * 
	 * @param object
	 *            系统中某个对象的ID。
	 * @param operation
	 *            针对该对象可进行的某种操作。
	 * @return 返回操作该对象所需要的权限。
	 */
	public abstract JacePermission getNeededPermission(String object,
			String operation);

	/**
	 * 获得某个认证对象所拥有的权限。
	 * 
	 * @param auth
	 *            认证信息。
	 * @return 该认证对象所拥有的权限。
	 */
	public abstract JacePermission getOwnedPermission(Authorization auth);

	/**
	 * 删除缓存中某个认证对象所拥有的权限。
	 * 
	 * @param auth
	 *            认证信息。
	 */
	public abstract void removeOwnedPermission(Authorization auth);

	/**
	 * 删除某个认证ID所拥有的权限。
	 * 
	 * @param passportId
	 *            认证对象ID。
	 */
	public abstract void removeOwnedPermission(String passportId);

	/**
	 * 清除所有认证对象缓存的权限。
	 */
	public abstract void clearOwnedPermission();

}
