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
package com.microbrain.cosmos.core.command.config;

import java.util.ArrayList;
import java.util.Collection;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.composite.CosmosCompositeCommand;
import com.microbrain.cosmos.core.command.loaders.AbstractCosmosGlobalCommandLoader;
import com.microbrain.cosmos.core.command.loaders.CosmosCommandLoaderException;
import com.microbrain.cosmos.core.domain.CosmosDomain;

/**
 * <p>
 * ��ȡ�����ļ���ȫ������װ������ʵ����<code>CosmosGlobalCommandLoader</code>�ӿڣ���Ҫ�Ĺ����ǴӸ�����������ļ���
 * ��ȡ��Ӧ���������װ�ɿ���ִ�еĵ�Ԫ��
 * </p>
 * <p>
 * <code>CosmosConfigGlobalCommandLoader</code>ʵ�������װ��һ���������������������ķ�����
 * װ��ĳ��������������ķ�����װ��ϵͳ��������ķ�����װ��ĳ�����µ�������ķ�����
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.loaders.CosmosGlobalCommandLoader
 * @see com.microbrain.cosmos.core.command.loaders.AbstractCosmosGlobalCommandLoader
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @since CFDK 1.0
 */
public class CosmosConfigGlobalCommandLoader extends
		AbstractCosmosGlobalCommandLoader {

	/**
	 * ���캯����
	 * 
	 * @param factory
	 *            Cosmos�����ࡣ
	 * @param domain
	 *            ��װ����������
	 */
	public CosmosConfigGlobalCommandLoader(CosmosFactory factory,
			CosmosDomain domain) {
		super(factory, domain);
	}

	/*
	 * ���װ��һ����������������Ŀǰ��֧���������ˣ�ֱ�ӷ��ظ����
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.loaders.AbstractCosmosGlobalCommandLoader
	 * #deepLoadCommand(com.microbrain.cosmos.core.command.composite.
	 * CosmosCompositeCommand, java.util.Collection)
	 */
	@Override
	protected CosmosCompositeCommand deepLoadCommand(
			CosmosCompositeCommand command,
			Collection<CosmosCommand> loadedCommands)
			throws CosmosCommandLoaderException {
		return command;
	}

	/*
	 * װ��ĳ���������е����Ŀǰ�汾��֧�ֱ�������
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.loaders.AbstractCosmosGlobalCommandLoader
	 * #loadDomainRootCommands(com.microbrain.cosmos.core.domain.CosmosDomain)
	 */
	@Override
	protected Collection<CosmosCommand> loadDomainRootCommands(
			CosmosDomain domain) throws CosmosCommandLoaderException {
		return new ArrayList<CosmosCommand>(0);
	}

	/*
	 * װ�����е����Ŀǰ�汾��֧�ֱ�������
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.loaders.AbstractCosmosGlobalCommandLoader
	 * #loadRootCommands()
	 */
	@Override
	protected Collection<CosmosCommand> loadRootCommands()
			throws CosmosCommandLoaderException {
		return new ArrayList<CosmosCommand>(0);
	}

	/*
	 * װ��ĳ�����Ŀǰ�汾��֧�ִ˷�����
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.loaders.AbstractCosmosGlobalCommandLoader
	 * #loadSingleCommand(com.microbrain.cosmos.core.domain.CosmosDomain,
	 * java.lang.String)
	 */
	@Override
	protected CosmosCommand loadSingleCommand(CosmosDomain domain, String name)
			throws CosmosCommandLoaderException {
		return null;
	}

}
