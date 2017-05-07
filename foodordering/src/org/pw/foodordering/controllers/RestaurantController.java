package org.pw.foodordering.controllers;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pw.foodordering.elements.RestaurantValidator;
import org.pw.foodordering.elements.db.Restaurant;
import org.pw.foodordering.elements.db.RestaurantType;
import org.pw.foodordering.interfaces.FoodOrderingInterface;
import org.pw.foodordering.interfaces.RestaurantCrudInterface;
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
@RequestMapping({"/admin/crudRestaurant.do"})
@SessionAttributes(types={Restaurant.class})
public class RestaurantController
{
  private Log logger = LogFactory.getLog(getClass());
  private RestaurantCrudInterface foodOrdering;
  
  @Autowired
  public RestaurantController(FoodOrderingInterface foodOrdering)
  {
    this.foodOrdering = ((RestaurantCrudInterface)foodOrdering);
  }
  
  @InitBinder
  public void setAllowedFields(WebDataBinder dataBinder)
  {
    dataBinder.setDisallowedFields(new String[] { "id" });
  }
  
  @ModelAttribute("typeList")
  public Collection<RestaurantType> populateResaurantsTypes()
  {
    return ((RestaurantTypeCrudInterface)this.foodOrdering).getRestaurantTypes();
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView setupFormHandler(@RequestParam(value="restaurantName", required=false) Object restaurantName, @RequestParam(value="action", required=false) Object action, HttpServletRequest request, HttpServletResponse response)
  {
    String sRestaurantName = (String)restaurantName;
    String sAction = (String)action;
    ModelAndView mav = null;
    if ((sRestaurantName == null) || (sAction == null))
    {
      mav = new ModelAndView("admin/crudRestaurant");
      mav.addObject("restaurant", new Restaurant());
    }
    else
    {
      Restaurant dbRest = this.foodOrdering.getRestaurantByName(sRestaurantName);
      if (dbRest == null)
      {
        mav = new ModelAndView("redirect:/restaurants.do");
        return mav;
      }
      if (sAction.equals("delete"))
      {
        this.foodOrdering.removeRestaurant(dbRest);
        mav = new ModelAndView("redirect:/restaurants.do");
      }
      else if (sAction.equals("edit"))
      {
        mav = new ModelAndView("admin/crudRestaurant");
        mav.addObject("restaurant", dbRest);
      }
    }
    return mav;
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String processSubmitHandler(@ModelAttribute Restaurant restaurant, BindingResult result, SessionStatus status)
  {
    new RestaurantValidator().validate(restaurant, result);
    if (result.hasErrors()) {
      return "admin/crudRestaurant";
    }
    this.logger.debug("Restaurant class: ");
    this.logger.debug("name" + restaurant.getName());
    this.logger.debug("type" + restaurant.getRestType().getTypeName());
    this.logger.debug("type id" + restaurant.getRestType().getRestTypeId());
    
    restaurant.setDescription(restaurant.getDescription().replaceAll("\n", "<br/>"));
    
    this.foodOrdering.storeRestaurant(restaurant);
    status.setComplete();
    return "redirect:/restaurants.do";
  }
}
