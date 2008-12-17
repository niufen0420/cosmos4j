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
 * ��������ҳ����������ʾҳ������
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see javax.servlet.jsp.tagext.TagSupport
 * @see com.microbrain.cosmos.web.tags.pager.PagerInfoTag
 * @since CFDK 1.0
 */
public class PagerCountTag extends TagSupport {

	/**
	 * ���л�ʱ�����кš�
	 */
	private static final long serialVersionUID = -5059981992769245053L;

	/**
	 * ҳ������
	 */
	private int pageCount;

	/**
	 * ��������
	 */
	private int totaItems;

	/**
	 * ÿҳ��ʾ�����С�
	 */
	private int numPerPage;

	/**
	 * ���һҳ��
	 */
	private int lastPage;

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
		} catch (Exception e) {
			throw new JspException(e);
		}

		pageCount = 1;
		lastPage = totaItems / numPerPage;
		if (0 != totaItems % numPerPage) {
			lastPage++;
		}

		if (pageCount <= lastPage) {
			pageContext.setAttribute("Pager_pageCount", pageCount);
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
		pageCount++;
		if (pageCount <= lastPage) {
			pageContext.setAttribute("Pager_pageCount", pageCount);
			return EVAL_BODY_AGAIN;
		} else {
			return SKIP_BODY;
		}
	}

	/*
	 * ��ǩ������
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		pageContext.removeAttribute("Pager_pageCount");
		return SKIP_BODY;
	}

}
