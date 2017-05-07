package org.pw.foodordering.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.pw.foodordering.editors.DishDefEditor;
import org.pw.foodordering.editors.RestaurantTypeEditor;
import org.pw.foodordering.editors.TimestampEditor;
import org.pw.foodordering.elements.db.DishDef;
import org.pw.foodordering.elements.db.RestaurantType;
import org.pw.foodordering.interfaces.FoodOrderingInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class FoodOrderingBindingInitializer
  implements WebBindingInitializer
{
  @Autowired
  private FoodOrderingInterface foodOrdering;
  
  public void initBinder(WebDataBinder binder, WebRequest request)
  {
    binder.registerCustomEditor(RestaurantType.class, new RestaurantTypeEditor(this.foodOrdering));
    binder.registerCustomEditor(DishDef.class, new DishDefEditor(this.foodOrdering));
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    dateFormat.setLenient(true);
    
    binder.registerCustomEditor(Timestamp.class, new TimestampEditor());
    binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor("true", "false", false));
  }
}
