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
 * <code>IfCosmosCommand</code>ͨ���жϻ����е�<code>test</code>����ֵ���������Ƿ�Ҫִ�б������µ����������
 * <code>IfCosmosCommand</code>����ִ���Լ����������ݣ�ȡ��ִ�н���е�<code>test</code>����ֵ�������жϡ�
 * </p>
 * <p>
 * <code>IfCosmosCommand</code>�����������У�����ͨ��<code>context</code>
 * ���������ϵͳ�еĸ��ֲ����������Լ�֮ǰ��ִ�н������Ϣ�����ܶ���Щ���ݽ��з��ʡ�
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
	 * �����ж��Ƿ�ִ�еı�������
	 */
	private static final String TEST = "test";

	/**
	 * ��û��������Լ�֮ǰ�����ִ�н���ı�����
	 */
	private static final String CONTEXT = "context";

	/**
	 * �̳еĹ��캯����
	 * 
	 * @param name
	 *            �������ơ�
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ݡ�
	 * @param executer
	 *            ����ִ������
	 * @param metaArgs
	 *            ��������б�
	 * @param type
	 *            �������͡�
	 * @param source
	 *            ������Դ��
	 */
	public IfCosmosCommand(String name, CosmosDomain domain, String command,
			CosmosExecuter executer, Collection<CosmosMetaArgument> metaArgs,
			CosmosMetaCommand type, CosmosCommandSource source) {
		super(name, domain, command, executer, metaArgs, type, source);
	}

	/**
	 * �̳еĹ��캯����
	 * 
	 * @param name
	 *            �������ơ�
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ݡ�
	 * @param executer
	 *            ����ִ�������ơ�
	 * @param metaArgs
	 *            ��������б�
	 * @param type
	 *            �������͡�
	 * @param source
	 *            ������Դ��
	 */
	public IfCosmosCommand(String name, CosmosDomain domain, String command,
			String executer, Collection<CosmosMetaArgument> metaArgs,
			CosmosMetaCommand type, CosmosCommandSource source) {
		super(name, domain, command, executer, metaArgs, type, source);
	}

	/*
	 * ִ��������жϸ��û��Ƿ���Ȩ�ޡ�
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
	 * ִ�����
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
