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

import com.microbrain.cosmos.core.command.CosmosResult;
import com.microbrain.cosmos.core.permission.CosmosPermissionException;
import com.microbrain.cosmos.core.sal.ServiceException;
import com.microbrain.cosmos.core.sal.annotation.Command;

/**
 * <p>
 * 负责进行框架中命令的服务接口，通过本接口，可以利用系统已经有的服务为系统添加一个命令，删除一个命令，拼凑一个命令。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.CosmosFactory
 * @see com.microbrain.cosmos.dev.AbstractCosmosCommandManager
 * @since CFDK 1.0
 */
public interface CosmosCommandService {

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
	 * @return 命令执行结果。
	 * @throws ServiceException
	 *             调用某个服务抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	@Command("createCommand")
	public CosmosResult create(String domain, String name, String command,
			String executer, String remark, String type, String debugLevel)
			throws ServiceException, CosmosPermissionException;

	/**
	 * 创建一个命令的子命令。
	 * 
	 * @param parentDomain
	 *            父命令所属域。
	 * @param parentName
	 *            父命令名称。
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
	 * @return 命令执行结果。
	 * @throws ServiceException
	 *             调用某个服务抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	@Command("createSubCommand")
	public CosmosResult createSubCommand(String parentDomain,
			String parentName, String composite, String domain, String name,
			String command, String executer, String remark, String type,
			String debugLevel) throws ServiceException,
			CosmosPermissionException;

	/**
	 * 为一个命令创建一个左邻居命令。
	 * 
	 * @param neighborDomain
	 *            邻居命令所属域。
	 * @param neighborName
	 *            邻居命令名称。
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
	 * @return 命令执行结果。
	 * @throws ServiceException
	 *             调用某个服务抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	@Command("createLeftNeighbor")
	public CosmosResult createLeftNeighbor(String neighborDomain,
			String neighborName, String composite, String domain, String name,
			String command, String executer, String remark, String type,
			String debugLevel) throws ServiceException,
			CosmosPermissionException;

	/**
	 * 为一个命令创建一个右邻居命令。
	 * 
	 * @param neighborDomain
	 *            邻居命令所属域。
	 * @param neighborName
	 *            邻居命令名称。
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
	 * @return 命令执行结果。
	 * @throws ServiceException
	 *             调用某个服务抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	@Command("createRightNeighbor")
	public CosmosResult createRightNeighbor(String neighborDomain,
			String neighborName, String composite, String domain, String name,
			String command, String executer, String remark, String type,
			String debugLevel) throws ServiceException,
			CosmosPermissionException;

	/**
	 * 为一个命令选择一个已经存在的子命令。
	 * 
	 * @param parentDomain
	 *            所属域。
	 * @param parentName
	 *            父命令名称。
	 * @param composite
	 *            所属组合命令。
	 * @param domain
	 *            命令所属域。
	 * @param name
	 *            命令名称。
	 * @return 命令执行结果。
	 * @throws ServiceException
	 *             调用某个服务抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	@Command("selectSubCommand")
	public CosmosResult selectSubCommand(String parentDomain,
			String parentName, String composite, String domain, String name)
			throws ServiceException, CosmosPermissionException;

	/**
	 * 为一个命令选择一个已经存在的左邻居命令。
	 * 
	 * @param neighborDomain
	 *            邻居命令所属域。
	 * @param neighborName
	 *            邻居命令名称。
	 * @param composite
	 *            所属组合命令。
	 * @param domain
	 *            命令所属域。
	 * @param name
	 *            命令名称。
	 * @return 命令执行结果。
	 * @throws ServiceException
	 *             调用某个服务抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	@Command("selectLeftNeighbor")
	public CosmosResult selectLeftNeighbor(String neighborDomain,
			String neighborName, String composite, String domain, String name)
			throws ServiceException, CosmosPermissionException;

	/**
	 * 为一个命令选择一个已经存在的右邻居命令。
	 * 
	 * @param neighborDomain
	 *            邻居命令所属域。
	 * @param neighborName
	 *            邻居命令名称。
	 * @param composite
	 *            所属组合命令。
	 * @param domain
	 *            命令所属域。
	 * @param name
	 *            命令名称。
	 * @return 命令执行结果。
	 * @throws ServiceException
	 *             调用某个服务抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	@Command("selectRightNeighbor")
	public CosmosResult selectRightNeighbor(String neighborDomain,
			String neighborName, String composite, String domain, String name)
			throws ServiceException, CosmosPermissionException;

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
	 * @return 命令执行结果。
	 * @throws ServiceException
	 *             调用某个服务抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	@Command("save")
	public CosmosResult save(String domain, String name, String command,
			String executer, String remark, String type, String debugLevel)
			throws ServiceException, CosmosPermissionException;

	/**
	 * 获得一个命令。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param name
	 *            命令名称。
	 * @return 命令执行结果。
	 * @throws ServiceException
	 *             调用某个服务抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	@Command("get")
	public CosmosResult get(String domain, String name)
			throws ServiceException, CosmosPermissionException;

	/**
	 * 删除一个命令。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param name
	 *            命令名称。
	 * @param composite
	 *            所属组合命令。
	 * @param leftIndex
	 *            命令的左序号。
	 * @return 命令执行结果。
	 * @throws ServiceException
	 *             调用某个服务抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	@Command("remove")
	public CosmosResult remove(String domain, String name, String composite,
			Integer leftIndex) throws ServiceException,
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
	 * @return 命令执行结果。
	 * @throws ServiceException
	 *             调用某个服务抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	@Command("createArg")
	public CosmosResult createArg(String domain, String command, String name,
			String inOutType, String converter, String remark)
			throws ServiceException, CosmosPermissionException;

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
	 * @return 命令执行结果。
	 * @throws ServiceException
	 *             调用某个服务抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	@Command("saveArg")
	public CosmosResult saveArg(String domain, String command, int index,
			String name, String inOutType, String converter, String remark)
			throws ServiceException, CosmosPermissionException;

	/**
	 * 删除一个参数。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @param index
	 *            参数序号。
	 * @return 命令执行结果。
	 * @throws ServiceException
	 *             调用某个服务抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	@Command("removeArg")
	public CosmosResult removeArg(String domain, String command, int index)
			throws ServiceException, CosmosPermissionException;

	/**
	 * 在一个参数之前插入一个参数。
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
	 * @return 命令执行结果。
	 * @throws ServiceException
	 *             调用某个服务抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	@Command("insertArgBefore")
	public CosmosResult insertArgBefore(String domain, String command,
			int index, String name, String inOutType, String converter,
			String remark) throws ServiceException, CosmosPermissionException;

	/**
	 * 在一个参数之后插入一个参数。
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
	 * @return 命令执行结果。
	 * @throws ServiceException
	 *             调用某个服务抛出的异常。
	 * @throws CosmosPermissionException
	 *             没有权限时抛出的异常。
	 */
	@Command("insertArgAfter")
	public CosmosResult insertArgAfter(String domain, String command,
			int index, String name, String inOutType, String converter,
			String remark) throws ServiceException, CosmosPermissionException;

}
