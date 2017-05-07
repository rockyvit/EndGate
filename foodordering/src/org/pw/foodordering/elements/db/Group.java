package org.pw.foodordering.elements.db;

import java.util.Collection;

public class Group
{
  Integer groupId = Integer.valueOf(0);
  String groupName;
  Restaurant restaurant;
  Collection<Dish> dishes;
  
  public Integer getGroupId()
  {
    return this.groupId;
  }
  
  public void setGroupId(Integer groupId)
  {
    this.groupId = groupId;
  }
  
  public Restaurant getRestaurant()
  {
    return this.restaurant;
  }
  
  public void setRestaurant(Restaurant restaurant)
  {
    this.restaurant = restaurant;
  }
  
  public Collection<Dish> getDishes()
  {
    return this.dishes;
  }
  
  public void setDishes(Collection<Dish> dishes)
  {
    this.dishes = dishes;
  }
  
  public String getGroupName()
  {
    return this.groupName;
  }
  
  public void setGroupName(String groupName)
  {
    this.groupName = groupName;
  }
}
