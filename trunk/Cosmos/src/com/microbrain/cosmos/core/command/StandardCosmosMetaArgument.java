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

import com.microbrain.cosmos.core.constants.ArgumentInOutType;

/**
 * <p>
 * <code>CosmosMetaArgument</code>的标准实现。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @see com.microbrain.cosmos.core.command.CosmosMetaArgument
 * @since CFDK 1.0
 */
public class StandardCosmosMetaArgument implements CosmosMetaArgument {

	/**
	 * 参数类型转换器。
	 */
	private CosmosArgumentConverter converter = null;

	/**
	 * 参数类型名称。
	 */
	private String name = null;

	/**
	 * 参数IN/OUT类型。
	 */
	private ArgumentInOutType inOutType = null;

	/**
	 * 参数类型注释。
	 */
	private String remark = null;

	/**
	 * 构造函数。
	 * 
	 * @param converter
	 *            类型转换器。
	 * @param name
	 *            类型名称。
	 * @param inOutType
	 *            IN/OUT类型。
	 * @param remark
	 *            注释。
	 */
	public StandardCosmosMetaArgument(CosmosArgumentConverter converter,
			String name, ArgumentInOutType inOutType, String remark) {
		this.converter = converter;
		this.name = name;
		this.inOutType = inOutType;
		this.remark = remark;
	}

	/*
	 * 获得类型转换器。
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosMetaArgument#getConverter()
	 */
	public CosmosArgumentConverter getConverter() {
		return this.converter;
	}

	/*
	 * 获得类型名称。
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosMetaArgument#getName()
	 */
	public String getName() {
		return this.name;
	}

	/*
	 * 获得IN/OUT类型。
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosMetaArgument#getInOutType()
	 */
	public ArgumentInOutType getInOutType() {
		return this.inOutType;
	}

	/*
	 * 获得注释。
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosMetaArgument#getRemark()
	 */
	public String getRemark() {
		return remark;
	}

}
