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
</head>

<body>
<form class="detail" action="../dev/createArg?forward=common.parent_refresh" method="post" enctype="application/x-www-form-urlencoded">
	<input type="hidden" name="domain" value="${param.domain}" />
	<input type="hidden" name="command" value="${param.command}" />
	<label for="name">参数名称<span>*</span></label>
	<input type="text" maxlength="50" class="input" onblur="this.className='input'" onfocus="this.className='down'" id="name" name="name" /><br />

	<label for="inOutType">参数IN/OUT类型<span>*</span></label>
	<select id="inOutType" name="inOutType" style="width:205px;">
		<option value="IN">IN</option>
		<option value="OUT">OUT</option>
		<option value="INOUT">INOUT</option>
	</select><br />

	<label for="converter">参数数据类型<span>*</span></label>
	<select id="converter" name="converter" style="width:205px;"><c:forEach var="converter" items="${system:converters()}">
		<option value="${converter.name}">${converter.label}</option></c:forEach>
	</select><br />

	<label for="remark">注释</label>
	<textarea type="textarea" class="textarea" onblur="this.className='textarea'" onfocus="this.className='down'"<c:if test="${param.readOnly == 'true'}"> readonly="readonly"</c:if> id="remark" name="remark"></textarea><br />

  <div class="toolBar">
  	<div class="right">
  	  <input class="ButSub" type="submit" name="Submit" value="提交" />
	  <input class="ButSub" onclick="closeModalDialog();" type="button" name="Submit" value="关闭" />
  	</div>
  </div>
</form>
</body>
</html>