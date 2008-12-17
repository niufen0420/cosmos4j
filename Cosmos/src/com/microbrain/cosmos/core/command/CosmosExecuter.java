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

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.auth.Authorization;
import com.microbrain.cosmos.core.config.Configuration;
import com.microbrain.cosmos.core.permission.CosmosPermissionException;

/**
 * <p>
 * <code>CosmosExecuter</code>是cosmos框架的执行器抽象，功能是负责进行<code>CosmosCommand</code>
 * 的执行工作。并将执行结果封装成<code>CosmosResult</code>类型，以供调用者使用。
 * </p>
 * <p>
 * 并非每一个<code>CosmosExecuter</code>都可以应用于所有的domain，根据<code>CosmosExecuter</code>
 * 的<code>category</code>属性与<code>CosmosDomain</code>的<code>category</code>
 * 属性的对应关系。可以判断一个domain中有多少种可用的executer。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @see com.microbrain.cosmos.core.command.AbstractCosmosExecuter
 * @since CFDK 1.0
 */
public interface CosmosExecuter {

	/**
	 * 获得<code>CosmosExecuter</code>的名称。
	 * 
	 * @return 返回<code>CosmosExecuter</code>的名称。
	 */
	public String getName();

	/**
	 * 获得<code>CosmosExecuter</code>的标签。
	 * 
	 * @return 返回<code>CosmosExecuter</code>的标签。
	 */
	public String getLabel();

	/**
	 * 获得<code>CosmosExecuter</code>的分类。
	 * 
	 * @return 返回<code>CosmosExecuter</code>的分类。
	 */
	public String getCategory();

	/**
	 * 获得<code>CosmosExecuter</code>的描述。
	 * 
	 * @return 返回<code>CosmosExecuter</code>的描述。
	 */
	public String getDescription();

	/**
	 * 初始化<code>CosmosExecuter</code>。
	 * 
	 * @param factory
	 *            工厂类实例。
	 * @param config
	 *            配置类实例。
	 */
	public void init(CosmosFactory factory, Configuration config);

	/**
	 * 执行一个<code>CosmosCommand</code>命令，使用的参数列表为args。
	 * 
	 * @param command
	 *            要执行的命令。
	 * @param args
	 *            参数列表。
	 * @return 返回执行结果。
	 * @throws CosmosExecuteException
	 *             执行命令过程中抛出的异常。
	 */
	public CosmosResult execute(CosmosCommand command, Map<String, Object> args)
			throws CosmosExecuteException;

	/**
	 * 执行一个<code>CosmosCommand</code>命令，使用的参数列表为args，并验证执行该命令的权限情况。
	 * 
	 * @param auth
	 *            执行命令的用户。
	 * @param command
	 *            要执行的命令。
	 * @param args
	 *            参数列表。
	 * @return 返回执行结果。
	 * @throws CosmosExecuteException
	 *             执行命令过程中抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有执行权限时，抛出的异常。
	 */
	public CosmosResult execute(Authorization auth, CosmosCommand command,
			Map<String, Object> args) throws CosmosExecuteException,
			CosmosPermissionException;

}
