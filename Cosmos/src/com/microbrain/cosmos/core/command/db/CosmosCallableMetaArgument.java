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
package com.microbrain.cosmos.core.command.db;

import com.microbrain.cosmos.core.command.CosmosArgumentConverter;
import com.microbrain.cosmos.core.command.StandardCosmosMetaArgument;
import com.microbrain.cosmos.core.constants.ArgumentInOutType;

/**
 * <p>
 * �洢��������Ĳ��������࣬��չ����ͨ�Ĳ��������࣬�����������������͵�sqlType���ԡ�
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosMetaArgument
 * @see com.microbrain.cosmos.core.command.StandardCosmosMetaArgument
 * @since CFDK 1.0
 */
public class CosmosCallableMetaArgument extends StandardCosmosMetaArgument {

	/**
	 * sql���ͣ������������͡�
	 */
	private int sqlType = -1;

	/**
	 * ���캯����
	 * 
	 * @param converter
	 *            ����ת������
	 * @param name
	 *            �������ơ�
	 * @param inOutType
	 *            ����IN/OUT���͡�
	 * @param remark
	 *            ����˵����
	 * @param sqlType
	 *            ����sql���͡�
	 */
	public CosmosCallableMetaArgument(CosmosArgumentConverter converter,
			String name, ArgumentInOutType inOutType, String remark, int sqlType) {
		super(converter, name, inOutType, remark);
		this.sqlType = sqlType;
	}

	/**
	 * ��ò�����sql���͡�
	 * 
	 * @return ������sql���͡�
	 */
	public int getSqlType() {
		return sqlType;
	}

}
