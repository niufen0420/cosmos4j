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
package com.microbrain.cosmos.web.tags.pager;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * <p>
 * 用来计算分页时所需要的一些数据。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see javax.servlet.jsp.tagext.TagSupport
 * @since CFDK 1.0
 */
public class PagerInfoTag extends TagSupport {

	/**
	 * 序列化时的序列号。
	 */
	private static final long serialVersionUID = -8727628763742569538L;

	/**
	 * 每个页面链接的共同<code>URL</code>。
	 */
	private String url = null;

	/**
	 * 页键。
	 */
	private String varPage = "page";

	/**
	 * 总数量。
	 */
	private String totalItems = null;

	/**
	 * 当前页数。
	 */
	private String currentPageNum = "1";

	/**
	 * 每页显示数量。
	 */
	private String numPerPage = "10";

	/**
	 * 显示多少页。
	 */
	private String pageNumShown = "10";

	/**
	 * 数量。
	 */
	private int count = 0;

	/*
	 * 开始标签。
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		if (totalItems == null || "".equals(totalItems)
				|| Integer.parseInt(totalItems) == 0) {
			return SKIP_BODY;
		}

		if (currentPageNum == null || "".equals(currentPageNum)) {
			currentPageNum = "1";
		}

		if (numPerPage == null || "".equals(numPerPage)) {
			numPerPage = "20";
		}

		if (pageNumShown == null || "".equals(pageNumShown)) {
			pageNumShown = "10";
		}

		try {
			count = Integer.parseInt(totalItems) / Integer.parseInt(numPerPage);
			if ((Integer.parseInt(totalItems) % Integer.parseInt(numPerPage)) != 0) {
				count++;
			}

			if (Integer.parseInt(currentPageNum) < 1) {
				currentPageNum = "1";
			}

			if (Integer.parseInt(currentPageNum) > count) {
				currentPageNum = String.valueOf(count);
			}
		} catch (Exception e) {
			throw new JspException(e);
		}

		if (url != null) {
			if (url.indexOf('?') >= 0) {
				url = url + '&' + varPage + '=';
			} else {
				url = url + '?' + varPage + '=';
			}
		}

		pageContext.setAttribute("url", url);
		pageContext.setAttribute("Pager_currentPageNum", currentPageNum);
		pageContext.setAttribute("Pager_count", count);
		pageContext.setAttribute("Pager_totalItems", totalItems);
		return EVAL_BODY_INCLUDE;
	}

	/*
	 * 标签结束。
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		pageContext.removeAttribute("Pager_currentPageNum");
		pageContext.removeAttribute("Pager_count");
		pageContext.removeAttribute("Pager_totalItems");
		pageContext.removeAttribute("url");
		return EVAL_PAGE;
	}

	/**
	 * 获得URL。
	 * 
	 * @return url。
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置URL。
	 * 
	 * @param url
	 *            页面URL。
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获得总数量。
	 * 
	 * @return 总数量。
	 */
	public String getTotalItems() {
		return totalItems;
	}

	/**
	 * 设置总数量。
	 * 
	 * @param totalItems
	 *            总数量。
	 */
	public void setTotalItems(String totalItems) {
		this.totalItems = totalItems;
	}

	/**
	 * 获得当前页。
	 * 
	 * @return 当前页。
	 */
	public String getCurrentPageNum() {
		return currentPageNum;
	}

	/**
	 * 设置当前页。
	 * 
	 * @param currentPageNum
	 *            当前页。
	 */
	public void setCurrentPageNum(String currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	/**
	 * 获得每页多少条。
	 * 
	 * @return 每页多少条。
	 */
	public String getNumPerPage() {
		return numPerPage;
	}

	/**
	 * 设置每页多少条。
	 * 
	 * @param numPerPage
	 *            每页多少条。
	 */
	public void setNumPerPage(String numPerPage) {
		this.numPerPage = numPerPage;
	}

	/**
	 * 获得显示多少页。
	 * 
	 * @return 显示多少页。
	 */
	public String getPageNumShown() {
		return pageNumShown;
	}

	/**
	 * 设置显示多少页。
	 * 
	 * @param pageNumShown
	 *            显示多少页。
	 */
	public void setPageNumShown(String pageNumShown) {
		this.pageNumShown = pageNumShown;
	}

	/**
	 * 获得页键。
	 * 
	 * @return 页键。
	 */
	public String getVarPage() {
		return varPage;
	}

	/**
	 * 设置页键。
	 * 
	 * @param varPage
	 *            页键。
	 */
	public void setVarPage(String varPage) {
		this.varPage = varPage;
	}

}
