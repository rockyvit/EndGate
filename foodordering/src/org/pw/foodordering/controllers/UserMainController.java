package org.pw.foodordering.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pw.foodordering.interfaces.FoodOrderingInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserMainController
{
  private Log logger = LogFactory.getLog(getClass());
  private FoodOrderingInterface foodOrdering;
  
  @Autowired
  public UserMainController(FoodOrderingInterface foodorder)
  {
    this.foodOrdering = foodorder;
  }
  
  @RequestMapping({"/user/main.do"})
  public ModelAndView adminMainHandler(HttpServletRequest request, HttpServletResponse response)
  {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String name = "NONE";
    if (auth != null) {
      if ((auth.getPrincipal() instanceof UserDetails)) {
        name = ((UserDetails)auth.getPrincipal()).getUsername();
      } else {
        name = auth.getPrincipal().toString();
      }
    }
    ModelAndView mav = new ModelAndView("user/main");
    mav.addObject("mname", name);
    return mav;
  }
}
