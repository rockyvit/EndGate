package org.pw.foodordering.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pw.foodordering.elements.db.Dish;
import org.pw.foodordering.elements.db.DishDef;
import org.pw.foodordering.interfaces.DishDefinitionCrudInterface;
import org.pw.foodordering.interfaces.FoodOrderingInterface;
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
@RequestMapping({"/admin/crudDishDef.do"})
@SessionAttributes(types={Dish.class})
public class DishDefController
{
  private Log logger = LogFactory.getLog(getClass());
  private DishDefinitionCrudInterface foodOrdering;
  
  @Autowired
  public DishDefController(FoodOrderingInterface foodOrdering)
  {
    this.foodOrdering = ((DishDefinitionCrudInterface)foodOrdering);
  }
  
  @InitBinder
  public void setAllowedFields(WebDataBinder dataBinder)
  {
    dataBinder.setDisallowedFields(new String[] { "id" });
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView setupFormHandler(@RequestParam(value="action", required=false) Object action, @RequestParam(value="dishDefId", required=false) Object dishDefId, HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("redirect:/user/orders.do");
    if (action != null)
    {
      String sAction = (String)action;
      if (sAction.equals("add"))
      {
        mav.addObject("dishDef", new DishDef());
        mav.setViewName("admin/crudDishDef");
      }
      else if ((sAction.equals("edit")) && (dishDefId != null))
      {
        try
        {
          Integer iDishDefId = Integer.valueOf((String)dishDefId);
          DishDef dishDef = this.foodOrdering.getDishDefById(iDishDefId);
          if (dishDef != null)
          {
            mav.setViewName("admin/crudDishDef");
            mav.addObject("dishDef", dishDef);
          }
        }
        catch (Exception e)
        {
          this.logger.debug("Unable to parse dishDefId !!!!");
          this.logger.debug(e.getStackTrace());
        }
      }
      else if ((sAction.equals("delete")) && (dishDefId != null))
      {
        try
        {
          Integer iDishDefId = Integer.valueOf((String)dishDefId);
          this.foodOrdering.removeDishDefById(iDishDefId);
        }
        catch (Exception e)
        {
          this.logger.debug("Unable to parse dishDefId !!!!");
          this.logger.debug(e.getStackTrace());
        }
      }
    }
    return mav;
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String processSubmitHandler(@ModelAttribute DishDef dishDef, BindingResult result, SessionStatus status)
  {
    if (result.hasErrors()) {
      return "admin/crudDishDef";
    }
    this.logger.debug("DishDef class: ");
    this.logger.debug("Name" + dishDef.getName());
    
    this.foodOrdering.storeDishDef(dishDef);
    status.setComplete();
    return "redirect:/restaurants.do";
  }
}
