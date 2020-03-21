package com.springmvccrm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvccrm.entity.CustomerModel;
import com.springmvccrm.service.CustomerService;

@Controller
@RequestMapping("/customercsv")
public class CustomerCsvDownloadController {
	@Autowired
	private CustomerService customerService;
	@RequestMapping("/downloadCsv")
	public void customerList (HttpServletResponse response) throws IOException{
		response.setContentType("text/csv");
		String fileName = "customer_list.csv";
		response.setHeader("Content-disposition", "attachment;filename="+fileName);
		ArrayList<String> sb = new ArrayList<String>();
		
		sb.add("FirstName,LastName,Email,Address");
		sb.add("\n");
		List<CustomerModel> customers = customerService.getCustomersList();
		for (CustomerModel customer:customers){
			sb.add(String.valueOf(customer.getFirstName()));
			sb.add(",");
			sb.add(String.valueOf(customer.getLastName()));
			sb.add(",");
			sb.add(String.valueOf(customer.getEmail()));
			sb.add(",");
			if(String.valueOf(customer.getAddress()).contains("\n")){
				sb.add("\"");
				sb.add(customer.getAddress());
				sb.add("\"");
			}else{
				sb.add(String.valueOf(customer.getAddress()));
			}
			sb.add("\n");
		}
		Iterator<String> iter = sb.iterator();
		while(iter.hasNext()){
			String output = iter.next();
			response.getOutputStream().print(output);
		}
		response.getOutputStream().flush();
	}
	
}

