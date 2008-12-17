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

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.config.Configuration;

/**
 * <p>
 * <code>CosmosArgumentConverter</code> �ǽ��в�������ת���ĳ���ӿڣ����ฺ���ַ����͵�
 * <code>CosmosMetaArgument</code>�������Ӧ�Ĳ������ͣ�ת����Ŀ�����͡�
 * </p>
 * <p>
 * ���о����<code>Converter</code>�඼��Ҫʵ�ֱ��ӿڣ�ϵͳĿǰ���õ�<code>Converter</code>�඼λ�ڰ�
 * <code>com.microbrain.cosmos.core.command.converters</code>
 * �У�������BigDecimalConverter, BooleanConverter, TimeStampConverter,
 * TimeConverter, StringConverter, LongConverter, IntegerConverter,
 * FloatConverter, DoubleConverter, DateConverter, ByteArrayConverter��
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.AbstractCosmosArgumentConverter
 * @see com.microbrain.cosmos.core.command.converters.BigDecimalConverter
 * @see com.microbrain.cosmos.core.command.converters.BooleanConverter
 * @see com.microbrain.cosmos.core.command.converters.TimeStampConverter
 * @see com.microbrain.cosmos.core.command.converters.TimeConverter
 * @see com.microbrain.cosmos.core.command.converters.StringConverter
 * @see com.microbrain.cosmos.core.command.converters.LongConverter
 * @see com.microbrain.cosmos.core.command.converters.IntegerConverter
 * @see com.microbrain.cosmos.core.command.converters.FloatConverter
 * @see com.microbrain.cosmos.core.command.converters.DoubleConverter
 * @see com.microbrain.cosmos.core.command.converters.DateConverter
 * @see com.microbrain.cosmos.core.command.converters.ByteArrayConverter
 * @since CFDK 1.0
 */
public interface CosmosArgumentConverter {

	/**
	 * ���<code>Converter</code>�����ơ�
	 * 
	 * @return ���ظ�<code>Converter</code>�����ơ�
	 */
	public String getName();

	/**
	 * ���<code>Converter</code>�ı�ǩ��
	 * 
	 * @return ���ظ�<code>Converter</code>�ı�ǩ��
	 */
	public String getLabel();

	/**
	 * ���<code>Converter</code>ӳ���jdbc���͡�
	 * 
	 * @return ���ظ�<code>Converter</code>ӳ���jdbc���͡�
	 */
	public Collection<Integer> getMappedJdbcTypes();

	/**
	 * ����<code>Converter</code>ӳ���jdbc���͡�
	 * 
	 * @param mappedJdbcTypes
	 *            jdbc���͡�
	 */
	public void setMappedJdbcTypes(Collection<Integer> mappedJdbcTypes);

	/**
	 * ��ʼ��һ��<code>Converter</code>��
	 * 
	 * @param config
	 *            Cosmos��ܵ������ࡣ
	 * @param factory
	 *            Cosmos��ܵĹ����ࡣ
	 */
	public void init(Configuration config, CosmosFactory factory);

	/**
	 * ����������Ϣ��һ��<code>String</code>�ı���ת��Ϊ��Ӧ�����͡�
	 * 
	 * @param value
	 *            ԭʼ��<code>String</code>����ֵ��
	 * @return ���ض�Ӧ���͵�ֵ��
	 */
	public Object convert(String value);

}
