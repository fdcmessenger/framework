var selectOnlyOneMsgTip = "请选择一条记录。";
var atLeastSelectOnlyOneMsgTip = "请选择一条记录。";

/**
 *将 少量单一的参数拼装到url串中,注意如果参数过多应避免如此使用.
 */
function updateURLParameter(url, param, paramVal) {
	var TheAnchor = null;
	var newAdditionalURL = "";
	var tempArray = url.split("?");
	var baseURL = tempArray[0];
	var additionalURL = tempArray[1];
	var temp = "";

	if (additionalURL) {
		var tmpAnchor = additionalURL.split("#");
		var TheParams = tmpAnchor[0];
		TheAnchor = tmpAnchor[1];
		if (TheAnchor)
			additionalURL = TheParams;

		tempArray = additionalURL.split("&");

		for ( i = 0; i < tempArray.length; i++) {
			if (tempArray[i].split('=')[0] != param) {
				newAdditionalURL += temp + tempArray[i];
				temp = "&";
			}
		}
	} else {
		var tmpAnchor = baseURL.split("#");
		var TheParams = tmpAnchor[0];
		TheAnchor = tmpAnchor[1];

		if (TheParams)
			baseURL = TheParams;
	}

	if (TheAnchor)
		paramVal += "#" + TheAnchor;

	var rows_txt = temp + "" + param + "=" + paramVal;
	return baseURL + "?" + newAdditionalURL + rows_txt;
}

/**
 *	jqgrid对象进行resize时,尺寸的计算方法
 */
function resizeJqGrid(tgrid) {
	console.log("当前resize的是：")
	console.log(tgrid);
	
	
	container = tgrid.parents('.pageContent:first');
	var cw = container.width();
	var ch = container.height();
	var ph = container.find(".ui-jqgrid-pager").height();
	var th = container.find(".ui-jqgrid-titlebar").height();
	var hh = container.find(".ui-jqgrid-hdiv").height();
	if (th > 5)
		th = th + 8;
	//console.log(ch - ph - th - hh - 30);
	// tgrid.jqGrid('setGridWidth', tgrid.getGridParam("width"), true);
	tgrid.jqGrid('setGridWidth', cw - 2, true);
	tgrid.jqGrid('setGridHeight', ch - ph - th - hh - 30, 'true');
}

/**
 * 定义当前页面中搜索grid对象并进行resize的方法变量
 */
var gridResize = function() {
	jQuery('.unitBox:visible').each(function() {

		jQuery(this).find('.ui-jqgrid-btable:visible').each(function() {
			var tableId = jQuery(this).attr("id");
			tgrid = $("#" + tableId);
			resizeJqGrid(tgrid);
		});

	});
};

/**
 *系统启动来如此脚本文件时,绑定系统resize事件,DWZ定义的resize事件
 */
jQuery(window).unbind(DWZ.eventType.resizeGrid).bind(DWZ.eventType.resizeGrid, gridResize);
jQuery(window).resize(function() {
	jQuery(window).trigger(DWZ.eventType.resizeGrid);
});

/**
 *打开新建/添加实体的表单对话框
 */
function addFormDialog(url, title, width, height, gridId) {
	url = updateURLParameter(url, 'gridId', gridId);
	url = encodeURI(url);
	$.pdialog.open(url, gridId+title, title, {
		mask : true,
		width : width,
		height : height
	});
}

/**
 *打开编辑实体的表单对话框
 */
function editFormDialog(url, title, width, height, gridId) {
	if (onlyOneSelectCheck(gridId)) {

		var sid = jQuery("#" + gridId).jqGrid('getGridParam', 'selrow');

		url = updateURLParameter(url, 'id', sid);
		url = updateURLParameter(url, 'gridId', gridId);
		url = encodeURI(url);
		$.pdialog.open(url, gridId+title, title, {
			mask : true,
			width : width,
			height : height
		});
	} else {
		return;
	}
}

