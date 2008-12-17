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
package com.microbrain.cosmos.core.command.loaders;

/**
 * <p>
 * 在各种命令装载器，包括本地命令装载器和全局命令装载器，在装载某个命令，或者某些命令出现异常时，抛出本异常。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see java.lang.Exception
 * @see com.microbrain.cosmos.core.command.loaders.CosmosGlobalCommandLoader
 * @see com.microbrain.cosmos.core.command.loaders.CosmosLocalCommandLoader
 * @since CFDK 1.0
 */
public class CosmosCommandLoaderException extends Exception {

	/**
	 * 可序列化的序列号。
	 */
	private static final long serialVersionUID = -7834224423175407902L;

	/**
	 * 默认构造函数。
	 */
	public CosmosCommandLoaderException() {
	}

	/**
	 * 构造函数。
	 * 
	 * @param message
	 *            异常消息。
	 */
	public CosmosCommandLoaderException(String message) {
		super(message);
	}

	/**
	 * 构造函数。
	 * 
	 * @param cause
	 *            异常引起的原因。
	 */
	public CosmosCommandLoaderException(Throwable cause) {
		super(cause);
	}

	/**
	 * 构造函数。
	 * 
	 * @param message
	 *            异常消息。
	 * @param cause
	 *            异常引起的原因。
	 */
	public CosmosCommandLoaderException(String message, Throwable cause) {
		super(message, cause);
	}

}
