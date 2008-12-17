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
 * ����ķ�װ�ӿڣ�������<code>Command</code> �����ƣ�<code>Command</code> �ľ������ݣ�
 * <code>Command</code>��Դ��<code>Command</code>���Լ���<code>Command</code>
 * ����������Ϣ���Լ�<code>Command</code>�����в����б����Ϣ��
 * </p>
 * <p>
 * ������汾���<code>Command</code>�����νṹʵ������Ϊ�������������������ķ�ʽ����������ȷ����װ��
 * <code>Command</code>�� �������<code>Command</code>���νṹ�����ܺ��㷨���Ӷȡ�
 * </p>
 * <p>
 * ͨ������<code>CosmosCommand</code>��<code>execute()</code>������ִ��һ�����ͨ����r�²�Ҫͨ������
 * <code>getExecuter()</code>�õ�<code>CosmosExecuter</code>֮���ٵ���
 * <code>CosmosExecuter</code>��<code>execute()</code>������ִ�С��������������˵��ͨ����û��
 * <code>CosmosExecuter</code>��������ִ�еġ�
 * </p>
 * <p>
 * ����ͨ�������������ϵ�һ��ķ�������ö��ִ�н����ͨ�����ָ����һ��ʵ����
 * <code>com.microbrain.cosmos.core.command.composite.CosmosCompositeCommand</code>
 * �ӿڵ���������������һЩ���õ����ָ������л�����˳��ִ�к��ж�ִ�е�������
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
	 * ���������������ơ�
	 * 
	 * @return �������ơ�
	 */
	public String getName();

	/**
	 * ��ø�������������
	 * 
	 * @return ���ظ�����������
	 */
	public CosmosDomain getDomain();

	/**
	 * ��ø�����ľ������ݡ�
	 * 
	 * @return ����������ݡ�
	 */
	public String getCommand();

	/**
	 * ����������������Ϣ��
	 * 
	 * @return �������������<code>CosmosMetaCommand</code>��
	 */
	public CosmosMetaCommand getType();

	/**
	 * ��������ִ������
	 * 
	 * @return �����������ִ������
	 */
	public CosmosExecuter getExecuter();

	/**
	 * ��ø��������Դ��
	 * 
	 * @return ������Դ���ֱ�Ϊȫ��<code>GLOBAL</code>���߱���<code>LOCAL</code>��
	 */
	public CosmosCommandSource getSource();

	/**
	 * ���һ�������˵�������֡�
	 * 
	 * @param remark
	 *            �����˵�������֡�
	 */
	public void setRemark(String remark);

	/**
	 * ��������˵�������֡�
	 * 
	 * @return �����˵����
	 */
	public String getRemark();

	/**
	 * ��������ĵ��Լ��𣬻Ḳ�ǵ���ĵ��Լ����������ΪNO_DEBUG�������ĵ��Լ�����ͬ��
	 * 
	 * @param debugLevel
	 *            ���Լ���
	 */
	public void setDebugLevel(DebugLevel debugLevel);

	/**
	 * �������ĵ��Լ���
	 * 
	 * @return ���Լ���
	 */
	public DebugLevel getDebugLevel();

	/**
	 * һ�������Ƿ��¼ĳ�����Լ������Ϣ��
	 * 
	 * @param debugLevel
	 *            ���Լ���
	 * @return ���ظü������־�Ƿ�Ӧ������¼��
	 */
	public boolean isLogEnabled(DebugLevel debugLevel);

	/**
	 * ��ø��������еĲ����б���Ϣ��
	 * 
	 * @return �����б�
	 */
	public Collection<CosmosMetaArgument> listMetaArguments();

	/**
	 * ��������������������������������νṹʱʹ�á�
	 * 
	 * @return ��������
	 */
	public Long getLeftIndex();

	/**
	 * �����������������
	 * 
	 * @param leftIndex
	 *            �����������ֵ��
	 */
	public void setLeftIndex(Long leftIndex);

	/**
	 * ��������������������������������νṹʱʹ�á�
	 * 
	 * @return ��������
	 */
	public Long getRightIndex();

	/**
	 * �����������������
	 * 
	 * @param rightIndex
	 *            �����������ֵ��
	 */
	public void setRightIndex(Long rightIndex);

	/**
	 * �ò���argsִ��������
	 * 
	 * @param args
	 *            ���������
	 * @return ����ִ�н����
	 * @throws CosmosExecuteException
	 *             ִ�������׳����쳣��
	 */
	public CosmosResult execute(Map<String, Object> args)
			throws CosmosExecuteException;

	/**
	 * �ò���argsִ������������Ҫȷ�����û������Ȩ�޷����׳�Ȩ���쳣
	 * <code>CosmosPermissionException</code>��
	 * 
	 * @param auth
	 *            ִ��������û���֤��Ϣ��
	 * @param args
	 *            ���������
	 * @return ����ִ�н����
	 * @throws CosmosExecuteException
	 *             ִ�������׳����쳣��
	 * @throws CosmosPermissionException
	 *             �]��Ȩ��ʱ���׳����쳣��
	 */
	public CosmosResult execute(Authorization auth, Map<String, Object> args)
			throws CosmosExecuteException, CosmosPermissionException;

	/**
	 * ���Ʊ��������ǳ�ȸ��ƣ����ܸ��Ƹ������������������
	 * 
	 * @return ���ظ���֮��������
	 * @throws CosmosFactoryException
	 *             ���Ʊ�����ʱ�׳�������쳣��
	 */
	public CosmosCommand duplicate() throws CosmosFactoryException;

}
