package com.stacksimplify.restservices.exceptions;

import java.util.Date;

public class CustumErrorDetailsException {
  private Date timestamp;
  private String msg;
  private String errorDetails;
  
public Date getTimestamp() {
	return timestamp;
}

public void setTimestamp(Date timestamp) {
	this.timestamp = timestamp;
}

public CustumErrorDetailsException(Date timestamp, String msg, String errorDetails) {
	super();
	this.timestamp = timestamp;
	this.msg = msg;
	this.errorDetails = errorDetails;
}

public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}

public String getErrorDetails() {
	return errorDetails;
}

public void setErrorDetails(String errorDetails) {
	this.errorDetails = errorDetails;
}

public CustumErrorDetailsException() {
	super();
	// TODO Auto-generated constructor stub
}
}
