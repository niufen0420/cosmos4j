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
package com.microbrain.cosmos.core.domain.db;

import com.microbrain.cosmos.core.command.CosmosExecuter;
import com.microbrain.cosmos.core.command.db.CosmosCallableExecuter;
import com.microbrain.cosmos.core.config.ConfigurationException;
import com.microbrain.cosmos.core.constants.Constants;
import com.microbrain.cosmos.core.domain.AbstractCosmosDomain;
import com.microbrain.cosmos.core.domain.CosmosDomainException;
import com.microbrain.cosmos.core.domain.CosmosDomainType;

/**
 * <p>
 * �����SQL��
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.domain.AbstractCosmosDomain
 * @see com.microbrain.cosmos.core.domain.db.CosmosSqlDomain
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @since CFDK 1.0
 */
public abstract class AbstractCosmosSqlDomain extends AbstractCosmosDomain
		implements CosmosSqlDomain {

	/**
	 * Ĭ�ϵķ�����
	 */
	private static final String DOMAIN_SCHEMA = "schema";

	/**
	 * Ĭ�ϵĴ洢���̵�������
	 */
	private static final String DEFAULT_CALL_EXECUTER = "default-call-executer";

	/**
	 * ���ݿ���ʵķ������ơ�
	 */
	private String schema = null;

	/**
	 * Ĭ�ϵĴ洢���̵�������
	 */
	private String defaultCallExecuter = null;

	/**
	 * ���·����
	 */
	protected String entryPath = null;

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
	public AbstractCosmosSqlDomain(String name, CosmosDomainType type, int index) {
		super(name, type, index);
		this.entryPath = String.format(Constants.COSMOS_DOMAINS_DOMAIN_INDEX,
				this.index);
	}

	/*
	 * ��ʼ����
	 * 
	 * @see com.microbrain.cosmos.core.domain.AbstractCosmosDomain#initDomain()
	 */
	protected void initDomain() throws CosmosDomainException {
		try {
			this.schema = config.getElementInitParameter(entryPath,
					DOMAIN_SCHEMA);
			this.defaultCallExecuter = config.getElementInitParameter(
					entryPath, DEFAULT_CALL_EXECUTER);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * ��÷�����
	 * 
	 * @see com.microbrain.cosmos.core.domain.db.CosmosSqlDomain#getSchema()
	 */
	public String getSchema() {
		return this.schema;
	}

	/*
	 * ���Ҵ洢���̴�������
	 * 
	 * @see
	 * com.microbrain.cosmos.core.domain.db.CosmosSqlDomain#findCallExecuter()
	 */
	public CosmosCallableExecuter findCallExecuter() {
		if (defaultCallExecuter != null) {
			CosmosExecuter executer = this.executerMap.get(defaultCallExecuter);
			if (executer != null && executer instanceof CosmosCallableExecuter) {
				return (CosmosCallableExecuter) executer;
			}
		}

		CosmosCallableExecuter callableExecuter = null;
		for (CosmosExecuter executer : this.executerMap.values()) {
			if (executer instanceof CosmosCallableExecuter) {
				callableExecuter = (CosmosCallableExecuter) executer;
				break;
			}
		}

		return callableExecuter;
	}

}
