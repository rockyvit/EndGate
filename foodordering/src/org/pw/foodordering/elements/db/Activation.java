package org.pw.foodordering.elements.db;

public class Activation
{
  Integer activationId;
  String activationCode;
  String login_id;
  public String getLogin_id() {
	return login_id;
}

public void setLogin_id(String login_id) {
	this.login_id = login_id;
}

Integer userId;
  User user;
  
  public String getActivationCode()
  {
    return this.activationCode;
  }
  
  public void setActivationCode(String activationCode)
  {
    this.activationCode = activationCode;
  }
  
  public User getUser()
  {
    return this.user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
    
  }
  
  public Integer getActivationId()
  {
    return this.activationId;
  }
  
  public void setActivationId(Integer activationId)
  {
    this.activationId = activationId;
  }
  
 /* public String getUsername()
  {
    return this.username;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }*/
  
  public Integer getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(Integer userId)
  {
    this.userId = userId;
  }
}
