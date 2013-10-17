<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
<!--
	// top
	jQuery(document)
			.ready(
					function() {
						var $fieldset = $("#newPermissonInput");

						var $name = $("input[name=_name]", $fieldset);
						var $shortName = $("input[name=_shortName]", $fieldset);
						var $description = $("input[name=_description]",
								$fieldset);

						$("#newPermission")
								.click(
										function(event) {
											/*    var nameValidate = !$name.validationEngine('validate');
											   var shortNameValidate = !$shortName.validationEngine('validate');
											   var descriptionValidate = !$description.validationEngine('validate');
											   
												// 验证
											   if (!nameValidate || !shortNameValidate || !descriptionValidate) {
											   	return false;
											   } */
											var $form = $("#moduleNewPermissionForm");
											if (!$form.valid()) {
												return false;
											}
											var $toNewPermission = $("div.toNewPermission");
											// 判断是否有定义权限
											var maxId = 0;
											if ($("input:last",
													$toNewPermission).length > 0) {
												maxId = parseInt($(
														"input:last",
														$toNewPermission).attr(
														"rel")) + 1;
											}
											/* $toNewPermission
											.append($name.val()
													+ '('
													+ $shortName.val()
													+ ')'
													+ '<input type="checkbox" name="model.permissions['
													+ maxId
													+ '].id" value="'
													+ '" checked="checked" rel="'
													+ maxId
													+ '"/>&nbsp;&nbsp;'); */
											$toNewPermission
													.append($name.val()
															+ '('
															+ $shortName.val()
															+ ')'
															+ '<input type="checkbox" name="model.permissions['
															+ maxId
															+ '].shortName" value="'
															+ $shortName.val()
															+ '" checked="checked" rel="'
															+ maxId
															+ '"/>&nbsp;&nbsp;');
											$toNewPermission
													.append('<input type="hidden" name="model.permissions['
															+ maxId
															+ '].name" value="'
															+ $name.val()
															+ '" rel="'
															+ maxId
															+ '"/>');
											$toNewPermission
													.append('<input type="hidden" name="model.permissions['
															+ maxId
															+ '].description" value="'
															+ $description
																	.val()
															+ '" rel="'
															+ maxId
															+ '"/>');

											$name.val("");
											$shortName.val("");
											$description.val("");

											event.preventDefault();
											event.stopPropagation();
										});

					/* 	$("#permissionForm").submit(
								function(event) {
									event.preventDefault();
									event.stopPropagation();

									var _nameClass = $name.attr("class");
									var _shortNameClass = $shortName
											.attr("class");
									var _descriptionClass = $description
											.attr("class");

									$name.attr("class", "required");
									$shortName.attr("class", "required");

									//var result = validateCallback(this,	dialogReloadRel2Module);
									var result = validateCallback(this,
											alert("submit"));
									console.log(event);
									result=true;
									if (!result) {
										$name.attr("class", _nameClass);
										$shortName.attr("class",
												_shortNameClass);
										$description.attr("class",
												_descriptionClass);
									}
									return result;
								}); */
					});
//-->
</script>
<%-- <a id="refreshJbsxBox2moduleTree" rel="jbsxBox2moduleTree" target="ajax" href="${ctx}/management/security/module/tree"
	style="display: none;"></a> --%>
<div class="pageContent">
	<div id="permissionFormContent" class="pageFormContent" layoutH="58">
		<form id="permissionForm" method="post" action="${ctx}/security/saveModule" class="required-validate pageForm"
			 onsubmit="return formValidateCallback(this,dialogFormCallBack)">
			<c:if test="${editType==0}"><input type="hidden" name="model.id" value="${model.id }" /></c:if> <%-- <input type="text" name="model.parentNode.id"
				value="${model.parentNode.id }" /> --%>
			<fieldset>
				<legend>模块信息</legend>
				<p>
					<label>名称：</label> <input type="text" name="model.name" class="required" size="32" maxlength="32"
						value="${model.name }" alt="请输入模块名称" />
				</p>
				<p>
					<label>优先级：</label> <input type="text" name="model.priority" class="required" size="2" maxlength="2"
						value="${model.priority }" /> <span class="info">&nbsp;&nbsp;默认:99</span>
				</p>
				<p>
					<label>URL：</label> <input type="text" name="model.url" class="required" size="32" maxlength="255" alt="以/或者http开头"
						value="${model.url }" />
				</p>
				<p>
					<label>授权名称：</label> <input type="text" name="model.sn" class="required" size="32" maxlength="32" alt="授权名称"
						value="${model.sn }" <c:if test="${editType==0}">readOnly=readOnly</c:if> />
				</p>
				<p>
					<label>描述：</label> <input type="text" name="model.description" class="" size="32" maxlength="255" alt="描述"
						value="${model.description }" />
				</p>
				<p>
					<label>父模块：</label> <input name="model.parentNode.id" value="${model.parentNode.id }" type="hidden" /> <input
						class="required" name="model.parentNode.name" type="text" readonly="readonly" value="${model.parentNode.name }" />
					<a class="btnLook" href="${ctx}/security/lookupModule" lookupGroup="model.parentNode" mask="true" title="更改父模块"
						width="400">查找带回</a>
				</p>
			</fieldset>
			<fieldset>
				<legend>自定义授权</legend>
				<div class="toNewPermission">
					<c:forEach var="p" items="${model.permissions }" varStatus="s">
			${p.name}(${p.shortName})<input type="checkbox" name="model.permissions[${s.index}].shortName" value="${p.shortName}"
							checked="checked" rel="${s.index}" />&nbsp;&nbsp;
				     <input type="hidden" name="model.permissions[${s.index}].id" value="${p.id}" rel="${s.index}" />
						<%-- <input type="hidden" name="model.permissions[${s.index}].module.id" value="${p.module.id}" rel="${s.index}" /> --%>
						<input type="hidden" name="model.permissions[${s.index}].name" value="${p.name}" rel="${s.index}" />
						<input type="hidden" name="model.permissions[${s.index}].description" value="${p.description}" rel="${s.index}" />
					</c:forEach>
				</div>
			</fieldset>
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






		<form id="moduleNewPermissionForm" method="post" action="" class="pageForm required-validate" onsubmit=""
			style="vertical-align: bottom;">
			<fieldset id="newPermissonInput">
				<legend>动态新增</legend>

				<p>
					<label>名称：</label> <input type="text" name="_name" class="required" size="32" maxlength="32" alt="请输入名称" />
				</p>
				<p>
					<label>短名：</label> <input type="text" name="_shortName" class="required" size="16" maxlength="16" alt="请输入短名" /> <span
						class="info">&nbsp;&nbsp;用作授权验证</span>
				</p>
				<p>
					<label>描述：</label> <input type="text" name="_description" size="32" class="" maxlength="255" alt="描述" />
				</p>
				<div class="button" id="newPermission">
					<div class="buttonContent">
						<button type="submit">新增</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</div>



