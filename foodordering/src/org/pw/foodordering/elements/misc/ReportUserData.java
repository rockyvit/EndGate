package org.pw.foodordering.elements.misc;

import java.util.Collection;
import org.pw.foodordering.elements.db.OrderElement;

public class ReportUserData
{
  String login_id;
  Collection<OrderElement> orderElements;
  
  /*public String getUsername()
  {
    return this.username;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }*/
  
  public Collection<OrderElement> getOrderElements()
  {
    return this.orderElements;
  }
  
  public String getLogin_id() {
	return login_id;
}

public void setLogin_id(String login_id) {
	this.login_id = login_id;
}

public void setOrderElements(Collection<OrderElement> orderElements)
  {
    this.orderElements = orderElements;
  }
}
