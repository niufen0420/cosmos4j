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
package com.microbrain.cosmos.core.sal;

/**
 * <p>
 * 执行某个接口中的方法时抛出的异常。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.sal.ServiceObjectManager
 * @see com.microbrain.cosmos.core.sal.ServiceDelegate
 * @see com.microbrain.cosmos.core.sal.annotation.Command
 * @see com.microbrain.cosmos.core.sal.annotation.Domain
 * @since CFDK 1.0
 */
public class ServiceException extends Exception {

	/**
	 * 序列化时的序列号。
	 */
	private static final long serialVersionUID = -3538364776595018063L;

	/**
	 * 默认构造函数。
	 */
	public ServiceException() {
	}

	/**
	 * 构造函数。
	 * 
	 * @param message
	 *            异常发生的消息。
	 */
	public ServiceException(String message) {
		super(message);
	}

	/**
	 * 构造函数。
	 * 
	 * @param cause
	 *            异常发生引起的原因。
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}

	/**
	 * 构造函数。
	 * 
	 * @param message
	 *            异常发生的消息。
	 * @param cause
	 *            异常发生引起的原因。
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
