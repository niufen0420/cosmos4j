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
package com.microbrain.cosmos.web.tags.permission;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.microbrain.cosmos.core.auth.Authorization;
import com.microbrain.cosmos.core.constants.Constants;
import com.microbrain.cosmos.core.permission.AccessController;

/**
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see javax.servlet.jsp.tagext.TagSupport
 * @see com.microbrain.cosmos.core.permission.AccessController
 * @see com.microbrain.cosmos.core.auth.Authorization
 * @since CFDK 1.0
 */
public class PermissionTag extends TagSupport {

	/**
	 * 序列化时的序列号。
	 */
	private static final long serialVersionUID = -2721244617982045316L;

	/**
	 * 权限控制的对象。
	 */
	private String object = null;

	/**
	 * 权限控制的操作。
	 */
	private String operation = null;

	/**
	 * 权限号。
	 */
	private int permission = 0;

	/*
	 * 标签开始。
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) this.pageContext
				.getRequest();
		Authorization auth = (Authorization) request
				.getAttribute(Constants.AUTHORIZATION_TOKEN);

		if (auth == null) {
			auth = (Authorization) request.getSession().getAttribute(
					Constants.AUTHORIZATION_TOKEN);
		}

		if (auth == null || auth.isAnonymous()) {
			return SKIP_BODY;
		}

		if (AccessController.checkPermission(auth, object, operation)) {
			return EVAL_BODY_INCLUDE;
		}

		return SKIP_BODY;
	}

	/**
	 * 获得权限控制的对象。
	 * 
	 * @return 权限控制的对象。
	 */
	public String getObject() {
		return object;
	}

	/**
	 * 设置权限控制的对象。
	 * 
	 * @param object
	 *            权限控制的对象。
	 */
	public void setObject(String object) {
		this.object = object;
	}

	/**
	 * 获得权限控制的操作。
	 * 
	 * @return 权限控制的操作。
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * 设置权限控制的操作。
	 * 
	 * @param operation
	 *            权限控制的操作。
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * 获得权限号。
	 * 
	 * @return 权限号。
	 */
	public String getPermission() {
		return String.valueOf(permission);
	}

	/**
	 * 设置权限号。
	 * 
	 * @param permission
	 *            权限号。
	 */
	public void setPermission(String permission) {
		this.permission = Integer.parseInt(permission);
	}

}
