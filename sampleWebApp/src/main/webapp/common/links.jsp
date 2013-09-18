<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>

<%-- <link href="${ctx}/scripts/dwz/themes/azure/style.css" rel="stylesheet" type="text/css" media="screen"/> --%>
<link href="${ctx}/scripts/dwz/themes/green/style.css" rel="stylesheet" type="text/css" media="screen"/>
<%-- <link href="${ctx}/scripts/dwz/themes/purple/style.css" rel="stylesheet" type="text/css" media="screen"/> --%>
<%-- <link href="${ctx}/scripts/dwz/themes/silver/style.css" rel="stylesheet" type="text/css" media="screen"/> --%>
<%-- <link href="${ctx}/scripts/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/> --%>
<link href="${ctx}/scripts/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx}/scripts/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="${ctx}/scripts/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>

<!--[if IE]>
<link href="${ctx}/scripts/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="${ctx}/scripts/dwz/js/speedup.js" type="text/javascript"></script>
<![endif]-->

<%-- <script src="${ctx}/scripts/dwz/js/jquery-1.7.2.js" type="text/javascript"></script> --%>

<script type="text/javascript"	src="${ctx}/scripts/jquery/jquery-1.8.1.js"></script>
<script type="text/javascript"	src="${ctx}/scripts/jqueryui/js/jquery-ui-1.8.23.custom.min.js"></script>
<%-- <script type="text/javascript"	src="${ctx}/scripts/jqueryui/js/jquery.ui.datepicker-zh-CN.min.js"></script> --%>
<%-- <link rel="stylesheet" type="text/css"	href="${ctx}/scripts/jqueryui/css/cupertino/jquery-ui.css"/> --%>
<link rel="stylesheet" type="text/css"	href="${ctx}/scripts/jqueryui/css/le-frog/jquery-ui.css"/>

<script src="${ctx}/scripts/dwz/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
<%-- <script src="${ctx}/scripts/dwz/xheditor/xheditor-1.1.14-zh-cn.min.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script> --%>

<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<%-- <script type="text/javascript" src="${ctx}/scripts/dwz/chart/raphael.js"></script>
<script type="text/javascript" src="${ctx}/scripts/dwz/chart/g.raphael.js"></script>
<script type="text/javascript" src="${ctx}/scripts/dwz/chart/g.bar.js"></script>
<script type="text/javascript" src="${ctx}/scripts/dwz/chart/g.line.js"></script>
<script type="text/javascript" src="${ctx}/scripts/dwz/chart/g.pie.js"></script>
<script type="text/javascript" src="${ctx}/scripts/dwz/chart/g.dot.js"></script> --%>

<script src="${ctx}/scripts/dwz/js/dwz.core.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.util.date.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.drag.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.tree.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.accordion.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.ui.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.theme.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.navTab.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.tab.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.resize.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.dialog.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.stable.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.ajax.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.pagination.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.database.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.effects.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.panel.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.history.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.combox.js" type="text/javascript"></script>
<script src="${ctx}/scripts/dwz/js/dwz.print.js" type="text/javascript"></script>
<!--
<script src="${ctx}/scripts/dwz/bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="${ctx}/scripts/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<!--jqgrid lib  -->
<script type="text/javascript" src="${ctx}/scripts/jqgrid/js/jquery.jqGrid.src.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jqgrid/js/i18n/grid.locale-cn.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/scripts/jqgrid/css/ui.jqgrid.css"/>

<script src="${ctx}/scripts/public.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/scripts/styles.css"/>