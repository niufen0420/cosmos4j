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

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.logging.Log;

import com.microbrain.cosmos.core.CosmosFactoryException;
import com.microbrain.cosmos.core.auth.Authorization;
import com.microbrain.cosmos.core.constants.CosmosCommandSource;
import com.microbrain.cosmos.core.constants.DebugLevel;
import com.microbrain.cosmos.core.domain.CosmosDomain;
import com.microbrain.cosmos.core.domain.CosmosDomainException;
import com.microbrain.cosmos.core.log.CosmosLogFactory;
import com.microbrain.cosmos.core.permission.CosmosPermissionException;

/**
 * <p>
 * <code>CosmosCommand</code>命令的一个标准实现。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.CosmosFactory
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @see com.microbrain.cosmos.core.command.composite.CosmosCompositeCommand
 * @see com.microbrain.cosmos.core.command.composite.SequenceCosmosCommand
 * @see com.microbrain.cosmos.core.command.composite.IfCosmosCommand
 * @since CFDK 1.0
 */
public class StandardCosmosCommand implements CosmosCommand {

	/**
	 * 命令名称。
	 */
	protected String name = null;

	/**
	 * 命令所属域。
	 */
	protected CosmosDomain domain = null;

	/**
	 * 命令内容。
	 */
	protected String command = null;

	/**
	 * 命令来源。
	 */
	protected CosmosCommandSource source = null;

	/**
	 * 命令执行器。
	 */
	protected CosmosExecuter executer = null;

	/**
	 * 命令注释。
	 */
	protected String remark = null;

	/**
	 * 命令调试级别。
	 */
	protected DebugLevel debugLevel = DebugLevel.INFO;

	/**
	 * 命令参数列表。
	 */
	protected Collection<CosmosMetaArgument> metaArgs = null;

	/**
	 * 命令说明性类。
	 */
	protected CosmosMetaCommand type = null;

	/**
	 * 命令左索引。
	 */
	protected Long leftIndex = 0l;

	/**
	 * 命令右索引。
	 */
	protected Long rightIndex = 0l;

	/**
	 * 命令日志记录器。
	 */
	private static final Log log = CosmosLogFactory.getLog();

