package org.pw.foodordering.interfaces;

import org.pw.foodordering.elements.misc.SearchResults;
import org.springframework.dao.DataAccessException;

public abstract interface SearchInterface
{
  public abstract SearchResults searchObjectByString(String paramString)
    throws DataAccessException;
}
