package org.pw.foodordering.elements.misc;

import java.util.LinkedList;
import java.util.List;

public class RestaurantUrlMap
{
  String restaurantName;
  List<String> opt = new LinkedList();
  String selected = "";
  
  public RestaurantUrlMap()
  {
    this.opt.add("uciecha");
    this.opt.add("dagrasso");
  }
  
  public String getRestaurantName()
  {
    return this.restaurantName;
  }
  
  public void setRestaurantName(String restaurantName)
  {
    this.restaurantName = restaurantName;
  }
  
  public List<String> getOpt()
  {
    return this.opt;
  }
  
  public void setOpt(List<String> opt)
  {
    this.opt = opt;
  }
  
  public String getSelected()
  {
    return this.selected;
  }
  
  public void setSelected(String selected)
  {
    this.selected = selected;
  }
}
