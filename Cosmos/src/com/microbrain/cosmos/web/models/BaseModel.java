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
package com.microbrain.cosmos.web.models;

import java.util.HashMap;
import java.util.Map;

import com.microbrain.cosmos.core.command.CosmosResult;

/**
 * <p>
 * 显示层的基础模型，用来保存将要在显示层显示的所有数据，并提供访问这些数据的统一接口。
 * </p>
 * <p>
 * 通过这个基础模型类，可以为一些编号的列表重新命名，从而能够实现重用页面的目的。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosResult
 * @since CFDK 1.0
 */
public class BaseModel {

	/**
	 * 业务层产生的数据结果。
	 */
	private CosmosResult result;

	/**
	 * 为序号取的名字映射。
	 */
	private Map<String, Integer> index = new HashMap<String, Integer>();

	/**
	 * 需要显示的对象数据映射。
	 */
	private Map<String, Object> data = new HashMap<String, Object>();

	/**
	 * 默认构造函数。
	 */
	public BaseModel() {
	}

	/**
	 * 构造函数。
	 * 
	 * @param result
	 *            业务层产生的数据结果。
	 */
	public BaseModel(CosmosResult result) {
		this.result = result;
	}

	/**
	 * 获得业务层产生的数据结果。
	 * 
	 * @return 业务层产生的数据结果。
	 */
	public CosmosResult getResult() {
		return result;
	}

	/**
	 * 设置业务层产生的数据结果。
	 * 
	 * @param result
	 *            业务层产生的数据结果。
	 */
	public void setResult(CosmosResult result) {
		this.result = result;
	}

	/**
	 * 获得为序号取的名字映射。
	 * 
	 * @return 为序号取的名字映射。
	 */
	public Map<String, Integer> getIndex() {
		return index;
	}

	/**
	 * 设置为序号取的名字映射。
	 * 
	 * @param resultType
	 *            结果类型。
	 * @param resultIndex
	 *            结果序号。
	 */
	public void setIndex(String resultType, Integer resultIndex) {
		this.index.put(resultType, resultIndex);
	}

	/**
	 * 获得需要显示的对象数据映射。
	 * 
	 * @return 需要显示的对象数据映射。
	 */
	public Map<String, Object> getData() {
		return data;
	}

	/**
	 * 设置需要显示的对象数据映射。
	 * 
	 * @param key
	 *            需要显示的对象键。
	 * @param data
	 *            需要显示的对象数据。
	 */
	public void setData(String key, Object data) {
		this.data.put(key, data);
	}

}
