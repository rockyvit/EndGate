package org.pw.foodordering.elements;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pw.foodordering.elements.db.Restaurant;
import org.pw.foodordering.elements.db.RestaurantType;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RestaurantValidator
  implements Validator
{
  protected final Log logger = LogFactory.getLog(getClass());
  
  public boolean supports(Class paramClass)
  {
    return RestaurantType.class.equals(paramClass);
  }
  
  public void validate(Object object, Errors errors)
  {
    Restaurant restaurant = (Restaurant)object;
    if (restaurant == null)
    {
      errors.rejectValue("nameame", "error.not-specified", null, "Value req");
    }
    else
    {
      if (restaurant.getName().length() <= 0) {
        errors.rejectValue("name", "error.name-too-short", new Object[] { new Integer(1) }, "Name too short. Min: {0}");
      }
      if (restaurant.getName().length() > 128) {
        errors.rejectValue("name", "error.name-too-long", new Object[] { new Integer(128) }, "Name too long. Max: {0}");
      }
    }
    if (restaurant.getRestType() == null) {
      errors.rejectValue("type", "error.wrong-type", null, "Type = null");
    }
  }
}
