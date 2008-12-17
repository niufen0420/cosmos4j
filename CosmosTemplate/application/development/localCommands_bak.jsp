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
    <h1><span>System/Command List</span></h1>
    <div id="top" class="toolBar">
        <div class="left"><button onclick="showModalDialog('测试窗口', '../dev/localCommands?forward=development.localCommands', {center: true, left: 100, top: 200, modal: true});">test</button></div>
    </div>
    <div id="tab" align="center">
        <table class="defaultList">
            <tr>
                <th scope="col">名称</th>
                <th scope="col">命令所属域</th>
                <th scope="col">命令类型</th>
            </tr><system:commands commands="${commands}" treeVar="node" var="command" begin="${param.page == null ? 1 : (param.page * 10 + 1)}" end="${param.page == null ? 10 : param.page * 10 + 10}">
            <tr onclick="selectRow(this);">
                <td><a href="javascript:void(0);" onclick="showModalDialog('显示命令信息', '../dev/globalCommand?domain=${command.domain.name}&command=${command.name}&readOnly=true&forward=development.command', {height: 300});">${command.name}</a></td>
                <td>${command.domain.name}</td>
                <td>${command.type.name}</td>
            </tr></system:commands>
        </table>
    </div>
    <div id="bottom" class="toolBar">
        <div class="left"></div>
    </div>
</div>
</body>
</html>