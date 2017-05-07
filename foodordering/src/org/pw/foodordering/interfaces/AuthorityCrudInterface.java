package org.pw.foodordering.interfaces;

import org.pw.foodordering.elements.db.Authority;
import org.springframework.dao.DataAccessException;

public abstract interface AuthorityCrudInterface
{
  public abstract void storeAuthority(Authority paramAuthority)
    throws DataAccessException;
  
  public abstract void removeAuthority(Authority paramAuthority)
    throws DataAccessException;
}
