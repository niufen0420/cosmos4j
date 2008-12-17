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
 * ���������ҳ�б�
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
	 * ���л�ʱ�����кš�
	 */
	private static final long serialVersionUID = -291857911178556838L;

	/**
	 * ��ʾ����ҳ��
	 */
	private int pageNumShown;

	/**
	 * ǰ��ҳ��
	 */
	private int prePages;

	/**
	 * ��ǰҳ����
	 */
	private int currentPageNum;

	/**
	 * ǰ��ҳ����
	 */
	private String pre_pages;

	/**
	 * ��־λ��
	 */
	private String flag = null;

	/**
	 * ҳ����
	 */
	private String pageKey = "prePages";

	/*
	 * ��ʼ��ǩ��
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
	 * ������ǩ��
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
	 * ��ñ�־λ��
	 * 
	 * @return ��־λ��
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * ���ñ�־λ��
	 * 
	 * @param flag
	 *            ��־λ��
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * ���ҳ����
	 * 
	 * @return ҳ����
	 */
	public String getPageKey() {
		return pageKey;
	}

	/**
	 * ����ҳ����
	 * 
	 * @param pageKey
	 *            ҳ����
	 */
	public void setPageKey(String pageKey) {
		this.pageKey = pageKey;
	}

}
