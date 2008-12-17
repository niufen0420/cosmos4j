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
package com.microbrain.cosmos.core;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.logging.Log;

import com.microbrain.cosmos.core.auth.Authorization;
import com.microbrain.cosmos.core.command.CosmosArgumentConverter;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.CosmosExecuter;
import com.microbrain.cosmos.core.config.Configuration;
import com.microbrain.cosmos.core.config.ConfigurationException;
import com.microbrain.cosmos.core.config.Plugin;
import com.microbrain.cosmos.core.domain.CosmosDomain;
import com.microbrain.cosmos.core.domain.CosmosDomainException;
import com.microbrain.cosmos.core.domain.CosmosDomainType;
import com.microbrain.cosmos.core.log.CosmosLogFactory;
import com.microbrain.cosmos.dev.CosmosCommandManager;

/**
 * <p>
 * <code>CosmosFactory</code> ��cosmos��ܵĺ��Ĺ����࣬ͨ��<code>CosmosFactory</code>��
 * <code>initFactory()</code>������ʼ����ȡϵͳ�����ļ��� �����<code>CosmosFactory</code>
 * �������ʵ����һ������<code>initFactory</code>���������˳�ʼ�����Ժ��ͨ��<code>getInstance()</code>
 * �����̬������ ����ȡ<code>CosmosFactory</code>���ڴ��еĵ�һʵ���ˡ�ע����һ��Ӧ�����еĹ����У�
 * <code>initFactory</code>����ֻ�ܱ�����һ�Ρ� �õ�ʵ��֮�󣬿��Ի�ö�Ӧ��������
 * <code>com.microbrain.cosmos.core.config.Configuration</code>������Ҫ���ǣ� ����ͨ��
 * <code>CosmosFactory</code>��ʵ���������ƺ���������һ�������������ļ������������ݿ��е�<code>Command</code>
 * ����ˣ�һ�����͵ĵ��ù��̿�������������
 * </p>
 * <p>
 * <blockquote>
 * 
 * <pre>
 * Map&lt;String, Object&gt; args = new HashMap&lt;String, Object&gt;();
 * CosmosFactory factory = CosmosFactory.getInstance();
 * CosmosCommand command = factory.getCommand(&quot;domain&quot;, &quot;command&quot;);
 * command.execute(args);
 * ...
 * </pre>
 * 
 * </blockquote>
 * </p>
 * <p>
 * ��Ҫ���ο����Ŀ���ͨ����չ<code>CosmosFactory</code>����ʵ���Լ��Ĺ����࣬ʹ���߿��������Զ���Ĺ��ܣ� ���Զ���ʵ�ֵ�
 * <code>CosmosFactory</code>��������cosmos-config.xml�ļ��оͿ���ʹ�ø��Զ��幤�����ˡ�<blockquote>
 * 
 * <pre>
 * &lt;cosmos factory=&quot;com.microbrain.cosmos.core.factory.DefaultCosmosFactoryImpl&quot;
 * 	configuration=&quot;com.microbrain.cosmos.core.config.DefaultConfigurationImpl&quot;&gt;
 * &lt;/cosmos&gt;
 * </pre>
 * 
 * </blockquote> ϵͳĬ�ϵ�CosmosFactoryʵ������
 * <code>com.microbrain.cosmos.core.factory.DefaultCosmosFactoryImpl</code>
 * ����ˣ�Ҳ����ֱ����չ���Ĭ��ʵ�֣����ﵽ�Զ����Ŀ�ġ�
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.factory.DefaultCosmosFactoryImpl
 * @see com.microbrain.cosmos.core.factory.JndiCosmosFactory
 * @see com.microbrain.cosmos.core.config.Configuration
 * @since CFDK 1.0
 */
public abstract class CosmosFactory {

	/**
	 * �����±����Ψһʵ�������ã��ڳ�ʼ����ʱ�������Ժ�ֱ��ͨ��<code>getInstance()</code>��������ȡ��
	 */
	private static CosmosFactory instance = null;

