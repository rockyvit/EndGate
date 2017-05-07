package org.pw.foodordering.elements.misc;

import java.util.List;
import org.pw.foodordering.elements.db.Dish;
import org.pw.foodordering.elements.db.Group;

public class SearchResults
{
  List<Group> groups;
  Integer groupsSize = Integer.valueOf(0);
  List<Dish> dishes;
  Integer dishesSize = Integer.valueOf(0);
  
  public List<Group> getGroups()
  {
    return this.groups;
  }
  
  public void setGroups(List<Group> groups)
  {
    this.groups = groups;
    this.groupsSize = Integer.valueOf(groups.size());
  }
  
  public Integer getGroupsSize()
  {
    return this.groupsSize;
  }
  
  public List<Dish> getDishes()
  {
    return this.dishes;
  }
  
  public void setDishes(List<Dish> dishes)
  {
    this.dishes = dishes;
    this.dishesSize = Integer.valueOf(dishes.size());
  }
  
  public Integer getDishesSize()
  {
    return this.dishesSize;
  }
}
