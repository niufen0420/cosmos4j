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
 * ����ӿڵĴ���ִ���࣬ͨ������<code>cosmos</code>����е����������ʵ�ֽӿڵĴ���ִ�С�
 * </p>
 * <p>
 * �����������ٵ���CosmosFactory�ȷ�������������ͨ������ִ����Ҫ�ķ��񣬿��Խ��Խӿڽ��е��ü��ɡ�
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
	 * Cosmos�����ࡣ
	 */
	private CosmosFactory factory = null;

	/**
	 * Cosmos�����ࡣ
	 */
	private Configuration config = null;

	/**
	 * ��֤��Ϣ��
	 */
	private Authorization auth = null;

	/**
	 * ���캯����
	 * 
	 * @param factory
	 *            Cosmos�����ࡣ
	 */
	public ServiceDelegate(CosmosFactory factory) {
		this.factory = factory;
		this.config = this.factory.lookupConfig();
	}

	/**
	 * ���캯����
	 * 
	 * @param auth
	 *            ��֤��Ϣ��
	 * @param factory
	 *            Cosmos�����ࡣ
	 */
	public ServiceDelegate(Authorization auth, CosmosFactory factory) {
		this.factory = factory;
		this.config = this.factory.lookupConfig();
		this.auth = auth;
	}

	/*
	 * ����ִ�С�
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
