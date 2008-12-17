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

/**
 * <p>
 * ����Ȩ��У�飬����Ȩ�޵���ز���ʱ�����ܳ��ֵ��쳣��
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.permission.PermissionFactory
 * @see java.lang.Exception
 * @since CFDK 1.0
 */
public class CosmosPermissionException extends Exception {

	/**
	 * ���л�ʱ�����кš�
	 */
	private static final long serialVersionUID = -4266179082214688027L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public CosmosPermissionException() {
	}

	/**
	 * ���캯����
	 * 
	 * @param message
	 *            �쳣��������Ϣ��
	 */
	public CosmosPermissionException(String message) {
		super(message);
	}

	/**
	 * ���캯����
	 * 
	 * @param cause
	 *            �쳣������ԭ��
	 */
	public CosmosPermissionException(Throwable cause) {
		super(cause);
	}

	/**
	 * ���캯����
	 * 
	 * @param message
	 *            �쳣��������Ϣ��
	 * @param cause
	 *            �쳣������ԭ��
	 */
	public CosmosPermissionException(String message, Throwable cause) {
		super(message, cause);
	}

}
