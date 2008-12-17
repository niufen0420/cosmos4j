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
package com.microbrain.cosmos.core.command;

/**
 * <p>
 * 描述一个命令的相关信息，这些信息包括：名称，类型，是否组合，是否默认类型，标签，描述等信息。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @see com.microbrain.cosmos.core.command.StandardCosmosMetaArgument
 * @since CFDK 1.0
 */
public class CosmosMetaCommand {

	/**
	 * 命令类型的名称。
	 */
	private String name = null;

	/**
	 * 命令所使用的实现类。
	 */
	private Class<CosmosCommand> clazz = null;

	/**
	 * 该命令是否是一个组合命令。
	 */
	private Boolean composite = false;

	/**
	 * 命令类型是否是默认的命令类型。
	 */
	private Boolean defaultType = false;

	/**
	 * 命令标签。
	 */
	private String label = null;

	/**
	 * 命令的描述。
	 */
	private String description = null;

	/**
	 * 构造函数，用来初始化建立时构造命令类型类。
	 * 
	 * @param clazz
	 *            命令所使用的类。
	 * @param composite
	 *            命令是否是组合类型。
	 * @param defaultType
	 *            命令类型是否默认。
	 * @param description
	 *            命令类型的描述。
	 * @param label
	 *            命令类型的标签。
	 * @param name
	 *            命令类型的名称。
	 */
	public CosmosMetaCommand(Class<CosmosCommand> clazz, Boolean composite,
			Boolean defaultType, String description, String label, String name) {
		this.clazz = clazz;
		this.composite = composite;
		this.defaultType = defaultType;
		this.description = description;
		this.label = label;
		this.name = name;
	}

	/**
	 * 获得命令类型的名称。
	 * 
	 * @return 命令类型的名称。
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置命令类型的名称。
	 * 
	 * @param name
	 *            命令类型的名称。
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获得命令类型所使用的类。
	 * 
	 * @return 返回命令类型所使用的类。
	 */
	public Class<CosmosCommand> getClazz() {
		return clazz;
	}

	/**
	 * 设置命令类型所使用的类。
	 * 
	 * @param clazz
	 *            命令类型所使用的类。
	 */
	public void setClazz(Class<CosmosCommand> clazz) {
		this.clazz = clazz;
	}

	/**
	 * 命令类型是否是组合类型。
	 * 
	 * @return 命令类型的组合属性。
	 */
	public Boolean getComposite() {
		return composite;
	}

	/**
	 * 设置命令类型的组合属性。
	 * 
	 * @param composite
	 *            命令类型的组合属性。
	 */
	public void setComposite(Boolean composite) {
		this.composite = composite;
	}

	/**
	 * 获得命令类型是否为默认的命令类型。
	 * 
	 * @return 命令类型的默认属性。
	 */
	public Boolean getDefaultType() {
		return defaultType;
	}

	/**
	 * 设置命令类型是否为默认的命令类型。
	 * 
	 * @param defaultType
	 *            命令类型的默认属性。
	 */
	public void setDefaultType(Boolean defaultType) {
		this.defaultType = defaultType;
	}

	/**
	 * 获得命令类型的标签。
	 * 
	 * @return 返回命令类型的标签。
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * 设置命令类型的标签。
	 * 
	 * @param label
	 *            命令类型的标签。
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * 获得命令类型的描述。
	 * 
	 * @return 返回命令类型的描述。
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置命令类型的描述。
	 * 
	 * @param description
	 *            命令类型的描述。
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
