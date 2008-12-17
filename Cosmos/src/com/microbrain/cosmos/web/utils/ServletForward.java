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
 * 页面跳转的封装类，包装了要跳转的路径、名称、是否转向、以及所属模块。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.web.utils.ForwardUtil
 * @since CFDK 1.0
 */
public class ServletForward {

	/**
	 * 要跳转的路径。
	 */
	protected String path = null;

	/**
	 * 名称。
	 */
	protected String name = null;

	/**
	 * 是否转向。
	 */
	protected boolean redirect = false;

	/**
	 * 所属模块。
	 */
	protected String module = null;

	/**
	 * 默认构造函数。
	 */
	public ServletForward() {
		this(null, false);
	}

	/**
	 * 构造函数。
	 * 
	 * @param path
	 *            要跳转的路径。
	 */
	public ServletForward(String path) {
		this(path, false);
	}

	/**
	 * 构造函数。
	 * 
	 * @param path
	 *            要跳转的路径。
	 * @param redirect
	 *            是否转向。
	 */
	public ServletForward(String path, boolean redirect) {
		this.path = path;
		this.redirect = redirect;
	}

	/**
	 * 构造函数。
	 * 
	 * @param name
	 *            名称。
	 * @param path
	 *            要跳转的路径。
	 * @param redirect
	 *            是否转向。
	 */
	public ServletForward(String name, String path, boolean redirect) {
		this.name = name;
		this.path = path;
		this.redirect = redirect;
	}

	/**
	 * 构造函数。
	 * 
	 * @param name
	 *            名称。
	 * @param path
	 *            要跳转的路径。
	 * @param redirect
	 *            是否转向。
	 * @param module
	 *            所属模块。
	 */
	public ServletForward(String name, String path, boolean redirect,
			String module) {
		this.name = name;
		this.path = path;
		this.redirect = redirect;
		this.module = module;
	}

	/**
	 * 构造函数。
	 * 
	 * @param copyMe
	 *            要复制的对象。
	 */
	public ServletForward(ServletForward copyMe) {
		this(copyMe.getName(), copyMe.getPath(), copyMe.isRedirect(), copyMe
				.getModule());
	}

	/**
	 * 获得所属模块。
	 * 
	 * @return 所属模块。
	 */
	public String getModule() {
		return module;
	}

	/**
	 * 设置所属模块。
	 * 
	 * @param module
	 *            所属模块。
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * 获得名称。
	 * 
	 * @return 名称。
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称。
	 * 
	 * @param name
	 *            名称。
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获得要跳转的路径。
	 * 
	 * @return 要跳转的路径。
	 */
	public String getPath() {
		return path;
	}

	/**
	 * 设置要跳转的路径。
	 * 
	 * @param path
	 *            要跳转的路径。
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 获得是否转向。
	 * 
	 * @return 是否转向。
	 */
	public boolean isRedirect() {
		return redirect;
	}

	/**
	 * 设置是否转向。
	 * 
	 * @param redirect
	 *            是否转向。
	 */
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

}
