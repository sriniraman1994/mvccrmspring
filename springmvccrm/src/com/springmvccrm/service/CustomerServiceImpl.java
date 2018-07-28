package com.springmvccrm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvccrm.dao.CustomerDao;
import com.springmvccrm.entity.CustomerModel;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
	private CustomerDao customerDao;
	@Override
	@Transactional
	public List<CustomerModel> getCustomersList() {
		return customerDao.getCustomersList();
	}
	@Override
	@Transactional
	public String insertCustomer(CustomerModel customer) {
		return customerDao.insertCustomer(customer);
	}
	@Override
	@Transactional
	public CustomerModel getCustomer(int customerId) {
		return customerDao.getCustomer(customerId);
	}
	@Override
	@Transactional
	public String deleteCustomer(CustomerModel customer) {
		// TODO Auto-generated method stub
		return customerDao.deleteCustomer(customer);
	}

}
