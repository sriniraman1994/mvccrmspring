package com.springmvccrm.service;

import java.util.List;

import com.springmvccrm.entity.CustomerModel;

public interface CustomerService {
	public List<CustomerModel> getCustomersList();
	public String insertCustomer(CustomerModel customer);
	public CustomerModel getCustomer(int customerId);
	public String deleteCustomer(CustomerModel customer);
	public boolean checkEmail(String email);

}
