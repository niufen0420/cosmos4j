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

/**
 * <p>
 * ��Щ�������޷��洢��<code>master</code> ��ģ����磺<code>master</code>��ΪXML�ļ�����
 * <code>slave</code> ��Ϊ���ݿ������£����ݿ��еĴ洢��������Ҫ������װ�صġ������ڴ洢�������������
 * ʵ�����Ѿ��洢�����ݿ����ˣ���ˣ���ϵͳ��������master�����ٱ���һ�ݣ������ṩ��������װ������ ��װ���������������
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 */
public interface CosmosLocalCommandLoader {

	/**
	 * װ�ر��������е����
	 * 
	 * @return ���ر��������������б�
	 * @throws CosmosCommandLoaderException
	 *             ����װ��ʱ�׳����쳣��
	 */
	public Collection<CosmosCommand> loadAllLocalRootCommands()
			throws CosmosCommandLoaderException;

	/**
	 * װ�ر�����ĳ�����
	 * 
	 * @param name
	 *            Ҫװ�ص��������ơ�
	 * @return ���ظ����
	 * @throws CosmosCommandLoaderException
	 *             ����װ��ʱ�׳����쳣��
	 */
	public CosmosCommand loadLocalRootCommand(String name)
			throws CosmosCommandLoaderException;

}
