<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglibs.jsp"%>
<%-- <%@ include file="/WEB-INF/views/include.inc.jsp"%> --%>
<div class="pageContent">
<div class="panelBar">
		<ul class="toolBar">
		<li><a class="icon" href="#"
				onclick="javascript:processSimpleRequest('${ctx}/security/rebuildOrgTree');"><span>重建组织树</span></a></li>


	<!-- 		<li><a class="add" href="#"
				onclick="javascript:addFormDialog('addEntity','样例添加',800,600,'organizationList');"><span>添加</span></a></li>
			<li><a class="delete" href="#"
				onclick="javascript:processMultiSelectedRecords('organizationList','deleteEntity','你确认要删除demo数据吗?');"><span>删除</span></a></li>
			<li><a class="edit" href="#"
				onclick="javascript:editFormDialog('editEntity','样例修改',800,600,'organizationList');"><span>修改</span></a></li>
			<li class="line">line</li>
				<li><a class="icon" href="#"
				onclick="javascript:exportEntity('exportEntity','organizationList',1);"><span>导出EXCEL</span></a></li>
			<li><a class="icon"
				href="javascript:$.printBox('organizationGridContainer')"><span>打印</span></a></li>
			<li><a class="icon" href="#"
				onclick="javascript:searchAreaToggle(this,'organizationSearchArea','organizationGridContainer',80);"><span>收起</span></a></li> -->
		</ul>
	</div>
	<div class="tabs">
		<div class="tabsContent">
			<div>
				<div layoutH="40" id="jbsxBox2organizationTree" style="float:left; display:block; overflow:auto; width:300px; border:solid 1px #CCC; line-height:21px; background:#fff">
					<%-- <c:import url="/security/organizationList"/> --%>
				<jsp:include page="/WEB-INF/pages/organization/organizationTree.jsp"></jsp:include>
				</div>

				<div layoutH="0" id="jbsxBox2organizationList" class="unitBox" style="margin-left:306px;">
					<%-- <c:import url="/security/organizationList"/> --%>
				<jsp:include page="/WEB-INF/pages/organization/organizationList.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>