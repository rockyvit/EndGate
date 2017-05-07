package org.pw.foodordering.interfaces;

import java.util.Collection;
import org.pw.foodordering.elements.db.RestaurantType;
import org.springframework.dao.DataAccessException;

public abstract interface RestaurantTypeCrudInterface
{
  public abstract Collection<RestaurantType> getRestaurantTypes()
    throws DataAccessException;
  
  public abstract RestaurantType getRestaurantTypeByName(String paramString)
    throws DataAccessException;
  
  public abstract void removeRestaurantType(RestaurantType paramRestaurantType)
    throws DataAccessException;
  
  public abstract void updateRestaurantType(RestaurantType paramRestaurantType)
    throws DataAccessException;
  
  public abstract void storeRestaurantType(RestaurantType paramRestaurantType)
    throws DataAccessException;
}
