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
package com.microbrain.cosmos.web.tags.system;

import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.CosmosFactoryException;
import com.microbrain.cosmos.core.config.Configuration;
import com.microbrain.cosmos.core.config.ConfigurationException;
import com.microbrain.cosmos.core.domain.CosmosDomain;

/**
 * <p>
 * �г�������ı�ǩ��ͨ������ϵͳ�����࣬���ϵͳ���õ������򣬽���ҳ����ʾ��
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see javax.servlet.jsp.tagext.TagSupport
 * @see com.microbrain.cosmos.core.domain.CosmosDomain
 * @see com.microbrain.cosmos.core.CosmosFactory
 * @since CFDK 1.0
 */
public class DomainTag extends TagSupport {

	/**
	 * ���л�ʱ�����кš�
	 */
	private static final long serialVersionUID = -609428257516775464L;

	/**
	 * �����Ƽ���
	 */
	private static final String DOMAIN_KEY = "domain";

	/**
	 * ������ࡣ
	 */
	private Iterator<CosmosDomain> domains = null;

	/*
	 * ��ǩ�����֮��
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doAfterBody()
	 */
	@Override
	public int doAfterBody() throws JspException {
		if (domains.hasNext()) {
			pageContext.setAttribute(DOMAIN_KEY, domains.next());
			return EVAL_BODY_AGAIN;
		}

		return SKIP_BODY;
	}

	/*
	 * ��ǩ������
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {
		pageContext.removeAttribute(DOMAIN_KEY);
		return SKIP_BODY;
	}

	/*
	 * ��ǩ��ʼ��
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			throw new JspException(e);
		}

		Configuration config = factory.lookupConfig();
		try {
			domains = config.getDomains().values().iterator();
		} catch (ConfigurationException e) {
			throw new JspException(e);
		}

		if (domains.hasNext()) {
			pageContext.setAttribute(DOMAIN_KEY, domains.next());
			return EVAL_BODY_AGAIN;
		}

		return SKIP_BODY;
	}

}
