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
package com.microbrain.cosmos.core.permission;

import java.math.BigInteger;
import java.security.Permission;

/**
 * <p>
 * <code>cosmos</code>权限框架的核心抽象类，提供合并两个权限和判断暗含的方法。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see java.security.Permission
 * @see com.microbrain.cosmos.core.permission.DefaultJacePermission
 * @since CFDK 1.0
 */
public abstract class JacePermission extends Permission {

	/**
	 * 代表权限的大整数值。
	 */
	protected BigInteger code = BigInteger.ZERO;

	/**
	 * 权限所代表的动作。
	 */
	protected StringBuilder actions;

	/**
	 * 默认构造函数。
	 */
	public JacePermission() {
		super(null);
	}

	/**
	 * 构造函数。
	 * 
	 * @param name
	 *            权限名称。
	 */
	public JacePermission(String name) {
		super(name);
	}

	/**
	 * 构造函数。
	 * 
	 * @param name
	 *            权限名称。
	 * @param actions
	 *            权限动作。
	 * @param code
	 *            权限值。
	 */
	public JacePermission(String name, String actions, String code) {
		super(name);
		this.actions = new StringBuilder(actions);
		this.code = new BigInteger(code, 16);
	}

	/**
	 * 序列化时的序列号。
	 */
	private static final long serialVersionUID = -8846262567623514126L;

	/**
	 * 合并两个权限。
	 * 
	 * @param permission
	 *            待合并的权限。
	 * @return 返回合并之后的权限。
	 */
	public abstract JacePermission combine(JacePermission permission);

	/**
	 * 获得权限的值。
	 * 
	 * @return 权限值。
	 */
	public abstract BigInteger getCode();

}
