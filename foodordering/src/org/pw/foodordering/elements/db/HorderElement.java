package org.pw.foodordering.elements.db;

public class HorderElement
{
  Integer hitemId;
  Horder horder;
  String dishName;
  Double price;
  
  public Integer getHitemId()
  {
    return this.hitemId;
  }
  
  public void setHitemId(Integer hitemId)
  {
    this.hitemId = hitemId;
  }
  
  public Horder getHorder()
  {
    return this.horder;
  }
  
  public void setHorder(Horder horder)
  {
    this.horder = horder;
  }
  
  public String getDishName()
  {
    return this.dishName;
  }
  
  public void setDishName(String dishName)
  {
    this.dishName = dishName;
  }
  
  public Double getPrice()
  {
    return this.price;
  }
  
  public void setPrice(Double price)
  {
    this.price = price;
  }
}
