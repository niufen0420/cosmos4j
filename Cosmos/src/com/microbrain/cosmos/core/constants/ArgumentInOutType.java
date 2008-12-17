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
package com.microbrain.cosmos.core.constants;

/**
 * <p>
 * 描述参数的IN/OUT类型，其中包括：IN，OUT，INOUT。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosMetaArgument
 * @since CFDK 1.0
 */
public enum ArgumentInOutType {

	/**
	 * 入参数。
	 */
	IN,

	/**
	 * 出参数。
	 */
	OUT,

	/**
	 * 既是入参数，也是出参数。
	 */
	INOUT;

}
