<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

</script>
</head>
<body>
<div class="pageContent">
<form method="post" action="${ctx }/security/savePwd"
class="pageForm required-validate"	onsubmit="return formValidateCallback(this,dialogFormCallBack)">
<!-- class="required-validate pageForm" onsubmit="return validateCallback(this, dialogAjaxDone);"> -->
	<div class="pageFormContent" layouth="58">
		<p>
			<label>当前密码：</label>
			<input type="password" name="plainPassword" class="validate[required, maxSize[32]] required" maxlength="32"/>
		</p>
		<p>
			<label>新密码：</label>
			<input type="password" name="newPassword" id="newPassword" class="validate[required, maxSize[32]] required" maxlength="32"/>
		</p>
		<p>
			<label>确认新密码：</label>
			<input type="password" name="rPassword" class="validate[required,equals[newPassword], maxSize[32]] required" maxlength="32"/>
		</p>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button type="submit">修改</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close" >关闭</button></div></div></li>
		</ul>
	</div>
</form>
</div>
</body>
</html>