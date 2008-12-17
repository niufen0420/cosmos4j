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
package com.microbrain.cosmos.web.el.system;

import java.util.ArrayList;
import java.util.Collection;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.CosmosFactoryException;
import com.microbrain.cosmos.core.command.CosmosArgumentConverter;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.CosmosExecuter;
import com.microbrain.cosmos.core.command.CosmosMetaCommand;
import com.microbrain.cosmos.core.config.Configuration;
import com.microbrain.cosmos.core.config.ConfigurationException;
import com.microbrain.cosmos.core.domain.CosmosDomain;
import com.microbrain.cosmos.core.domain.CosmosDomainException;

/**
 * <p>
 * ϵͳ�ṩ��<code>EL</code>���ʽ���ṩ�˷���һЩϵͳ����ķ�����
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.CosmosFactory
 * @see com.microbrain.cosmos.core.config.Configuration
 * @since CFDK 1.0
 */
public class SystemUtil {

	/**
	 * ˽�л����캯����
	 */
	private SystemUtil() {
	}

	/**
	 * ���ϵͳ���õ�������
	 * 
	 * @return ������
	 */
	public static Collection<CosmosDomain> domains() {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			e.printStackTrace();
		}

		Configuration config = factory.lookupConfig();
		try {
			return config.getDomains().values();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		return new ArrayList<CosmosDomain>(0);
	}

	/**
	 * ������п��õ�ִ������
	 * 
	 * @return ���п��õ�ִ������
	 */
	public static Collection<CosmosExecuter> executers() {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			e.printStackTrace();
		}

		Configuration config = factory.lookupConfig();
		try {
			return config.getExecuters();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		return new ArrayList<CosmosExecuter>(0);
	}

	/**
	 * ������п��õ�ִ������
	 * 
	 * @param domain
	 *            �����ơ�
	 * @return ���п��õ�ִ������
	 */
	public static Collection<CosmosExecuter> executers(String domain) {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			e.printStackTrace();
		}

		Configuration config = factory.lookupConfig();
		try {
			CosmosDomain cosmosDomain = config.getDomains().get(domain);
			return cosmosDomain.availableExecuters();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (CosmosDomainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ArrayList<CosmosExecuter>(0);
	}

	/**
	 * �����������ת������
	 * 
	 * @return ���е�����ת������
	 */
	public static Collection<CosmosArgumentConverter> converters() {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			e.printStackTrace();
		}

		Configuration config = factory.lookupConfig();
		try {
			return config.getConverters();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		return new ArrayList<CosmosArgumentConverter>(0);
	}

	/**
	 * ������е��������͡�
	 * 
	 * @return ���е��������͡�
	 */
	public static Collection<CosmosMetaCommand> commandTypes() {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			e.printStackTrace();
		}

		Configuration config = factory.lookupConfig();
		try {
			return config.getCommandTypes().values();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		return new ArrayList<CosmosMetaCommand>(0);
	}

	/**
	 * �г�ϵͳ���е��������
	 * 
	 * @return ���������б�
	 */
	public static Collection<CosmosCommand> commands() {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			e.printStackTrace();
		}

		try {
			return factory.listAllCommands();
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ArrayList<CosmosCommand>(0);
	}

	/**
	 * ����������е��������ȫ������ͱ������
	 * 
	 * @param domain
	 *            �����ơ�
	 * @return ���������б�
	 */
	public static Collection<CosmosCommand> commands(String domain) {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			e.printStackTrace();
		}

		Configuration config = factory.lookupConfig();
		try {
			CosmosDomain cosmosDomain = config.getDomains().get(domain);
			return cosmosDomain.listAllCommands();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (CosmosDomainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ArrayList<CosmosCommand>(0);
	}

	/**
	 * �г�ϵͳ���е�����ȫ�����ȫ��������ָ�洢����Domain�е����
	 * 
	 * @return �������е�ȫ�����
	 */
	public static Collection<CosmosCommand> globalCommands() {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			e.printStackTrace();
		}

		try {
			return factory.listAllGlobalCommands();
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ArrayList<CosmosCommand>(0);
	}

	/**
	 * ����������е�ȫ�����
	 * 
	 * @param domain
	 *            �����ơ�
	 * @return ����ȫ�������б�
	 */
	public static Collection<CosmosCommand> globalCommands(String domain) {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			e.printStackTrace();
		}

		Configuration config = factory.lookupConfig();
		try {
			CosmosDomain cosmosDomain = config.getDomains().get(domain);
			return cosmosDomain.listGlobalCommands();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (CosmosDomainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ArrayList<CosmosCommand>(0);
	}

	/**
	 * �г�ϵͳ���е����б����������������ָ�޷��洢����<code>Domain</code>�У�ֻ�ܴ洢�ڸ���
	 * <code>Domain</code>�е�������磺�洢���̵ȡ�
	 * 
	 * @return �������еı������
	 */
	public static Collection<CosmosCommand> localCommands() {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			e.printStackTrace();
		}

		try {
			return factory.listAllLocalCommands();
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ArrayList<CosmosCommand>(0);
	}

	/**
	 * ����������еı������
	 * 
	 * @param domain
	 *            �����ơ�
	 * @return �������б��������б�
	 */
	public static Collection<CosmosCommand> localCommands(String domain) {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			e.printStackTrace();
		}

		Configuration config = factory.lookupConfig();
		try {
			CosmosDomain cosmosDomain = config.getDomains().get(domain);
			return cosmosDomain.listLocalCommands();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (CosmosDomainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ArrayList<CosmosCommand>(0);
	}

}
