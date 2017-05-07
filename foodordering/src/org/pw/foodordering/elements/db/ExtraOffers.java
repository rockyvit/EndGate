package org.pw.foodordering.elements.db;

public class ExtraOffers
{
  Integer offerId;
  String description;
  Restaurant restaurant;
  
  public Integer getOfferId()
  {
    return this.offerId;
  }
  
  public void setOfferId(Integer offerId)
  {
    this.offerId = offerId;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
  }
  
  public Restaurant getRestaurant()
  {
    return this.restaurant;
  }
  
  public void setRestaurant(Restaurant restaurant)
  {
    this.restaurant = restaurant;
  }
}
