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
 * 认证信息类，经过认证之后的用户，可以获得一个本类的实例，其中记录了认证者的ID，认证者经过认证的服务。
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
	 * 序列化时的序列号。
	 */
	private static final long serialVersionUID = -7201201379982443172L;

	/**
	 * 认证者ID。
	 */
	protected String passportId = null;

	/**
	 * 认证的服务。
	 */
	protected String service = null;

	/**
	 * 构造函数。
	 * 
	 * @param passportId
	 *            认证者ID。
	 * @param service
	 *            认证的服务。
	 */
	public Authorization(String passportId, String service) {
		this.passportId = passportId;
		this.service = service;
	}

	/**
	 * 默认构造函数。
	 */
	public Authorization() {
	}

	/**
	 * 判断一个认证信息是否为匿名信息。
	 * 
	 * @return 是否匿名。
	 */
	public boolean isAnonymous() {
		return passportId == null;
	}

	/**
	 * 获得认证者ID。
	 * 
	 * @return 认证者ID。
	 */
	public String getPassportId() {
		return passportId;
	}

	/**
	 * 设置认证者ID。
	 * 
	 * @param passportId
	 *            认证者ID。
	 */
	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}

	/**
	 * 获得认证的服务。
	 * 
	 * @return 认证的服务。
	 */
	public String getService() {
		return service;
	}

	/**
	 * 设置认证的服务。
	 * 
	 * @param service
	 *            认证的服务。
	 */
	public void setService(String service) {
		this.service = service;
	}

}