	/**
	 * Ĭ�ϵ�COSMOS��Ŀ¼��
	 */
	private static final String HOME_KEY = "COSMOS_HOME";

	/**
	 * ����ʹ�õ�ͬ�������ڶ��̻߳�����ȷ���ڴ��н���һ��ʵ��
	 */
	private static final Object locked = new Object();

	/**
	 * ��־��¼��
	 */
	protected static final Log log = CosmosLogFactory.getLog();

	/**
	 * ˽�л����캯����ȷ���ڃȴ��е�һʵ����
	 */
	protected CosmosFactory() {
	}

	/**
	 * ��������������һ��CosmosFactory��ʵ�����ڳ�ʼ��CosmosFactory֮��
	 * ͨ��������������������CosmosFactory��һ��ʵ���ġ�
	 * 
	 * @return CosmosFactory��ʵ����
	 * @throws CosmosFactoryException
	 *             ��û�г�ʼ�����߳�ʼ��ʧ��ʱ�����ñ�������ͼ��ȡһ��CosmosFactory��ʵ�����׳����쳣��
	 */
	public static CosmosFactory getInstance() throws CosmosFactoryException {
		if (instance == null) {
			synchronized (locked) {
				if (instance == null) {
					log.fatal("Cosmos factory initialize error.");
					throw new CosmosFactoryException(
							"Cosmos factory initialize error.");
				}
			}
		}

		return instance;
	}

	/**
	 * ����Cosmos��ܵ�����ʵ����Configuration��һ�����á�
	 * 
	 * @return Cosmos��ܵ�������ʵ����
	 */
	public abstract Configuration lookupConfig();

	/**
	 * ����Cosmos��ܵ�����ʵ���ࡣ
	 * 
	 * @param config
	 *            Cosmos��ܵ�������ʵ����
	 */
	protected abstract void injectConfig(Configuration config);

	/**
	 * ͨ��һ��������ʵ��Configuration����ʼ��Cosmos��ܡ�
	 * 
	 * @param config
	 *            Cosmos��ܵ�������ʵ����
	 * @return �Ѿ���ʼ���õ�CosmosFactory��ʵ����
	 * @throws CosmosFactoryException
	 *             ��ʼ������CosmosFactory�ĸ����쳣��
	 */
	public static CosmosFactory initFactory(Configuration config)
			throws CosmosFactoryException {
		String classString = config.getCosmosFactoryClass();
		Class<?> clazz = null;
		try {
			clazz = Class.forName(classString);

			instance = (CosmosFactory) clazz.newInstance();
		} catch (Exception e) {
			throw new CosmosFactoryException(
					"Initializing the cosmos factory has some error.", e);
		}

		instance.injectConfig(config);

		instance.initConverters(config);

		instance.initExecuters(config);

		instance.initDomains(config);

		instance.initPlugins(config);

		return instance;
	}

	/**
	 * ͨ��һ����Ŀ¼�������ļ�Ŀ¼����ʼ��Cosmos��ܡ�
	 * 
	 * @param homePath
	 *            Cosmos���ʹ�õ���Ŀ¼��
	 * @param configFileName
	 *            Cosmos��ܵ������ļ����·�����������Ŀ¼����
	 * @return ��ʼ��֮���CosmosFactoryʵ����
	 * @throws IOException
	 *             ��ȡ�����ļ���IO�쳣��
	 * @throws ConfigurationException
	 *             ���������ļ�ʱ���׳����쳣��
	 * @throws CosmosFactoryException
	 *             ���������ļ����Լ���ʼ��ϵͳ����ʵ��ʱ�׳����쳣��
	 */
	public static CosmosFactory initFactory(String homePath,
			String configFileName) throws IOException, ConfigurationException,
			CosmosFactoryException {
		Configuration config = Configuration.load(homePath + configFileName);
		config.setHomePath(homePath);
		System.setProperty(HOME_KEY, homePath);

		return initFactory(config);
	}

