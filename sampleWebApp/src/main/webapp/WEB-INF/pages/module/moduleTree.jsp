<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
		var zTree, rMenu;
		function OnRightClick(event, treeId, treeNode) {
			if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
				zTree.cancelSelectedNode();
				showRMenu("root", event.clientX, event.clientY);
			} else if (treeNode && !treeNode.noR) {
				zTree.selectNode(treeNode);
				showRMenu("node", event.clientX, event.clientY);
			}
		}

		function showRMenu(type, x, y) {
			$("#rMenu ul").show();
			if (type=="root") {
				$("#m_del").hide();
				$("#m_check").hide();
				$("#m_unCheck").hide();
			} else {
				$("#m_del").show();
				$("#m_check").show();
				$("#m_unCheck").show();
			}
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}
		function hideRMenu() {
			if (rMenu) rMenu.css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		function onBodyMouseDown(event){
			if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
				rMenu.css({"visibility" : "hidden"});
			}
		}
		var addCount = 1;
		function addTreeNode() {
			hideRMenu();
			var newNode = { name:"增加" + (addCount++)};
			if (zTree.getSelectedNodes()[0]) {
				newNode.checked = zTree.getSelectedNodes()[0].checked;
				zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
			} else {
				zTree.addNodes(null, newNode);
			}
		}
		function removeTreeNode() {
			hideRMenu();
			var nodes = zTree.getSelectedNodes();
			if (nodes && nodes.length>0) {
				var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
				if (confirm(msg)==true){
					 $.post('deleteDemoTreeNode',{delIds:nodes[0].id}, function(data) {});
					
					
					zTree.removeNode(nodes[0]);
				}
				
				/* if (nodes[0].children && nodes[0].children.length > 0) {
					var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
					if (confirm(msg)==true){
						zTree.removeNode(nodes[0]);
					}
				} else {
					zTree.removeNode(nodes[0]);
				} */
			}
		}
		function checkTreeNode(checked) {
			var nodes = zTree.getSelectedNodes();
			if (nodes && nodes.length>0) {
				zTree.checkNode(nodes[0], checked, true);
			}
			hideRMenu();
		}
		function resetTree() {
			hideRMenu();
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		}
		function beforeDrag(treeId, treeNodes) {
			for (var i=0,l=treeNodes.length; i<l; i++) {
				if (treeNodes[i].drag === false) {
					return false;
				}
			}
			return true;
		}
		function beforeDrop(treeId, treeNodes, targetNode, moveType) {
			
			 $.post('moveDemotreeNode',{from:treeNodes[0].id,target:targetNode.id}, function(data) {
				//alert(data);
				}); 
			return targetNode ? targetNode.drop !== false : true;
		}
		
		var setting = {
				edit: {
					enable: false,
					showRemoveBtn: false,
					showRenameBtn: false
				},
				view: {
					dblClickExpand: false
				},
				check: {
					enable: false
				},
				callback: {
					onRightClick: OnRightClick,
					beforeDrag: beforeDrag,
					beforeDrop: beforeDrop
				},
				data: {
					key:{
						name:"name"
					},
					simpleData: {
						enable: true,
						idKey:"id",
						pIdKey:"parent"
					}
				}
		};

		
		
		function initTree(){
			$.get("${ctx}/security/moduleTreeJson", function(data){
				var	treeData = data.fullTreeList;
				zTree=$.fn.zTree.init($("#treeDemo"), setting, treeData);
				zTree.expandAll(true);
				//zTree = $.fn.zTree.getZTreeObj("treeDemo");
				rMenu = $("#rMenu");
					});
		}
		
		//var treeData;
		
		$(document).ready(function() {
			initTree();
			/* $.get("demoTreeJson", function(data){
			var	treeData = data.fullTreeList;
			zTree=$.fn.zTree.init($("#treeDemo"), setting, treeData);
			zTree.expandAll(true);
			//zTree = $.fn.zTree.getZTreeObj("treeDemo");
			rMenu = $("#rMenu");
				}); */
			
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

<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>


<div id="rMenu">
	<ul>
		<li id="m_add" onclick="addTreeNode();">增加节点</li>
		<li id="m_del" onclick="removeTreeNode();">删除节点</li>
		<li id="m_check" onclick="checkTreeNode(true);">Check节点</li>
		<li id="m_unCheck" onclick="checkTreeNode(false);">unCheck节点</li>
		<li id="m_reset" onclick="resetTree();">恢复zTree</li>
	</ul>
</div>
</BODY>
</HTML>




















