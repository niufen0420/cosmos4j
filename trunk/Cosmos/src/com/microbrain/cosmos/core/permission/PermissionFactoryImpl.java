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
 * ��Ĭ��ʵ�֣����ṩ�˶���Ȩ�޺���֤����Ȩ�޵Ļ��棬�ṩȨ����֤���ٶȡ�
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
	 * ��֤�������Ȩ�޻��档
	 */
	private Map<String, JacePermission> permissionMap = new HashMap<String, JacePermission>();

	/**
	 * ����Ȩ�޻��档
	 */
	private Map<String, JacePermission> objectPermissionMap = new HashMap<String, JacePermission>();

	/**
	 * Ĭ�ϵ�Ȩ��ʵ�֡�
	 */
	private static final String DEFAULT_PERMISSION = "com.jace.cosmos.core.permission.DefaultJacePermission";

	/**
	 * Ȩ�޼���
	 */
	private static final String COSMOS_PERMISSION = "cosmos.permission";

	/**
	 * ִ�в�����Ҫ��Ȩ�ޡ�
	 */
	private static final String NEEDED = "needed";

	/**
	 * ��֤������ӵ�е�Ȩ�ޡ�
	 */
	private static final String OWNED = "owned";

	/**
	 * ִ�в�����Ҫ��Ȩ�ޡ�
	 */
	private CosmosCommand neededCommand = null;

	/**
	 * ��֤������ӵ�е�Ȩ�ޡ�
	 */
	private CosmosCommand ownedCommand = null;

	/**
	 * Ȩ���ࡣ
	 */
	private Class<?> permissionClass = null;

	/**
	 * Ĭ�Ϲ��캯����
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
	 * ���ִ��ĳ��������Ҫ��Ȩ�ޡ�
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
	 * �����֤������ӵ�е�Ȩ�ޡ�
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
	 * ɾ�������ĳ����֤�����Ȩ�ޡ�
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
	 * ɾ�������ĳ����֤ID��ӵ�е�Ȩ�ޡ�
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
	 * ������л����Ȩ�ޡ�
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
