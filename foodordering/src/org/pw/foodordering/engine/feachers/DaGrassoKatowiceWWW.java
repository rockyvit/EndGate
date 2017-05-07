package org.pw.foodordering.engine.feachers;

import java.io.PrintStream;
import java.util.HashMap;
import org.pw.foodordering.engine.feachers.tools.URLConnectionReader;
import org.pw.foodordering.interfaces.FetcherInterface;

public class DaGrassoKatowiceWWW
  implements FetcherInterface
{
  private static final int REST_MAX = 42;
  HashMap<String, HashMap<String, HashMap<String, Object>>> menu = null;
  String urlPrefix = "http://www.dagrasso.pl/restauracje/franczyza/katowice_restauracja/menu_pokaz.php?id=";
  String[] parse_1_size = { " mała porcja", " duża porcja" };
  Integer currentId = Integer.valueOf(0);
  String groupName = "";
  String dishName = "";
  String dishDesc = "";
  String dishInfo = "";
  Double dishPrice = Double.valueOf(0.0D);
  
  private void resetValues(Boolean reset_group)
  {
    if (reset_group.booleanValue()) {
      this.groupName = "";
    }
    this.dishName = "";
    this.dishDesc = "";
    this.dishInfo = "";
    this.dishPrice = Double.valueOf(0.0D);
  }
  
  private void parse_1_menu(String html)
  {
    String[] men = html.split("<table id=\"tresc\"");
    parse_1_find_group_name(men[1]);
    parse_1_find_dishes(html);
  }
  
  private void parse_1_find_group_name(String html)
  {
    String[] groups = html.split("<td height=\"30\" colspan=\"2\" class=\"tresc_l\"><strong>");
    this.groupName = groups[1].split("</strong>")[0].trim();
  }
  
  private void parse_1_find_dishes(String html)
  {
    String[] dishes = html.split("<td width=\"390\"><span class=\"tresc_l\"><strong>");
    Boolean skip = Boolean.valueOf(true);
    for (String dishElem : dishes) {
      if (skip.booleanValue())
      {
        skip = Boolean.valueOf(false);
      }
      else
      {
        resetValues(Boolean.valueOf(false));
        parse_1_find_dish_name(dishElem);
        parse_1_find_dish_desc(dishElem);
        parse_1_find_dish_price_and_add(dishElem);
      }
    }
  }
  
  private void parse_1_find_dish_name(String html)
  {
    String localDishName = html.split("</strong>")[0];
    this.dishName = localDishName.replaceFirst("[0-9]+[/]?[0-9]*[.] ", "").trim();
  }
  
  private void parse_1_find_dish_desc(String html)
  {
    String[] desc = html.split("<em>");
    this.dishDesc = desc[1].split("</em>")[0].trim();
  }
  
  private void parse_1_find_dish_price_and_add(String html)
  {
    String prices = html.split("<td width=\"120\" align=\"center\"><strong>")[1].split("</strong></td>")[0];
    String[] pricesTab = prices.split("&nbsp;");
    Integer cnt = Integer.valueOf(0);
    Boolean exce = Boolean.valueOf(false);
    Integer localInteger1;
    for (String price : pricesTab)
    {
      try
      {
        this.dishPrice = Double.valueOf(price.trim());
      }
      catch (Exception e)
      {
        exce = Boolean.valueOf(true);
        continue;
      }
      HashMap<String, HashMap<String, Object>> mGroup = (HashMap)this.menu.remove(this.groupName);
      if (mGroup == null) {
        mGroup = new HashMap();
      }
      HashMap<String, Object> mDish = new HashMap();
      mDish.put("price", this.dishPrice);
      mDish.put("desc", this.dishDesc);
      mDish.put("info", this.dishInfo);
      if (((this.currentId.intValue() >= 6) && (this.currentId.intValue() <= 17)) || ((this.currentId.intValue() >= 26) && (this.currentId.intValue() <= 27)) || ((this.currentId.intValue() >= 30) && (this.currentId.intValue() <= 33)) || ((this.currentId.intValue() >= 36) && (this.currentId.intValue() <= 37)))
      {
        mGroup.put(this.dishName, mDish);
        localPrintBool("------name: " + this.dishName, Boolean.valueOf(true));
      }
      else if (this.currentId.intValue() == 29)
      {
        if ((pricesTab.length > 1) && (!exce.booleanValue()))
        {
          mGroup.put(this.dishName + this.parse_1_size[cnt.intValue()], mDish);
          localPrintBool("------name: " + this.dishName + this.parse_1_size[cnt.intValue()], Boolean.valueOf(true));
        }
        else
        {
          mGroup.put(this.dishName, mDish);
          localPrintBool("------name: " + this.dishName, Boolean.valueOf(true));
        }
      }
      else if (this.currentId.intValue() == 42)
      {
        mGroup.put(this.dishName + this.parse_1_size[cnt.intValue()], mDish);
        localPrintBool("------name: " + this.dishName + this.parse_1_size[cnt.intValue()], Boolean.valueOf(true));
      }
      else
      {
        mGroup.put(this.dishName + this.parse_1_size[cnt.intValue()], mDish);
        localPrintBool("------name: " + this.dishName + this.parse_1_size[cnt.intValue()], Boolean.valueOf(true));
      }
      this.menu.put(this.groupName, mGroup);
      
      localPrintBool("------desc: " + this.dishDesc, Boolean.valueOf(true));
      localPrintBool("------info: " + this.dishInfo, Boolean.valueOf(true));
      localPrintBool("-----price: " + this.dishPrice, Boolean.valueOf(true));
      localPrintBool("-----group: " + this.groupName, Boolean.valueOf(true));
      
      localInteger1 = cnt;Integer localInteger2 = cnt = Integer.valueOf(cnt.intValue() + 1);
    }
  }
  
  private void parseMenu(Integer id, String html)
  {
    localPrint("Parsing ID: " + id);
    resetValues(Boolean.valueOf(true));
    this.currentId = id;
    switch (id.intValue())
    {
    case 1: 
    case 2: 
    case 3: 
    case 5: 
    case 18: 
    case 26: 
    case 27: 
    case 29: 
    case 30: 
    case 31: 
    case 32: 
    case 33: 
    case 37: 
    case 42: 
      parse_1_menu(html);
      break;
    case 4: 
    case 6: 
    case 7: 
    case 8: 
    case 9: 
    case 10: 
    case 11: 
    case 12: 
    case 13: 
    case 14: 
    case 15: 
    case 16: 
    case 17: 
    case 19: 
    case 20: 
    case 21: 
    case 22: 
    case 23: 
    case 24: 
    case 25: 
    case 28: 
    case 34: 
    case 35: 
    case 36: 
    case 38: 
    case 39: 
    case 40: 
    case 41: 
    default: 
      localPrint("No action !!!");
      return;
    }
  }
  
  public void parse()
  {
    this.menu = new HashMap();
    URLConnectionReader con = new URLConnectionReader();
    for (int id = 0; id <= 42; id++)
    {
      try
      {
        con.initPage(this.urlPrefix + id, "ISO-8859-2");
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      parseMenu(Integer.valueOf(id), con.getSiteHtml());
      con.reset();
    }
  }
  
  private void localPrintBool(String info, Boolean really)
  {
    if (really.booleanValue()) {
      System.out.println(info);
    }
  }
  
  private void localPrint(String info)
  {
    localPrintBool(info, Boolean.valueOf(true));
  }
  
  public HashMap<String, HashMap<String, HashMap<String, Object>>> getMenu()
  {
    return this.menu;
  }
}
