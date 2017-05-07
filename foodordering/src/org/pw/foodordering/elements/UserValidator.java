package org.pw.foodordering.elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pw.foodordering.elements.db.User;
import org.pw.foodordering.interfaces.FoodOrderingInterface;
import org.pw.foodordering.interfaces.UserCrudInterface;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator
  implements Validator
{
  protected final Log logger = LogFactory.getLog(getClass());
  UserCrudInterface foodOrdering;
  Boolean noPassMode = Boolean.valueOf(false);
  
  public UserValidator(FoodOrderingInterface foodOrdering)
  {
    this.foodOrdering = ((UserCrudInterface)foodOrdering);
  }
  
  public UserValidator(FoodOrderingInterface foodOrdering, Boolean noPassMode)
  {
    this.foodOrdering = ((UserCrudInterface)foodOrdering);
    this.noPassMode = noPassMode;
  }
  
  public boolean supports(Class paramClass)
  {
    return User.class.equals(paramClass);
  }
  
  public void validate(Object object, Errors errors)
  {
    User user = (User)object;
    User dbUser = this.foodOrdering.getUserByEmail(user.getLogin_id());
    System.out.println("dbUser is ========>:" +dbUser);
    System.out.println("Phone is :==: "+ user.getPhone());
    //if (user == null)
    {
      if (!this.noPassMode.booleanValue()) {
        errors.rejectValue("login_id", "error.not-specified", null, "Value req");
      }
   // }
    else
    {
      if (!this.noPassMode.booleanValue())
      {
    	if(dbUser==null){
    		
    		errors.rejectValue("login_id", "error.not-specified", null, "Value req");
    	}
    	  
          if (dbUser != null) {
            errors.rejectValue("login_id", "error.user-exists", new Object[] { dbUser.getFirst_name() }, "User {0} allready exists");
          }
    	  Pattern patt = Pattern.compile("(^[789][0-9]{9}$)");
 		 Pattern email_patt =Pattern.compile(
 				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
 				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
 	     
 		// Matcher mob_matcher = patt.matcher(user.getPhone();
 		 
 		patt.equals(user.getPhone());
 	      Matcher email_matcher = email_patt.matcher(user.getLogin_id());
 	     

 	      if ((patt.equals(user.getPhone())) && email_matcher.matches()) {
 	   	   logger.info("Phone Number And Email Valid");
 	   
    }else{
    	System.out.println("Not Matches");
    }
      }
    }}
 }
  
  
  
}