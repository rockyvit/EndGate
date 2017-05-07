package org.pw.foodordering.interfaces;


import org.pw.foodordering.elements.db.Activation;
import org.pw.foodordering.elements.db.User;
import org.springframework.dao.DataAccessException;

public abstract interface ActivationCrudInterface
{
  /*public abstract void storeActivation(Activation paramActivation)
    throws DataAccessException;
  
  public abstract void removeActivation(Activation paramActivation)
    throws DataAccessException;
  
  public abstract Activation getActivationByKey(String paramString)
    throws DataAccessException;*/
  
 /* public abstract void storeEmail_secrete(User paramUser)
  throws DataAccessException;

public abstract void removeEmail_secrete(User paramUser)
  throws DataAccessException;

public abstract User getActivationByKey(String paramString)
  throws DataAccessException;*/
	
	/*public abstract void storeEmail_secrete(Activation paramActivation)
	  throws DataAccessException;*/

	public abstract void removeEmail_secrete(User user)
	  throws DataAccessException;

	public abstract Activation getActivationByKey(String paramString)
	  throws DataAccessException;
}
