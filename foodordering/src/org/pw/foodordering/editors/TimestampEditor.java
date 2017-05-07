package org.pw.foodordering.editors;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TimestampEditor
  extends PropertyEditorSupport
{
  private Log logger = LogFactory.getLog(getClass());
  
  public void setAsText(String text)
    throws IllegalArgumentException
  {
    String[] parts = text.split(" ");
    String[] date = parts[0].split("-");
    String[] time = parts[1].split(":");
    
    Integer year = null;Integer month = null;Integer day = null;
    Integer hour = null;Integer minute = null;
    Timestamp ts = new Timestamp(0L);
    try
    {
      year = Integer.valueOf(Integer.parseInt(date[0]));
      month = Integer.valueOf(Integer.parseInt(date[1]));
      day = Integer.valueOf(Integer.parseInt(date[2]));
      hour = Integer.valueOf(Integer.parseInt(time[0]));
      minute = Integer.valueOf(Integer.parseInt(time[1]));
    }
    catch (NumberFormatException nfe)
    {
      this.logger.error(nfe);
      setValue(ts);
      return;
    }
    Calendar cal = new GregorianCalendar();
    cal.set(year.intValue(), month.intValue() - 1, day.intValue(), hour.intValue(), minute.intValue());
    cal.set(13, 0);
    cal.set(14, 0);
    ts = new Timestamp(cal.getTimeInMillis());
    
    setValue(ts);
  }
}
