package org.pw.foodordering.interfaces;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import org.pw.foodordering.elements.db.Horder;
import org.pw.foodordering.elements.db.Order;
import org.pw.foodordering.elements.db.OrderElement;
import org.pw.foodordering.elements.db.Restaurant;
import org.pw.foodordering.exceptions.OperationCannotBeCompletedException;
import org.springframework.dao.DataAccessException;

public abstract interface OrderCrudInterface
{
  public abstract Collection<Horder> getOldOrdersByUsername(String paramString)
    throws DataAccessException;
  
  public abstract List<Order> getNewOrders(String paramString)
    throws DataAccessException;
  
  public abstract Collection<Timestamp> getAllOrdersDistinctByOrderingDate(Integer paramInteger)
    throws DataAccessException;
  
  public abstract Collection<Timestamp> getAllOrdersDistinctByOrderingDate(Restaurant paramRestaurant)
    throws DataAccessException;
  
  public abstract Collection<Order> getAllOrdersDistinctByRestaurantAndOrderingDate(Restaurant paramRestaurant, Timestamp paramTimestamp)
    throws DataAccessException;
  
  public abstract Collection<Collection<Order>> getAllOrdersBeforeDate(Timestamp paramTimestamp)
    throws DataAccessException;
  
  public abstract void removeOrderById(Integer paramInteger)
    throws DataAccessException, OperationCannotBeCompletedException;
  
  public abstract void removeOrder(Order paramOrder)
    throws DataAccessException;
  
  public abstract void removeOrderLocal(Order paramOrder)
    throws DataAccessException;
  
  public abstract void updateOrder(Order paramOrder)
    throws DataAccessException;
  
  public abstract void storeOrder(Order paramOrder)
    throws DataAccessException;
  
  public abstract void moveToHistory(Order paramOrder)
    throws DataAccessException;
  
  public abstract void storeOrderElement(OrderElement paramOrderElement)
    throws DataAccessException;
  
  public abstract void removeOrderElement(OrderElement paramOrderElement)
    throws DataAccessException;
  
  public abstract void removeOrderElementById(Integer paramInteger)
    throws DataAccessException, OperationCannotBeCompletedException;
  
  public abstract void updateOrderElement(OrderElement paramOrderElement)
    throws DataAccessException;
  
  public abstract void orderDishForUser(String paramString, Integer paramInteger, Timestamp paramTimestamp)
    throws DataAccessException, OperationCannotBeCompletedException;
  
  public abstract void orderDishForUser(String paramString, Integer paramInteger)
    throws DataAccessException, OperationCannotBeCompletedException;
}
