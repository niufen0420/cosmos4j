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
package com.microbrain.cosmos.web.el.system;

import java.util.Collection;

import com.microbrain.cosmos.core.command.CosmosDynamicObject;
import com.microbrain.cosmos.core.command.CosmosResult;

/**
 * <p>
 * 表示层<code>EL</code>表达式，用来将从底层服务中产生的结果转化为页面能够显示的对象。
 * </p>
 * <p>
 * 默认地，<code>Servlet</code>会将执行结果放置在<code>request</code>中，默认地，取名为“
 * <code>result</code>”，在页面中通过该名称对结果进行操作。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.CosmosFactory
 * @see com.microbrain.cosmos.core.command.CosmosResult
 * @since CFDK 1.0
 */
public class PresentUtil {

	/**
	 * 私有化构造函数。
	 */
	private PresentUtil() {
	}

	/**
	 * 获得封装成对象的结果。
	 * 
	 * @param result
	 *            执行的结果。
	 * @return 封装成动态对象的结果。
	 */
	public static CosmosDynamicObject varObject(CosmosResult result) {
		return result.getVarObject();
	}

	/**
	 * 获得封装之后的某个属性。
	 * 
	 * @param <T>
	 *            属性类型。
	 * @param name
	 *            属性名称。
	 * @param result
	 *            执行的结果。
	 * @return 属性的值。
	 */
	public static <T> T var(String name, CosmosResult result) {
		return result.getVar(name);
	}

	/**
	 * 根据序号返回某个属性。
	 * 
	 * @param <T>
	 *            属性类型。
	 * @param index
	 *            属性的序号。
	 * @param result
	 *            执行的结果。
	 * @return 属性值。
	 */
	public static <T> T var(Integer index, CosmosResult result) {
		return result.getVar(index);
	}

	/**
	 * 获得所有的属性名称。
	 * 
	 * @param result
	 *            执行的结果。
	 * @return 返回属性名称。
	 */
	public static Collection<String> varNames(CosmosResult result) {
		return result.getVarNames();
	}

	/**
	 * 返回属性个数。
	 * 
	 * @param result
	 *            执行的结果。
	 * @return 属性个数。
	 */
	public static Integer varCount(CosmosResult result) {
		return result.getVarCount();
	}

	/**
	 * 获得第一个封装成列表的执行结果。
	 * 
	 * @param result
	 *            执行的结果。
	 * @return 动态对象的集合结果。
	 */
	public static Collection<CosmosDynamicObject> list(CosmosResult result) {
		return result.getList();
	}

	/**
	 * 获得第index个封装成列表的执行结果。
	 * 
	 * @param index
	 *            列表序号。
	 * @param result
	 *            执行的结果。
	 * @return 动态对象的集合结果。
	 */
	public static Collection<CosmosDynamicObject> list(Integer index,
			CosmosResult result) {
		return result.getList(index);
	}

	/**
	 * 获得名称为name的封装成列表的执行结果。
	 * 
	 * @param name
	 *            列表名称。
	 * @param result
	 *            执行的结果。
	 * @return 动态对象的集合结果。
	 * @throws IllegalArgumentException
	 *             命令如果不支持对列表集合进行命名的话，则抛出此异常。
	 */
	public static Collection<CosmosDynamicObject> list(String name,
			CosmosResult result) throws IllegalArgumentException {
		return result.getList(name);
	}

	/**
	 * 获得封装成列表的名称列表。
	 * 
	 * @param result
	 *            执行的结果。
	 * @return 名称列表
	 * @throws IllegalArgumentException
	 *             命令如果不支持对列表集合进行命名的话，则抛出此异常。
	 */
	public static Collection<String> listNames(CosmosResult result)
			throws IllegalArgumentException {
		return result.getListNames();
	}

	/**
	 * 获得列表的数量。
	 * 
	 * @param result
	 *            执行的结果。
	 * @return 列表的数量。
	 */
	public static Integer listCount(CosmosResult result) {
		return result.getListCount();
	}

