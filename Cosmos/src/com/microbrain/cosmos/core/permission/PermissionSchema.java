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

import com.microbrain.cosmos.core.auth.Authorization;

/**
 * <p>
 * Ȩ����֤�ķ�����ͨ�����ò�ͬ�����ﵽ��ͬ����֤ʵ�֡�
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @since CFDK 1.0
 */
public interface PermissionSchema {

	/**
	 * ���Ȩ�ޡ�
	 * 
	 * @param auth
	 *            ��֤����
	 * @param object
	 *            �����Ķ���
	 * @param operation
	 *            Ҫ�����ֲ�����
	 * @return �Ƿ�����ִ�иò�����
	 */
	public boolean check(Authorization auth, String object, String operation);

}
