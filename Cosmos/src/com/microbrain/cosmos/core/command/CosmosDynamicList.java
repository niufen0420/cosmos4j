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
 * 动态列表，在列表中只可以放置<code>CosmosDynamicObject</code>对象，在放置
 * <code>CosmosDynamicObject</code>对象时，利用本类进行属性编辑，建立列表中每一个对象的属性集合。
 * </p>
 * <p>
 * <code>CosmosDynamicList</code>实现了<code>Collection<T></code>接口，因此，拥有
 * <code>Collection<T></code>接口所赋予的大部分功能，其中修改这个集合类的一些功能未被公开。 另外，
 * <code>CosmosDynamicList</code> 根据属性名称，可以进行一定的转化工作，使得类似于
 * <code>COSMOS_PROPERTY</code>形式的属性，转变成了java常见的属性定义方式：cosmosProperty，方便开发人员的习惯。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see com.microbrain.cosmos.core.command.StandardComponentTypeEditor
 * @see com.microbrain.cosmos.core.command.StandardCosmosResult
 * @see com.microbrain.cosmos.core.command.ComponentTypeEditor
 * @since CFDK 1.0
 * @param <T>
 *            泛型参数，描述该类可以在其中放置的对象类型。
 */
public class CosmosDynamicList<T> extends StandardComponentTypeEditor implements
		Collection<T> {

	/**
	 * 保存所有列表对象的列表类。
	 */
	private Collection<CosmosDynamicObject> list = null;

	/**
	 * 本动态列表的名称。
	 */
	private String name = null;

	/**
	 * 本动态列表在装饰之后的组件类型。
	 */
	private Class<?> componentType = null;

	/**
	 * 组件字段列表。
	 */
	private String[] fields = null;

	/**
	 * 是否经过装饰的标志位。
	 */
	private boolean decorate = false;

	/**
	 * 构造函数，建立一个只有名称的动态列表。
	 * 
	 * @param name
	 *            动态列表的名称。
	 */
	public CosmosDynamicList(String name) {
		this.name = StringUtils.decorate(name);
		list = new ArrayList<CosmosDynamicObject>();
		this.decorate = false;
	}

	/**
	 * 构建动态列表的全构造函数。
	 * 
	 * @param name
	 *            动态列表的名称。
	 * @param list
	 *            动态列表的内容。
	 * @param componentType
	 *            动态裂帛啊的组件类型。
	 * @param fields
	 *            动态列表字段名称列表。
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
	 * 获得动态列表的名称。
	 * 
	 * @return 返回动态列表的名称。
	 */
	public String getName() {
		return name;
	}

	/*
	 * 添加一个对象进入动态列表，但是如果该列表已经经过装饰，则不能再对该列表进行写操作。
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
	 * 添加若干个对象进入动态列表，但是如果该列表已经经过装饰，则不能再对该列表进行写操作。
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
	 * 清空该动态列表。
	 * 
	 * @see java.util.Collection#clear()
	 */
	public void clear() {
		list.clear();
	}

	/*
	 * 判断该动态列表中是否包含一个对象，如果经过装饰，该动态列表则无法进行此判断。
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
	 * 判断该动态列表中是否包含一个集合中的所有对象，如果经过装饰，该动态列表则无法进行此判断。
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
	 * 判断该动态列表是否为空。
	 * 
	 * @see java.util.Collection#isEmpty()
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/*
	 * 产生该动态列表的迭代类。
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
	 * 删除动态列表中的一个对象，如果经过装饰，则不能进行此操作。
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
	 * 删除动态列表中的多个对象，如果经过装饰，则不能进行此操作。
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
	 * 保留动态列表中的多个对象，如果经过装饰，则不能进行此操作。
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
	 * 获得该动态列表的尺寸。
	 * 
	 * @see java.util.Collection#size()
	 */
	public int size() {
		return list.size();
	}

	/*
	 * 将这个动态列表转换为对应的数组，如果经过装饰，则不能进行此操作。
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
	 * 获得这个动态列表相应泛型的数组，如果经过装饰，则不能进行此操作。
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
	 * 根据该动态列表编辑的属性列表，产生一个动态对象，将来会插入到这个动态列表中。
	 * 
	 * @return 一个新的动态对象。
	 */
	public CosmosDynamicObject newObject() {
		return new CosmosDynamicObject(properties);
	}

	/*
	 * 增加一个组件属性。
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
	 * 将一个动态<code>CosmosDynamicObject</code>对象打包成componentType所描述的类型。
	 * 
	 * @param object
	 *            动态对象。
	 * @return 返回打包之后的类型。
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
	 * 为<code>CosmosDynamicList</code>提供迭代类。
	 * 
	 * @author Richard Sun
	 * @version 1.0, 08/12/10
	 * @see com.microbrain.cosmos.core.command.CosmosDynamicList
	 * @see java.util.Iterator
	 * @since CFDK 1.0
	 * @param <E>
	 *            泛型参数，描述一个动态对象的迭代类。
	 */
	private class DynamicIterater<E> implements Iterator<E> {

		/**
		 * 被包装的迭代类。
		 */
		private Iterator<CosmosDynamicObject> it = null;

		/**
		 * 构造函数，包装一个动态对象<code>CosmosDynamicObject</code>的迭代类。
		 * 
		 * @param it
		 *            要被包装的迭代类。
		 */
		public DynamicIterater(Iterator<CosmosDynamicObject> it) {
			this.it = it;
		}

		/*
		 * 判断迭代类中是否还有下一个对象。
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		public boolean hasNext() {
			return it.hasNext();
		}

		/*
		 * 获得下一个对象。
		 * 
		 * @see java.util.Iterator#next()
		 */
		@SuppressWarnings("unchecked")
		public E next() {
			return (E) pack(this.it.next());
		}

		/*
		 * 删除当前对象。
		 * 
		 * @see java.util.Iterator#remove()
		 */
		public void remove() {
			it.remove();
		}

	}

}
