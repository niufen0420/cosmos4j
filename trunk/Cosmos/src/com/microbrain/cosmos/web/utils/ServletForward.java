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
 * ҳ����ת�ķ�װ�࣬��װ��Ҫ��ת��·�������ơ��Ƿ�ת���Լ�����ģ�顣
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.web.utils.ForwardUtil
 * @since CFDK 1.0
 */
public class ServletForward {

	/**
	 * Ҫ��ת��·����
	 */
	protected String path = null;

	/**
	 * ���ơ�
	 */
	protected String name = null;

	/**
	 * �Ƿ�ת��
	 */
	protected boolean redirect = false;

	/**
	 * ����ģ�顣
	 */
	protected String module = null;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public ServletForward() {
		this(null, false);
	}

	/**
	 * ���캯����
	 * 
	 * @param path
	 *            Ҫ��ת��·����
	 */
	public ServletForward(String path) {
		this(path, false);
	}

	/**
	 * ���캯����
	 * 
	 * @param path
	 *            Ҫ��ת��·����
	 * @param redirect
	 *            �Ƿ�ת��
	 */
	public ServletForward(String path, boolean redirect) {
		this.path = path;
		this.redirect = redirect;
	}

	/**
	 * ���캯����
	 * 
	 * @param name
	 *            ���ơ�
	 * @param path
	 *            Ҫ��ת��·����
	 * @param redirect
	 *            �Ƿ�ת��
	 */
	public ServletForward(String name, String path, boolean redirect) {
		this.name = name;
		this.path = path;
		this.redirect = redirect;
	}

	/**
	 * ���캯����
	 * 
	 * @param name
	 *            ���ơ�
	 * @param path
	 *            Ҫ��ת��·����
	 * @param redirect
	 *            �Ƿ�ת��
	 * @param module
	 *            ����ģ�顣
	 */
	public ServletForward(String name, String path, boolean redirect,
			String module) {
		this.name = name;
		this.path = path;
		this.redirect = redirect;
		this.module = module;
	}

	/**
	 * ���캯����
	 * 
	 * @param copyMe
	 *            Ҫ���ƵĶ���
	 */
	public ServletForward(ServletForward copyMe) {
		this(copyMe.getName(), copyMe.getPath(), copyMe.isRedirect(), copyMe
				.getModule());
	}

	/**
	 * �������ģ�顣
	 * 
	 * @return ����ģ�顣
	 */
	public String getModule() {
		return module;
	}

	/**
	 * ��������ģ�顣
	 * 
	 * @param module
	 *            ����ģ�顣
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * ������ơ�
	 * 
	 * @return ���ơ�
	 */
	public String getName() {
		return name;
	}

	/**
	 * �������ơ�
	 * 
	 * @param name
	 *            ���ơ�
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ���Ҫ��ת��·����
	 * 
	 * @return Ҫ��ת��·����
	 */
	public String getPath() {
		return path;
	}

	/**
	 * ����Ҫ��ת��·����
	 * 
	 * @param path
	 *            Ҫ��ת��·����
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * ����Ƿ�ת��
	 * 
	 * @return �Ƿ�ת��
	 */
	public boolean isRedirect() {
		return redirect;
	}

	/**
	 * �����Ƿ�ת��
	 * 
	 * @param redirect
	 *            �Ƿ�ת��
	 */
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

}
