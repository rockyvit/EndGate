package org.pw.foodordering.interfaces;

import java.util.HashMap;

public abstract interface FetcherInterface
{
  public abstract HashMap<String, HashMap<String, HashMap<String, Object>>> getMenu();
  
  public abstract void parse();
}
