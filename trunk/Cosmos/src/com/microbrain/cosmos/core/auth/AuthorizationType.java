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
 * 认证情况列表，针对不同的认证结果，有不同的情况。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.auth.AuthorizationFactory
 * @see com.microbrain.cosmos.core.auth.Authorization
 * @since CFDK 1.0
 */
public enum AuthorizationType {

	/**
	 * 认证成功。
	 */
	SUCCEED(0),

	/**
	 * 系统内部错误。
	 */
	SYSTEM_ERROR(-100),

	/**
	 * 没有这个用户。
	 */
	NO_THIS_USER(-200),

	/**
	 * 密码为空。
	 */
	PASSWORD_IS_NULL(-300),

	/**
	 * 密码错误。
	 */
	PASSWORD_ERROR(-400);

	/**
	 * 代码值。
	 */
	private int value = -1;

	/**
	 * 构造函数。
	 * 
	 * @param value
	 *            代码值。
	 */
	private AuthorizationType(int value) {
		this.value = value;
	}

	/**
	 * 获得代码值。
	 * 
	 * @return 代码值。
	 */
	public int getValue() {
		return this.value;
	}

}
