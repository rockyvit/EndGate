package org.pw.foodordering.interfaces;

import java.util.Collection;
import org.pw.foodordering.elements.db.Dish;
import org.pw.foodordering.elements.db.User;
import org.springframework.dao.DataAccessException;

public abstract interface DishCrudInterface
{
  public abstract Dish getDishByDishId(Integer paramInteger)
    throws DataAccessException;
  
  public abstract void orderDish(User paramUser, Dish paramDish)
    throws DataAccessException;
  
  public abstract Collection<Dish> getDishByGroupId(Integer paramInteger)
    throws DataAccessException;
  
  public abstract void storeDish(Dish paramDish)
    throws DataAccessException;
  
  public abstract void updateDish(Dish paramDish)
    throws DataAccessException;
  
  public abstract void removeDish(Dish paramDish)
    throws DataAccessException;
  
  public abstract void removeDishById(Integer paramInteger)
    throws DataAccessException;
}
