package org.pw.foodordering.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pw.foodordering.interfaces.FoodOrderingInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CommonActionsController
{
  private Log logger = LogFactory.getLog(getClass());
  private FoodOrderingInterface foodOrdering;
  
  @Autowired
  public CommonActionsController(FoodOrderingInterface foodorder)
  {
    this.foodOrdering = foodorder;
  }
}
