package com.redhat.c0dered.math;

import java.io.Serializable;


public class MathResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String error;
	private String result;
	
	public MathResult() {
		this.error = "No Input Error";
		this.result = "0";
	}
	
	public MathResult(String error, String result) {
		this.error = error;
		this.result = result;
	}

	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
