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
 * 系统常量，提供系统所有的常量。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.config.Configuration
 * @since CFDK 1.0
 */
public class Constants {

	/**
	 * 存储在Session中的认证标志。
	 */
	public static final String AUTHORIZATION_TOKEN = "AUTHORIZATION";

	/**
	 * 系统在互联网应用时，默认的主目录。
	 */
	public static final String HOME_PATH = "/WEB-INF/home";

	/**
	 * 主目录的默认键。
	 */
	public static final String COSMOS_HOME = "COSMOS_HOME";

	/**
	 * 用户权限。
	 */
	public static final String USER_PERMISSION = "permission";

	/**
	 * 登录代码。
	 */
	public static final String LOGIN_CODE = "jace_login_code";

	/**
	 * 登录名。
	 */
	public static final String LOGIN_NAME = "jace_login_name";

	/**
	 * 登录的服务。
	 */
	public static final String LOGIN_SERVICE = "jace_login_service";

	/**
	 * 验证图存储的键。
	 */
	public static final String RANDOM_IMAGE_CODE = "RANDOM_IMAGE_CODE";

	/**
	 * 验证图存储的时间键。
	 */
	public static final String RANDOM_IMAGE_TIME = "RANDOM_IMAGE_TIME";

	/**
	 * HTTP头中的语言键。
	 */
	public static final String ACCEPT_LANGUAGE_KEY = "ACCEPT_LANGUAGE";

	/**
	 * 配置文件中的Cosmos工厂访问路径。
	 */
	public static final String FACTORY_CLASS_KEY = "cosmos.[@factory]";
	/**
	 * 配置文件中的Cosmos配置文件类实现访问路径。
	 */
	public static final String CONFIGURATION_CLASS_KEY = "cosmos.[@configuration]";

	/**
	 * 配置文件中的Cosmos插件实现类访问路径。
	 */
	public static final String COSMOS_PLUGIN_CLASS = "cosmos.plugins.plugin(%d).[@class]";
	/**
	 * 配置文件中的Cosmos插件访问路径。
	 */
	public static final String COSMOS_PLUGIN = "cosmos.plugins.plugin";

	/**
	 * 配置文件中的Cosmos权限类访问路径。
	 */
	public static final String COSMOS_PERMISSION_CLASS = "cosmos.permission.[@class]";
	/**
	 * 配置文件中的Cosmos权限工厂类访问路径。
	 */
	public static final String COSMOS_PERMISSION_FACTORY = "cosmos.permission.[@factory]";

	/**
	 * 配置文件中的Cosmos认证键访问路径。
	 */
	public static final String COSMOS_AUTHENTICATION_TOKEN = "cosmos.authentication.[@token]";
	/**
	 * 配置文件中的Cosmos认证工厂类访问路径。
	 */
	public static final String COSMOS_AUTHENTICATION_FACTORY = "cosmos.authentication.[@factory]";

	/**
	 * 配置文件中的Cosmos单个域访问路径。
	 */
	public static final String COSMOS_DOMAINS_DOMAIN_INDEX = "cosmos.domains.domain(%d)";
	/**
	 * 配置文件中的Cosmos所有域访问路径。
	 */
	public static final String COSMOS_DOMAINS_DOMAIN = "cosmos.domains.domain";
	/**
	 * 配置文件中的Cosmos域实现类访问路径。
	 */
	public static final String COSMOS_DOMAINS_DOMAIN_CLASS = "cosmos.domains.domain(%d).[@class]";
	/**
	 * 配置文件中的Cosmos域类型访问路径。
	 */
	public static final String COSMOS_DOMAINS_DOMAIN_TYPE = "cosmos.domains.domain(%d).[@type]";
	/**
	 * 配置文件中的Cosmos域分类访问路径。
	 */
	public static final String COSMOS_DOMAINS_DOMAIN_CATEGORY = "cosmos.domains.domain(%d).[@category]";
	/**
	 * 配置文件中的Cosmos域缓存访问路径。
	 */
	public static final String COSMOS_DOMAINS_DOMAIN_CACHABLE = "cosmos.domains.domain(%d).[@cachable]";
	/**
	 * 配置文件中的Cosmos域重载访问路径。
	 */
	public static final String COSMOS_DOMAINS_DOMAIN_RELOADABLE = "cosmos.domains.domain(%d).[@reloadable]";
	/**
	 * 配置文件中的Cosmos域调试级别访问路径。
	 */
	public static final String COSMOS_DOMAINS_DOMAIN_DEBUG = "cosmos.domains.domain(%d).[@debug]";