	/**
	 * ˽�з������ڳ�ʼ�����ʱ���ã���ʼ�����е�Converter��
	 * 
	 * @param config
	 *            Cosmos��ܵ�������ʵ����
	 * @throws CosmosFactoryException
	 *             ��ʼ��Converterʱ�׳����쳣��
	 */
	private void initConverters(Configuration config)
			throws CosmosFactoryException {
		try {
			for (CosmosArgumentConverter converter : config.getConverters()) {
				converter.init(config, this);
			}
		} catch (ConfigurationException e) {
			throw new CosmosFactoryException("Factory Exception. ", e);
		}
	}

	/**
	 * ˽�з������ڳ�ʼ�����ʱ���ã���ʼ�����е�Executer��
	 * 
	 * @param config
	 *            Cosmos��ܵ�������ʵ����
	 * @throws CosmosFactoryException
	 *             ��ʼ��Converterʱ�׳����쳣��
	 */
	private void initExecuters(Configuration config)
			throws CosmosFactoryException {
		try {
			for (CosmosExecuter executer : config.getExecuters()) {
				executer.init(this, config);
			}
		} catch (ConfigurationException e) {
			throw new CosmosFactoryException("Factory Exception. ", e);
		}
	}

	/**
	 * ˽�з������ڳ�ʼ�����ʱ���ã���ʼ�����е�Domain��
	 * 
	 * @param config
	 *            Cosmos��ܵ�������ʵ����
	 * @throws CosmosFactoryException
	 *             ��ʼ��Domainʱ�׳����쳣��
	 */
	private void initDomains(Configuration config)
			throws CosmosFactoryException {
		try {
			config.getMasterDomain().init(config, this);

			for (CosmosDomain domain : config.getDomains().values()) {
				if (domain.getType() != CosmosDomainType.master) {
					domain.init(config, this);
				}
			}
		} catch (ConfigurationException e) {
			throw new CosmosFactoryException("Factory Exception. ", e);
		} catch (CosmosDomainException e) {
			throw new CosmosFactoryException("Factory Exception. ", e);
		}
	}

	/**
	 * ˽�з������ڳ�ʼ�����ʱ���ã���ʼ�����е�Plugin��
	 * 
	 * @param config
	 *            Cosmos��ܵ�������ʵ����
	 * @throws CosmosFactoryException
	 *             ��ʼ��Pluginʱ�׳����쳣��
	 */
	private void initPlugins(Configuration config)
			throws CosmosFactoryException {
		try {
			for (Plugin plugin : config.getPlugins().values()) {
				plugin.init(config, this);
			}
		} catch (ConfigurationException e) {
			throw new CosmosFactoryException("Factory Exception. ", e);
		}
	}

	/**
	 * ���������ļ��е�����Plugin��
	 * 
	 * @return ���������ļ������е�Plugin��
	 */
	public Map<String, Plugin> getPlugins() throws ConfigurationException {
		return lookupConfig().getPlugins();
	}

	/**
	 * ͨ�����ƻ�������ļ��е�ĳһ��Converter��
	 * 
	 * @param name
	 *            Converter�����֡�
	 * @return ������Ƶ�Converter��
	 * @throws ConfigurationException
	 *             �ڶ�ȡ�����ļ�ʱ������ڴ������ȡConverterʱ�׳����쳣��
	 */
	public CosmosArgumentConverter getConverter(String name)
			throws ConfigurationException {
		return lookupConfig().getConvertersMap().get(name);
	}

	/**
	 * ͨ��jdbc type��������ļ��е�ĳһ��Converter��
	 * 
	 * @param jdbcType
	 *            ĳһ��jdbc���ͣ����Դ�java.sql.Types�л�á�
	 * @return �������jdbc���͵�Converter��
	 * @throws ConfigurationException
	 *             �ڶ�ȡ�����ļ�ʱ������ڴ������ȡConverterʱ�׳����쳣��
	 */
	public CosmosArgumentConverter getConverter(int jdbcType)
			throws ConfigurationException {
		return lookupConfig().getConvertersMap().get(jdbcType);
	}

