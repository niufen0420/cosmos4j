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
package com.microbrain.cosmos.web.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.CosmosFactoryException;
import com.microbrain.cosmos.core.command.CosmosArgumentConverter;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.CosmosExecuter;
import com.microbrain.cosmos.core.command.CosmosMetaArgument;
import com.microbrain.cosmos.core.command.CosmosMetaCommand;
import com.microbrain.cosmos.core.command.composite.CosmosCompositeCommand;
import com.microbrain.cosmos.core.config.Configuration;
import com.microbrain.cosmos.core.config.ConfigurationException;
import com.microbrain.cosmos.core.domain.CosmosDomain;
import com.microbrain.cosmos.core.domain.CosmosDomainException;
import com.microbrain.cosmos.core.permission.CosmosPermissionException;
import com.microbrain.cosmos.dev.CosmosCommandManager;
import com.microbrain.cosmos.dev.CosmosCommandManagerException;
import com.microbrain.cosmos.web.utils.ForwardUtil;
import com.microbrain.cosmos.web.utils.ServletForward;

/**
 * <p>
 * <code>CosmosDevelopmentServlet</code>提供了开发环境中管理系统中命令的方法，通过这个
 * <code>Servlet</code>，开发人员可以直接在<code>Web</code>界面中进行命令的开发和测试。
 * </p>
 * <p>
 * 这个<code>Servlet</code>通过调用框架中的相应类来完成相应的操作。管理命令的接口主要是
 * <code>com.microbrain.cosmos.dev.CosmosCommandManager</code>。
 * </p>
 * <p>
 * 本<code>Servlet</code>在配置时需要配置其映射的路径为/*，以便于通过后面的<code>pathinfo</code>来区分不同的操作。
 * </p>
 * <p>
 * 如果在没有传入跳转页面时，本<code>Servlet</code>一般将跳转至当前执行操作名称所对应的页面，这些页面一般配置在对应的目录中。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see javax.servlet.http.HttpServlet
 * @see com.microbrain.cosmos.dev.CosmosCommandManager
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @see com.microbrain.cosmos.core.CosmosFactory
 * @since CFDK 1.0
 */
public class CosmosDevelopmentServlet extends HttpServlet {

	/**
	 * 序列化时的序列号。
	 */
	private static final long serialVersionUID = 2104061821624512861L;

	/**
	 * 跳转页面的键。
	 */
	private static final String FORWARD_KEY = "forward";

	/**
	 * 跳转页面的目录键。
	 */
	private static final String PAGE_DIR_KEY = "dir";

	/**
	 * 跳转页面的默认目录。
	 */
	private static final String DEFAULT_PAGE_DIR = "/";

	/**
	 * 跳转页面的目录。
	 */
	private String dir = null;

	/**
	 * Cosmos工厂类。
	 */
	private CosmosFactory factory = null;

	/**
	 * Cosmos配置类。
	 */
	private Configuration config = null;

