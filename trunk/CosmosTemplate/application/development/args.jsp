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
</head>

<body onresize="fixSubHeight();" onload="fixSubHeight();">
<iframe id="hiddenFrame" name="hiddenFrame" style="display:none;"></iframe>
<div id="listContent">
    <h1><span>${param.domain}域${param.command}命令的参数列表</span></h1>
    <div id="top" class="toolBar">
        <div class="left">
			<button onclick="showModalDialog('创建参数', '../development/arg.jsp?domain=${param.domain}&command=${param.command}', {height: 300});">创建参数</button>
			<button onclick="execute('edit');">修改参数</button>
			<button onclick="execute('remove');">删除参数</button>
		</div>
        <div class="right">
			<button onclick="showModalDialog('创建参数', '../development/arg.jsp?domain=${param.domain}', {height: 400});">创建参数</button>
			<button onclick="showModalDialog('测试窗口', '../dev/localCommands?forward=development.localCommands', {});">修改参数</button>
			<button onclick="showModalDialog('测试窗口', '../dev/localCommands?forward=development.localCommands', {});">删除参数</button>
		</div>
    </div>
    <div id="tab" align="center">
        <table class="defaultList">
            <tr>
                <th scope="col">名称</th>
                <th scope="col">参数IN/OUT类型</th>
                <th scope="col">参数数据类型</th>
                <th scope="col">参数注释</th>
                <th scope="col">在之前插入</th>
                <th scope="col">在之后插入</th>
            </tr><c:forEach items="${args}" var="arg" varStatus="status" begin="${param.page == null ? 0 : (param.page * 10)}" end="${param.page == null ? 9 : param.page * 10 + 9}">
            <tr onclick="selectRow(this);" remove="$('hiddenFrame').contentWindow.location = '../dev/removeArg?domain=${param.domain}&command=${param.command}&index=${status.index}&forward=common.parent_refresh';" edit="showModalDialog('显示参数信息', '../dev/arg?domain=${param.domain}&command=${param.command}&index=${status.index}&forward=development.editArg', {height: 300});">
                <td><a href="javascript:void(0);" onclick="showModalDialog('显示参数信息', '../dev/arg?domain=${param.domain}&command=${param.command}&index=${status.index}&readOnly=true&forward=development.editArg', {height: 300});">${arg.name}</a></td>
                <td>${arg.inOutType}</td>
                <td>${arg.converter.label}</td>
                <td>${arg.remark}</td>
                <td><a href="javascript:void(0);" onclick="showModalDialog('在${arg.name}之前插入参数', '../development/insertArg.jsp?domain=${param.domain}&command=${param.command}&index=${status.index}&insertType=insertArgBefore', {height: 300});"><img src="../styles/default/images/lneighbor.gif" border="0" /></a></td>
                <td><a href="javascript:void(0);" onclick="showModalDialog('在${arg.name}之后插入参数', '../development/insertArg.jsp?domain=${param.domain}&command=${param.command}&index=${status.index}&insertType=insertArgAfter', {height: 300});"><img src="../styles/default/images/rneighbor.gif" border="0" /></a></td>
            </tr></c:forEach>
        </table>
    </div>
    <div id="bottom" class="toolBar">
        <div class="left">
			<button onclick="showModalDialog('创建参数', '../development/developCommand.jsp?domain=${param.domain}', {height: 400});">创建参数</button>
			<button onclick="execute('edit');">修改参数</button>
			<button onclick="execute('remove');">删除参数</button>
		</div>
        <div class="right">
			<button onclick="showModalDialog('创建参数', '../development/developCommand.jsp?domain=${param.domain}', {height: 400});">创建参数</button>
			<button onclick="showModalDialog('测试窗口', '../dev/localCommands?forward=development.localCommands', {});">修改参数</button>
			<button onclick="showModalDialog('测试窗口', '../dev/localCommands?forward=development.localCommands', {});">删除参数</button>
		</div>
    </div>
</div>
</body>
</html>