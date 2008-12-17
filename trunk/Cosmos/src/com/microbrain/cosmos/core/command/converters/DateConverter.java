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

import java.sql.Date;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.command.AbstractCosmosArgumentConverter;
import com.microbrain.cosmos.core.config.Configuration;

/**
 * <p>
 * <code>Boolean</code>类型转换器，将字符串型转换为<code>Boolean</code>。
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
 * @see com.microbrain.cosmos.core.command.converters.IntegerConverter
 * @see com.microbrain.cosmos.core.command.converters.FloatConverter
 * @see com.microbrain.cosmos.core.command.converters.DoubleConverter
 * @see com.microbrain.cosmos.core.command.converters.DateConverter
 * @see com.microbrain.cosmos.core.command.converters.ByteArrayConverter
 */
public class DateConverter extends AbstractCosmosArgumentConverter {

	/**
	 * 构造函数。
	 * 
	 * @param label
	 *            类型转换器的标签。
	 * @param name
	 *            类型转换器的名称。
	 */
	public DateConverter(String label, String name) {
		super(label, name);
	}

	/*
	 * 初始化方法。
	 * 
	 * @seecom.microbrain.cosmos.core.command.CosmosArgumentConverter#init(com.
	 * microbrain.cosmos.core.config.Configuration,
	 * com.microbrain.cosmos.core.CosmosFactory)
	 */
	public void init(Configuration config, CosmosFactory factory) {
		// TODO Auto-generated method stub

	}

	/*
	 * 类型转换方法。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosArgumentConverter#convert(java
	 * .lang.String)
	 */
	public Object convert(String value) {
		Date rlt = null;
		try {
			rlt = Date.valueOf(value);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Bad date format of '" + value
					+ "',should be 'yyyy-mm-dd'");
		}
		return rlt;
	}

}
