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
 * �ṩ��EL�������Է��㿪����Ա���ع���Ȩ�ޡ�
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
	 * ˽�л����캯����ȷ�����ڲ�ȫ��Ϊ��̬������
	 */
	private PermissionUtil() {
	}

	/**
	 * ����Ƿ��ĳ��������ĳ�ֲ�����Ȩ�ޡ�
	 * 
	 * @param auth
	 *            ��֤����
	 * @param object
	 *            Ҫ�����Ķ���
	 * @param operation
	 *            Ҫִ�еĲ�����
	 * @return �����Ƿ���Խ��иò�����
	 */
	public static Boolean check(Authorization auth, String object,
			String operation) {
		return AccessController.checkPermission(auth, object, operation);
	}

	/**
	 * ����Ƿ��ж�ĳ�������Ĳ���Ȩ�ޡ�
	 * 
	 * @param auth
	 *            ��֤����
	 * @param method
	 *            Ҫִ�еķ�����
	 * @return �����Ƿ���Խ��иò�����
	 */
	public static Boolean check(Authorization auth, Method method) {
		return AccessController.checkPermission(auth, method);
	}

}
