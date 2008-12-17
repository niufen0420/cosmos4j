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
 * ���������¼�ҳ��
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see javax.servlet.jsp.tagext.TagSupport
 * @see com.microbrain.cosmos.web.tags.pager.PagerInfoTag
 * @since CFDK 1.0
 */
public class PagerNextpagesTag extends TagSupport {

	/**
	 * ���л�ʱ�����кš�
	 */
	private static final long serialVersionUID = -235962647410910499L;

	/**
	 * ��ʾ����ҳ��
	 */
	private int pageNumShown;

	/**
	 * �¼�ҳ��
	 */
	private int nextPages;

	/**
	 * ��ǰҳ����
	 */
	private int currentPageNum;

	/**
	 * ��������
	 */
	private int totaItems;

	/**
	 * ÿҳ�����С�
	 */
	private int numPerPage;

	/**
	 * ���һҳ��
	 */
	private int lastPage;

	/**
	 * �¼�ҳ��
	 */
	private String next_pages;

	/**
	 * ��־λ��
	 */
	private String flag = null;

	/**
	 * ҳ�����
	 */
	private String pageKey = "nextPages";

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
			totaItems = Integer.parseInt(tag.getTotalItems());
			numPerPage = Integer.parseInt(tag.getNumPerPage());
			currentPageNum = Integer.parseInt(tag.getCurrentPageNum());
			pageNumShown = Integer.parseInt(tag.getPageNumShown());
		} catch (Exception e) {
			throw new JspException(e);
		}

		lastPage = totaItems / numPerPage;
		if (totaItems % numPerPage != 0) {
			lastPage++;
		}

		nextPages = currentPageNum + 1;

		if (0 == currentPageNum % pageNumShown) {
			nextPages = (currentPageNum / pageNumShown) * pageNumShown + 1;
		} else {
			nextPages = (currentPageNum / pageNumShown + 1) * pageNumShown + 1;
		}

		next_pages = tag.getUrl() + nextPages;
		if (currentPageNum >= ((lastPage / pageNumShown) * pageNumShown)
				&& flag == null) {
			return SKIP_BODY;
		} else if (currentPageNum >= ((lastPage / pageNumShown) * pageNumShown)
				&& flag != null) {
			pageContext.setAttribute(pageKey, next_pages);
			pageContext.setAttribute("Pager_pageNumShown", pageNumShown);
			pageContext.setAttribute(flag, false);
			return EVAL_BODY_INCLUDE;
		} else if (currentPageNum < ((lastPage / pageNumShown) * pageNumShown)
				&& flag == null) {
			next_pages = tag.getUrl();
			pageContext.setAttribute(pageKey, next_pages);
			pageContext.setAttribute("Pager_pageNumShown", pageNumShown);
			return EVAL_BODY_INCLUDE;
		} else {
			next_pages = tag.getUrl();
			pageContext.setAttribute(pageKey, next_pages);
			pageContext.setAttribute("Pager_pageNumShown", pageNumShown);
			pageContext.setAttribute(flag, true);
			return EVAL_BODY_INCLUDE;
		}
	}

	/*
	 * �����������
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		if (currentPageNum >= ((lastPage / pageNumShown) * pageNumShown)
				&& flag == null) {
			return EVAL_PAGE;
		} else if (currentPageNum < ((lastPage / pageNumShown) * pageNumShown)
				&& flag == null) {

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
