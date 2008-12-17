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

import java.util.Map;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.CosmosFactoryException;
import com.microbrain.cosmos.core.command.CosmosArgumentConverter;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.CosmosMetaCommand;
import com.microbrain.cosmos.core.command.CosmosResult;
import com.microbrain.cosmos.core.command.composite.CosmosCompositeCommand;
import com.microbrain.cosmos.core.config.ConfigurationException;
import com.microbrain.cosmos.core.domain.CosmosDomain;
import com.microbrain.cosmos.core.permission.CosmosPermissionException;
import com.microbrain.cosmos.core.sal.ServiceException;

/**
 * <p>
 * <code>com.microbrain.cosmos.dev.CosmosCommandManager</code>的标准实现，通过调用
 * <code>com.microbrain.cosmos.dev.CosmosCommandService</code>接口来实现管理命令及其参数的目的。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.CosmosFactory
 * @see com.microbrain.cosmos.dev.CosmosCommandManager
 * @see com.microbrain.cosmos.dev.AbstractCosmosCommandManager
 * @since CFDK 1.0
 */
public class StandardCosmosCommandManager extends AbstractCosmosCommandManager {

	/**
	 * 服务接口引用。
	 */
	private CosmosCommandService service = null;

	/**
	 * 所有域映射。
	 */
	private Map<String, CosmosDomain> domains = null;

	/**
	 * 所有命令类型。
	 */
	private Map<String, CosmosMetaCommand> types = null;

