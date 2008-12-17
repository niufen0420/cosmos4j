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
package com.microbrain.cosmos.core.command.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.microbrain.cosmos.core.auth.Authorization;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.CosmosExecuteException;
import com.microbrain.cosmos.core.command.CosmosExecuter;
import com.microbrain.cosmos.core.command.CosmosMetaArgument;
import com.microbrain.cosmos.core.command.CosmosMetaCommand;
import com.microbrain.cosmos.core.command.CosmosResult;
import com.microbrain.cosmos.core.command.StandardCosmosCommand;
import com.microbrain.cosmos.core.command.StandardCosmosResult;
import com.microbrain.cosmos.core.constants.CosmosCommandSource;
import com.microbrain.cosmos.core.domain.CosmosDomain;
import com.microbrain.cosmos.core.permission.CosmosPermissionException;

/**
 * <p>
 * <code>SequenceCosmosCommand</code>是组合命令的一个默认实现，其他的实现都可以继承自
 * <code>SequenceCosmosCommand</code>。<code>SequenceCosmosCommand</code>
 * 实现了顺序执行其所有子命令的<code>execute</code>方法，并将所有子命令的执行结果进行合并。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @see com.microbrain.cosmos.core.command.StandardCosmosCommand
 * @see com.microbrain.cosmos.core.command.composite.CosmosCompositeCommand
 * @see com.microbrain.cosmos.core.command.composite.IfCosmosCommand
 * @since CFDK 1.0
 */
public class SequenceCosmosCommand extends StandardCosmosCommand implements
		CosmosCompositeCommand {

	/**
	 * 所有子命令列表。
	 */
	protected Collection<CosmosCommand> commands = new ArrayList<CosmosCommand>();

	/**
	 * 继承自父类<code>StandardCosmosCommand</code>的构造函数。
	 * 
	 * @param name
	 *            命令名称。
	 * @param domain
	 *            所属域。
	 * @param command
	 *            命令内容。
	 * @param executer
	 *            执行器。
	 * @param metaArgs
	 *            参数列表。
	 * @param type
	 *            命令类型。
	 * @param source
	 *            命令来源。
	 */
	public SequenceCosmosCommand(String name, CosmosDomain domain,
			String command, CosmosExecuter executer,
			Collection<CosmosMetaArgument> metaArgs, CosmosMetaCommand type,
			CosmosCommandSource source) {
		super(name, domain, command, executer, metaArgs, type, source);
	}

	/**
	 * 继承自父类<code>StandardCosmosCommand</code>的构造函数。
	 * 
	 * @param name
	 *            命令名称。
	 * @param domain
	 *            所属域。
	 * @param command
	 *            命令内容。
	 * @param executer
	 *            执行器名称。
	 * @param metaArgs
	 *            参数列表。
	 * @param type
	 *            命令类型。
	 * @param source
	 *            命令来源。
	 */
	public SequenceCosmosCommand(String name, CosmosDomain domain,
			String command, String executer,
			Collection<CosmosMetaArgument> metaArgs, CosmosMetaCommand type,
			CosmosCommandSource source) {
		super(name, domain, command, executer, metaArgs, type, source);
	}

	/*
	 * 添加一个子命令。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.composite.CosmosCompositeCommand#add
	 * (com.microbrain.cosmos.core.command.CosmosCommand)
	 */
	public boolean add(CosmosCommand command) {
		return commands.add(command);
	}

	/*
	 * 所有子命令列表。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.composite.CosmosCompositeCommand#commands
	 * ()
	 */
	public Collection<CosmosCommand> commands() {
		return commands;
	}

	/*
	 * 删除一个子命令。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.composite.CosmosCompositeCommand#remove
	 * (com.microbrain.cosmos.core.command.CosmosCommand)
	 */
	public boolean remove(CosmosCommand command) {
		return commands.remove(command);
	}

	/*
	 * 顺序执行所有子命令，并将结果合并。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.StandardCosmosCommand#execute(java
	 * .util.Map)
	 */
	@Override
	public CosmosResult execute(Map<String, Object> args)
			throws CosmosExecuteException {
		CosmosResult totalResult = new StandardCosmosResult(this), result = null;
		for (CosmosCommand command : commands) {
			result = command.execute(args);

			if (result != null) {
				String domain = command.getDomain().getName();
				String name = command.getName();

				args.put(domain + "." + name, result);
				totalResult.combine(result);
			}
		}

		return totalResult;
	}

	/*
	 * 以auth身份顺序执行所有子命令，并将结果合并。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.StandardCosmosCommand#execute(com.
	 * microbrain.cosmos.core.auth.Authorization, java.util.Map)
	 */
	@Override
	public CosmosResult execute(Authorization auth, Map<String, Object> args)
			throws CosmosExecuteException, CosmosPermissionException {
		CosmosResult totalResult = new StandardCosmosResult(this), result = null;
		for (CosmosCommand command : commands) {
			result = command.execute(auth, args);

			args.put(command.getName(), result);
			totalResult.combine(result);
		}

		return totalResult;
	}

}
