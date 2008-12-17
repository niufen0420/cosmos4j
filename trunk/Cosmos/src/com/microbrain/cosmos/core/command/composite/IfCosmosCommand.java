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

import java.util.Collection;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.microbrain.cosmos.core.auth.Authorization;
import com.microbrain.cosmos.core.command.CosmosExecuteException;
import com.microbrain.cosmos.core.command.CosmosExecuter;
import com.microbrain.cosmos.core.command.CosmosMetaArgument;
import com.microbrain.cosmos.core.command.CosmosMetaCommand;
import com.microbrain.cosmos.core.command.CosmosResult;
import com.microbrain.cosmos.core.command.StandardCosmosResult;
import com.microbrain.cosmos.core.constants.CosmosCommandSource;
import com.microbrain.cosmos.core.domain.CosmosDomain;
import com.microbrain.cosmos.core.permission.CosmosPermissionException;

/**
 * <p>
 * <code>IfCosmosCommand</code>通过判断环境中的<code>test</code>变量值，来决定是否要执行本命令下的所有子命令。
 * <code>IfCosmosCommand</code>首先执行自己的命令内容，取出执行结果中的<code>test</code>变量值，进行判断。
 * </p>
 * <p>
 * <code>IfCosmosCommand</code>的命令内容中，可以通过<code>context</code>
 * 变量来获得系统中的各种参数变量，以及之前的执行结果等信息，并能对这些数据进行访问。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @see com.microbrain.cosmos.core.command.StandardCosmosCommand
 * @see com.microbrain.cosmos.core.command.composite.CosmosCompositeCommand
 * @see com.microbrain.cosmos.core.command.composite.SequenceCosmosCommand
 * @since CFDK 1.0
 */
public class IfCosmosCommand extends SequenceCosmosCommand {

	/**
	 * 用来判断是否执行的变量名。
	 */
	private static final String TEST = "test";

	/**
	 * 获得环境参数以及之前命令的执行结果的变量。
	 */
	private static final String CONTEXT = "context";

	/**
	 * 继承的构造函数。
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
	public IfCosmosCommand(String name, CosmosDomain domain, String command,
			CosmosExecuter executer, Collection<CosmosMetaArgument> metaArgs,
			CosmosMetaCommand type, CosmosCommandSource source) {
		super(name, domain, command, executer, metaArgs, type, source);
	}

	/**
	 * 继承的构造函数。
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
	public IfCosmosCommand(String name, CosmosDomain domain, String command,
			String executer, Collection<CosmosMetaArgument> metaArgs,
			CosmosMetaCommand type, CosmosCommandSource source) {
		super(name, domain, command, executer, metaArgs, type, source);
	}

	/*
	 * 执行命令，并判断该用户是否有权限。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.composite.SequenceCosmosCommand#execute
	 * (com.microbrain.cosmos.core.auth.Authorization, java.util.Map)
	 */
	@Override
	public CosmosResult execute(Authorization auth, Map<String, Object> args)
			throws CosmosExecuteException, CosmosPermissionException {
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("bsh");
		engine.put(CONTEXT, args);

		try {
			engine.eval(command);
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Boolean test = (Boolean) engine.get(TEST);
		if (test) {
			return super.execute(auth, args);
		}

		return new StandardCosmosResult(this);
	}

	/*
	 * 执行命令。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.composite.SequenceCosmosCommand#execute
	 * (java.util.Map)
	 */
	@Override
	public CosmosResult execute(Map<String, Object> args)
			throws CosmosExecuteException {
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("bsh");
		engine.put(CONTEXT, args);

		try {
			engine.eval(command);
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Boolean test = (Boolean) engine.get(TEST);
		if (test) {
			return super.execute(args);
		}

		return new StandardCosmosResult(this);
	}

}
