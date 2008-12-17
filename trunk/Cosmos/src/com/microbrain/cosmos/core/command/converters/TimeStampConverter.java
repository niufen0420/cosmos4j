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

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.command.AbstractCosmosArgumentConverter;
import com.microbrain.cosmos.core.config.Configuration;

/**
 * <p>
 * <code>TimeStamp</code>类型转换器，将字符串型转换为<code>TimeStamp</code>。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.AbstractCosmosArgumentConverter
 * @see com.microbrain.cosmos.core.command.CosmosArgumentConverter
 * @see com.microbrain.cosmos.core.command.converters.BigDecimalConverter
 * @see com.microbrain.cosmos.core.command.converters.BooleanConverter
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
public class TimeStampConverter extends AbstractCosmosArgumentConverter {

	/**
	 * 类型转换长度。
	 */
	private static final int DATE_FORMAT_LENGTH = 10;

	/**
	 * 长Date类型转换。
	 */
	private static final SimpleDateFormat SDF_FULL = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 短Date类型转换。
	 */
	private static final SimpleDateFormat SDF_DATE = new SimpleDateFormat(
			"yyyy-MM-dd");

	/**
	 * 构造函数。
	 * 
	 * @param label
	 *            类型转换器的标签。
	 * @param name
	 *            类型转换器的名称。
	 */
	public TimeStampConverter(String label, String name) {
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
		Timestamp rlt = null;
		long time = 0;
		try {
			String trimValue = value.trim();
			if (trimValue.length() == DATE_FORMAT_LENGTH) {
				time = SDF_DATE.parse(value).getTime();
			} else {
				time = SDF_FULL.parse(value).getTime();
			}

			rlt = new Timestamp(time);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Bad timestamp format of '"
					+ value + "',should be 'yyyy-MM-dd HH:mm:ss'");
		}
		return rlt;
	}

}
