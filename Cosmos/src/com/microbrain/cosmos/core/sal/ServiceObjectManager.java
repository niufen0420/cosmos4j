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
 * 服务对象的管理者，负责创建一个服务接口的对象，通过Java动态代理机制达到本目的。
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
	 * 获得一个没有认证信息的服务对象。
	 * 
	 * @param <T>
	 *            模板类名称。
	 * @param clazz
	 *            模板类。
	 * @param factory
	 *            Cosmos工厂类。
	 * @return 服务对象。
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getServiceObject(Class<?> clazz, CosmosFactory factory) {
		return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
				new Class[] { clazz }, new ServiceDelegate(factory));
	}

	/**
	 * 获得一个带有认证信息的服务对象。
	 * 
	 * @param <T>
	 *            模板类名称。
	 * @param auth
	 *            认证对象。
	 * @param clazz
	 *            模板类。
	 * @param factory
	 *            Cosmos工厂类。
	 * @return 服务对象。
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getServiceObject(Authorization auth, Class<?> clazz,
			CosmosFactory factory) {
		return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
				new Class[] { clazz }, new ServiceDelegate(auth, factory));
	}

}
