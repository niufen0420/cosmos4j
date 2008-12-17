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

import java.io.Serializable;

/**
 * <p>
 * ��֤��Ϣ�࣬������֤֮����û������Ի��һ�������ʵ�������м�¼����֤�ߵ�ID����֤�߾�����֤�ķ���
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see java.io.Serializable
 * @see com.microbrain.cosmos.core.auth.AuthorizationFactory
 * @see com.microbrain.cosmos.core.auth.AuthorizationFactoryImpl
 * @since CFDK 1.0
 */
public class Authorization implements Serializable {

	/**
	 * ���л�ʱ�����кš�
	 */
	private static final long serialVersionUID = -7201201379982443172L;

	/**
	 * ��֤��ID��
	 */
	protected String passportId = null;

	/**
	 * ��֤�ķ���
	 */
	protected String service = null;

	/**
	 * ���캯����
	 * 
	 * @param passportId
	 *            ��֤��ID��
	 * @param service
	 *            ��֤�ķ���
	 */
	public Authorization(String passportId, String service) {
		this.passportId = passportId;
		this.service = service;
	}

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public Authorization() {
	}

	/**
	 * �ж�һ����֤��Ϣ�Ƿ�Ϊ������Ϣ��
	 * 
	 * @return �Ƿ�������
	 */
	public boolean isAnonymous() {
		return passportId == null;
	}

	/**
	 * �����֤��ID��
	 * 
	 * @return ��֤��ID��
	 */
	public String getPassportId() {
		return passportId;
	}

	/**
	 * ������֤��ID��
	 * 
	 * @param passportId
	 *            ��֤��ID��
	 */
	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}

	/**
	 * �����֤�ķ���
	 * 
	 * @return ��֤�ķ���
	 */
	public String getService() {
		return service;
	}

	/**
	 * ������֤�ķ���
	 * 
	 * @param service
	 *            ��֤�ķ���
	 */
	public void setService(String service) {
		this.service = service;
	}

}
