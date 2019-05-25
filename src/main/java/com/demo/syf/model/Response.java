package com.demo.syf.model;


public class Response {

	private ResponseData data;
	private String success;
	private String status;
	public ResponseData getData() {
		return data;
	}
	public void setData(ResponseData data) {
		this.data = data;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
