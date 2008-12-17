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
package com.microbrain.cosmos.core.domain.config;

import com.microbrain.cosmos.core.command.config.CosmosConfigGlobalCommandLoader;
import com.microbrain.cosmos.core.command.config.CosmosConfigLocalCommandLoader;
import com.microbrain.cosmos.core.command.loaders.CosmosGlobalCommandLoader;
import com.microbrain.cosmos.core.command.loaders.CosmosLocalCommandLoader;
import com.microbrain.cosmos.core.domain.AbstractCosmosDomain;
import com.microbrain.cosmos.core.domain.CosmosDomainException;
import com.microbrain.cosmos.core.domain.CosmosDomainType;

/**
 * <p>
 * �����ļ��򣬶�ȡ�����ļ����ں����İ汾�н��ṩ���������ļ���ִ������
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @see com.microbrain.cosmos.core.domain.AbstractCosmosDomain
 * @since CFDK 1.0
 */
public class CosmosConfigurationDomain extends AbstractCosmosDomain {

	/**
	 * ȫ������װ������
	 */
	private CosmosGlobalCommandLoader globalLoader = null;

	/**
	 * ��������װ������
	 */
	private CosmosLocalCommandLoader localLoader = null;

	/**
	 * ���캯����
	 * 
	 * @param name
	 *            �����ơ�
	 * @param type
	 *            �����͡�
	 * @param index
	 *            ����š�
	 */
	public CosmosConfigurationDomain(String name, CosmosDomainType type,
			int index) {
		super(name, type, index);
	}

	/*
	 * ��ʼ����
	 * 
	 * @see com.microbrain.cosmos.core.domain.AbstractCosmosDomain#initDomain()
	 */
	@Override
	protected void initDomain() throws CosmosDomainException {
	}

	/*
	 * ��ʼ��װ������
	 * 
	 * @see com.microbrain.cosmos.core.domain.AbstractCosmosDomain#initLoader()
	 */
	@Override
	protected void initLoader() throws CosmosDomainException {
		this.globalLoader = new CosmosConfigGlobalCommandLoader(this.factory,
				this);
		this.localLoader = new CosmosConfigLocalCommandLoader(this.factory,
				this);
	}

	/*
	 * ���ȫ��װ������
	 * 
	 * @see
	 * com.microbrain.cosmos.core.domain.AbstractCosmosDomain#getGlobalLoader()
	 */
	public CosmosGlobalCommandLoader getGlobalLoader() {
		return this.globalLoader;
	}

	/*
	 * ��ñ���װ������
	 * 
	 * @see
	 * com.microbrain.cosmos.core.domain.AbstractCosmosDomain#getLocalLoader()
	 */
	public CosmosLocalCommandLoader getLocalLoader() {
		return this.localLoader;
	}

}
