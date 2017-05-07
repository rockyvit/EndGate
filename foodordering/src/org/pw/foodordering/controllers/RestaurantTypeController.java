package org.pw.foodordering.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pw.foodordering.elements.RestaurantTypeValidator;
import org.pw.foodordering.elements.db.RestaurantType;
import org.pw.foodordering.interfaces.FoodOrderingInterface;
import org.pw.foodordering.interfaces.RestaurantTypeCrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping({"/admin/crudRestaurantType.do"})
@SessionAttributes(types={RestaurantType.class})
public class RestaurantTypeController
{
  private RestaurantTypeCrudInterface foodOrdering;
  private Log logger = LogFactory.getLog(getClass());
  
  @Autowired
  public RestaurantTypeController(FoodOrderingInterface foodOrdering)
  {
    this.foodOrdering = ((RestaurantTypeCrudInterface)foodOrdering);
  }
  
  @InitBinder
  public void setAllowedFields(WebDataBinder dataBinder)
  {
    dataBinder.setDisallowedFields(new String[] { "id" });
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView setupFormHandler(@RequestParam(value="restaurantTypeName", required=false) Object restaurantTypeName, @RequestParam(value="action", required=false) Object action, HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mav = null;
    String sRestaurantTypeName = (String)restaurantTypeName;
    String sAction = (String)action;
    if ((sRestaurantTypeName == null) || (sAction == null))
    {
      mav = new ModelAndView("admin/crudRestaurantType");
      mav.addObject("restaurantType", new RestaurantType());
    }
    else
    {
      RestaurantType restTypeDb = this.foodOrdering.getRestaurantTypeByName(sRestaurantTypeName);
      if ((restTypeDb == null) || (!sAction.equals("delete")))
      {
        mav.addObject("restaurantType", new RestaurantType());
        mav = new ModelAndView("admin/crudRestaurantType");
        return mav;
      }
      try
      {
        this.foodOrdering.removeRestaurantType(restTypeDb);
      }
      catch (Exception e)
      {
        this.logger.debug(e);
        mav = new ModelAndView("redirect:/restaurantsTypes.do");
        mav.addObject("errorCode", new String("Unable to delete type"));
        return mav;
      }
      mav = new ModelAndView("redirect:/restaurantsTypes.do");
    }
    return mav;
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String processSubmitHandler(@ModelAttribute RestaurantType restaurantType, BindingResult result, SessionStatus status)
  {
    new RestaurantTypeValidator(this.foodOrdering).validate(restaurantType, result);
    if (result.hasErrors()) {
      return "admin/addRestaurantType";
    }
    this.foodOrdering.storeRestaurantType(restaurantType);
    status.setComplete();
    return "redirect:/restaurantsTypes.do?typeName=" + restaurantType.getTypeName();
  }
}
