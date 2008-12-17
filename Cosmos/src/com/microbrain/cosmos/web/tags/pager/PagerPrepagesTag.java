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
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * <p>
 * 用来计算分页列表。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see javax.servlet.jsp.tagext.TagSupport
 * @see com.microbrain.cosmos.web.tags.pager.PagerInfoTag
 * @since CFDK 1.0
 */
public class PagerPrepagesTag extends TagSupport {

	/**
	 * 序列化时的序列号。
	 */
	private static final long serialVersionUID = -291857911178556838L;

	/**
	 * 显示多少页。
	 */
	private int pageNumShown;

	/**
	 * 前几页。
	 */
	private int prePages;

	/**
	 * 当前页数。
	 */
	private int currentPageNum;

	/**
	 * 前几页键。
	 */
	private String pre_pages;

	/**
	 * 标志位。
	 */
	private String flag = null;

	/**
	 * 页键。
	 */
	private String pageKey = "prePages";

	/*
	 * 开始标签。
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		PagerInfoTag tag = (PagerInfoTag) findAncestorWithClass(this,
				PagerInfoTag.class);
		if (tag == null) {
			throw new JspTagException("The root tag not found!");
		}

		try {
			currentPageNum = Integer.parseInt(tag.getCurrentPageNum());
			pageNumShown = Integer.parseInt(tag.getPageNumShown());
		} catch (Exception e) {
			throw new JspException(e);
		}

		if (0 == currentPageNum % pageNumShown) {
			prePages = (currentPageNum / pageNumShown - 2) * pageNumShown + 1;
		} else {
			prePages = (currentPageNum / pageNumShown - 1) * pageNumShown + 1;
		}

		pre_pages = tag.getUrl() + prePages;
		if (currentPageNum <= pageNumShown && flag == null) {
			return SKIP_BODY;
		} else if (currentPageNum <= pageNumShown && flag != null) {
			pageContext.setAttribute(pageKey, pre_pages);
			pageContext.setAttribute("Pager_pageNumShown", pageNumShown);
			pageContext.setAttribute(flag, false);
			return EVAL_BODY_INCLUDE;
		} else if (currentPageNum > pageNumShown && flag == null) {
			pageContext.setAttribute(pageKey, pre_pages);
			pageContext.setAttribute("Pager_pageNumShown", pageNumShown);
			return EVAL_BODY_INCLUDE;
		} else {
			pageContext.setAttribute(pageKey, pre_pages);
			pageContext.setAttribute("Pager_pageNumShown", pageNumShown);
			pageContext.setAttribute(flag, true);
			return EVAL_BODY_INCLUDE;
		}
	}

	/*
	 * 结束标签。
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		if (currentPageNum <= pageNumShown && flag == null) {
			return EVAL_PAGE;
		} else if (currentPageNum > pageNumShown && flag == null) {
			pageContext.removeAttribute(pageKey);
			pageContext.removeAttribute("Pager_pageNumShown");
			return EVAL_PAGE;
		} else {
			pageContext.removeAttribute(pageKey);
			pageContext.removeAttribute("Pager_pageNumShown");
			pageContext.removeAttribute(flag);
			return EVAL_PAGE;
		}
	}

	/**
	 * 获得标志位。
	 * 
	 * @return 标志位。
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * 设置标志位。
	 * 
	 * @param flag
	 *            标志位。
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 获得页键。
	 * 
	 * @return 页键。
	 */
	public String getPageKey() {
		return pageKey;
	}

	/**
	 * 设置页键。
	 * 
	 * @param pageKey
	 *            页键。
	 */
	public void setPageKey(String pageKey) {
		this.pageKey = pageKey;
	}

}
