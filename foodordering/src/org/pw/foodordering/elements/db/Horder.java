package org.pw.foodordering.elements.db;

import java.sql.Timestamp;
import java.util.Collection;

public class Horder
{
  Integer horderId;
  User user;
  String restaurantName;
  Timestamp orderingDate;
  Timestamp creationDate;
  Double cashTotal;
  Collection<HorderElement> horderElements;
  
  public Integer getHorderId()
  {
    return this.horderId;
  }
  
  public void setHorderId(Integer horderId)
  {
    this.horderId = horderId;
  }
  
  public User getUser()
  {
    return this.user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
  
  public Collection<HorderElement> getHorderElements()
  {
    return this.horderElements;
  }
  
  public void setHorderElements(Collection<HorderElement> horderElements)
  {
    this.horderElements = horderElements;
  }
  
  public String getRestaurantName()
  {
    return this.restaurantName;
  }
  
  public void setRestaurantName(String restaurantName)
  {
    this.restaurantName = restaurantName;
  }
  
  public Double getCashTotal()
  {
    return this.cashTotal;
  }
  
  public void setCashTotal(Double cashTotal)
  {
    this.cashTotal = cashTotal;
  }
  
  public Timestamp getCreationDate()
  {
    return this.creationDate;
  }
  
  public void setCreationDate(Timestamp creationDate)
  {
    this.creationDate = creationDate;
  }
  
  public Timestamp getOrderingDate()
  {
    return this.orderingDate;
  }
  
  public void setOrderingDate(Timestamp orderingDate)
  {
    this.orderingDate = orderingDate;
  }
}
