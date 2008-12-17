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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.beanutils.BeanUtils;

import com.microbrain.cosmos.core.command.utils.StringUtils;

/**
 * <p>
 * ��̬�б����б���ֻ���Է���<code>CosmosDynamicObject</code>�����ڷ���
 * <code>CosmosDynamicObject</code>����ʱ�����ñ���������Ա༭�������б���ÿһ����������Լ��ϡ�
 * </p>
 * <p>
 * <code>CosmosDynamicList</code>ʵ����<code>Collection<T></code>�ӿڣ���ˣ�ӵ��
 * <code>Collection<T></code>�ӿ�������Ĵ󲿷ֹ��ܣ������޸�����������һЩ����δ�������� ���⣬
 * <code>CosmosDynamicList</code> �����������ƣ����Խ���һ����ת��������ʹ��������
 * <code>COSMOS_PROPERTY</code>��ʽ�����ԣ�ת�����java���������Զ��巽ʽ��cosmosProperty�����㿪����Ա��ϰ�ߡ�
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.StandardComponentTypeEditor
 * @see com.microbrain.cosmos.core.command.StandardCosmosResult
 * @see com.microbrain.cosmos.core.command.ComponentTypeEditor
 * @since CFDK 1.0
 * @param <T>
 *            ���Ͳ���������������������з��õĶ������͡�
 */
