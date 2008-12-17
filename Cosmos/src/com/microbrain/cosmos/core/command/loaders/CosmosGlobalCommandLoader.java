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
package com.microbrain.cosmos.core.command.loaders;

import java.util.Collection;

import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.domain.CosmosDomain;

/**
 * <p>
 * ȫ�ֵ�����װ����������Щ���У��ǿ��Դ洢����������ģ����������֮Ϊ����Ϊmaster���͵���
 * ���磺���ݿ��򣬿��������ݿ���д洢�����ʱ����Ҫͨ��ʵ�ֱ��ӿڣ��ﵽ�ܴ�����������װ�������Ŀ�ġ�
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @since CFDK 1.0
 */
public interface CosmosGlobalCommandLoader {

	/**
	 * װ�����е����
	 * 
	 * @return ���������б�
	 * @throws CosmosCommandLoaderException
	 *             װ��ʱ�׳����쳣��
	 */
	public Collection<CosmosCommand> loadAllCommands()
			throws CosmosCommandLoaderException;

	/**
	 * װ��ĳ�����µ��������
	 * 
	 * @param domain
	 *            ������
	 * @return ���������������б�
	 * @throws CosmosCommandLoaderException
	 *             װ��ʱ�׳����쳣��
	 */
	public Collection<CosmosCommand> loadDomainCommands(CosmosDomain domain)
			throws CosmosCommandLoaderException;

	/**
	 * װ��ĳ�����
	 * 
	 * @param domain
	 *            ������
	 * @param name
	 *            �������ơ�
	 * @return �����
	 * @throws CosmosCommandLoaderException
	 *             װ��ʱ�׳����쳣��
	 */
	public CosmosCommand loadCommand(CosmosDomain domain, String name)
			throws CosmosCommandLoaderException;

}
