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
 * ��ʾ��Ļ���ģ�ͣ��������潫Ҫ����ʾ����ʾ���������ݣ����ṩ������Щ���ݵ�ͳһ�ӿڡ�
 * </p>
 * <p>
 * ͨ���������ģ���࣬����ΪһЩ��ŵ��б������������Ӷ��ܹ�ʵ������ҳ���Ŀ�ġ�
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosResult
 * @since CFDK 1.0
 */
public class BaseModel {

	/**
	 * ҵ�����������ݽ����
	 */
	private CosmosResult result;

	/**
	 * Ϊ���ȡ������ӳ�䡣
	 */
	private Map<String, Integer> index = new HashMap<String, Integer>();

	/**
	 * ��Ҫ��ʾ�Ķ�������ӳ�䡣
	 */
	private Map<String, Object> data = new HashMap<String, Object>();

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public BaseModel() {
	}

	/**
	 * ���캯����
	 * 
	 * @param result
	 *            ҵ�����������ݽ����
	 */
	public BaseModel(CosmosResult result) {
		this.result = result;
	}

	/**
	 * ���ҵ�����������ݽ����
	 * 
	 * @return ҵ�����������ݽ����
	 */
	public CosmosResult getResult() {
		return result;
	}

	/**
	 * ����ҵ�����������ݽ����
	 * 
	 * @param result
	 *            ҵ�����������ݽ����
	 */
	public void setResult(CosmosResult result) {
		this.result = result;
	}

	/**
	 * ���Ϊ���ȡ������ӳ�䡣
	 * 
	 * @return Ϊ���ȡ������ӳ�䡣
	 */
	public Map<String, Integer> getIndex() {
		return index;
	}

	/**
	 * ����Ϊ���ȡ������ӳ�䡣
	 * 
	 * @param resultType
	 *            ������͡�
	 * @param resultIndex
	 *            �����š�
	 */
	public void setIndex(String resultType, Integer resultIndex) {
		this.index.put(resultType, resultIndex);
	}

	/**
	 * �����Ҫ��ʾ�Ķ�������ӳ�䡣
	 * 
	 * @return ��Ҫ��ʾ�Ķ�������ӳ�䡣
	 */
	public Map<String, Object> getData() {
		return data;
	}

	/**
	 * ������Ҫ��ʾ�Ķ�������ӳ�䡣
	 * 
	 * @param key
	 *            ��Ҫ��ʾ�Ķ������
	 * @param data
	 *            ��Ҫ��ʾ�Ķ������ݡ�
	 */
	public void setData(String key, Object data) {
		this.data.put(key, data);
	}

}
