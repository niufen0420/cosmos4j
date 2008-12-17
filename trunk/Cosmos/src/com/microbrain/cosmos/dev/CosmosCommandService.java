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

import com.microbrain.cosmos.core.command.CosmosResult;
import com.microbrain.cosmos.core.permission.CosmosPermissionException;
import com.microbrain.cosmos.core.sal.ServiceException;
import com.microbrain.cosmos.core.sal.annotation.Command;

/**
 * <p>
 * ������п��������ķ���ӿڣ�ͨ�����ӿڣ���������ϵͳ�Ѿ��еķ���Ϊϵͳ���һ�����ɾ��һ�����ƴ��һ�����
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.CosmosFactory
 * @see com.microbrain.cosmos.dev.AbstractCosmosCommandManager
 * @since CFDK 1.0
 */
public interface CosmosCommandService {

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
	 * @return ����ִ�н����
	 * @throws ServiceException
	 *             ����ĳ�������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	@Command("createCommand")
	public CosmosResult create(String domain, String name, String command,
			String executer, String remark, String type, String debugLevel)
			throws ServiceException, CosmosPermissionException;

	/**
	 * ����һ������������
	 * 
	 * @param parentDomain
	 *            ������������
	 * @param parentName
	 *            ���������ơ�
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
	 * @return ����ִ�н����
	 * @throws ServiceException
	 *             ����ĳ�������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	@Command("createSubCommand")
	public CosmosResult createSubCommand(String parentDomain,
			String parentName, String composite, String domain, String name,
			String command, String executer, String remark, String type,
			String debugLevel) throws ServiceException,
			CosmosPermissionException;

	/**
	 * Ϊһ�������һ�����ھ����
	 * 
	 * @param neighborDomain
	 *            �ھ�����������
	 * @param neighborName
	 *            �ھ��������ơ�
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
	 * @return ����ִ�н����
	 * @throws ServiceException
	 *             ����ĳ�������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	@Command("createLeftNeighbor")
	public CosmosResult createLeftNeighbor(String neighborDomain,
			String neighborName, String composite, String domain, String name,
			String command, String executer, String remark, String type,
			String debugLevel) throws ServiceException,
			CosmosPermissionException;

	/**
	 * Ϊһ�������һ�����ھ����
	 * 
	 * @param neighborDomain
	 *            �ھ�����������
	 * @param neighborName
	 *            �ھ��������ơ�
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
	 * @return ����ִ�н����
	 * @throws ServiceException
	 *             ����ĳ�������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	@Command("createRightNeighbor")
	public CosmosResult createRightNeighbor(String neighborDomain,
			String neighborName, String composite, String domain, String name,
			String command, String executer, String remark, String type,
			String debugLevel) throws ServiceException,
			CosmosPermissionException;

	/**
	 * Ϊһ������ѡ��һ���Ѿ����ڵ������
	 * 
	 * @param parentDomain
	 *            ������
	 * @param parentName
	 *            ���������ơ�
	 * @param composite
	 *            ����������
	 * @param domain
	 *            ����������
	 * @param name
	 *            �������ơ�
	 * @return ����ִ�н����
	 * @throws ServiceException
	 *             ����ĳ�������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	@Command("selectSubCommand")
	public CosmosResult selectSubCommand(String parentDomain,
			String parentName, String composite, String domain, String name)
			throws ServiceException, CosmosPermissionException;

	/**
	 * Ϊһ������ѡ��һ���Ѿ����ڵ����ھ����
	 * 
	 * @param neighborDomain
	 *            �ھ�����������
	 * @param neighborName
	 *            �ھ��������ơ�
	 * @param composite
	 *            ����������
	 * @param domain
	 *            ����������
	 * @param name
	 *            �������ơ�
	 * @return ����ִ�н����
	 * @throws ServiceException
	 *             ����ĳ�������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	@Command("selectLeftNeighbor")
	public CosmosResult selectLeftNeighbor(String neighborDomain,
			String neighborName, String composite, String domain, String name)
			throws ServiceException, CosmosPermissionException;

	/**
	 * Ϊһ������ѡ��һ���Ѿ����ڵ����ھ����
	 * 
	 * @param neighborDomain
	 *            �ھ�����������
	 * @param neighborName
	 *            �ھ��������ơ�
	 * @param composite
	 *            ����������
	 * @param domain
	 *            ����������
	 * @param name
	 *            �������ơ�
	 * @return ����ִ�н����
	 * @throws ServiceException
	 *             ����ĳ�������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	@Command("selectRightNeighbor")
	public CosmosResult selectRightNeighbor(String neighborDomain,
			String neighborName, String composite, String domain, String name)
			throws ServiceException, CosmosPermissionException;

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
	 * @return ����ִ�н����
	 * @throws ServiceException
	 *             ����ĳ�������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	@Command("save")
	public CosmosResult save(String domain, String name, String command,
			String executer, String remark, String type, String debugLevel)
			throws ServiceException, CosmosPermissionException;

	/**
	 * ���һ�����
	 * 
	 * @param domain
	 *            ����������
	 * @param name
	 *            �������ơ�
	 * @return ����ִ�н����
	 * @throws ServiceException
	 *             ����ĳ�������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	@Command("get")
	public CosmosResult get(String domain, String name)
			throws ServiceException, CosmosPermissionException;

	/**
	 * ɾ��һ�����
	 * 
	 * @param domain
	 *            ����������
	 * @param name
	 *            �������ơ�
	 * @param composite
	 *            ����������
	 * @param leftIndex
	 *            ���������š�
	 * @return ����ִ�н����
	 * @throws ServiceException
	 *             ����ĳ�������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	@Command("remove")
	public CosmosResult remove(String domain, String name, String composite,
			Integer leftIndex) throws ServiceException,
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
	 * @return ����ִ�н����
	 * @throws ServiceException
	 *             ����ĳ�������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	@Command("createArg")
	public CosmosResult createArg(String domain, String command, String name,
			String inOutType, String converter, String remark)
			throws ServiceException, CosmosPermissionException;

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
	 * @return ����ִ�н����
	 * @throws ServiceException
	 *             ����ĳ�������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	@Command("saveArg")
	public CosmosResult saveArg(String domain, String command, int index,
			String name, String inOutType, String converter, String remark)
			throws ServiceException, CosmosPermissionException;

	/**
	 * ɾ��һ��������
	 * 
	 * @param domain
	 *            ����������
	 * @param command
	 *            �������ơ�
	 * @param index
	 *            ������š�
	 * @return ����ִ�н����
	 * @throws ServiceException
	 *             ����ĳ�������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	@Command("removeArg")
	public CosmosResult removeArg(String domain, String command, int index)
			throws ServiceException, CosmosPermissionException;

	/**
	 * ��һ������֮ǰ����һ��������
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
	 * @return ����ִ�н����
	 * @throws ServiceException
	 *             ����ĳ�������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	@Command("insertArgBefore")
	public CosmosResult insertArgBefore(String domain, String command,
			int index, String name, String inOutType, String converter,
			String remark) throws ServiceException, CosmosPermissionException;

	/**
	 * ��һ������֮�����һ��������
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
	 * @return ����ִ�н����
	 * @throws ServiceException
	 *             ����ĳ�������׳����쳣��
	 * @throws CosmosPermissionException
	 *             û��Ȩ��ʱ�׳����쳣��
	 */
	@Command("insertArgAfter")
	public CosmosResult insertArgAfter(String domain, String command,
			int index, String name, String inOutType, String converter,
			String remark) throws ServiceException, CosmosPermissionException;

}
