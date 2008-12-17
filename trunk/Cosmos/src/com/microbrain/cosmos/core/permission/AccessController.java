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

import java.lang.reflect.Method;
import java.math.BigInteger;

import com.microbrain.cosmos.core.auth.Authorization;

/**
 * <p>
 * 权限控制器，通过调用底层提供的方法来检验一个经过认证的身份是否有权限来执行某个命令。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.permission.PermissionFactory
 * @see com.microbrain.cosmos.core.permission.JacePermission
 * @since CFDK 1.0
 */
public class AccessController {

	/**
	 * 验证是否有权限执行一个方法。
	 * 
	 * @param auth
	 *            身份信息。
	 * @param method
	 *            要执行的方法。
	 * @return 是否可以执行该方法。
	 */
	public static boolean checkPermission(Authorization auth, Method method) {
		return checkPermission(auth, method.getDeclaringClass().getName(),
				method.getName());
	}

	/**
	 * 验证是否可以对某个对象进行某种操作。
	 * 
	 * @param auth
	 *            身份信息。
	 * @param object
	 *            要操作的对象。
	 * @param operation
	 *            要做的操作。
	 * @return 是否可以继续该操作。
	 */
	public static boolean checkPermission(Authorization auth, String object,
			String operation) {
		PermissionFactory factory = PermissionFactory.getInstance();
		JacePermission neededPermission = factory.getNeededPermission(object,
				operation);

		BigInteger neededCode = neededPermission.code;
		if (BigInteger.ZERO.equals(neededCode)) {
			return true;
		}

		if (auth.isAnonymous()) {
			return false;
		}

		JacePermission ownedPermission = factory.getOwnedPermission(auth);
		return ownedPermission.implies(neededPermission);
	}

}
