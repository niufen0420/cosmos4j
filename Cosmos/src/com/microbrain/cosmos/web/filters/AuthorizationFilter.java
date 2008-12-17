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
package com.microbrain.cosmos.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;

import com.microbrain.cosmos.core.auth.Authorization;
import com.microbrain.cosmos.core.auth.AuthorizationException;
import com.microbrain.cosmos.core.auth.AuthorizationFactory;
import com.microbrain.cosmos.core.constants.Constants;
import com.microbrain.cosmos.core.log.CosmosLogFactory;

/**
 * <p>
 * Ȩ����֤�Ĺ����������û���¼֮�󣬽���֤��Ϣ����<code>Session</code>
 * �У������������֮���ж��Ƿ�Ϊ�գ����Ϊ�գ��򴴽�һ����������֤������û�<code>Session</code>�С�
 * </p>
 * <p>
 * ����������ҪΪ�˱�֤�ں����Ĳ�������֤���󲻻�Ϊ�ա�
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see javax.servlet.Filter
 * @since CFDK 1.0
 */
public class AuthorizationFilter implements Filter {

	/**
	 * ��־��¼����
	 */
	private static final Log log = CosmosLogFactory.getLog();

	/*
	 * ���������������
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}

	/*
	 * ִ�в�����
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
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

		chain.doFilter(req, res);
	}

	/*
	 * ��ʼ����������
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
	}

}
