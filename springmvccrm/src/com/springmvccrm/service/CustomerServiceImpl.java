package com.springmvccrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvccrm.dao.CustomerDao;
import com.springmvccrm.entity.CustomerModel;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
	private CustomerDao customerDao;
	@Override
	@Transactional(readOnly=true)
	public List<CustomerModel> getCustomersList() {
		return customerDao.getCustomersList();
	}
	@Override
	@Transactional(readOnly=true)
	public String insertCustomer(CustomerModel customer) {
		return customerDao.insertCustomer(customer);
	}
	@Override
	@Transactional(readOnly=true)
	public CustomerModel getCustomer(int customerId) {
		return customerDao.getCustomer(customerId);
	}
	@Override
	@Transactional(readOnly=true)
	public String deleteCustomer(CustomerModel customer) {
		return customerDao.deleteCustomer(customer);
	}
	@Override
	@Transactional(readOnly=true)
	public boolean checkEmail(String email) {
		Long count = customerDao.checkEmail(email);
		if(count.intValue()>0){
			return true;
		}
		return false ;
	}

}
