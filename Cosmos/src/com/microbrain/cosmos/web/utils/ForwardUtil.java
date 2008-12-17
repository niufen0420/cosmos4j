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
package com.microbrain.cosmos.web.utils;

/**
 * <p>
 * ����<code>forward</code>ҳ�����д���򣬽�һ����д�õ��ַ���ת��Ϊ���Ӧ��
 * <code>com.microbrain.cosmos.web.utils.ServletForward</code>ʵ�֡�
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.web.utils.ServletForward
 * @since CFDK 1.0
 */
public class ForwardUtil {

	/**
	 * ˽�л����캯����
	 */
	private ForwardUtil() {
	}

	/**
	 * ���ݹ���һ����תҳ���·��ת��Ϊһ��
	 * <code>com.microbrain.cosmos.web.utils.ServletForward</code>����
	 * 
	 * @param forward
	 *            ��תҳ���ַ�����
	 * @return <code>com.microbrain.cosmos.web.utils.ServletForward</code>����
	 */
	public static ServletForward getForward(String forward) {
		ServletForward servletForward = new ServletForward();
		boolean redirect = false;
		if (forward.endsWith(".r")) {
			redirect = true;
			forward = forward.substring(0, forward.length() - 2);
		}
		servletForward.setRedirect(redirect);

		if (forward.indexOf(".") > 0) {
			servletForward.setModule("/");
		}
		servletForward.setPath("/" + forward.replace('.', '/') + ".jsp");
		return servletForward;
	}

}
