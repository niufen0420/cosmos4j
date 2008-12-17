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
package com.microbrain.cosmos.web.executers;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.microbrain.cosmos.core.command.AbstractCosmosExecuter;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.CosmosExecuteException;
import com.microbrain.cosmos.core.command.CosmosMetaArgument;
import com.microbrain.cosmos.core.command.CosmosResult;
import com.microbrain.cosmos.core.command.StandardCosmosResult;

/**
 * <p>
 * �ϴ��ļ���ִ������ͨ�����ϴ��ļ���ִ������ִ�����ݿ��ִ���������������������һ���������ϴ��ļ����̡�
 * </p>
 * <p>
 * �ϴ��ļ���ִ��������common-upload����ˣ����ȻὫ�ļ��ϴ���һ����ʱĿ¼��Ȼ���ƶ���Ŀ��ص㡣
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.AbstractCosmosExecuter
 * @see com.microbrain.cosmos.dev.AbstractCosmosCommandManager
 * @since CFDK 1.0
 */
public class CosmosUploadFileExecuter extends AbstractCosmosExecuter {

	/**
	 * ���캯����
	 * 
	 * @param name
	 *            ִ�������ơ�
	 * @param label
	 *            ִ������ǩ��
	 * @param description
	 *            ִ����������
	 * @param category
	 *            ִ�������ࡣ
	 */
	public CosmosUploadFileExecuter(String name, String label,
			String description, String category) {
		super(name, label, description, category);
	}

	/*
	 * ִ���ϴ��ļ��Ĳ�����
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.CosmosExecuter#execute(com.microbrain
	 * .cosmos.core.command.CosmosCommand, java.util.Map)
	 */
	public CosmosResult execute(CosmosCommand command, Map<String, Object> args)
			throws CosmosExecuteException {
		CosmosResult result = new StandardCosmosResult(command);
		Collection<CosmosMetaArgument> metaArgs = command.listMetaArguments();
		try {
			for (CosmosMetaArgument metaArg : metaArgs) {
				Object arg = args.get(metaArg.getName());
				if (arg instanceof FileItem) {
					FileItem item = (FileItem) arg;
					item.write(new File(""));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
