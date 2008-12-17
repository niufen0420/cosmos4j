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
 * <code>CosmosCommand</code>�����һ����׼ʵ�֡�
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
	 * �������ơ�
	 */
	protected String name = null;

	/**
	 * ����������
	 */
	protected CosmosDomain domain = null;

	/**
	 * �������ݡ�
	 */
	protected String command = null;

	/**
	 * ������Դ��
	 */
	protected CosmosCommandSource source = null;

	/**
	 * ����ִ������
	 */
	protected CosmosExecuter executer = null;

	/**
	 * ����ע�͡�
	 */
	protected String remark = null;

	/**
	 * ������Լ���
	 */
	protected DebugLevel debugLevel = DebugLevel.INFO;

	/**
	 * ��������б�
	 */
	protected Collection<CosmosMetaArgument> metaArgs = null;

	/**
	 * ����˵�����ࡣ
	 */
	protected CosmosMetaCommand type = null;

	/**
	 * ������������
	 */
	protected Long leftIndex = 0l;

	/**
	 * ������������
	 */
	protected Long rightIndex = 0l;

	/**
	 * ������־��¼����
	 */
	private static final Log log = CosmosLogFactory.getLog();

	/**
	 * ����캯������װ������ʱʹ�á�
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
	 * ����캯������װ������ʱʹ�á�
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
	 * ��������������Ϣ��
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosCommand#getType()
	 */
	public CosmosMetaCommand getType() {
		return type;
	}

	/*
	 * ��������ִ������
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosCommand#getExecuter()
	 */
	public CosmosExecuter getExecuter() {
		return executer;
	}

	/*
	 * ���������Դ��
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosCommand#getSource()
	 */
	public CosmosCommandSource getSource() {
		return source;
	}

	/*
	 * ����������ݡ�
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosCommand#getCommand()
	 */
	public String getCommand() {
		return this.command;
	}

	/*
	 * �������������
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosCommand#getDomain()
	 */
	public CosmosDomain getDomain() {
		return this.domain;
	}

	/*
	 * ����������ơ�
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosCommand#getName()
	 */
	public String getName() {
		return this.name;
	}

	/*
	 * �������ע�͡�
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosCommand#getRemark()
	 */
	public String getRemark() {
		return remark;
	}

	/*
	 * ��������ע�͡�
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosCommand#setRemark(java.lang.
	 * String)
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/*
	 * ���������Լ���
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosCommand#getDebugLevel()
	 */
	public DebugLevel getDebugLevel() {
		return debugLevel;
	}

	/*
	 * ����������Լ���
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosCommand#setDebugLevel(com.microbrain
	 * .cosmos.core.constants.DebugLevel)
	 */
	public void setDebugLevel(DebugLevel debugLevel) {
		this.debugLevel = debugLevel;
	}

	/*
	 * ĳ������ĵ�����־�Ƿ�򿪡�
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosCommand#isLogEnabled(com.microbrain
	 * .cosmos.core.constants.DebugLevel)
	 */
	public boolean isLogEnabled(DebugLevel debugLevel) {
		return this.debugLevel.ordinal() <= debugLevel.ordinal();
	}

	/*
	 * ��������б�
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosCommand#listMetaArguments()
	 */
	public Collection<CosmosMetaArgument> listMetaArguments() {
		return metaArgs;
	}

	/*
	 * ���������������
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosCommand#getLeftIndex()
	 */
	public Long getLeftIndex() {
		return this.leftIndex;
	}

	/*
	 * ���������������
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosCommand#getRightIndex()
	 */
	public Long getRightIndex() {
		return this.rightIndex;
	}

	/*
	 * ����������������
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosCommand#setLeftIndex(java.lang
	 * .Long)
	 */
	public void setLeftIndex(Long leftIndex) {
		this.leftIndex = leftIndex;
	}

	/*
	 * ����������������
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosCommand#setRightIndex(java.lang
	 * .Long)
	 */
	public void setRightIndex(Long rightIndex) {
		this.rightIndex = rightIndex;
	}

	/*
	 * �������
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
	 * ˽�з���������������в�����Ŀ��������͵Ĳ���ͨ��Converter���в�������ת����
	 * 
	 * @param args
	 *            �������ӳ���
	 * @return ����ת������֮��Ĳ���ӳ���
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
	 * ִ�и����
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
	 * ��ĳ���û�����֤��Ϣִ��ĳ�����
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
