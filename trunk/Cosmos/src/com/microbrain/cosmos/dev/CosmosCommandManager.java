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
package com.microbrain.cosmos.dev;

import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.composite.CosmosCompositeCommand;
import com.microbrain.cosmos.core.permission.CosmosPermissionException;

/**
 * <p>
 * 负责进行框架中命令的管理接口，通过本接口，可以为系统添加一个命令，删除一个命令，拼凑一个命令。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.CosmosFactory
 * @see com.microbrain.cosmos.dev.AbstractCosmosCommandManager
 * @since CFDK 1.0
 */
public interface CosmosCommandManager {

	/**
	 * 创建一个命令。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param name
	 *            命令名称。
	 * @param command
	 *            命令内容。
	 * @param executer
	 *            命令执行器名称。
	 * @param remark
	 *            命令说明。
	 * @param type
	 *            命令类型。
	 * @param debugLevel
	 *            命令调试级别。
	 * @throws CosmosCommandManagerException
	 *             创建命令抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	public void create(String domain, String name, String command,
			String executer, String remark, String type, String debugLevel)
			throws CosmosCommandManagerException, CosmosPermissionException;

	/**
	 * 创建一个命令的子命令。
	 * 
	 * @param parent
	 *            父命令。
	 * @param composite
	 *            命令所属的组合命令。
	 * @param domain
	 *            命令所属域。
	 * @param name
	 *            命令名称。
	 * @param command
	 *            命令内容。
	 * @param executer
	 *            命令执行器名称。
	 * @param remark
	 *            命令说明。
	 * @param type
	 *            命令类型。
	 * @param debugLevel
	 *            命令调试级别。
	 * @throws CosmosCommandManagerException
	 *             创建命令抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	public void createSubCommand(CosmosCompositeCommand parent,
			String composite, String domain, String name, String command,
			String executer, String remark, String type, String debugLevel)
			throws CosmosCommandManagerException, CosmosPermissionException;

	/**
	 * 为一个命令创建一个左邻居命令。
	 * 
	 * @param neighbor
	 *            邻居命令。
	 * @param composite
	 *            所属组合命令。
	 * @param domain
	 *            命令所属域。
	 * @param name
	 *            命令名称。
	 * @param command
	 *            命令内容。
	 * @param executer
	 *            命令执行器名称。
	 * @param remark
	 *            命令说明。
	 * @param type
	 *            命令类型。
	 * @param debugLevel
	 *            命令调试级别。
	 * @throws CosmosCommandManagerException
	 *             创建命令抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	public void createLeftNeighbor(CosmosCommand neighbor, String composite,
			String domain, String name, String command, String executer,
			String remark, String type, String debugLevel)
			throws CosmosCommandManagerException, CosmosPermissionException;

	/**
	 * 为一个命令创建一个右邻居命令。
	 * 
	 * @param neighbor
	 *            邻居命令。
	 * @param composite
	 *            所属组合命令。
	 * @param domain
	 *            命令所属域。
	 * @param name
	 *            命令名称。
	 * @param command
	 *            命令内容。
	 * @param executer
	 *            命令执行器名称。
	 * @param remark
	 *            命令说明。
	 * @param type
	 *            命令类型。
	 * @param debugLevel
	 *            命令调试级别。
	 * @throws CosmosCommandManagerException
	 *             创建命令抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	public void createRightNeighbor(CosmosCommand neighbor, String composite,
			String domain, String name, String command, String executer,
			String remark, String type, String debugLevel)
			throws CosmosCommandManagerException, CosmosPermissionException;

	/**
	 * 为一个命令选择一个已经存在的子命令。
	 * 
	 * @param parent
	 *            父命令。
	 * @param composite
	 *            所属组合命令。
	 * @param domain
	 *            命令所属域。
	 * @param name
	 *            命令名称。
	 * @throws CosmosCommandManagerException
	 *             创建命令抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	public void selectSubCommand(CosmosCompositeCommand parent,
			String composite, String domain, String name)
			throws CosmosCommandManagerException, CosmosPermissionException;

	/**
	 * 为一个命令选择一个已经存在的左邻居命令。
	 * 
	 * @param neighbor
	 *            邻居命令。
	 * @param composite
	 *            所属组合命令。
	 * @param domain
	 *            命令所属域。
	 * @param name
	 *            命令名称。
	 * @throws CosmosCommandManagerException
	 *             创建命令抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	public void selectLeftNeighbor(CosmosCommand neighbor, String composite,
			String domain, String name) throws CosmosCommandManagerException,
			CosmosPermissionException;

	/**
	 * 为一个命令选择一个已经存在的右邻居命令。
	 * 
	 * @param neighbor
	 *            邻居命令。
	 * @param composite
	 *            所属组合命令。
	 * @param domain
	 *            命令所属域。
	 * @param name
	 *            命令名称。
	 * @throws CosmosCommandManagerException
	 *             创建命令抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	public void selectRightNeighbor(CosmosCommand neighbor, String composite,
			String domain, String name) throws CosmosCommandManagerException,
			CosmosPermissionException;

	/**
	 * 更新一个命令的信息。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param name
	 *            命令名称。
	 * @param command
	 *            命令内容。
	 * @param executer
	 *            命令执行器名称。
	 * @param remark
	 *            命令说明。
	 * @param type
	 *            命令类型。
	 * @param debugLevel
	 *            命令调试级别。
	 * @throws CosmosCommandManagerException
	 *             创建命令抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	public void save(String domain, String name, String command,
			String executer, String remark, String type, String debugLevel)
			throws CosmosCommandManagerException, CosmosPermissionException;

	/**
	 * 获得一个命令。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param name
	 *            命令名称。
	 * @return 返回这个命令。
	 * @throws CosmosCommandManagerException
	 *             创建命令抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	public CosmosCommand get(String domain, String name)
			throws CosmosCommandManagerException, CosmosPermissionException;

	/**
	 * 删除一个命令。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param name
	 *            命令名称。
	 * @param composite
	 *            命令所属组合。
	 * @param leftIndex
	 *            命令的左序号。
	 * @throws CosmosCommandManagerException
	 *             创建命令抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	public void remove(String domain, String name, String composite,
			Integer leftIndex) throws CosmosCommandManagerException,
			CosmosPermissionException;

	/**
	 * 创建一个命令的参数。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @param name
	 *            参数名称。
	 * @param inOutType
	 *            参数IN/OUT类型。
	 * @param converter
	 *            参数类型转换器名称。
	 * @param remark
	 *            参数说明。
	 * @throws CosmosCommandManagerException
	 *             创建命令抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	public void createArg(String domain, String command, String name,
			String inOutType, String converter, String remark)
			throws CosmosCommandManagerException, CosmosPermissionException;

	/**
	 * 更新一个参数的信息。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @param index
	 *            参数序号。
	 * @param name
	 *            参数名称。
	 * @param inOutType
	 *            参数IN/OUT类型。
	 * @param converter
	 *            参数类型转换器名称。
	 * @param remark
	 *            参数说明。
	 * @throws CosmosCommandManagerException
	 *             创建命令抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	public void saveArg(String domain, String command, int index, String name,
			String inOutType, String converter, String remark)
			throws CosmosCommandManagerException, CosmosPermissionException;

	/**
	 * 删除一个参数。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @param index
	 *            待删除参数的序号。
	 * @throws CosmosCommandManagerException
	 *             创建命令抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	public void removeArg(String domain, String command, int index)
			throws CosmosCommandManagerException, CosmosPermissionException;

	/**
	 * 在一个参数之前插入一个参数。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @param index
	 *            要插入的参数序号。
	 * @param name
	 *            参数命令。
	 * @param inOutType
	 *            参数IN/OUT类型。
	 * @param converter
	 *            参数类型转换器名称。
	 * @param remark
	 *            参数说明。
	 * @throws CosmosCommandManagerException
	 *             创建命令抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	public void insertArgBefore(String domain, String command, int index,
			String name, String inOutType, String converter, String remark)
			throws CosmosCommandManagerException, CosmosPermissionException;

	/**
	 * 在一个参数之后插入一个参数。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @param index
	 *            要插入的参数序号。
	 * @param name
	 *            参数命令。
	 * @param inOutType
	 *            参数IN/OUT类型。
	 * @param converter
	 *            参数类型转换器名称。
	 * @param remark
	 *            参数说明。
	 * @throws CosmosCommandManagerException
	 *             创建命令抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	public void insertArgAfter(String domain, String command, int index,
			String name, String inOutType, String converter, String remark)
			throws CosmosCommandManagerException, CosmosPermissionException;

}
