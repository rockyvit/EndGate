package org.pw.foodordering.elements.misc;

import java.sql.Timestamp;

public class DateDishId
{
  Timestamp date;
  Integer dishId;
  String dishName;
  String restaurantName;
  String login_id;
  String odate;
  
  public String getOdate()
  {
    return this.odate;
  }
  
  public void setOdate(String odate)
  {
    this.odate = odate;
  }
  
  public Timestamp getDate()
  {
    return this.date;
  }
  
  public void setDate(Timestamp date)
  {
    this.date = date;
  }
  
  public Integer getDishId()
  {
    return this.dishId;
  }
  
  public void setDishId(Integer dishId)
  {
    this.dishId = dishId;
  }
  
  public String getDishName()
  {
    return this.dishName;
  }
  
  public void setDishName(String dishName)
  {
    this.dishName = dishName;
  }
  
  public String getRestaurantName()
  {
    return this.restaurantName;
  }
  
  public void setRestaurantName(String restaurantName)
  {
    this.restaurantName = restaurantName;
  }

public String getLogin_id() {
	return login_id;
}

public void setLogin_id(String login_id) {
	this.login_id = login_id;
}
  
  /*public String getUsername()
  {
    return this.username;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }*/
}
