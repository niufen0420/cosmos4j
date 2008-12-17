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
package com.microbrain.cosmos.core.factory;

import java.util.Collection;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.CosmosFactoryException;
import com.microbrain.cosmos.core.auth.Authorization;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.config.Configuration;
import com.microbrain.cosmos.dev.CosmosCommandManager;

/**
 * <p>
 * ����<code>com.microbrain.cosmos.core.CosmosFactory</code>
 * ������һ��ʵ�֣���ͼ�������ļ��洢��JNDI�У��Ա��ڿ�����ʱ��ȡ��
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.CosmosFactory
 * @see com.microbrain.cosmos.core.factory.DefaultCosmosFactoryImpl
 * @see com.microbrain.cosmos.core.config.Configuration
 * @since CFDK 1.0
 */
public class JndiCosmosFactory extends CosmosFactory {

	/*
	 * ͨ�������ƺ��������ƻ�����
	 * 
	 * @see
	 * com.microbrain.cosmos.core.CosmosFactory#getCommand(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public CosmosCommand getCommand(String domain, String name)
			throws CosmosFactoryException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * ͨ���������ƴ������л�����
	 * 
	 * @see
	 * com.microbrain.cosmos.core.CosmosFactory#getCommand(java.lang.String)
	 */
	@Override
	public CosmosCommand getCommand(String name) throws CosmosFactoryException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * ���һ��<code>SAL</code>�ӿڵ�ʵ����
	 * 
	 * @see com.microbrain.cosmos.core.CosmosFactory#getService(java.lang.Class)
	 */
	@Override
	public <T> T getService(Class<?> clazz) throws CosmosFactoryException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * ��Cosmos������ע��һ��������ʵ����
	 * 
	 * @see
	 * com.microbrain.cosmos.core.CosmosFactory#injectConfig(com.microbrain.
	 * cosmos.core.config.Configuration)
	 */
	@Override
	protected void injectConfig(Configuration config) {
		// TODO Auto-generated method stub

	}

	/*
	 * ����ע���������ʵ����
	 * 
	 * @see com.microbrain.cosmos.core.CosmosFactory#lookupConfig()
	 */
	@Override
	public Configuration lookupConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * ������֤��Ϣ�ػ��һ��<code>SAL</code>�ӿڵ�ʵ����
	 * 
	 * @see
	 * com.microbrain.cosmos.core.CosmosFactory#getService(com.microbrain.cosmos
	 * .core.auth.Authorization, java.lang.Class)
	 */
	@Override
	public <T> T getService(Authorization auth, Class<?> clazz)
			throws CosmosFactoryException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * ������е������б�
	 * 
	 * @see com.microbrain.cosmos.core.CosmosFactory#listAllCommands()
	 */
	@Override
	public Collection<CosmosCommand> listAllCommands()
			throws CosmosFactoryException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * ������е�ȫ�������б�
	 * 
	 * @see com.microbrain.cosmos.core.CosmosFactory#listAllGlobalCommands()
	 */
	@Override
	public Collection<CosmosCommand> listAllGlobalCommands()
			throws CosmosFactoryException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * ������еı��������б�
	 * 
	 * @see com.microbrain.cosmos.core.CosmosFactory#listAllLocalCommands()
	 */
	@Override
	public Collection<CosmosCommand> listAllLocalCommands()
			throws CosmosFactoryException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * ���һ������Ĺ����ߡ�
	 * 
	 * @see com.microbrain.cosmos.core.CosmosFactory#getManager()
	 */
	@Override
	public CosmosCommandManager getManager() throws CosmosFactoryException {
		// TODO Auto-generated method stub
		return null;
	}

}
