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
    <h1><span>结果集列表</span></h1>
    <div id="top" class="toolBar">
        <div class="left">
		</div>
        <div class="right">
		</div>
    </div>
    <div id="tab" align="center">
        <table class="defaultList">
            <tr>
                <th scope="col">用户ID</th>
                <th scope="col">用户密码</th>
            </tr><c:forEach items="${present:commandFirstList('jndi', 'login', result)}" var="user">
            <tr onclick="selectRow(this);">
                <td>${user.id}</td>
                <td>${user.password}</td>
            </tr></c:forEach>
        </table>
        <table class="defaultList">
            <tr>
                <th scope="col">权限代码</th>
            </tr><c:forEach items="${present:commandFirstList('jndi', 'needed', result)}" var="perm">
            <tr onclick="selectRow(this);">
                <td>${perm.permissionCode}</td>
            </tr></c:forEach>
        </table>
    </div>
    <div id="bottom" class="toolBar">
        <div class="left">
		</div>
        <div class="right">
		</div>
    </div>
</div>
</body>
</html>