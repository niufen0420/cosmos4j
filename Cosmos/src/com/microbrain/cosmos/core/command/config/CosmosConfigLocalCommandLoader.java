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
package com.microbrain.cosmos.core.command.config;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.command.CosmosArgumentConverter;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.CosmosMetaArgument;
import com.microbrain.cosmos.core.command.CosmosMetaCommand;
import com.microbrain.cosmos.core.command.StandardCosmosMetaArgument;
import com.microbrain.cosmos.core.command.loaders.AbstractCosmosLocalCommandLoader;
import com.microbrain.cosmos.core.command.loaders.CosmosCommandLoaderException;
import com.microbrain.cosmos.core.constants.ArgumentInOutType;
import com.microbrain.cosmos.core.constants.Constants;
import com.microbrain.cosmos.core.constants.CosmosCommandSource;
import com.microbrain.cosmos.core.constants.DebugLevel;
import com.microbrain.cosmos.core.domain.CosmosDomain;

/**
 * <p>
 * 读取配置文件的本地命令装载器，实现了<code>CosmosLocalCommandLoader</code>接口，主要的功能是从各个域自己的配置文件中
 * 读取相应的命令，并组装成可以执行的单元。
 * </p>
 * <p>
 * <code>CosmosConfigLocalCommandLoader</code>实现了装载一个域下所有本地命令的方法，
 * 以及装载某个域下单个命令的方法。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.loaders.CosmosLocalCommandLoader
 * @see com.microbrain.cosmos.core.command.loaders.AbstractCosmosLocalCommandLoader
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @since CFDK 1.0
 */
