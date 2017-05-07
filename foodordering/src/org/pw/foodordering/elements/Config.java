package org.pw.foodordering.elements;

import java.sql.Date;

public class Config
{
  Date deliveryDate;
  Integer preOrderTime;
  
  public Date getDeliveryDate()
  {
    return this.deliveryDate;
  }
  
  public void setDeliveryDate(Date deliveryDate)
  {
    this.deliveryDate = deliveryDate;
  }
  
  public Integer getPreOrderTime()
  {
    return this.preOrderTime;
  }
  
  public void setPreOrderTime(Integer preOrderTime)
  {
    this.preOrderTime = preOrderTime;
  }
}
