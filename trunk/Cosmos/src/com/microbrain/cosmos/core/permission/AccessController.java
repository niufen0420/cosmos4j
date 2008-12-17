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
 * Ȩ�޿�������ͨ�����õײ��ṩ�ķ���������һ��������֤������Ƿ���Ȩ����ִ��ĳ�����
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
	 * ��֤�Ƿ���Ȩ��ִ��һ��������
	 * 
	 * @param auth
	 *            �����Ϣ��
	 * @param method
	 *            Ҫִ�еķ�����
	 * @return �Ƿ����ִ�и÷�����
	 */
	public static boolean checkPermission(Authorization auth, Method method) {
		return checkPermission(auth, method.getDeclaringClass().getName(),
				method.getName());
	}

	/**
	 * ��֤�Ƿ���Զ�ĳ���������ĳ�ֲ�����
	 * 
	 * @param auth
	 *            �����Ϣ��
	 * @param object
	 *            Ҫ�����Ķ���
	 * @param operation
	 *            Ҫ���Ĳ�����
	 * @return �Ƿ���Լ����ò�����
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
