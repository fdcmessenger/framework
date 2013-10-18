<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	//var gridIdString = "#moduleList";

	jQuery(document).ready(
			function() {

				grid = jQuery("#moduleList");
				grid.jqGrid({
					url : "${ctx}/security/moduleGrid",
					datatype : "json",
					mtype : "GET",
					colModel : [ {
						name : 'id',
						index : 'id',
						align : 'center',
						label : '<s:text name="module.id" />',
						hidden : true,
						key : true
					},
					{
						name : 'name',
						index : 'name',
						align : 'center',
						label : '<s:text name="module.name" />'
					},
					{
						name : 'parentNode.name',
						index : 'parentNode.name',
						align : 'center',
						label : '<s:text name="module.parentName" />'
					},
					{
						name : 'url',
						index : 'url',
						align : 'center',
						label : '<s:text name="module.url" />'
					},
					{
						name : 'sn',
						index : 'sn',
						align : 'center',
						label : '<s:text name="module.sn" />'
					},
					{
						name : 'priority',
						index : 'priority',
						align : 'center',
						label : '<s:text name="module.priority" />'
					},


					{
						name : 'description',
						index : 'description',
						align : 'center',
						label : '<s:text name="module.description" />'

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
					//rownumWidth : 50,
					pager : '#modulePager',
					sortname : 'id',
					sortorder : 'desc',
					//caption : '<s:text name="moduleList.title" />',
					//height : "685",

					 width : 800,
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

				 jQuery(grid).jqGrid('navGrid', '#modulePager', {
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
		clear_input_elements("moduleSearchArea");
		propertyFilterSearch('moduleSearchForm', 'moduleList');
	}
</script>
</head>
<body>
<!-- 	<div id="moduleSearchArea" class="pageHeader">
		<form id="moduleSearchForm">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>样例主键小于:<input name="filter_lt_demoId" /></td>
						<td>样例主键大于:<input name="filter_gt_demoId" /></td>
							<td>样例主键小于:<input name="filter_lt_demoId1" /></td>
						<td>样例主键大于:<input name="filter_gt_demoId2" /></td>
						<td>样例主键小于:<input name="filter_lt_demoId3" /></td>
						<td>样例主键小于:<input name="filter_lt_demoId4" /></td>
						<td><a class="button buttonActive" href="#"
							onclick="javascript:propertyFilterSearch('moduleSearchForm','moduleList');"><span>搜索</span></a>
							<a class="button" href="#" onclick="javascript:cleanSearch();"><span>清空</span></a></td>
					</tr>
				</table>


			</div>
		</form>
	</div> -->

	<div id="moduleGridContainer" class="pageContent" layoutH="8">
		<table id="moduleList"></table>
		<div id="modulePager"></div>
	</div>
</body>
</html>