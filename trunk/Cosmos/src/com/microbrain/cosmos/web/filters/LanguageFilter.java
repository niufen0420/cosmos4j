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
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.microbrain.cosmos.core.constants.Constants;

/**
 * <p>
 * 读取请求中的语言选项，为后续的国际化操作做一些准备工作。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see javax.servlet.Filter
 * @since CFDK 1.0
 */
public class LanguageFilter implements Filter {

	/*
	 * 销毁这个过滤器。
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}

	/*
	 * 执行操作。
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		String hl = null;
		if ((hl = request.getParameter("hl")) == null) {
			if ((hl = (String) session
					.getAttribute(Constants.ACCEPT_LANGUAGE_KEY)) == null) {
				if (request.getCookies() != null) {
					for (Cookie cookie : request.getCookies()) {
						if (Constants.ACCEPT_LANGUAGE_KEY.equals(cookie
								.getName())) {
							hl = cookie.getValue();
							break;
						}
					}
				}

				if (hl == null) {
					Enumeration<String> en = request
							.getHeaders("Accept-Language");
					if (en.hasMoreElements()) {
						hl = en.nextElement();
						int first = -1;
						if ((first = hl.indexOf(",")) > 0) {
							hl = hl.substring(0, first);
						}
					}

					if (hl == null) {
						hl = "zh-cn";
					}
				}

				hl = "zh-cn";
				session.setAttribute(Constants.ACCEPT_LANGUAGE_KEY, hl);
			}
		} else {
			hl = "zh-cn";
			session.setAttribute(Constants.ACCEPT_LANGUAGE_KEY, hl);
		}

		chain.doFilter(req, res);
	}

	/*
	 * 初始化过滤器。
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {
	}

}
