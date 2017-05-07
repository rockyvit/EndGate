package org.pw.foodordering.editors;

import java.beans.PropertyEditorSupport;
import java.sql.Time;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DeliveryTimeEditor
  extends PropertyEditorSupport
{
  private Log logger = LogFactory.getLog(getClass());
  
  public void setAsText(String text)
    throws IllegalArgumentException
  {
    String[] parts = text.split(":");
    Integer hour = Integer.valueOf(parts[0]);
    Integer minute = Integer.valueOf(parts[1]);
    
    Time time = new Time(hour.intValue(), minute.intValue(), 0);
    setValue(time);
  }
}
