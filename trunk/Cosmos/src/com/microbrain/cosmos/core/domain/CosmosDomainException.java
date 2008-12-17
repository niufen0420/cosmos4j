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
package com.microbrain.cosmos.core.domain;

/**
 * <p>
 * 执行一个域相关的操作时，可能会存在某些异常，这时通常抛出本异常。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see java.lang.Exception
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @since CFDK 1.0
 */
public class CosmosDomainException extends Exception {

	/**
	 * 序列化时的序列号。
	 */
	private static final long serialVersionUID = -4801154008717311227L;

	/**
	 * 默认构造函数。
	 */
	public CosmosDomainException() {
	}

	/**
	 * 构造函数。
	 * 
	 * @param message
	 *            异常时产生的消息。
	 */
	public CosmosDomainException(String message) {
		super(message);
	}

	/**
	 * 构造函数。
	 * 
	 * @param cause
	 *            引发异常的原因。
	 */
	public CosmosDomainException(Throwable cause) {
		super(cause);
	}

	/**
	 * 构造函数。
	 * 
	 * @param message
	 *            异常时产生的消息。
	 * @param cause
	 *            引发异常的原因。
	 */
	public CosmosDomainException(String message, Throwable cause) {
		super(message, cause);
	}

}
