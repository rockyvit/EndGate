package org.pw.foodordering.elements.db;

import java.sql.Timestamp;
import java.util.Collection;

public class Order
{
  Integer orderId;
  String additionalInfo;
  Timestamp orderingDate;
  Timestamp creationDate;
  Double cashPaid = Double.valueOf(0.0D);
  Double cashToPay = Double.valueOf(0.0D);
  Integer status;
  Restaurant restaurant;
  String stringTimeRemaining;
  User user;
  Collection<OrderElement> orderElements;
  
  public Integer getOrderId()
  {
    return this.orderId;
  }
  
  public void setOrderId(Integer orderId)
  {
    this.orderId = orderId;
  }
  
  public String getAdditionalInfo()
  {
    return this.additionalInfo;
  }
  
  public void setAdditionalInfo(String additionalInfo)
  {
    this.additionalInfo = additionalInfo;
  }
  
  public Double getCashPaid()
  {
    return this.cashPaid;
  }
  
  public void setCashPaid(Double cashPaid)
  {
    this.cashPaid = cashPaid;
  }
  
  public Integer getStatus()
  {
    return this.status;
  }
  
  public void setStatus(Integer status)
  {
    this.status = status;
  }
  
  public Collection<OrderElement> getOrderElements()
  {
    return this.orderElements;
  }
  
  public void setOrderElements(Collection<OrderElement> orderElements)
  {
    this.orderElements = orderElements;
  }
  
  public User getUser()
  {
    return this.user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
  
  public Restaurant getRestaurant()
  {
    return this.restaurant;
  }
  
  public void setRestaurant(Restaurant restaurant)
  {
    this.restaurant = restaurant;
  }
  
  public Double getCashToPay()
  {
    return this.cashToPay;
  }
  
  public void setCashToPay(Double cashToPay)
  {
    this.cashToPay = cashToPay;
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
  
  public String getStringTimeRemaining()
  {
    return this.stringTimeRemaining;
  }
  
  public void setStringTimeRemaining(String stringTimeRemaining)
  {
    this.stringTimeRemaining = stringTimeRemaining;
  }
}
