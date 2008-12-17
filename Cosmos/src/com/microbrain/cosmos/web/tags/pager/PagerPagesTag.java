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
 * 用来计算部分页列表。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see javax.servlet.jsp.tagext.TagSupport
 * @see com.microbrain.cosmos.web.tags.pager.PagerInfoTag
 * @since CFDK 1.0
 */
public class PagerPagesTag extends TagSupport {

	/**
	 * 序列化时的序列号。
	 */
	private static final long serialVersionUID = 1757519602179918415L;

	/**
	 * 总数量。
	 */
	private int totaItems;

	/**
	 * 当前页数。
	 */
	private int currentPageNum;

	/**
	 * 每页多少行。
	 */
	private int numPerPage;

	/**
	 * 显示多少页。
	 */
	private int pageNumShown;

	/**
	 * 开始页数。
	 */
	private int from;

	/**
	 * 结束页数。
	 */
	private int to;

	/**
	 * 页数。
	 */
	private int pageNum;

	/**
	 * 上一页。
	 */
	private int lastPage;

	/**
	 * 页键。
	 */
	private String pageKey = "pages";

	/**
	 * 页链接。
	 */
	private String num_page;

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
			currentPageNum = Integer.parseInt(tag.getCurrentPageNum());
			numPerPage = Integer.parseInt(tag.getNumPerPage());
			pageNumShown = Integer.parseInt(tag.getPageNumShown());
		} catch (Exception e) {
			throw new JspException(e);
		}

		lastPage = totaItems / numPerPage;
		if (totaItems % numPerPage != 0) {
			lastPage++;
		}

		if (0 == currentPageNum % pageNumShown) {
			from = (currentPageNum / pageNumShown - 1) * pageNumShown + 1;
		} else {
			from = (currentPageNum / pageNumShown) * pageNumShown + 1;
		}

		if (from < 1) {
			from = 1;
		}

		to = from + pageNumShown - 1;

		if (to > lastPage)
			to = lastPage;

		pageNum = from;
		num_page = tag.getUrl();
		if (pageNum <= to) {
			pageContext.setAttribute("Pager_pageNum", pageNum);
			pageContext.setAttribute(pageKey, num_page + pageNum);
			return EVAL_BODY_AGAIN;
		} else {
			return SKIP_BODY;
		}
	}

	/*
	 * 内容体结束后。
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doAfterBody()
	 */
	public int doAfterBody() throws JspException {
		pageNum++;
		if (pageNum <= to) {
			pageContext.setAttribute("Pager_pageNum", pageNum);
			pageContext.setAttribute(pageKey, num_page + pageNum);
			return EVAL_BODY_AGAIN;
		} else {
			return SKIP_BODY;
		}
	}

	/*
	 * 结束标签。
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		pageContext.removeAttribute(pageKey);
		pageContext.removeAttribute("Pager_pageNum");
		return SKIP_BODY;
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
