package org.pw.foodordering.controllers;

import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pw.foodordering.elements.DateTimeDef;
import org.pw.foodordering.elements.db.Restaurant;
import org.pw.foodordering.engine.EmailTimerService;
import org.pw.foodordering.interfaces.FoodOrderingInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping({"/setupDate.do"})
@SessionAttributes(types={Restaurant.class})
public class DateSetterController
{
  private Log logger = LogFactory.getLog(getClass());
  private FoodOrderingInterface foodOrdering;
  private EmailTimerService emailTimerService;
  
  @Autowired
  public DateSetterController(FoodOrderingInterface foodOrdering, EmailTimerService emailTimerService)
  {
    this.foodOrdering = foodOrdering;
    this.emailTimerService = emailTimerService;
  }
  
  @InitBinder
  public void setAllowedFields(WebDataBinder dataBinder)
  {
    dataBinder.setDisallowedFields(new String[] { "id" });
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String setupFormHandler(Model model)
  {
    DateTimeDef dateTime = new DateTimeDef();
    model.addAttribute("dateTime", dateTime);
    model.addAttribute("hlist", dateTime.getAllowedHours());
    model.addAttribute("mlist", dateTime.getAllowedMinutes());
    return "setupDate";
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String processSubmitHandler(@ModelAttribute DateTimeDef dateTime, BindingResult result, SessionStatus status)
  {
    if (result.hasErrors()) {
      return "setupDate";
    }
    Date date = new Date();
    date.setHours(dateTime.getHour().intValue());
    date.setMinutes(dateTime.getMinute().intValue());
    date.setSeconds(0);
    this.logger.debug("Date: " + date);
    
    return "redirect:welcome.do";
  }
}