	/*
	 * 处理GET请求。
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/*
	 * 处理POST请求。
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String command = request.getPathInfo();
		if (command == null) {
			command = request.getServletPath();
		}

		if (command.startsWith("/")) {
			command = command.substring(1);
		}

		Method method = null;
		try {
			method = getClass().getMethod(command, HttpServletRequest.class,
					HttpServletResponse.class);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ServletForward forward = null;
		if (method != null) {
			try {
				method.invoke(this, request, response);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String ret = request.getParameter(FORWARD_KEY);
		if (ret == null || "".equals(ret.trim())) {
			ret = dir + "." + command;
		}

		forward = ForwardUtil.getForward(ret);

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(response.encodeRedirectURL(forward
						.getPath()));
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		} else {
			response.sendRedirect(response.encodeRedirectURL(""));
		}
	}

	/**
	 * 获得域中所有的全局命令。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void domainGlobalCommands(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String domain = request.getParameter("domain");
		if (domain == null || "".equals(domain.trim())) {
			throw new ServletException("");
		}

		Map<String, CosmosDomain> domains = null;
		try {
			domains = config.getDomains();
		} catch (ConfigurationException e) {
			throw new ServletException(e);
		}

		if (!domains.containsKey(domain)) {
			throw new ServletException("");
		}
		CosmosDomain cosmosDomain = domains.get(domain);

		Collection<CosmosCommand> commands = null;
		try {
			commands = cosmosDomain.listGlobalCommands();
		} catch (CosmosDomainException e) {
			throw new ServletException(e);
		}

		request.setAttribute("commands", commands);
	}

	/**
	 * 获得域中所有的本地命令。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void domainLocalCommands(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String domain = request.getParameter("domain");
		if (domain == null || "".equals(domain.trim())) {
			throw new ServletException("");
		}

		Map<String, CosmosDomain> domains = null;
		try {
			domains = config.getDomains();
		} catch (ConfigurationException e) {
			throw new ServletException(e);
		}

		if (!domains.containsKey(domain)) {
			throw new ServletException("");
		}

		CosmosDomain cosmosDomain = domains.get(domain);

		Collection<CosmosCommand> commands = null;
		try {
			commands = cosmosDomain.listLocalCommands();
		} catch (CosmosDomainException e) {
			throw new ServletException(e);
		}

		request.setAttribute("commands", commands);
	}

	/**
	 * 列出系统中有的所有全局命令，全局命令是指存储在主Domain中的命令。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void globalCommands(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Collection<CosmosCommand> commands = null;
		try {
			commands = factory.listAllGlobalCommands();
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		request.setAttribute("commands", commands);
	}

	/**
	 * 列出系统中有的所有本地命令，本地命令是指无法存储在主Domain中，只能存储在各自Domain中的命令，比如：存储过程等。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void localCommands(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Collection<CosmosCommand> commands = null;
		try {
			commands = factory.listAllLocalCommands();
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		request.setAttribute("commands", commands);
	}

	/**
	 * 列出系统中有的所有命令。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void commands(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Collection<CosmosCommand> commands = null;
		try {
			commands = factory.listAllCommands();
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		request.setAttribute("commands", commands);
	}

	/**
	 * 获得系统配置的所有域。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void domains(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Configuration config = factory.lookupConfig();
		Map<String, CosmosDomain> domains = null;
		try {
			domains = config.getDomains();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("domains", domains.values());
	}

	/**
	 * 获得所有类型转换器。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void converters(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Configuration config = factory.lookupConfig();
		Collection<CosmosArgumentConverter> converters = null;
		try {
			converters = config.getConverters();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("converters", converters);
	}

	/**
	 * 获得所有可用的执行器。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void executers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Configuration config = factory.lookupConfig();
		Collection<CosmosExecuter> executers = null;
		try {
			executers = config.getExecuters();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("executers", executers);
	}

	/**
	 * 获得所有的命令类型。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void commandTypes(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Configuration config = factory.lookupConfig();
		Map<String, CosmosMetaCommand> metaCommands = null;
		try {
			metaCommands = config.getCommandTypes();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("metaCommands", metaCommands.values());
	}

	/**
	 * 创建一个命令。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void createCommand(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String domain = request.getParameter("domain");
		String name = request.getParameter("name");
		String command = request.getParameter("command");
		String executer = request.getParameter("executer");
		String remark = request.getParameter("remark");
		String type = request.getParameter("type");
		String debugLevel = request.getParameter("debugLevel");
		CosmosCommandManager manager = null;
		try {
			manager = factory.getManager();
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		try {
			manager.create(domain, name, command, executer, remark, type,
					debugLevel);
		} catch (CosmosCommandManagerException e) {
			throw new ServletException(e);
		} catch (CosmosPermissionException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * 创建一个命令的子命令。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void createSubCommand(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String domain = request.getParameter("domain");
		String name = request.getParameter("name");
		String command = request.getParameter("command");
		String executer = request.getParameter("executer");
		String remark = request.getParameter("remark");
		String type = request.getParameter("type");
		String debugLevel = request.getParameter("debugLevel");
		String parentDomain = request.getParameter("parentDomain");
		String parentName = request.getParameter("parentCommand");
		String composite = request.getParameter("composite");
		CosmosCommand parentCommand = null;
		try {
			parentCommand = factory.getCommand(parentDomain, parentName);
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		if (!(parentCommand instanceof CosmosCompositeCommand)) {
			throw new ServletException(
					"parent command is not an instance of CosmosCompositeCommand.");
		}

		CosmosCommandManager manager = null;
		try {
			manager = factory.getManager();
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		try {
			manager.createSubCommand((CosmosCompositeCommand) parentCommand,
					composite, domain, name, command, executer, remark, type,
					debugLevel);
		} catch (CosmosCommandManagerException e) {
			throw new ServletException(e);
		} catch (CosmosPermissionException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * 为一个命令创建一个左邻居命令。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void createLeftCommand(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String domain = request.getParameter("domain");
		String name = request.getParameter("name");
		String command = request.getParameter("command");
		String executer = request.getParameter("executer");
		String remark = request.getParameter("remark");
		String type = request.getParameter("type");
		String debugLevel = request.getParameter("debugLevel");
		String rightDomain = request.getParameter("rightDomain");
		String rightName = request.getParameter("rightCommand");
		String composite = request.getParameter("composite");
		CosmosCommand rightCommand = null;
		try {
			rightCommand = factory.getCommand(rightDomain, rightName);
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		CosmosCommandManager manager = null;
		try {
			manager = factory.getManager();
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		try {
			manager.createLeftNeighbor(rightCommand, composite, domain, name,
					command, executer, remark, type, debugLevel);
		} catch (CosmosCommandManagerException e) {
			throw new ServletException(e);
		} catch (CosmosPermissionException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * 为一个命令创建一个右邻居命令。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void createRightCommand(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String domain = request.getParameter("domain");
		String name = request.getParameter("name");
		String command = request.getParameter("command");
		String executer = request.getParameter("executer");
		String remark = request.getParameter("remark");
		String type = request.getParameter("type");
		String debugLevel = request.getParameter("debugLevel");
		String leftDomain = request.getParameter("leftDomain");
		String leftName = request.getParameter("leftCommand");
		String composite = request.getParameter("composite");
		CosmosCommand leftCommand = null;
		try {
			leftCommand = factory.getCommand(leftDomain, leftName);
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		CosmosCommandManager manager = null;
		try {
			manager = factory.getManager();
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		try {
			manager.createRightNeighbor(leftCommand, composite, domain, name,
					command, executer, remark, type, debugLevel);
		} catch (CosmosCommandManagerException e) {
			throw new ServletException(e);
		} catch (CosmosPermissionException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * 为一个命令选择一个已经存在的子命令。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void selectSubCommand(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		if (command == null) {
			throw new ServletException();
		}

		String domain = null, name = null;
		int index = command.indexOf(".");
		if (index >= 0) {
			domain = command.substring(0, index);
			name = command.substring(index + 1);
		} else {
			name = command;
		}

		String parentDomain = request.getParameter("parentDomain");
		String parentName = request.getParameter("parentCommand");
		String composite = request.getParameter("composite");
		CosmosCommand parentCommand = null;
		try {
			parentCommand = factory.getCommand(parentDomain, parentName);
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		if (!(parentCommand instanceof CosmosCompositeCommand)) {
			throw new ServletException(
					"parent command is not an instance of CosmosCompositeCommand.");
		}

		CosmosCommandManager manager = null;
		try {
			manager = factory.getManager();
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		try {
			manager.selectSubCommand((CosmosCompositeCommand) parentCommand,
					composite, domain, name);
		} catch (CosmosCommandManagerException e) {
			throw new ServletException(e);
		} catch (CosmosPermissionException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * 为一个命令选择一个已经存在的左邻居命令。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void selectLeftCommand(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		if (command == null) {
			throw new ServletException();
		}

		String domain = null, name = null;
		int index = command.indexOf(".");
		if (index >= 0) {
			domain = command.substring(0, index);
			name = command.substring(index + 1);
		} else {
			name = command;
		}

		String rightDomain = request.getParameter("rightDomain");
		String rightName = request.getParameter("rightCommand");
		String composite = request.getParameter("composite");
		CosmosCommand rightCommand = null;
		try {
			rightCommand = factory.getCommand(rightDomain, rightName);
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		CosmosCommandManager manager = null;
		try {
			manager = factory.getManager();
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		try {
			manager.selectLeftNeighbor(rightCommand, composite, domain, name);
		} catch (CosmosCommandManagerException e) {
			throw new ServletException(e);
		} catch (CosmosPermissionException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * 为一个命令选择一个已经存在的右邻居命令。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void selectRightCommand(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		if (command == null) {
			throw new ServletException();
		}

		String domain = null, name = null;
		int index = command.indexOf(".");
		if (index >= 0) {
			domain = command.substring(0, index);
			name = command.substring(index + 1);
		} else {
			name = command;
		}

		String leftDomain = request.getParameter("leftDomain");
		String leftName = request.getParameter("leftCommand");
		String composite = request.getParameter("composite");
		CosmosCommand leftCommand = null;
		try {
			leftCommand = factory.getCommand(leftDomain, leftName);
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		CosmosCommandManager manager = null;
		try {
			manager = factory.getManager();
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		try {
			manager.selectRightNeighbor(leftCommand, composite, domain, name);
		} catch (CosmosCommandManagerException e) {
			throw new ServletException(e);
		} catch (CosmosPermissionException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * 通过domain和name来获得某个domain下的某个命令。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void command(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String domain = request.getParameter("domain");
		String name = request.getParameter("command");
		CosmosCommand command = null;
		try {
			command = factory.getCommand(domain, name);
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		request.setAttribute("command", command);
	}

	/**
	 * 更新一个命令的信息。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void saveCommand(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String domain = request.getParameter("domain");
		String name = request.getParameter("name");
		String command = request.getParameter("command");
		String executer = request.getParameter("executer");
		String remark = request.getParameter("remark");
		String type = request.getParameter("type");
		String debugLevel = request.getParameter("debugLevel");

		CosmosCommandManager manager = null;
		try {
			manager = factory.getManager();
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		try {
			manager.save(domain, name, command, executer, remark, type,
					debugLevel);
		} catch (CosmosCommandManagerException e) {
			throw new ServletException(e);
		} catch (CosmosPermissionException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * 删除一个命令。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void removeCommand(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String domain = request.getParameter("domain");
		String name = request.getParameter("name");
		String composite = request.getParameter("composite");
		String leftIndex = request.getParameter("leftIndex");
		if (leftIndex == null || "".equals(leftIndex.trim())) {
			throw new ServletException("left Index is null.");
		}

		CosmosCommandManager manager = null;
		try {
			manager = factory.getManager();
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		try {
			manager.remove(domain, name, composite, Integer.valueOf(leftIndex));
		} catch (CosmosCommandManagerException e) {
			throw new ServletException(e);
		} catch (CosmosPermissionException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * 获得该命令所有的参数列表信息。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void args(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String domainName = request.getParameter("domain");
		String commandName = request.getParameter("command");
		CosmosCommand command = null;
		try {
			command = factory.getCommand(domainName, commandName);
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("args", command.listMetaArguments());
	}

	/**
	 * 创建一个命令的参数。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void createArg(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String domain = request.getParameter("domain");
		String command = request.getParameter("command");
		String name = request.getParameter("name");
		String inOutType = request.getParameter("inOutType");
		String converter = request.getParameter("converter");
		String remark = request.getParameter("remark");

		CosmosCommandManager manager = null;
		try {
			manager = factory.getManager();
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		try {
			manager.createArg(domain, command, name, inOutType, converter,
					remark);
		} catch (CosmosCommandManagerException e) {
			throw new ServletException(e);
		} catch (CosmosPermissionException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * 获得该命令某个参数列表信息。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void arg(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String domainName = request.getParameter("domain");
		String commandName = request.getParameter("command");
		String argIndex = request.getParameter("index");
		int index = 0;
		if (argIndex != null) {
			index = Integer.parseInt(argIndex);
		}

		CosmosCommand command = null;
		try {
			command = factory.getCommand(domainName, commandName);
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Collection<CosmosMetaArgument> args = command.listMetaArguments();
		int i = 0;
		CosmosMetaArgument retArg = null;
		for (CosmosMetaArgument arg : args) {
			if (i == index) {
				retArg = arg;
			}
			i++;
		}

		request.setAttribute("arg", retArg);
	}

	/**
	 * 更新一个参数的信息。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void saveArg(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String domain = request.getParameter("domain");
		String command = request.getParameter("command");
		String argIndex = request.getParameter("index");
		int index = 0;
		if (argIndex != null && !"".equals(argIndex.trim())) {
			index = Integer.parseInt(argIndex);
		}

		String name = request.getParameter("name");
		String inOutType = request.getParameter("inOutType");
		String converter = request.getParameter("converter");
		String remark = request.getParameter("remark");

		CosmosCommandManager manager = null;
		try {
			manager = factory.getManager();
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		try {
			manager.saveArg(domain, command, index, name, inOutType, converter,
					remark);
		} catch (CosmosCommandManagerException e) {
			throw new ServletException(e);
		} catch (CosmosPermissionException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * 删除一个参数。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void removeArg(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String domain = request.getParameter("domain");
		String command = request.getParameter("command");
		String argIndex = request.getParameter("index");
		int index = 0;
		if (argIndex != null && !"".equals(argIndex.trim())) {
			index = Integer.parseInt(argIndex);
		}

		CosmosCommandManager manager = null;
		try {
			manager = factory.getManager();
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		try {
			manager.removeArg(domain, command, index);
		} catch (CosmosCommandManagerException e) {
			throw new ServletException(e);
		} catch (CosmosPermissionException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * 在一个参数之前插入一个参数。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void insertArgBefore(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String domain = request.getParameter("domain");
		String command = request.getParameter("command");
		String argIndex = request.getParameter("index");
		int index = 0;
		if (argIndex != null && !"".equals(argIndex.trim())) {
			index = Integer.parseInt(argIndex);
		}

		String name = request.getParameter("name");
		String inOutType = request.getParameter("inOutType");
		String converter = request.getParameter("converter");
		String remark = request.getParameter("remark");

		CosmosCommandManager manager = null;
		try {
			manager = factory.getManager();
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		try {
			manager.insertArgBefore(domain, command, index, name, inOutType,
					converter, remark);
		} catch (CosmosCommandManagerException e) {
			throw new ServletException(e);
		} catch (CosmosPermissionException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * 在一个参数之后插入一个参数。
	 * 
	 * @param request
	 *            请求对象。
	 * @param response
	 *            响应对象。
	 * @throws ServletException
	 *             执行Servlet时抛出的异常。
	 * @throws IOException
	 *             在对应的IO操作时产生的异常。
	 */
	public void insertArgAfter(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String domain = request.getParameter("domain");
		String command = request.getParameter("command");
		String argIndex = request.getParameter("index");
		int index = 0;
		if (argIndex != null && !"".equals(argIndex.trim())) {
			index = Integer.parseInt(argIndex);
		}

		String name = request.getParameter("name");
		String inOutType = request.getParameter("inOutType");
		String converter = request.getParameter("converter");
		String remark = request.getParameter("remark");

		CosmosCommandManager manager = null;
		try {
			manager = factory.getManager();
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		try {
			manager.insertArgAfter(domain, command, index, name, inOutType,
					converter, remark);
		} catch (CosmosCommandManagerException e) {
			throw new ServletException(e);
		} catch (CosmosPermissionException e) {
			throw new ServletException(e);
		}
	}

	/*
	 * 初始化方法。
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		this.dir = getInitParameter(PAGE_DIR_KEY);
		if (this.dir == null) {
			this.dir = DEFAULT_PAGE_DIR;
		}

		try {
			factory = CosmosFactory.getInstance();
			config = factory.lookupConfig();
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}
	}

}
