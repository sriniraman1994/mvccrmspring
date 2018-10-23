package com.springmvccrm.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springmvccrm.entity.CustomerModel;
@Repository
public class CustomerDaoImpl implements CustomerDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<CustomerModel> getCustomersList() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<CustomerModel> customerQuery =session.createQuery("from CustomerModel",CustomerModel.class).setCacheable(true); 
		List<CustomerModel> customerList = customerQuery.getResultList();
		return customerList;
	}
	@Override
	public String insertCustomer(CustomerModel customer) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(customer);
		return "success";
	}
	@Override
	public CustomerModel getCustomer(int customerId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(CustomerModel.class, customerId);
		
	}
	@Override
	public String deleteCustomer(CustomerModel customer) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(customer);
		return "success";
	}
	@Override
	public Long checkEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from CustomerModel customer where customer.email=:email");
		query.setParameter("email", email);
		return (Long)query.uniqueResult();
	}

}
