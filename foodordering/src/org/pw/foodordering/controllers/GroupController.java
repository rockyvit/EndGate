package org.pw.foodordering.controllers;

import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pw.foodordering.elements.db.Group;
import org.pw.foodordering.elements.db.Restaurant;
import org.pw.foodordering.interfaces.FoodOrderingInterface;
import org.pw.foodordering.interfaces.GroupCrudInterface;
import org.pw.foodordering.interfaces.RestaurantCrudInterface;
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
@RequestMapping({"/admin/crudGroup.do"})
@SessionAttributes(types={Group.class})
public class GroupController
{
  private Log logger = LogFactory.getLog(getClass());
  private GroupCrudInterface foodOrdering;
  
  @Autowired
  public GroupController(FoodOrderingInterface foodOrdering)
  {
    this.foodOrdering = ((GroupCrudInterface)foodOrdering);
  }
  
  @InitBinder
  public void setAllowedFields(WebDataBinder dataBinder)
  {
    dataBinder.setDisallowedFields(new String[] { "id" });
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView setupFormHandler(@RequestParam(value="restaurantName", required=false) Object restaurantName, @RequestParam(value="groupId", required=false) Object groupId, @RequestParam(value="action", required=false) Object action, HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mav = new ModelAndView();
    if ((restaurantName != null) && (action != null) && (((String)action).equals("add")))
    {
      mav.setViewName("admin/crudGroup");
      Restaurant restaurant = ((RestaurantCrudInterface)this.foodOrdering).getRestaurantByName((String)restaurantName);
      Group group = new Group();
      group.setRestaurant(restaurant);
      mav.addObject("group", group);
      return mav;
    }
    if (groupId == null)
    {
      mav.setViewName("redirect:/restaurants.do");
      return mav;
    }
    try
    {
      Integer iGroupId = Integer.valueOf(Integer.parseInt((String)groupId));
      if (((String)action).equals("delete"))
      {
        Group group = this.foodOrdering.getGroupById(iGroupId);
        Restaurant restaurant = group.getRestaurant();
        restaurant.getGroups().remove(group);
        this.foodOrdering.removeGroup(group);
      }
      else if (((String)action).equals("edit"))
      {
        mav.setViewName("admin/crudGroup");
        Group group = this.foodOrdering.getGroupById(iGroupId);
        mav.addObject("group", group);
        return mav;
      }
    }
    catch (Exception e)
    {
      this.logger.debug(e.getStackTrace());
    }
    mav.setViewName("redirect:/restaurants.do");
    return mav;
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String processSubmitHandler(@ModelAttribute Group group, BindingResult result, SessionStatus status)
  {
    if (result.hasErrors()) {
      return "admin/crudGroup";
    }
    this.logger.debug("Group class: ");
    this.logger.debug("name" + group.getGroupName());
    
    this.foodOrdering.storeGroup(group);
    status.setComplete();
    return "redirect:/restaurants.do";
  }
}
