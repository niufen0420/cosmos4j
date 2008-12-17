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
 * �ڽ�����֤�����У����п��ܲ������쳣��
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
	 * ���л�ʱ�����кš�
	 */
	private static final long serialVersionUID = -1127383787304341337L;

	/**
	 * ��֤״̬��
	 */
	private int loginCode = -1;

	/**
	 * ���캯����
	 * 
	 * @param loginCode
	 *            ��֤״̬��
	 */
	public AuthorizationException(int loginCode) {
		super();
		this.loginCode = loginCode;
	}

	/**
	 * ���캯����
	 * 
	 * @param loginCode
	 *            ��֤״̬��
	 * @param message
	 *            �쳣��������Ϣ��
	 * @param cause
	 *            �쳣������ԭ��
	 */
	public AuthorizationException(int loginCode, String message, Throwable cause) {
		super(message, cause);
		this.loginCode = loginCode;
	}

	/**
	 * ���캯����
	 * 
	 * @param loginCode
	 *            ��֤״̬��
	 * @param message
	 *            �쳣��������Ϣ��
	 */
	public AuthorizationException(int loginCode, String message) {
		super(message);
		this.loginCode = loginCode;
	}

	/**
	 * ���캯����
	 * 
	 * @param loginCode
	 *            ��֤״̬��
	 * @param cause
	 *            �쳣������ԭ��
	 */
	public AuthorizationException(int loginCode, Throwable cause) {
		super(cause);
		this.loginCode = loginCode;
	}

	/**
	 * �����֤״̬��
	 * 
	 * @return ��֤״̬��
	 */
	public int getLoginCode() {
		return loginCode;
	}

	/**
	 * ������֤״̬��
	 * 
	 * @param loginCode
	 *            ��֤״̬��
	 */
	public void setLoginCode(int loginCode) {
		this.loginCode = loginCode;
	}

}