public class CosmosDynamicList<T> extends StandardComponentTypeEditor implements
		Collection<T> {

	/**
	 * ���������б������б��ࡣ
	 */
	private Collection<CosmosDynamicObject> list = null;

	/**
	 * ����̬�б�����ơ�
	 */
	private String name = null;

	/**
	 * ����̬�б���װ��֮���������͡�
	 */
	private Class<?> componentType = null;

	/**
	 * ����ֶ��б�
	 */
	private String[] fields = null;

	/**
	 * �Ƿ񾭹�װ�εı�־λ��
	 */
	private boolean decorate = false;

	/**
	 * ���캯��������һ��ֻ�����ƵĶ�̬�б�
	 * 
	 * @param name
	 *            ��̬�б�����ơ�
	 */
	public CosmosDynamicList(String name) {
		this.name = StringUtils.decorate(name);
		list = new ArrayList<CosmosDynamicObject>();
		this.decorate = false;
	}

	/**
	 * ������̬�б��ȫ���캯����
	 * 
	 * @param name
	 *            ��̬�б�����ơ�
	 * @param list
	 *            ��̬�б�����ݡ�
	 * @param componentType
	 *            ��̬�Ѳ�����������͡�
	 * @param fields
	 *            ��̬�б��ֶ������б�
	 */
	public CosmosDynamicList(String name, Collection<CosmosDynamicObject> list,
			Class<?> componentType, String... fields) {
		this.name = name;
		this.list = list;
		this.componentType = componentType;
		this.fields = fields;
		this.decorate = true;
	}

	/**
	 * ��ö�̬�б�����ơ�
	 * 
	 * @return ���ض�̬�б�����ơ�
	 */
	public String getName() {
		return name;
	}

	/*
	 * ���һ��������붯̬�б�����������б��Ѿ�����װ�Σ������ٶԸ��б����д������
	 * 
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	public boolean add(T e) {
		if (this.decorate) {
			throw new IllegalArgumentException(
					"This collection can just be readed.");
		}

		return list.add((CosmosDynamicObject) e);
	}

	/*
	 * ������ɸ�������붯̬�б�����������б��Ѿ�����װ�Σ������ٶԸ��б����д������
	 * 
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	@SuppressWarnings("unchecked")
	public boolean addAll(Collection<? extends T> c) {
		if (this.decorate) {
			throw new IllegalArgumentException(
					"This collection can just be readed.");
		}

		return this.list.addAll((Collection<CosmosDynamicObject>) c);
	}

	/*
	 * ��ոö�̬�б�
	 * 
	 * @see java.util.Collection#clear()
	 */
	public void clear() {
		list.clear();
	}

	/*
	 * �жϸö�̬�б����Ƿ����һ�������������װ�Σ��ö�̬�б����޷����д��жϡ�
	 * 
	 * @see java.util.Collection#contains(java.lang.Object)
	 */
	public boolean contains(Object o) {
		if (this.decorate) {
			throw new IllegalArgumentException(
					"This collection doesn't test containsAll.");
		}

		return list.contains(o);
	}

	/*
	 * �жϸö�̬�б����Ƿ����һ�������е����ж����������װ�Σ��ö�̬�б����޷����д��жϡ�
	 * 
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	public boolean containsAll(Collection<?> c) {
		if (this.decorate) {
			throw new IllegalArgumentException(
					"This collection doesn't test containsAll.");
		}

		return list.containsAll(c);
	}

	/*
	 * �жϸö�̬�б��Ƿ�Ϊ�ա�
	 * 
	 * @see java.util.Collection#isEmpty()
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/*
	 * �����ö�̬�б�ĵ����ࡣ
	 * 
	 * @see java.util.Collection#iterator()
	 */
	@SuppressWarnings("unchecked")
	public Iterator<T> iterator() {
		if (this.decorate) {
			return new DynamicIterater<T>(list.iterator());
		}

		return (Iterator<T>) list.iterator();
	}

	/*
	 * ɾ����̬�б��е�һ�������������װ�Σ����ܽ��д˲�����
	 * 
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	public boolean remove(Object o) {
		if (this.decorate) {
			throw new IllegalArgumentException(
					"This collection can just be readed.");
		}

		return list.remove(o);
	}

	/*
	 * ɾ����̬�б��еĶ�������������װ�Σ����ܽ��д˲�����
	 * 
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 */
	public boolean removeAll(Collection<?> c) {
		if (this.decorate) {
			throw new IllegalArgumentException(
					"This collection can just be readed.");
		}

		return list.removeAll(c);
	}

	/*
	 * ������̬�б��еĶ�������������װ�Σ����ܽ��д˲�����
	 * 
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	public boolean retainAll(Collection<?> c) {
		if (this.decorate) {
			throw new IllegalArgumentException(
					"This collection can just be readed.");
		}

		return list.retainAll(c);
	}

	/*
	 * ��øö�̬�б�ĳߴ硣
	 * 
	 * @see java.util.Collection#size()
	 */
	public int size() {
		return list.size();
	}

	/*
	 * �������̬�б�ת��Ϊ��Ӧ�����飬�������װ�Σ����ܽ��д˲�����
	 * 
	 * @see java.util.Collection#toArray()
	 */
	public Object[] toArray() {
		if (this.decorate) {
			throw new IllegalArgumentException(
					"This collection can just be readed.");
		}

		return list.toArray();
	}

	/*
	 * ��������̬�б���Ӧ���͵����飬�������װ�Σ����ܽ��д˲�����
	 * 
	 * @see java.util.Collection#toArray(T[])
	 */
	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] a) {
		if (this.decorate) {
			throw new IllegalArgumentException(
					"This collection can just be readed.");
		}

		return list.toArray(a);
	}

	/**
	 * ���ݸö�̬�б�༭�������б�����һ����̬���󣬽�������뵽�����̬�б��С�
	 * 
	 * @return һ���µĶ�̬����
	 */
	public CosmosDynamicObject newObject() {
		return new CosmosDynamicObject(properties);
	}

	/*
	 * ����һ��������ԡ�
	 * 
	 * @see
	 * com.microbrain.cosmos.core.command.StandardComponentTypeEditor#pushProperty
	 * (java.lang.String)
	 */
	@Override
	public void pushProperty(String name) {
		super.pushProperty(StringUtils.decorate(name));
	}

	/**
	 * ��һ����̬<code>CosmosDynamicObject</code>��������componentType�����������͡�
	 * 
	 * @param object
	 *            ��̬����
	 * @return ���ش��֮������͡�
	 */
	@SuppressWarnings("unchecked")
	private T pack(CosmosDynamicObject object) {
		T t = null;
		try {
			t = (T) this.componentType.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			BeanUtils.populate(t, object);

			if (this.fields != null) {
				int index = 0;
				for (String field : fields) {
					BeanUtils.setProperty(t, field, object.getVar(index));
					index++;
				}
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return t;
	}

	/**
	 * Ϊ<code>CosmosDynamicList</code>�ṩ�����ࡣ
	 * 
	 * @author Richard Sun
	 * @version 1.0, 08/12/10
	 * @see com.microbrain.cosmos.core.command.CosmosDynamicList
	 * @see java.util.Iterator
	 * @since CFDK 1.0
	 * @param <E>
	 *            ���Ͳ���������һ����̬����ĵ����ࡣ
	 */
	private class DynamicIterater<E> implements Iterator<E> {

		/**
		 * ����װ�ĵ����ࡣ
		 */
		private Iterator<CosmosDynamicObject> it = null;

		/**
		 * ���캯������װһ����̬����<code>CosmosDynamicObject</code>�ĵ����ࡣ
		 * 
		 * @param it
		 *            Ҫ����װ�ĵ����ࡣ
		 */
		public DynamicIterater(Iterator<CosmosDynamicObject> it) {
			this.it = it;
		}

		/*
		 * �жϵ��������Ƿ�����һ������
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		public boolean hasNext() {
			return it.hasNext();
		}

		/*
		 * �����һ������
		 * 
		 * @see java.util.Iterator#next()
		 */
		@SuppressWarnings("unchecked")
		public E next() {
			return (E) pack(this.it.next());
		}

		/*
		 * ɾ����ǰ����
		 * 
		 * @see java.util.Iterator#remove()
		 */
		public void remove() {
			it.remove();
		}

	}

}
