
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/scripts/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctx}/scripts/zTree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx}/scripts/zTree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ctx}/scripts/zTree/js/jquery.ztree.exedit-3.5.js"></script>
<style>
<!--
.setAll {
	vertical-align: top;
	margin-right: 3px;
}

span.inputValueRole input {
	vertical-align: middle;
	margin-right: 15px;
}
-->
</style>

<script type="text/javascript">
<!--
// top

var IDMark_Switch = "_switch",
IDMark_Icon = "_ico",
IDMark_Span = "_span",
IDMark_Input = "_input",
IDMark_Check = "_check",
IDMark_Edit = "_edit",
IDMark_Remove = "_remove",
IDMark_Ul = "_ul",
IDMark_A = "_a";

var setting = {
	data: {
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "pId",
			rootPId: ""
		}
	},		
	check: {
		enable: true,
		autoCheckTrigger: true
	},
	view: {
		addDiyDom: addDiyDom,
		showIcon: false
	},
	callback: {
		onCheck: selectPermission
	}
};


 var zNodes =[${modulePermissionTree}]; 

	
	function addDiyDom(treeId, treeNode) {
		var cObj = $("#" + treeNode.tId + IDMark_Check);
		var cEditStr = '<input type="checkbox" class="setAll"/>';
		cObj.after(cEditStr);

		var aObj = $("#" + treeNode.tId + IDMark_A);
		var aEditStr = treeNode.expland;
		aObj.after(aEditStr);
	}

	function selectPermission(event, treeId, treeNode) {
		var cObj = $("#" + treeNode.tId + IDMark_Check);
		var isChecked = treeNode.checked;

		var $setAll = cObj.next(".setAll");
		var $inputSpan = $(cObj).nextAll(".inputValueRole");

		if (isChecked == true) {
			var manualLength = $("input:checked", $inputSpan).length;
			if (manualLength == 0) {// 判断是否手动选取，true不是
				$setAll.attr("checked", "checked");
				$("input[type=checkbox]", $inputSpan).each(function() {
					$(this).attr("checked", "checked");
				});
			}
		} else {
			if ($setAll.is(":checked") == true) {// 判断是否手动选取，true不是
				$setAll.removeAttr("checked");
				$("input[type=checkbox]", $inputSpan).each(function() {
					$(this).removeAttr("checked");
				});
			}
		}
	}

	$(document)
			.ready(
					function() {
						var t = $("#uPermissonTree");
						t = $.fn.zTree.init(t, setting, zNodes);
						t.expandAll(true);

						// 给全选加入事件
						$(".setAll").click(
								function() {
									var isChecked = $(this).is(":checked");
									var $inputSpan = $(this).nextAll(
											".inputValueRole");
									$("input[type=checkbox]", $inputSpan).each(
											function() {
												if (isChecked == true) {
													$(this).attr("checked",
															"checked");
												} else {
													$(this).removeAttr(
															"checked");
												}
											});
								});

						$(".inputValueRole input[type=checkbox]").click(
								function() {
									var isChecked = $(this).is(":checked");
									if (isChecked == false) {
										$(this).parent().prevAll(".setAll")
												.removeAttr("checked");
									}
								});

						// 初始化全选状态
						$(".setAll")
								.each(
										function() {
											var $inputSpan = $(this).nextAll(
													".inputValueRole");
											var allCheckBoxLength = $(
													"input[type=checkbox]",
													$inputSpan).length;
											var checkedLength = $(
													"input:checked", $inputSpan).length;
											if (allCheckBoxLength == checkedLength) {
												$(this).attr("checked",
														"checked");
											}
										});
					});
//-->
</script>
<div class="pageContent">
	<form method="post" action="${ctx}/security/saveRole?gridId=${gridId}" class="pageForm required-validate" onsubmit="return formValidateCallback(this,dialogFormCallBack)">
		<c:if test="${editType==0}"><input type="hidden" name="model.id" value="${model.id}"></c:if>
		<div class="pageFormContent" layoutH="58">
			<dl>
				<dt>角色名称：</dt>
				<dd>
					<input type="text" name="model.name" class="required" size="30" maxlength="32"
						alt="请输入角色名称" value="${model.name }" />
				</dd>
			</dl>
			<dl>
				<dt>描述：</dt>
				<dd>
					<input type="text" name="model.description" class="" size="30" maxlength="255" alt="请输入描述"
						value="${model.description }" />
				</dd>
			</dl>
			<div class="divider"></div>
			<ul id="uPermissonTree" class="ztree"></ul>
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