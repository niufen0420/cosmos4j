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

import com.microbrain.cosmos.core.constants.ArgumentInOutType;

/**
 * <p>
 * <code>CosmosMetaArgument</code>�ı�׼ʵ�֡�
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @see com.microbrain.cosmos.core.command.CosmosMetaArgument
 * @since CFDK 1.0
 */
public class StandardCosmosMetaArgument implements CosmosMetaArgument {

	/**
	 * ��������ת������
	 */
	private CosmosArgumentConverter converter = null;

	/**
	 * �����������ơ�
	 */
	private String name = null;

	/**
	 * ����IN/OUT���͡�
	 */
	private ArgumentInOutType inOutType = null;

	/**
	 * ��������ע�͡�
	 */
	private String remark = null;

	/**
	 * ���캯����
	 * 
	 * @param converter
	 *            ����ת������
	 * @param name
	 *            �������ơ�
	 * @param inOutType
	 *            IN/OUT���͡�
	 * @param remark
	 *            ע�͡�
	 */
	public StandardCosmosMetaArgument(CosmosArgumentConverter converter,
			String name, ArgumentInOutType inOutType, String remark) {
		this.converter = converter;
		this.name = name;
		this.inOutType = inOutType;
		this.remark = remark;
	}

	/*
	 * �������ת������
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosMetaArgument#getConverter()
	 */
	public CosmosArgumentConverter getConverter() {
		return this.converter;
	}

	/*
	 * ����������ơ�
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosMetaArgument#getName()
	 */
	public String getName() {
		return this.name;
	}

	/*
	 * ���IN/OUT���͡�
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosMetaArgument#getInOutType()
	 */
	public ArgumentInOutType getInOutType() {
		return this.inOutType;
	}

	/*
	 * ���ע�͡�
	 * 
	 * @see com.microbrain.cosmos.core.command.CosmosMetaArgument#getRemark()
	 */
	public String getRemark() {
		return remark;
	}

}
