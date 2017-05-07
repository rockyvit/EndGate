package org.pw.foodordering.engine;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pw.foodordering.elements.OrderStatusType;
import org.pw.foodordering.elements.db.Activation;
import org.pw.foodordering.elements.db.Authority;
import org.pw.foodordering.elements.db.Dish;
import org.pw.foodordering.elements.db.DishDef;
import org.pw.foodordering.elements.db.Group;
import org.pw.foodordering.elements.db.Horder;
import org.pw.foodordering.elements.db.HorderElement;
import org.pw.foodordering.elements.db.OrderElement;
import org.pw.foodordering.elements.db.Restaurant;
import org.pw.foodordering.elements.db.RestaurantType;
import org.pw.foodordering.elements.db.User;
import org.pw.foodordering.elements.misc.SearchResults;
import org.pw.foodordering.exceptions.OperationCannotBeCompletedException;
import org.pw.foodordering.interfaces.ActivationCrudInterface;
import org.pw.foodordering.interfaces.AuthorityCrudInterface;
import org.pw.foodordering.interfaces.DishCrudInterface;
import org.pw.foodordering.interfaces.DishDefinitionCrudInterface;
import org.pw.foodordering.interfaces.FoodOrderingInterface;
import org.pw.foodordering.interfaces.GroupCrudInterface;
import org.pw.foodordering.interfaces.OrderCrudInterface;
import org.pw.foodordering.interfaces.RestaurantCrudInterface;
import org.pw.foodordering.interfaces.RestaurantTypeCrudInterface;
import org.pw.foodordering.interfaces.SearchInterface;
import org.pw.foodordering.interfaces.UserCrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class FoodOrderingHibernate
  implements FoodOrderingInterface, RestaurantCrudInterface, RestaurantTypeCrudInterface, UserCrudInterface, ActivationCrudInterface, AuthorityCrudInterface, OrderCrudInterface, GroupCrudInterface, DishCrudInterface, DishDefinitionCrudInterface, SearchInterface
{
  @Autowired
  private SessionFactory sessionFactory;
  private Log logger = LogFactory.getLog(getClass());
  
  public String getInfo()
  {
    return "This if FO service";
  }
  
  @Transactional(readOnly=true)
  public Collection<Restaurant> getRestaurantsWithAllData()
    throws DataAccessException
  {
    Collection<Restaurant> rests = this.sessionFactory.getCurrentSession().createCriteria(Restaurant.class).addOrder(org.hibernate.criterion.Order.asc("name")).list();
    
    Collection<Timestamp> dates = null;
    for (Restaurant rest : rests)
    {
      Hibernate.initialize(rest.getGroups());
      
      dates = getAllOrdersDistinctByOrderingDate(rest);
      rest.setOrderDates((List)dates);
    }
    return rests;
  }
  
  @Transactional(readOnly=true)
  public Collection<Restaurant> getRestaurantsOnly()
    throws DataAccessException
  {
    Collection<Restaurant> rests = this.sessionFactory.getCurrentSession().createCriteria(Restaurant.class).addOrder(org.hibernate.criterion.Order.asc("name")).list();
    
    Collection<Timestamp> dates = null;
    for (Restaurant rest : rests)
    {
      dates = getAllOrdersDistinctByOrderingDate(rest);
      rest.setOrderDates((List)dates);
    }
    return rests;
  }
  
  @Transactional(readOnly=true)
  public Restaurant getRestaurantByName(String restaurantName)
    throws DataAccessException
  {
    return (Restaurant)this.sessionFactory.getCurrentSession().createQuery("from Restaurant r where name = :name").setParameter("name", restaurantName).setMaxResults(1).uniqueResult();
  }
  
  @Transactional(readOnly=true)
  public Restaurant getRestaurantByRestId(Integer restId)
    throws DataAccessException
  {
    return (Restaurant)this.sessionFactory.getCurrentSession().createCriteria(Restaurant.class).add(Restrictions.eq("restId", restId)).setMaxResults(1).uniqueResult();
  }
  
  @Transactional
  @Secured({"ROLE_ADMIN"})
  public void storeRestaurant(Restaurant restaurant)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().merge(restaurant);
  }
  
  @Transactional
  @Secured({"ROLE_ADMIN"})
  public void removeRestaurant(Restaurant restaurant)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().delete(restaurant);
  }
  
  @Transactional
  @Secured({"ROLE_ADMIN"})
  public void updateRestaurant(Restaurant restaurant)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().merge(restaurant);
  }
  
  @Transactional(readOnly=true)
  @Secured({"ROLE_USER"})
  public Collection<RestaurantType> getRestaurantTypes()
    throws DataAccessException
  {
    return this.sessionFactory.getCurrentSession().createQuery("from RestaurantType rt order by rt.typeName").list();
  }
  
  @Transactional(readOnly=true)
  @Secured({"ROLE_USER"})
  public RestaurantType getRestaurantTypeByName(String restaurantTypeName)
    throws DataAccessException
  {
    return (RestaurantType)this.sessionFactory.getCurrentSession().createQuery("from RestaurantType rt where typeName = :typeName").setParameter("typeName", restaurantTypeName).setMaxResults(1).uniqueResult();
  }
  
  @Transactional
  @Secured({"ROLE_ADMIN"})
  public void removeRestaurantType(RestaurantType restaurantType)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().delete(restaurantType);
  }
  
  @Transactional
  @Secured({"ROLE_ADMIN"})
  public void storeRestaurantType(RestaurantType restaurantType)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().merge(restaurantType);
  }
  
  @Transactional
  @Secured({"ROLE_ADMIN"})
  public void updateRestaurantType(RestaurantType restaurantType)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().merge(restaurantType);
  }
  
  @Transactional
  @Secured({"ROLE_ADMIN"})
  public void importRestaurantMenu(String restaurantName, HashMap<String, HashMap<String, HashMap<String, Object>>> menu)
    throws DataAccessException
  {
    RestaurantType restType = getRestaurantTypeByName("DEFAULT");
    Restaurant rest = new Restaurant();
    rest.setName(restaurantName);
    rest.setRestType(restType);
    rest = (Restaurant)this.sessionFactory.getCurrentSession().merge(rest);
    
    Set<Group> groupSet = new HashSet();
    Set<Dish> dishSet = new HashSet();
    for (String groupKey : menu.keySet())
    {
      Group localGroup = new Group();
      localGroup.setGroupName(groupKey);
      localGroup.setRestaurant(rest);
      
      dishSet = new HashSet();
      HashMap<String, HashMap<String, Object>> dishHM = (HashMap)menu.get(groupKey);
      for (String dishKey : dishHM.keySet())
      {
        DishDef localDishDef = getDishDefByName(dishKey);
        String desc = "";
        Double price = Double.valueOf(0.0D);
        String info = "";
        HashMap<String, Object> detailsHM = (HashMap)dishHM.get(dishKey);
        for (String detKey : detailsHM.keySet()) {
          if (detKey.equals("price")) {
            price = (Double)detailsHM.get(detKey);
          } else if (detKey.equals("desc")) {
            desc = (String)detailsHM.get(detKey);
          } else if (detKey.equals("info")) {
            info = (String)detailsHM.get(detKey);
          }
        }
        if (localDishDef == null)
        {
          localDishDef = new DishDef();
          localDishDef.setName(dishKey);
          localDishDef.setDescription(desc);
          this.logger.debug("PIO: import dish def name: " + dishKey);
          this.sessionFactory.getCurrentSession().save(localDishDef);
        }
        Dish localDish = new Dish();
        localDish.setDetails(localDishDef);
        localDish.setPrice(price);
        localDish.setSpecyficInfo(info);
        localDish.setGroup(localGroup);
        dishSet.add(localDish);
      }
      localGroup.setDishes(dishSet);
      groupSet.add(localGroup);
    }
    rest.setGroups(groupSet);
  }
  
  @Transactional(readOnly=true)
  /*public User getUserByUserName(String first_name)
    throws DataAccessException
  {
	  return (User)this.sessionFactory.getCurrentSession().createQuery("from User u where first_name = :first_name").setParameter("first_name", first_name).setMaxResults(1).uniqueResult();
	  //return (User)this.sessionFactory.getCurrentSession().createQuery("from User u where login_id = :login_id").setParameter("login_id", login_id).setMaxResults(1).uniqueResult();
  }
  */
  public User getUserByMobileno(String phone)
  throws DataAccessException
{
  return (User)this.sessionFactory.getCurrentSession().createQuery("from User u where phone = :phone").setParameter("phone", phone).setMaxResults(1).uniqueResult();
}
  public User getUserByEmail(String login_id)
  throws DataAccessException
{
	  return (User)this.sessionFactory.getCurrentSession().createQuery("from User u where login_id = :login_id").setParameter("login_id", login_id).setMaxResults(1).uniqueResult();
	  //return (User)this.sessionFactory.getCurrentSession().createQuery("from User u where first_name = :name").setParameter("first_name", first_name).setMaxResults(1).uniqueResult();
}
  
  
  @SuppressWarnings("unchecked")
