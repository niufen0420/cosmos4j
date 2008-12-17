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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.microbrain.cosmos.core.command.CosmosCommand;
import com.microbrain.cosmos.core.command.composite.CosmosCompositeCommand;

/**
 * <p>
 * �����ǩ�������������ɭ�ֽṹ�����εķ�ʽ��ʾ����������ÿ��������ɭ���еľ���λ�á�
 * </p>
 * <p>
 * ͨ����ɭ�ֽṹ��ǰ������ķ�ʽ��ʾ��������ÿ������ڵ��Ӧ�����ֵ�����ֵ���Ƿ�Ҷ�ӽڵ����Ϣ������Щ��Ϣ������
 * <code>com.microbrain.cosmos.web.tags.system.CommandTag.CommandTreeNode</code>
 * ���У��Թ�ҳ����ʾʹ�á�
 * </p>
 * <p>
 * ͨ��<code>begin</code>��<code>end</code>
 * ���ԣ����������Ʒ�ҳ���Ա�������������ʱ�û������Ķ������ң�����ķ�ҳ���Ը��ڵ���з�ҳ��
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see javax.servlet.jsp.tagext.TagSupport
 * @see com.microbrain.cosmos.core.command.CosmosCommand
 * @see com.microbrain.cosmos.web.tags.system.CommandTag.CommandTreeNode
 * @since CFDK 1.0
 */
public class CommandTag extends TagSupport {

	/**
	 * ���л�ʱ�����кš�
	 */
	private static final long serialVersionUID = 3742077359481991734L;

	/**
	 * ����������б�
	 */
	private Collection<CosmosCommand> commands = null;

	/**
	 * ��õı�������
	 */
	private String var = null;

	/**
	 * ��õ����νڵ��������
	 */
	private String treeVar = null;

	/**
	 * ��ʼ��š�
	 */
	private Integer begin = 0;

	/**
	 * ������š�
	 */
	private Integer end = -1;

	/**
	 * ��ȡ�
	 */
	private Integer step = 1;

	/**
	 * ƽ�滯֮��Ľڵ�����ࡣ
	 */
	private Iterator<CommandTreeNode> itFlatCommands = null;

	/**
	 * ��ǰ�������Ľڵ㡣
	 */
	private CommandTreeNode it = null;

	/*
	 * ������ǩ�塣
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doAfterBody()
	 */
	@Override
	public int doAfterBody() throws JspException {
		if (itFlatCommands.hasNext()) {
			it = itFlatCommands.next();
		} else {
			return SKIP_BODY;
		}

		pageContext.setAttribute(var, it.command);
		pageContext.setAttribute(treeVar, it);
		return EVAL_BODY_AGAIN;
	}

	/*
	 * ������ǩ��
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {
		pageContext.removeAttribute(var);
		pageContext.removeAttribute(treeVar);
		return SKIP_BODY;
	}

	/*
	 * ��ʼ��ǩ��
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		if (commands == null || commands.size() < 1) {
			return SKIP_BODY;
		}

		if (end == -1) {
			end = commands.size() - 1;
		}

		Collection<CosmosCommand> subCommandList = new ArrayList<CosmosCommand>();
		int index = 0, stepCursor = step;
		for (CosmosCommand command : commands) {
			if (index >= begin && index <= end && --stepCursor == 0) {
				subCommandList.add(command);
			}

			if (stepCursor == 0) {
				stepCursor = step;
			}

			index++;
		}

		commands = subCommandList;

		Collection<CommandTreeNode> flatCommands = new ArrayList<CommandTreeNode>();
		deepToFlat(flatCommands, null, 0, 0, null, true, false);
		itFlatCommands = flatCommands.iterator();
		if (itFlatCommands.hasNext()) {
			it = itFlatCommands.next();
		} else {
			return SKIP_BODY;
		}

		pageContext.setAttribute(var, it.command);
		pageContext.setAttribute(treeVar, it);

		return EVAL_BODY_AGAIN;
	}

	/**
	 * ��ԭʼ�������б�ƽ�滯�����εĵ����ࡣ
	 * 
	 * @param flatCommands
	 *            ���νڵ��б�
	 * @param current
	 *            ��ǰ���
	 * @param level
	 *            ��ȡ�
	 * @param currentIndex
	 *            �ýڵ����š�
	 * @param composite
	 *            �ýڵ�������������
	 * @param first
	 *            �ýڵ��Ƿ��ǵ�һ���ӽڵ㡣
	 * @param last
	 *            �ýڵ��Ƿ������һ���ӽڵ㡣
	 */
	private void deepToFlat(Collection<CommandTreeNode> flatCommands,
			CosmosCommand current, int level, int currentIndex,
			String composite, boolean first, boolean last) {
		Collection<CosmosCommand> children = null;
		if (current == null) {
			children = commands;
		} else {
			CommandTreeNode node = new CommandTreeNode();
			node.command = current;
			node.index = currentIndex;
			node.level = level;
			node.composite = composite;
			node.first = first;
			node.last = last;

			flatCommands.add(node);
			if (current instanceof CosmosCompositeCommand) {
				CosmosCompositeCommand compositeCommand = (CosmosCompositeCommand) current;
				children = compositeCommand.commands();
				if (children == null || children.size() < 1) {
					node.size = 0;
					return;
				}

				node.size = children.size();
			} else {
				node.size = 0;
				return;
			}
		}

		int index = 0, total = children.size();
		boolean fir = true, las = false;
		String comp = null;
		for (CosmosCommand command : children) {
			if (current == null) {
				StringBuilder compBuilder = new StringBuilder();
				compBuilder.append(command.getDomain().getName()).append('.')
						.append(command.getName());
				comp = compBuilder.toString();
			} else {
				comp = composite;
			}

			if (index == total - 1) {
				las = true;
			}

			deepToFlat(flatCommands, command, level + 1, index, comp, fir, las);
			if (fir) {
				fir = false;
			}
			index++;
		}
	}

