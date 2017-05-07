package org.pw.foodordering.elements.db;

public class Dish
  implements Comparable<Dish>
{
  Integer dishId = Integer.valueOf(0);
  Double price;
  String specyficInfo;
  Group group;
  DishDef details;
  
  public Integer getDishId()
  {
    return this.dishId;
  }
  
  public void setDishId(Integer dishId)
  {
    this.dishId = dishId;
  }
  
  public Double getPrice()
  {
    return this.price;
  }
  
  public void setPrice(Double price)
  {
    this.price = price;
  }
  
  public String getSpecyficInfo()
  {
    return this.specyficInfo;
  }
  
  public void setSpecyficInfo(String specyficInfo)
  {
    this.specyficInfo = specyficInfo;
  }
  
  public Group getGroup()
  {
    return this.group;
  }
  
  public void setGroup(Group group)
  {
    this.group = group;
  }
  
  public DishDef getDetails()
  {
    return this.details;
  }
  
  public void setDetails(DishDef details)
  {
    this.details = details;
  }
  
  public int compareTo(Dish o)
  {
    return this.details.getName().compareTo(o.getDetails().getName());
  }
}
