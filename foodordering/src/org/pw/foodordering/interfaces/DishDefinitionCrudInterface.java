package org.pw.foodordering.interfaces;

import java.util.Collection;
import org.pw.foodordering.elements.db.DishDef;
import org.springframework.dao.DataAccessException;

public abstract interface DishDefinitionCrudInterface
{
  public abstract Collection<DishDef> getAllDishDefs()
    throws DataAccessException;
  
  public abstract DishDef getDishDefById(Integer paramInteger)
    throws DataAccessException;
  
  public abstract DishDef getDishDefByName(String paramString)
    throws DataAccessException;
  
  public abstract void removeDishDefById(Integer paramInteger)
    throws DataAccessException;
  
  public abstract void storeDishDef(DishDef paramDishDef)
    throws DataAccessException;
  
  public abstract void updateDishDef(DishDef paramDishDef)
    throws DataAccessException;
  
  public abstract void removeDishDef(DishDef paramDishDef)
    throws DataAccessException;
}
