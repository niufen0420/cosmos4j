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

import java.util.Collection;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.CosmosExecuter;
import com.microbrain.cosmos.core.command.loaders.CosmosGlobalCommandLoader;
import com.microbrain.cosmos.core.command.loaders.CosmosLocalCommandLoader;
import com.microbrain.cosmos.core.config.Configuration;
import com.microbrain.cosmos.core.constants.DebugLevel;

/**
 * <p>
 * 在<code>cosmos</code>框架中，一个域是指一个可以执行命令的环境，这可能包括：数据库或者内存数据库（<code>SQL</code>）、
 * <code>XML</code>文件（<code>XPATH</code>）、<code>WebService</code>（
 * <code>wsdl</code>）、文件系统（解释性<code>java</code>）、<code>Java scripting</code>（
 * <code>scripting</code>语句）等。有些域可以作为master类型的域，这种域可以用来存储全局命令，比如数据库或者
 * <code>XML</code>文件。
 * </p>
 * <p>
 * 域通过<code>category</code>属性来确认该域内可以使用的执行器<code>executer</code>的，在域和执行器中都拥有属性
 * <code>category</code>，通过该属性将两者关联起来，因此每一个域和每一个执行器，<code>category</code>
 * 属性都是不可或缺的。
 * </p>
 * <p>
 * 域中可以执行的命令是可以缓存在域中的，这个可以通过设置<code>cachable</code>属性来达到，当<code>cachable</code>
 * 属性为<code>true</code>时，可以通过设置<code>reloadable</code>属性，来决定每次执行一个命令时，是否要重载这个命令。
 * </p>
 * <p>
 * 通过设置域的调试级别，来达到统一设置调试级别的目的，但是也可以通过单独设置某个命令的调试级别，达到开发过程中调试的目的。
 * </p>
 * <p>
 * 域在启动时需要初始化，在程序终止时需要销毁，可以通过域来获得该域中的一个命令，当然也可以通过
 * <code>com.microbrain.cosmos.core.CosmosFactory</code>来获得一个命令。
 * </p>
 * <p>
 * 每个域都有一个单独的名称，域之间名称需要唯一，域之内的命令名称需要唯一，域之间的命令名称可以重复。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @see com.microbrain.cosmos.core.command.CosmosExecuter
 * @see com.microbrain.cosmos.core.domain.AbstractCosmosDomain
 * @see com.microbrain.cosmos.core.domain.CosmosDomainType
 * @see com.microbrain.cosmos.core.CosmosFactory
 * @see com.microbrain.cosmos.core.config.Configuration
 * @since CFDK 1.0
 */
public interface CosmosDomain {

	/**
	 * 获得域名称。
	 * 
	 * @return 域名称。
	 */
	public String getName();

	/**
	 * 获得域类型。
	 * 
	 * @return 域类型。
	 */
	public CosmosDomainType getType();

	/**
	 * 获得域分类。
	 * 
	 * @return 域分类。
	 */
	public String getCategory();

	/**
	 * 获得域是否缓存。
	 * 
	 * @return 域是否缓存。
	 */
	public Boolean isCachable();

	/**
	 * 获得域是否重载。
	 * 
	 * @return 域是否重载。
	 */
	public Boolean isReloadable();

	/**
	 * 获得域序号。
	 * 
	 * @return 域序号。
	 */
	public int getIndex();

	/**
	 * 获得域调试级别。
	 * 
	 * @return 域调试级别。
	 */
	public DebugLevel getDebugLevel();

	/**
	 * 初始化域。
	 * 
	 * @param config
	 *            配置类实例。
	 * @param factory
	 *            工厂类实例。
	 * @throws CosmosDomainException
	 *             初始化域时抛出的异常。
	 */
	public void init(Configuration config, CosmosFactory factory)
			throws CosmosDomainException;

	/**
	 * 获得域中可用的执行器。
	 * 
	 * @return 域中可用的执行器。
	 * @throws CosmosDomainException
	 *             获得域中可用执行器时抛出的异常。
	 */
	public Collection<CosmosExecuter> availableExecuters()
			throws CosmosDomainException;

	/**
	 * 通过名称获得一个执行器。
	 * 
	 * @param name
	 *            执行器名称。
	 * @return 执行器。
	 * @throws CosmosDomainException
	 *             可能无该名称的执行器。
	 */
	public CosmosExecuter getExecuter(String name) throws CosmosDomainException;

	/**
	 * 获得域中所有的命令，包括全局命令和本地命令。
	 * 
	 * @return 所有命令列表。
	 * @throws CosmosDomainException
	 *             获得所有命令时抛出的异常。
	 */
	public Collection<CosmosCommand> listAllCommands()
			throws CosmosDomainException;

	/**
	 * 获得域中所有的全局命令。
	 * 
	 * @return 所有全局命令列表。
	 * @throws CosmosDomainException
	 *             获得所有全局命令时抛出的异常。
	 */
	public Collection<CosmosCommand> listGlobalCommands()
			throws CosmosDomainException;

	/**
	 * 获得域中所有的本地命令。
	 * 
	 * @return 域中所有本地命令列表。
	 * @throws CosmosDomainException
	 *             获得所有本地命令时抛出的异常。
	 */
	public Collection<CosmosCommand> listLocalCommands()
			throws CosmosDomainException;

	/**
	 * 通过名称获得域中某个命令。
	 * 
	 * @param name
	 *            命令名称。
	 * @return 该命令。
	 * @throws CosmosDomainException
	 *             获得域中某个命令时抛出的异常。
	 */
	public CosmosCommand getCommand(String name) throws CosmosDomainException;

	/**
	 * 获得域的全局命令装载器。
	 * 
	 * @return 全局命令装载器。
	 */
	public CosmosGlobalCommandLoader getGlobalLoader();

	/**
	 * 获得域的本地命令装载器。
	 * 
	 * @return 本地命令装载器。
	 */
	public CosmosLocalCommandLoader getLocalLoader();

	/**
	 * 销毁一个域。
	 * 
	 * @throws CosmosDomainException
	 *             销毁一个域时抛出的异常。
	 */
	public void destroy() throws CosmosDomainException;

}
