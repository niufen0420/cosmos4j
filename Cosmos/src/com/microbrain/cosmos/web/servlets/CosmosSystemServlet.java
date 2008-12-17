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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microbrain.cosmos.core.auth.Authorization;
import com.microbrain.cosmos.core.auth.AuthorizationException;
import com.microbrain.cosmos.core.auth.AuthorizationFactory;
import com.microbrain.cosmos.core.constants.Constants;
import com.microbrain.cosmos.web.models.LoginModel;
import com.microbrain.cosmos.web.utils.ImageUtils;

/**
 * <p>
 * 与系统操作相关的<code>Servlet</code>，主要提供和统一身份认证相关的方法，其中包括登录、获得验证码等。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see javax.servlet.http.HttpServlet
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @see com.microbrain.cosmos.core.CosmosFactory
 * @since CFDK 1.0
 */
public class CosmosSystemServlet extends HttpServlet {

	/**
	 * 序列化时的序列号。
	 */
	private static final long serialVersionUID = 7800134204070902732L;

	/**
	 * 登录用户名键。
	 */
	private static final String LOGIN_NAME_KEY = "com.jace.cosmos.authentiation.name";

	/**
	 * 登录密码键
	 */
	private static final String LOGIN_PASSWORD_KEY = "com.jace.cosmos.authentiation.password";

	/**
	 * 是否启用验证码。
	 */
	private static final String VALIDATE_IMAGE_ENABLE_KEY = "com.jace.cosmos.authentiation.validate.enable";

	/**
	 * 验证码的键。
	 */
	private static final String VALIDATE_IMAGE_KEY = "com.jace.cosmos.authentiation.validate";

	/**
	 * 登录服务的键。
	 */
	private static final String LOGIN_SERVICE_KEY = "com.jace.cosmos.authentiation.service";

	/**
	 * 默认登录成功的键。
	 */
	private static final String DEFAULT_LOGINED_KEY = "com.jace.cosmos.authentiation.logined";

	/**
	 * 默认登录失败的键。
	 */
	private static final String DEFAULT_LOGIN_ERROR_KEY = "com.jace.cosmos.authentiation.error";

	/**
	 * 默认登录名键。
	 */
	private static final String DEFAULT_LOGIN_NAME = "cosmos.name";

	/**
	 * 默认登录密码键。
	 */
	private static final String DEFAULT_LOGIN_PASSWORD = "cosmos.password";

	/**
	 * 默认登录服务键。
	 */
	private static final String DEFAULT_LOGIN_SERVICE = "cosmos.service";

	/**
	 * 默认验证码键。
	 */
	private static final String DEFAULT_VALIDATE_IMAGE = "cosmos.validate.image";

	/**
	 * 验证码图宽。
	 */
	private static final String WIDTH_KEY = "width";

	/**
	 * 验证码图高。
	 */
	private static final String HEIGHT_KEY = "height";

	/**
	 * 登录名键。
	 */
	private String loginNameKey = null;

	/**
	 * 登录密码键。
	 */
	private String loginPasswordKey = null;

	/**
	 * 登录服务键。
	 */
	private String loginServiceKey = null;

	/**
	 * 验证码是否启用。
	 */
	private boolean validateImageEnabled = true;

	/**
	 * 验证图。
	 */
	private String validateImage = null;

	/**
	 * 登陆成功的默认界面。
	 */
	private String loginedPage = null;

	/**
	 * 登录失败的界面。
	 */
	private String loginErrorPage = null;