	/**
	 * 配置文件中的Cosmos类型转换器访问路径。
	 */
	public static final String COSMOS_CONVERTERS_CONVERTER = "cosmos.converters.converter";
	/**
	 * 配置文件中的Cosmos类型转换器实现类访问路径。
	 */
	public static final String COSMOS_CONVERTERS_CONVERTER_CLASS = "cosmos.converters.converter(%d).[@class]";
	/**
	 * 配置文件中的Cosmos类型转换器调试级别访问路径。
	 */
	public static final String COSMOS_CONVERTERS_CONVERTER_DEBUG = "cosmos.converters.converter(%d).[@debug]";
	/**
	 * 配置文件中的Cosmos单个类型转换器访问路径。
	 */
	public static final String COSMOS_CONVERTERS_CONVERTER_INDEX = "cosmos.converters.converter(%d)";
	/**
	 * 配置文件中的Cosmos类型转换器标签访问路径。
	 */
	public static final String COSMOS_CONVERTERS_CONVERTER_LABEL = "cosmos.converters.converter(%d).[@label]";
	/**
	 * 配置文件中的Cosmos类型转换器对应的jdbc类型访问路径。
	 */
	public static final String COSMOS_CONVERTERS_CONVERTER_JDBC_TYPE = "cosmos.converters.converter(%d).jdbc-type";

	/**
	 * 配置文件中的Cosmos命令类型访问路径。
	 */
	public static final String COSMOS_COMMAND_TYPES_TYPE = "cosmos.command-types.command-type";
	/**
	 * 配置文件中的Cosmos命令类型实现类访问路径。
	 */
	public static final String COSMOS_COMMAND_TYPES_TYPE_CLASS = "cosmos.command-types.command-type(%d).[@class]";
	/**
	 * 配置文件中的Cosmos命令类型标签访问路径。
	 */
	public static final String COSMOS_COMMAND_TYPES_TYPE_LABEL = "cosmos.command-types.command-type(%d).[@label]";
	/**
	 * 配置文件中的Cosmos命令类型组合属性访问路径。
	 */
	public static final String COSMOS_COMMAND_TYPES_TYPE_COMPOSITE = "cosmos.command-types.command-type(%d).[@composite]";
	/**
	 * 配置文件中的Cosmos命令类型默认属性访问路径。
	 */
	public static final String COSMOS_COMMAND_TYPES_TYPE_DEFAULT = "cosmos.command-types.command-type(%d).[@default]";
	/**
	 * 配置文件中的Cosmos命令类型描述访问路径。
	 */
	public static final String COSMOS_COMMAND_TYPES_TYPE_DESCRIPTION = "cosmos.command-types.command-type(%d)";

	/**
	 * 配置文件中的Cosmos执行器访问路径。
	 */
	public static final String COSMOS_EXECUTERS_EXECUTER = "cosmos.executers.executer";
	/**
	 * 配置文件中的Cosmos执行器实现类访问路径。
	 */
	public static final String COSMOS_EXECUTERS_EXECUTER_CLASS = "cosmos.executers.executer(%d).[@class]";
	/**
	 * 配置文件中的Cosmos执行器标签访问路径。
	 */
	public static final String COSMOS_EXECUTERS_EXECUTER_LABEL = "cosmos.executers.executer(%d).[@label]";
	/**
	 * 配置文件中的Cosmos执行器分类访问路径。
	 */
	public static final String COSMOS_EXECUTERS_EXECUTER_CATEGORY = "cosmos.executers.executer(%d).[@category]";
	/**
	 * 配置文件中的Cosmos执行器描述访问路径。
	 */
	public static final String COSMOS_EXECUTERS_EXECUTER_DESCRIPTION = "cosmos.executers.executer(%d)";

}