	/**
	 * 命令构造函数，在装载命令时使用。
	 * 
	 * @param name
	 *            命令名称。
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令内容。
	 * @param executer
	 *            命令执行器名称。
	 * @param metaArgs
	 *            命令参数列表。
	 * @param type
	 *            命令类型。
	 * @param source
	 *            命令来源。
	 */
	public StandardCosmosCommand(String name, CosmosDomain domain,
			String command, String executer,
			Collection<CosmosMetaArgument> metaArgs, CosmosMetaCommand type,
			CosmosCommandSource source) {
		this.name = name;
		this.command = command;
		this.domain = domain;

		if (executer != null) {
			try {
				this.executer = this.domain.getExecuter(executer);
			} catch (CosmosDomainException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		this.metaArgs = metaArgs;
		this.type = type;
		this.source = source;
	}

	/**
	 * 命令构造函数，在装载命令时使用。
	 * 
	 * @param name
	 *            命令名称。
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令内容。
	 * @param executer
	 *            命令执行器。
	 * @param metaArgs
	 *            命令参数列表。
	 * @param type
	 *            命令类型。
	 * @param source
	 *            命令来源。
	 */
	public StandardCosmosCommand(String name, CosmosDomain domain,
			String command, CosmosExecuter executer,
			Collection<CosmosMetaArgument> metaArgs, CosmosMetaCommand type,
			CosmosCommandSource source) {
		this.name = name;
		this.command = command;
		this.domain = domain;
		this.executer = executer;

		this.metaArgs = metaArgs;
		this.type = type;
		this.source = source;
	}

	/*
	 * 获得命令的描述信息。
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosCommand#getType()
	 */
	public CosmosMetaCommand getType() {
		return type;
	}

	/*
	 * 获得命令的执行器。
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosCommand#getExecuter()
	 */
	public CosmosExecuter getExecuter() {
		return executer;
	}

	/*
	 * 获得命令来源。
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosCommand#getSource()
	 */
	public CosmosCommandSource getSource() {
		return source;
	}

	/*
	 * 获得命令内容。
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosCommand#getCommand()
	 */
	public String getCommand() {
		return this.command;
	}

	/*
	 * 获得命令所属域。
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosCommand#getDomain()
	 */
	public CosmosDomain getDomain() {
		return this.domain;
	}

	/*
	 * 获得命令名称。
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosCommand#getName()
	 */
	public String getName() {
		return this.name;
	}

	/*
	 * 获得命令注释。
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosCommand#getRemark()
	 */
	public String getRemark() {
		return remark;
	}

	/*
	 * 设置命令注释。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosCommand#setRemark(java.lang.
	 * String)
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/*
	 * 获得命令调试级别。
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosCommand#getDebugLevel()
	 */
	public DebugLevel getDebugLevel() {
		return debugLevel;
	}

	/*
	 * 设置命令调试级别。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosCommand#setDebugLevel(com.microbrain
	 * .cosmos.core.constants.DebugLevel)
	 */
	public void setDebugLevel(DebugLevel debugLevel) {
		this.debugLevel = debugLevel;
	}

	/*
	 * 某个级别的调试日志是否打开。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosCommand#isLogEnabled(com.microbrain
	 * .cosmos.core.constants.DebugLevel)
	 */
	public boolean isLogEnabled(DebugLevel debugLevel) {
		return this.debugLevel.ordinal() <= debugLevel.ordinal();
	}

	/*
	 * 命令参数列表。
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosCommand#listMetaArguments()
	 */
	public Collection<CosmosMetaArgument> listMetaArguments() {
		return metaArgs;
	}

	/*
	 * 获得命令左索引。
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosCommand#getLeftIndex()
	 */
	public Long getLeftIndex() {
		return this.leftIndex;
	}

	/*
	 * 获得命令右索引。
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosCommand#getRightIndex()
	 */
	public Long getRightIndex() {
		return this.rightIndex;
	}

	/*
	 * 设置命令左索引。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosCommand#setLeftIndex(java.lang
	 * .Long)
	 */
	public void setLeftIndex(Long leftIndex) {
		this.leftIndex = leftIndex;
	}

	/*
	 * 设置命令右索引。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosCommand#setRightIndex(java.lang
	 * .Long)
	 */
	public void setRightIndex(Long rightIndex) {
		this.rightIndex = rightIndex;
	}

	/*
	 * 复制命令。
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosCommand#duplicate()
	 */
	public CosmosCommand duplicate() throws CosmosFactoryException {
		Class<CosmosCommand> clazz = type.getClazz();
		CosmosCommand cosmosCommand = null;
		try {
			Constructor<CosmosCommand> constructor = clazz.getConstructor(
					String.class, CosmosDomain.class, String.class,
					CosmosExecuter.class, Collection.class,
					CosmosMetaCommand.class, CosmosCommandSource.class);
			cosmosCommand = constructor.newInstance(name, domain, command,
					executer, metaArgs, type, source);
			cosmosCommand.setRemark(remark);
			cosmosCommand.setDebugLevel(debugLevel);
		} catch (Exception e) {
			throw new CosmosFactoryException(e);
		}

		return cosmosCommand;
	}

	/**
	 * 私有方法，将传入参数中不符合目标参数类型的参数通过Converter进行参数类型转换。
	 * 
	 * @param args
	 *            输入参数映射表。
	 * @return 返回转化类型之后的参数映射表。
	 */
	private Map<String, Object> convertArgs(Map<String, Object> args) {
		for (CosmosMetaArgument metaArg : this.metaArgs) {
			String name = metaArg.getName();
			Object argValue = args.get(name);
			if (argValue instanceof String) {
				CosmosArgumentConverter converter = metaArg.getConverter();
				Object value = converter.convert((String) argValue);
				args.put(name, value);
			}
		}

		return args;
	}

	/*
	 * 执行该命令。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosCommand#execute(java.util.Map)
	 */
	public CosmosResult execute(Map<String, Object> args)
			throws CosmosExecuteException {
		if (isLogEnabled(DebugLevel.DEBUG)
				|| (debugLevel == DebugLevel.NO_DEBUG && log.isDebugEnabled())) {
			log.debug("Prepare to execute this command. name is: " + this.name);
		}

		return executer.execute(this, convertArgs(args));
	}

	/*
	 * 用某个用户的认证信息执行某个命令。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosCommand#execute(com.microbrain
	 * .cosmos.core.auth.Authorization, java.util.Map)
	 */
	public CosmosResult execute(Authorization auth, Map<String, Object> args)
			throws CosmosExecuteException, CosmosPermissionException {
		if (log.isDebugEnabled()) {
			log.debug("Prepare to execute this command. name is: " + this.name);
		}

		return executer.execute(auth, this, convertArgs(args));
	}

}
