package org.pw.foodordering.controllers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pw.foodordering.editors.TimestampEditor;
import org.pw.foodordering.elements.misc.DateDishId;
import org.pw.foodordering.exceptions.OperationCannotBeCompletedException;
import org.pw.foodordering.interfaces.FoodOrderingInterface;
import org.pw.foodordering.interfaces.OrderCrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/users/orders.do"})
public class OrderController
{
  private Log logger = LogFactory.getLog(getClass());
  private OrderCrudInterface foodOrdering;
  
  @Autowired
  public OrderController(FoodOrderingInterface foodOrdering)
  {
    this.foodOrdering = ((OrderCrudInterface)foodOrdering);
  }
  
  @InitBinder
  public void setAllowedFields(WebDataBinder dataBinder)
  {
    dataBinder.setDisallowedFields(new String[] { "id" });
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView setupFormHandler(@RequestParam(value="orderId", required=false) Object orderId, @RequestParam(value="orderElemId", required=false) Object orderElemId, @RequestParam(value="dishId", required=false) Object dishId, @RequestParam(value="action", required=false) Object action, HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mav = new ModelAndView("users/orders");
    
    String sAction = (String)action;
    Integer iOrderId = null;
    try
    {
      iOrderId = Integer.valueOf((String)orderId);
    }
    catch (Exception e)
    {
      this.logger.debug(e.getStackTrace());
      iOrderId = null;
    }
    Integer iOrderElemId = null;
    try
    {
      iOrderElemId = Integer.valueOf((String)orderElemId);
    }
    catch (Exception e)
    {
      this.logger.debug(e.getStackTrace());
      iOrderElemId = null;
    }
    Integer iDishId = null;
    try
    {
      iDishId = Integer.valueOf((String)dishId);
    }
    catch (Exception e)
    {
      this.logger.debug(e.getStackTrace());
      iDishId = null;
    }
    if (sAction != null) {
      if ((sAction.equals("delete")) && (iOrderId != null) && (iOrderElemId == null))
      {
        try
        {
          this.foodOrdering.removeOrderById(iOrderId);
          mav.setViewName("redirect:/users/orders.do");
        }
        catch (OperationCannotBeCompletedException e)
        {
          mav.addObject("exception", e);
          mav.setViewName("/myException");
        }
      }
      else if ((sAction.equals("delete")) && (iOrderId == null) && (iOrderElemId != null))
      {
        try
        {
          this.foodOrdering.removeOrderElementById(iOrderElemId);
          mav.setViewName("redirect:/users/orders.do");
        }
        catch (OperationCannotBeCompletedException e)
        {
          mav.addObject("exception", e);
          mav.setViewName("/myException");
        }
      }
      else if ((sAction.equals("order")) && (dishId != null))
      {
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
          this.logger.debug("Checking for current orders");
          
          Collection<Timestamp> dates = this.foodOrdering.getAllOrdersDistinctByOrderingDate(iDishId);
          
          SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
          for (Timestamp dat : dates) {
            this.logger.debug("PIO: date -> " + formatter.format(dat));
          }
          DateDishId dateDishId = new DateDishId();
          dateDishId.setDishId(iDishId);
          
          this.logger.debug("PIO: setting username: " + username);
          mav.addObject("dateDishId", dateDishId);
          mav.addObject("dates", dates);
          mav.addObject("datesSize", Integer.valueOf(dates.size()));
          
          mav.setViewName("users/selectTime");
          return mav;
        }
        mav.setViewName("redirect:/users/orders.do");
      }
    }
    return mav;
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView processSubmitHandler(@ModelAttribute("dateDishId") DateDishId dateDishId, BindingResult result, SessionStatus status)
  {
    ModelAndView mav = new ModelAndView("redirect:/users/orders.do");
    if (dateDishId.getOdate() == null)
    {
      this.logger.error("Odate cannot be null !!!");
      return mav;
    }
    this.logger.debug("PIO: Odate OK: " + dateDishId.getOdate());
    TimestampEditor te = new TimestampEditor();
    te.setAsText(dateDishId.getOdate());
    Timestamp ts = (Timestamp)te.getValue();
    this.logger.debug("PIO: date obtained: " + ts);
    dateDishId.setDate(ts);
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    
    this.logger.debug("PIO: selected " + dateDishId.getDate() + " for " + dateDishId.getDishId());
    
    String username = "NONE";
    if (auth != null)
    {
      if ((auth.getPrincipal() instanceof UserDetails)) {
        username = ((UserDetails)auth.getPrincipal()).getUsername();
      } else {
        username = auth.getPrincipal().toString();
      }
      try
      {
        this.foodOrdering.orderDishForUser(username, dateDishId.getDishId(), dateDishId.getDate());
      }
      catch (OperationCannotBeCompletedException e)
      {
        mav.addObject("exception", e);
        mav.setViewName("/myException");
      }
    }
    else
    {
      this.logger.debug("Autoruzation failed !!!");
    }
    return mav;
  }
}
