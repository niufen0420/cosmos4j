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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>Cosmos开发框架</title>
<link href="styles/default/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="styles/default/scripts/main.js"></script>
</head>

<body onresize="fixHeight();" onload="fixHeight();">
<!------------ModalDiv------------->
<div id="modalWindow" style="display: none;">
	<div id="mask"></div>
	<div id="modalDiv">
    	<div class="modalTitle"><span id="title">功能操作</span><a class="close" href="javascript:void(0);" onclick="closeModalDialog();" title="关闭"></a></div>
        <div id="modalContent"><iframe src="common/loading.html" id="modalIframe" name="modalIframe" frameborder="0"></iframe></div>
    </div>
</div>
<div id="hm">
	<div id="header"><div class="logo"></div></div>
    <div id="menu">
    	<ul id="bar">
        	<li><a href="javascript:void(0);" onclick="toggleMenu(this);" onfocus="this.blur();"><span name="menuBut" class="menuOn">系统管理</span></a></li>
        	<li><a href="javascript:void(0);" onclick="toggleMenu(this);" onfocus="this.blur();"><span name="menuBut">用户管理</span></a></li>
        </ul>
    </div>
</div>
<div id="mc">
    <div id="navigator">
        <div class="frame">
            <div class="navTop">系统开发管理</div>
            <div class="navBar">
            	<ul class="navMenu">
                	<li class="navParent" onclick="toggleChildren(this);">系统域全局命令</li>
                    <ul class="navChild"><c:forEach var="domain" items="${system:domains()}">
                    	<li><a href="dev/domainGlobalCommands?forward=development.globalCommands&domain=${domain.name}" target="mainContent" onClick="openItem('${domain.name} 域全局命令管理', this);">${domain.name} 域</a></li></c:forEach>
                    </ul>
                	<li class="navParent" onclick="toggleChildren(this);">系统域本地命令</li>
                    <ul class="navChild"><c:forEach var="domain" items="${system:domains()}">
                    	<li><a href="dev/domainLocalCommands?forward=development.localCommands&domain=${domain.name}" target="mainContent" onClick="openItem('${domain.name} 域本地命令管理', this);">${domain.name} 域</a></li></c:forEach>
                    </ul>
                	<li class="navParent" onclick="toggleChildren(this);">测试样例</li>
                    <ul class="navChild">
                    	<li><a href="cosmos.slet?forward=test.result&domain=jndi&method=search&dom=jndi&object=cosmos_ds_mysql&operation=SpListCommands&name=7777&service=service&pageNo=1&pageLimit=10&orderBy=2&orderDesc=0" target="mainContent" onClick="openItem('命令管理', this);">命令管理</a></li>
                    </ul>
                </ul>
            	<ul class="navMenu" style="display: none;">
                	<li class="navParent" onclick="toggleChildren(this);">用户管理</li>
                    <ul class="navChild">
                    	<li><a href="dev/localCommands?forward=development.localCommands" target="mainContent" onClick="openItem('指令管理', this);">指令管理</a></li>
                    </ul>
                	<li class="navLi"><a href="dev/localCommands?forward=development.localCommands" target="mainContent" onClick="openItem('系统控制', this);">系统控制</a></li>
                </ul>
            </div>
            <div class="navBottom">
            	<ul>
                	<li><a class="active" href="javascript:void(0);" onclick="toggleNavMenu(this);" onFocus="this.blur();">系统管理</a></li>
                	<li><a href="javascript:void(0);" onclick="toggleNavMenu(this);" onFocus="this.blur();">用户管理</a></li>
                </ul>
            </div>
        </div>
		<div class="frame" style="display: none;">
            <div class="navTop">开发平台</div>
            <div class="navBar">
            	<ul class="navMenu">
                	<li class="navParent" onclick="toggleChildren(this);">Development</li>
                    <ul class="navChild">
                    	<li><a href="dev/localCommands?forward=development.localCommands" target="mainContent" onClick="openItem('Command', this);">Command</a></li>
                    </ul>
                	<li class="navLi"><a href="dev/localCommands?forward=development.localCommands" target="mainContent" onClick="openItem('System', this);">System</a></li>
                </ul>
            </div>
            <div class="navBottom">
            	<ul>
                	<li><a class="active" href="javascript:void(0);" onclick="toggleNavMenu(this);" onFocus="this.blur();">开发平台</a></li>
                	<li><a href="javascript:void(0);" onclick="toggleNavMenu(this);" onFocus="this.blur();">用户开发</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div id="control" onclick="toggleNav();"><img id="controlNav" src="styles/default/images/ck.gif" /></div>
    <div id="main">
    	<div class="mainBar">
        	<div class="mainSub"><span id="mainTitle">后台管理系统</span></div>
        </div>
        <div id="mainIndex" class="indexBg"><iframe src="common/loading.html" id="mainContent" name="mainContent" scrolling="no" frameborder="0" width="100%" height="100%"></iframe></div>
  </div>
</div>
</body>
</html>