/**
 * grid上的单选检查
 */
function onlyOneSelectCheck(gridId) {
	var sids = jQuery("#" + gridId).jqGrid('getGridParam', 'selarrrow');
	if (!sids || sids.length === 0 || sids.length > 1) {
		alertMsg.warn(selectOnlyOneMsgTip);
		return false;
	} else {
		return true;
	}
}

/**
 * grid上的多选检查
 */
function atLeastOneSelectCheck(gridId) {
	var sids = jQuery("#" + gridId).jqGrid('getGridParam', 'selarrrow');
	if (!sids || sids.length === 0) {
		alertMsg.warn(atLeastSelectOnlyOneMsgTip);
		return false;
	} else {
		return true;
	}
}

/**
 * 表单提交的校验回调方法 ,可以替代DWZ自己定义的validateCallback回调方法
 *
 */
function formValidateCallback(form, callback, confirmMsg) {
	var $form = $(form);
	if (!$form.valid()) {
		return false;
	}

	var _submitFn = function() {
		$.ajax({
			type : form.method || 'POST',
			url : $form.attr("action"),
			data : $form.serializeArray(),
			dataType : "json",
			cache : false,
			success : function(data, textStatus, jqXHR) {
				callback(data, form, textStatus, jqXHR) || DWZ.ajaxDone(data);
			},
			error : DWZ.ajaxError
		});
	}
	if (confirmMsg) {
		alertMsg.confirm(confirmMsg, {
			okCall : _submitFn
		});
	} else {
		_submitFn();
	}

	return false;
}

/**
 * 这是个callback回调,使用方法如下:
 * <form  id="demoForm"  method="post" action="saveEntity"	class="pageForm required-validate"	onsubmit="return formValidateCallback(this,dialogFormCallBack)">
 * 主要是针对struts2中ognl封装bean时报出的 fieldErrors反映到编辑表单页面上
 *
 */
function dialogFormCallBack(data, form) {
	 //console.log(data);
	if (data.fieldErrors != null && data.fieldErrors.length !== 0) {

		$.each(data.fieldErrors, function(e) {
			// $("[name$='"+e+"']").validationEngine('showPrompt',
			// data.fieldErrors[e], 'error','centerRight', true);
			// 能自动聚焦到就好了
			$("[name$='" + e + "']").focus()
			return false;
		});
		$(form).validate().showErrors(data.fieldErrors);

		//console.log(data.fieldErrors);
		return false;

	}
	
	//var dataajx = data.ajaxReturn;
	
	if (data.ajaxReturn.gridId) {
		jQuery("#" + data.ajaxReturn.gridId).jqGrid('setGridParam', {
			page : 1
		}).trigger("reloadGrid");
	}

	dialogAjaxDone(data.ajaxReturn);
}

/**
 * grid上选择多条记录,并进行相应的功能处理(由url所对应的action进行处理)
 */
function processMultiSelectedRecords(jqGridId, url, msg) {

	if (atLeastOneSelectCheck(jqGridId)) {
		alertMsg.confirm(msg, {
			okCall : function() {
				var sid = jQuery("#" + jqGridId).jqGrid('getGridParam', 'selarrrow');
				var eurl = url + "?ids=" + sid;
				eurl = encodeURI(eurl);
				jQuery.post(eurl, {}, function(json) {
					DWZ.ajaxDone(json);
					jQuery("#" + jqGridId).jqGrid('setGridParam', {
						page : 1
					}).trigger("reloadGrid");

				}, "json");
			}
		})

	}
}

/**
 * 处理简单请求
 */
function processSimpleRequest(url) {
	//console.log("processSimpleRequest");
	jQuery.post(url, {}, function(json) {
		DWZ.ajaxDone(json);
	});
}

/**
 * 在搜索条件区输入搜索条件,然后将条件加入到grid中的搜索条件中,进行查询刷新
 */
