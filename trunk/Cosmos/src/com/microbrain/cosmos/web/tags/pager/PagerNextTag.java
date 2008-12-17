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
 * 用来计算下一页。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see javax.servlet.jsp.tagext.TagSupport
 * @see com.microbrain.cosmos.web.tags.pager.PagerInfoTag
 * @since CFDK 1.0
 */
public class PagerNextTag extends TagSupport {

	/**
	 * 序列化时的序列号。
	 */
	private static final long serialVersionUID = 2585520376304429512L;

	/**
	 * 最后一页。
	 */
	private int lastPage;

	/**
	 * 下一页。
	 */
	private int nextPage;

	/**
	 * 总数量。
	 */
	private int totaItems;

	/**
	 * 总数量。
	 */
	private int numPerPage;

	/**
	 * 当前页数。
	 */
	private int currentPageNum;

	/**
	 * 下一页页的链接。
	 */
	private String nextUrl;

	/**
	 * 标志位。
	 */
	private String flag = null;

	/**
	 * 页键。
	 */
	private String pageKey = "nextPage";

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
			totaItems = Integer.parseInt(tag.getTotalItems());
			numPerPage = Integer.parseInt(tag.getNumPerPage());
			currentPageNum = Integer.parseInt(tag.getCurrentPageNum());
		} catch (Exception e) {
			throw new JspException(e);
		}

		lastPage = totaItems / numPerPage;
		if (0 != totaItems % numPerPage) {
			lastPage++;
		}

		nextUrl = tag.getUrl();
		if (flag == null && currentPageNum >= lastPage) {
			return SKIP_BODY;
		} else if (flag == null && currentPageNum < lastPage) {
			nextPage = currentPageNum + 1;
			pageContext.setAttribute(pageKey, nextUrl + nextPage);
			return EVAL_BODY_INCLUDE;
		} else if (flag != null && currentPageNum >= lastPage) {
			nextUrl = tag.getUrl();
			pageContext.setAttribute(pageKey, nextUrl + lastPage);
			pageContext.setAttribute(flag, false);
			return EVAL_BODY_INCLUDE;
		} else {
			nextUrl = tag.getUrl();
			nextPage = currentPageNum + 1;
			pageContext.setAttribute(pageKey, nextUrl + nextPage);
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
		if (flag == null && currentPageNum >= lastPage) {
			return EVAL_PAGE;
		} else if (flag == null && currentPageNum < lastPage) {
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
