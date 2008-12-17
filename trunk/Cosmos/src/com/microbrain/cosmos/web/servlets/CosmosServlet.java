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

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.CosmosFactoryException;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.CosmosExecuteException;
import com.microbrain.cosmos.core.command.CosmosResult;
import com.microbrain.cosmos.core.log.CosmosLogFactory;
import com.microbrain.cosmos.web.utils.ForwardUtil;
import com.microbrain.cosmos.web.utils.ServletForward;

/**
 * <p>
 * 核心的<code>Servlet</code>，通常开发好的命令，将来都可以通过这个<code>Servlet</code>
 * 进行前台调用，然后跳转到对应的前台页面，显示执行的结果数据。
 * </p>
 * <p>
 * 本<code>Servlet</code>会自动将<code>HttpServletRequest</code>
 * 中的参数解析出来，并分析出数组参数，普通参数，以及上传文件的参数。上传文件是利用<code>common-upload</code>
 * 进行解析的，因此，首先将解析出来的文件保存在临时目录里，然后交由后续的处理器进行处理。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see javax.servlet.http.HttpServlet
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @see com.microbrain.cosmos.core.CosmosFactory
 * @since CFDK 1.0
 */
public class CosmosServlet extends HttpServlet {

	/**
	 * 序列化时的序列号。
	 */
	private static final long serialVersionUID = 7997324820970811451L;

	/**
	 * 上传文件保存的临时目录。
	 */
	private static final String TEMP_REPOSITORY = "temp-repository";

	/**
	 * 域参数名。
	 */
	private static final String DOMAIN = "domain";

	/**
	 * 方法参数名。
	 */
	private static final String METHOD = "method";

	/**
	 * 跳转页面的参数名。
	 */
	private static final String FORWARD = "forward";

	/**
	 * 默认的上传文件的临时目录。
	 */
	private static final String DEFAULT_TEMP_REPOSITORY = "/WEB-INF/home/tmp/repository";

	/**
	 * 最大上传文件长度。
	 */
	private static final String MAX_FILE_LEN = "max-file-len";

	/**
	 * 内存中的文件长度。
	 */
	private static final String MEMORY_FILE_LEN = "memory-file-len";

	/**
	 * UTF-8编码。
	 */
	private static final String UTF_8_ENCODING = "UTF-8";

	/**
	 * 内存中文件长度。
	 */
	private int memoryFileLen = 1048576;

	/**
	 * 最大上传文件长度。
	 */
	private int maxFileLen = 1073741824;

	/**
	 * 临时目录。
	 */
	private String temp;

	/**
	 * 上传文件工厂类。
	 */
	private DiskFileItemFactory factory = null;

	/**
	 * Cosmos工厂类。
	 */
	private CosmosFactory cosmosFactory = null;

	/**
	 * 日志记录器。
	 */
	private static final Log log = CosmosLogFactory.getLog();

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
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			String[] values = request.getParameterValues(paramName);
			if (values != null) {
				if (values.length == 1) {
					params.put(paramName, values[0]);
				} else {
					params.put(paramName, values);
				}
			}
		}

		params.put("request", request);
		params.put("response", response);

		if (ServletFileUpload.isMultipartContent(request)) {
			ServletFileUpload sfu = new ServletFileUpload(factory);
			sfu.setFileSizeMax(maxFileLen);
			sfu.setSizeMax(maxFileLen);
			sfu.setHeaderEncoding(UTF_8_ENCODING);

			List<FileItem> items = null;
			try {
				items = sfu.parseRequest(request);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (FileItem item : items) {
				String name = item.getFieldName();
				if (item.isFormField()) {
					String value = item.getString();
					params.put(name, value);
				} else {
					params.put(name, item);
				}
			}
		}

		if (log.isDebugEnabled()) {
			StringBuilder sb = new StringBuilder();
			for (String key : params.keySet()) {
				sb.append("[" + key + "]=\t" + params.get(key)).append("\n\r");
			}

			log.debug(sb);
		}

		Object domain = params.get(DOMAIN);
		Object method = params.get(METHOD);
		CosmosCommand command = null;
		try {
			if (domain == null) {
				command = cosmosFactory.getCommand((String) method);
			} else {
				command = cosmosFactory.getCommand((String) domain,
						(String) method);
			}
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		CosmosResult result = null;
		try {
			result = command.execute(params);
		} catch (CosmosExecuteException e) {
			throw new ServletException(e);
		}

		request.setAttribute("result", result);

		String forward = result.getVar(FORWARD);
		if (forward == null) {
			forward = request.getParameter(FORWARD);
		}

		if (forward == null) {
			response.sendRedirect(response.encodeRedirectURL("error.jsp"));
		} else {
			ServletForward servletForward = ForwardUtil.getForward(forward);
			if (servletForward.isRedirect()) {
				response.sendRedirect(response.encodeRedirectURL(servletForward
						.getPath()));
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(servletForward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	/*
	 * 初始化。
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		String strMemoryFileLen = config.getInitParameter(MEMORY_FILE_LEN);
		if (strMemoryFileLen != null) {
			memoryFileLen = Integer.valueOf(strMemoryFileLen);
		}

		String strMaxFileLen = config.getInitParameter(MAX_FILE_LEN);
		if (strMaxFileLen != null) {
			maxFileLen = Integer.valueOf(strMaxFileLen);
		}

		ServletContext application = config.getServletContext();
		temp = config.getInitParameter(TEMP_REPOSITORY);
		if (temp == null) {
			temp = application.getRealPath(DEFAULT_TEMP_REPOSITORY);
		}

		File tempRepository = new File(temp);
		if (tempRepository.exists()) {
			tempRepository.mkdirs();
		}

		try {
			cosmosFactory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			throw new ServletException(e);
		}

		factory = new DiskFileItemFactory();
		factory.setRepository(new File(temp));
		factory.setSizeThreshold(memoryFileLen);
	}

}
