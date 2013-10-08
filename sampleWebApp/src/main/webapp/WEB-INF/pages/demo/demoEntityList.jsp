<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	var gridIdString = "#list";

	jQuery(document).ready(
			function() {

				grid = jQuery(gridIdString);
				grid.jqGrid({
					url : "demoEntityGrid",
					datatype : "json",
					mtype : "GET",
					colModel : [ {
						name : 'demoId',
						index : 'id',
						align : 'center',
						label : '<s:text name="demoEntity.demoId" />',
						hidden : false,
						key : true
					}, {
						name : 'intField',
						index : 'intField',
						align : 'center',
						label : '<s:text name="demoEntity.intField" />',
						sorttype : 'int',
						editable : true,
						editrules : {
							number : true
						}
					}, {
						name : 'longField',
						index : 'longField',
						align : 'center',
						label : '<s:text name="demoEntity.longField" />'
					}, {
						name : 'shortField',
						index : 'shortField',
						align : 'center',
						label : '<s:text name="demoEntity.shortField" />'
					}, {
						name : 'booleanField',
						index : 'booleanField',
						align : 'center',
						label : '<s:text name="demoEntity.booleanField" />',
						editable : true
					}, {
						name : 'charField',
						index : 'charField',
						align : 'center',
						label : '<s:text name="demoEntity.charField" />',
						editable : true
					}, {
						name : 'floatField',
						index : 'floatField',
						align : 'center',
						label : '<s:text name="demoEntity.floatField" />',
						editable : true
					}, {
						name : 'doubleField',
						index : 'doubleField',
						align : 'center',
						label : '<s:text name="demoEntity.doubleField" />',
						editable : true
					}, {
						name : 'byteField',
						index : 'byteField',
						align : 'center',
						label : '<s:text name="demoEntity.byteField" />',
						editable : true
					}, {
						name : 'intObjField',
						index : 'intObjField',
						align : 'center',
						label : '<s:text name="demoEntity.intObjField" />',
						editable : true
					}, {
						name : 'longObjField',
						index : 'longObjField',
						align : 'center',
						label : '<s:text name="demoEntity.longObjField" />',
						editable : true
					}, {
						name : 'shortObjField',
						index : 'shortObjField',
						align : 'center',
						label : '<s:text name="demoEntity.shortObjField" />',
						editable : true
					}, {
						name : 'byteObjField',
						index : 'byteObjField',
						align : 'center',
						label : '<s:text name="demoEntity.byteObjField" />',
						editable : true
					}, {
						name : 'booleanObjField',
						index : 'booleanObjField',
						align : 'center',
						label : '<s:text name="demoEntity.booleanObjField" />',
						editable : true
					}, {
						name : 'charObjField',
						index : 'charObjField',
						align : 'center',
						label : '<s:text name="demoEntity.charObjField" />',
						editable : true
					}, {
						name : 'floatObjField',
						index : 'floatObjField',
						align : 'center',
						label : '<s:text name="demoEntity.floatObjField" />',
						editable : true
					}, {
						name : 'doubleObjField',
						index : 'doubleObjField',
						align : 'center',
						label : '<s:text name="demoEntity.doubleObjField" />',
						editable : true
					}, {
						name : 'dateField',
						index : 'dateField',
						align : 'center',
						label : '<s:text name="demoEntity.dateField" />',
						editable : true
					}, {
						name : 'stringField',
						index : 'stringField',
						align : 'center',
						label : '<s:text name="demoEntity.stringField" />',
						editable : true
					}, {
						name : 'bigDecimalField',
						index : 'bigDecimalField',
						align : 'center',
						label : '<s:text name="demoEntity.bigDecimalField" />',
						editable : true
					}, {
						name : 'timestampField',
						index : 'timestampField',
						align : 'center',
						label : '<s:text name="demoEntity.timestampField" />',
						editable : true
					} ],

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
					rowNum : 10,
					rowList : [ 5, 10, 20, 50, 100, 200, 500, 1000, 2000 ],
					rownumbers : true,
					rownumWidth : 50,
					pager : '#pager',
					sortname : 'demoId',
					sortorder : 'desc',
					caption : '<s:text name="demoEntityList.title" />',
					height : 900,

					width : 1000,
					autowidth : true,
					forceFit : false,
					shrinkToFit : false,
					loadui: "disable",
					multiselect : true,
					multiboxonly : true,
					hidegrid : false,
					loadError : function(xhr, status, error) {
						console.log(xhr.responseText);
						alertMsg.error(xhr.responseText);
					},
					gridComplete : function() {
						if (jQuery(this).getDataIDs().length > 0) {
							jQuery(this).jqGrid('setSelection',
									jQuery(this).getDataIDs()[0]);//默认选中第一行
						}
					},
					loadComplete : function(json) {
						$(this).trigger(DWZ.eventType.resizeGrid);
					}

				});

				//jQuery(grid).jqGrid('bindKeys');

				jQuery(grid).jqGrid('navGrid', '#pager', {
					edit : false,
					add : false,
					del : false,
					search : false,
					excel : false
				}, {}, {}, {}, {
					multipleSearch : false,
					multipleGroup : false
				});

			});

	function cleanDemoEntitySearch() {
		clear_input_elements("demoEntitySearchArea");
		propertyFilterSearch('demoEntitySearchForm', 'list');
	}
</script>
</head>
<body>
	<div id="demoEntitySearchArea" class="pageHeader">
		<form id="demoEntitySearchForm">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>样例主键小于:<input name="filter_lt_demoId" /></td>
						<td>样例主键大于:<input name="filter_gt_demoId" /></td>
						<!-- 	<td>样例主键小于:<input name="filter_lt_demoId1" /></td>
						<td>样例主键大于:<input name="filter_gt_demoId2" /></td>
						<td>样例主键小于:<input name="filter_lt_demoId3" /></td>
						<td>样例主键小于:<input name="filter_lt_demoId4" /></td> -->
						<td><a class="button buttonActive" href="#"
							onclick="javascript:propertyFilterSearch('demoEntitySearchForm','list');"><span>搜索</span></a>
							<a class="button" href="#"
							onclick="javascript:cleanDemoEntitySearch();"><span>清空</span></a></td>
					</tr>
				</table>


			</div>
		</form>
	</div>
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="#"
				onclick="javascript:addFormDialog('addEntity','样例添加',800,600,'list');"><span>添加</span></a></li>
			<li><a class="delete" href="#"
				onclick="javascript:processMultiSelectedRecords('list','deleteEntity','你确认要删除demo数据吗?');"><span>删除</span></a></li>
			<li><a class="edit" href="#"
				onclick="javascript:editFormDialog('editEntity','样例修改',800,600,'list');"><span>修改</span></a></li>
			<li class="line">line</li>
		<!-- 	<li><a class="icon" href="#"
				onclick="javascript:exportEntity('exportEntity','list',1);"><span>导出EXCEL</span></a></li>
			<li><a class="icon"
				href="javascript:$.printBox('demoEntityGridContainer')"><span>打印</span></a></li> -->
			<li><a class="icon" href="#"
				onclick="javascript:searchAreaToggle(this,'demoEntitySearchArea','demoEntityGridContainer',37);"><span>收起</span></a></li>
		</ul>
	</div>
	<div id="demoEntityGridContainer" class="pageContent" layoutH="37">
		<table id="list"></table>
		<div id="pager"></div>
	</div>
</body>
</html>