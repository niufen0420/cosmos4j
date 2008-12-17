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
 * 认证的工厂类，通过本类可以创建一个认证之后的认证对象，也可以创建一个匿名的认证对象。
 * </p>
 * <p>
 * 用户在经过认证之后，需要带着本类创建的认证对象，在下次操作时进行验证是否该认证对象已经失效。
 * </p>
 * <p>
 * 这个类是一个单例类，确保在每一次认证过程中，都只有一个实例可用。
 * </p>
 * <p>
 * 这个类是一个抽象类，实际的认证工厂类需要扩展自这个类，实现方法<code>loadAuthorization</code>来决定如何进行认证过程。
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
	 * 认证工厂类的默认实现。
	 */
	private static final String FACTORY_CLASS = "com.microbrain.cosmos.core.auth.AuthorizationFactoryImpl";

	/**
	 * 匿名的认证类。
	 */
	private static final Authorization ANONYMOUS_AUTHOTIZATION = new Authorization(
			null, null);

	/**
	 * 认证工厂类实例引用。
	 */
	private static AuthorizationFactory factory = null;

	/**
	 * 同步锁。
	 */
	private static final Object locked = new Object();

	/**
	 * 创建一个认证对象。
	 * 
	 * @param name
	 *            用户名。
	 * @param password
	 *            用户密码。
	 * @param service
	 *            用户登录的服务。
	 * @return 认证对象。
	 * @throws AuthorizationException
	 *             认证过程中抛出的异常。
	 */
	public static Authorization createAuthorization(String name,
			String password, String service) throws AuthorizationException {
		loadFactory();
		return factory.loadAuthorization(name, password, service);
	}

	/**
	 * 创建一个匿名的认证对象。
	 * 
	 * @return 匿名认证对象。
	 * @throws AuthorizationException
	 *             认证过程中抛出的异常。
	 */
	public static Authorization createAnonymousAuthorization()
			throws AuthorizationException {
		return ANONYMOUS_AUTHOTIZATION;
	}

	/**
	 * 装载配置文件中所配置的认证工厂实现。
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
	 * 装载认证对象。
	 * 
	 * @param name
	 *            用户名。
	 * @param password
	 *            用户密码。
	 * @param service
	 *            用户认证的服务。
	 * @return 用户认证对象。
	 * @throws AuthorizationException
	 *             认证过程中抛出的异常。
	 */
	protected abstract Authorization loadAuthorization(String name,
			String password, String service) throws AuthorizationException;

}
