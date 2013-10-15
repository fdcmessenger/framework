<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="pageContent">
	<form method="post" action="${ctx }/security/saveUser?gridId=${gridId}" class="pageForm required-validate" onsubmit="return formValidateCallback(this,dialogFormCallBack)">
		<div class="pageFormContent" layoutH="58">
		<c:if test="${editType==0}"><input type="hidden" name="model.id" class="required" size="20"
					maxlength="32" value="${model.id}"/></c:if>
			<p>
				<label>登录名称：</label> <input type="text" name="model.username" class="required" size="20"
					maxlength="32" value="${model.username}"/>
			</p>
			<p>
				<label>真实名字：</label> <input type="text" name="model.realname" value="${model.realname}" class="required" size="20"
					maxlength="32" />
			</p>
			<c:if test="${editType==1}">
			<p>
				<label>登录密码：</label> <input type="text" name="model.plainPassword" value="${model.plainPassword}"
					class="required" size="20" maxlength="32" value="123456"
					alt="字母、数字、下划线 6-20位"  /> <span class="info">&nbsp;&nbsp;默认：123456</span>
			</p>
			</c:if>
			<p>
				<label>电话：</label> <input type="text" name="model.phone" value="${model.phone}" class="" size="20"
					maxlength="32" />
			</p>
			<p>
				<label>用户邮箱：</label> <input type="text" name="model.email" value="${model.email}" class="" size="20"
					maxlength="128" />
			</p>
			<p>
				<label>用户状态：</label> <select name="model.status" >
					<option value="enabled" ${model.status == "enabled" ? 'selected="selected"' : ''}>可用</option>
				<option value="disabled" ${model.status == "disabled" ? 'selected="selected"' : ''}>不可用</option>
				</select>
			</p>
			<p>
				<label>关联组织：</label> <input name="model.organization.id" value="${model.organization.id}" type="hidden" /> <input
					class="validate[required] required" name="model.organization.name" type="text" readonly="readonly"  value="${model.organization.name}" /> <a class="btnLook"
					href="${ctx}/security/lookupOrg" lookupGroup="model.organization" title="关联组织" width="400">查找带回</a>
			</p>
		</div>

		<div class="formBar">
			<ul>
				<li><div class="button">
						<div class="buttonContent">
							<button type="submit">确定</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">关闭</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</div>