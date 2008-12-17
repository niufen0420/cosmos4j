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
package com.microbrain.cosmos.core.permission;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.microbrain.cosmos.core.CosmosFactory;
import com.microbrain.cosmos.core.CosmosFactoryException;
import com.microbrain.cosmos.core.auth.Authorization;
import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.CosmosDynamicObject;
import com.microbrain.cosmos.core.command.CosmosExecuteException;
import com.microbrain.cosmos.core.command.CosmosResult;
import com.microbrain.cosmos.core.config.Configuration;
import com.microbrain.cosmos.core.config.ConfigurationException;

/**
 * <p>
 * <code>com.microbrain.cosmos.core.permission.PermissionFactory</code>
 * 的默认实现，并提供了对象权限和认证持有权限的缓存，提供权限验证的速度。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.permission.PermissionFactory
 * @see com.microbrain.cosmos.core.permission.JacePermission
 * @since CFDK 1.0
 */
public class PermissionFactoryImpl extends PermissionFactory {

	/**
	 * 认证对象持有权限缓存。
	 */
	private Map<String, JacePermission> permissionMap = new HashMap<String, JacePermission>();

	/**
	 * 对象权限缓存。
	 */
	private Map<String, JacePermission> objectPermissionMap = new HashMap<String, JacePermission>();

	/**
	 * 默认的权限实现。
	 */
	private static final String DEFAULT_PERMISSION = "com.jace.cosmos.core.permission.DefaultJacePermission";

	/**
	 * 权限键。
	 */
	private static final String COSMOS_PERMISSION = "cosmos.permission";

	/**
	 * 执行操作需要的权限。
	 */
	private static final String NEEDED = "needed";

	/**
	 * 认证对象所拥有的权限。
	 */
	private static final String OWNED = "owned";

	/**
	 * 执行操作需要的权限。
	 */
	private CosmosCommand neededCommand = null;

	/**
	 * 认证对象所拥有的权限。
	 */
	private CosmosCommand ownedCommand = null;

	/**
	 * 权限类。
	 */
	private Class<?> permissionClass = null;

	/**
	 * 默认构造函数。
	 */
	public PermissionFactoryImpl() {
		CosmosFactory factory = null;
		try {
			factory = CosmosFactory.getInstance();
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Configuration config = factory.lookupConfig();
		String clazz = config.getPermClass();
		if (clazz == null) {
			clazz = DEFAULT_PERMISSION;
		}

		try {
			permissionClass = Class.forName(clazz);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String needed = null, owned = null;
		try {
			needed = config.getElementInitParameter(COSMOS_PERMISSION, NEEDED);
			owned = config.getElementInitParameter(COSMOS_PERMISSION, OWNED);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (needed == null || owned == null) {
			throw new IllegalArgumentException(
					"Permission system need init parameter 'needed' and 'owned'.");
		}

		String neededPermDomain = null, neededPermCommand = null, ownedPermDomain = null, ownedPermCommand = null;
		if (needed.indexOf(".") > 0) {
			neededPermDomain = needed.substring(0, needed.indexOf("."));
			neededPermCommand = needed.substring(needed.indexOf(".") + 1);
		} else {
			neededPermCommand = needed;
		}

		if (owned.indexOf(".") > 0) {
			ownedPermDomain = owned.substring(0, owned.indexOf("."));
			ownedPermCommand = owned.substring(owned.indexOf(".") + 1);
		} else {
			ownedPermCommand = needed;
		}

		try {
			this.neededCommand = factory.getCommand(neededPermDomain,
					neededPermCommand);
			this.ownedCommand = factory.getCommand(ownedPermDomain,
					ownedPermCommand);
		} catch (CosmosFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 获得执行某操作所需要的权限。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.permission.PermissionFactory#getNeededPermission
	 * (java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public synchronized JacePermission getNeededPermission(String object,
			String operation) {
		String key = object + "." + operation;
		if (objectPermissionMap.containsKey(key)) {
			return objectPermissionMap.get(key);
		}

		CosmosResult result = null;
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("object", object);
		args.put("operation", operation);

		try {
			result = this.neededCommand.execute(args);
		} catch (CosmosExecuteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JacePermission permission = null;
		Collection<CosmosDynamicObject> perms = result.getList();
		Constructor<JacePermission> singleConst = null;
		Constructor<JacePermission> fullConst = null;
		try {
			singleConst = (Constructor<JacePermission>) permissionClass
					.getConstructor(String.class);
			fullConst = (Constructor<JacePermission>) permissionClass
					.getConstructor(String.class, String.class, String.class);
			permission = singleConst.newInstance(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			for (CosmosDynamicObject perm : perms) {
				permission
						.combine(fullConst.newInstance(key, key, perm.get(0)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.objectPermissionMap.put(key, permission);
		return permission;
	}

	/*
	 * 获得认证对象所拥有的权限。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.permission.PermissionFactory#getOwnedPermission
	 * (com.microbrain.cosmos.core.auth.Authorization)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public synchronized JacePermission getOwnedPermission(Authorization auth) {
		if (this.permissionMap.containsKey(auth.getPassportId())) {
			return this.permissionMap.get(auth.getPassportId());
		}

		CosmosResult result = null;
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("passportId", auth.getPassportId());

		try {
			result = this.ownedCommand.execute(args);
		} catch (CosmosExecuteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JacePermission permission = null;
		Collection<CosmosDynamicObject> perms = result.getList();
		Constructor<JacePermission> singleConst = null;
		Constructor<JacePermission> fullConst = null;
		try {
			singleConst = (Constructor<JacePermission>) permissionClass
					.getConstructor(String.class);
			fullConst = (Constructor<JacePermission>) permissionClass
					.getConstructor(String.class, String.class, String.class);
			permission = singleConst.newInstance(auth.getPassportId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			for (CosmosDynamicObject perm : perms) {
				permission.combine(fullConst.newInstance(auth.getPassportId(),
						perm.get(0) + "." + perm.get(1), perm.get(2)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.permissionMap.put(auth.getPassportId(), permission);
		return permission;
	}

	/*
	 * 删除缓存的某个认证对象的权限。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.permission.PermissionFactory#removeOwnedPermission
	 * (com.microbrain.cosmos.core.auth.Authorization)
	 */
	@Override
	public void removeOwnedPermission(Authorization auth) {
		permissionMap.remove(auth.getPassportId());
	}

	/*
	 * 删除缓存的某个认证ID所拥有的权限。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.permission.PermissionFactory#removeOwnedPermission
	 * (java.lang.String)
	 */
	@Override
	public void removeOwnedPermission(String passportId) {
		permissionMap.remove(passportId);
	}

	/*
	 * 清除所有缓存的权限。
	 * 
	 * @see
	 * com.microbrain.cosmos.core.permission.PermissionFactory#clearOwnedPermission
	 * ()
	 */
	@Override
	public void clearOwnedPermission() {
		permissionMap.clear();
	}

}
