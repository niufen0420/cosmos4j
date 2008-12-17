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
 * 调试级别分类，分别是致命的，错误的，警告的，信息的，调试的，跟踪的，无调试的。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.config.Configuration
 * @since CFDK 1.0
 */
public enum DebugLevel {

	/**
	 * 致命的。
	 */
	FATAL,

	/**
	 * 错误的。
	 */
	ERROR,

	/**
	 * 警告的。
	 */
	WARN,

	/**
	 * 信息的。
	 */
	INFO,

	/**
	 * 调试的。
	 */
	DEBUG,

	/**
	 * 跟踪的。
	 */
	TRACE,

	/**
	 * 无调试的。
	 */
	NO_DEBUG

}
