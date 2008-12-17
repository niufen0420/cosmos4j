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
 * 上传文件的执行器，通过将上传文件的执行器和执行数据库的执行器联合起来，可以完成一个完整的上传文件过程。
 * </p>
 * <p>
 * 上传文件的执行器基于common-upload，因此，首先会将文件上传至一个临时目录，然后移动到目标地点。
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
	 * 构造函数。
	 * 
	 * @param name
	 *            执行器名称。
	 * @param label
	 *            执行器标签。
	 * @param description
	 *            执行器描述。
	 * @param category
	 *            执行器分类。
	 */
	public CosmosUploadFileExecuter(String name, String label,
			String description, String category) {
		super(name, label, description, category);
	}

	/*
	 * 执行上传文件的操作。
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
