package org.pw.foodordering.controllers;

import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pw.foodordering.elements.OrderStatusType;
import org.pw.foodordering.elements.db.Dish;
import org.pw.foodordering.elements.db.DishDef;
import org.pw.foodordering.elements.db.Group;
import org.pw.foodordering.elements.db.Horder;
import org.pw.foodordering.elements.db.HorderElement;
import org.pw.foodordering.elements.db.Order;
import org.pw.foodordering.elements.db.Restaurant;
import org.pw.foodordering.elements.misc.SearchResults;
import org.pw.foodordering.engine.CommonUtils;
import org.pw.foodordering.interfaces.DishCrudInterface;
import org.pw.foodordering.interfaces.FoodOrderingInterface;
import org.pw.foodordering.interfaces.GroupCrudInterface;
import org.pw.foodordering.interfaces.OrderCrudInterface;
import org.pw.foodordering.interfaces.RestaurantCrudInterface;
import org.pw.foodordering.interfaces.SearchInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AjaxController
{
  private Log logger = LogFactory.getLog(getClass());
  private FoodOrderingInterface foodOrdering;
  
  @Autowired
  public AjaxController(FoodOrderingInterface foodorder)
  {
    this.foodOrdering = foodorder;
  }
  
  @RequestMapping(value={"/ajaxGetMainMessage.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView ajaxMainMessageHandler(HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mda = new ModelAndView("ajax/ajaxGetMainMessage");
    return mda;
  }
  
  @RequestMapping(value={"/ajaxGetRestaurants.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView ajaxRestaurantsHandler(HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mda = new ModelAndView("ajax/ajaxGetRestaurants");
    Collection<Restaurant> rests = ((RestaurantCrudInterface)this.foodOrdering).getRestaurantsOnly();
    mda.addObject("restaurantsList", rests);
    return mda;
  }
  
  @RequestMapping(value={"/ajaxGetGroups.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView ajaxGroupsHandler(HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mda = new ModelAndView("ajax/ajaxGetGroups");
    String restId = request.getParameter("restId");
    if (restId == null) {
      return mda;
    }
    Integer iRestId = Integer.valueOf(restId);
    Collection<Group> groups = ((GroupCrudInterface)this.foodOrdering).getGroupsByRestaurantId(iRestId);
    mda.addObject("groupsList", groups);
    Restaurant restaurant = ((RestaurantCrudInterface)this.foodOrdering).getRestaurantByRestId(iRestId);
    mda.addObject("restaurant", restaurant);
    return mda;
  }
  
  @RequestMapping(value={"/ajaxGetDishes.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView ajaxDishesHandler(HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mda = new ModelAndView("ajax/ajaxGetDishes");
    String groupId = request.getParameter("groupId");
    if (groupId == null) {
      return mda;
    }
    Integer iGroupId = Integer.valueOf(groupId);
    Collection<Dish> dishes = ((DishCrudInterface)this.foodOrdering).getDishByGroupId(iGroupId);
    mda.addObject("dishesList", dishes);
    Group gr = ((GroupCrudInterface)this.foodOrdering).getGroupById(iGroupId);
    mda.addObject("currentGroup", gr);
    return mda;
  }
  
  @RequestMapping(value={"/ajaxGetDetails.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView ajaxDetailsHandler(HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mda = new ModelAndView("ajax/ajaxGetDetails");
    String dishId = request.getParameter("dishId");
    if (dishId == null) {
      return mda;
    }
    Integer iDishId = Integer.valueOf(dishId);
    Dish dish = ((DishCrudInterface)this.foodOrdering).getDishByDishId(iDishId);
    mda.addObject("dish", dish);
    Integer empty = Integer.valueOf(0);
    if ((dish.getDetails().getDescription().length() == 0) && (dish.getSpecyficInfo().length() == 0)) {
      empty = Integer.valueOf(1);
    }
    mda.addObject("emp", empty);
    return mda;
  }
  
  @RequestMapping(value={"/ajaxGetFooter.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView ajaxFooterHandler(HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mda = new ModelAndView("ajax/ajaxGetFooter");
    return mda;
  }
  
  @RequestMapping(value={"/search.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView ajaxSearchHandler(HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mda = new ModelAndView("ajax/ajaxSearch");
    String value = request.getParameter("searching");
    if (value == null)
    {
      mda.addObject("found", new SearchResults());
      return mda;
    }
    SearchResults res = ((SearchInterface)this.foodOrdering).searchObjectByString(value);
    mda.addObject("found", res);
    return mda;
  }
  
  @RequestMapping(value={"/ajaxGetUserOldOrders.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView getUserOldOrdersList(HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mda = new ModelAndView("ajax/ajaxGetUserOldOrders");
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String name = "NONE";
    if (auth != null)
    {
      if ((auth.getPrincipal() instanceof UserDetails)) {
        name = ((UserDetails)auth.getPrincipal()).getUsername();
      } else {
        name = auth.getPrincipal().toString();
      }
    }
    else {
      throw new SecurityException("Not logged in");
    }
    Collection<Horder> col = ((OrderCrudInterface)this.foodOrdering).getOldOrdersByUsername(name);
    for (Horder oldOrder : col)
    {
      Double totalCash = Double.valueOf(0.0D);
      for (HorderElement oldOrderElem : oldOrder.getHorderElements()) {
        totalCash = Double.valueOf(totalCash.doubleValue() + oldOrderElem.getPrice().doubleValue());
      }
      oldOrder.setCashTotal(totalCash);
    }
    mda.addObject("oldOrdersList", col);
    return mda;
  }
  
  @ModelAttribute("orders")
  @RequestMapping(value={"/ajaxGetUserCurrentOrders.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView getUserCurrentOrdersList(HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mda = new ModelAndView("ajax/ajaxGetUserCurrentOrders");
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String name = "NONE";
    if (auth != null)
    {
      if ((auth.getPrincipal() instanceof UserDetails)) {
        name = ((UserDetails)auth.getPrincipal()).getUsername();
      } else {
        name = auth.getPrincipal().toString();
      }
    }
    else {
      throw new SecurityException("Not logged in");
    }
    List<Order> newOrders = ((OrderCrudInterface)this.foodOrdering).getNewOrders(name);
    Long currentTime = Long.valueOf(System.currentTimeMillis());
    for (Order curOrder : newOrders) {
      curOrder.setStringTimeRemaining(CommonUtils.convertTimesDiffToString(curOrder.getOrderingDate(), currentTime));
    }
    mda.addObject("orders", newOrders);
    mda.addObject("errorVal", Integer.valueOf(OrderStatusType.ERROR.getId()));
    
    return mda;
  }
}
