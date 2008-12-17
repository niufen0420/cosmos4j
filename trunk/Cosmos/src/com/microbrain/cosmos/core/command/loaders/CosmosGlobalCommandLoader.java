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
package com.microbrain.cosmos.core.command.loaders;

import java.util.Collection;

import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.domain.CosmosDomain;

/**
 * <p>
 * 全局的命令装载器，在有些域中，是可以存储别的域的命令的，这样的域称之为可作为master类型的域。
 * 比如：数据库域，可以在数据库表中存储命令，这时，需要通过实现本接口，达到能从这样的域中装载命令的目的。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @since CFDK 1.0
 */
public interface CosmosGlobalCommandLoader {

	/**
	 * 装载所有的命令。
	 * 
	 * @return 所有命令列表。
	 * @throws CosmosCommandLoaderException
	 *             装载时抛出的异常。
	 */
	public Collection<CosmosCommand> loadAllCommands()
			throws CosmosCommandLoaderException;

	/**
	 * 装载某个域下的所有命令。
	 * 
	 * @param domain
	 *            所属域。
	 * @return 该域下所有命令列表。
	 * @throws CosmosCommandLoaderException
	 *             装载时抛出的异常。
	 */
	public Collection<CosmosCommand> loadDomainCommands(CosmosDomain domain)
			throws CosmosCommandLoaderException;

	/**
	 * 装载某个命令。
	 * 
	 * @param domain
	 *            所属域。
	 * @param name
	 *            命令名称。
	 * @return 该命令。
	 * @throws CosmosCommandLoaderException
	 *             装载时抛出的异常。
	 */
	public CosmosCommand loadCommand(CosmosDomain domain, String name)
			throws CosmosCommandLoaderException;

}
