<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="pageContent">
	<form method="post" action="${ctx }/security/saveUser" class="pageForm required-validate" onsubmit="return formValidateCallback(this,dialogFormCallBack)">
	<input type="hidden" name="id" value="${organization.id }"/>
	<input type="hidden" name="parent.id" value="${organization.parent.id }"/>
	<div class="pageFormContent" layoutH="58">
		<dl>
			<dt>名称：</dt>
			<dd>
				<input type="text" name="name" class="validate[required,maxSize[64]] required" size="32" maxlength="64" value="${organization.name }" alt="请输入组织名称"/>
			</dd>
		</dl>
		<dl>
			<dt>父组织：</dt>
			<dd>
				<input name="parent.id" value="${organization.parent.id }" type="hidden"/>
				<input class="validate[required] required" name="parent.name" type="text" readonly="readonly" value="${organization.parent.name }"/>
				<a class="btnLook" href="${ctx}/management/security/organization/lookupParent/${organization.id}" lookupGroup="parent" mask="true" title="更改父组织" width="400">查找带回</a>
			</dd>
		</dl>			
		<dl>
			<dt>描述：</dt>
			<dd>
				<textarea name="description" cols="28" rows="3" maxlength="255">${organization.description }</textarea>
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