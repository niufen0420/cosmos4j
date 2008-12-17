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
 * 实现了<code>CosmosArgumentConverter</code>接口的抽象类，提供了访问
 * <code>CosmosArgumentConverter</code>的名称和标签的方法。并将类型转换与底层的数据库类型
 * <code>java.sql.Types</code>关联起来。
 * </p>
 * <p>
 * 所有具体的<code>Converter</code>类都需要从本抽象类继承下来，系统目前内置的<code>Converter</code>类都位于包
 * <code>com.microbrain.cosmos.core.command.converters</code>
 * 中，包括：BigDecimalConverter, BooleanConverter, TimeStampConverter,
 * TimeConverter, StringConverter, LongConverter, IntegerConverter,
 * FloatConverter, DoubleConverter, DateConverter, ByteArrayConverter。
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
	 * 类型转换器名称。
	 */
	protected String name = null;

	/**
	 * 类型转换器标签。
	 */
	protected String label = null;

	/**
	 * 类型转换器映射的jdbc类型。
	 */
	protected Collection<Integer> mappedJdbcTypes = null;

	/**
	 * 构造函数，在初始化CosmosArgumentConverter时被调用。
	 * 
	 * @param name
	 *            转换器名称。
	 * @param label
	 *            转换器标签。
	 */
	public AbstractCosmosArgumentConverter(String name, String label) {
		this.name = name;
		this.label = label;
	}

	/*
	 * 获得类型转换器的标签。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosArgumentConverter#getLabel()
	 */
	public String getLabel() {
		return this.label;
	}

	/*
	 * 获得类型转换器映射的jdbc类型。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosArgumentConverter#getMappedJdbcTypes
	 * ()
	 */
	public Collection<Integer> getMappedJdbcTypes() {
		return this.mappedJdbcTypes;
	}

	/*
	 * 设置类型转换器映射的jdbc类型。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosArgumentConverter#setMappedJdbcTypes
	 * (java.util.Collection)
	 */
	public void setMappedJdbcTypes(Collection<Integer> mappedJdbcTypes) {
		this.mappedJdbcTypes = mappedJdbcTypes;
	}

	/*
	 * 获得类型转换器的名称。
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosArgumentConverter#getName()
	 */
	public String getName() {
		return this.name;
	}

}
