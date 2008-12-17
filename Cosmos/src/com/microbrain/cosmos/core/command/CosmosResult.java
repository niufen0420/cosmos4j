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

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 封装各种命令执行的结果，保存这些结果，以供上层来使用。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @see com.microbrain.cosmos.core.command.StandardCosmosResult
 * @see com.microbrain.cosmos.core.command.ComponentTypeEditor
 * @since CFDK 1.0
 */
public interface CosmosResult extends ComponentTypeEditor {

	/**
	 * 获得这个运行结果所关联的命令。
	 * 
	 * @return 返回关联的命令。
	 */
	public CosmosCommand getCommand();

	/**
	 * 获得封装成对象的结果。
	 * 
	 * @return 封装成动态对象的结果。
	 */
	public CosmosDynamicObject getVarObject();

	/**
	 * 将动态对象封装成对应的类。
	 * 
	 * @param <T>
	 *            返回该对应类的对象。
	 * @param clazz
	 *            要封装成的对应类。
	 * @param fields
	 *            属性对应关系。
	 * @return 返回封装之后的对象。
	 */
	public <T> T getVarObject(Class<?> clazz, String... fields);

	/**
	 * 获得封装之后的某个属性。
	 * 
	 * @param <T>
	 *            属性类型。
	 * @param name
	 *            属性名称。
	 * @return 属性的值。
	 */
	public <T> T getVar(String name);

	/**
	 * 根据序号返回某个属性。
	 * 
	 * @param <T>
	 *            属性类型。
	 * @param index
	 *            属性的序号。
	 * @return 属性值。
	 */
	public <T> T getVar(int index);

	/**
	 * 获得所有的属性名称。
	 * 
	 * @return 返回属性名称。
	 */
	public Collection<String> getVarNames();

	/**
	 * 返回属性个数。
	 * 
	 * @return 属性个数。
	 */
	public Integer getVarCount();

	/**
	 * 获得第一个封装成列表的执行结果。
	 * 
	 * @return 动态对象的集合结果。
	 */
	public Collection<CosmosDynamicObject> getList();

	/**
	 * 获得第一个封装成列表的执行结果，并将每个对象封装成componentType描述的类，属性的对应关系采用fields描述的那样。
	 * 
	 * @param <T>
	 *            列表每一个对象的类型。
	 * @param componentType
	 *            列表组件类型。
	 * @param fields
	 *            属性列表。
	 * @return 列表结果。
	 */
	public <T> Collection<T> getList(Class<?> componentType, String... fields);

	/**
	 * 获得第index个封装成列表的执行结果。
	 * 
	 * @param index
	 *            列表序号。
	 * @return 动态对象的集合结果。
	 */
	public Collection<CosmosDynamicObject> getList(int index);

	/**
	 * 获得名称为name的封装成列表的执行结果。
	 * 
	 * @param name
	 *            列表名称。
	 * @return 动态对象的集合结果。
	 * @throws IllegalArgumentException
	 *             命令如果不支持对列表集合进行命名的话，则抛出此异常。
	 */
	public Collection<CosmosDynamicObject> getList(String name)
			throws IllegalArgumentException;

	/**
	 * 获得第index个封装成列表的执行结果，并将每个对象封装成componentType描述的类，属性的对应关系采用fields描述的那样。
	 * 
	 * @param <T>
	 *            列表每一个对象的类型。
	 * @param index
	 *            列表序号。
	 * @param componentType
	 *            列表组件类型。
	 * @param fields
	 *            属性列表。
	 * @return 列表结果。
	 */
	public <T> Collection<T> getList(int index, Class<?> componentType,
			String... fields);

	/**
	 * 获得名称为name的封装成列表的执行结果，并将每个对象封装成componentType描述的类，属性的对应关系采用fields描述的那样。
	 * 
	 * @param <T>
	 *            列表每一个对象的类型。
	 * @param name
	 *            列表名称。
	 * @param componentType
	 *            列表组件类型。
	 * @param fields
	 *            属性列表。
	 * @return 列表结果。
	 * @throws IllegalArgumentException
	 *             命令如果不支持对列表集合进行命名的话，则抛出此异常。
	 */
	public <T> Collection<T> getList(String name, Class<?> componentType,
			String... fields) throws IllegalArgumentException;

	/**
	 * 获得封装成列表的名称列表。
	 * 
	 * @return 名称列表
	 * @throws IllegalArgumentException
	 *             命令如果不支持对列表集合进行命名的话，则抛出此异常。
	 */
	public Collection<String> getListNames() throws IllegalArgumentException;

	/**
	 * 获得列表的数量。
	 * 
	 * @return 列表的数量。
	 */
	public Integer getListCount();

	/**
	 * 获得组合命令中某个原子命令封装成对象的结果。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @return 结果对象。
	 */
	public CosmosDynamicObject getVarObject(String domain, String command);

