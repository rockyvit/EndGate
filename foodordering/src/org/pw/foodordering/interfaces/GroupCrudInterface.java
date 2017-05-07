package org.pw.foodordering.interfaces;

import java.util.Collection;
import org.pw.foodordering.elements.db.Group;
import org.springframework.dao.DataAccessException;

public abstract interface GroupCrudInterface
{
  public abstract Group getGroupById(Integer paramInteger)
    throws DataAccessException;
  
  public abstract void removeGroupById(Integer paramInteger)
    throws DataAccessException;
  
  public abstract Collection<Group> getGroupsByRestaurantId(Integer paramInteger)
    throws DataAccessException;
  
  public abstract void storeGroup(Group paramGroup)
    throws DataAccessException;
  
  public abstract void removeGroup(Group paramGroup)
    throws DataAccessException;
  
  public abstract void updateGruop(Group paramGroup)
    throws DataAccessException;
}
