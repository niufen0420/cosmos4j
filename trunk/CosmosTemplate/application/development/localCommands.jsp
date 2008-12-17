<%--
	Copyright (c) 2006 Microbrain Inc.
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	       http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
--%><%@ page pageEncoding="UTF-8" language="java" contentType="text/html;charset=UTF-8"%><%@ include file="/include.inc.jsp"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="../styles/default/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../styles/default/scripts/main.js"></script>
<script type="text/javascript">
function toggleChildren(a) {
	var tr = a.parentNode.parentNode, img = a.getElementsByTagName("img")[0], open = true, level = Number(tr.getAttribute("level"));
	if (img.src.indexOf("folder.gif") >= 0) {
		img.src = "../styles/default/images/tree/folderopen.gif";
	} else {
		img.src = "../styles/default/images/tree/folder.gif";
		open = false;
	}

	var next = tr, nextLevel = level + 1;
	while (next != null && nextLevel > level) {
		next = next.nextSibling;
		if (next == null) {
			break;
		}

		if (typeof(next.tagName) != "undefined") {
			nextLevel = Number(next.getAttribute("level"));
			var imgs = next.getElementsByTagName("td")[0].getElementsByTagName("img");
			img = imgs[imgs.length - 1];

			if (open) {
				if (nextLevel == level + 1) {
					next.style.display = "";
				}
			} else {
				if (nextLevel > level) {
					next.style.display = "none";
					if (img.src.indexOf("folderopen.gif") >= 0) {
						img.src = "../styles/default/images/tree/folder.gif";
					}
				}
			}
		} else {
			nextLevel = level + 1;
		}
	}
}
</script>
</head>

<body onresize="fixSubHeight();" onload="fixSubHeight();">
<iframe id="hiddenFrame" name="hiddenFrame" style="display:none;"></iframe>
<div id="listContent">
    <h1><span>全局命令列表</span></h1>
    <div id="top" class="toolBar">
        <div class="left">
		</div>
        <div class="right">
			<pager:info totalItems="${fn:length(commands)}" numPerPage="20" currentPageNum="${param.page}" pageNumShown="5" >
			当前是第${Pager_currentPageNum}页　共${Pager_count}页  共${Pager_totalItems}条数据
			</pager:info>
		</div>
    </div>
    <div id="tab" align="center">
        <table class="defaultList">
            <tr>
                <th scope="col" width="">名称</th>
                <th scope="col">命令类型</th>
                <th scope="col">命令内容</th>
                <th scope="col">命令参数管理</th>
                <th scope="col" width="50px">子命令</th>
                <th scope="col" width="40px">前置</th>
                <th scope="col" width="40px">后置</th>
            </tr><system:commands commands="${commands}" treeVar="node" var="command" begin="${param.page == null ? 0 : ((param.page - 1) * 20)}" end="${param.page == null ? 19 : (param.page - 1) * 20 + 19}">
            <tr onclick="selectRow(this);" remove="$('hiddenFrame').contentWindow.location = '../dev/removeCommand?domain=${command.domain.name}&name=${command.name}&composite=${node.composite}&forward=common.parent_refresh';" edit="showModalDialog('显示命令信息', '../dev/command?domain=${command.domain.name}&command=${command.name}&forward=development.command', {height: 400});" level="${node.level}"<c:if test="${node.level != 1}"> style="display:none;"</c:if>>
                <td><c:forEach begin="2" end="${node.level}"><img src="../styles/default/images/tree/empty.gif" /></c:forEach><c:if test="${command.type.composite}"><a href="javascript:void(0);" onclick="toggleChildren(this);"><img src="../styles/default/images/tree/folder.gif" border="0" /></a></c:if><c:if test="${!command.type.composite}"><img src="../styles/default/images/tree/page.gif" border="0" /></c:if><a href="javascript:void(0);" onclick="showModalDialog('显示命令信息', '../dev/command?domain=${command.domain.name}&command=${command.name}&readOnly=true&forward=development.command', {height: 400});">${command.name}</a></td>
                <td>${command.type.label}</td>
                <td title="${fn:escapeXml(command.command)}"><div style="width:400px; white-space:nowrap; overflow:hidden; text-overflow:ellipsis;">${fn:escapeXml(command.command)}</div></td>
                <td><a href="args?domain=${command.domain.name}&command=${command.name}&forward=development.args">参数列表</a></td>
                <td><c:if test="${command.type.composite}"><a href="javascript:void(0);" onclick="showModalDialog('创建${command.name}的子命令', '../development/developSubCommand.jsp?parentDomain=${command.domain.name}&parentCommand=${command.name}&composite=${node.composite}&domain=${command.domain.name}', {height: 400});"><img src="../styles/default/images/sub.gif" border="0" /></a></c:if></td>
                <td><c:if test="${node.level != 1}"><a href="javascript:void(0);" onclick="showModalDialog('创建${command.name}的前置命令', '../development/developLeftCommand.jsp?rightDomain=${command.domain.name}&rightCommand=${command.name}&composite=${node.composite}&domain=${command.domain.name}', {height: 400});"><img src="../styles/default/images/lneighbor.gif" border="0" /></a></c:if></td>
                <td><c:if test="${node.level != 1}"><a href="javascript:void(0);" onclick="showModalDialog('创建${command.name}的前置命令', '../development/developRightCommand.jsp?leftDomain=${command.domain.name}&leftCommand=${command.name}&composite=${node.composite}&domain=${command.domain.name}', {height: 400});"><img src="../styles/default/images/rneighbor.gif" border="0" /></a></c:if></td>
            </tr></system:commands>
        </table>
    </div>
    <div id="bottom" class="toolBar">
        <div class="left">
		</div>
        <div class="right">
			<pager:info totalItems="${fn:length(commands)}" url="domainLocalCommands?forward=development.localCommands&domain=${param.domain}" numPerPage="20" currentPageNum="${param.page}" pageNumShown="5" >
				<pager:first><a href="${firstPage}">首页</a></pager:first>
				<pager:prepages><a href="${prePages}">前${Pager_pageNumShown }页</a></pager:prepages>		
				<pager:previous><a href="${prePage}">上一页</a></pager:previous>
				<pager:pages><a href="${pages}"><c:if test="${Pager_pageNum == Pager_currentPageNum }"><strong>${Pager_pageNum }</strong></c:if><c:if test="${!(Pager_pageNum == Pager_currentPageNum)}">${Pager_pageNum}</c:if></a></pager:pages>
				<pager:next><a href="${nextPage}">下一页</a></pager:next>
				<pager:nextpages><a href="${nextPages}">后${Pager_pageNumShown}页</a></pager:nextpages>		
				<pager:last><a href="${lastPage}">尾页</a></pager:last>
				<select onchange="document.location='${url}'+this.options[this.selectedIndex].value;"><pager:allpages><option ${Pager_currentPageNum == Pager_pageCount ? "selected" : ""} value="${Pager_pageCount}">${Pager_pageCount}</option></pager:allpages></select>
			</pager:info>
		</div>
    </div>
</div>
</body>
</html>