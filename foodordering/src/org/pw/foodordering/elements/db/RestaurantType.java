package org.pw.foodordering.elements.db;

import java.util.Set;

public class RestaurantType
{
  Integer restTypeId;
  String typeName;
  Set<Restaurant> restaurants;
  
  public Integer getRestTypeId()
  {
    return this.restTypeId;
  }
  
  public void setRestTypeId(Integer restTypeId)
  {
    this.restTypeId = restTypeId;
  }
  
  public String getTypeName()
  {
    return this.typeName;
  }
  
  public void setTypeName(String typeName)
  {
    this.typeName = typeName;
  }
  
  public Set<Restaurant> getRestaurants()
  {
    return this.restaurants;
  }
  
  public void setRestaurants(Set<Restaurant> restaurants)
  {
    this.restaurants = restaurants;
  }
  
  public String toString()
  {
    return this.typeName;
  }
}
