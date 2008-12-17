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
package com.microbrain.cosmos.dev;

import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.composite.CosmosCompositeCommand;
import com.microbrain.cosmos.core.permission.CosmosPermissionException;

/**
 * <p>
 * ������п��������Ĺ���ӿڣ�ͨ�����ӿڣ�����Ϊϵͳ���һ�����ɾ��һ�����ƴ��һ�����
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.CosmosFactory
 * @see com.microbrain.cosmos.dev.AbstractCosmosCommandManager
 * @since CFDK 1.0
 */
public interface CosmosCommandManager {

	/**
	 * ����һ�����
	 * 
	 * @param domain
	 *            ����������
	 * @param name
	 *            �������ơ�
	 * @param command
	 *            �������ݡ�
	 * @param executer
	 *            ����ִ�������ơ�
	 * @param remark
	 *            ����˵����
	 * @param type
	 *            �������͡�
	 * @param debugLevel
	 *            ������Լ���
	 * @throws CosmosCommandManagerException
	 *             ���������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	public void create(String domain, String name, String command,
			String executer, String remark, String type, String debugLevel)
			throws CosmosCommandManagerException, CosmosPermissionException;

	/**
	 * ����һ������������
	 * 
	 * @param parent
	 *            �����
	 * @param composite
	 *            ����������������
	 * @param domain
	 *            ����������
	 * @param name
	 *            �������ơ�
	 * @param command
	 *            �������ݡ�
	 * @param executer
	 *            ����ִ�������ơ�
	 * @param remark
	 *            ����˵����
	 * @param type
	 *            �������͡�
	 * @param debugLevel
	 *            ������Լ���
	 * @throws CosmosCommandManagerException
	 *             ���������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	public void createSubCommand(CosmosCompositeCommand parent,
			String composite, String domain, String name, String command,
			String executer, String remark, String type, String debugLevel)
			throws CosmosCommandManagerException, CosmosPermissionException;

	/**
	 * Ϊһ�������һ�����ھ����
	 * 
	 * @param neighbor
	 *            �ھ����
	 * @param composite
	 *            ����������
	 * @param domain
	 *            ����������
	 * @param name
	 *            �������ơ�
	 * @param command
	 *            �������ݡ�
	 * @param executer
	 *            ����ִ�������ơ�
	 * @param remark
	 *            ����˵����
	 * @param type
	 *            �������͡�
	 * @param debugLevel
	 *            ������Լ���
	 * @throws CosmosCommandManagerException
	 *             ���������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	public void createLeftNeighbor(CosmosCommand neighbor, String composite,
			String domain, String name, String command, String executer,
			String remark, String type, String debugLevel)
			throws CosmosCommandManagerException, CosmosPermissionException;

	/**
	 * Ϊһ�������һ�����ھ����
	 * 
	 * @param neighbor
	 *            �ھ����
	 * @param composite
	 *            ����������
	 * @param domain
	 *            ����������
	 * @param name
	 *            �������ơ�
	 * @param command
	 *            �������ݡ�
	 * @param executer
	 *            ����ִ�������ơ�
	 * @param remark
	 *            ����˵����
	 * @param type
	 *            �������͡�
	 * @param debugLevel
	 *            ������Լ���
	 * @throws CosmosCommandManagerException
	 *             ���������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	public void createRightNeighbor(CosmosCommand neighbor, String composite,
			String domain, String name, String command, String executer,
			String remark, String type, String debugLevel)
			throws CosmosCommandManagerException, CosmosPermissionException;

	/**
	 * Ϊһ������ѡ��һ���Ѿ����ڵ������
	 * 
	 * @param parent
	 *            �����
	 * @param composite
	 *            ����������
	 * @param domain
	 *            ����������
	 * @param name
	 *            �������ơ�
	 * @throws CosmosCommandManagerException
	 *             ���������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	public void selectSubCommand(CosmosCompositeCommand parent,
			String composite, String domain, String name)
			throws CosmosCommandManagerException, CosmosPermissionException;

	/**
	 * Ϊһ������ѡ��һ���Ѿ����ڵ����ھ����
	 * 
	 * @param neighbor
	 *            �ھ����
	 * @param composite
	 *            ����������
	 * @param domain
	 *            ����������
	 * @param name
	 *            �������ơ�
	 * @throws CosmosCommandManagerException
	 *             ���������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	public void selectLeftNeighbor(CosmosCommand neighbor, String composite,
			String domain, String name) throws CosmosCommandManagerException,
			CosmosPermissionException;

	/**
	 * Ϊһ������ѡ��һ���Ѿ����ڵ����ھ����
	 * 
	 * @param neighbor
	 *            �ھ����
	 * @param composite
	 *            ����������
	 * @param domain
	 *            ����������
	 * @param name
	 *            �������ơ�
	 * @throws CosmosCommandManagerException
	 *             ���������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	public void selectRightNeighbor(CosmosCommand neighbor, String composite,
			String domain, String name) throws CosmosCommandManagerException,
			CosmosPermissionException;

	/**
	 * ����һ���������Ϣ��
	 * 
	 * @param domain
	 *            ����������
	 * @param name
	 *            �������ơ�
	 * @param command
	 *            �������ݡ�
	 * @param executer
	 *            ����ִ�������ơ�
	 * @param remark
	 *            ����˵����
	 * @param type
	 *            �������͡�
	 * @param debugLevel
	 *            ������Լ���
	 * @throws CosmosCommandManagerException
	 *             ���������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	public void save(String domain, String name, String command,
			String executer, String remark, String type, String debugLevel)
			throws CosmosCommandManagerException, CosmosPermissionException;

	/**
	 * ���һ�����
	 * 
	 * @param domain
	 *            ����������
	 * @param name
	 *            �������ơ�
	 * @return ����������
	 * @throws CosmosCommandManagerException
	 *             ���������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	public CosmosCommand get(String domain, String name)
			throws CosmosCommandManagerException, CosmosPermissionException;

	/**
	 * ɾ��һ�����
	 * 
	 * @param domain
	 *            ����������
	 * @param name
	 *            �������ơ�
	 * @param composite
	 *            ����������ϡ�
	 * @param leftIndex
	 *            ���������š�
	 * @throws CosmosCommandManagerException
	 *             ���������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	public void remove(String domain, String name, String composite,
			Integer leftIndex) throws CosmosCommandManagerException,
			CosmosPermissionException;

	/**
	 * ����һ������Ĳ�����
	 * 
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @param name
	 *            �������ơ�
	 * @param inOutType
	 *            ����IN/OUT���͡�
	 * @param converter
	 *            ��������ת�������ơ�
	 * @param remark
	 *            ����˵����
	 * @throws CosmosCommandManagerException
	 *             ���������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	public void createArg(String domain, String command, String name,
			String inOutType, String converter, String remark)
			throws CosmosCommandManagerException, CosmosPermissionException;

	/**
	 * ����һ����������Ϣ��
	 * 
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @param index
	 *            ������š�
	 * @param name
	 *            �������ơ�
	 * @param inOutType
	 *            ����IN/OUT���͡�
	 * @param converter
	 *            ��������ת�������ơ�
	 * @param remark
	 *            ����˵����
	 * @throws CosmosCommandManagerException
	 *             ���������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	public void saveArg(String domain, String command, int index, String name,
			String inOutType, String converter, String remark)
			throws CosmosCommandManagerException, CosmosPermissionException;

	/**
	 * ɾ��һ��������
	 * 
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @param index
	 *            ��ɾ����������š�
	 * @throws CosmosCommandManagerException
	 *             ���������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	public void removeArg(String domain, String command, int index)
			throws CosmosCommandManagerException, CosmosPermissionException;

	/**
	 * ��һ������֮ǰ����һ��������
	 * 
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @param index
	 *            Ҫ����Ĳ�����š�
	 * @param name
	 *            �������
	 * @param inOutType
	 *            ����IN/OUT���͡�
	 * @param converter
	 *            ��������ת�������ơ�
	 * @param remark
	 *            ����˵����
	 * @throws CosmosCommandManagerException
	 *             ���������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	public void insertArgBefore(String domain, String command, int index,
			String name, String inOutType, String converter, String remark)
			throws CosmosCommandManagerException, CosmosPermissionException;

	/**
	 * ��һ������֮�����һ��������
	 * 
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @param index
	 *            Ҫ����Ĳ�����š�
	 * @param name
	 *            �������
	 * @param inOutType
	 *            ����IN/OUT���͡�
	 * @param converter
	 *            ��������ת�������ơ�
	 * @param remark
	 *            ����˵����
	 * @throws CosmosCommandManagerException
	 *             ���������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	public void insertArgAfter(String domain, String command, int index,
			String name, String inOutType, String converter, String remark)
			throws CosmosCommandManagerException, CosmosPermissionException;

}
