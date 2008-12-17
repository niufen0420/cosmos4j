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
package com.microbrain.cosmos.core.auth;

/**
 * <p>
 * 在进行认证过程中，所有可能产生的异常。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see java.lang.Exception
 * @see com.microbrain.cosmos.core.auth.AuthorizationFactory
 * @see com.microbrain.cosmos.core.auth.Authorization
 * @since CFDK 1.0
 */
public class AuthorizationException extends Exception {

	/**
	 * 序列化时的序列号。
	 */
	private static final long serialVersionUID = -1127383787304341337L;

	/**
	 * 认证状态。
	 */
	private int loginCode = -1;

	/**
	 * 构造函数。
	 * 
	 * @param loginCode
	 *            认证状态。
	 */
	public AuthorizationException(int loginCode) {
		super();
		this.loginCode = loginCode;
	}

	/**
	 * 构造函数。
	 * 
	 * @param loginCode
	 *            认证状态。
	 * @param message
	 *            异常发生的消息。
	 * @param cause
	 *            异常引发的原因。
	 */
	public AuthorizationException(int loginCode, String message, Throwable cause) {
		super(message, cause);
		this.loginCode = loginCode;
	}

	/**
	 * 构造函数。
	 * 
	 * @param loginCode
	 *            认证状态。
	 * @param message
	 *            异常发生的消息。
	 */
	public AuthorizationException(int loginCode, String message) {
		super(message);
		this.loginCode = loginCode;
	}

	/**
	 * 构造函数。
	 * 
	 * @param loginCode
	 *            认证状态。
	 * @param cause
	 *            异常引发的原因。
	 */
	public AuthorizationException(int loginCode, Throwable cause) {
		super(cause);
		this.loginCode = loginCode;
	}

	/**
	 * 获得认证状态。
	 * 
	 * @return 认证状态。
	 */
	public int getLoginCode() {
		return loginCode;
	}

	/**
	 * 设置认证状态。
	 * 
	 * @param loginCode
	 *            认证状态。
	 */
	public void setLoginCode(int loginCode) {
		this.loginCode = loginCode;
	}

}
