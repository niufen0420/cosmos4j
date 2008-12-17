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

/**
 * <p>
 * 命令执行中产生的各种异常。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see java.lang.Exception
 * @since CFDK 1.0
 */
public class CosmosExecuteException extends Exception {

	/**
	 * java中的serialVersionUID代表序列号。
	 */
	private static final long serialVersionUID = -6030544503390581011L;

	/**
	 * 空参数的构造函数。
	 */
	public CosmosExecuteException() {
	}

	/**
	 * 构造函数。
	 * 
	 * @param message
	 *            异常消息。
	 */
	public CosmosExecuteException(String message) {
		super(message);
	}

	/**
	 * 构造函数。
	 * 
	 * @param cause
	 *            异常原因。
	 */
	public CosmosExecuteException(Throwable cause) {
		super(cause);
	}

	/**
	 * 构造函数。
	 * 
	 * @param message
	 *            异常消息。
	 * @param cause
	 *            异常原因。
	 */
	public CosmosExecuteException(String message, Throwable cause) {
		super(message, cause);
	}

}
