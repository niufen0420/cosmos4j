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
package com.microbrain.cosmos.web.models;

/**
 * <p>
 * 登录所需要的模型类，增加了登录状态的属性。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.web.models.BaseModel
 * @see com.microbrain.cosmos.core.command.CosmosResult
 * @since CFDK 1.0
 */
public class LoginModel extends BaseModel {

	/**
	 * 登录状态。
	 */
	private int loginStatus = -1;

	/**
	 * 获得登录状态。
	 * 
	 * @return 登录状态。
	 */
	public int getLoginStatus() {
		return loginStatus;
	}

	/**
	 * 设置登录状态。
	 * 
	 * @param loginStatus
	 *            登录状态。
	 */
	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}

}
