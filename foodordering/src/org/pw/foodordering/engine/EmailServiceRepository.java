package org.pw.foodordering.engine;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pw.foodordering.elements.OrderStatusType;
import org.pw.foodordering.elements.db.Dish;
import org.pw.foodordering.elements.db.DishDef;
import org.pw.foodordering.elements.db.Order;
import org.pw.foodordering.elements.db.OrderElement;
import org.pw.foodordering.elements.db.User;
import org.pw.foodordering.interfaces.EmailServiceRepositoryInterface;
import org.pw.foodordering.interfaces.FoodMailServerInterface;
import org.pw.foodordering.interfaces.FoodOrderingInterface;
import org.pw.foodordering.interfaces.OrderCrudInterface;
import org.springframework.stereotype.Repository;

@Repository
public class EmailServiceRepository
  implements EmailServiceRepositoryInterface
{
  private FoodOrderingInterface foodOrdering;
  private FoodMailServerInterface foodMailServer;
  private Log logger = LogFactory.getLog(getClass());
  
  public void setFoodOrdering(FoodOrderingInterface foodordering)
  {
    this.foodOrdering = foodordering;
  }
  
  public void setFoodMailServer(FoodMailServerInterface foodMailServer)
  {
    this.foodMailServer = foodMailServer;
  }
  
  public User selectLeader(Collection<User> users)
  {
    if (users.isEmpty()) {
      return null;
    }
    Random rnd = new Random(System.currentTimeMillis());
    Integer pOrder = Integer.valueOf(rnd.nextInt(1048576) % users.size());
    Integer cnt = Integer.valueOf(0);
    Integer localInteger1;
    Integer localInteger2;
    for (Iterator i$ = users.iterator(); i$.hasNext(); localInteger2 = cnt = Integer.valueOf(cnt.intValue() + 1))
    {
      User userIter = (User)i$.next();
      if (pOrder == cnt) {
        return userIter;
      }
      localInteger1 = cnt;
    }
    this.logger.error("This is a bug in leader selection !!!");
    return null;
  }
  
  public void checkOrdersAndSend()
  {
    Timestamp currentDate = new Timestamp(System.currentTimeMillis());
    
    Collection<Collection<Order>> ordersSet = ((OrderCrudInterface)this.foodOrdering).getAllOrdersBeforeDate(currentDate);
    for (Collection<Order> ords : ordersSet)
    {
      this.logger.debug("ORDERS: " + ords.size());
      for (Order orders : ords)
      {
        this.logger.debug("OR: user->" + orders.getUser().getLogin_id());
        this.logger.debug("OR: oDate->" + orders.getOrderingDate());
        for (OrderElement oe : orders.getOrderElements()) {
          this.logger.debug("OE: dishname->" + oe.getDish().getDetails().getName());
        }
      }
    }
    if (!ordersSet.isEmpty())
    {
      this.logger.debug("PIO: orders Set:" + ordersSet);
      for (Collection<Order> order : ordersSet)
      {
        this.logger.debug("PIO: sending...");
        Collection<User> users = new HashSet();
        for (Order orderIter : order) {
          users.add(orderIter.getUser());
        }
        for (;;)
        {
          User leader = selectLeader(users);
          if (leader == null)
          {
            this.logger.error("LEADER = null !!!");
            for (int a = order.size() - 1; a >= 0; a--)
            {
              Order ord = (Order)((List)order).get(a);
              this.logger.error("NEXT !!!");
              ord.setStatus(Integer.valueOf(OrderStatusType.ERROR.getId()));
              ((OrderCrudInterface)this.foodOrdering).updateOrder(ord);
              this.logger.error("After upadate !!!");
              
              order.remove(ord);
            }
            this.logger.error("END - err !!!");
            break;
          }
          users.remove(leader);
          
          Integer sendReties = Integer.valueOf(5);
          while (sendReties.intValue() > 0) {
            try
            {
              this.foodMailServer.sendOrderListToOrdersLeader(leader, leader, order);
              this.logger.debug("Sending to LEADER " + leader.getLogin_id() + " OK.");
            }
            catch (Exception e)
            {
              this.logger.error("Sending to LEADER " + leader.getLogin_id() + " failed.");
              this.logger.error(e);
              Integer localInteger1 = sendReties;Integer localInteger2 = sendReties = Integer.valueOf(sendReties.intValue() - 1);
              try
              {
                if (sendReties.intValue() > 0)
                {
                  this.logger.debug("Waiting 5 s before retrying...");
                  Thread.sleep(5000L);
                }
              }
              catch (InterruptedException e1)
              {
                e1.printStackTrace();
              }
            }
          }
          if (sendReties.intValue() > 0)
          {
            users.clear();
            for (Order orderIter : order) {
              if (orderIter.getUser().getLogin_id() != leader.getLogin_id()) {
                users.add(orderIter.getUser());
              }
            }
            for (User currentUser : users)
            {
              sendReties = Integer.valueOf(3);
              while (sendReties.intValue() > 0) {
                try
                {
                  this.foodMailServer.sendOrderListToOrdersLeader(currentUser, leader, order);
                  this.logger.debug("Sending to USER " + currentUser.getLogin_id() + " OK");
                }
                catch (Exception e)
                {
                  this.logger.error("Sending to USER " + currentUser.getLogin_id() + " failed.");
                  this.logger.error(e);
                  Integer localInteger3 = sendReties;Integer localInteger4 = sendReties = Integer.valueOf(sendReties.intValue() - 1);
                  try
                  {
                    if (sendReties.intValue() > 0)
                    {
                      this.logger.debug("Waiting 3 s before retrying...");
                      Thread.sleep(3000L);
                    }
                  }
                  catch (InterruptedException e1)
                  {
                    e1.printStackTrace();
                  }
                }
              }
            }
            for (int a = order.size() - 1; a >= 0; a--)
            {
              Order ord = (Order)((List)order).get(a);
              ((OrderCrudInterface)this.foodOrdering).moveToHistory(ord);
              order.remove(ord);
            }
            this.logger.debug("Sending finished.");
            break;
          }
        }
      }
    }
  }
}
