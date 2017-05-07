package org.pw.foodordering.editors;

import java.beans.PropertyEditorSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pw.foodordering.elements.db.RestaurantType;
import org.pw.foodordering.interfaces.FoodOrderingInterface;
import org.pw.foodordering.interfaces.RestaurantTypeCrudInterface;

public class RestaurantTypeEditor
  extends PropertyEditorSupport
{
  private Log logger = LogFactory.getLog(getClass());
  private FoodOrderingInterface foodOrdering;
  
  public RestaurantTypeEditor(FoodOrderingInterface foodOrdering)
  {
    this.foodOrdering = foodOrdering;
  }
  
  public void setAsText(String text)
    throws IllegalArgumentException
  {
    for (RestaurantType type : ((RestaurantTypeCrudInterface)this.foodOrdering).getRestaurantTypes())
    {
      this.logger.debug("Checking type: " + type.getTypeName() + " against: " + text);
      if (type.getTypeName().equals(text)) {
        setValue(type);
      }
    }
  }
}
