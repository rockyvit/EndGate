package org.pw.foodordering.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.cache.Timestamper;
import org.pw.foodordering.elements.OrderStatusType;
import org.pw.foodordering.elements.db.Dish;
import org.pw.foodordering.elements.db.Order;
import org.pw.foodordering.elements.db.OrderElement;
import org.pw.foodordering.elements.db.Restaurant;
import org.pw.foodordering.elements.db.User;
import org.pw.foodordering.elements.misc.ReportData;
import org.pw.foodordering.elements.misc.ReportUserData;
import org.pw.foodordering.engine.CommonUtils;
import org.pw.foodordering.interfaces.FoodOrderingInterface;
import org.pw.foodordering.interfaces.UserCrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportsController
{
  private Log logger = LogFactory.getLog(getClass());
  private FoodOrderingInterface foodOrdering;
  
  @Autowired
  public ReportsController(FoodOrderingInterface foodorder)
  {
    this.foodOrdering = foodorder;
  }
  User user;
  Integer restId;
  @SuppressWarnings("rawtypes")
@RequestMapping({"/showCurrentOrders.do"})
  public ModelAndView currentOrdersHandler(HttpServletRequest request, HttpServletResponse response, HashMap<Timestamp, HashMap<Integer, Collection<OrderElement>>> times)
  
  {
    ModelAndView mav = new ModelAndView("/ajax/ajaxGetCurrentOrders");
    
    /* Collection<User> users = ((UserCrudInterface)this.foodOrdering).getAllUsers();
    this.logger.debug("PIO: users " + users.size());
    
    HashMap<Integer, HashMap<Timestamp, HashMap<Integer, Collection<OrderElement>>>> allData = new HashMap();
    HashMap<Integer, Restaurant> restaurantMapping = new HashMap();
    HashMap<Integer, User> userMapping = new HashMap();
    for (Iterator i$ = users.iterator(); i$.hasNext();)
    {
      user = (User)i$.next();
     // this.logger.debug("PIO: !user.getOrders().isEmpty() " + (!user.geto().isEmpty()));
    if (!user.getOrders().isEmpty()) {
        for (Order order : user.getOrders()) {
          if (!order.getStatus().equals(Integer.valueOf(OrderStatusType.ERROR.getId())))
          {
            HashMap<Timestamp, HashMap<Integer, Collection<OrderElement>>> rTime = null;
            rTime = (HashMap)allData.get(order.getRestaurant().getRestId());
            if (rTime == null)
            {
              this.logger.debug("PIO: 1null");
              rTime = new HashMap();
            }
            HashMap<Integer, Collection<OrderElement>> rUserId = null;
            rUserId = (HashMap)rTime.get(order.getOrderingDate());
            if (rUserId == null)
            {
              this.logger.debug("PIO: 2 null");
              rUserId = new HashMap();
            }
            this.logger.debug("PIO: !order.getOrderElements().isEmpty()" + (!order.getOrderElements().isEmpty()));
            if (!order.getOrderElements().isEmpty())
            {
              rUserId.put(user.getUserId(), order.getOrderElements());
              userMapping.put(user.getUserId(), user);
              restaurantMapping.put(order.getRestaurant().getRestId(), order.getRestaurant());
              
              rTime.put(order.getOrderingDate(), rUserId);
              allData.put(order.getRestaurant().getRestId(), rTime);
            }
          }
        }
      }
    }
    
    ArrayList<ReportData> finalList = new ArrayList();
    Long currentTime = Long.valueOf(System.currentTimeMillis());
    for (Iterator i$ = allData.keySet().iterator(); i$.hasNext();)
    {
      restId = (Integer)i$.next();
      times = (HashMap<Timestamp, HashMap<Integer,Collection<OrderElement>>>)allData.get(restId);
     
      for (Timestamp time : times.keySet())
      {
        HashMap<Integer, Collection<OrderElement>> usersMappingObj = (HashMap)times.get(time);
        Double total = Double.valueOf(0.0D);
        for (Collection<OrderElement> oelements : usersMappingObj.values()) {
          for (OrderElement oe : oelements) {
            total = Double.valueOf(total.doubleValue() + oe.getDish().getPrice().doubleValue());
          }
        }
        ReportData rd = new ReportData();
        rd.setTotalCash(total);
        rd.setTs(time);
        rd.setTimeRemaining(CommonUtils.convertTimesDiffToString(time, currentTime));
        rd.setRestName(((Restaurant)restaurantMapping.get(restId)).getName());
        rd.setUsersCount(Integer.valueOf(usersMappingObj.size()));
        
        Collection<ReportUserData> localUserData = new ArrayList();
        for (Integer objUserId : usersMappingObj.keySet())
        {
          ReportUserData ud = new ReportUserData();
          ud.setUsername(((User)userMapping.get(objUserId)).getLogin_id());
          ud.setOrderElements((Collection)usersMappingObj.get(objUserId));
          localUserData.add(ud);
        }
        rd.setDetails(localUserData);
        finalList.add(rd);
      }
    }
    
    HashMap<Timestamp, HashMap<Integer, Collection<OrderElement>>> times1;
    Collections.sort(finalList, new ReportListComparator());
    
    mav.addObject("report", finalList);
    mav.addObject("report_size", Integer.valueOf(finalList.size()));
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = "NONE";
    if (auth != null)
    {
      if ((auth.getPrincipal() instanceof UserDetails)) {
        username = ((UserDetails)auth.getPrincipal()).getUsername();
      } else {
        username = auth.getPrincipal().toString();
      }
      mav.addObject("username", username);
    }
    else
    {
      mav.addObject("username", "nan");
    }*/
    return mav;
  }

}
