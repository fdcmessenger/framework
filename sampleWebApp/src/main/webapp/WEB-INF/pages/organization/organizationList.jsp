<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	var gridIdString = "#organizationList";

	jQuery(document).ready(
			function() {

				grid = jQuery(gridIdString);
				grid.jqGrid({
					url : "${ctx}/security/organizationGrid",
					datatype : "json",
					mtype : "GET",
					colModel : [ {
						name : 'id',
						index : 'id',
						align : 'center',
						label : '<s:text name="organization.id" />',
						hidden : false,
						key : true
					}, {
						name : 'name',
						index : 'name',
						align : 'center',
						label : '<s:text name="organization.name" />'
					}, {
						name : 'description',
						index : 'description',
						align : 'center',
						label : '<s:text name="organization.description" />'

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
					pager : '#organizationPager',
					sortname : 'id',
					sortorder : 'desc',
					caption : '<s:text name="organizationList.title" />',
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

				jQuery(grid).jqGrid('navGrid', '#organizationPager', {
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
		clear_input_elements("organizationSearchArea");
		propertyFilterSearch('organizationSearchForm', 'organizationList');
	}
</script>
</head>
<body>
	<div id="organizationSearchArea" class="pageHeader">
		<form id="organizationSearchForm">
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
							onclick="javascript:propertyFilterSearch('organizationSearchForm','organizationList');"><span>搜索</span></a>
							<a class="button" href="#" onclick="javascript:cleanSearch();"><span>清空</span></a></td>
					</tr>
				</table>


			</div>
		</form>
	</div>
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="#"
				onclick="javascript:addFormDialog('addEntity','样例添加',800,600,'organizationList');"><span>添加</span></a></li>
			<li><a class="delete" href="#"
				onclick="javascript:processMultiSelectedRecords('organizationList','deleteEntity','你确认要删除demo数据吗?');"><span>删除</span></a></li>
			<li><a class="edit" href="#"
				onclick="javascript:editFormDialog('editEntity','样例修改',800,600,'organizationList');"><span>修改</span></a></li>
			<li class="line">line</li>
			<!-- 	<li><a class="icon" href="#"
				onclick="javascript:exportEntity('exportEntity','organizationList',1);"><span>导出EXCEL</span></a></li>
			<li><a class="icon"
				href="javascript:$.printBox('organizationGridContainer')"><span>打印</span></a></li> -->
			<li><a class="icon" href="#"
				onclick="javascript:searchAreaToggle(this,'organizationSearchArea','organizationGridContainer',37);"><span>收起</span></a></li>
		</ul>
	</div>
	<div id="organizationGridContainer" class="pageContent" layoutH="37">
		<table id="organizationList"></table>
		<div id="organizationPager"></div>
	</div>
</body>
</html>