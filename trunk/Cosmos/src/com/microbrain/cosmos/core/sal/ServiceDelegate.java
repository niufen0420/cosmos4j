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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.auth.Authorization;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.CosmosExecuteException;
import com.microbrain.cosmos.core.command.CosmosMetaArgument;
import com.microbrain.cosmos.core.config.Configuration;
import com.microbrain.cosmos.core.constants.ArgumentInOutType;
import com.microbrain.cosmos.core.domain.CosmosDomain;
import com.microbrain.cosmos.core.permission.AccessController;
import com.microbrain.cosmos.core.permission.CosmosPermissionException;
import com.microbrain.cosmos.core.sal.annotation.Command;
import com.microbrain.cosmos.core.sal.annotation.Domain;

/**
 * <p>
 * 服务接口的代理执行类，通过调用<code>cosmos</code>框架中的命令，来进行实现接口的代理执行。
 * </p>
 * <p>
 * 开发者无需再调用CosmosFactory等方法来获得命令，再通过命令执行需要的服务，可以仅对接口进行调用即可。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see java.lang.reflect.InvocationHandler
 * @see com.microbrain.cosmos.core.sal.ServiceObjectManager
 * @see com.microbrain.cosmos.core.sal.ServiceException
 * @see com.microbrain.cosmos.core.sal.annotation.Command
 * @see com.microbrain.cosmos.core.sal.annotation.Domain
 * @since CFDK 1.0
 */
class ServiceDelegate implements InvocationHandler {

	/**
	 * Cosmos工厂类。
	 */
	private CosmosFactory factory = null;

	/**
	 * Cosmos配置类。
	 */
	private Configuration config = null;

	/**
	 * 认证信息。
	 */
	private Authorization auth = null;

	/**
	 * 构造函数。
	 * 
	 * @param factory
	 *            Cosmos工厂类。
	 */
	public ServiceDelegate(CosmosFactory factory) {
		this.factory = factory;
		this.config = this.factory.lookupConfig();
	}

	/**
	 * 构造函数。
	 * 
	 * @param auth
	 *            认证信息。
	 * @param factory
	 *            Cosmos工厂类。
	 */
	public ServiceDelegate(Authorization auth, CosmosFactory factory) {
		this.factory = factory;
		this.config = this.factory.lookupConfig();
		this.auth = auth;
	}

	/*
	 * 调用执行。
	 * 
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
	 * java.lang.reflect.Method, java.lang.Object[])
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Class<?> clazz = method.getDeclaringClass();

		String domain = null;
		if (clazz.isAnnotationPresent(Domain.class)) {
			domain = clazz.getAnnotation(Domain.class).value();
		}

		if (method.isAnnotationPresent(Domain.class)) {
			domain = method.getAnnotation(Domain.class).value();
		}

		CosmosDomain cosmosDomain = null;
		if (domain == null) {
			cosmosDomain = this.config.getMasterDomain();
		} else {
			cosmosDomain = this.config.getDomains().get(domain);
		}

		String command = null;
		if (method.isAnnotationPresent(Command.class)) {
			command = method.getAnnotation(Command.class).value();
		}

		if (command == null) {
			command = method.getName();
		}

		if (auth != null
				&& !AccessController.checkPermission(auth, domain, command)) {
			throw new CosmosPermissionException("User '" + auth.getPassportId()
					+ "' does not have permission to execute this command '"
					+ command + "'.");
		}

		CosmosCommand cosmosCommand = cosmosDomain.getCommand(command);
		Map<String, Object> argsMap = new HashMap<String, Object>();
		int i = 0;
		for (CosmosMetaArgument metaArg : cosmosCommand.listMetaArguments()) {
			if (metaArg.getInOutType() == ArgumentInOutType.IN
					|| metaArg.getInOutType() == ArgumentInOutType.INOUT) {
				argsMap.put(metaArg.getName(), args[i]);
				i++;
			}
		}

		try {
			return cosmosCommand.execute(argsMap);
		} catch (CosmosExecuteException e) {
			throw new ServiceException(e);
		}
	}

}
