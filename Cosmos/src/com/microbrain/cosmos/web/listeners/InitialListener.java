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
package com.microbrain.cosmos.web.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.auth.Authorization;
import com.microbrain.cosmos.core.auth.AuthorizationException;
import com.microbrain.cosmos.core.auth.AuthorizationFactory;
import com.microbrain.cosmos.core.constants.Constants;
import com.microbrain.cosmos.core.log.CosmosLogFactory;
import com.microbrain.cosmos.core.permission.PermissionFactory;

/**
 * <p>
 * 在Web应用中，启动Cosmos框架的一个监听器，用来初始化系统的各项参数。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see javax.servlet.ServletContextListener
 * @see javax.servlet.http.HttpSessionListener
 * @since CFDK 1.0
 */
public class InitialListener implements ServletContextListener,
		HttpSessionListener {

	/**
	 * 配置文件键。
	 */
	public static final String COSMOS_CONFIG = "cosmos-config";

	/**
	 * 日志记录器。
	 */
	private static final Log log = CosmosLogFactory.getLog();

	/*
	 * 上下文销毁事件。
	 * 
	 * @seejavax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent event) {
	}

	/*
	 * 上下文启动事件。
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
	 * .ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		ServletContext application = event.getServletContext();
		String param = application.getInitParameter(COSMOS_CONFIG);

		if (param != null) {
			application.log("Starting to init jace factory...");
			try {
				String homePath = application.getRealPath(Constants.HOME_PATH);
				CosmosFactory.initFactory(homePath, param);
			} catch (Exception e) {
				application.log("Error to init jace factory...", e);
			}

			application.log("End to init jace factory...");
		}
	}

	/*
	 * session创建事件。
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http
	 * .HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		Authorization auth = (Authorization) session
				.getAttribute(Constants.AUTHORIZATION_TOKEN);
		if (auth == null) {
			try {
				auth = AuthorizationFactory.createAnonymousAuthorization();
			} catch (AuthorizationException e) {
				log.error("[Exception]: System exception is: ", e);
			}

			session.setAttribute(Constants.AUTHORIZATION_TOKEN, auth);
		}
	}

	/*
	 * session销毁事件。
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet
	 * .http.HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		Authorization auth = (Authorization) session
				.getAttribute(Constants.AUTHORIZATION_TOKEN);
		if (auth != null && !auth.isAnonymous()) {
			PermissionFactory factory = PermissionFactory.getInstance();
			factory.removeOwnedPermission(auth);
		}

		session.removeAttribute(Constants.AUTHORIZATION_TOKEN);
		session.removeAttribute(Constants.USER_PERMISSION);
	}

}
