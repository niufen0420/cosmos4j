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
 * ���������࣬������һ������Ĳ�����������������Կ�����Ҫ������ת���������Ӧ������ת������
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @see com.microbrain.cosmos.core.command.StandardCosmosMetaArgument
 * @since CFDK 1.0
 */
public interface CosmosMetaArgument {

	/**
	 * ��ò������ơ�
	 * 
	 * @return �������ơ�
	 */
	public String getName();

	/**
	 * ��ò�����IN/OUT���͡�
	 * 
	 * @return ������IN/OUT���͡�
	 */
	public ArgumentInOutType getInOutType();

	/**
	 * ��ò���������ת������
	 * 
	 * @return ����������ת������
	 */
	public CosmosArgumentConverter getConverter();

	/**
	 * ��ò�����ע�͡�
	 * 
	 * @return ������ע�͡�
	 */
	public String getRemark();

}
