package org.pw.foodordering.elements.db;

public class OrderElement
{
  Integer orderElementId;
  String additionalInfo;
  Dish dish;
  Order order;
  
  public Dish getDish()
  {
    return this.dish;
  }
  
  public void setDish(Dish dish)
  {
    this.dish = dish;
  }
  
  public Integer getOrderElementId()
  {
    return this.orderElementId;
  }
  
  public void setOrderElementId(Integer orderElementId)
  {
    this.orderElementId = orderElementId;
  }
  
  public String getAdditionalInfo()
  {
    return this.additionalInfo;
  }
  
  public void setAdditionalInfo(String additionalInfo)
  {
    this.additionalInfo = additionalInfo;
  }
  
  public Order getOrder()
  {
    return this.order;
  }
  
  public void setOrder(Order order)
  {
    this.order = order;
  }
}
