<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="pageContent">
	<form method="post" action="${ctx }/security/saveUser" class="pageForm required-validate" onsubmit="return formValidateCallback(this,dialogFormCallBack)">

	<c:if test="${editType==0}"><input type="hidden" name="model.id" class="required" size="20"	maxlength="32" value="${model.id}"/></c:if>

	<div class="pageFormContent" layoutH="58">
		<dl>
			<dt>名称：</dt>
			<dd>
				<input type="text" name="name" class="required" size="32" maxlength="64" value="${model.name }" alt="请输入组织名称"/>
			</dd>
		</dl>
		<dl>
			<dt>父组织：</dt>
			<dd>
				<input name="model.parentNode.id" value="${model.parentNode.id }" type="hidden"/>
				<input class="required" name="model.parentNode.name" type="text" readonly="readonly" value="${model.parentNode.name }"/>
				<a class="btnLook" href="${ctx}/security/lookupOrg" lookupGroup="model.parentNode" mask="true" title="更改父组织" width="400">查找带回</a>
			</dd>
		</dl>
		<dl>
			<dt>描述：</dt>
			<dd>
				<textarea name="description" cols="28" rows="3" maxlength="255">${model.description }</textarea>
			</dd>
		</dl>
	</div>

	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
		</ul>
	</div>
</form>
</div>