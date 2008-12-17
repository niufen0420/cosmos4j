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
package com.microbrain.cosmos.core.config;

import com.microbrain.cosmos.core.CosmosFactory;

/**
 * <p>
 * �ṩһ���ɲ���ʽ�Ĳ���ӿڣ�ʹ�ÿ�����Ա������ϵͳ����չ�Լ���һЩ���ܡ�
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.config.AbstractPlugin
 * @since CFDK 1.0
 */
public interface Plugin {

	/**
	 * ��ʼ�������
	 * 
	 * @param config
	 * @param factory
	 */
	public void init(Configuration config, CosmosFactory factory);

	/**
	 * ��ò�����ơ�
	 * 
	 * @return ������ơ�
	 */
	public String getName();

	/**
	 * ���ò�����ơ�
	 * 
	 * @param name
	 *            ������ơ�
	 */
	public void setName(String name);

	/**
	 * ��ò����š�
	 * 
	 * @return �����š�
	 */
	public int getIndex();

	/**
	 * ���ò����š�
	 * 
	 * @param index
	 *            �����š�
	 */
	public void setIndex(int index);

}