	/**
	 * ��������б�
	 * 
	 * @return �����б�
	 */
	public Collection<CosmosCommand> getCommands() {
		return commands;
	}

	/**
	 * ���������б�
	 * 
	 * @param commands
	 *            �����б�
	 */
	public void setCommands(Collection<CosmosCommand> commands) {
		this.commands = commands;
	}

	/**
	 * ��ñ�������
	 * 
	 * @return ��������
	 */
	public String getVar() {
		return var;
	}

	/**
	 * ���ñ�������
	 * 
	 * @param var
	 *            ��������
	 */
	public void setVar(String var) {
		this.var = var;
	}

	/**
	 * ������α�������
	 * 
	 * @return ���α�������
	 */
	public String getTreeVar() {
		return treeVar;
	}

	/**
	 * �������α�������
	 * 
	 * @param treeVar
	 *            ���α�������
	 */
	public void setTreeVar(String treeVar) {
		this.treeVar = treeVar;
	}

	/**
	 * ��ÿ�ʼ��š�
	 * 
	 * @return ��ʼ��š�
	 */
	public Integer getBegin() {
		return begin;
	}

	/**
	 * ���ÿ�ʼ��š�
	 * 
	 * @param begin
	 *            ��ʼ��š�
	 */
	public void setBegin(Integer begin) {
		this.begin = begin;
	}

	/**
	 * ��ý�����š�
	 * 
	 * @return ������š�
	 */
	public Integer getEnd() {
		return end;
	}

	/**
	 * ���ý�����š�
	 * 
	 * @param end
	 *            ������š�
	 */
	public void setEnd(Integer end) {
		this.end = end;
	}

	/**
	 * ��ò�����
	 * 
	 * @return ������
	 */
	public Integer getStep() {
		return step;
	}

	/**
	 * ���ò�����
	 * 
	 * @param step
	 *            ������
	 */
	public void setStep(Integer step) {
		this.step = step;
	}

	/**
	 * <p>
	 * ��������νڵ��࣬���˰�װ������ڵ�֮�⣬��������ص��������е���Ϣ�����а�����ȡ���š��ߴ����Ϣ��
	 * </p>
	 * 
	 * @author Richard Sun
	 * @version 1.0, 08/12/10
	 * @see javax.servlet.jsp.tagext.TagSupport
	 * @see com.microbrain.cosmos.core.command.CosmosCommand
	 * @see com.microbrain.cosmos.web.tags.system.CommandTag
	 * @since CFDK 1.0
	 */
	public static class CommandTreeNode {

		/**
		 * ����ڵ㡣
		 */
		private CosmosCommand command;

		/**
		 * ��ȡ�
		 */
		private int level = -1;

		/**
		 * ��š�
		 */
		private int index = -1;

		/**
		 * �ߴ硣
		 */
		private int size = -1;

		/**
		 * �Ƿ��һ���ӽڵ㡣
		 */
		private boolean first = true;

		/**
		 * �Ƿ����һ���ӽڵ㡣
		 */
		private boolean last = false;

		/**
		 * ����������
		 */
		private String composite = null;

		/**
		 * ������
		 * 
		 * @return ���
		 */
		public CosmosCommand getCommand() {
			return command;
		}

		/**
		 * �������
		 * 
		 * @param command
		 *            ���
		 */
		public void setCommand(CosmosCommand command) {
			this.command = command;
		}

		/**
		 * �����ȡ�
		 * 
		 * @return ��ȡ�
		 */
		public int getLevel() {
			return level;
		}

		/**
		 * ������ȡ�
		 * 
		 * @param level
		 *            ��ȡ�
		 */
		public void setLevel(int level) {
			this.level = level;
		}

		/**
		 * ��ýڵ���š�
		 * 
		 * @return �ڵ���š�
		 */
		public int getIndex() {
			return index;
		}

		/**
		 * ���ýڵ���š�
		 * 
		 * @param index
		 *            �ڵ���š�
		 */
		public void setIndex(int index) {
			this.index = index;
		}

		/**
		 * ��ýڵ�ߴ硣
		 * 
		 * @return �ڵ�ߴ硣
		 */
		public int getSize() {
			return size;
		}

		/**
		 * ���ýڵ�ߴ硣
		 * 
		 * @param size
		 *            �ڵ�ߴ硣
		 */
		public void setSize(int size) {
			this.size = size;
		}

		/**
		 * ���������
		 * 
		 * @return ������
		 */
		public String getComposite() {
			return composite;
		}

		/**
		 * ����������
		 * 
		 * @param composite
		 *            ������
		 */
		public void setComposite(String composite) {
			this.composite = composite;
		}

		/**
		 * �Ƿ��һ���ӽڵ㡣
		 * 
		 * @return ��һ���ӽڵ㡣
		 */
		public boolean isFirst() {
			return first;
		}

		/**
		 * �����Ƿ��һ���ӽڵ㡣
		 * 
		 * @param first
		 *            ��һ���ӽڵ㡣
		 */
		public void setFirst(boolean first) {
			this.first = first;
		}

		/**
		 * ����Ƿ����һ���ӽڵ㡣
		 * 
		 * @return ���һ���ӽڵ㡣
		 */
		public boolean isLast() {
			return last;
		}

		/**
		 * �����Ƿ����һ���ӽڵ㡣
		 * 
		 * @param last
		 *            ���һ���ӽڵ㡣
		 */
		public void setLast(boolean last) {
			this.last = last;
		}

	}

}
