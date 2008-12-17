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
 * �������㲿��ҳ�б�
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
	 * ���л�ʱ�����кš�
	 */
	private static final long serialVersionUID = 1757519602179918415L;

	/**
	 * ��������
	 */
	private int totaItems;

	/**
	 * ��ǰҳ����
	 */
	private int currentPageNum;

	/**
	 * ÿҳ�����С�
	 */
	private int numPerPage;

	/**
	 * ��ʾ����ҳ��
	 */
	private int pageNumShown;

	/**
	 * ��ʼҳ����
	 */
	private int from;

	/**
	 * ����ҳ����
	 */
	private int to;

	/**
	 * ҳ����
	 */
	private int pageNum;

	/**
	 * ��һҳ��
	 */
	private int lastPage;

	/**
	 * ҳ����
	 */
	private String pageKey = "pages";

	/**
	 * ҳ���ӡ�
	 */
	private String num_page;

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
	 * �����������
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
	 * ������ǩ��
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		pageContext.removeAttribute(pageKey);
		pageContext.removeAttribute("Pager_pageNum");
		return SKIP_BODY;
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