	/**
	 * 将动态对象封装成对应的类。
	 * 
	 * @param <T>
	 *            返回该对应类的对象。
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @param clazz
	 *            要封装成的对应类。
	 * @param fields
	 *            属性列表。
	 * @return 结果对象。
	 */
	public <T> T getVarObject(String domain, String command, Class<?> clazz,
			String... fields);

	/**
	 * 获得组合命令中某个原子命令执行结果封装之后的某个属性。
	 * 
	 * @param <T>
	 *            属性类型。
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @param name
	 *            属性名称
	 * @return 属性值。
	 */
	public <T> T getVar(String domain, String command, String name);

	/**
	 * 根据序号获得组合命令中某个原子命令执行结果封装之后的某个属性。
	 * 
	 * @param <T>
	 *            属性类型。
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @param index
	 *            属性序号
	 * @return 属性值。
	 */
	public <T> T getVar(String domain, String command, int index);

	/**
	 * 获得组合命令中某个原子命令执行结果所有的属性名称。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @return 返回属性名称列表。
	 */
	public Collection<String> getVarNames(String domain, String command);

	/**
	 * 获得组合命令中某个原子命令执行结果的属性数量。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @return 返回属性数量。
	 */
	public Integer getVarCount(String domain, String command);

	/**
	 * 获得组合命令中某个原子命令执行结果第一个封装成列表的集合。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @return 列表结果。
	 */
	public Collection<CosmosDynamicObject> getList(String domain, String command);

	/**
	 * 获得某个命令第一个封装成列表的执行结果，并将每个对象封装成componentType描述的类，属性的对应关系采用fields描述的那样。
	 * 
	 * @param <T>
	 *            列表每一个对象的类型。
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @param componentType
	 *            列表组件类型。
	 * @param fields
	 *            属性列表。
	 * @return 列表结果。
	 */
	public <T> Collection<T> getList(String domain, String command,
			Class<?> componentType, String... fields);

	/**
	 * 获得某个命令第index个封装成列表的执行结果。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @param index
	 *            列表序号
	 * @return 列表结果。
	 */
	public Collection<CosmosDynamicObject> getList(String domain,
			String command, int index);

	/**
	 * 获得某个命令名称为name的封装成列表的执行结果。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @param name
	 *            列表名称。
	 * @return 列表结果。
	 * @throws IllegalArgumentException
	 *             命令如果不支持对列表集合进行命名的话，则抛出此异常。
	 */
	public Collection<CosmosDynamicObject> getList(String domain,
			String command, String name) throws IllegalArgumentException;

	/**
	 * 获得某个命令第index个封装成列表的执行结果，并将每个对象封装成componentType描述的类，属性的对应关系采用fields描述的那样。
	 * 
	 * @param <T>
	 *            列表每一个对象的类型。
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @param index
	 * @param componentType
	 *            列表组件类型。
	 * @param fields
	 *            属性列表。
	 * @return 列表结果。
	 */
	public <T> Collection<T> getList(String domain, String command, int index,
			Class<?> componentType, String... fields);

	/**
	 * 获得某个命令名称为name的封装成列表的执行结果，并将每个对象封装成componentType描述的类，属性的对应关系采用fields描述的那样。
	 * 
	 * @param <T>
	 *            列表每一个对象的类型。
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @param name
	 *            列表名称。
	 * @param componentType
	 *            列表组件类型。
	 * @param fields
	 *            属性列表。
	 * @return 列表结果。
	 * @throws IllegalArgumentException
	 *             命令如果不支持对列表集合进行命名的话，则抛出此异常。
	 */
	public <T> Collection<T> getList(String domain, String command,
			String name, Class<?> componentType, String... fields)
			throws IllegalArgumentException;

	/**
	 * 获得某个命令封装成列表的名称列表。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @return 名称列表
	 * @throws IllegalArgumentException
	 *             命令如果不支持对列表集合进行命名的话，则抛出此异常。
	 */
	public Collection<String> getListNames(String domain, String command)
			throws IllegalArgumentException;

	/**
	 * 获得列表的数量。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @return 列表数量。
	 */
	public Integer getListCount(String domain, String command);

	/**
	 * 合并两个结果集。
	 * 
	 * @param result
	 *            待合并的结果集。
	 * @return 合并之后的结果集。
	 */
	public CosmosResult combine(CosmosResult result);

	/**
	 * 获得组合指令的所有结果对象映射。
	 * 
	 * @return 结果对象映射。
	 */
	public Map<String, CosmosDynamicObject> getObjectMap();

	/**
	 * 列出所有命令的结果列表映射。
	 * 
	 * @return 结果列表映射。
	 */
	public Map<String, List<CosmosDynamicList<CosmosDynamicObject>>> getListMap();

	/**
	 * 产生一个新的结果对象。
	 * 
	 * @return 结果对象。
	 */
	public CosmosDynamicObject newObject();

}
