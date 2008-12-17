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
package com.microbrain.cosmos.core.domain;

import java.util.Collection;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.CosmosExecuter;
import com.microbrain.cosmos.core.command.loaders.CosmosGlobalCommandLoader;
import com.microbrain.cosmos.core.command.loaders.CosmosLocalCommandLoader;
import com.microbrain.cosmos.core.config.Configuration;
import com.microbrain.cosmos.core.constants.DebugLevel;

/**
 * <p>
 * ��<code>cosmos</code>����У�һ������ָһ������ִ������Ļ���������ܰ��������ݿ�����ڴ����ݿ⣨<code>SQL</code>����
 * <code>XML</code>�ļ���<code>XPATH</code>����<code>WebService</code>��
 * <code>wsdl</code>�����ļ�ϵͳ��������<code>java</code>����<code>Java scripting</code>��
 * <code>scripting</code>��䣩�ȡ���Щ�������Ϊmaster���͵�����������������洢ȫ������������ݿ����
 * <code>XML</code>�ļ���
 * </p>
 * <p>
 * ��ͨ��<code>category</code>������ȷ�ϸ����ڿ���ʹ�õ�ִ����<code>executer</code>�ģ������ִ�����ж�ӵ������
 * <code>category</code>��ͨ�������Խ����߹������������ÿһ�����ÿһ��ִ������<code>category</code>
 * ���Զ��ǲ��ɻ�ȱ�ġ�
 * </p>
 * <p>
 * ���п���ִ�е������ǿ��Ի��������еģ��������ͨ������<code>cachable</code>�������ﵽ����<code>cachable</code>
 * ����Ϊ<code>true</code>ʱ������ͨ������<code>reloadable</code>���ԣ�������ÿ��ִ��һ������ʱ���Ƿ�Ҫ����������
 * </p>
 * <p>
 * ͨ��������ĵ��Լ������ﵽͳһ���õ��Լ����Ŀ�ģ�����Ҳ����ͨ����������ĳ������ĵ��Լ��𣬴ﵽ���������е��Ե�Ŀ�ġ�
 * </p>
 * <p>
 * ��������ʱ��Ҫ��ʼ�����ڳ�����ֹʱ��Ҫ���٣�����ͨ��������ø����е�һ�������ȻҲ����ͨ��
 * <code>com.microbrain.cosmos.core.CosmosFactory</code>�����һ�����
 * </p>
 * <p>
 * ÿ������һ�����������ƣ���֮��������ҪΨһ����֮�ڵ�����������ҪΨһ����֮����������ƿ����ظ���
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @see com.microbrain.cosmos.core.command.CosmosExecuter
 * @see com.microbrain.cosmos.core.domain.AbstractCosmosDomain
 * @see com.microbrain.cosmos.core.domain.CosmosDomainType
 * @see com.microbrain.cosmos.core.CosmosFactory
 * @see com.microbrain.cosmos.core.config.Configuration
 * @since CFDK 1.0
 */
public interface CosmosDomain {

	/**
	 * ��������ơ�
	 * 
	 * @return �����ơ�
	 */
	public String getName();

	/**
	 * ��������͡�
	 * 
	 * @return �����͡�
	 */
	public CosmosDomainType getType();

	/**
	 * �������ࡣ
	 * 
	 * @return ����ࡣ
	 */
	public String getCategory();

	/**
	 * ������Ƿ񻺴档
	 * 
	 * @return ���Ƿ񻺴档
	 */
	public Boolean isCachable();

	/**
	 * ������Ƿ����ء�
	 * 
	 * @return ���Ƿ����ء�
	 */
	public Boolean isReloadable();

	/**
	 * �������š�
	 * 
	 * @return ����š�
	 */
	public int getIndex();

	/**
	 * �������Լ���
	 * 
	 * @return ����Լ���
	 */
	public DebugLevel getDebugLevel();

	/**
	 * ��ʼ����
	 * 
	 * @param config
	 *            ������ʵ����
	 * @param factory
	 *            ������ʵ����
	 * @throws CosmosDomainException
	 *             ��ʼ����ʱ�׳����쳣��
	 */
	public void init(Configuration config, CosmosFactory factory)
			throws CosmosDomainException;

	/**
	 * ������п��õ�ִ������
	 * 
	 * @return ���п��õ�ִ������
	 * @throws CosmosDomainException
	 *             ������п���ִ����ʱ�׳����쳣��
	 */
	public Collection<CosmosExecuter> availableExecuters()
			throws CosmosDomainException;

	/**
	 * ͨ�����ƻ��һ��ִ������
	 * 
	 * @param name
	 *            ִ�������ơ�
	 * @return ִ������
	 * @throws CosmosDomainException
	 *             �����޸����Ƶ�ִ������
	 */
	public CosmosExecuter getExecuter(String name) throws CosmosDomainException;

	/**
	 * ����������е��������ȫ������ͱ������
	 * 
	 * @return ���������б�
	 * @throws CosmosDomainException
	 *             �����������ʱ�׳����쳣��
	 */
	public Collection<CosmosCommand> listAllCommands()
			throws CosmosDomainException;

	/**
	 * ����������е�ȫ�����
	 * 
	 * @return ����ȫ�������б�
	 * @throws CosmosDomainException
	 *             �������ȫ������ʱ�׳����쳣��
	 */
	public Collection<CosmosCommand> listGlobalCommands()
			throws CosmosDomainException;

	/**
	 * ����������еı������
	 * 
	 * @return �������б��������б�
	 * @throws CosmosDomainException
	 *             ������б�������ʱ�׳����쳣��
	 */
	public Collection<CosmosCommand> listLocalCommands()
			throws CosmosDomainException;

	/**
	 * ͨ�����ƻ������ĳ�����
	 * 
	 * @param name
	 *            �������ơ�
	 * @return �����
	 * @throws CosmosDomainException
	 *             �������ĳ������ʱ�׳����쳣��
	 */
	public CosmosCommand getCommand(String name) throws CosmosDomainException;

	/**
	 * ������ȫ������װ������
	 * 
	 * @return ȫ������װ������
	 */
	public CosmosGlobalCommandLoader getGlobalLoader();

	/**
	 * �����ı�������װ������
	 * 
	 * @return ��������װ������
	 */
	public CosmosLocalCommandLoader getLocalLoader();

	/**
	 * ����һ����
	 * 
	 * @throws CosmosDomainException
	 *             ����һ����ʱ�׳����쳣��
	 */
	public void destroy() throws CosmosDomainException;

}
