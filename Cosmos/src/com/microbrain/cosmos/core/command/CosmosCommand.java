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

import java.util.Collection;
import java.util.Map;

import com.microbrain.cosmos.core.CosmosFactoryException;
import com.microbrain.cosmos.core.auth.Authorization;
import com.microbrain.cosmos.core.constants.CosmosCommandSource;
import com.microbrain.cosmos.core.constants.DebugLevel;
import com.microbrain.cosmos.core.domain.CosmosDomain;
import com.microbrain.cosmos.core.permission.CosmosPermissionException;

/**
 * <p>
 * 命令的封装接口，包含有<code>Command</code> 的名称，<code>Command</code> 的具体内容，
 * <code>Command</code>来源，<code>Command</code>调试级别，<code>Command</code>
 * 的描述性信息，以及<code>Command</code>的所有参数列表等信息。
 * </p>
 * <p>
 * 在这个版本里，将<code>Command</code>的树形结构实现限制为采用左索引和右索引的方式。这样可以确保在装载
 * <code>Command</code>和 构造组合<code>Command</code>树形结构的性能和算法复杂度。
 * </p>
 * <p>
 * 通过调用<code>CosmosCommand</code>的<code>execute()</code>方法来执行一个命令。通常情況下不要通过调用
 * <code>getExecuter()</code>得到<code>CosmosExecuter</code>之后再调用
 * <code>CosmosExecuter</code>的<code>execute()</code>方法来执行。对于组合命令来说，通常是没有
 * <code>CosmosExecuter</code>可以用来执行的。
 * </p>
 * <p>
 * 可以通过将多个命令组合到一起的方法来获得多个执行结果。通常组合指令是一个实现了
 * <code>com.microbrain.cosmos.core.command.composite.CosmosCompositeCommand</code>
 * 接口的命令，框架中内置了一些可用的组合指令，其中有基本的顺序执行和判断执行的组合命令。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.CosmosFactory
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @see com.microbrain.cosmos.core.command.StandardCosmosCommand
 * @see com.microbrain.cosmos.core.command.composite.CosmosCompositeCommand
 * @see com.microbrain.cosmos.core.command.composite.SequenceCosmosCommand
 * @see com.microbrain.cosmos.core.command.composite.IfCosmosCommand
 * @since CFDK 1.0
 */
public interface CosmosCommand {

	/**
	 * 返回这个命令的名称。
	 * 
	 * @return 命令名称。
	 */
	public String getName();

	/**
	 * 获得该命令所属的域。
	 * 
	 * @return 返回该命令所属域。
	 */
	public CosmosDomain getDomain();

	/**
	 * 获得该命令的具体内容。
	 * 
	 * @return 命令具体内容。
	 */
	public String getCommand();

	/**
	 * 获得命令的描述性信息。
	 * 
	 * @return 命令的描述性类<code>CosmosMetaCommand</code>。
	 */
	public CosmosMetaCommand getType();

	/**
	 * 获得命令的执行器。
	 * 
	 * @return 命令相关联的执行器。
	 */
	public CosmosExecuter getExecuter();

	/**
	 * 获得该命令的来源。
	 * 
	 * @return 命令来源，分别为全局<code>GLOBAL</code>或者本地<code>LOCAL</code>。
	 */
	public CosmosCommandSource getSource();

	/**
	 * 添加一个命令的说明性文字。
	 * 
	 * @param remark
	 *            命令的说明性文字。
	 */
	public void setRemark(String remark);

	/**
	 * 获得命令的说明性文字。
	 * 
	 * @return 命令的说明。
	 */
	public String getRemark();

	/**
	 * 设置命令的调试级别，会覆盖掉域的调试级别。如果设置为NO_DEBUG，则和域的调试级别相同。
	 * 
	 * @param debugLevel
	 *            调试级别。
	 */
	public void setDebugLevel(DebugLevel debugLevel);

	/**
	 * 获得命令的调试级别。
	 * 
	 * @return 调试级别。
	 */
	public DebugLevel getDebugLevel();

	/**
	 * 一个命令是否记录某个调试级别的信息。
	 * 
	 * @param debugLevel
	 *            调试级别。
	 * @return 返回该级别的日志是否应当被记录。
	 */
	public boolean isLogEnabled(DebugLevel debugLevel);

	/**
	 * 获得该命令所有的参数列表信息。
	 * 
	 * @return 参数列表。
	 */
	public Collection<CosmosMetaArgument> listMetaArguments();

	/**
	 * 获得命令的左索引，用来构建命令的树形结构时使用。
	 * 
	 * @return 左索引。
	 */
	public Long getLeftIndex();

	/**
	 * 设置命令的左索引。
	 * 
	 * @param leftIndex
	 *            命令的左索引值。
	 */
	public void setLeftIndex(Long leftIndex);

	/**
	 * 获得命令的右索引，用来构建命令的树形结构时使用。
	 * 
	 * @return 右索引。
	 */
	public Long getRightIndex();

	/**
	 * 设置命令的右索引。
	 * 
	 * @param rightIndex
	 *            命令的右索引值。
	 */
	public void setRightIndex(Long rightIndex);

	/**
	 * 用参数args执行这个命令。
	 * 
	 * @param args
	 *            命令参数。
	 * @return 返回执行结果。
	 * @throws CosmosExecuteException
	 *             执行命令抛出的异常。
	 */
	public CosmosResult execute(Map<String, Object> args)
			throws CosmosExecuteException;

	/**
	 * 用参数args执行这个命令，但是要确保该用户有这个权限否则，抛出权限异常
	 * <code>CosmosPermissionException</code>。
	 * 
	 * @param auth
	 *            执行命令的用户认证信息。
	 * @param args
	 *            命令参数。
	 * @return 命令执行结果。
	 * @throws CosmosExecuteException
	 *             执行命令抛出的异常。
	 * @throws CosmosPermissionException
	 *             沒有权限时，抛出该异常。
	 */
	public CosmosResult execute(Authorization auth, Map<String, Object> args)
			throws CosmosExecuteException, CosmosPermissionException;

	/**
	 * 复制本命令，属于浅度复制，不能复制该命令所包含的子命令。
	 * 
	 * @return 返回复制之后的新命令。
	 * @throws CosmosFactoryException
	 *             复制本命令时抛出命令工厂异常。
	 */
	public CosmosCommand duplicate() throws CosmosFactoryException;

}
