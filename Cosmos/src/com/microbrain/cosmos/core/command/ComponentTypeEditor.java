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
 * ��̬��������Ա༭���ӿ�<code>ComponentTypeEditor</code>
 * ��ʹ������ӿڿ���Ϊһ�����������һ�����ԣ�ɾ��һ�����ԣ��Ӷ��ﵽ��̬���������Ŀ�ġ�
 * </p>
 * <p>
 * ��cosmos����У�<code>com.microbrain.cosmos.core.command.CosmosDynamicList</code>��
 * <code>com.microbrain.cosmos.core.command.StandardCosmosResult</code>�Լ�
 * <code>com.microbrain.cosmos.core.command.StandardComponentTypeEditor</code>
 * ����չ�˱���Ĺ��ܡ�
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosDynamicList
 * @see com.microbrain.cosmos.core.command.StandardCosmosResult
 * @see com.microbrain.cosmos.core.command.StandardComponentTypeEditor
 * @since CFDK 1.0
 */
public interface ComponentTypeEditor {

	/**
	 * Ϊһ����̬�������һ�����ԡ�
	 * 
	 * @param name
	 *            ���Ե����ơ�
	 */
	public void pushProperty(String name);

	/**
	 * ɾ��һ����̬������ӵ�е�ĳ�����ԡ�
	 * 
	 * @param name
	 *            ���Ե����ơ�
	 */
	public void removeProperty(String name);

}
