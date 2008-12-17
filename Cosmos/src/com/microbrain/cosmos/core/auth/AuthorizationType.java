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
 * ��֤����б���Բ�ͬ����֤������в�ͬ�������
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
	 * ��֤�ɹ���
	 */
	SUCCEED(0),

	/**
	 * ϵͳ�ڲ�����
	 */
	SYSTEM_ERROR(-100),

	/**
	 * û������û���
	 */
	NO_THIS_USER(-200),

	/**
	 * ����Ϊ�ա�
	 */
	PASSWORD_IS_NULL(-300),

	/**
	 * �������
	 */
	PASSWORD_ERROR(-400);

	/**
	 * ����ֵ��
	 */
	private int value = -1;

	/**
	 * ���캯����
	 * 
	 * @param value
	 *            ����ֵ��
	 */
	private AuthorizationType(int value) {
		this.value = value;
	}

	/**
	 * ��ô���ֵ��
	 * 
	 * @return ����ֵ��
	 */
	public int getValue() {
		return this.value;
	}

}