	/**
	 * ������Domain���������ļ���ֻ������һ����Domain��������Ӧ���Ǵ�Domain��
	 * 
	 * @return ���������ļ��е���Domain��
	 */
	public CosmosDomain getMasterDomain() {
		return lookupConfig().getMasterDomain();
	}

	/**
	 * ͨ��domain��name�����ĳ��domain�µ�ĳ�����
	 * 
	 * @param domain
	 *            �����ļ��е�ĳ��domain���ơ�
	 * @param name
	 *            ĳ����������ơ�
	 * @return ���ظ�domain��name���������
	 * @throws CosmosFactoryException
	 *             ���һ��Commandʱ�׳����쳣��
	 */
	public abstract CosmosCommand getCommand(String domain, String name)
			throws CosmosFactoryException;

	/**
	 * ͨ��name�������domain�µ�ĳ�����
	 * 
	 * @param name
	 *            ĳ����������ơ�
	 * @return ���ظ�name���������
	 * @throws CosmosFactoryException
	 *             ���һ��Commandʱ�׳����쳣��
	 */
	public abstract CosmosCommand getCommand(String name)
			throws CosmosFactoryException;

	/**
	 * ���Cosmos���������Ĺ����ࡣ
	 * 
	 * @return ����������ʵ����
	 * @throws CosmosFactoryException
	 *             ���һ��CosmosCommandManagerʱ�׳����쳣��
	 */
	public abstract CosmosCommandManager getManager()
			throws CosmosFactoryException;

	/**
	 * �г�ϵͳ���е��������
	 * 
	 * @return ���������б�
	 * @throws CosmosFactoryException
	 *             ���CosmosCommand�б�ʱ�׳����쳣��
	 */
	public abstract Collection<CosmosCommand> listAllCommands()
			throws CosmosFactoryException;

	/**
	 * �г�ϵͳ���е�����ȫ�����ȫ��������ָ�洢����Domain�е����
	 * 
	 * @return �������е�ȫ�����
	 * @throws CosmosFactoryException
	 *             ���ȫ��CosmosCommand�б�ʱ�׳����쳣��
	 */
	public abstract Collection<CosmosCommand> listAllGlobalCommands()
			throws CosmosFactoryException;

	/**
	 * �г�ϵͳ���е����б����������������ָ�޷��洢����Domain�У�ֻ�ܴ洢�ڸ���Domain�е�������磺�洢���̵ȡ�
	 * 
	 * @return �������еı������
	 * @throws CosmosFactoryException
	 *             ��ñ���CosmosCommand�б�ʱ�׳����쳣��
	 */
	public abstract Collection<CosmosCommand> listAllLocalCommands()
			throws CosmosFactoryException;

	/**
	 * ���һ��SAL�ӿڵ�ʵ��Serviceʵ����
	 * 
	 * @param <T>
	 *            ��ʵ���������Ľӿڡ�
	 * @param clazz
	 *            ��Service��Ӧ�Ľӿڡ�
	 * @return ���ظ�ʵ����
	 * @throws CosmosFactoryException
	 *             ���Serviceʱ�׳����쳣��
	 */
	public abstract <T> T getService(Class<?> clazz)
			throws CosmosFactoryException;

	/**
	 * ���һ��SAL�ӿڵ�ʵ��Serviceʵ�����������û���Ϣ���Ա��ڵײ�ȷ�����û��Ƿ��ܵ���ĳ��������
	 * 
	 * @param <T>
	 *            ��ʵ���������Ľӿڡ�
	 * @param auth
	 *            ����һ���û���Ϣ����֤�ࡣ
	 * @param clazz
	 *            ��Service��Ӧ�Ľӿڡ�
	 * @return ���ظ�ʵ����
	 * @throws CosmosFactoryException
	 *             ���Serviceʱ�׳����쳣��
	 */
	public abstract <T> T getService(Authorization auth, Class<?> clazz)
			throws CosmosFactoryException;

}
