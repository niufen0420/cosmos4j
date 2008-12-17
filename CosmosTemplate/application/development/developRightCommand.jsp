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
<link href="../styles/default/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../styles/default/scripts/main.js"></script>
<script type="text/javascript">
function changeType() {
	var executer = $("executer"), type = $("type");
	var selectedOption = type.options[type.selectedIndex];
	if (selectedOption.getAttribute("composite") == "true") {
		executer.disabled = true;
	} else {
		executer.disabled = false;
	}
}
</script>
</head>

<body>
<form class="detail" action="../dev/createRightCommand?forward=common.parent_refresh" method="post" enctype="application/x-www-form-urlencoded">
	<input type="hidden" name="leftDomain" value="${param.leftDomain}" />
	<input type="hidden" name="leftCommand" value="${param.leftCommand}" />
	<input type="hidden" name="composite" value="${param.composite}" />
	<label for="name">名称<span>*</span></label>
	<input type="text" maxlength="50" class="input" onblur="this.className='input'" onfocus="this.className='down'" id="name" name="name" /><br />

	<label for="domain">所属域<span>*</span></label>
	<input type="text" maxlength="50" class="input" readonly="true" onblur="this.className='input'" onfocus="this.className='down'" id="domain" name="domain" value="${param.domain}" /><br />

	<label for="content">命令内容<span>*</span></label>
	<textarea type="textarea" class="textarea" onblur="this.className='textarea'" onfocus="this.className='down'" id="command" name="command"></textarea><br />

	<label for="type">命令类型<span>*</span></label>
	<select id="type" name="type" style="width:205px;" onchange="changeType();"><c:forEach var="commandType" items="${system:commandTypes()}">
		<option composite="${commandType.composite}" value="${commandType.name}"${commandType.defaultType ? " selected" : ""}>${commandType.label}</option></c:forEach>
	</select><br />

	<label for="debugLevel">调试级别<span>*</span></label>
	<select id="debugLevel" name="debugLevel" style="width:205px;"<c:if test="${param.readOnly == 'true'}"> disabled="disabled"</c:if>>
		<option value="NO_DEBUG">与域的调试级别相同</option>
		<option value="FATAL">致命</option>
		<option value="ERROR">错误</option>
		<option value="WARN">警告</option>
		<option value="INFO">信息</option>
		<option value="DEBUG">调试</option>
		<option value="TRACE">跟踪</option>
	</select><br />

	<label for="executer">可用执行器<span>*</span></label>
	<select id="executer" name="executer" style="width:205px;"><c:forEach var="executer" items="${system:domainExecuters(param.domain)}">
		<option value="${executer.name}">${executer.label}</option></c:forEach>
	</select><br />

	<label for="remark">说明</label>
	<textarea type="textarea" class="textarea" onblur="this.className='textarea'" onfocus="this.className='down'" id="remark" name="remark"></textarea><br />

  <div class="toolBar">
  	<div class="left">
	  <input class="ButSub" onclick="document.location='../dev/commands?leftDomain=${param.leftDomain}&leftCommand=${param.leftCommand}&composite=${param.composite}&forward=development.selectRightCommand';" type="button" name="Load" value="已有命令" />
  	</div>
  	<div class="right">
  	  <c:if test="${param.readOnly != 'true'}"><input class="ButSub" type="submit" name="Submit" value="提交" /></c:if>
	  <input class="ButSub" onclick="closeModalDialog();" type="button" name="Submit" value="关闭" />
  	</div>
  </div>
</form>
</body>
</html>