	/**
	 * 构造函数。
	 * 
	 * @param factory
	 *            Cosmos工厂类实例。
	 */
	public StandardCosmosCommandManager(CosmosFactory factory) {
		super(factory);

		try {
			this.domains = config.getDomains();
			this.types = config.getCommandTypes();
			service = factory.getService(CosmosCommandService.class);
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 创建一个命令。
	 * 
	 * @see
	 * com.microbrain.cosmos.dev.CosmosCommandManager#create(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public void create(String domain, String name, String command,
			String executer, String remark, String type, String debugLevel)
			throws CosmosCommandManagerException, CosmosPermissionException {
		if (!domains.containsKey(domain)) {
			throw new CosmosCommandManagerException();
		}

		if (!types.containsKey(type)) {
			throw new CosmosCommandManagerException();
		}

		try {
			service.create(domain, name, command, executer, remark, type,
					debugLevel);
		} catch (ServiceException e) {
			throw new CosmosCommandManagerException(e);
		}
	}

	/*
	 * 创建一个命令的左邻居。
	 * 
	 * @see
	 * com.microbrain.cosmos.dev.CosmosCommandManager#createLeftNeighbor(com
	 * .microbrain.cosmos.core.command.CosmosCommand, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public void createLeftNeighbor(CosmosCommand neighbor, String composite,
			String domain, String name, String command, String executer,
			String remark, String type, String debugLevel)
			throws CosmosCommandManagerException, CosmosPermissionException {
		if (!domains.containsKey(domain)) {
			throw new CosmosCommandManagerException();
		}

		if (!types.containsKey(type)) {
			throw new CosmosCommandManagerException();
		}

		try {
			service.createLeftNeighbor(neighbor.getDomain().getName(), neighbor
					.getName(), composite, domain, name, command, executer,
					remark, type, debugLevel);
		} catch (ServiceException e) {
			throw new CosmosCommandManagerException(e);
		}
	}

	/*
	 * 创建一个命令的右邻居。
	 * 
	 * @see
	 * com.microbrain.cosmos.dev.CosmosCommandManager#createRightNeighbor(com
	 * .microbrain.cosmos.core.command.CosmosCommand, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public void createRightNeighbor(CosmosCommand neighbor, String composite,
			String domain, String name, String command, String executer,
			String remark, String type, String debugLevel)
			throws CosmosCommandManagerException, CosmosPermissionException {
		if (!domains.containsKey(domain)) {
			throw new CosmosCommandManagerException();
		}

		if (!types.containsKey(type)) {
			throw new CosmosCommandManagerException();
		}

		try {
			service.createRightNeighbor(neighbor.getDomain().getName(),
					neighbor.getName(), composite, domain, name, command,
					executer, remark, type, debugLevel);
		} catch (ServiceException e) {
			throw new CosmosCommandManagerException(e);
		}
	}

	/*
	 * 创建一个命令的子命令。
	 * 
	 * @seecom.microbrain.cosmos.dev.CosmosCommandManager#createSubCommand(com.
	 * microbrain.cosmos.core.command.composite.CosmosCompositeCommand,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void createSubCommand(CosmosCompositeCommand parent,
			String composite, String domain, String name, String command,
			String executer, String remark, String type, String debugLevel)
			throws CosmosCommandManagerException, CosmosPermissionException {
		if (!domains.containsKey(domain)) {
			throw new CosmosCommandManagerException();
		}

		if (!types.containsKey(type)) {
			throw new CosmosCommandManagerException();
		}

		try {
			service.createSubCommand(parent.getDomain().getName(), parent
					.getName(), composite, domain, name, command, executer,
					remark, type, debugLevel);
		} catch (ServiceException e) {
			throw new CosmosCommandManagerException(e);
		}
	}

	/*
	 * 选择一个命令的子命令。
	 * 
	 * @seecom.microbrain.cosmos.dev.CosmosCommandManager#selectSubCommand(com.
	 * microbrain.cosmos.core.command.composite.CosmosCompositeCommand,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public void selectSubCommand(CosmosCompositeCommand parent,
			String composite, String domain, String name)
			throws CosmosCommandManagerException, CosmosPermissionException {
		if (!domains.containsKey(domain)) {
			throw new CosmosCommandManagerException();
		}

		try {
			service.selectSubCommand(parent.getDomain().getName(), parent
					.getName(), composite, domain, name);
		} catch (ServiceException e) {
			throw new CosmosCommandManagerException(e);
		}
	}

	/*
	 * 选择一个命令的左邻居。
	 * 
	 * @see
	 * com.microbrain.cosmos.dev.CosmosCommandManager#selectLeftNeighbor(com
	 * .microbrain.cosmos.core.command.CosmosCommand, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public void selectLeftNeighbor(CosmosCommand neighbor, String composite,
			String domain, String name) throws CosmosCommandManagerException,
			CosmosPermissionException {
		if (!domains.containsKey(domain)) {
			throw new CosmosCommandManagerException();
		}

		try {
			service.selectLeftNeighbor(neighbor.getDomain().getName(), neighbor
					.getName(), composite, domain, name);
		} catch (ServiceException e) {
			throw new CosmosCommandManagerException(e);
		}
	}

	/*
	 * 选择一个命令的右邻居。
	 * 
	 * @see
	 * com.microbrain.cosmos.dev.CosmosCommandManager#selectRightNeighbor(com
	 * .microbrain.cosmos.core.command.CosmosCommand, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public void selectRightNeighbor(CosmosCommand neighbor, String composite,
			String domain, String name) throws CosmosCommandManagerException,
			CosmosPermissionException {
		if (!domains.containsKey(domain)) {
			throw new CosmosCommandManagerException();
		}

		try {
			service.selectRightNeighbor(neighbor.getDomain().getName(),
					neighbor.getName(), composite, domain, name);
		} catch (ServiceException e) {
			throw new CosmosCommandManagerException(e);
		}
	}

	/*
	 * 获得一个命令。
	 * 
	 * @see com.microbrain.cosmos.dev.CosmosCommandManager#get(java.lang.String,
	 * java.lang.String)
	 */
	public CosmosCommand get(String domain, String name)
			throws CosmosCommandManagerException, CosmosPermissionException {
		CosmosResult result = null;
		try {
			result = service.get(domain, name);
		} catch (ServiceException e) {
			throw new CosmosCommandManagerException(e);
		}

		if (result != null) {
			return null;
		}

		return null;
	}

	/*
	 * 删除一个命令。
	 * 
	 * @see
	 * com.microbrain.cosmos.dev.CosmosCommandManager#remove(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.Integer)
	 */
	public void remove(String domain, String name, String composite,
			Integer leftIndex) throws CosmosCommandManagerException,
			CosmosPermissionException {
		try {
			service.remove(domain, name, composite, leftIndex);
		} catch (ServiceException e) {
			throw new CosmosCommandManagerException(e);
		}
	}

	/*
	 * 更新一个命令的信息。
	 * 
	 * @see
	 * com.microbrain.cosmos.dev.CosmosCommandManager#save(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public void save(String domain, String name, String command,
			String executer, String remark, String type, String debugLevel)
			throws CosmosCommandManagerException, CosmosPermissionException {
		if (!domains.containsKey(domain)) {
			throw new CosmosCommandManagerException();
		}

		if (!types.containsKey(type)) {
			throw new CosmosCommandManagerException();
		}

		try {
			if (factory.getCommand(domain, name) == null) {
				throw new CosmosCommandManagerException("no this command.");
			}
		} catch (CosmosFactoryException e) {
			throw new CosmosCommandManagerException(e);
		}

		try {
			service.save(domain, name, command, executer, remark, type,
					debugLevel);
		} catch (ServiceException e) {
			throw new CosmosCommandManagerException(e);
		}
	}

	/*
	 * 创建一个命令的参数。
	 * 
	 * @see
	 * com.microbrain.cosmos.dev.CosmosCommandManager#createArg(java.lang.String
	 * , java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public void createArg(String domain, String command, String name,
			String inOutType, String converter, String remark)
			throws CosmosCommandManagerException, CosmosPermissionException {
		if (!domains.containsKey(domain)) {
			throw new CosmosCommandManagerException();
		}

		Map<Object, CosmosArgumentConverter> converters = null;
		try {
			converters = config.getConvertersMap();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		if (!converters.containsKey(converter)) {
			throw new CosmosCommandManagerException();
		}

		try {
			service.createArg(domain, command, name, inOutType, converter,
					remark);
		} catch (ServiceException e) {
			throw new CosmosCommandManagerException(e);
		}
	}

	/*
	 * 更新一个参数的信息。
	 * 
	 * @see
	 * com.microbrain.cosmos.dev.CosmosCommandManager#saveArg(java.lang.String,
	 * java.lang.String, int, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public void saveArg(String domain, String command, int index, String name,
			String inOutType, String converter, String remark)
			throws CosmosCommandManagerException, CosmosPermissionException {
		if (!domains.containsKey(domain)) {
			throw new CosmosCommandManagerException();
		}

		Map<Object, CosmosArgumentConverter> converters = null;
		try {
			converters = config.getConvertersMap();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		if (!converters.containsKey(converter)) {
			throw new CosmosCommandManagerException();
		}

		try {
			service.saveArg(domain, command, index, name, inOutType, converter,
					remark);
		} catch (ServiceException e) {
			throw new CosmosCommandManagerException(e);
		}
	}

	/*
	 * 删除一个参数。
	 * 
	 * @see
	 * com.microbrain.cosmos.dev.CosmosCommandManager#removeArg(java.lang.String
	 * , java.lang.String, int)
	 */
	public void removeArg(String domain, String command, int index)
			throws CosmosCommandManagerException, CosmosPermissionException {
		if (!domains.containsKey(domain)) {
			throw new CosmosCommandManagerException();
		}

		try {
			service.removeArg(domain, command, index);
		} catch (ServiceException e) {
			throw new CosmosCommandManagerException(e);
		}
	}

	/*
	 * 在一个参数之后插入一个参数。
	 * 
	 * @see
	 * com.microbrain.cosmos.dev.CosmosCommandManager#insertArgAfter(java.lang
	 * .String, java.lang.String, int, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public void insertArgAfter(String domain, String command, int index,
			String name, String inOutType, String converter, String remark)
			throws CosmosCommandManagerException, CosmosPermissionException {
		if (!domains.containsKey(domain)) {
			throw new CosmosCommandManagerException();
		}

		Map<Object, CosmosArgumentConverter> converters = null;
		try {
			converters = config.getConvertersMap();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		if (!converters.containsKey(converter)) {
			throw new CosmosCommandManagerException();
		}

		try {
			service.insertArgAfter(domain, command, index, name, inOutType,
					converter, remark);
		} catch (ServiceException e) {
			throw new CosmosCommandManagerException(e);
		}
	}

	/*
	 * 在一个参数之前插入一个参数。
	 * 
	 * @see
	 * com.microbrain.cosmos.dev.CosmosCommandManager#insertArgBefore(java.lang
	 * .String, java.lang.String, int, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public void insertArgBefore(String domain, String command, int index,
			String name, String inOutType, String converter, String remark)
			throws CosmosCommandManagerException, CosmosPermissionException {
		if (!domains.containsKey(domain)) {
			throw new CosmosCommandManagerException();
		}

		Map<Object, CosmosArgumentConverter> converters = null;
		try {
			converters = config.getConvertersMap();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		if (!converters.containsKey(converter)) {
			throw new CosmosCommandManagerException();
		}

		try {
			service.insertArgBefore(domain, command, index, name, inOutType,
					converter, remark);
		} catch (ServiceException e) {
			throw new CosmosCommandManagerException(e);
		}
	}

}
