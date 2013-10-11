<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
<!--
	// top
	jQuery(document)
			.ready(
					function() {
						/* function getCurrentNavtabRel() {
							var $pDiv = $(
									'.tabsPage div[class="page unitBox"][style*="block"]')
									.first();
							var $ub = $("div.unitBox", $pDiv);
							if ($ub.length > 0) {
								return $ub.first();
							}
							return $pDiv;
						}
						; */

						$(".assignRole")
								.click(
										function() {
											var roleId = $(this).attr("id")
													.split("submit_")[1];
											var $roleRow = $("#userRoleRow_"
													+ roleId);
											var priority = $(
													"#priority_" + roleId)
													.val();

											jQuery
													.ajax({
														type : 'POST',
														/* contentType : 'application/x-www-form-urlencoded;charset=UTF-8', */
														url : '${ctx}/security/assignUserRole?userId=${userId}&roleId='
																+ roleId
																+ '&priority='
																+ priority,
														error : function() {
															alertMsg
																	.error('分配角色失败！');
														},
														success : function() {
															// 删除已分配
															var $remove = $roleRow
																	.remove();
															var roleName = $remove
																	.find("td")
																	.eq(0)
																	.text();
															// 添加分配
															$("#hasRoles")
																	.append(
																			"<tr><td>"
																					+ roleName
																					+ "</td><td>"
																					+ priority
																					+ "</td></tr>");
															$(
																	'tr[class="selected"]',
																	getCurrentNavtabRel())
																	.find("td")
																	.eq(6)
																	.find("div")
																	.append(
																			roleName
																					+ "  ");
														}
													});
										});

					});
//-->
</script>
</HEAD>

<BODY>
	<div class="pageContent" layoutH="0">

		<fieldset>
			<legend>用户已分配角色</legend>
			<table class="list" width="100%">
				<thead>
					<tr>
						<th width="40%">角色名称</th>
						<th width="60%">优先级（数值越小，优先级越高）</th>
					</tr>
				</thead>
				<tbody id="hasRoles">
					<c:forEach var="item" items="${userRoles}">
						<tr>
							<td>${item.role.name}</td>
							<td>${item.priority}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</fieldset>
		<fieldset>
			<legend>用户可分配角色</legend>
			<table class="list" width="100%">
				<thead>
					<tr>
						<th width="40%">角色名称</th>
						<th width="60%">优先级（数值越小，优先级越高）</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${roles}">
						<tr id="userRoleRow_${item.id}">
							<td>${item.name}</td>
							<td><select id="priority_${item.id}" name="priority" class="required combox">
									<c:forEach begin="1" end="98" step="1" varStatus="s">
										<option value="${s.index}">${s.index}</option>
									</c:forEach>
									<option value="99" selected>99</option>
							</select>
								<div class="button">
									<div class="buttonContent">
										<button id="submit_${item.id}" class="assignRole">分配</button>
									</div>
								</div></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</fieldset>
	</div>
</BODY>
</HTML>