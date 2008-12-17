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
 * ��ϵͳ������ص�<code>Servlet</code>����Ҫ�ṩ��ͳһ�����֤��صķ��������а�����¼�������֤��ȡ�
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
	 * ���л�ʱ�����кš�
	 */
	private static final long serialVersionUID = 7800134204070902732L;

	/**
	 * ��¼�û�������
	 */
	private static final String LOGIN_NAME_KEY = "com.jace.cosmos.authentiation.name";

	/**
	 * ��¼�����
	 */
	private static final String LOGIN_PASSWORD_KEY = "com.jace.cosmos.authentiation.password";

	/**
	 * �Ƿ�������֤�롣
	 */
	private static final String VALIDATE_IMAGE_ENABLE_KEY = "com.jace.cosmos.authentiation.validate.enable";

	/**
	 * ��֤��ļ���
	 */
	private static final String VALIDATE_IMAGE_KEY = "com.jace.cosmos.authentiation.validate";

	/**
	 * ��¼����ļ���
	 */
	private static final String LOGIN_SERVICE_KEY = "com.jace.cosmos.authentiation.service";

	/**
	 * Ĭ�ϵ�¼�ɹ��ļ���
	 */
	private static final String DEFAULT_LOGINED_KEY = "com.jace.cosmos.authentiation.logined";

	/**
	 * Ĭ�ϵ�¼ʧ�ܵļ���
	 */
	private static final String DEFAULT_LOGIN_ERROR_KEY = "com.jace.cosmos.authentiation.error";

	/**
	 * Ĭ�ϵ�¼������
	 */
	private static final String DEFAULT_LOGIN_NAME = "cosmos.name";

	/**
	 * Ĭ�ϵ�¼�������
	 */
	private static final String DEFAULT_LOGIN_PASSWORD = "cosmos.password";

	/**
	 * Ĭ�ϵ�¼�������
	 */
	private static final String DEFAULT_LOGIN_SERVICE = "cosmos.service";

	/**
	 * Ĭ����֤�����
	 */
	private static final String DEFAULT_VALIDATE_IMAGE = "cosmos.validate.image";

	/**
	 * ��֤��ͼ��
	 */
	private static final String WIDTH_KEY = "width";

	/**
	 * ��֤��ͼ�ߡ�
	 */
	private static final String HEIGHT_KEY = "height";

	/**
	 * ��¼������
	 */
	private String loginNameKey = null;

	/**
	 * ��¼�������
	 */
	private String loginPasswordKey = null;

	/**
	 * ��¼�������
	 */
	private String loginServiceKey = null;

	/**
	 * ��֤���Ƿ����á�
	 */
	private boolean validateImageEnabled = true;

	/**
	 * ��֤ͼ��
	 */
	private String validateImage = null;

	/**
	 * ��½�ɹ���Ĭ�Ͻ��档
	 */
	private String loginedPage = null;

	/**
	 * ��¼ʧ�ܵĽ��档
	 */
	private String loginErrorPage = null;

	/*
	 * �����ȡ��֤������
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
	 * �����¼����
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
	 * ��ʼ����
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
