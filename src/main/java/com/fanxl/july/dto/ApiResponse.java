package com.fanxl.july.dto;

public class ApiResponse<T> {

	public static final int SUCCESS_CODE = 0;

	private int resultNo;

	private String message;

	private int total;

	private T result;
	
	public int getResultNo() {
		return resultNo;
	}

	public void setResultNo(int resultNo) {
		this.resultNo = resultNo;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
	
	public boolean isSuccess() {
		if (this.resultNo == SUCCESS_CODE) {
			return true;
		} else {
			return false;
		}
	}
}