function propertyFilterSearch(searchAreaId, gridId) {
	try {
		var sdata = $('#' + searchAreaId).serializeObject();

		//console.log(sdata);

		var postData = $("#" + gridId).jqGrid("getGridParam", "postData");
		$.extend(postData, sdata);
		//console.log(postData);
		$("#" + gridId).setGridParam({
			postData : postData
		});
		$("#" + gridId).jqGrid("setGridParam", {
			search : true
		}).trigger("reloadGrid", [{
			page : 1
		}]);
	} catch (e) {
		alert(e.message);
	}

}

/**
 * 清除指定区域内的编辑内容
 */
function clear_input_elements(containId) {
	$("#" + containId).find(':input').each(function() {
		switch (this.type) {
			case 'password':
			case 'select-multiple':
			case 'select-one':
			case 'text':
			case 'textarea':
				$(this).val('');
				break;
			case 'checkbox':
			case 'radio':
				this.checked = false;
		}
	});
}

/**
 *	搜索区域展开与隐藏
 */
function searchAreaToggle(button, sAreaId, pcContentId, deffH) {
	$this = $(button);
	$search = $("#" + sAreaId);
	$content = $("#" + pcContentId);
	//console.log($this);
	if ($this.find("span:first").text() == '展开') {
		$this.find("span:first").text('收起')
		$search.toggle();
		$content.attr("layoutH", parseInt($content.attr("layoutH")) + deffH);
		$content.layoutH();
	} else {
		$this.find("span:first").text('展开')
		$search.toggle();
		$content.attr("layoutH", parseInt($content.attr("layoutH")) - deffH);
		$content.layoutH();
	}
	//console.log($this);
	jQuery(window).trigger(DWZ.eventType.resizeGrid);
	return false;
}

// isXml:1为xslx扩展名的文件类型,其他为xsl扩展名的类型
function exportEntity(url, gridId, isXml) {
	var total = $("#" + gridId).getGridParam("records");
	var msg = "共有:" + total + "条数据,你确定要导出吗?";
	var excelType = null;
	if (isXml == 1)
		excelType = "xlsx";
	else
		excelType = "xls";

	alertMsg.confirm(msg, {
		okCall : function() {
			jQuery("#" + gridId).jqGrid('excelExport', {
				"url" : url,
				"tag" : excelType
			});

		}
	});
}

// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
 * function ajaxCallBack(json) { DWZ.ajaxDone(json); if (json.ajaxStatus ==
 * "error") { alertMsg.error(json.ajaxMessage); } else { try { navTab.reload(); }
 * catch (e) { alert(e.message); } alertMsg.correct(json.ajaxMessage) } }
 *
 * function popAjaxCallBack(json) { DWZ.ajaxDone(json); if (json.ajaxStatus ==
 * "error") { alertMsg.error(json.ajaxMessage); } else { try { navTab.reload();
 * $.pdialog.closeCurrent(); } catch (e) { alert(e.message); }
 * alertMsg.correct(json.ajaxMessage) } }
 *
 * function processGridSelectedRecords(jqGridId, url, msg) { var sid =
 * jQuery("#" + jqGridId).jqGrid('getGridParam', 'selarrrow'); var eurl = url +
 * "?ids=" + sid; eurl = encodeURI(eurl); if (sid == null || sid.length == 0) {
 * alertMsg.warn("璇疯嚦灏戦�鎷╀竴鏉¤褰�"); return; } else { alertMsg.confirm(msg, {
 * okCall : function() { jQuery.post(eurl, {}, function(json) {
 * DWZ.ajaxDone(json); if (json.ajaxStatus == "error") {
 * alertMsg.error(json.ajaxMessage); } else { try { navTab.reload(); } catch (e) {
 * alert(e.message); } alertMsg.correct(json.ajaxMessage) } }, "json"); } }); } }
 */

$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	//console.log(a);
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
}