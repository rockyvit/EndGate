package org.pw.foodordering.controllers;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pw.foodordering.elements.UserValidator;
import org.pw.foodordering.elements.db.Activation;
import org.pw.foodordering.elements.db.User;
import org.pw.foodordering.engine.CommonUtils;
import org.pw.foodordering.engine.SendOTPAutoVerification;
import org.pw.foodordering.interfaces.FoodMailServerInterface;
import org.pw.foodordering.interfaces.FoodOrderingInterface;
import org.pw.foodordering.interfaces.UserCrudInterface;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping({"/jsp/address.jsp"})
/*@RequestMapping({"/welcome.do"})*/




@SessionAttributes(types={User.class})
public class AddressController {
	private Log logger = LogFactory.getLog(getClass());
	  private UserCrudInterface foodOrdering;
	  
	  
	 
	  public String processSubmitHandler(@ModelAttribute("user") User user, BindingResult result, SessionStatus status) 
	  {
	    new UserValidator((FoodOrderingInterface)this.foodOrdering).validate(user, result);
	    System.out.println("kahan be");
	  if (result.hasErrors()) {
	    	 System.out.println("kahan be");
	         //return "secure/register";
	    	//return "../register.do";
	    	 
	  }
	    			user.setAddress1(user.getAddress1());
	    			user.setAddress1_city(user.getAddress1_city());
	    			user.setAddress1_state(user.getAddress1_state());
	    			user.setAddress1_zip(user.getAddress1_zip());
	    			user.setAddress1_landmark(user.getAddress1_landmark());
	    			System.out.println("address1 is ---:" + user.getAddress1());
	    			/*System.out.println("User isss :" + user.getFirst_name());
				    System.out.println("User is :" + user);*/
				   
	   
	    
	    System.out.println("Every ting is fine here and we are returning back");
	    	//return "welcome";
	    return "redirect:/welcome.do";
	  }
	 
}
