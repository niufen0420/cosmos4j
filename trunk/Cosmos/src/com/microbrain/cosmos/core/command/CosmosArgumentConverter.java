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
 * <code>CosmosArgumentConverter</code> 是进行参数类型转换的抽象接口，本类负责将字符串型的
 * <code>CosmosMetaArgument</code>根据其对应的参数类型，转换成目标类型。
 * </p>
 * <p>
 * 所有具体的<code>Converter</code>类都需要实现本接口，系统目前内置的<code>Converter</code>类都位于包
 * <code>com.microbrain.cosmos.core.command.converters</code>
 * 中，包括：BigDecimalConverter, BooleanConverter, TimeStampConverter,
 * TimeConverter, StringConverter, LongConverter, IntegerConverter,
 * FloatConverter, DoubleConverter, DateConverter, ByteArrayConverter。
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
	 * 获得<code>Converter</code>的名称。
	 * 
	 * @return 返回该<code>Converter</code>的名称。
	 */
	public String getName();

	/**
	 * 获得<code>Converter</code>的标签。
	 * 
	 * @return 返回该<code>Converter</code>的标签。
	 */
	public String getLabel();

	/**
	 * 获得<code>Converter</code>映射的jdbc类型。
	 * 
	 * @return 返回该<code>Converter</code>映射的jdbc类型。
	 */
	public Collection<Integer> getMappedJdbcTypes();

	/**
	 * 设置<code>Converter</code>映射的jdbc类型。
	 * 
	 * @param mappedJdbcTypes
	 *            jdbc类型。
	 */
	public void setMappedJdbcTypes(Collection<Integer> mappedJdbcTypes);

	/**
	 * 初始化一个<code>Converter</code>。
	 * 
	 * @param config
	 *            Cosmos框架的配置类。
	 * @param factory
	 *            Cosmos框架的工厂类。
	 */
	public void init(Configuration config, CosmosFactory factory);

	/**
	 * 根据已有信息将一个<code>String</code>的变量转换为对应的类型。
	 * 
	 * @param value
	 *            原始的<code>String</code>类型值。
	 * @return 返回对应类型的值。
	 */
	public Object convert(String value);

}
