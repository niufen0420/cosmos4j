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
package com.microbrain.cosmos.core;

import com.microbrain.cosmos.core.command.CosmosResult;
import com.microbrain.cosmos.core.permission.CosmosPermissionException;
import com.microbrain.cosmos.core.sal.ServiceException;
import com.microbrain.cosmos.core.sal.annotation.Command;
import com.microbrain.cosmos.core.sal.annotation.Domain;

/**
 * @author Richard Sun
 * 
 */
@Domain("direct")
public interface Direct {

	/**
	 * @param domain
	 * @param test
	 * @return
	 * @throws ServiceException
	 * @throws CosmosPermissionException
	 */
	@Command("SpListExecuters")
	public CosmosResult listCommands(String domain, String test)
			throws ServiceException, CosmosPermissionException;

}
