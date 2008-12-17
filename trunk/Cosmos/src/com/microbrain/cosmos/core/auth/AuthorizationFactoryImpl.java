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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.CosmosFactoryException;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.CosmosDynamicObject;
import com.microbrain.cosmos.core.command.CosmosExecuteException;
import com.microbrain.cosmos.core.command.CosmosResult;
import com.microbrain.cosmos.core.config.Configuration;
import com.microbrain.cosmos.core.config.ConfigurationException;

/**
 * <p>
 * <code>com.microbrain.cosmos.core.auth.AuthorizationFactory</code>的默认实现，实现了利用
 * <code>cosmos</code>框架中所提供的服务进行认证对象的装载过程。
 * </p>
 * <p>
 * 用户可以通过配置不同的命令，来达到从不同的环境中装载认证对象的目的。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.auth.AuthorizationFactory
 * @see com.microbrain.cosmos.core.auth.Authorization
 * @since CFDK 1.0
 */
public class AuthorizationFactoryImpl extends AuthorizationFactory {

	/**
	 * 认证所使用的默认命令名称。
	 */
	private static final String DEFAULT_AUTH_COMMAND = "login";

	/**
	 * 认证所需命令所属域键。
	 */
	private static final String AUTH_DOMAIN_KEY = "domain";

	/**
	 * 认证所需命令键。
	 */
	private static final String AUTH_COMMAND_KEY = "command";

	/**
	 * 默认的认证对象实现。
	 */
	private static final String DEFAULT_AUTHORIZATION = "com.jace.cosmos.core.auth.Authorization";

	/**
	 * 认证对象键。
	 */
	private static final String COSMOS_AUTHENTICATION = "cosmos.authentication";

	/**
	 * 认证对象类。
	 */
	private Class<?> authorizationClass = null;

	/**
	 * 认证命令所属域。
	 */
	private String domain = null;

	/**
	 * 认证命令名称。
	 */
	private String command = null;

	/**
	 * 认证命令对象。
	 */
	private CosmosCommand loginCommand = null;

	/**
	 * 默认构造函数。
	 */
	public AuthorizationFactoryImpl() {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Configuration config = factory.lookupConfig();
		String clazz = config.getAuthClass();
		if (clazz == null) {
			clazz = DEFAULT_AUTHORIZATION;
		}

		try {
			authorizationClass = Class.forName(clazz);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			this.domain = config.getElementInitParameter(COSMOS_AUTHENTICATION,
					AUTH_DOMAIN_KEY);
			this.command = config.getElementInitParameter(
					COSMOS_AUTHENTICATION, AUTH_COMMAND_KEY);

			if (this.command == null) {
				this.command = DEFAULT_AUTH_COMMAND;
			}

			if (this.domain == null) {
				this.loginCommand = factory.getCommand(command);
			} else {
				this.loginCommand = factory.getCommand(domain, command);
			}
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 装载认证对象过程。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.auth.AuthorizationFactory#loadAuthorization
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	protected Authorization loadAuthorization(String name, String password,
			String service) throws AuthorizationException {
		if (password == null) {
			throw new AuthorizationException(AuthorizationType.PASSWORD_IS_NULL
					.getValue());
		}

		Authorization auth = null;
		try {
			auth = (Authorization) authorizationClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CosmosResult result = null;
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("name", name);
		args.put("password", password);
		args.put("service", service);

		try {
			result = this.loginCommand.execute(args);
		} catch (CosmosExecuteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Collection<CosmosDynamicObject> loginResult = result.getList();

		if (loginResult.size() < 1) {
			throw new AuthorizationException(AuthorizationType.NO_THIS_USER
					.getValue());
		}

		if (loginResult.size() > 1) {
			throw new AuthorizationException(AuthorizationType.SYSTEM_ERROR
					.getValue());
		}

		String passportId = null, savedPassword = null;
		for (CosmosDynamicObject object : loginResult) {
			passportId = object.getVar(0);
			savedPassword = object.getVar(1);
		}

		if (!password.equals(savedPassword)) {
			throw new AuthorizationException(AuthorizationType.PASSWORD_ERROR
					.getValue());
		}

		auth.passportId = passportId;
		auth.service = service;

		return auth;
	}

}
