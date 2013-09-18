<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<!DOCTYPE html>
<html>
<head>
<%-- <%@ include file="/common/links.jsp"%> --%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
    var gridIdString = "#demoTreelist";

    jQuery(document).ready(function() {

	grid = jQuery(gridIdString);
	grid.jqGrid({
	    url : "demoTreeGrid",
	    datatype : "json",
	    mtype : "GET",
	    colModel:[
					{name:'nodeName',index:'nodeName',align:'center',label : '<s:text name="demoTreeNode.nodeName" />',hidden:false},
					{name:'id',index:'nodeId',align:'center',label : '<s:text name="demoTreeNode.nodeId" />',hidden:false,key:true,formatter:'integer'},
			 		 {name:'level',index:'level',align:'center',label : 'level',hidden:false},
					 {name:'parent',index:'parent',align:'center',label : 'parent',hidden:false},
					 {name:'isLeaf',index:'isLeaf',align:'center',label : 'isLeaf',hidden:false},
					 {name:'expanded',index:'expanded',align:'center',label : 'expanded',hidden:false},
					 {name:'lft',index:'lft',align:'center',label : 'lft',hidden:false},
					 {name:'rgt',index:'rgt',align:'center',label : 'rgt',hidden:false}
	                ],

	    jsonReader : {
		root : "pageList", // (2)
		page : "page",
		total : "total",
		records : "records", // (3)
		repeatitems : false
	    // (4)
	    },

	    prmNames : {
		page : "page",
		rows : "pagesize",
		sort : "sortname",
		order : "sortorder"
	    },
	    //rowNum : 10,
	    //rowList : [ 5, 10, 20, 50, 100, 200, 500, 1000, 2000 ],
	    //rownumbers : true,
	   // rownumWidth : 50,
	    //pager : '#demoTreepager',
	    sortname : 'lft',
	    sortorder : 'asc',
	    caption : '<s:text name="demoTreeNodeList.title" />',
	    //height : "685",

	    // width : 800,
	   //autowidth : true,
	   // forceFit : false,
	   // shrinkToFit : false,
	    //loadui: "disable",
	    //multiselect : true,
	    //multiboxonly : true,
	    //hidegrid : false,

	    treeGrid: true,
		treeGridModel: "adjacency",
		ExpandColumn: "nodeName",
        treeIcons: {leaf:'ui-icon-document-b'},
        ExpandColClick: true,

	    gridComplete : function() {
		/* if (jQuery(this).getDataIDs().length > 0) {
			jQuery(this).jqGrid('setSelection',
					jQuery(this).getDataIDs()[0]);//默认选中第一行
		} */
	    },
	    loadComplete : function(json) {
		$(this).trigger(DWZ.eventType.resizeGrid);
		//resizeJqGrid($(this));
		//DWZ.ajaxDone(json);
	    }

	});

	//jQuery(grid).jqGrid('bindKeys');

	/* jQuery(grid).jqGrid('navGrid', '#demoTreepager', {
	    edit : false,
	    add : false,
	    del : false,
	    search : false,
	    excel : false
	}, {}, {}, {}, {
	    multipleSearch : false,
	    multipleGroup : false
	}); */

    });

    function rebuildTree(){

    }

</script>
</head>
<body>

	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="#"
				onclick="javascript:addFormDialog('addEntity','样例添加',800,600,'list');"><span>添加</span></a></li>
			<li><a class="delete" href="#"
				onclick="javascript:processMultiSelectedRecords('list','deleteEntity','你确认要删除demo数据吗?');"><span>删除</span></a></li>
			<li><a class="edit" href="#"
				onclick="javascript:editFormDialog('editEntity','样例修改',800,600,'list');"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#"
				onclick="javascript:processSimpleRequest('rebuildTree');"><span>重建树</span></a></li>
			<li><a class="icon"
				href="javascript:$.printBox('demoTreeGridContainer')"><span>打印</span></a></li>
			<li><a class="icon" href="#"
				onclick="javascript:searchAreaToggle(this,'demoTreeSearchArea','demoTreeGridContainer',0);"><span>收起</span></a></li>
		</ul>
	</div>
	<div id="demoTreeGridContainer" class="pageContent" layoutH="0">
		<table id="demoTreelist"></table>
		<div id="demoTreepager"></div>
	</div>
</body>
</html>