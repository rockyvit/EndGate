package org.pw.foodordering.engine;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.UUID;
import org.apache.commons.codec.binary.Hex;

public class CommonUtils
{
  public static String encodeAsMd5(String value)
  {
    try
    {

      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] bytes = md.digest(value.getBytes());
      return new String(Hex.encodeHex(bytes));
    }
    catch (Exception e) {}
    return null;
  }
  
  public static String generateActivationKey(String user)
  {
    UUID uuid = null;
    Long iVal = Long.valueOf(user.hashCode() * System.currentTimeMillis());
    Long fVal = Long.valueOf(iVal.longValue() * iVal.hashCode());
    Long sVal = Long.valueOf(fVal.longValue() * iVal.hashCode());
    
    uuid = new UUID(fVal.longValue(), sVal.longValue());
    String[] sts = uuid.toString().split("-");
    String activation = "";
    System.out.println("we are here to check activation status");
    for (String s : sts) {
      activation = activation + s;
    }
    System.out.println("activation is :---:" + activation);
    return activation;
  }
  
  public static String generatePIN(String phone) 
  {   
      int x = (int)(Math.random() * 9);
      x = x + 1;
      String randomPIN = (x + "") + ( ((int)(Math.random()*1000)) + "" );
      System.out.println("PIN is :---:" + randomPIN);
      return randomPIN;
  }

  
  public static String convertTimesDiffToString(Timestamp ts, Long current)
  {
    Long tsl = Long.valueOf((Long.valueOf(ts.getTime()).longValue() - current.longValue()) / 1000L);
    
    Long days = Long.valueOf(tsl.longValue() / 86400L);
    tsl = Long.valueOf(tsl.longValue() % 86400L);
    Long hours = Long.valueOf(tsl.longValue() / 3600L);
    tsl = Long.valueOf(tsl.longValue() % 3600L);
    Long mins = Long.valueOf(tsl.longValue() / 60L);
    
    String retString = "";
    Boolean anySet = Boolean.valueOf(false);
    if (days.longValue() != 0L)
    {
      retString = retString + days;
      if (days.longValue() == 1L) {
        retString = retString + " day ";
      } else {
        retString = retString + " days ";
      }
      anySet = Boolean.valueOf(true);
    }
    if (hours.longValue() != 0L)
    {
      retString = retString + hours;
      if (hours.longValue() == 1L) {
        retString = retString + " hour ";
      } else {
        retString = retString + " hours ";
      }
      anySet = Boolean.valueOf(true);
    }
    if (mins.longValue() != 0L)
    {
      retString = retString + mins;
      if (mins.longValue() == 1L) {
        retString = retString + " minute";
      } else {
        retString = retString + " minutes";
      }
      anySet = Boolean.valueOf(true);
    }
    if (!anySet.booleanValue()) {
      retString = retString + "Less then 1 minute";
    }
    return retString;
  }
}
