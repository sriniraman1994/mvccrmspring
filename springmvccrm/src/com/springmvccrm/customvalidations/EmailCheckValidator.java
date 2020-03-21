package com.springmvccrm.customvalidations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.springmvccrm.entity.Mode;
import com.springmvccrm.service.CustomerService;

public class EmailCheckValidator implements ConstraintValidator<EmailCheck,String> {

	private String emailValue;
	@Autowired
	private CustomerService customerService;
	
	@Override
	public void initialize(EmailCheck emailCheck) {
		// TODO Auto-generated method stub
		String val = emailCheck.value();
		
	}

	@Override
	public boolean isValid(String userEmail, ConstraintValidatorContext arg1) {
		// TODO Auto-generated method stub
		if(Mode.MODE.equals("none")||Mode.MODE.equals("update")||Mode.MODE.equals("delete")){
			return true;
		}
		if(userEmail!=null){
			return (!customerService.checkEmail(userEmail));
		}
		return true;
	}

}
