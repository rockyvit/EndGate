package org.pw.foodordering.controllers;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pw.foodordering.elements.db.Restaurant;
import org.pw.foodordering.interfaces.FoodOrderingInterface;
import org.pw.foodordering.interfaces.RestaurantCrudInterface;
import org.pw.foodordering.interfaces.RestaurantTypeCrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController
{
  private Log logger = LogFactory.getLog(getClass());
  private FoodOrderingInterface foodOrdering;
  
  @Autowired
  public MainController(FoodOrderingInterface foodorder)
  {
    this.foodOrdering = foodorder;
  }
  
  @RequestMapping({"/welcome.do"})
  public ModelAndView welcomeHandler(HttpServletRequest request, HttpServletResponse response)
  {
    return new ModelAndView("welcome");
  }
  
  @RequestMapping({"/restaurants.do"})
  public ModelMap restaurantHandler(HttpServletRequest request, HttpServletResponse response)
  {
    Collection<Restaurant> rests = ((RestaurantCrudInterface)this.foodOrdering).getRestaurantsWithAllData();
    return new ModelMap("restaurantList", rests);
  }
  
  @RequestMapping({"/restaurantsTypes.do"})
  public ModelMap restaurantTypesParamHandler(@RequestParam(value="typeName", required=false) Object typeName, HttpServletRequest request, HttpServletResponse response)
  {
    String addedType = null;
    ModelMap ModelMap = new ModelMap("restaurantsTypes");
    ModelMap.addAttribute("restaurantsTypesList", ((RestaurantTypeCrudInterface)this.foodOrdering).getRestaurantTypes());
    addedType = (String)typeName;
    ModelMap.addAttribute("addedType", addedType);
    return ModelMap;
  }
  
  @RequestMapping({"/403.do"})
  public ModelAndView error403MainHandler(HttpServletRequest request, HttpServletResponse response)
  {
    return new ModelAndView("403");
  }
}
