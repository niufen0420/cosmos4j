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
package com.microbrain.cosmos.core.command;

/**
 * <p>
 * ����һ������������Ϣ����Щ��Ϣ���������ƣ����ͣ��Ƿ���ϣ��Ƿ�Ĭ�����ͣ���ǩ����������Ϣ��
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @see com.microbrain.cosmos.core.command.StandardCosmosMetaArgument
 * @since CFDK 1.0
 */
public class CosmosMetaCommand {

	/**
	 * �������͵����ơ�
	 */
	private String name = null;

	/**
	 * ������ʹ�õ�ʵ���ࡣ
	 */
	private Class<CosmosCommand> clazz = null;

	/**
	 * �������Ƿ���һ��������
	 */
	private Boolean composite = false;

	/**
	 * ���������Ƿ���Ĭ�ϵ��������͡�
	 */
	private Boolean defaultType = false;

	/**
	 * �����ǩ��
	 */
	private String label = null;

	/**
	 * �����������
	 */
	private String description = null;

	/**
	 * ���캯����������ʼ������ʱ�������������ࡣ
	 * 
	 * @param clazz
	 *            ������ʹ�õ��ࡣ
	 * @param composite
	 *            �����Ƿ���������͡�
	 * @param defaultType
	 *            ���������Ƿ�Ĭ�ϡ�
	 * @param description
	 *            �������͵�������
	 * @param label
	 *            �������͵ı�ǩ��
	 * @param name
	 *            �������͵����ơ�
	 */
	public CosmosMetaCommand(Class<CosmosCommand> clazz, Boolean composite,
			Boolean defaultType, String description, String label, String name) {
		this.clazz = clazz;
		this.composite = composite;
		this.defaultType = defaultType;
		this.description = description;
		this.label = label;
		this.name = name;
	}

	/**
	 * ����������͵����ơ�
	 * 
	 * @return �������͵����ơ�
	 */
	public String getName() {
		return name;
	}

	/**
	 * �����������͵����ơ�
	 * 
	 * @param name
	 *            �������͵����ơ�
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * �������������ʹ�õ��ࡣ
	 * 
	 * @return ��������������ʹ�õ��ࡣ
	 */
	public Class<CosmosCommand> getClazz() {
		return clazz;
	}

	/**
	 * ��������������ʹ�õ��ࡣ
	 * 
	 * @param clazz
	 *            ����������ʹ�õ��ࡣ
	 */
	public void setClazz(Class<CosmosCommand> clazz) {
		this.clazz = clazz;
	}

	/**
	 * ���������Ƿ���������͡�
	 * 
	 * @return �������͵�������ԡ�
	 */
	public Boolean getComposite() {
		return composite;
	}

	/**
	 * �����������͵�������ԡ�
	 * 
	 * @param composite
	 *            �������͵�������ԡ�
	 */
	public void setComposite(Boolean composite) {
		this.composite = composite;
	}

	/**
	 * ������������Ƿ�ΪĬ�ϵ��������͡�
	 * 
	 * @return �������͵�Ĭ�����ԡ�
	 */
	public Boolean getDefaultType() {
		return defaultType;
	}

	/**
	 * �������������Ƿ�ΪĬ�ϵ��������͡�
	 * 
	 * @param defaultType
	 *            �������͵�Ĭ�����ԡ�
	 */
	public void setDefaultType(Boolean defaultType) {
		this.defaultType = defaultType;
	}

	/**
	 * ����������͵ı�ǩ��
	 * 
	 * @return �����������͵ı�ǩ��
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * �����������͵ı�ǩ��
	 * 
	 * @param label
	 *            �������͵ı�ǩ��
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * ����������͵�������
	 * 
	 * @return �����������͵�������
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * �����������͵�������
	 * 
	 * @param description
	 *            �������͵�������
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