public class CosmosConfigLocalCommandLoader extends
		AbstractCosmosLocalCommandLoader {

	/**
	 * 获得配置文件中某个域下所有的命令节点。
	 */
	public static final String COSMOS_DOMAIN_COMMAND = "cosmos.domains.domain(%d).commands.command";

	/**
	 * 获得配置文件中某个域下所有的命令节点的内容。
	 */
	public static final String COSMOS_DOMAIN_COMMAND_CONTENT = "cosmos.domains.domain(%d).commands.command(%d)";

	/**
	 * 获得配置文件中某个域下所有的命令节点的执行器。
	 */
	public static final String COSMOS_DOMAIN_COMMAND_EXECUTER = "cosmos.domains.domain(%d).commands.command(%d).[@executer]";

	/**
	 * 获得配置文件中某个域下所有的命令节点的类型。
	 */
	public static final String COSMOS_DOMAIN_COMMAND_TYPE = "cosmos.domains.domain(%d).commands.command(%d).[@type]";

	/**
	 * 获得配置文件中某个域下所有的命令节点的调试级别。
	 */
	public static final String COSMOS_DOMAIN_COMMAND_DEBUG = "cosmos.domains.domain(%d).commands.command(%d).[@debug]";

	/**
	 * 获得配置文件中某个域下所有的命令节点的参数列表。
	 */
	public static final String COSMOS_DOMAIN_COMMAND_ARG = "cosmos.domains.domain(%d).commands.command(%d).argument";

	/**
	 * 获得配置文件中某个域下所有的命令节点的说明内容。
	 */
	public static final String COSMOS_DOMAIN_COMMAND_REMARK = "cosmos.domains.domain(%d).commands.command(%d).remark";

	/**
	 * 获得配置文件中某个域下某个命令节点的某个参数。
	 */
	public static final String COSMOS_DOMAIN_COMMAND_ARG_REMARK = "cosmos.domains.domain(%d).commands.command(%d).argument(%d)";

	/**
	 * 获得配置文件中某个域下所有的命令节点的参数IN/OUT类型。
	 */
	public static final String COSMOS_DOMAIN_COMMAND_ARG_INOUTTYPE = "cosmos.domains.domain(%d).commands.command(%d).argument(%d).[@in-out-type]";

	/**
	 * 获得配置文件中某个域下所有的命令节点的参数类型转换器。
	 */
	public static final String COSMOS_DOMAIN_COMMAND_ARG_CONVERTER = "cosmos.domains.domain(%d).commands.command(%d).argument(%d).[@converter]";

	/**
	 * 构造函数。
	 * 
	 * @param factory
	 *            Cosmos工厂类。
	 * @param domain
	 *            本装载器所属域。
	 */
	public CosmosConfigLocalCommandLoader(CosmosFactory factory,
			CosmosDomain domain) {
		super(factory, domain);
	}

	/*
	 * 转载本域下的本地命令。
	 * 
	 * @seecom.microbrain.cosmos.core.command.loaders.CosmosLocalCommandLoader#
	 * loadAllLocalRootCommands()
	 */
	public Collection<CosmosCommand> loadAllLocalRootCommands()
			throws CosmosCommandLoaderException {
		int domainIndex = 0;
		Collection<CosmosCommand> commands = new ArrayList<CosmosCommand>();
		try {
			Map<String, CosmosMetaCommand> commandTypes = config
					.getCommandTypes();
			for (String curr : this.config
					.getByName(Constants.COSMOS_DOMAINS_DOMAIN)) {
				if (curr.equals(domain.getName())) {
					int commandIndex = 0;
					for (String name : this.config.getByName(String.format(
							COSMOS_DOMAIN_COMMAND, domainIndex))) {
						String executer = this.config.getString(String.format(
								COSMOS_DOMAIN_COMMAND_EXECUTER, domainIndex,
								commandIndex));
						String command = this.config.getString(String.format(
								COSMOS_DOMAIN_COMMAND_CONTENT, domainIndex,
								commandIndex));
						String type = this.config.getString(String.format(
								COSMOS_DOMAIN_COMMAND_TYPE, domainIndex,
								commandIndex));
						String remark = this.config.getString(String.format(
								COSMOS_DOMAIN_COMMAND_REMARK, domainIndex,
								commandIndex));
						String debug = this.config.getString(String.format(
								COSMOS_DOMAIN_COMMAND_DEBUG, domainIndex,
								commandIndex));
						CosmosMetaCommand commandType = config
								.getDefaultCommandType();

						Collection<CosmosMetaArgument> metaArgs = new ArrayList<CosmosMetaArgument>();
						int argIndex = 0;
						for (String argName : this.config.getByName(String
								.format(COSMOS_DOMAIN_COMMAND_ARG, domainIndex,
										commandIndex))) {
							String inOutTypeValue = this.config
									.getString(String
											.format(
													COSMOS_DOMAIN_COMMAND_ARG_INOUTTYPE,
													domainIndex, commandIndex,
													argIndex));
							String converterValue = this.config
									.getString(String
											.format(
													COSMOS_DOMAIN_COMMAND_ARG_CONVERTER,
													domainIndex, commandIndex,
													argIndex));
							String argRemark = this.config
									.getString(String
											.format(
													COSMOS_DOMAIN_COMMAND_ARG_REMARK,
													domainIndex, commandIndex,
													argIndex));
							ArgumentInOutType inOutType = inOutTypeValue == null ? ArgumentInOutType.IN
									: ArgumentInOutType.valueOf(inOutTypeValue);
							CosmosArgumentConverter converter = converterValue == null ? null
									: this.factory.getConverter(converterValue);

							CosmosMetaArgument arg = new StandardCosmosMetaArgument(
									converter, argName, inOutType, argRemark);

							metaArgs.add(arg);

							argIndex++;
						}

						if (type != null && !"".equals(type.trim())) {
							commandType = commandTypes.get(type);
						}

						Constructor<CosmosCommand> constructor = commandType
								.getClazz().getConstructor(String.class,
										CosmosDomain.class, String.class,
										String.class, Collection.class,
										CosmosMetaCommand.class,
										CosmosCommandSource.class);

						CosmosCommand cosmosCommand = constructor.newInstance(
								name, domain, command, executer, metaArgs,
								commandType, CosmosCommandSource.LOCAL);
						cosmosCommand.setRemark(remark);
						if (debug == null || "".equals(debug.trim())
								|| DebugLevel.NO_DEBUG.toString().equals(debug)) {
							cosmosCommand.setDebugLevel(domain.getDebugLevel());
						} else {
							cosmosCommand.setDebugLevel(DebugLevel
									.valueOf(debug));
						}

						commands.add(cosmosCommand);
						commandIndex++;
					}

					break;
				}

				domainIndex++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return commands;
	}

	/*
	 * 装载某个本地命令。
	 * 
	 * @seecom.microbrain.cosmos.core.command.loaders.CosmosLocalCommandLoader#
	 * loadLocalRootCommand(java.lang.String)
	 */
	public CosmosCommand loadLocalRootCommand(String name)
			throws CosmosCommandLoaderException {
		int domainIndex = 0;
		CosmosCommand cosmosCommand = null;
		try {
			Map<String, CosmosMetaCommand> commandTypes = config
					.getCommandTypes();
			for (String curr : this.config
					.getByName(Constants.COSMOS_DOMAINS_DOMAIN)) {
				if (curr.equals(domain.getName())) {
					int commandIndex = 0;
					for (String commandName : this.config.getByName(String
							.format(COSMOS_DOMAIN_COMMAND, domainIndex))) {
						if (commandName.equals(name)) {
							String executer = this.config.getString(String
									.format(COSMOS_DOMAIN_COMMAND_EXECUTER,
											domainIndex, commandIndex));
							String command = this.config.getString(String
									.format(COSMOS_DOMAIN_COMMAND_CONTENT,
											domainIndex, commandIndex));
							String type = this.config.getString(String.format(
									COSMOS_DOMAIN_COMMAND_TYPE, domainIndex,
									commandIndex));
							String remark = this.config.getString(String
									.format(COSMOS_DOMAIN_COMMAND_REMARK,
											domainIndex, commandIndex));
							String debug = this.config.getString(String.format(
									COSMOS_DOMAIN_COMMAND_DEBUG, domainIndex,
									commandIndex));
							CosmosMetaCommand commandType = config
									.getDefaultCommandType();

							Collection<CosmosMetaArgument> metaArgs = new ArrayList<CosmosMetaArgument>();
							int argIndex = 0;
							for (String argName : this.config.getByName(String
									.format(COSMOS_DOMAIN_COMMAND_ARG,
											domainIndex, commandIndex))) {
								String inOutTypeValue = this.config
										.getString(String
												.format(
														COSMOS_DOMAIN_COMMAND_ARG_INOUTTYPE,
														domainIndex,
														commandIndex, argIndex));
								String converterValue = this.config
										.getString(String
												.format(
														COSMOS_DOMAIN_COMMAND_ARG_CONVERTER,
														domainIndex,
														commandIndex, argIndex));
								String argRemark = this.config
										.getString(String
												.format(
														COSMOS_DOMAIN_COMMAND_ARG_REMARK,
														domainIndex,
														commandIndex, argIndex));
								ArgumentInOutType inOutType = inOutTypeValue == null ? ArgumentInOutType.IN
										: ArgumentInOutType
												.valueOf(inOutTypeValue);
								CosmosArgumentConverter converter = converterValue == null ? null
										: this.factory
												.getConverter(converterValue);

								CosmosMetaArgument arg = new StandardCosmosMetaArgument(
										converter, argName, inOutType,
										argRemark);

								metaArgs.add(arg);
								argIndex++;
							}

							if (type != null && !"".equals(type.trim())) {
								commandType = commandTypes.get(type);
							}

							Constructor<CosmosCommand> constructor = commandType
									.getClazz().getConstructor(String.class,
											CosmosDomain.class, String.class,
											String.class, Collection.class,
											CosmosMetaCommand.class,
											CosmosCommandSource.class);

							cosmosCommand = constructor.newInstance(name,
									domain, command, executer, metaArgs,
									commandType, CosmosCommandSource.LOCAL);
							cosmosCommand.setRemark(remark);
							if (debug == null
									|| "".equals(debug.trim())
									|| DebugLevel.NO_DEBUG.toString().equals(
											debug)) {
								cosmosCommand.setDebugLevel(domain
										.getDebugLevel());
							} else {
								cosmosCommand.setDebugLevel(DebugLevel
										.valueOf(debug));
							}

							break;
						}

						commandIndex++;
					}

					break;
				}
				domainIndex++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cosmosCommand;
	}

}
