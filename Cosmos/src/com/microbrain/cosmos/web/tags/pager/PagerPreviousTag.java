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
public class PagerPreviousTag extends TagSupport {

	/**
	 * 序列化时的序列号。
	 */
	private static final long serialVersionUID = -8087949809819566869L;

	/**
	 * 前一页。
	 */
	private int prePage;

	/**
	 * 当前页。
	 */
	private int currentPageNum;

	/**
	 * 前一页链接。
	 */
	private String preUrl;

	/**
	 * 标志位。
	 */
	private String flag = null;

	/**
	 * 页键。
	 */
	private String pageKey = "prePage";

	/*
	 * 标签开始。
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
		} catch (Exception e) {
			throw new JspException(e);
		}

		preUrl = tag.getUrl();
		if (flag == null && currentPageNum <= 1) {
			return SKIP_BODY;
		} else if (flag == null && currentPageNum > 1) {
			prePage = currentPageNum - 1;
			pageContext.setAttribute(pageKey, preUrl + prePage);
			return EVAL_BODY_INCLUDE;
		} else if (flag != null && currentPageNum <= 1) {
			pageContext.setAttribute(pageKey, preUrl + 1);
			pageContext.setAttribute(flag, false);
			return EVAL_BODY_INCLUDE;
		} else {
			prePage = currentPageNum - 1;
			pageContext.setAttribute(pageKey, preUrl + prePage);
			pageContext.setAttribute(flag, true);
			return EVAL_BODY_INCLUDE;
		}
	}

	/*
	 * 标签结束。
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		if (flag == null && currentPageNum <= 1) {
			return EVAL_PAGE;
		} else if (flag == null && currentPageNum > 1) {
			pageContext.removeAttribute(pageKey);
			return EVAL_PAGE;
		} else {
			pageContext.removeAttribute(pageKey);
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
