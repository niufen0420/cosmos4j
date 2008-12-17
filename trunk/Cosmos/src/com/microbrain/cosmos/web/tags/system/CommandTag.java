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
 * 命令标签，将传入的命令森林结构以树形的方式显示出来，计算每个命令在森林中的具体位置。
 * </p>
 * <p>
 * 通过将森林结构以前序迭代的方式显示，并计算每个命令节点对应的深度值、序号值，是否叶子节点等信息，将这些信息放置在
 * <code>com.microbrain.cosmos.web.tags.system.CommandTag.CommandTreeNode</code>
 * 类中，以供页面显示使用。
 * </p>
 * <p>
 * 通过<code>begin</code>和<code>end</code>
 * 属性，可以来控制分页，以便于在命令增多时用户方便阅读，并且，这里的分页仅对根节点进行分页。
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
	 * 序列化时的序列号。
	 */
	private static final long serialVersionUID = 3742077359481991734L;

	/**
	 * 传入的命令列表。
	 */
	private Collection<CosmosCommand> commands = null;

	/**
	 * 获得的变量名。
	 */
	private String var = null;

	/**
	 * 获得的树形节点变量名。
	 */
	private String treeVar = null;

	/**
	 * 开始序号。
	 */
	private Integer begin = 0;

	/**
	 * 结束序号。
	 */
	private Integer end = -1;

	/**
	 * 跨度。
	 */
	private Integer step = 1;

	/**
	 * 平面化之后的节点迭代类。
	 */
	private Iterator<CommandTreeNode> itFlatCommands = null;

	/**
	 * 当前迭代到的节点。
	 */
	private CommandTreeNode it = null;

	/*
	 * 结束标签体。
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
	 * 结束标签。
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
	 * 开始标签。
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
	 * 将原始的命令列表，平面化成树形的迭代类。
	 * 
	 * @param flatCommands
	 *            树形节点列表。
	 * @param current
	 *            当前命令。
	 * @param level
	 *            深度。
	 * @param currentIndex
	 *            该节点的序号。
	 * @param composite
	 *            该节点所属的组合命令。
	 * @param first
	 *            该节点是否是第一个子节点。
	 * @param last
	 *            该节点是否是最后一个子节点。
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
	 * 获得命令列表。
	 * 
	 * @return 命令列表。
	 */
	public Collection<CosmosCommand> getCommands() {
		return commands;
	}

	/**
	 * 设置命令列表。
	 * 
	 * @param commands
	 *            命令列表。
	 */
	public void setCommands(Collection<CosmosCommand> commands) {
		this.commands = commands;
	}

	/**
	 * 获得变量名。
	 * 
	 * @return 变量名。
	 */
	public String getVar() {
		return var;
	}

	/**
	 * 设置变量名。
	 * 
	 * @param var
	 *            变量名。
	 */
	public void setVar(String var) {
		this.var = var;
	}

	/**
	 * 获得树形变量名。
	 * 
	 * @return 树形变量名。
	 */
	public String getTreeVar() {
		return treeVar;
	}

	/**
	 * 设置树形变量名。
	 * 
	 * @param treeVar
	 *            树形变量名。
	 */
	public void setTreeVar(String treeVar) {
		this.treeVar = treeVar;
	}

	/**
	 * 获得开始序号。
	 * 
	 * @return 开始序号。
	 */
	public Integer getBegin() {
		return begin;
	}

	/**
	 * 设置开始序号。
	 * 
	 * @param begin
	 *            开始序号。
	 */
	public void setBegin(Integer begin) {
		this.begin = begin;
	}

	/**
	 * 获得结束序号。
	 * 
	 * @return 结束序号。
	 */
	public Integer getEnd() {
		return end;
	}

	/**
	 * 设置结束序号。
	 * 
	 * @param end
	 *            结束序号。
	 */
	public void setEnd(Integer end) {
		this.end = end;
	}

	/**
	 * 获得步长。
	 * 
	 * @return 步长。
	 */
	public Integer getStep() {
		return step;
	}

	/**
	 * 设置步长。
	 * 
	 * @param step
	 *            步长。
	 */
	public void setStep(Integer step) {
		this.step = step;
	}

	/**
	 * <p>
	 * 命令的树形节点类，除了包装了命令节点之外，增加了相关的在树形中的信息，其中包括深度、序号、尺寸等信息。
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
		 * 命令节点。
		 */
		private CosmosCommand command;

		/**
		 * 深度。
		 */
		private int level = -1;

		/**
		 * 序号。
		 */
		private int index = -1;

		/**
		 * 尺寸。
		 */
		private int size = -1;

		/**
		 * 是否第一个子节点。
		 */
		private boolean first = true;

		/**
		 * 是否最后一个子节点。
		 */
		private boolean last = false;

		/**
		 * 所属组合命令。
		 */
		private String composite = null;

		/**
		 * 获得命令。
		 * 
		 * @return 命令。
		 */
		public CosmosCommand getCommand() {
			return command;
		}

		/**
		 * 设置命令。
		 * 
		 * @param command
		 *            命令。
		 */
		public void setCommand(CosmosCommand command) {
			this.command = command;
		}

		/**
		 * 获得深度。
		 * 
		 * @return 深度。
		 */
		public int getLevel() {
			return level;
		}

		/**
		 * 设置深度。
		 * 
		 * @param level
		 *            深度。
		 */
		public void setLevel(int level) {
			this.level = level;
		}

		/**
		 * 获得节点序号。
		 * 
		 * @return 节点序号。
		 */
		public int getIndex() {
			return index;
		}

		/**
		 * 设置节点序号。
		 * 
		 * @param index
		 *            节点序号。
		 */
		public void setIndex(int index) {
			this.index = index;
		}

		/**
		 * 获得节点尺寸。
		 * 
		 * @return 节点尺寸。
		 */
		public int getSize() {
			return size;
		}

		/**
		 * 设置节点尺寸。
		 * 
		 * @param size
		 *            节点尺寸。
		 */
		public void setSize(int size) {
			this.size = size;
		}

		/**
		 * 获得组合命令。
		 * 
		 * @return 组合命令。
		 */
		public String getComposite() {
			return composite;
		}

		/**
		 * 设置组合命令。
		 * 
		 * @param composite
		 *            组合命令。
		 */
		public void setComposite(String composite) {
			this.composite = composite;
		}

		/**
		 * 是否第一个子节点。
		 * 
		 * @return 第一个子节点。
		 */
		public boolean isFirst() {
			return first;
		}

		/**
		 * 设置是否第一个子节点。
		 * 
		 * @param first
		 *            第一个子节点。
		 */
		public void setFirst(boolean first) {
			this.first = first;
		}

		/**
		 * 获得是否最后一个子节点。
		 * 
		 * @return 最后一个子节点。
		 */
		public boolean isLast() {
			return last;
		}

		/**
		 * 设置是否最后一个子节点。
		 * 
		 * @param last
		 *            最后一个子节点。
		 */
		public void setLast(boolean last) {
			this.last = last;
		}

	}

}
