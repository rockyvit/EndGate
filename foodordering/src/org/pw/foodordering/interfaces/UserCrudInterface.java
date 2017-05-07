package org.pw.foodordering.interfaces;

import java.util.Collection;
import org.pw.foodordering.elements.db.User;
import org.springframework.dao.DataAccessException;

public abstract interface UserCrudInterface
{
	/* public abstract User getUserByUserName(String paramString)
	    throws DataAccessException;*/
  public abstract User getUserByEmail(String paramUser)
    throws DataAccessException;
  
  public abstract Collection<User> getAllUsers()
    throws DataAccessException;
  
  public abstract void deleteUser(User paramUser)
    throws DataAccessException;
  
  public abstract void storeUser(User user)
    throws DataAccessException;
  
  public abstract void updateUser(User paramUser)
    throws DataAccessException;

public abstract User getUserByMobileno(String Phone)
throws DataAccessException;
}

