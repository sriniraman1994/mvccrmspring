package com.springmvccrm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springmvccrm.entity.CustomerModel;
import com.springmvccrm.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@RequestMapping("/customerlist")
	public String customerList (Model model){
		List<CustomerModel> customers = customerService.getCustomersList();
		model.addAttribute("customers",customers);
		return "customer_list_boot";
	}
	@RequestMapping("/editCustomer")
	public String editCustomer(@RequestParam("customerId") int customerId,Model model){
		CustomerModel customerModel = customerService.getCustomer(customerId);
		model.addAttribute("saveCustomer", customerModel);
		model.addAttribute("action","updateCustomer");
		model.addAttribute("button","update");
		model.addAttribute("methodName","update Customer");
		return "customer_crud";
	}
	@RequestMapping("/addcustomer")
	public String addCustomer(Model model){
		CustomerModel customerModel = new CustomerModel();
		model.addAttribute("saveCustomer", customerModel);
		model.addAttribute("action","insertCustomer");
		model.addAttribute("button","add");
		model.addAttribute("methodName","Add Customer");
		return "customer_crud";
	}
	@RequestMapping("/insertCustomer")
	public String insertCustomer(@Valid @ModelAttribute ("saveCustomer") CustomerModel customer,BindingResult result,Model model){

		if(result.hasErrors()){
			model.addAttribute("css","danger");
			model.addAttribute("msg","form has following errors");
		}else{
			String finalResult = customerService.insertCustomer(customer);
			if(finalResult.equals("success")){
				model.addAttribute("css","success");
				model.addAttribute("msg","customer added succesfully");
			}
		}
		model.addAttribute("button","add");
		model.addAttribute("action","insertCustomer");
		return "customer_crud";
	}
	@RequestMapping("/updateCustomer")
	public String updateCustomer(@Valid @ModelAttribute ("saveCustomer")CustomerModel customer,BindingResult result,Model model){
		if(result.hasErrors()){
			model.addAttribute("css","danger");
			model.addAttribute("msg","form has following errors");

		}else{
			String finalResult = customerService.insertCustomer(customer);
			if(finalResult.equals("success")){
				model.addAttribute("css","success");
				model.addAttribute("msg","customer updated succesfully");
			}
		}
		model.addAttribute("button","update");
		model.addAttribute("action","updateCustomer");
		return "customer_crud";
	}
	@RequestMapping("/removeCustomer")
	public String removeCustomer( @ModelAttribute ("saveCustomer")CustomerModel customer,Model model){
		String finalResult = customerService.deleteCustomer(customer);
		if(finalResult.equals("success")){
			model.addAttribute("msg","deletionSuccess");
		}
		model.addAttribute("saveCustomer", customer);
		model.addAttribute("action","removeCustomer");
		model.addAttribute("button","delete");
		model.addAttribute("methodName","Delete Customer");
		return "customer_crud";
	}
	@RequestMapping("/deleteCustomer")
	public String deleteCustomer(Model model,@RequestParam("customerId") int customerId){
		CustomerModel customerModel = customerService.getCustomer(customerId);
		model.addAttribute("saveCustomer", customerModel);
		model.addAttribute("action","removeCustomer");
		model.addAttribute("button","delete");
		model.addAttribute("methodName","Delete Customer");
		return "customer_crud";
	}
	@InitBinder
	public void initBInder(WebDataBinder binder){
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
}

