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
 * <code>com.microbrain.cosmos.core.permission.JacePermission</code>
 * 的默认实现，提供通过位运算记录权限的算法，每一位都代表一个权限，该位如果为1，代表有权限，如果为0，则代表没有权限。
 * </p>
 * <p>
 * 权限的代码<code>code</code>用一个大整数<code>java.math.BigInteger</code>
 * 来表示，通过大整数的相应运算来完成权限的相应业务逻辑。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.permission.JacePermission
 * @see java.security.Permission
 * @since CFDK 1.0
 */
public class DefaultJacePermission extends JacePermission {

	/**
	 * 序列化时的序列号。
	 */
	private static final long serialVersionUID = -3863652913311704801L;

	/**
	 * 代表没有任何权限的权限。
	 */
	public static final JacePermission NULL = new DefaultJacePermission();

	/**
	 * 默认构造函数，私有化，外部无法构造。
	 */
	private DefaultJacePermission() {
		super();
	}

	/**
	 * 构造函数。
	 * 
	 * @param name
	 *            权限名称。
	 */
	public DefaultJacePermission(String name) {
		super(name);
	}

	/**
	 * 构造函数。
	 * 
	 * @param name
	 *            权限名称。
	 * @param actions
	 *            权限代表的动作。
	 * @param code
	 *            权限值。
	 */
	public DefaultJacePermission(String name, String actions, String code) {
		super(name, actions, code);
	}

	/*
	 * 获得权限值。
	 * 
	 * @see com.microbrain.cosmos.core.permission.JacePermission#getCode()
	 */
	@Override
	public BigInteger getCode() {
		return code;
	}

	/**
	 * 设置权限值。
	 * 
	 * @param code
	 *            权限值。
	 */
	public void setCode(BigInteger code) {
		this.code = code;
	}

	/*
	 * 重载等于方法。
	 * 
	 * @see java.security.Permission#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JacePermission)) {
			return false;
		}

		if (hashCode() != obj.hashCode()) {
			return false;
		}

		JacePermission permission = (JacePermission) obj;
		if (permission.code.equals(code)) {
			return true;
		}

		return false;
	}

	/*
	 * 获得权限代表的所有动作。
	 * 
	 * @see java.security.Permission#getActions()
	 */
	@Override
	public String getActions() {
		return this.actions.toString();
	}

	/*
	 * 重载权限的散列值。
	 * 
	 * @see java.security.Permission#hashCode()
	 */
	@Override
	public int hashCode() {
		return (getName() == null ? 0 : getName().hashCode()) * 7
				+ code.hashCode() * 23;
	}

	/*
	 * 权限暗含操作，判断一个权限是否暗含另外一个权限。
	 * 
	 * @see java.security.Permission#implies(java.security.Permission)
	 */
	@Override
	public boolean implies(Permission permission) {
		if (permission == null) {
			return false;
		}

		if (!(permission instanceof JacePermission)) {
			return false;
		}

		JacePermission other = (JacePermission) permission;
		if (BigInteger.ZERO.equals(other.code)) {
			return true;
		}

		if (BigInteger.ZERO.equals(other.getCode().and(this.code))) {
			return false;
		}

		return true;
	}

	/*
	 * 权限组合方法，将两个权限合并为一个权限。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.permission.JacePermission#combine(com.microbrain
	 * .cosmos.core.permission.JacePermission)
	 */
	@Override
	public JacePermission combine(JacePermission permission) {
		this.code = this.code.or(permission.getCode());

		if (this.actions != null && this.actions.length() > 0) {
			this.actions.append(", ").append(permission.getActions());
		} else {
			this.actions = new StringBuilder(permission.getActions());
		}

		return this;
	}

}