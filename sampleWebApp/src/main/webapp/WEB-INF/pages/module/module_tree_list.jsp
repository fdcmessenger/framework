<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglibs.jsp"%>
<%-- <%@ include file="/WEB-INF/views/include.inc.jsp"%> --%>
<div class="pageContent">
<div class="panelBar">
		<ul class="toolBar">
		<li><a class="icon" href="#"
				onclick="javascript:processSimpleRequest('${ctx}/security/rebuildTree');"><span>initMenu</span></a></li>


			<li><a class="add" href="#"
				onclick="javascript:addFormDialog('addEntity','样例添加',800,600,'moduleList');"><span>添加</span></a></li>
			<li><a class="delete" href="#"
				onclick="javascript:processMultiSelectedRecords('moduleList','deleteEntity','你确认要删除demo数据吗?');"><span>删除</span></a></li>
			<li><a class="edit" href="#"
				onclick="javascript:editFormDialog('editEntity','样例修改',800,600,'moduleList');"><span>修改</span></a></li>
			<li class="line">line</li>
				<li><a class="icon" href="#"
				onclick="javascript:exportEntity('exportEntity','moduleList',1);"><span>导出EXCEL</span></a></li>
			<li><a class="icon"
				href="javascript:$.printBox('moduleGridContainer')"><span>打印</span></a></li>
			<li><a class="icon" href="#"
				onclick="javascript:searchAreaToggle(this,'moduleSearchArea','moduleGridContainer',80);"><span>收起</span></a></li>
		</ul>
	</div>
	<div class="tabs">
		<div class="tabsContent">
			<div>
				<div layoutH="40" id="jbsxBox2moduleTree" style="float:left; display:block; overflow:auto; width:300px; border:solid 1px #CCC; line-height:21px; background:#fff">
					<%-- <c:import url="/security/moduleList"/> --%>
				<jsp:include page="/WEB-INF/pages/module/moduleTree.jsp"></jsp:include>
				</div>

				<div layoutH="0" id="jbsxBox2moduleList" class="unitBox" style="margin-left:306px;">
					<%-- <c:import url="/security/moduleList"/> --%>
				<jsp:include page="/WEB-INF/pages/module/moduleList.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>