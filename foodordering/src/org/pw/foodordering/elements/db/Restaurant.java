package org.pw.foodordering.elements.db;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class Restaurant
{
  Integer restId = Integer.valueOf(0);
  String name;
  String description;
  RestaurantType restType;
  Set<Group> groups;
  List<Timestamp> orderDates;
  
  public List<Timestamp> getOrderDates()
  {
    return this.orderDates;
  }
  
  public void setOrderDates(List<Timestamp> orderDates)
  {
    this.orderDates = orderDates;
  }
  
  public Integer getRestId()
  {
    return this.restId;
  }
  
  public void setRestId(Integer restId)
  {
    this.restId = restId;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
  }
  
  public RestaurantType getRestType()
  {
    return this.restType;
  }
  
  public void setRestType(RestaurantType restType)
  {
    this.restType = restType;
  }
  
  public Set<Group> getGroups()
  {
    return this.groups;
  }
  
  public void setGroups(Set<Group> groups)
  {
    this.groups = groups;
  }
}
