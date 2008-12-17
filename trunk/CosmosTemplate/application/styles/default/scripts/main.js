// JavaScript Document
var menuItem = null;
var dataRow = null;
function $(d) {
	return document.getElementById(d);
}
function $p(p, d) {
	return p.document.getElementById(d);
}
function $t(o, t) {
	return o.getElementsByTagName(t);
}
function $n(e) {
	return document.createElement(e);
}
function fixHeight() {
	var cHeight = document.documentElement.clientHeight;
	var cWidth = document.documentElement.clientWidth;
	var navig = $("navigator"), contro = $('control'), mai = $('main'), maiCon = $('mainContent'), maiIndex = $('mainIndex'), navWidth = 0;
	if (navig !== null && cHeight > 200 && cWidth > 300) {
		if (navig.style.display == "none") {
			navWidth = -14;
		} else {
			navWidth = 166;
		}

		var navs = navig.childNodes;
		navig.style.height = (cHeight - 120) + "px";
		contro.style.height = (cHeight - 120) + "px";
		mai.style.height = (cHeight - 145) + "px";
		mai.style.width = (cWidth - 29 - navWidth) + "px";
		maiCon.style.width = (cWidth - 44 - navWidth) + "px";
		maiCon.style.height = (cHeight - 155) + "px";
		maiIndex.style.height = (cHeight - 175) + "px";

		for ( var i = 0; i < navs.length; i++) {
			var nav = navs[i];
			if (nav.className && nav.className == "frame") {
				nav.style.height = (cHeight - 135) + "px";
			}
		}
	}
}
function fixSubHeight() {
	var cHeight = document.documentElement.clientHeight;
	var cWidth = document.documentElement.clientWidth;
	var listCont = $("listContent"), ta = $('tab');
	if (listCont !== null && cHeight && cHeight > 50 && cWidth > 40) {
		listCont.style.height = (cHeight - 10) + "px";
		listCont.style.width = (cWidth - 10) + "px";
		if (ta) {
			ta.style.height = (cHeight - 120) + "px";
		}
	}
}
function toggleNav() {
	var src = $("controlNav").src;
	var imageLength = src.length;
	var image = src.substring(imageLength - 6, imageLength);
	if (image == "ck.gif") {
		$("controlNav").src = src.substring(0, imageLength - 6) + "co.gif";
		$("navigator").style.display = "none";
	} else {
		$("controlNav").src = src.substring(0, imageLength - 6) + "ck.gif";
		$("navigator").style.display = "block";
	}

	fixHeight();
}
function toggleMenu(a) {
	var currentLi = a.parentNode, i = 0, selected = 0, liNodes = $("bar").getElementsByTagName("li"), navs = $("navigator").childNodes, j = 0, k = 0;
	for (i = 0; i < liNodes.length; i++) {
		var perLi = liNodes[i];
		var perSpan = perLi.getElementsByTagName("span")[0];
		if (perLi == currentLi) {
			perSpan.className = "menuOn";
			selected = i;
		} else {
			perSpan.className = "menuOff";
		}
	}

	for (k = 0; k < navs.length; k++) {
		var nav = navs[k];
		if (nav.className && nav.className == "frame") {
			if (j == selected) {
				nav.style.display = "block";
			} else {
				nav.style.display = "none";
			}
			j++;
		}
	}

	var src = $("controlNav").src;
	var imageLength = src.length;
	var image = src.substring(imageLength - 6, imageLength);
	if (image == "co.gif") {
		$("controlNav").src = src.substring(0, imageLength - 6) + "ck.gif";
		$("navigator").style.display = "block";
	}

	fixHeight();
}
function toggleNavMenu(curA) {
	var curLi = curA.parentNode, ul = curLi.parentNode, lis = ul.getElementsByTagName("li"), frame = ul.parentNode.parentNode, children = frame.childNodes, i = 0, selected = 0, j = 0, k = 0;
	for ( var j = 0; j < lis.length; j++) {
		var li = lis[j], a = li.getElementsByTagName("a")[0];
		if (curLi == li) {
			selected = j;
			a.className = "active";
		} else {
			a.className = "";
		}
	}

	for (i = 0; i < children.length; i++) {
		var div = children[i];
		if (div.className) {
			if (div.className == "navTop") {
				div.innerHTML = curA.innerHTML;
			} else if (div.className == "navBar") {
				var bars = div.getElementsByTagName("ul");
				for (j = 0; j < bars.length; j++) {
					var bar = bars[j];
					if (bar.className == "navMenu") {
						if (k == selected) {
							bar.style.display = "block";
						} else {
							bar.style.display = "none";
						}
						k++;
					}
				}
			}
		}
	}
}
function toggleChildren(parent) {
	var child = null;
	if (parent.sourceIndex) {
		var srcIndex = parent.sourceIndex;
		child = document.all[srcIndex + 1];
		event.cancelBubble = true;
		if (child.tagName.toLowerCase() != "ul"
				|| child.className != "navChild") {
			return;
		}
	} else {
		var cur = parent.nextSibling;
		while (cur.className != "navChild" || cur.tagName.toLowerCase() != "ul") {
			cur = cur.nextSibling;
		}

		child = cur;
	}

	if (child != null) {
		if (child.style.display == "" || child.style.display == "none") {
			child.style.display = "block";
			parent.style.listStyleImage = "url(styles/default/images/open.gif)";
		} else {
			child.style.display = "none";
			parent.style.listStyleImage = "url(styles/default/images/fold.gif)";
		}
	}
}
function openItem(title, curA) {
	$("mainContent").style.display = "block";
	$("mainTitle").innerHTML = title;
	if (menuItem != null) {
		menuItem.className = "itemOff";
	}

	curA.className = "itemOn";
	menuItem = curA;
	event.cancelBubble = true;
}
function selectRow(row) {
	if (dataRow != null) {
		dataRow.className = "tr";
	}

	row.className = "down";
	dataRow = row;
}
function showModalDialog(title, url, options) {
	var dialog = $p(parent, "modalWindow");
	if (dialog == null || dialog.style.display == "block") {
		dialog.style.display = "none";
		return;
	}

	if (title) {
		$p(parent, "title").innerHTML = title;
	} else {
		$p(parent, "title").innerHTML = "No title window";
	}

	if (url) {
		$p(parent, "modalIframe").src = 'common/loading.html?url=' + escape(url);
	} else {
		$p(parent, "modalIframe").src = 'common/loading.html';
	}

	var opt = {
		width :450,
		height :500,
		modal :true,
		center :true,
		left :0,
		top :0
	};

	if (options) {
		if (typeof (options.width) != "undefined" && options.width > 200) {
			opt.width = options.width;
		}
		if (typeof (options.height) != "undefined" && options.height > 40) {
			opt.height = options.height;
		}
		if (typeof (options.modal) != "undefined") {
			opt.modal = options.modal;
		}
		if (typeof (options.center) != "undefined") {
			opt.center = options.center;
		}
		if (typeof (options.left) != "undefined") {
			opt.left = options.left;
		}
		if (typeof (options.top) != "undefined") {
			opt.top = options.top;
		}
	}

	if (opt.center) {
		var cHeight = parent.document.documentElement.clientHeight;
		var cWidth = parent.document.documentElement.clientWidth;
		if (cWidth < opt.width) {
			opt.left = 0;
		} else {
			opt.left = (cWidth - opt.width) / 2;
		}
		if (cHeight < opt.height) {
			opt.top = 0;
		} else {
			opt.top = (cHeight - opt.height) / 2;
		}
	}

	if (!opt.modal) {
		$p(parent, "mask").style.display = "none";
	}

	var dialogWindow = $p(parent, "modalDiv"), dialogContent = $p(parent,
			"modalContent");
	dialogWindow.style.width = opt.width + "px";
	dialogWindow.style.height = opt.height + "px";
	dialogWindow.style.left = opt.left + "px";
	dialogWindow.style.top = opt.top + "px";
	dialogContent.style.width = (opt.width - 16) + "px";
	dialogContent.style.height = (opt.height - 38) + "px";
	dialog.style.display = "block";
}
function closeModalDialog() {
	$p(parent.parent, "modalWindow").style.display = "none";
}
function execute(command) {
	if (dataRow == null) {
		alert("No selected row.");
		return;
	}

	var content = dataRow.getAttribute(command);
	if (content == null || content == "") {
		alert("command content is null.");
	}

	return eval(content);
}
if (document.addEventListener)
	window.addEventListener("resize", fixHeight, true);
if (document.attachEvent)
	window.attachEvent("onresize", fixHeight);
if (document.addEventListener)
	window.addEventListener("resize", fixSubHeight, true);
if (document.attachEvent)
	window.attachEvent("onresize", fixSubHeight);
