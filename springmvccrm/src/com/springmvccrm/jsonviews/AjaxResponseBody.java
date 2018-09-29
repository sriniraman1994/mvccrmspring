package com.springmvccrm.jsonviews;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.springmvccrm.entity.CustomerModel;

public class AjaxResponseBody {
	public AjaxResponseBody() {
		super();
		// TODO Auto-generated constructor stub
	}
	@JsonView(JViews.Data.class)
	private String msg;
	@JsonView(JViews.Data.class)
	private String statusCode;
	@JsonView(JViews.Data.class)
	private List<CustomerModel> customerList;
	@JsonView(JViews.Data.class)
	private boolean emailStatus;
	@JsonView(JViews.Data.class)
	private boolean isDuplicate;
	@JsonView(JViews.Data.class)
	private String email;
	public boolean isDuplicate() {
		return isDuplicate;
	}
	public void setDuplicate(boolean isDuplicate) {
		this.isDuplicate = isDuplicate;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public List<CustomerModel> getCustomerList() {
		return customerList;
	}
	public void setCustomerList(List<CustomerModel> customerList) {
		this.customerList = customerList;
	}
	public boolean isEmailStatus() {
		return emailStatus;
	}
	public void setEmailStatus(boolean emailStatus) {
		this.emailStatus = emailStatus;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
