package com.springmvccrm.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.annotation.JsonView;
import com.springmvccrm.entity.CustomerModel;
import com.springmvccrm.jsonviews.AjaxResponseBody;
import com.springmvccrm.jsonviews.JViews;
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
		if(!model.containsAttribute("saveCustomer")){
			CustomerModel customerModel = customerService.getCustomer(customerId);
			model.addAttribute("saveCustomer", customerModel);
		}
		
		model.addAttribute("action","updateCustomer");
		model.addAttribute("button","update");
		model.addAttribute("methodName","update Customer");
		
		return "customer_crud";
	}
	@RequestMapping("/addcustomer")
	public String addCustomer(Model model){
		CustomerModel customerModel = new CustomerModel();
		if(!model.containsAttribute("saveCustomer")){
			model.addAttribute("saveCustomer", customerModel);
		}
		model.addAttribute("action","insertCustomer");
		model.addAttribute("button","add");
		model.addAttribute("methodName","Add Customer");
		return "customer_crud";
	}
	@RequestMapping("/insertCustomer")
	public String insertCustomer(@Valid @ModelAttribute ("saveCustomer") CustomerModel saveCustomer,BindingResult result,Model model,RedirectAttributes red){
		String url = "redirect:/customer/addcustomer";
		if(result.hasErrors()){
			red.addFlashAttribute("css","danger");
			red.addFlashAttribute("msg","form has following errors");
			red.addFlashAttribute("org.springframework.validation.BindingResult.saveCustomer",result);
			red.addFlashAttribute("saveCustomer",saveCustomer);
			
		}else{
			String finalResult = customerService.insertCustomer(saveCustomer);
			if(finalResult.equals("success")){
				red.addFlashAttribute("css","success");
				red.addFlashAttribute("msg","customer added succesfully");
			}
		}
		red.addFlashAttribute("button","add");
		red.addFlashAttribute("action","insertCustomer");
		return url;
	}
	@RequestMapping("/updateCustomer")
	public String updateCustomer(@Valid @ModelAttribute ("saveCustomer")CustomerModel saveCustomer,BindingResult result,Model model,RedirectAttributes red){
		String url = "redirect:/customer/editCustomer?customerId="+saveCustomer.getId();
//		RedirectView rv = new RedirectView(url,true);
//		rv.setExposeModelAttributes(false);
//		ModelAndView andView = new ModelAndView();
//		andView.setView(rv);
		if(result.hasErrors()){
//			red.addFlashAttribute("css","danger");
//			red.addFlashAttribute("msg","form has following errors");
			red.addFlashAttribute("css","danger");
			red.addFlashAttribute("msg","form has following errors");
			red.addFlashAttribute("org.springframework.validation.BindingResult.saveCustomer",result);
			red.addFlashAttribute("saveCustomer",saveCustomer);
		}else{
			String finalResult = customerService.insertCustomer(saveCustomer);
			if(finalResult.equals("success")){
				red.addFlashAttribute("css","success");
				red.addFlashAttribute("msg","customer updated succesfully");
//				model.addAttribute("css","success");
//				model.addAttribute("msg","customer updated succesfully");
			}
		}
		
		red.addFlashAttribute("button","update");
		red.addFlashAttribute("action","updateCustomer");
		red.addFlashAttribute("saveCustomer", saveCustomer);
	
		return url;
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
	@JsonView(JViews.Data.class)
	@RequestMapping(value="/getAjaxResult" ,method=RequestMethod.POST,headers="Accept=*/*",produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody AjaxResponseBody getAjaxResult(@RequestBody String msg){
		AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();
		List<CustomerModel> customers = customerService.getCustomersList();
		ajaxResponseBody.setCustomerList(customers);
		ajaxResponseBody.setMsg(""); 
		ajaxResponseBody.setStatusCode("200");
		return ajaxResponseBody;
	}
	@JsonView(JViews.Data.class)
	@RequestMapping(value="/checkEmail" ,method=RequestMethod.POST,headers="Accept=*/*",produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody boolean checkEmail(@RequestBody Map<String,String> paramMap){
		AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();
		boolean checkEmail = customerService.checkEmail(paramMap.get("email"));
//		ajaxResponseBody.setCustomerList(customers);
		ajaxResponseBody.setMsg(""); 
		ajaxResponseBody.setStatusCode("200");
		ajaxResponseBody.setDuplicate(checkEmail);
		return checkEmail;
	}
	@InitBinder
	public void initBInder(WebDataBinder binder){
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
}

