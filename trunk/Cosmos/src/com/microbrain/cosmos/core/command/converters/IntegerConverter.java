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
package com.microbrain.cosmos.core.command.converters;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.command.AbstractCosmosArgumentConverter;
import com.microbrain.cosmos.core.config.Configuration;

/**
 * <p>
 * <code>Integer</code>����ת���������ַ�����ת��Ϊ<code>Integer</code>��
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.AbstractCosmosArgumentConverter
 * @see com.microbrain.cosmos.core.command.CosmosArgumentConverter
 * @see com.microbrain.cosmos.core.command.converters.BigDecimalConverter
 * @see com.microbrain.cosmos.core.command.converters.BooleanConverter
 * @see com.microbrain.cosmos.core.command.converters.TimeStampConverter
 * @see com.microbrain.cosmos.core.command.converters.TimeConverter
 * @see com.microbrain.cosmos.core.command.converters.StringConverter
 * @see com.microbrain.cosmos.core.command.converters.LongConverter
 * @see com.microbrain.cosmos.core.command.converters.FloatConverter
 * @see com.microbrain.cosmos.core.command.converters.DoubleConverter
 * @see com.microbrain.cosmos.core.command.converters.DateConverter
 * @see com.microbrain.cosmos.core.command.converters.ByteArrayConverter
 * @since CFDK 1.0
 */
public class IntegerConverter extends AbstractCosmosArgumentConverter {

	/**
	 * ���캯����
	 * 
	 * @param label
	 *            ����ת�����ı�ǩ��
	 * @param name
	 *            ����ת���������ơ�
	 */
	public IntegerConverter(String label, String name) {
		super(label, name);
	}

	/*
	 * ��ʼ��������
	 * 
	 * @seecom.microbrain.cosmos.core.command.CosmosArgumentConverter#init(com.
	 * microbrain.cosmos.core.config.Configuration,
	 * com.microbrain.cosmos.core.CosmosFactory)
	 */
	public void init(Configuration config, CosmosFactory factory) {
		// TODO Auto-generated method stub

	}

	/*
	 * ����ת��������
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosArgumentConverter#convert(java
	 * .lang.String)
	 */
	public Object convert(String value) {
		Integer rlt = Integer.valueOf(0);
		if ((value != null) && !value.equals("")) {
			rlt = Integer.valueOf(value);
		}
		return rlt;
	}

}