@Transactional(readOnly=true)
  public Collection<User> getAllUsers()
    throws DataAccessException
  {
    return this.sessionFactory.getCurrentSession().createQuery("from User u order by u.phone").list();
  }
  
  @Transactional
  public void storeUser(User user)
    throws DataAccessException
  {
	  System.out.println("Hello we are here");
    this.sessionFactory.getCurrentSession().merge(user);
    System.out.println("we are here to see where we are");
  }
  
  @Transactional
  public void updateUser(User user)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().merge(user);
  }
  
  @Transactional
  @Secured({"ROLE_ADMIN"})
  public void deleteUser(User user)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().delete(user);
  }
  
 /* @Transactional
  public void removeActivation(Activation activation)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().delete(activation);
  }*/
  
 /* @Transactional
  public void storeActivation(Activation activation)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().merge(activation);
  }*/
 
  
 /* @Transactional(readOnly=true)
  public Activation getActivationByKey(String activationKey)
    throws DataAccessException
  {
    return (Activation)this.sessionFactory.getCurrentSession().createQuery("from Activation where activationCode = :activationCode").setParameter("activationCode", activationKey).setMaxResults(1).uniqueResult();
  }
  */
  @Transactional
  public void removeEmail_secrete(User user)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().delete(user);
  }
  @Transactional
  public void storeEmail_secrete(User user)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().merge(user);
  }
  @Transactional(readOnly=true)
  public Activation getActivationByKey(String activationKey)
    throws DataAccessException
  {
    return (Activation)this.sessionFactory.getCurrentSession().createQuery("from User where email_secret_key = :email_secret_key").setParameter("email_secret_key", activationKey).setMaxResults(1).uniqueResult();
  }
  
  @Transactional
  @Secured({"ROLE_ADMIN"})
  public void removeAuthority(Authority authority)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().delete(authority);
  }
  
  @Transactional
  public void storeAuthority(Authority authority)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().merge(authority);
  }
  
  @Transactional(readOnly=true)
  @Secured({"ROLE_USER"})
  public Collection<Horder> getOldOrdersByUsername(String username)
    throws DataAccessException
  {
    return this.sessionFactory.getCurrentSession().createCriteria(Horder.class).addOrder(org.hibernate.criterion.Order.desc("orderingDate")).createCriteria("user").add(Restrictions.eq("username", username)).list();
  }
  
  @Transactional
  @Secured({"ROLE_USER"})
  public void removeOrder(org.pw.foodordering.elements.db.Order order)
    throws DataAccessException
  {
   // order.getUser().getLogin_id().remove(order);
    this.sessionFactory.getCurrentSession().delete(order);
  }
  
  @Transactional
  public void removeOrderLocal(org.pw.foodordering.elements.db.Order order)
    throws DataAccessException
  {
   // order.getUser().getOrders().remove(order);
    this.sessionFactory.getCurrentSession().delete(order);
  }
  
  public void moveToHistory(org.pw.foodordering.elements.db.Order order)
    throws DataAccessException
  {
    Horder horder = new Horder();
    horder.setCreationDate(order.getCreationDate());
    horder.setOrderingDate(order.getOrderingDate());
    horder.setUser(order.getUser());
    horder.setRestaurantName(order.getRestaurant().getName());
    
    Collection<HorderElement> horderElements = new HashSet();
    for (OrderElement orderElement : order.getOrderElements())
    {
      HorderElement horderElem = new HorderElement();
      horderElem.setDishName(orderElement.getDish().getDetails().getName());
      horderElem.setHorder(horder);
      horderElem.setPrice(orderElement.getDish().getPrice());
      horderElements.add(horderElem);
    }
    horder.setHorderElements(horderElements);
    this.sessionFactory.getCurrentSession().save(horder);
    
    removeOrder(order);
  }
  
  @Transactional
  @Secured({"ROLE_USER"})
  public void removeOrderById(Integer orderId)
    throws DataAccessException, OperationCannotBeCompletedException
  {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String name = "NONE";
    if (auth != null)
    {
      if ((auth.getPrincipal() instanceof UserDetails)) {
        name = ((UserDetails)auth.getPrincipal()).getUsername();
      } else {
        name = auth.getPrincipal().toString();
      }
      org.pw.foodordering.elements.db.Order order = (org.pw.foodordering.elements.db.Order)this.sessionFactory.getCurrentSession().createCriteria(org.pw.foodordering.elements.db.Order.class).add(Restrictions.eq("orderId", orderId)).setMaxResults(1).uniqueResult();
      if (!order.getUser().getLogin_id().equals(name)) {
        throw new OperationCannotBeCompletedException("No such order ID!");
      }
      if ((order != null) && (order.getStatus().intValue() != OrderStatusType.CLOSED.getId()))
      {
       // order.getUser().getOrders().remove(order);
        this.sessionFactory.getCurrentSession().delete(order);
      }
      else
      {
        throw new OperationCannotBeCompletedException("Order is allready closed!");
      }
    }
  }
  
  @Transactional
  @Secured({"ROLE_USER"})
  public void removeOrderElement(OrderElement orderElement)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().delete(orderElement);
  }
  
  @Transactional
  @Secured({"ROLE_USER"})
  public void storeOrder(org.pw.foodordering.elements.db.Order order)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().merge(order);
  }
  
  @Transactional
  @Secured({"ROLE_USER"})
  public void storeOrderElement(OrderElement orderElement)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().merge(orderElement);
  }
  
  @Transactional
  public void updateOrder(org.pw.foodordering.elements.db.Order order)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().merge(order);
  }
  
  @Transactional
  @Secured({"ROLE_USER"})
  public void updateOrderElement(OrderElement orderElement)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().merge(orderElement);
  }
  
  @Transactional(readOnly=true)
  @Secured({"ROLE_USER"})
  public List<org.pw.foodordering.elements.db.Order> getNewOrders(String username)
    throws DataAccessException
  {
    List<org.pw.foodordering.elements.db.Order> retList = this.sessionFactory.getCurrentSession().createCriteria(org.pw.foodordering.elements.db.Order.class).addOrder(org.hibernate.criterion.Order.asc("orderingDate")).createCriteria("user").add(Restrictions.eq("username", username)).list();
    for (org.pw.foodordering.elements.db.Order thisOrder : retList)
    {
      Double cashToPay = (Double)this.sessionFactory.getCurrentSession().createQuery("SELECT sum(d.price) FROM OrderElement oe, Dish d where d.dishId = oe.dish.dishId and oe.order.orderId = :orderId").setParameter("orderId", thisOrder.getOrderId()).setMaxResults(1).uniqueResult();
      
      thisOrder.setCashToPay(cashToPay);
    }
    return retList;
  }
  
  @Transactional
  @Secured({"ROLE_USER"})
  public void removeOrderElementById(Integer orderElementId)
    throws DataAccessException, OperationCannotBeCompletedException
  {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = "NONE";
    if (auth != null)
    {
      if ((auth.getPrincipal() instanceof UserDetails)) {
        username = ((UserDetails)auth.getPrincipal()).getUsername();
      } else {
        username = auth.getPrincipal().toString();
      }
      OrderElement orderElement = (OrderElement)this.sessionFactory.getCurrentSession().createQuery("from OrderElement oe where orderElementId = :orderElementId").setParameter("orderElementId", orderElementId).setMaxResults(1).uniqueResult();
      if (orderElement != null)
      {
        if (!orderElement.getOrder().getUser().getLogin_id().equals(username)) {
          throw new OperationCannotBeCompletedException("No such order element!");
        }
        org.pw.foodordering.elements.db.Order currentOrder = orderElement.getOrder();
        if (currentOrder.getStatus().intValue() != OrderStatusType.CLOSED.getId())
        {
          currentOrder.getOrderElements().remove(orderElement);
          this.sessionFactory.getCurrentSession().delete(orderElement);
          if (currentOrder.getOrderElements().isEmpty()) {
            removeOrderLocal(currentOrder);
          }
        }
        else
        {
          throw new OperationCannotBeCompletedException("Order is allready closed!");
        }
      }
    }
  }
  
  @Transactional
  public void removeGroup(Group group)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().delete(group);
  }
  
  @Transactional
  public void storeGroup(Group group)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().merge(group);
  }
  
  @Transactional
  public void updateGruop(Group group)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().merge(group);
  }
  
  @Transactional(readOnly=true)
  public Group getGroupById(Integer groupId)
    throws DataAccessException
  {
    return (Group)this.sessionFactory.getCurrentSession().createQuery("from Group g where groupId = :groupId").setParameter("groupId", groupId).setMaxResults(1).uniqueResult();
  }
  
  @Transactional(readOnly=true)
  public Collection<Group> getGroupsByRestaurantId(Integer restId)
    throws DataAccessException
  {
    return this.sessionFactory.getCurrentSession().createCriteria(Group.class).add(Restrictions.eq("restaurant.restId", restId)).addOrder(org.hibernate.criterion.Order.asc("groupName")).list();
  }
  
  @Transactional
  public void removeGroupById(Integer groupId)
    throws DataAccessException
  {
    Group group = getGroupById(groupId);
    if (group != null) {
      this.sessionFactory.getCurrentSession().delete(group);
    }
  }
  
  @Transactional
  public void orderDish(User user, Dish dish)
    throws DataAccessException
  {}
  
  @Transactional
  public void removeDish(Dish dish)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().delete(dish);
  }
  
  @Transactional
  public void storeDish(Dish dish)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().merge(dish);
  }
  
  @Transactional
  public void updateDish(Dish dish)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().merge(dish);
  }
  
  @Transactional
  public void removeDishById(Integer dishId)
    throws DataAccessException
  {
    Dish dish = (Dish)this.sessionFactory.getCurrentSession().createCriteria(Dish.class).add(Restrictions.eq("dishId", dishId)).setMaxResults(1).uniqueResult();
    if (dish != null)
    {
      dish.getGroup().getDishes().remove(dish);
      this.sessionFactory.getCurrentSession().delete(dish);
    }
  }
  
  @Transactional
  public Dish getDishByDishId(Integer dishId)
    throws DataAccessException
  {
    return (Dish)this.sessionFactory.getCurrentSession().createCriteria(Dish.class).add(Restrictions.eq("dishId", dishId)).setMaxResults(1).uniqueResult();
  }
  
  @Transactional(readOnly=true)
  public Collection<Dish> getDishByGroupId(Integer groupId)
    throws DataAccessException
  {
    return this.sessionFactory.getCurrentSession().createCriteria(Dish.class).add(Restrictions.eq("group.groupId", groupId)).createCriteria("details").addOrder(org.hibernate.criterion.Order.asc("name")).list();
  }
  
  @Transactional
  public void orderDishForUser(String username, Integer dishId)
    throws DataAccessException, OperationCannotBeCompletedException
  {
    User user = (User)this.sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("username", username)).setMaxResults(1).uniqueResult();
    
   /*Time userDefault = user.getDefaultDeliveryTime();
    Timestamp defaultDate = new Timestamp(System.currentTimeMillis());
    defaultDate.setHours(userDefault.getHours());
    defaultDate.setMinutes(userDefault.getMinutes());
    orderDishForUser(username, dishId, defaultDate);*/
  }
  
  @Transactional
  public void orderDishForUser(String username, Integer dishId, Timestamp orderingDate)
    throws DataAccessException, OperationCannotBeCompletedException
  {
    this.logger.debug("HBPIO: 1 " + username);
    User user = (User)this.sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("username", username)).setMaxResults(1).uniqueResult();
    
    this.logger.debug("HBPIO: 2 " + user);
    Dish dish = (Dish)this.sessionFactory.getCurrentSession().createCriteria(Dish.class).add(Restrictions.eq("dishId", dishId)).setMaxResults(1).uniqueResult();
    
    this.logger.debug("HBPIO: 3 " + dish);
    if ((user != null) && (dish != null))
    {
      this.logger.debug("HBPIO: 4");
      org.pw.foodordering.elements.db.Order order = (org.pw.foodordering.elements.db.Order)this.sessionFactory.getCurrentSession().createCriteria(org.pw.foodordering.elements.db.Order.class).add(Restrictions.eq("user.userId", user.getLogin_id())).add(Restrictions.eq("orderingDate", orderingDate)).setMaxResults(1).createCriteria("restaurant").add(Restrictions.eq("restId", dish.getGroup().getRestaurant().getRestId())).uniqueResult();
      
      this.logger.debug("HBPIO: 5 " + order);
      if (order == null)
      {
        this.logger.debug("HBPIO: 6 -> null");
        Date currentTime = new Date(System.currentTimeMillis());
        if (orderingDate.before(currentTime)) {
          throw new OperationCannotBeCompletedException("Cannot create/attach to order in the past.");
        }
        order = new org.pw.foodordering.elements.db.Order();
        order.setUser(user);
        order.setStatus(Integer.valueOf(0));
        order.setCreationDate(new Timestamp(System.currentTimeMillis()));
        order.setOrderingDate(orderingDate);
        order.setRestaurant(dish.getGroup().getRestaurant());
        order.setStatus(Integer.valueOf(OrderStatusType.CONSTRUCTED.getId()));
       // user.getOrders().add(order);
        this.logger.debug("HBPIO: 7 " + order);
      }
      else if (order.getStatus().intValue() == OrderStatusType.CLOSED.getId())
      {
        this.logger.error("Cannot attach to closed order !");
        throw new OperationCannotBeCompletedException("Cannot attach to closed order.");
      }
      this.logger.debug("HBPIO: 8");
      OrderElement orderElement = new OrderElement();
      orderElement.setDish(dish);
      orderElement.setOrder(order);
      
      this.logger.debug("HBPIO: 9 " + orderElement);
      
      Collection<OrderElement> oElems = order.getOrderElements();
      this.logger.debug("HBPIO: 10 " + oElems);
      if (oElems == null)
      {
        this.logger.debug("HBPIO: 11 -> null");
        oElems = new HashSet();
      }
      this.logger.debug("HBPIO: 12 " + oElems);
      oElems.add(orderElement);
      order.setOrderElements(oElems);
    }
  }
  
  @Transactional(readOnly=true)
  public Collection<Timestamp> getAllOrdersDistinctByOrderingDate(Integer dishId)
    throws DataAccessException
  {
    Dish dish = (Dish)this.sessionFactory.getCurrentSession().createCriteria(Dish.class).add(Restrictions.eq("dishId", dishId)).setMaxResults(1).uniqueResult();
    
    return getAllOrdersDistinctByOrderingDate(dish.getGroup().getRestaurant());
  }
  
  @Transactional(readOnly=true)
  public Collection<Timestamp> getAllOrdersDistinctByOrderingDate(Restaurant rest)
    throws DataAccessException
  {
    Collection<Timestamp> ts = this.sessionFactory.getCurrentSession().createCriteria(org.pw.foodordering.elements.db.Order.class).add(Restrictions.eq("status", Integer.valueOf(OrderStatusType.CONSTRUCTED.getId()))).setProjection(Projections.groupProperty("orderingDate")).addOrder(org.hibernate.criterion.Order.asc("orderingDate")).createCriteria("restaurant").add(Restrictions.eq("restId", rest.getRestId())).list();
    
    return ts;
  }
  
  public Collection<org.pw.foodordering.elements.db.Order> getAllOrdersDistinctByRestaurantAndOrderingDate(Restaurant rest, Timestamp orderingDate)
    throws DataAccessException
  {
    return this.sessionFactory.getCurrentSession().createCriteria(org.pw.foodordering.elements.db.Order.class).add(Restrictions.and(Restrictions.eq("orderingDate", orderingDate), Restrictions.ne("status", Integer.valueOf(OrderStatusType.CLOSED.getId())))).createCriteria("restaurant").add(Restrictions.eq("restId", rest.getRestId())).list();
  }
  
  @Transactional
  public Collection<Collection<org.pw.foodordering.elements.db.Order>> getAllOrdersBeforeDate(Timestamp currentDate)
    throws DataAccessException
  {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    this.logger.debug("PIO: Current date: " + formatter.format(currentDate));
    Collection<Collection<org.pw.foodordering.elements.db.Order>> result = new HashSet();
    Collection<Restaurant> restaurants = getRestaurantsOnly();
    Collection<Timestamp> orderDates = null;
    Restaurant rest;
    this.logger.debug("PIO: no of restaurants: " + restaurants.size());
    for (Iterator i$ = restaurants.iterator(); i$.hasNext();)
    {
      rest = (Restaurant)i$.next();
      this.logger.debug("PIO: Checking rest: " + rest.getName());
      orderDates = new HashSet();
      for (Timestamp restDates : rest.getOrderDates())
      {
        this.logger.debug("PIO: XX " + restDates.getTime() + " milis time: " + formatter.format(restDates) + " date: " + currentDate.getTime() + " -> " + formatter.format(currentDate));
        if (restDates.before(currentDate))
        {
          this.logger.debug("PIO: Adding time: " + formatter.format(restDates) + " is before " + formatter.format(currentDate));
          orderDates.add(restDates);
        }
      }
      this.logger.debug("PIO: order times for rst: " + orderDates.size());
      if (!orderDates.isEmpty()) {
        for (Timestamp dateIn : orderDates)
        {
          Collection<org.pw.foodordering.elements.db.Order> orders = getAllOrdersDistinctByRestaurantAndOrderingDate(rest, dateIn);
          if (!orders.isEmpty())
          {
            for (org.pw.foodordering.elements.db.Order order : orders)
            {
              this.logger.debug("PIO: Order status " + order.getStatus());
              order.setStatus(Integer.valueOf(OrderStatusType.CLOSED.getId()));
            }
            result.add(orders);
          }
        }
      }
    }
   // Restaurant rest;
    this.logger.debug("PIO: size of result from getAllOrdersBeforeTime is: " + result.size());
    return result;
  }
  
  @Transactional
  public void removeDishDef(DishDef dishDef)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().delete(dishDef);
  }
  
  @Transactional
  public void storeDishDef(DishDef dishDef)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().merge(dishDef);
  }
  
  @Transactional
  public void updateDishDef(DishDef dishDef)
    throws DataAccessException
  {
    this.sessionFactory.getCurrentSession().merge(dishDef);
  }
  
  @Transactional(readOnly=true)
  public Collection<DishDef> getAllDishDefs()
    throws DataAccessException
  {
    return this.sessionFactory.getCurrentSession().createCriteria(DishDef.class).addOrder(org.hibernate.criterion.Order.asc("name")).list();
  }
  
  @Transactional
  public DishDef getDishDefById(Integer dishDefId)
    throws DataAccessException
  {
    return (DishDef)this.sessionFactory.getCurrentSession().createCriteria(DishDef.class).add(Restrictions.eq("dishDefId", dishDefId)).setMaxResults(1).uniqueResult();
  }
  
  @Transactional
  public void removeDishDefById(Integer dishDefId)
    throws DataAccessException
  {
    DishDef dishDef = (DishDef)this.sessionFactory.getCurrentSession().createCriteria(DishDef.class).add(Restrictions.eq("dishDefId", dishDefId)).setMaxResults(1).uniqueResult();
    if (dishDef != null) {
      this.sessionFactory.getCurrentSession().delete(dishDef);
    }
  }
  
  @Transactional(readOnly=true)
  public DishDef getDishDefByName(String dishDefName)
    throws DataAccessException
  {
    return (DishDef)this.sessionFactory.getCurrentSession().createCriteria(DishDef.class).add(Restrictions.eq("name", dishDefName)).setMaxResults(1).uniqueResult();
  }
  
  @Transactional(readOnly=true)
  public SearchResults searchObjectByString(String value)
    throws DataAccessException
  {
    List<Group> groups = this.sessionFactory.getCurrentSession().createCriteria(Group.class).add(Restrictions.like("groupName", value, MatchMode.ANYWHERE)).list();
    
    this.logger.debug("SEARCH: " + groups.size() + " groups found for " + value);
    
    String query = "select d from Dish d, DishDef dd where d.details.dishDefId = dd.dishDefId and (d.specyficInfo like :dInfo or dd.name like :ddName or dd.description like :ddDesc)";
    
    List<Dish> dishes = this.sessionFactory.getCurrentSession().createQuery(query).setParameter("dInfo", "%" + value + "%").setParameter("ddName", "%" + value + "%").setParameter("ddDesc", "%" + value + "%").list();
    
    this.logger.debug("SEARCH: " + dishes.size() + " dishes found for " + value);
    
    SearchResults sr = new SearchResults();
    sr.setDishes(dishes);
    sr.setGroups(groups);
    
    return sr;
  }



}
