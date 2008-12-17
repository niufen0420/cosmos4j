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
package com.microbrain.cosmos.core.command.utils;

import java.util.regex.Pattern;

/**
 * <p>
 * 提供一些字符串处理的工具，其中比较重要的是<code>decorate</code>
 * 方法，用来将类似于TABLE_COLUMN这样类型的字符串转换为tableColumn这样的java类型。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.StandardCosmosResult
 * @see com.microbrain.cosmos.core.command.CosmosDynamicList
 * @see com.microbrain.cosmos.core.command.CosmosDynamicObject
 * @since CFDK 1.0
 */
public class StringUtils {

	/**
	 * 判断名称是否有错误的正则表达式。
	 */
	private static final Pattern BAD_NAME_PATTERN = Pattern
			.compile("[\\p{Punct}\\s&&[^_\\.]]");

	/**
	 * 私有化构造函数。
	 */
	private StringUtils() {
	}

	/**
	 * 装饰一个名字，变成java类似的表示方式。
	 * 
	 * @param name
	 *            原始字符串。
	 * @return 处理之后的字符串。
	 */
	public static String decorate(String name) {
		String rlt = name;
		if (!BAD_NAME_PATTERN.matcher(name).find()) {
			if ((name.indexOf(".") == -1) && (name.indexOf("_") == -1)) {
				boolean isWordCaptial = true;

				for (int i = 0; i < name.length(); i++) {
					if (Character.isLowerCase(name.charAt(i))) {
						isWordCaptial = false;
						break;
					}
				}
				if (isWordCaptial) {
					name = name.toLowerCase();
				}
				return name;
			}

			StringBuilder newName = new StringBuilder();
			String lowerCaseName = name.toLowerCase();
			for (int i = 0; i < lowerCaseName.length(); i++) {
				if ((lowerCaseName.charAt(i) == '_')
						|| (lowerCaseName.charAt(i) == '.')) {
					if ((i > 0) && (i + 1 < lowerCaseName.length())) {
						newName.append(Character.toUpperCase(lowerCaseName
								.charAt(++i)));
					}
				} else {
					newName.append(lowerCaseName.charAt(i));
				}
			}
			rlt = newName.toString();
		}

		return rlt;
	}

}
