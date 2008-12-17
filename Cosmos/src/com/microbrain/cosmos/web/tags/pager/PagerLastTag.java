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
 * �����������һҳ��
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see javax.servlet.jsp.tagext.TagSupport
 * @see com.microbrain.cosmos.web.tags.pager.PagerInfoTag
 * @since CFDK 1.0
 */
public class PagerLastTag extends TagSupport {

	/**
	 * ���л�ʱ�����кš�
	 */
	private static final long serialVersionUID = 4245943708585254361L;

	/**
	 * ���һҳ��
	 */
	private int lastPage;

	/**
	 * ��������
	 */
	private int totaItems;

	/**
	 * ÿҳ�����С�
	 */
	private int numPerPage;

	/**
	 * ��ǰҳ����
	 */
	private int currentPageNum;

	/**
	 * ���һҳ�����ӡ�
	 */
	private String lastUrl;

	/**
	 * ҳ�����
	 */
	private String pageKey = "lastPage";

	/**
	 * ��־λ����־�Ƿ���Ҫ�ڵ������һҳʱ�������һҳ��
	 */
	private String flag = null;

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
		} catch (Exception e) {
			throw new JspException(e);
		}

		lastPage = totaItems / numPerPage;
		if (0 != totaItems % numPerPage) {
			lastPage++;
		}

		lastUrl = tag.getUrl() + lastPage;
		if (flag == null && currentPageNum == lastPage) {
			return SKIP_BODY;
		} else if (flag == null && currentPageNum != lastPage) {
			pageContext.setAttribute(pageKey, lastUrl);
			return EVAL_BODY_INCLUDE;
		} else if (flag != null && currentPageNum == lastPage) {
			pageContext.setAttribute(pageKey, lastUrl);
			pageContext.setAttribute(flag, false);
			return EVAL_BODY_INCLUDE;
		} else {
			pageContext.setAttribute(pageKey, lastUrl);
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
		if (flag == null && currentPageNum == lastPage) {
			return EVAL_PAGE;
		} else if (flag == null && currentPageNum != lastPage) {
			pageContext.removeAttribute(pageKey);
			return EVAL_PAGE;
		} else {
			pageContext.removeAttribute(flag);
			pageContext.removeAttribute(pageKey);
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
