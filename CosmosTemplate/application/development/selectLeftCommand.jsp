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
<form style="padding: 0;" action="../dev/selectLeftCommand?forward=common.parent_refresh" method="post" enctype="application/x-www-form-urlencoded">
<input type="hidden" name="rightDomain" value="${param.rightDomain}" />
<input type="hidden" name="rightCommand" value="${param.rightCommand}" />
<input type="hidden" name="composite" value="${param.composite}" />
<div id="listContent">
    <h1><span>全局命令列表</span></h1>
    <div id="top" class="toolBar">
        <div class="left" style="width: 100%;">
			<pager:info totalItems="${fn:length(commands)}" url="commands?rightDomain=${param.rightDomain}&rightCommand=${param.rightCommand}&composite=${param.composite}&forward=development.selectLeftCommand" numPerPage="20" currentPageNum="${param.page}" pageNumShown="5" >
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
    <div id="tab" align="center">
        <table class="defaultList">
            <tr>
                <th scope="col">选择</th>
                <th scope="col">名称</th>
                <th scope="col">命令类型</th>
            </tr><c:forEach items="${commands}" var="command" begin="${param.page == null ? 0 : ((param.page - 1) * 20)}" end="${param.page == null ? 19 : (param.page - 1) * 20 + 19}">
            <tr onclick="selectRow(this);this.getElementsByTagName('input')[0].checked = true;">
                <td><input type="radio" name="command" value="${command.domain.name}.${command.name}" /></td>
                <td>${command.domain.name}.${command.name}</td>
                <td>${command.type.label}</td>
            </tr></c:forEach>
        </table>
    </div>
    <div id="bottom" class="toolBar">
        <div class="left">
			<pager:info totalItems="${fn:length(commands)}" numPerPage="20" currentPageNum="${param.page}" pageNumShown="5" >
			当前是第${Pager_currentPageNum}页　共${Pager_count}页  共${Pager_totalItems}条数据
			</pager:info>
		</div>
	  	<div class="right">
	  	  <c:if test="${param.readOnly != 'true'}"><input class="ButSub" type="submit" name="Submit" value="提交" /></c:if>
		  <input class="ButSub" onclick="closeModalDialog();" type="button" name="Submit" value="关闭" />
	  	</div>
    </div>
</div>
</form>
</body>
</html>