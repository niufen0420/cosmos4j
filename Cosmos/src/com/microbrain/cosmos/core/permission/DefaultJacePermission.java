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
 * ��Ĭ��ʵ�֣��ṩͨ��λ�����¼Ȩ�޵��㷨��ÿһλ������һ��Ȩ�ޣ���λ���Ϊ1��������Ȩ�ޣ����Ϊ0�������û��Ȩ�ޡ�
 * </p>
 * <p>
 * Ȩ�޵Ĵ���<code>code</code>��һ��������<code>java.math.BigInteger</code>
 * ����ʾ��ͨ������������Ӧ���������Ȩ�޵���Ӧҵ���߼���
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
	 * ���л�ʱ�����кš�
	 */
	private static final long serialVersionUID = -3863652913311704801L;

	/**
	 * ����û���κ�Ȩ�޵�Ȩ�ޡ�
	 */
	public static final JacePermission NULL = new DefaultJacePermission();

	/**
	 * Ĭ�Ϲ��캯����˽�л����ⲿ�޷����졣
	 */
	private DefaultJacePermission() {
		super();
	}

	/**
	 * ���캯����
	 * 
	 * @param name
	 *            Ȩ�����ơ�
	 */
	public DefaultJacePermission(String name) {
		super(name);
	}

	/**
	 * ���캯����
	 * 
	 * @param name
	 *            Ȩ�����ơ�
	 * @param actions
	 *            Ȩ�޴���Ķ�����
	 * @param code
	 *            Ȩ��ֵ��
	 */
	public DefaultJacePermission(String name, String actions, String code) {
		super(name, actions, code);
	}

	/*
	 * ���Ȩ��ֵ��
	 * 
	 * @see com.microbrain.cosmos.core.permission.JacePermission#getCode()
	 */
	@Override
	public BigInteger getCode() {
		return code;
	}

	/**
	 * ����Ȩ��ֵ��
	 * 
	 * @param code
	 *            Ȩ��ֵ��
	 */
	public void setCode(BigInteger code) {
		this.code = code;
	}

	/*
	 * ���ص��ڷ�����
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
	 * ���Ȩ�޴�������ж�����
	 * 
	 * @see java.security.Permission#getActions()
	 */
	@Override
	public String getActions() {
		return this.actions.toString();
	}

	/*
	 * ����Ȩ�޵�ɢ��ֵ��
	 * 
	 * @see java.security.Permission#hashCode()
	 */
	@Override
	public int hashCode() {
		return (getName() == null ? 0 : getName().hashCode()) * 7
				+ code.hashCode() * 23;
	}

	/*
	 * Ȩ�ް����������ж�һ��Ȩ���Ƿ񰵺�����һ��Ȩ�ޡ�
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
	 * Ȩ����Ϸ�����������Ȩ�޺ϲ�Ϊһ��Ȩ�ޡ�
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