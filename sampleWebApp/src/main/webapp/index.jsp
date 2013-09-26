<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>简单实用国产jQuery UI框架 - DWZ富客户端框架(J-UI.com)</title>
<%@ include file="/common/links.jsp"%>
<%@ include file="/common/meta.jsp"%>
<script type="text/javascript">
	$(function() {
		DWZ.init("dwz.frag.xml", {
			//	loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
			loginUrl:"login.jsp",	// 跳到登录页面
			statusCode : {
				ok : 200,
				error : 300,
				timeout : 301
			},  //【可选】
			/* pageInfo : {
				pageNum : "pageNum",
				numPerPage : "numPerPage",
				orderField : "orderField",
				orderDirection : "orderDirection"
			}, */ //【可选】
			debug : false, // 调试模式 【true|false】
			callback : function() {
				initEnv();
				$("#themeList").theme({
					themeBase : "scripts/dwz/themes"
				}); // themeBase 相对于index页面的主题base路径
			}
		});
		$.ajaxSettings.global = false;
	});


</script>
<script type="text/javascript">


</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">

				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			
			
			<a href="${ctx }/logout">logout</a>
			</div>
			<!-- navMenu -->


		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse">
						<div></div>
					</div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse">
					<h2>主菜单</h2>
					<div>收缩</div>
				</div>

				<div class="accordion" fillSpace="sidebar">
					<!-- <div class="accordionHeader">
						<h2>
							<span>Folder</span>界面组件
						</h2>
					</div> -->
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<!-- <li><a>主框架面板</a>
								<ul>
									<li><a href="main.html" target="navTab" rel="main">我的主页</a></li>
									<li><a href="http://www.baidu.com" target="navTab"
										rel="page1">页面一(外部页面)</a></li>

								</ul></li> -->
							<li><a>dwz布局学习</a>
								<ul>
								
								</ul></li>
							<li><a>Demo样例</a>
								<ul>
									<li><a href="${ctx}/demoEntityList" target="navTab" rel="demoEntityList" external="false">样例实体列表页面</a></li>
								</ul></li>
						</ul>
					</div>

				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent">
						<!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span
										class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div>
					<!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div>
					<!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<div class="left">qq
							</div>
							<div class="right">ee
							</div>

						</div>
						<div class="pageFormContent" layoutH="80"
							style="margin-right: 230px"></div>


					</div>

				</div>
			</div>
		</div>

	</div>

	<!-- <div id="footer">

	</div> -->



</body>
</html>