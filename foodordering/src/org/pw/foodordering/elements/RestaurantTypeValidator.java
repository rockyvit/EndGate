package org.pw.foodordering.elements;

import java.util.Collection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pw.foodordering.elements.db.RestaurantType;
import org.pw.foodordering.interfaces.RestaurantTypeCrudInterface;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RestaurantTypeValidator
  implements Validator
{
  private RestaurantTypeCrudInterface foodOrdering;
  protected final Log logger = LogFactory.getLog(getClass());
  
  public boolean supports(Class paramClass)
  {
    return RestaurantType.class.equals(paramClass);
  }
  
  public RestaurantTypeValidator(RestaurantTypeCrudInterface foodOrdering)
  {
    this.foodOrdering = foodOrdering;
  }
  
  public void validate(Object object, Errors errors)
  {
    RestaurantType typeValue = (RestaurantType)object;
    if (typeValue == null)
    {
      errors.rejectValue("typeName", "error.not-specified", null, "Value req");
    }
    else
    {
      if (typeValue.getTypeName().length() <= 0) {
        errors.rejectValue("typeName", "error.type-too-short", new Object[] { new Integer(1) }, "Name too short {0}");
      }
      if (typeValue.getTypeName().length() > 128) {
        errors.rejectValue("typeName", "error.type-too-long", new Object[] { new Integer(128) }, "Name too long {0}");
      }
    }
    Collection<RestaurantType> typesDefined = this.foodOrdering.getRestaurantTypes();
    for (RestaurantType restType : typesDefined) {
      if (restType.getTypeName().trim().equals(typeValue.getTypeName().trim())) {
        errors.rejectValue("typeName", "error.type-exists", new Object[] { typeValue.getTypeName().trim() }, "Type name {0} allready exists.");
      }
    }
  }
}
