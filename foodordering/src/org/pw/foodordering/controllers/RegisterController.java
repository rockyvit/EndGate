package org.pw.foodordering.controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.mail.event.StoreEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pw.foodordering.elements.UserValidator;
import org.pw.foodordering.elements.db.Activation;
import org.pw.foodordering.elements.db.Authority;
import org.pw.foodordering.elements.db.User;
import org.pw.foodordering.engine.CommonUtils;
import org.pw.foodordering.engine.SendOTPAutoVerification;
import org.pw.foodordering.interfaces.ActivationCrudInterface;
import org.pw.foodordering.interfaces.AuthorityCrudInterface;
import org.pw.foodordering.interfaces.FoodMailServerInterface;
import org.pw.foodordering.interfaces.FoodOrderingInterface;
import org.pw.foodordering.interfaces.UserCrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/secure/register.do"})
/*@RequestMapping({"/welcome.do"})*/




@SessionAttributes(types={User.class})
public class RegisterController
{
	
  private Log logger = LogFactory.getLog(getClass());
  private UserCrudInterface foodOrdering;
  private FoodMailServerInterface foodMailService;
  
  @Autowired
  public RegisterController(FoodOrderingInterface foodOrdering, FoodMailServerInterface foodMailService)
  {
	  System.out.println("Hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
	  
    this.foodOrdering = ((UserCrudInterface)foodOrdering);
    this.foodMailService = foodMailService;
}
  
  @InitBinder
  public void setAllowedFields(WebDataBinder dataBinder)
  {
    dataBinder.setDisallowedFields(new String[] { "id" });
  }
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @Transactional
  public ModelAndView setupFormHandler(@RequestParam(value="activation", required=false) Object activation, Model model)
  {
    ModelAndView mav = new ModelAndView();
    if (activation == null)
    {
      mav.setViewName("secure/register");
    	//mav.setViewName("../register.do");
      model.addAttribute("user", new User());
      model.addAttribute("password", new String());
    }
    else
    {
      String activationStr = (String)activation;
      Activation activationObj = ((ActivationCrudInterface)this.foodOrdering).getActivationByKey(activationStr);
      if (activationObj == null)
      {
        mav.setViewName("activationFailed");
      }
      else
      {
        User user = activationObj.getUser();
      //user.setEnabled(Boolean.valueOf(true));
        this.foodOrdering.updateUser(user);
        ((ActivationCrudInterface)this.foodOrdering).removeEmail_secrete(user);
      //  mav.addObject("user", user.getUsername());
        mav.addObject("user", user.getLogin_id());
        mav.setViewName("activationComplete");
      }
    }
    return mav;
  }
  
  
 /* @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @Transactional
  public ModelAndView setupFormHandler(@RequestParam(value="user", required=false) Object activation, Model model)
  {
	 // activationKey ='d';
    ModelAndView mav = new ModelAndView();
    if (activation == null)
    	
    {
    	System.out.println("Activstion key is null");
      mav.setViewName("secure/register");
    	//mav.setViewName("../register.do");
      model.addAttribute("user", new User());
      model.addAttribute("password2", new String());
    }
    //if{
    	System.out.println("Activstion key is -----------");
      String activationStr = (String)activation;
      User activationObj = ((ActivationCrudInterface)this.foodOrdering).getActivationByKey(activationStr);
      if (activationObj == null)
      {
        mav.setViewName("activationFailed");
      }
      else
      {
       // User user = activationObj.getUser_id();
     // user.setEnabled(Boolean.valueOf(true));
        //this.foodOrdering.updateUser(user);
        //((ActivationCrudInterface)this.foodOrdering).removeEmail_secrete();
      //  mav.addObject("user", user.getUsername());
      //  mav.addObject("user", user.getPhone());
        mav.setViewName("activationComplete");
     // }
    }
    return mav;
  }*/
  
  
  private static String getClientIp(HttpServletRequest request) {

	 String created_ip = "";

      if (request != null) {
    	  created_ip = request.getHeader("X-FORWARDED-FOR");
          if (created_ip == null || "".equals(created_ip)) {
        	  created_ip = request.getRemoteAddr();
          }
      }

      return created_ip;
  }
  
  
  
  
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @Transactional
  public String processSubmitHandler(@ModelAttribute("user") User user, BindingResult result, SessionStatus status) 
  {
    new UserValidator((FoodOrderingInterface)this.foodOrdering).validate(user, result);
    System.out.println("kahan be");
  if (result.hasErrors()) {
    	 System.out.println("kahan be");
         //return "secure/register";
    	//return "../register.do";
    	 
    }
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date = new Date();
	System.out.println(dateFormat.format(date));

	Timestamp ts = Timestamp.valueOf(dateFormat.format(date));
	

				user.setCreation_date(ts);
    			user.setFlag(1);
    			user.setEnabled(1);
    			user.setAuthority_type("ROLE_ADMIN");
    			user.setLogin_id(user.getLogin_id());
    			user.setPhone(user.getPhone());
    			System.out.println("Phone is ---:" + user.getPhone());
    			user.setPassword(CommonUtils.encodeAsMd5(user.getPassword()));
    			// user.setPassword2(CommonUtils.encodeAsMd5(user.getPassword2()));
    
    			this.logger.debug("password MD5: " + user.getPassword());
    			System.out.println("User isss :" + user.getFirst_name());
			    user.setModified_ip(user.getModified_ip());
			    System.out.println("User is :" + user);
			    user.setCreated_ip(user.getCreated_ip());
			    System.out.println("ip is :" +user.getCreated_ip());
			    Activation activation = null;
			  	user.setEmail_secret_key(CommonUtils.generateActivationKey(user.getLogin_id()));
			  	//user.setPhone_secret_key(CommonUtils.generateActivationKey(user.getLogin_id()));
			  	user.setCountry_code("91");
			  	SendOTPAutoVerification.generateOTP(user.getCountry_code(),user.getPhone());
			  	//System.out.println("generate key is:====:" SendOTPAutoVerification.generateOTP(user.getCountry_code(),user.getPhone()));
			  	SendOTPAutoVerification.verifyOTP(user.getPhone(),user.getPhone_secret_key());
			  	this.foodOrdering.storeUser(user);
   
    foodMailService.sendActivationMessage(user.getLogin_id(), user.getEmail_secret_key());
   // foodMailService.sendActivationMessage(user.getPhone(), user.getPhone_secret_key());
    //changes done here to escape activation mail
    //return "secure/waitForActivation";
    //return "secure/activationComplete";
    System.out.println("Every ting is fine here and we are returning back");
    	//return "welcome";
    return "redirect:address.jsp";
  }
}

