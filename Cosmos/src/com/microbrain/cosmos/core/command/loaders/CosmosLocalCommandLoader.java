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

/**
 * <p>
 * 有些命令是无法存储到<code>master</code> 域的，比如：<code>master</code>域为XML文件，而
 * <code>slave</code> 域为数据库的情况下，数据库中的存储过程是需要被单独装载的。类似于存储过程这样的命令，
 * 实际上已经存储在数据库中了，因此，在系统中无需在master域中再保存一份，这样提供本地命令装载器， 来装载类似这样的命令。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 */
public interface CosmosLocalCommandLoader {

	/**
	 * 装载本域下所有的命令。
	 * 
	 * @return 返回本域下所有命令列表。
	 * @throws CosmosCommandLoaderException
	 *             命令装载时抛出的异常。
	 */
	public Collection<CosmosCommand> loadAllLocalRootCommands()
			throws CosmosCommandLoaderException;

	/**
	 * 装载本域下某个命令。
	 * 
	 * @param name
	 *            要装载的命令名称。
	 * @return 返回该命令。
	 * @throws CosmosCommandLoaderException
	 *             命令装载时抛出的异常。
	 */
	public CosmosCommand loadLocalRootCommand(String name)
			throws CosmosCommandLoaderException;

}
