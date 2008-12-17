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
 * <code>CosmosExecuter</code>��cosmos��ܵ�ִ�������󣬹����Ǹ������<code>CosmosCommand</code>
 * ��ִ�й���������ִ�н����װ��<code>CosmosResult</code>���ͣ��Թ�������ʹ�á�
 * </p>
 * <p>
 * ����ÿһ��<code>CosmosExecuter</code>������Ӧ�������е�domain������<code>CosmosExecuter</code>
 * ��<code>category</code>������<code>CosmosDomain</code>��<code>category</code>
 * ���ԵĶ�Ӧ��ϵ�������ж�һ��domain���ж����ֿ��õ�executer��
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
	 * ���<code>CosmosExecuter</code>�����ơ�
	 * 
	 * @return ����<code>CosmosExecuter</code>�����ơ�
	 */
	public String getName();

	/**
	 * ���<code>CosmosExecuter</code>�ı�ǩ��
	 * 
	 * @return ����<code>CosmosExecuter</code>�ı�ǩ��
	 */
	public String getLabel();

	/**
	 * ���<code>CosmosExecuter</code>�ķ��ࡣ
	 * 
	 * @return ����<code>CosmosExecuter</code>�ķ��ࡣ
	 */
	public String getCategory();

	/**
	 * ���<code>CosmosExecuter</code>��������
	 * 
	 * @return ����<code>CosmosExecuter</code>��������
	 */
	public String getDescription();

	/**
	 * ��ʼ��<code>CosmosExecuter</code>��
	 * 
	 * @param factory
	 *            ������ʵ����
	 * @param config
	 *            ������ʵ����
	 */
	public void init(CosmosFactory factory, Configuration config);

	/**
	 * ִ��һ��<code>CosmosCommand</code>���ʹ�õĲ����б�Ϊargs��
	 * 
	 * @param command
	 *            Ҫִ�е����
	 * @param args
	 *            �����б�
	 * @return ����ִ�н����
	 * @throws CosmosExecuteException
	 *             ִ������������׳����쳣��
	 */
	public CosmosResult execute(CosmosCommand command, Map<String, Object> args)
			throws CosmosExecuteException;

	/**
	 * ִ��һ��<code>CosmosCommand</code>���ʹ�õĲ����б�Ϊargs������ִ֤�и������Ȩ�������
	 * 
	 * @param auth
	 *            ִ��������û���
	 * @param command
	 *            Ҫִ�е����
	 * @param args
	 *            �����б�
	 * @return ����ִ�н����
	 * @throws CosmosExecuteException
	 *             ִ������������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��ִ��Ȩ��ʱ���׳����쳣��
	 */
	public CosmosResult execute(Authorization auth, CosmosCommand command,
			Map<String, Object> args) throws CosmosExecuteException,
			CosmosPermissionException;

}
