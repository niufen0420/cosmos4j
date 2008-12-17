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
package com.microbrain.cosmos.dev;

/**
 * <p>
 * 执行管理命令的相关操作时抛出的异常。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see java.lang.Exception
 * @see com.microbrain.cosmos.dev.CosmosCommandManager
 * @since CFDK 1.0
 */
public class CosmosCommandManagerException extends Exception {

	/**
	 * 序列化时的序列号。
	 */
	private static final long serialVersionUID = -7458322264055512004L;

	/**
	 * 默认的构造函数。
	 */
	public CosmosCommandManagerException() {
	}

	/**
	 * 构造函数。
	 * 
	 * @param message
	 *            异常发生的消息。
	 */
	public CosmosCommandManagerException(String message) {
		super(message);
	}

	/**
	 * 构造函数。
	 * 
	 * @param cause
	 *            异常引起的原因。
	 */
	public CosmosCommandManagerException(Throwable cause) {
		super(cause);
	}

	/**
	 * 构造函数。
	 * 
	 * @param message
	 *            异常发生的消息。
	 * @param cause
	 *            异常引起的原因。
	 */
	public CosmosCommandManagerException(String message, Throwable cause) {
		super(message, cause);
	}

}