	/**
	 * 获得组合命令中某个原子命令封装成对象的结果。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @param result
	 *            执行的结果。
	 * @return 结果对象。
	 */
	public static CosmosDynamicObject varObject(String domain, String command,
			CosmosResult result) {
		return result.getVarObject(domain, command);
	}

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
	 *            属性名称。
	 * @param result
	 *            执行的结果。
	 * @return 属性值。
	 */
	public static <T> T var(String domain, String command, String name,
			CosmosResult result) {
		return result.getVar(domain, command, name);
	}

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
	 * @param result
	 *            执行的结果。
	 * @return 属性值。
	 */
	public static <T> T var(String domain, String command, Integer index,
			CosmosResult result) {
		return result.getVar(domain, command, index);
	}

	/**
	 * 获得组合命令中某个原子命令执行结果所有的属性名称。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @param result
	 *            执行的结果。
	 * @return 返回属性名称列表。
	 */
	public static Collection<String> varNames(String domain, String command,
			CosmosResult result) {
		return result.getVarNames(domain, command);
	}

	/**
	 * 获得组合命令中某个原子命令执行结果的属性数量。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @param result
	 *            执行的结果。
	 * @return 返回属性数量。
	 */
	public static Integer varCount(String domain, String command,
			CosmosResult result) {
		return result.getVarCount(domain, command);
	}

	/**
	 * 获得组合命令中某个原子命令执行结果第一个封装成列表的集合。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @param result
	 *            执行的结果。
	 * @return 列表结果。
	 */
	public static Collection<CosmosDynamicObject> list(String domain,
			String command, CosmosResult result) {
		return result.getList(domain, command);
	}

	/**
	 * 获得某个命令第index个封装成列表的执行结果。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @param index
	 *            列表序号。
	 * @param result
	 *            执行的结果。
	 * @return 列表结果。
	 */
	public static Collection<CosmosDynamicObject> list(String domain,
			String command, Integer index, CosmosResult result) {
		return result.getList(domain, command, index);
	}

	/**
	 * 获得某个命令名称为name的封装成列表的执行结果。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @param name
	 *            列表名称。
	 * @param result
	 *            执行的结果。
	 * @return 列表结果。
	 * @throws IllegalArgumentException
	 *             命令如果不支持对列表集合进行命名的话，则抛出此异常。
	 */
	public static Collection<CosmosDynamicObject> list(String domain,
			String command, String name, CosmosResult result)
			throws IllegalArgumentException {
		return result.getList(domain, command, name);
	}

	/**
	 * 获得某个命令封装成列表的名称列表。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @param result
	 *            执行的结果。
	 * @return 名称列表。
	 * @throws IllegalArgumentException
	 *             命令如果不支持对列表集合进行命名的话，则抛出此异常。
	 */
	public static Collection<String> listNames(String domain, String command,
			CosmosResult result) throws IllegalArgumentException {
		return result.getListNames(domain, command);
	}

	/**
	 * 获得列表的数量。
	 * 
	 * @param domain
	 *            命令所属域。
	 * @param command
	 *            命令名称。
	 * @param result
	 *            执行的结果。
	 * @return 列表数量。
	 */
	public static Integer listCount(String domain, String command,
			CosmosResult result) {
		return result.getListCount(domain, command);
	}

	/**
	 * 获得列表中的第一个对象。
	 * 
	 * @param list
	 *            要操作的列表。
	 * @return 列表中的第一个对象。
	 */
	public static Object first(Collection<?> list) {
		if (list.size() < 1) {
			return null;
		}

		return list.toArray()[0];
	}

	/**
	 * 获得列表中的最后一个对象。
	 * 
	 * @param list
	 *            要操作的列表。
	 * @return 列表中的最后一个对象。
	 */
	public static Object last(Collection<?> list) {
		int size = list.size();
		if (size < 1) {
			return null;
		}

		return list.toArray()[size - 1];
	}

	/**
	 * 获得列表中的某个对象。
	 * 
	 * @param index
	 *            该对象的序号。
	 * @param list
	 *            要操作的列表。
	 * @return 该序号对应的对象。
	 */
	public static Object get(Integer index, Collection<?> list) {
		int size = list.size();
		if (size < 1 || index >= size) {
			return null;
		}

		return list.toArray()[index];
	}

}
