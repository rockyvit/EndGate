package org.pw.foodordering.elements.misc;

import java.sql.Timestamp;
import java.util.Collection;

public class ReportData
{
  Timestamp ts;
  String timeRemaining;
  String restName;
  Double totalCash;
  Integer usersCount;
  Collection<ReportUserData> details;
  
  public Timestamp getTs()
  {
    return this.ts;
  }
  
  public void setTs(Timestamp ts)
  {
    this.ts = ts;
  }
  
  public String getTimeRemaining()
  {
    return this.timeRemaining;
  }
  
  public void setTimeRemaining(String timeRemaining)
  {
    this.timeRemaining = timeRemaining;
  }
  
  public String getRestName()
  {
    return this.restName;
  }
  
  public void setRestName(String restName)
  {
    this.restName = restName;
  }
  
  public Double getTotalCash()
  {
    return this.totalCash;
  }
  
  public void setTotalCash(Double totalCash)
  {
    this.totalCash = totalCash;
  }
  
  public Integer getUsersCount()
  {
    return this.usersCount;
  }
  
  public void setUsersCount(Integer usersCount)
  {
    this.usersCount = usersCount;
  }
  
  public Collection<ReportUserData> getDetails()
  {
    return this.details;
  }
  
  public void setDetails(Collection<ReportUserData> details)
  {
    this.details = details;
  }
}
