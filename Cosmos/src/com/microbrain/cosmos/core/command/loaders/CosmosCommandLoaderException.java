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
package com.microbrain.cosmos.core.command.loaders;

/**
 * <p>
 * �ڸ�������װ������������������װ������ȫ������װ��������װ��ĳ���������ĳЩ��������쳣ʱ���׳����쳣��
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see java.lang.Exception
 * @see com.microbrain.cosmos.core.command.loaders.CosmosGlobalCommandLoader
 * @see com.microbrain.cosmos.core.command.loaders.CosmosLocalCommandLoader
 * @since CFDK 1.0
 */
public class CosmosCommandLoaderException extends Exception {

	/**
	 * �����л������кš�
	 */
	private static final long serialVersionUID = -7834224423175407902L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public CosmosCommandLoaderException() {
	}

	/**
	 * ���캯����
	 * 
	 * @param message
	 *            �쳣��Ϣ��
	 */
	public CosmosCommandLoaderException(String message) {
		super(message);
	}

	/**
	 * ���캯����
	 * 
	 * @param cause
	 *            �쳣�����ԭ��
	 */
	public CosmosCommandLoaderException(Throwable cause) {
		super(cause);
	}

	/**
	 * ���캯����
	 * 
	 * @param message
	 *            �쳣��Ϣ��
	 * @param cause
	 *            �쳣�����ԭ��
	 */
	public CosmosCommandLoaderException(String message, Throwable cause) {
		super(message, cause);
	}

}
