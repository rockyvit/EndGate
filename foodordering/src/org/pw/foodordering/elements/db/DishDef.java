package org.pw.foodordering.elements.db;

import java.util.Set;

public class DishDef
{
  Integer dishDefId = Integer.valueOf(0);
  String name;
  String description;
  Set<Dish> dishes;
  
  public Integer getDishDefId()
  {
    return this.dishDefId;
  }
  
  public void setDishDefId(Integer dishDefId)
  {
    this.dishDefId = dishDefId;
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
  
  public Set<Dish> getDishes()
  {
    return this.dishes;
  }
  
  public void setDishes(Set<Dish> dishes)
  {
    this.dishes = dishes;
  }
  
  public String toString()
  {
    return this.name;
  }
}
