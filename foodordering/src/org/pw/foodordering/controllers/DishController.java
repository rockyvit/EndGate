package org.pw.foodordering.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pw.foodordering.elements.db.Dish;
import org.pw.foodordering.elements.db.Group;
import org.pw.foodordering.interfaces.DishCrudInterface;
import org.pw.foodordering.interfaces.DishDefinitionCrudInterface;
import org.pw.foodordering.interfaces.FoodOrderingInterface;
import org.pw.foodordering.interfaces.GroupCrudInterface;
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
@RequestMapping({"/admin/crudDish.do"})
@SessionAttributes(types={Dish.class})
public class DishController
{
  private Log logger = LogFactory.getLog(getClass());
  private DishCrudInterface foodOrdering;
  
  @Autowired
  public DishController(FoodOrderingInterface foodOrdering)
  {
    this.foodOrdering = ((DishCrudInterface)foodOrdering);
  }
  
  @InitBinder
  public void setAllowedFields(WebDataBinder dataBinder)
  {
    dataBinder.setDisallowedFields(new String[] { "id" });
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView setupFormHandler(@RequestParam(value="groupId", required=false) Object groupId, @RequestParam(value="dishId", required=false) Object dishId, @RequestParam(value="action", required=false) Object action, HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("redirect:/restaurants.do");
    String sAction = (String)action;
    Integer iDishId = null;
    try
    {
      iDishId = Integer.valueOf((String)dishId);
    }
    catch (Exception e)
    {
      this.logger.debug("Ooops wrong dish Id !!!");
      this.logger.debug(e.getStackTrace());
      iDishId = null;
    }
    Integer iGroupId = null;
    try
    {
      iGroupId = Integer.valueOf((String)groupId);
    }
    catch (Exception e)
    {
      this.logger.debug("Ooops wrong group Id !!!");
      this.logger.debug(e.getStackTrace());
      iGroupId = null;
    }
    if ((sAction.equals("delete")) && (iDishId != null))
    {
      this.foodOrdering.removeDishById(iDishId);
    }
    else if ((sAction.equals("add")) && (iGroupId != null))
    {
      Group group = ((GroupCrudInterface)this.foodOrdering).getGroupById(iGroupId);
      if (group != null)
      {
        mav.setViewName("admin/crudDish");
        Dish dish = new Dish();
        dish.setGroup(group);
        mav.addObject("dishDefList", ((DishDefinitionCrudInterface)this.foodOrdering).getAllDishDefs());
        mav.addObject("dish", dish);
      }
    }
    else if ((sAction.equals("edit")) && (iDishId != null))
    {
      Dish dish = this.foodOrdering.getDishByDishId(iDishId);
      mav.setViewName("admin/crudDish");
      mav.addObject("dish", dish);
      mav.addObject("dishDefList", ((DishDefinitionCrudInterface)this.foodOrdering).getAllDishDefs());
    }
    return mav;
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String processSubmitHandler(@ModelAttribute Dish dish, BindingResult result, SessionStatus status)
  {
    if (result.hasErrors()) {
      return "admin/crudDish";
    }
    this.logger.debug("Dish class: ");
    this.logger.debug("ID" + dish.getDishId());
    
    this.foodOrdering.storeDish(dish);
    status.setComplete();
    return "redirect:/restaurants.do";
  }
}
