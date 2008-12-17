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

/**
 * <p>
 * ʵ����<code>CosmosArgumentConverter</code>�ӿڵĳ����࣬�ṩ�˷���
 * <code>CosmosArgumentConverter</code>�����ƺͱ�ǩ�ķ�������������ת����ײ�����ݿ�����
 * <code>java.sql.Types</code>����������
 * </p>
 * <p>
 * ���о����<code>Converter</code>�඼��Ҫ�ӱ�������̳�������ϵͳĿǰ���õ�<code>Converter</code>�඼λ�ڰ�
 * <code>com.microbrain.cosmos.core.command.converters</code>
 * �У�������BigDecimalConverter, BooleanConverter, TimeStampConverter,
 * TimeConverter, StringConverter, LongConverter, IntegerConverter,
 * FloatConverter, DoubleConverter, DateConverter, ByteArrayConverter��
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosArgumentConverter
 * @see com.microbrain.cosmos.core.command.CosmosMetaArgument
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @since CFDK 1.0
 */
public abstract class AbstractCosmosArgumentConverter implements
		CosmosArgumentConverter {

	/**
	 * ����ת�������ơ�
	 */
	protected String name = null;

	/**
	 * ����ת������ǩ��
	 */
	protected String label = null;

	/**
	 * ����ת����ӳ���jdbc���͡�
	 */
	protected Collection<Integer> mappedJdbcTypes = null;

	/**
	 * ���캯�����ڳ�ʼ��CosmosArgumentConverterʱ�����á�
	 * 
	 * @param name
	 *            ת�������ơ�
	 * @param label
	 *            ת������ǩ��
	 */
	public AbstractCosmosArgumentConverter(String name, String label) {
		this.name = name;
		this.label = label;
	}

	/*
	 * �������ת�����ı�ǩ��
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosArgumentConverter#getLabel()
	 */
	public String getLabel() {
		return this.label;
	}

	/*
	 * �������ת����ӳ���jdbc���͡�
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosArgumentConverter#getMappedJdbcTypes
	 * ()
	 */
	public Collection<Integer> getMappedJdbcTypes() {
		return this.mappedJdbcTypes;
	}

	/*
	 * ��������ת����ӳ���jdbc���͡�
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosArgumentConverter#setMappedJdbcTypes
	 * (java.util.Collection)
	 */
	public void setMappedJdbcTypes(Collection<Integer> mappedJdbcTypes) {
		this.mappedJdbcTypes = mappedJdbcTypes;
	}

	/*
	 * �������ת���������ơ�
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosArgumentConverter#getName()
	 */
	public String getName() {
		return this.name;
	}

}
