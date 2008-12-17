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
 * ִ��һ������صĲ���ʱ�����ܻ����ĳЩ�쳣����ʱͨ���׳����쳣��
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
	 * ���л�ʱ�����кš�
	 */
	private static final long serialVersionUID = -4801154008717311227L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public CosmosDomainException() {
	}

	/**
	 * ���캯����
	 * 
	 * @param message
	 *            �쳣ʱ��������Ϣ��
	 */
	public CosmosDomainException(String message) {
		super(message);
	}

	/**
	 * ���캯����
	 * 
	 * @param cause
	 *            �����쳣��ԭ��
	 */
	public CosmosDomainException(Throwable cause) {
		super(cause);
	}

	/**
	 * ���캯����
	 * 
	 * @param message
	 *            �쳣ʱ��������Ϣ��
	 * @param cause
	 *            �����쳣��ԭ��
	 */
	public CosmosDomainException(String message, Throwable cause) {
		super(message, cause);
	}

}
