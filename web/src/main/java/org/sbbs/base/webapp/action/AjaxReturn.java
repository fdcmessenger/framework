package org.sbbs.base.webapp.action;

public class AjaxReturn {
	private static final String AJAX_STATUS_SUCCESS = "200";
	private static final String AJAX_STATUS_ERROR = "300";
	private static final String AJAX_STATUS_TIMEOUT = "301";
	private static final String AJAX_STATUS_FORBIDDEN = "403";

	protected static final String CALLBACKTYPE_CLOSECURRENT = "closeCurrent";
	protected static final String CALLBACKTYPE_FORWARD = "forward";

	protected static final String MSG_SHOW_SUCCESS_NORMAL = "0";
	protected static final String MSG_SHOW_SUCCESS_STAY = "1";

	private String statusCode = AJAX_STATUS_SUCCESS;
	private String message = "";
	private String navTabId = "";
	private String forwardUrl = "";
	private String callbackType = "";

	private String gridId = "";
	private String showType = MSG_SHOW_SUCCESS_NORMAL;

	public String getMessage() {
		return message;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getShowType() {
		return showType;
	}

	public String getNavTabId() {
		return navTabId;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public String getCallbackType() {
		return callbackType;
	}

	public String getGridId() {
		return gridId;
	}

	public String success(String msg) {
		statusCode = AJAX_STATUS_SUCCESS;
		message = msg;
		return "success";
	}

	
	
	
	public String successWithMsgStay(String msg) {
		statusCode = AJAX_STATUS_SUCCESS;
		message = msg;
		showType = MSG_SHOW_SUCCESS_STAY;
		return "success";
	}

	public String formSuccess(String msg){
		return success(msg);
	}
	public String formSuccessRefreshGrid(String msg,String gridid){
		this.gridId = gridid;
		return success(msg);
	}
	public String formSuccessRefreshGridCloseFormDialog(String msg,String gridid){
		//this.gridId = gridid;
		callbackType = CALLBACKTYPE_CLOSECURRENT;
		return formSuccessRefreshGrid(msg,gridid);
	}
	
	
	
	public String error(String msg) {
		statusCode = AJAX_STATUS_ERROR;
		message = msg;
		return "success";
	}
}
