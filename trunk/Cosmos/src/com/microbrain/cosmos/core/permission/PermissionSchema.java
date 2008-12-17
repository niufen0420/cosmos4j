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
 * 权限验证的方案，通过配置不同方案达到不同的验证实现。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @since CFDK 1.0
 */
public interface PermissionSchema {

	/**
	 * 检查权限。
	 * 
	 * @param auth
	 *            认证对象。
	 * @param object
	 *            操作的对象。
	 * @param operation
	 *            要做何种操作。
	 * @return 是否允许执行该操作。
	 */
	public boolean check(Authorization auth, String object, String operation);

}
