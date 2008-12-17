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
package com.microbrain.cosmos.core.domain;

/**
 * <p>
 * 域类型，描述一个域是<code>master</code> 类型还是<code>slave</code>类型，<code>master</code>
 * 类型可以用来存储全局命令，而<code>slave</code>型域仅可以存储本地命令。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @since CFDK 1.0
 */
public enum CosmosDomainType {

	/**
	 * 主类型域。
	 */
	master,

	/**
	 * 从类型域。
	 */
	slave;

}
