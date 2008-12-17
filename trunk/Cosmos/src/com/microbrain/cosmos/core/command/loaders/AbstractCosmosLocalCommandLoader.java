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

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.config.Configuration;
import com.microbrain.cosmos.core.domain.CosmosDomain;

/**
 * <p>
 * <code>CosmosLocalCommandLoader</code>�ӿڵĳ���ʵ�֣����еı���װ������Ӧ����չ�Ա������ࡣ
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.loaders.CosmosLocalCommandLoader
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @since CFDK 1.0
 */
public abstract class AbstractCosmosLocalCommandLoader implements
		CosmosLocalCommandLoader {

	/**
	 * Cosmos�����ࡣ
	 */
	protected CosmosFactory factory = null;

	/**
	 * ��������ࡣ
	 */
	protected Configuration config = null;

	/**
	 * ������
	 */
	protected CosmosDomain domain = null;

	/**
	 * ���캯����
	 * 
	 * @param factory
	 *            Cosmos�����ࡣ
	 * @param domain
	 *            ������
	 */
	public AbstractCosmosLocalCommandLoader(CosmosFactory factory,
			CosmosDomain domain) {
		this.factory = factory;
		this.config = factory.lookupConfig();
		this.domain = domain;
	}

}