	/*
	 * 处理获取验证码请求。
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (!validateImageEnabled) {
			throw new ServletException(
					"could not enable validate image function.");
		}

		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-Cache");
		response.setDateHeader("Expires", 0);

		int width = 0;
		int height = 0;
		String widthStr = request.getParameter(WIDTH_KEY);
		String heightStr = request.getParameter(HEIGHT_KEY);
		String randomNumber = null;
		if (widthStr == null || heightStr == null) {
			randomNumber = ImageUtils.random(response.getOutputStream());
		} else {
			width = Integer.parseInt(widthStr);
			height = Integer.parseInt(heightStr);

			randomNumber = ImageUtils.random(width, height, response
					.getOutputStream());
		}

		HttpSession session = request.getSession();
		session.setAttribute(validateImage, randomNumber);
		session.setAttribute(Constants.RANDOM_IMAGE_TIME, System
				.currentTimeMillis());
	}

	/*
	 * 处理登录请求。
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		LoginModel model = new LoginModel();
		model.setLoginStatus(200);

		if (this.validateImageEnabled) {
			String checkCode = request.getParameter(validateImage);
			String code = (String) session.getAttribute(validateImage);
			if (code == null) {
				model.setLoginStatus(300);
			} else {
				Long imageTime = (Long) session
						.getAttribute(Constants.RANDOM_IMAGE_TIME);
				session.removeAttribute(validateImage);
				session.removeAttribute(Constants.RANDOM_IMAGE_TIME);

				if (System.currentTimeMillis() - imageTime > 60 * 1000 * 5) {
					model.setLoginStatus(400);
				} else {
					if (checkCode == null || "".equals(checkCode)) {
						model.setLoginStatus(500);
					} else {
						if (code == null || !code.equalsIgnoreCase(checkCode)) {
							model.setLoginStatus(600);
						}
					}
				}
			}
		}

		String loginCode = request.getParameter(loginNameKey);
		String loginPassword = request.getParameter(loginPasswordKey);
		String loginService = request.getParameter(loginServiceKey);

		if (loginCode == null || "".equals(loginCode)) {
			model.setLoginStatus(700);
		}

		if (loginPassword == null || "".equals(loginPassword)) {
			model.setLoginStatus(800);
		}

		if (loginService == null || "".equals(loginService)) {
			model.setLoginStatus(900);
		}

		String errorPage = request.getParameter("errorPage");
		if (errorPage == null || "".equals(errorPage)) {
			errorPage = loginErrorPage;
		}

		if (model.getLoginStatus() != 200) {
			StringBuilder sb = new StringBuilder(errorPage);
			if (errorPage.indexOf('?') >= 0) {
				sb.append("&");
			} else {
				sb.append("?");
			}

			sb.append(Constants.LOGIN_CODE).append('=').append(
					model.getLoginStatus()).append('&').append(
					Constants.LOGIN_NAME).append('=').append(loginCode).append(
					'&').append(Constants.LOGIN_SERVICE).append('=').append(
					loginService);

			response.sendRedirect(response.encodeRedirectURL(sb.toString()));
			return;
		}

		Authorization authToken = null;
		try {
			authToken = AuthorizationFactory.createAuthorization(loginCode,
					loginPassword, loginService);
		} catch (AuthorizationException e) {
			model.setLoginStatus(e.getLoginCode());
		}

		if (authToken != null && !authToken.isAnonymous()) {
			session.setAttribute(Constants.AUTHORIZATION_TOKEN, authToken);
			model.setLoginStatus(200);
		} else {
			StringBuilder sb = new StringBuilder(errorPage);
			if (errorPage.indexOf('?') >= 0) {
				sb.append("&");
			} else {
				sb.append("?");
			}

			sb.append(Constants.LOGIN_CODE).append('=').append(
					model.getLoginStatus()).append('&').append(
					Constants.LOGIN_NAME).append('=').append(loginCode).append(
					'&').append(Constants.LOGIN_SERVICE).append('=').append(
					loginService);

			response.sendRedirect(response.encodeRedirectURL(sb.toString()));
			return;
		}

		String returnUrl = request.getParameter("returnUrl");
		if (returnUrl == null || "".equals(returnUrl)) {
			returnUrl = loginedPage;
		}

		StringBuilder sb = new StringBuilder(returnUrl);
		if (returnUrl.indexOf('?') >= 0) {
			sb.append("&");
		} else {
			sb.append("?");
		}

		sb.append(Constants.LOGIN_CODE).append('=').append(
				model.getLoginStatus()).append('&')
				.append(Constants.LOGIN_NAME).append('=').append(loginCode)
				.append('&').append(Constants.LOGIN_SERVICE).append('=')
				.append(loginService);

		response.sendRedirect(response.encodeRedirectURL(sb.toString()));
	}

	/*
	 * 初始化。
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		loginNameKey = config.getInitParameter(LOGIN_NAME_KEY);
		if (loginNameKey == null) {
			loginNameKey = DEFAULT_LOGIN_NAME;
		}

		loginPasswordKey = config.getInitParameter(LOGIN_PASSWORD_KEY);
		if (loginPasswordKey == null) {
			loginPasswordKey = DEFAULT_LOGIN_PASSWORD;
		}

		loginServiceKey = config.getInitParameter(LOGIN_SERVICE_KEY);
		if (loginServiceKey == null) {
			loginServiceKey = DEFAULT_LOGIN_SERVICE;
		}

		if (config.getInitParameter(VALIDATE_IMAGE_ENABLE_KEY) != null) {
			validateImageEnabled = Boolean.valueOf(config
					.getInitParameter(VALIDATE_IMAGE_ENABLE_KEY));
		}

		validateImage = config.getInitParameter(VALIDATE_IMAGE_KEY);
		if (validateImage == null) {
			validateImage = DEFAULT_VALIDATE_IMAGE;
		}

		if (config.getInitParameter(DEFAULT_LOGINED_KEY) != null) {
			loginedPage = config.getInitParameter(DEFAULT_LOGINED_KEY);
		}

		if (config.getInitParameter(DEFAULT_LOGIN_ERROR_KEY) != null) {
			loginErrorPage = config.getInitParameter(DEFAULT_LOGIN_ERROR_KEY);
		}
	}

}
