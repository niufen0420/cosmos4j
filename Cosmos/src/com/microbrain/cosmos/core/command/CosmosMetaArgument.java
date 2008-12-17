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
 * 参数描述类，用来对一个命令的参数进行描述，并针对可能需要的类型转换产生其对应的类型转换器。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @see com.microbrain.cosmos.core.command.StandardCosmosMetaArgument
 * @since CFDK 1.0
 */
public interface CosmosMetaArgument {

	/**
	 * 获得参数名称。
	 * 
	 * @return 参数名称。
	 */
	public String getName();

	/**
	 * 获得参数的IN/OUT类型。
	 * 
	 * @return 参数的IN/OUT类型。
	 */
	public ArgumentInOutType getInOutType();

	/**
	 * 获得参数的类型转换器。
	 * 
	 * @return 参数的类型转换器。
	 */
	public CosmosArgumentConverter getConverter();

	/**
	 * 获得参数的注释。
	 * 
	 * @return 参数的注释。
	 */
	public String getRemark();

}
