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
package com.microbrain.cosmos.core.command.composite;

import java.util.Collection;

import com.microbrain.cosmos.core.command.CosmosCommand;

/**
 * <p>
 * <code>CosmosCompositeCommand</code>��չ��<code>CosmosCommand</code>�ӿڣ����ӿ�Ϊ
 * <code>CosmosCommand</code>��������϶��������ִ�еķ������Ӷ��ܹ�����������ִ�н������������ظ������ߡ�
 * </p>
 * <p>
 * <code>CosmosCompositeCommand</code>��Ҫ�ṩ��Ϊһ���������������������ķ��������磺
 * <code>add()</code>��<code>remove()</code>�ȣ�ͨ����Щ���������Խ�һЩԭ�ӵ����������ϡ�
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @see com.microbrain.cosmos.core.command.composite.IfCosmosCommand
 * @see com.microbrain.cosmos.core.command.composite.SequenceCosmosCommand
 * @since CFDK 1.0
 */
public interface CosmosCompositeCommand extends CosmosCommand {

	/**
	 * Ϊһ���������һ�������
	 * 
	 * @param command
	 *            Ҫ��ӵ������
	 * @return �����Ƿ���ӳɹ��ı�־λ��
	 */
	public boolean add(CosmosCommand command);

	/**
	 * ɾ��һ�������
	 * 
	 * @param command
	 *            Ҫɾ���������
	 * @return �����Ƿ�ɾ���ɹ��ı�־λ��
	 */
	public boolean remove(CosmosCommand command);

	/**
	 * ��ø�����������������б�
	 * 
	 * @return �������б�
	 */
	public Collection<CosmosCommand> commands();

}
