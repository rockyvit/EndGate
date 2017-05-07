package org.pw.foodordering.elements.misc;

import java.sql.Time;

public class TimeDishId
{
  Time time;
  Integer dishId;
  String dishName;
  String restaurantName;
  String login_id;
  
  public Time getTime()
  {
    return this.time;
  }
  
  public void setTime(Time time)
  {
    this.time = time;
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
  
 /* public String getUsername()
  {
    return this.username;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }*/
}
