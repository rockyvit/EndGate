package org.pw.foodordering.controllers;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pw.foodordering.elements.UserValidator;
import org.pw.foodordering.elements.db.User;
import org.pw.foodordering.elements.misc.RestaurantUrlMap;
import org.pw.foodordering.engine.CommonUtils;
import org.pw.foodordering.engine.feachers.DaGrassoKatowiceWWW;
import org.pw.foodordering.engine.feachers.WwwToClassTranslate;
import org.pw.foodordering.interfaces.FetcherInterface;
import org.pw.foodordering.interfaces.FoodOrderingInterface;
import org.pw.foodordering.interfaces.RestaurantCrudInterface;
import org.pw.foodordering.interfaces.UserCrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.pw.foodordering.elements.db.*;

@Controller
public class AdminMainController
{
  private Log logger = LogFactory.getLog(getClass());
  private FoodOrderingInterface foodOrdering;
  
  @Autowired
  public AdminMainController(FoodOrderingInterface foodorder)
  {
    this.foodOrdering = foodorder;
  }
  
  @RequestMapping({"/err.do"})
  public ModelAndView errorMainHandler(HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mav = new ModelAndView("/err");
    return mav;
  }
  
  @RequestMapping({"/admin/main.do"})
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
    ModelAndView mav = new ModelAndView("admin/main");
    mav.addObject("mname", name);
    return mav;
  }
  
  @RequestMapping(value={"/admin/fetcher.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView fetchManagerGet(HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mav = new ModelAndView("/admin/fetcher");
    mav.addObject("restMap", new RestaurantUrlMap());
    return mav;
  }
  
  @RequestMapping(value={"/admin/fetcher.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView fetchManagerPost(@ModelAttribute RestaurantUrlMap rmap, BindingResult result, SessionStatus status)
  {
    ModelAndView mav = new ModelAndView("redirect:/restaurants.do");
    if (rmap == null)
    {
      mav.setViewName("/admin/fetcher");
      return mav;
    }
    FetcherInterface fetcher = null;
    if (rmap.getSelected().equals("uciecha"))
    {
      fetcher = new WwwToClassTranslate();
    }
    else if (rmap.getSelected().equals("dagrasso"))
    {
      fetcher = new DaGrassoKatowiceWWW();
    }
    else
    {
      mav.setViewName("/admin/fetcher");
      return mav;
    }
    fetcher.parse();
    HashMap<String, HashMap<String, HashMap<String, Object>>> menu = fetcher.getMenu();
    this.logger.debug("Downloadind menu...");
    ((RestaurantCrudInterface)this.foodOrdering).importRestaurantMenu(rmap.getRestaurantName(), menu);
    this.logger.debug("Downloadind menu...DONE");
    
    return mav;
  }
  
  @RequestMapping(value={"/admin/userManager.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.PUT})
  public ModelAndView userManagerHandlerGet(@RequestParam(value="user", required=false) User user, @RequestParam(value="action", required=false) Object action, HttpServletRequest request, HttpServletResponse response)
  {
	  //User user =(user) object;
    ModelAndView mav = null;
    if (user.getLogin_id() == null)
    {
      mav = new ModelAndView("admin/listUsers");
      mav.addObject("user", ((UserCrudInterface)this.foodOrdering).getAllUsers());
    }
    else
    {
      String sAction = (String)action;
      //String sUsername = (String)login_id;
      //User user = ((UserCrudInterface)this.foodOrdering).getUserByMobileno(sUsername);
     User adminuser = ((UserCrudInterface)this.foodOrdering).getUserByEmail(user.getLogin_id());
      if (adminuser == null)
      {
        mav = new ModelAndView("redirect:/admin/userManager.do");
      }
      else if (action == null)
      {
        mav = new ModelAndView("admin/userDetails");
        mav.addObject("user", user);
      }
      else if (sAction.equals("remove"))
      {
        ((UserCrudInterface)this.foodOrdering).deleteUser(user);
        mav = new ModelAndView("redirect:/admin/userManager.do");
      }
    }
    return mav;
  }
  
  @RequestMapping(value={"/admin/userManager.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String userManagerHandlerPost(@ModelAttribute User user, BindingResult result, SessionStatus status)
  {
    new UserValidator(this.foodOrdering, Boolean.valueOf(true)).validate(user, result);
    if (result.hasErrors())
    {
      this.logger.debug("PIO: errors");
      return "secure/register.do";
    }
    this.logger.debug("PIO: getting " + user.getLogin_id());
    User origUser = ((UserCrudInterface)this.foodOrdering).getUserByEmail(user.getLogin_id());
    //User origUser = ((UserCrudInterface)this.foodOrdering).getUserByMobileno(user.getMobileno());
    if ((origUser == null))
    {
      result.rejectValue("username", "error.user-no-longer-exists", new Object[] { user.getLogin_id() }, "User {0} no longer exists.");
      
      this.logger.debug("PIO: orig user = null");
      return "secure/register.do";
    }
    this.logger.debug("orignuser.name " + origUser.getLogin_id());
    if (user.getPassword().length() != 0) {
      origUser.setPassword(CommonUtils.encodeAsMd5(user.getPassword()));
    }
    origUser.setLogin_id(user.getLogin_id());
    
    ((UserCrudInterface)this.foodOrdering).updateUser(origUser);
    System.out.println("where to redirect paages");
    return "redirect:/admin/userManager.do";
  }
}
