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
package com.microbrain.cosmos.core.constants;

/**
 * <p>
 * ϵͳ�������ṩϵͳ���еĳ�����
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.config.Configuration
 * @since CFDK 1.0
 */
public class Constants {

	/**
	 * �洢��Session�е���֤��־��
	 */
	public static final String AUTHORIZATION_TOKEN = "AUTHORIZATION";

	/**
	 * ϵͳ�ڻ�����Ӧ��ʱ��Ĭ�ϵ���Ŀ¼��
	 */
	public static final String HOME_PATH = "/WEB-INF/home";

	/**
	 * ��Ŀ¼��Ĭ�ϼ���
	 */
	public static final String COSMOS_HOME = "COSMOS_HOME";

	/**
	 * �û�Ȩ�ޡ�
	 */
	public static final String USER_PERMISSION = "permission";

	/**
	 * ��¼���롣
	 */
	public static final String LOGIN_CODE = "jace_login_code";

	/**
	 * ��¼����
	 */
	public static final String LOGIN_NAME = "jace_login_name";

	/**
	 * ��¼�ķ���
	 */
	public static final String LOGIN_SERVICE = "jace_login_service";

	/**
	 * ��֤ͼ�洢�ļ���
	 */
	public static final String RANDOM_IMAGE_CODE = "RANDOM_IMAGE_CODE";

	/**
	 * ��֤ͼ�洢��ʱ�����
	 */
	public static final String RANDOM_IMAGE_TIME = "RANDOM_IMAGE_TIME";

	/**
	 * HTTPͷ�е����Լ���
	 */
	public static final String ACCEPT_LANGUAGE_KEY = "ACCEPT_LANGUAGE";

	/**
	 * �����ļ��е�Cosmos��������·����
	 */
	public static final String FACTORY_CLASS_KEY = "cosmos.[@factory]";
	/**
	 * �����ļ��е�Cosmos�����ļ���ʵ�ַ���·����
	 */
	public static final String CONFIGURATION_CLASS_KEY = "cosmos.[@configuration]";

	/**
	 * �����ļ��е�Cosmos���ʵ�������·����
	 */
	public static final String COSMOS_PLUGIN_CLASS = "cosmos.plugins.plugin(%d).[@class]";
	/**
	 * �����ļ��е�Cosmos�������·����
	 */
	public static final String COSMOS_PLUGIN = "cosmos.plugins.plugin";

	/**
	 * �����ļ��е�CosmosȨ�������·����
	 */
	public static final String COSMOS_PERMISSION_CLASS = "cosmos.permission.[@class]";
	/**
	 * �����ļ��е�CosmosȨ�޹��������·����
	 */
	public static final String COSMOS_PERMISSION_FACTORY = "cosmos.permission.[@factory]";

	/**
	 * �����ļ��е�Cosmos��֤������·����
	 */
	public static final String COSMOS_AUTHENTICATION_TOKEN = "cosmos.authentication.[@token]";
	/**
	 * �����ļ��е�Cosmos��֤���������·����
	 */
	public static final String COSMOS_AUTHENTICATION_FACTORY = "cosmos.authentication.[@factory]";

	/**
	 * �����ļ��е�Cosmos���������·����
	 */
	public static final String COSMOS_DOMAINS_DOMAIN_INDEX = "cosmos.domains.domain(%d)";
	/**
	 * �����ļ��е�Cosmos���������·����
	 */
	public static final String COSMOS_DOMAINS_DOMAIN = "cosmos.domains.domain";
	/**
	 * �����ļ��е�Cosmos��ʵ�������·����
	 */
	public static final String COSMOS_DOMAINS_DOMAIN_CLASS = "cosmos.domains.domain(%d).[@class]";
	/**
	 * �����ļ��е�Cosmos�����ͷ���·����
	 */
	public static final String COSMOS_DOMAINS_DOMAIN_TYPE = "cosmos.domains.domain(%d).[@type]";
	/**
	 * �����ļ��е�Cosmos��������·����
	 */
	public static final String COSMOS_DOMAINS_DOMAIN_CATEGORY = "cosmos.domains.domain(%d).[@category]";
	/**
	 * �����ļ��е�Cosmos�򻺴����·����
	 */
	public static final String COSMOS_DOMAINS_DOMAIN_CACHABLE = "cosmos.domains.domain(%d).[@cachable]";
	/**
	 * �����ļ��е�Cosmos�����ط���·����
	 */
	public static final String COSMOS_DOMAINS_DOMAIN_RELOADABLE = "cosmos.domains.domain(%d).[@reloadable]";
	/**
	 * �����ļ��е�Cosmos����Լ������·����
	 */
	public static final String COSMOS_DOMAINS_DOMAIN_DEBUG = "cosmos.domains.domain(%d).[@debug]";

