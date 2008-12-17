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
package com.microbrain.cosmos.core.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>
 * 对common-logging的一个简单封装，让整个环境在运行时仅存在唯一一个记录日志的实例。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see org.apache.commons.logging.LogFactory
 * @see org.apache.commons.logging.Log
 * @since CFDK 1.0
 */
public class CosmosLogFactory {

	/**
	 * 日志记录实例。
	 */
	private static final Log log = LogFactory.getLog(CosmosLogFactory.class);

	/**
	 * 私有化构造函数。
	 */
	private CosmosLogFactory() {
	}

	/**
	 * 获得生成的日志记录实例。
	 * 
	 * @return 日志记录实例。
	 */
	public static Log getLog() {
		return log;
	}

}
