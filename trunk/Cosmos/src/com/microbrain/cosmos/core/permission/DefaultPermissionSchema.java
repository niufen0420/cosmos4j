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
 * 默认的权限方案实现，通过调用权限控制器
 * <code>com.microbrain.cosmos.core.permission.AccessController</code>来达到目的。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.permission.PermissionSchema
 * @since CFDK 1.0
 */
public class DefaultPermissionSchema implements PermissionSchema {

	/*
	 * 检查权限。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.permission.PermissionSchema#check(com.microbrain
	 * .cosmos.core.auth.Authorization, java.lang.String, java.lang.String)
	 */
	public boolean check(Authorization auth, String object, String operation) {
		return AccessController.checkPermission(auth, object, operation);
	}

}
