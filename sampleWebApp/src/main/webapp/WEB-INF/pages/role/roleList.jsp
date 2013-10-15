<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	var gridIdString = "#roleList";

	jQuery(document).ready(
			function() {

				grid = jQuery(gridIdString);
				grid.jqGrid({
					url : "${ctx}/security/roleGrid",
					datatype : "json",
					mtype : "GET",
					colModel : [ {
						name : 'id',
						index : 'id',
						align : 'center',
						label : '<s:text name="role.id" />',
						hidden : false,
						key : true
					}, {
						name : 'name',
						index : 'name',
						align : 'center',
						label : '<s:text name="role.name" />'
					}, {
						name : 'description',
						index : 'description',
						align : 'center',
						label : '<s:text name="role.description" />'

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
					pager : '#rolePager',
					sortname : 'id',
					sortorder : 'desc',
					caption : '<s:text name="roleList.title" />',
					height : "685",

					// width : 800,
					autowidth : true,
					forceFit : false,
					shrinkToFit : false,
					//loadui: "disable",
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

				jQuery(grid).jqGrid('navGrid', '#rolePager', {
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

	function cleanSearch() {
		clear_input_elements("roleSearchArea");
		propertyFilterSearch('roleSearchForm', 'roleList');
	}
</script>
</head>
<body>
	<div id="roleSearchArea" class="pageHeader">
		<form id="roleSearchForm">
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
							onclick="javascript:propertyFilterSearch('roleSearchForm','roleList');"><span>搜索</span></a>
							<a class="button" href="#" onclick="javascript:cleanSearch();"><span>清空</span></a></td>
					</tr>
				</table>


			</div>
		</form>
	</div>
	<div class="panelBar">
		<ul class="toolBar">
		<li><a class="add" href="#"
				onclick="javascript:addFormDialog('${ctx}/security/addRole','用户添加',800,600,'roleList');"><span>添加</span></a></li>
			<li><a class="delete" href="#"
				onclick="javascript:processMultiSelectedRecords('roleList','${ctx}/security/deleteRoles','你确认要删除role吗?');"><span>删除</span></a></li>
			<li><a class="edit" href="#"
				onclick="javascript:editFormDialog('${ctx}/security/editRole','用户修改',800,600,'roleList');"><span>修改</span></a></li>
			<li class="line">line</li>
			<!-- 	<li><a class="icon" href="#"
				onclick="javascript:exportEntity('exportEntity','roleList',1);"><span>导出EXCEL</span></a></li>
			<li><a class="icon"
				href="javascript:$.printBox('roleGridContainer')"><span>打印</span></a></li> -->
			<li><a class="icon" href="#"
				onclick="javascript:searchAreaToggle(this,'roleSearchArea','roleGridContainer',37);"><span>收起</span></a></li>
		</ul>
	</div>
	<div id="roleGridContainer" class="pageContent" layoutH="37">
		<table id="roleList"></table>
		<div id="rolePager"></div>
	</div>
</body>
</html>