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
package com.microbrain.cosmos.core.config;

/**
 * <p>
 * ���������ļ�ʱ�׳����쳣��
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.config.Configuration
 * @since CFDK 1.0
 */
public class ConfigurationException extends Exception {

	/**
	 * ���л�ʱ�����кš�
	 */
	private static final long serialVersionUID = 8723131949271720418L;

	/**
	 * Ĭ�ϵĹ��캯����
	 */
	public ConfigurationException() {
	}

	/**
	 * ���캯����
	 * 
	 * @param message
	 *            ��Ϣ��
	 */
	public ConfigurationException(String message) {
		super(message);
	}

	/**
	 * ���캯����
	 * 
	 * @param cause
	 *            ԭ��
	 */
	public ConfigurationException(Throwable cause) {
		super(cause);
	}

	/**
	 * ���캯����
	 * 
	 * @param message
	 *            ��Ϣ��
	 * @param cause
	 *            ԭ��
	 */
	public ConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

}
