<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglibs.jsp"%>
<%-- <%@ include file="/WEB-INF/views/include.inc.jsp"%> --%>
<div class="pageContent">
<div class="panelBar">
		<ul class="toolBar">
		<li><a iconClass="magnet" href="#"
				onclick="javascript:processSimpleRequest('${ctx}/security/rebuildOrgTree');"><span>重建组织树</span></a></li>
<li><a class="add" href="#"
				onclick="javascript:addFormDialog('${ctx}/security/addOrg','用户添加',800,600,'organizationList');"><span>添加</span></a></li>
			<li><a class="delete" href="#"
				onclick="javascript:processMultiSelectedRecords('organizationList','${ctx}/security/deleteOrgs','你确认要删除organization吗?');"><span>删除</span></a></li>
			<li><a class="edit" href="#"
				onclick="javascript:editFormDialog('${ctx}/security/editOrg','用户修改',800,600,'organizationList');"><span>修改</span></a></li>
			<li class="line">line</li>

			<li><a iconClass="shield_add" href="#" onclick="javascript:editFormDialog('${ctx}/security/listUnassignOrganizationRole','分配角色',800,600,'organizationList');"><span>分配角色</span></a></li>
			<li><a iconClass="shield_delete" href="#" onclick="javascript:editFormDialog('${ctx}/security/listAssignedOrganizationRole','删除角色',800,600,'organizationList');"><span>删除角色</span></a></li>

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