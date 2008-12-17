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
package com.microbrain.cosmos.core;

/**
 * 使用CosmosFactory类的对应方法时抛出的异常。
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.CosmosFactory
 * @since CFDK 1.0
 */
public class CosmosFactoryException extends Exception {

	/**
	 * java中的serialVersionUID代表序列号。
	 */
	private static final long serialVersionUID = 1643203169699955061L;

	/**
	 * 空参数的构造函数。
	 */
	public CosmosFactoryException() {
	}

	/**
	 * 构造函数。
	 * 
	 * @param message
	 *            异常消息。
	 * @param cause
	 *            异常原因。
	 */
	public CosmosFactoryException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 构造函数。
	 * 
	 * @param message
	 *            异常消息。
	 */
	public CosmosFactoryException(String message) {
		super(message);
	}

	/**
	 * 构造函数。
	 * 
	 * @param cause
	 *            异常原因。
	 */
	public CosmosFactoryException(Throwable cause) {
		super(cause);
	}

}
