package org.pw.foodordering.interfaces;

import java.util.Collection;
import java.util.HashMap;
import org.pw.foodordering.elements.db.Restaurant;
import org.springframework.dao.DataAccessException;

public abstract interface RestaurantCrudInterface
{
  public abstract Collection<Restaurant> getRestaurantsOnly()
    throws DataAccessException;
  
  @Deprecated
  public abstract Collection<Restaurant> getRestaurantsWithAllData()
    throws DataAccessException;
  
  public abstract Restaurant getRestaurantByName(String paramString)
    throws DataAccessException;
  
  public abstract Restaurant getRestaurantByRestId(Integer paramInteger)
    throws DataAccessException;
  
  public abstract void removeRestaurant(Restaurant paramRestaurant)
    throws DataAccessException;
  
  public abstract void updateRestaurant(Restaurant paramRestaurant)
    throws DataAccessException;
  
  public abstract void storeRestaurant(Restaurant paramRestaurant)
    throws DataAccessException;
  
  public abstract void importRestaurantMenu(String paramString, HashMap<String, HashMap<String, HashMap<String, Object>>> paramHashMap)
    throws DataAccessException;
}
