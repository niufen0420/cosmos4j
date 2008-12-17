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
package com.microbrain.cosmos.core.command;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.auth.Authorization;
import com.microbrain.cosmos.core.config.Configuration;
import com.microbrain.cosmos.core.constants.DebugLevel;
import com.microbrain.cosmos.core.permission.AccessController;
import com.microbrain.cosmos.core.permission.CosmosPermissionException;

/**
 * <p>
 * <code>com.microbrain.cosmos.core.command.CosmosExecuter</code>
 * �ĳ���ʵ�֣��ṩ������һ��ִ�����ĸ��ַ�����
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosExecuter
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @since CFDK 1.0
 */
public abstract class AbstractCosmosExecuter implements CosmosExecuter {

	/**
	 * Cosmos�����ࡣ
	 */
	protected CosmosFactory factory = null;

	/**
	 * Cosmos�����ࡣ
	 */
	protected Configuration config = null;

	/**
	 * ִ�������ơ�
	 */
	protected String name = null;

	/**
	 * ִ������ǩ��
	 */
	protected String label = null;

	/**
	 * ִ����˵����
	 */
	protected String description = null;

	/**
	 * ִ�������ࡣ
	 */
	protected String category = null;

	/**
	 * ��־��¼����
	 */
	protected Log log = LogFactory.getLog(CosmosExecuter.class);

	/**
	 * ���캯����
	 * 
	 * @param name
	 *            ִ�������ơ�
	 * @param label
	 *            ִ������ǩ��
	 * @param description
	 *            ִ����˵����
	 * @param category
	 *            ִ�������ࡣ
	 */
	public AbstractCosmosExecuter(String name, String label,
			String description, String category) {
		this.name = name;
		this.label = label;
		this.description = description;
		this.category = category;
	}

	/*
	 * ���ִ�������ࡣ
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosExecuter#getCategory()
	 */
	public String getCategory() {
		return this.category;
	}

	/*
	 * ���ִ����˵����
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosExecuter#getDescription()
	 */
	public String getDescription() {
		return this.description;
	}

	/*
	 * ���ִ������ǩ��
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosExecuter#getLabel()
	 */
	public String getLabel() {
		return this.label;
	}

	/*
	 * ���ִ�������ơ�
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosExecuter#getName()
	 */
	public String getName() {
		return this.name;
	}

	/*
	 * ��ʼ��ִ������
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosExecuter#init(com.microbrain
	 * .cosmos.core.CosmosFactory,
	 * com.microbrain.cosmos.core.config.Configuration)
	 */
	public void init(CosmosFactory factory, Configuration config) {
		this.factory = factory;
		this.config = config;
	}

	/*
	 * ִ�����
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosExecuter#execute(com.microbrain
	 * .cosmos.core.auth.Authorization,
	 * com.microbrain.cosmos.core.command.CosmosCommand, java.util.Map)
	 */
	public CosmosResult execute(Authorization auth, CosmosCommand command,
			Map<String, Object> args) throws CosmosExecuteException,
			CosmosPermissionException {
		if (!AccessController.checkPermission(auth, command.getDomain()
				.getName(), command.getName())) {
			if (command.isLogEnabled(DebugLevel.INFO)
					|| (command.getDebugLevel() == DebugLevel.NO_DEBUG && log
							.isInfoEnabled())) {
				log
						.info("User '"
								+ auth.getPassportId()
								+ "' does not have permission to execute this command '"
								+ command.getDomain().getName() + "." + command
								+ "'.");
			}

			throw new CosmosPermissionException("User '" + auth.getPassportId()
					+ "' does not have permission to execute this command '"
					+ command + "'.");
		}

		return execute(command, args);
	}

}
