package org.pw.foodordering.interfaces;

import java.util.Collection;
import javax.mail.MessagingException;
import org.pw.foodordering.elements.db.Order;
import org.pw.foodordering.elements.db.User;

public abstract interface FoodMailServerInterface
{
  public abstract void sendTestMessage();
  
  public abstract void sendActivationMessage(String paramString1, String paramString2);
  
  public abstract void sendOrderListToOrdersLeader(User paramUser1, User paramUser2, Collection<Order> paramCollection)
    throws MessagingException;
  
  public abstract void sendTest();
}
