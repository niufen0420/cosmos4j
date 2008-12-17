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
package com.microbrain.cosmos.web.el.permission;

import java.lang.reflect.Method;

import com.microbrain.cosmos.core.auth.Authorization;
import com.microbrain.cosmos.core.permission.AccessController;

/**
 * <p>
 * 提供的EL方法，以方便开发人员灵活地管理权限。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.permission.AccessController
 * @see com.microbrain.cosmos.core.permission.PermissionFactory
 * @see com.microbrain.cosmos.core.permission.JacePermission
 * @since CFDK 1.0
 */
public class PermissionUtil {

	/**
	 * 私有化构造函数，确认类内部全部为静态方法。
	 */
	private PermissionUtil() {
	}

	/**
	 * 检查是否对某个对象有某种操作的权限。
	 * 
	 * @param auth
	 *            认证对象。
	 * @param object
	 *            要操作的对象。
	 * @param operation
	 *            要执行的操作。
	 * @return 返回是否可以进行该操作。
	 */
	public static Boolean check(Authorization auth, String object,
			String operation) {
		return AccessController.checkPermission(auth, object, operation);
	}

	/**
	 * 检查是否有对某个方法的操作权限。
	 * 
	 * @param auth
	 *            认证对象。
	 * @param method
	 *            要执行的方法。
	 * @return 返回是否可以进行该操作。
	 */
	public static Boolean check(Authorization auth, Method method) {
		return AccessController.checkPermission(auth, method);
	}

}