	/**
	 * �����ļ��е�Cosmos����ת��������·����
	 */
	public static final String COSMOS_CONVERTERS_CONVERTER = "cosmos.converters.converter";
	/**
	 * �����ļ��е�Cosmos����ת����ʵ�������·����
	 */
	public static final String COSMOS_CONVERTERS_CONVERTER_CLASS = "cosmos.converters.converter(%d).[@class]";
	/**
	 * �����ļ��е�Cosmos����ת�������Լ������·����
	 */
	public static final String COSMOS_CONVERTERS_CONVERTER_DEBUG = "cosmos.converters.converter(%d).[@debug]";
	/**
	 * �����ļ��е�Cosmos��������ת��������·����
	 */
	public static final String COSMOS_CONVERTERS_CONVERTER_INDEX = "cosmos.converters.converter(%d)";
	/**
	 * �����ļ��е�Cosmos����ת������ǩ����·����
	 */
	public static final String COSMOS_CONVERTERS_CONVERTER_LABEL = "cosmos.converters.converter(%d).[@label]";
	/**
	 * �����ļ��е�Cosmos����ת������Ӧ��jdbc���ͷ���·����
	 */
	public static final String COSMOS_CONVERTERS_CONVERTER_JDBC_TYPE = "cosmos.converters.converter(%d).jdbc-type";

	/**
	 * �����ļ��е�Cosmos�������ͷ���·����
	 */
	public static final String COSMOS_COMMAND_TYPES_TYPE = "cosmos.command-types.command-type";
	/**
	 * �����ļ��е�Cosmos��������ʵ�������·����
	 */
	public static final String COSMOS_COMMAND_TYPES_TYPE_CLASS = "cosmos.command-types.command-type(%d).[@class]";
	/**
	 * �����ļ��е�Cosmos�������ͱ�ǩ����·����
	 */
	public static final String COSMOS_COMMAND_TYPES_TYPE_LABEL = "cosmos.command-types.command-type(%d).[@label]";
	/**
	 * �����ļ��е�Cosmos��������������Է���·����
	 */
	public static final String COSMOS_COMMAND_TYPES_TYPE_COMPOSITE = "cosmos.command-types.command-type(%d).[@composite]";
	/**
	 * �����ļ��е�Cosmos��������Ĭ�����Է���·����
	 */
	public static final String COSMOS_COMMAND_TYPES_TYPE_DEFAULT = "cosmos.command-types.command-type(%d).[@default]";
	/**
	 * �����ļ��е�Cosmos����������������·����
	 */
	public static final String COSMOS_COMMAND_TYPES_TYPE_DESCRIPTION = "cosmos.command-types.command-type(%d)";

	/**
	 * �����ļ��е�Cosmosִ��������·����
	 */
	public static final String COSMOS_EXECUTERS_EXECUTER = "cosmos.executers.executer";
	/**
	 * �����ļ��е�Cosmosִ����ʵ�������·����
	 */
	public static final String COSMOS_EXECUTERS_EXECUTER_CLASS = "cosmos.executers.executer(%d).[@class]";
	/**
	 * �����ļ��е�Cosmosִ������ǩ����·����
	 */
	public static final String COSMOS_EXECUTERS_EXECUTER_LABEL = "cosmos.executers.executer(%d).[@label]";
	/**
	 * �����ļ��е�Cosmosִ�����������·����
	 */
	public static final String COSMOS_EXECUTERS_EXECUTER_CATEGORY = "cosmos.executers.executer(%d).[@category]";
	/**
	 * �����ļ��е�Cosmosִ������������·����
	 */
	public static final String COSMOS_EXECUTERS_EXECUTER_DESCRIPTION = "cosmos.executers.executer(%d)";

}
