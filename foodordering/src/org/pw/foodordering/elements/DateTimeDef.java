package org.pw.foodordering.elements;

import java.util.ArrayList;
import java.util.List;

public class DateTimeDef
{
  Integer hour;
  Integer minute;
  
  public Integer getHour()
  {
    return this.hour;
  }
  
  public void setHour(Integer hour)
  {
    this.hour = hour;
  }
  
  public Integer getMinute()
  {
    return this.minute;
  }
  
  public void setMinute(Integer minute)
  {
    this.minute = minute;
  }
  
  public List<Integer> getAllowedHours()
  {
    List<Integer> lis = new ArrayList();
    for (int a = 0; a < 24; a++) {
      lis.add(Integer.valueOf(a));
    }
    return lis;
  }
  
  public List<Integer> getAllowedMinutes()
  {
    List<Integer> lis = new ArrayList();
    for (int a = 0; a < 60; a++) {
      lis.add(Integer.valueOf(a));
    }
    return lis;
  }
}
