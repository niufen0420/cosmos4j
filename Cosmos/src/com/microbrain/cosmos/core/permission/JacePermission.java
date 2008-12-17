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
 * <code>cosmos</code>Ȩ�޿�ܵĺ��ĳ����࣬�ṩ�ϲ�����Ȩ�޺��жϰ����ķ�����
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
	 * ����Ȩ�޵Ĵ�����ֵ��
	 */
	protected BigInteger code = BigInteger.ZERO;

	/**
	 * Ȩ��������Ķ�����
	 */
	protected StringBuilder actions;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public JacePermission() {
		super(null);
	}

	/**
	 * ���캯����
	 * 
	 * @param name
	 *            Ȩ�����ơ�
	 */
	public JacePermission(String name) {
		super(name);
	}

	/**
	 * ���캯����
	 * 
	 * @param name
	 *            Ȩ�����ơ�
	 * @param actions
	 *            Ȩ�޶�����
	 * @param code
	 *            Ȩ��ֵ��
	 */
	public JacePermission(String name, String actions, String code) {
		super(name);
		this.actions = new StringBuilder(actions);
		this.code = new BigInteger(code, 16);
	}

	/**
	 * ���л�ʱ�����кš�
	 */
	private static final long serialVersionUID = -8846262567623514126L;

	/**
	 * �ϲ�����Ȩ�ޡ�
	 * 
	 * @param permission
	 *            ���ϲ���Ȩ�ޡ�
	 * @return ���غϲ�֮���Ȩ�ޡ�
	 */
	public abstract JacePermission combine(JacePermission permission);

	/**
	 * ���Ȩ�޵�ֵ��
	 * 
	 * @return Ȩ��ֵ��
	 */
	public abstract BigInteger getCode();

}
