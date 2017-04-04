<%@ page language= "java" contentType = "text/html; charset=UTF-8" pageEncoding ="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Demo</title>

<link href="${ctx}/mgr/dwz_jui-1.5.2/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx}/mgr/dwz_jui-1.5.2/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx}/mgr/dwz_jui-1.5.2/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="${ctx}/mgr/dwz_jui-1.5.2/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="${ctx}/mgr/dwz_jui-1.5.2/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lt IE 9]><script src="${ctx}/mgr/dwz_jui-1.5.2/js/speedup.js" type="text/javascript"></script><script src="${ctx}/mgr/dwz_jui-1.5.2/js/jquery-1.11.3.min.js" type="text/javascript"></script><![endif]-->
<!--[if gte IE 9]><!--><script src="${ctx}/mgr/dwz_jui-1.5.2/js/jquery-2.1.4.min.js" type="text/javascript"></script><!--<![endif]-->

<script src="${ctx}/mgr/dwz_jui-1.5.2/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/xheditor/xheditor-1.2.2.min.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<script type="text/javascript" src="${ctx}/mgr/dwz_jui-1.5.2/chart/raphael.js"></script>
<script type="text/javascript" src="${ctx}/mgr/dwz_jui-1.5.2/chart/g.raphael.js"></script>
<script type="text/javascript" src="${ctx}/mgr/dwz_jui-1.5.2/chart/g.bar.js"></script>
<script type="text/javascript" src="${ctx}/mgr/dwz_jui-1.5.2/chart/g.line.js"></script>
<script type="text/javascript" src="${ctx}/mgr/dwz_jui-1.5.2/chart/g.pie.js"></script>
<script type="text/javascript" src="${ctx}/mgr/dwz_jui-1.5.2/chart/g.dot.js"></script>

<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.core.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.util.date.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.drag.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.tree.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.accordion.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.ui.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.theme.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.navTab.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.tab.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.resize.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.dialog.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.stable.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.ajax.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.pagination.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.database.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.effects.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.panel.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.history.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.combox.js" type="text/javascript"></script>
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.print.js" type="text/javascript"></script>

<!-- 可以用dwz.min.js替换前面全部dwz.*.js (注意：替换时下面dwz.regional.zh.js还需要引入)
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="${ctx}/mgr/dwz_jui-1.5.2/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="${ctx}/mgr/js/date.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("${ctx}/mgr/dwz_jui-1.5.2/dwz.frag.xml", {
		//loginUrl:"${ctx}/login.html", loginTitle:"登录",	// 弹出登录对话框
		loginUrl:"${ctx}/mgr/login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		keys: {statusCode:"statusCode", message:"message"}, //【可选】
		ui:{hideMode:'offsets'}, //【可选】hideMode:navTab组件切换的隐藏方式，支持的值有’display’，’offsets’负数偏移位置的值，默认值为’display’
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});
</script>
</head>

<body>
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a href="#"></a>
				<ul class="nav">
					<li><a href="javascript:">（<span>${admin.account}</span>）${admin.name}</a></li>
					<li><a href="${ctx}/admin/modifyPassword_html" maxable=true target="dialog" width="450"  height="300" style="color:white;">修改密码</a></li>						
					<li><a href="#" id="logout">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<span id="liveclock" style="color:white;"/>
				</ul>
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<c:if test="${currPerm['AUTH_ADMIN_PAGE'] == 'A' || loginId == 0}">
							<li><a href="${ctx}/admin/listAllAdmins?navTabId=p_admin_list" target="navTab" rel="p_admin_list">管理员管理</a></li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<p><span>欢迎使用本系统！</span></p>
						</div>
						<div class="pageFormContent" layoutH="80" style="margin-right:230px">
						</div>
						
					</div>
					
				</div>
			</div>
		</div>

	</div>

	<div id="footer"> <a href="#" target="dialog"></div>
<script>
	$(function(){
		$('#logout').click(function(e){
			$.ajax({
				url : '${ctx}/admin/logout',
				type : 'POST',
				dataType : 'json',
				success : function(data){
					if(data.r == 0){
						window.location.href = '${ctx}/mgr/login.html';
					}
				}
			});
			e.preventDefault();
		});
	});
</script>
</body>

</html>