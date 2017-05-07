package org.pw.foodordering.editors;

import java.beans.PropertyEditorSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pw.foodordering.elements.db.DishDef;
import org.pw.foodordering.interfaces.DishDefinitionCrudInterface;
import org.pw.foodordering.interfaces.FoodOrderingInterface;

public class DishDefEditor
  extends PropertyEditorSupport
{
  private Log logger = LogFactory.getLog(getClass());
  private DishDefinitionCrudInterface foodOrdering;
  
  public DishDefEditor(FoodOrderingInterface foodOrdering)
  {
    this.foodOrdering = ((DishDefinitionCrudInterface)foodOrdering);
  }
  
  public void setAsText(String text)
    throws IllegalArgumentException
  {
    for (DishDef dishDef : this.foodOrdering.getAllDishDefs())
    {
      this.logger.debug("Checking def: " + dishDef.getName() + " against: " + text);
      if (dishDef.getName().equals(text)) {
        setValue(dishDef);
      }
    }
  }
}
