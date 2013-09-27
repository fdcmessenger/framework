<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglibs.jsp"%>
<%-- <%@ include file="/WEB-INF/views/include.inc.jsp"%> --%>    
<div class="pageContent">
	<div class="tabs">
		<div class="tabsContent">
			<div>
				<div layoutH="5" id="jbsxBox2moduleTree" style="float:left; display:block; overflow:auto; width:300px; border:solid 1px #CCC; line-height:21px; background:#fff">
					<%-- <c:import url="/security/moduleList"/> --%>
				<jsp:include page="/WEB-INF/pages/module/moduleTree.jsp"></jsp:include>
				</div>
				
				<div layoutH="5" id="jbsxBox2moduleList" class="unitBox" style="margin-left:306px;">
					<%-- <c:import url="/security/moduleList"/> --%>
				<jsp:include page="/WEB-INF/pages/module/moduleList.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>