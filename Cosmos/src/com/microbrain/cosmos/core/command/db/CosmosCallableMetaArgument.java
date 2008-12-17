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
package com.microbrain.cosmos.core.command.db;

import com.microbrain.cosmos.core.command.CosmosArgumentConverter;
import com.microbrain.cosmos.core.command.StandardCosmosMetaArgument;
import com.microbrain.cosmos.core.constants.ArgumentInOutType;

/**
 * <p>
 * 存储过程命令的参数描述类，扩展自普通的参数描述类，增加了描述参数类型的sqlType属性。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosMetaArgument
 * @see com.microbrain.cosmos.core.command.StandardCosmosMetaArgument
 * @since CFDK 1.0
 */
public class CosmosCallableMetaArgument extends StandardCosmosMetaArgument {

	/**
	 * sql类型，即：参数类型。
	 */
	private int sqlType = -1;

	/**
	 * 构造函数。
	 * 
	 * @param converter
	 *            类型转换器。
	 * @param name
	 *            参数名称。
	 * @param inOutType
	 *            参数IN/OUT类型。
	 * @param remark
	 *            参数说明。
	 * @param sqlType
	 *            参数sql类型。
	 */
	public CosmosCallableMetaArgument(CosmosArgumentConverter converter,
			String name, ArgumentInOutType inOutType, String remark, int sqlType) {
		super(converter, name, inOutType, remark);
		this.sqlType = sqlType;
	}

	/**
	 * 获得参数的sql类型。
	 * 
	 * @return 参数的sql类型。
	 */
	public int getSqlType() {
		return sqlType;
	}

}
