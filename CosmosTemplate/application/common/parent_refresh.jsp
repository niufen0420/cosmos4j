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
--%><%@ page pageEncoding="UTF-8" language="java" contentType="text/html;charset=UTF-8"%><%@ include file="/include.inc.jsp"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../styles/default/scripts/main.js"></script>
<script type="text/javascript" language="javascript">
function refresh() {
	closeModalDialog();
	if (parent.document.getElementById('mainContent') != null) {
		parent.document.getElementById('mainContent').contentWindow.location.reload();
		return;
	}

	if (parent.document.getElementById('hiddenFrame') != null) {
		parent.document.location.reload();
		return;
	}

	parent.document.location.reload();
}
</script>
</head>

<body onload="refresh();">
</body>
</html>