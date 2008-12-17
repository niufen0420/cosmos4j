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
package com.microbrain.cosmos.core.command.composite;

import java.util.Collection;

import com.microbrain.cosmos.core.command.CosmosCommand;

/**
 * <p>
 * <code>CosmosCompositeCommand</code>扩展自<code>CosmosCommand</code>接口，本接口为
 * <code>CosmosCommand</code>增加了组合多个命令来执行的方法，从而能够将多个命令的执行结果组合起来返回给调用者。
 * </p>
 * <p>
 * <code>CosmosCompositeCommand</code>主要提供了为一个命令管理其所有子命令的方法，比如：
 * <code>add()</code>、<code>remove()</code>等，通过这些方法，可以将一些原子的命令进行组合。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @see com.microbrain.cosmos.core.command.composite.IfCosmosCommand
 * @see com.microbrain.cosmos.core.command.composite.SequenceCosmosCommand
 * @since CFDK 1.0
 */
public interface CosmosCompositeCommand extends CosmosCommand {

	/**
	 * 为一个命令添加一个子命令。
	 * 
	 * @param command
	 *            要添加的子命令。
	 * @return 返回是否添加成功的标志位。
	 */
	public boolean add(CosmosCommand command);

	/**
	 * 删除一个子命令。
	 * 
	 * @param command
	 *            要删除的子命令。
	 * @return 返回是否删除成功的标志位。
	 */
	public boolean remove(CosmosCommand command);

	/**
	 * 获得该命令的所有子命令列表。
	 * 
	 * @return 子命令列表。
	 */
	public Collection<CosmosCommand> commands();

}
