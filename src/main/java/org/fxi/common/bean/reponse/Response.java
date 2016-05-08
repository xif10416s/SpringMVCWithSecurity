package org.fxi.common.bean.reponse;

public class Response {
	
	public final static int SUCCESS = 0b0;
	public final static int UNKNOW_ERROR = 0b1;
	public final static int DATA_NOT_FOUND = 0b10;
	public final static int ILLEGAL_PARAM = 0b100;
	public final static int AUTH_FAILED = 0b1000;
	public final static int INVALID_STATE = 0b10000;

	private int code = 0;
	
	private String msg;
	
	public Response() {}
	public Response(int respCode) {
		this.code = respCode;
	}
	public Response(int respCode, String msg) {
		this.code = respCode;
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
