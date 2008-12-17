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
 * ���������ҳʱ����Ҫ��һЩ���ݡ�
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see javax.servlet.jsp.tagext.TagSupport
 * @since CFDK 1.0
 */
public class PagerInfoTag extends TagSupport {

	/**
	 * ���л�ʱ�����кš�
	 */
	private static final long serialVersionUID = -8727628763742569538L;

	/**
	 * ÿ��ҳ�����ӵĹ�ͬ<code>URL</code>��
	 */
	private String url = null;

	/**
	 * ҳ����
	 */
	private String varPage = "page";

	/**
	 * ��������
	 */
	private String totalItems = null;

	/**
	 * ��ǰҳ����
	 */
	private String currentPageNum = "1";

	/**
	 * ÿҳ��ʾ������
	 */
	private String numPerPage = "10";

	/**
	 * ��ʾ����ҳ��
	 */
	private String pageNumShown = "10";

	/**
	 * ������
	 */
	private int count = 0;

	/*
	 * ��ʼ��ǩ��
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
	 * ��ǩ������
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
	 * ���URL��
	 * 
	 * @return url��
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * ����URL��
	 * 
	 * @param url
	 *            ҳ��URL��
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * �����������
	 * 
	 * @return ��������
	 */
	public String getTotalItems() {
		return totalItems;
	}

	/**
	 * ������������
	 * 
	 * @param totalItems
	 *            ��������
	 */
	public void setTotalItems(String totalItems) {
		this.totalItems = totalItems;
	}

	/**
	 * ��õ�ǰҳ��
	 * 
	 * @return ��ǰҳ��
	 */
	public String getCurrentPageNum() {
		return currentPageNum;
	}

	/**
	 * ���õ�ǰҳ��
	 * 
	 * @param currentPageNum
	 *            ��ǰҳ��
	 */
	public void setCurrentPageNum(String currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	/**
	 * ���ÿҳ��������
	 * 
	 * @return ÿҳ��������
	 */
	public String getNumPerPage() {
		return numPerPage;
	}

	/**
	 * ����ÿҳ��������
	 * 
	 * @param numPerPage
	 *            ÿҳ��������
	 */
	public void setNumPerPage(String numPerPage) {
		this.numPerPage = numPerPage;
	}

	/**
	 * �����ʾ����ҳ��
	 * 
	 * @return ��ʾ����ҳ��
	 */
	public String getPageNumShown() {
		return pageNumShown;
	}

	/**
	 * ������ʾ����ҳ��
	 * 
	 * @param pageNumShown
	 *            ��ʾ����ҳ��
	 */
	public void setPageNumShown(String pageNumShown) {
		this.pageNumShown = pageNumShown;
	}

	/**
	 * ���ҳ����
	 * 
	 * @return ҳ����
	 */
	public String getVarPage() {
		return varPage;
	}

	/**
	 * ����ҳ����
	 * 
	 * @param varPage
	 *            ҳ����
	 */
	public void setVarPage(String varPage) {
		this.varPage = varPage;
	}

}
