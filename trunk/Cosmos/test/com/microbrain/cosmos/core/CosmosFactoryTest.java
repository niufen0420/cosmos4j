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
package com.microbrain.cosmos.core;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.microbrain.cosmos.core.auth.Authorization;
import com.microbrain.cosmos.core.auth.AuthorizationException;
import com.microbrain.cosmos.core.auth.AuthorizationFactory;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.CosmosDynamicObject;
import com.microbrain.cosmos.core.command.CosmosExecuteException;
import com.microbrain.cosmos.core.command.CosmosResult;
import com.microbrain.cosmos.core.config.ConfigurationException;
import com.microbrain.cosmos.core.permission.CosmosPermissionException;
import com.microbrain.cosmos.core.sal.ServiceException;

/**
 * @author Richard Sun
 * 
 */
public class CosmosFactoryTest extends TestCase {

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		try {
			CosmosFactory.initFactory("E:/Projects/Java/Cosmos/home",
					"/etc/cosmos-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		super.setUp();
	}

	/**
	 * 
	 */
	public void testConfigSql() {
		long start = System.currentTimeMillis();
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CosmosCommand command = null;
		CosmosResult result = null;
		try {
			command = factory.getCommand("direct", "select_command");
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("dom", "direct");
			args.put("tt", "cccc");
			args.put("test", "cc");

			result = command.execute(args);
		} catch (CosmosExecuteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("span: " + (end - start));
		Collection<CosmosDynamicObject> list = result.getList();

		for (CosmosDynamicObject row : list) {
			System.out.println("-----------------------");

			for (String key : row.keySet()) {
				System.out.print(key + ": " + row.get(key) + "\t");
			}
			System.out.println();
		}

		System.out
				.println("#################################################################");
		System.out.println();
	}

	/**
	 * 
	 */
	public void testSql() {
		long start = System.currentTimeMillis();
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CosmosCommand command = null;
		CosmosResult result = null;
		try {
			command = factory.getCommand("select_tb_cos_command");
		} catch (CosmosFactoryException e) {
		}

		try {
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("dom", "dm");
			args.put("tt", "cccc");
			args.put("test", "cc");

			result = command.execute(args);
		} catch (CosmosExecuteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("span: " + (end - start));
		Collection<CosmosDynamicObject> list = result.getList();

		for (CosmosDynamicObject row : list) {
			System.out.println("-----------------------");

			for (String key : row.keySet()) {
				System.out.print(key + ": " + row.get(key) + "\t");
			}
			System.out.println();
		}

		System.out
				.println("#################################################################");
		System.out.println();
	}

	/**
	 * 
	 */
	public void testSp() {
		long start = System.currentTimeMillis();
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CosmosCommand command = null;
		CosmosResult result = null;
		try {
			command = factory.getCommand("SpListExecuters");
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("dom", "direct");
			args.put("TT_GG", "cccc");
			args.put("test", "cc");

			result = command.execute(args);
		} catch (CosmosExecuteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("span: " + (end - start));
		Collection<CosmosDynamicObject> list = result.getList();

		for (CosmosDynamicObject row : list) {
			System.out.println("-----------------------");

			for (String key : row.keySet()) {
				System.out.print(key + ": " + row.get(key) + "\t");
			}
			System.out.println();
		}

		CosmosDynamicObject var = result.getVarObject();
		System.out.println("ttGg: " + var.get("ttGg"));
		System.out.println("gg: " + var.get("gg"));

		System.out
				.println("#################################################################");
		System.out.println();
	}

	/**
	 * 
	 */
	public void testSpPopulate() {
		long start = System.currentTimeMillis();
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CosmosCommand command = null;
		CosmosResult result = null;
		try {
			command = factory.getCommand("SpListExecuters");
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("dom", "direct");
			args.put("TT_GG", "cccc");
			args.put("test", "cc");

			result = command.execute(args);
		} catch (CosmosExecuteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("span: " + (end - start));
		Collection<CoreCommand> list = result.getList(CoreCommand.class);

		for (CoreCommand com : list) {
			System.out.println("-----------------------");

			System.out.println("updateTime: " + com.getUpdateTime()
					+ "\tupdateBy: " + com.getUpdateBy() + "\tcreateTime: "
					+ com.getCreateTime() + "\tname: " + com.getName()
					+ "\tcreateBy: " + com.getCreateBy() + "\tid: "
					+ com.getId());
		}

		CosmosDynamicObject var = result.getVarObject();
		System.out.println("ttGg: " + var.get("ttGg"));
		System.out.println("gg: " + var.get("gg"));

		System.out
				.println("#################################################################");
		System.out.println();
	}

	/**
	 * 
	 */
	public void testServicePopulate() {
		long start = System.currentTimeMillis();
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CosmosResult result = null;
		Direct direct = null;
		try {
			direct = factory.getService(Direct.class);
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			result = direct.listCommands("direct", "aa");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CosmosPermissionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("span: " + (end - start));
		Collection<CoreCommand> list = result.getList(CoreCommand.class);

		for (CoreCommand com : list) {
			System.out.println("-----------------------");

			System.out.println("updateTime: " + com.getUpdateTime()
					+ "\tupdateBy: " + com.getUpdateBy() + "\tcreateTime: "
					+ com.getCreateTime() + "\tname: " + com.getName()
					+ "\tcreateBy: " + com.getCreateBy() + "\tid: "
					+ com.getId());
		}

		OutVariable var = result.getVarObject(OutVariable.class);
		System.out.println("ttGg: " + var.getTtGg());
		System.out.println("gg: " + var.getGg());

		System.out
				.println("#################################################################");
		System.out.println();
	}

	/**
	 * 
	 */
	public void testLogin() {
		Authorization auth = null;
		try {
			auth = AuthorizationFactory.createAuthorization("7777", "545645",
					"service");
		} catch (AuthorizationException e) {
			// TODO Auto-generated catch block
			System.out.println("system authorization exception: "
					+ e.getLoginCode());
			return;
		}

		System.out.println("auth: " + auth.getPassportId());

		long start = System.currentTimeMillis();
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CosmosResult result = null;
		Direct direct = null;
		try {
			direct = factory.getService(auth, Direct.class);
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			result = direct.listCommands("direct", "aa");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("span: " + (end - start));
		Collection<CoreCommand> list = result.getList(CoreCommand.class);

		for (CoreCommand com : list) {
			System.out.println("-----------------------");

			System.out.println("updateTime: " + com.getUpdateTime()
					+ "\tupdateBy: " + com.getUpdateBy() + "\tcreateTime: "
					+ com.getCreateTime() + "\tname: " + com.getName()
					+ "\tcreateBy: " + com.getCreateBy() + "\tid: "
					+ com.getId());
		}

		OutVariable var = result.getVarObject(OutVariable.class);
		System.out.println("ttGg: " + var.getTtGg());
		System.out.println("gg: " + var.getGg());

		System.out
				.println("#################################################################");
		System.out.println();
	}

}
