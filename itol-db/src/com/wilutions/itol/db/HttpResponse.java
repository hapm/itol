/*
    Copyright (c) 2015 Wolfgang Imig
    
    This file is part of the library "JOA Issue Tracker for Microsoft Outlook".

    This file must be used according to the terms of   
      
      MIT License, http://opensource.org/licenses/MIT

 */
package com.wilutions.itol.db;

import java.util.Arrays;

public class HttpResponse {

	private String content;
	private String[] headers;
	private int status;
	private String errorMessage;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content != null ? content.trim() : "";
	}

	public String[] getHeaders() {
		return headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String toString() {
		StringBuilder sbuf = new StringBuilder();
		sbuf.append("[").append("status=").append(status);
		sbuf.append(",headers=" + Arrays.toString(headers));
		sbuf.append(",content=" + content);
		sbuf.append("]");
		return sbuf.toString();
	}
}
