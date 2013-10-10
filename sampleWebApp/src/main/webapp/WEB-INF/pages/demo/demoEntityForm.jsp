<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<s:fielderror></s:fielderror>
		<form id="demoForm" method="post" action="saveEntity" class="pageForm required-validate" onsubmit="return formValidateCallback(this,dialogFormCallBack)">
			<div class="pageFormContent nowrap" layoutH="97">
				<s:hidden name="editType" />
				<s:hidden name="gridId" />
				<fieldset style="display: block; float: left; width: 300px;">
					<legend> 简单类型 </legend>

					<dl>
						<dd>
							<s:textfield key="demoEntity.demoId" name="model.demoId" cssClass="" />
						</dd>
					</dl>
					<dl>
						<dd>
							<s:textfield key="demoEntity.intField" name="model.intField" />
						</dd>
					</dl>
					<dl>
						<dd>
							<s:textfield key="demoEntity.longField" name="model.longField" cssClass="required" />
						</dd>
					</dl>
					<dl>
						<dd>
							<s:textfield key="demoEntity.shortField" name="model.shortField" />
						</dd>
					</dl>
					<dl>
						<dd>
							<s:textfield key="demoEntity.byteField" name="model.byteField" />
						</dd>
					</dl>
					<dl>
						<dd>
							<s:checkbox key="demoEntity.booleanField" name="model.booleanField" />
						</dd>
					</dl>
					<dl>
						<dd>
							<s:textfield key="demoEntity.charField" name="model.charField" />
						</dd>
					</dl>
					<dl>
						<dd>
							<s:textfield key="demoEntity.floatField" name="model.floatField">
								<s:param name="value">
									<s:number name="model.floatField" type="number" />
								</s:param>
							</s:textfield>
						</dd>
					</dl>
					<dl>
						<dd>
							<s:textfield id="demoEntity_doubleField" key="demoEntity.doubleField" name="model.doubleField" required="true" />
						</dd>
					</dl>
				</fieldset>
				<fieldset style="display: block; float: right; width: 300px;">
					<legend> 对象类型 </legend>
					<dl>
						<dd>
							<s:textfield key="demoEntity.intObjField" name="model.intObjField" />
						</dd>
					</dl>
					<dl>
						<dd>
							<s:textfield key="demoEntity.longObjField" name="model.longObjField" />
						</dd>
					</dl>
					<dl>
						<dd>
							<s:textfield key="demoEntity.shortObjField" name="model.shortObjField" />
						</dd>
					</dl>
					<dl>
						<dd>
							<s:textfield key="demoEntity.byteObjField" name="model.byteObjField" />
						</dd>
					</dl>
					<dl>
						<dd>
							<s:checkbox key="demoEntity.booleanObjField" name="model.booleanObjField" />
						</dd>
					</dl>
					<dl>
						<dd>
							<s:textfield key="demoEntity.charObjField" name="model.charObjField" />
						</dd>
					</dl>
					<dl>
						<dd>
							<s:textfield key="demoEntity.floatObjField" name="model.floatObjField" />
						</dd>
					</dl>
					<dl>
						<dd>
							<s:textfield key="demoEntity.doubleObjField" name="model.doubleObjField" />
						</dd>
					</dl>
					<dl>
						<dd>
							<s:textfield id="demoEntity_dateField" key="demoEntity.dateField" name="model.dateField">
								<s:param name="value">
									<s:date name="model.dateField" format="%{getText('date.format')}" />
								</s:param>
							</s:textfield>
						</dd>
					</dl>
					<dl>
						<dd>
							<s:textfield key="demoEntity.stringField" name="model.stringField" />
						</dd>
					</dl>
					<dl>
						<dd>
							<s:textfield key="demoEntity.bigDecimalField" name="model.bigDecimalField" />
						</dd>
					</dl>
					<dl>
						<dd>
							<s:textfield key="demoEntity.timestampField" name="model.timestampField">
								<s:param name="value">
									<s:date name="model.timestampField" format="%{getText('timestamp.format')}" />
								</s:param>
							</s:textfield>
						</dd>
					</dl>
				</fieldset>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">提交</button>
							</div>
						</div></li>
					<li><div class="button">
							<div class="buttonContent">
								<button type="button" class="close">取消</button>
							</div>
						</div></li>
				</ul>
			</div>
		</form>

	</div>
</body>
</html>