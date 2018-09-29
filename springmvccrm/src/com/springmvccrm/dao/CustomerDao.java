package com.springmvccrm.dao;

import java.util.List;

import com.springmvccrm.entity.CustomerModel;

public interface CustomerDao {
	
	public List<CustomerModel> getCustomersList();

	public String insertCustomer(CustomerModel customer);

	public CustomerModel getCustomer(int customerId);

	public String deleteCustomer(CustomerModel customer);

	public Long checkEmail(String email);

}
