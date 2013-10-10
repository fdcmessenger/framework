<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
<%-- <%@ include file="/common/links.jsp"%> --%>
<%-- <link rel="stylesheet" href="${ctx}/scripts/zTree/css/demo.css" type="text/css"> --%>
<link rel="stylesheet" href="${ctx}/scripts/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctx}/scripts/zTree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx}/scripts/zTree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ctx}/scripts/zTree/js/jquery.ztree.exedit-3.5.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<SCRIPT type="text/javascript">
<!--
	$(document).ready(
			function() {
				initTree();
				var uloTree;
				var setting = {
					edit : {
						enable : false,
						showRemoveBtn : false,
						showRenameBtn : false
					},
					view : {
						dblClickExpand : false
					},
					check : {
						enable : false
					},
					callback : {
						onClick : onOrgClick
					},
					data : {
						key : {
							name : "name"
						},
						simpleData : {
							enable : true,
							idKey : "id",
							pIdKey : "parent"
						}
					}
				};

				function onOrgClick(event, treeId, treeNode, clickFlag) {

					/* var gridId = "organizationList";
					var url = "${ctx}/security/organizationGrid";
					var parentId = treeNode.id;
					if(parentId)
					url += "?filter_eq_parentNode.id=" + parentId;
					url = encodeURI(url);
					jQuery("#" + gridId).jqGrid('setGridParam', {
					url : url,
					page : 1
					}).trigger("reloadGrid"); 
					 */
					 
					/*  onclick=\"$.bringBack({id:'" + o.getId() + "', name:'" + o.getName() + "'})
 */					console.log(treeNode);
					 $.bringBack({id:treeNode.id , name: treeNode.name });
					 
					 
				}

				function initTree() {
					$.get("${ctx}/security/organizationTreeJson",
							function(data) {
								var treeData = data.fullTreeList;
								uloTree = $.fn.zTree.init($("#usrLookUpOrg"),
										setting, treeData);
								uloTree.expandAll(true);
								//uloTree = $.fn.zTree.getZTreeObj("usrLookUpOrg");
								//rMenu = $("#rMenu");
							});
				}

			});
//-->
</SCRIPT>
<style type="text/css">
div#rMenu {
	position: absolute;
	visibility: hidden;
	top: 0;
	background-color: #555;
	text-align: left;
	padding: 2px;
}

div#rMenu ul li {
	margin: 1px 0;
	padding: 0 0px;
	cursor: pointer;
	list-style: none outside none;
	/* background-color: #DFDFDF; */
}
</style>
</HEAD>

<BODY>
<div class="pageContent">
	<div class="pageFormContent" layoutH="58">
	<div class="zTreeDemoBackground left">
		<ul id="usrLookUpOrg" class="ztree"></ul>
	</div>
</div>
	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
		</ul>
	</div>
</div>
</BODY>
</HTML>




















