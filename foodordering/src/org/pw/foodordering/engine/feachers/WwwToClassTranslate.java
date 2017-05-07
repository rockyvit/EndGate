package org.pw.foodordering.engine.feachers;

import java.io.PrintStream;
import java.util.HashMap;
import org.pw.foodordering.engine.feachers.tools.URLConnectionReader;
import org.pw.foodordering.interfaces.FetcherInterface;

public class WwwToClassTranslate
  implements FetcherInterface
{
  HashMap<String, HashMap<String, HashMap<String, Object>>> menu = null;
  String url = "http://www.uciecha.com.pl/menu.php";
  String groupName = "";
  String dishName = "";
  String dishDesc = "";
  String dishInfo = "";
  Double dishPrice = Double.valueOf(0.0D);
  String dishPrefix = null;
  String[] size = { " mała 26cm", " średnia 32cm", " duża 45cm" };
  String[] addSize = { " do małej", " do średniej", " do dużej" };
  
  static enum pizzaGroup
  {
    NONE,  PIZZA,  ADDITIONS;
    
    private pizzaGroup() {}
  }
  
  public void splitIntoMainCategories(String input)
  {
    int cnt = 0;
    String[] categories = input.split("<h3>");
    for (String category : categories) {
      if (cnt++ != 0)
      {
        this.groupName = category.split("</h3>")[0].trim();
        localPrint("GR name: " + this.groupName);
        splitIntoSubCategories(category);
      }
    }
  }
  
  public void splitIntoSubCategories(String input)
  {
    int cnt = 0;
    String[] subcategories = input.split("<table class");
    Integer extraOptions = Integer.valueOf(0);
    for (String subcategory : subcategories)
    {
      if (cnt != 0)
      {
        localPrint("SUBCAT: " + subcategory);
        localPrint("########## cnt: " + cnt);
        if ((this.groupName.toLowerCase().equals("pizza")) && (cnt == pizzaGroup.ADDITIONS.ordinal()))
        {
          localPrint("------------------ PIZZA && DODATKI ------------");
          this.dishPrefix = "Dodatki ";
        }
        else
        {
          localPrint("------------------ NORMAL ------------");
          this.dishPrefix = null;
        }
        extraOptions = parseHead(subcategory);
        parseBody(subcategory, extraOptions);
      }
      cnt++;
    }
  }
  
  public Integer parseHead(String input)
  {
    String[] head = input.split("thead");
    return countOptions(head[1]);
  }
  
  public Integer countOptions(String input)
  {
    String[] options = input.split("<th");
    localPrint("OPTS: " + (options.length - 1));
    return Integer.valueOf(options.length - 1);
  }
  
  public void parseBody(String input, Integer extraOptions)
  {
    int cnt = 0;
    localPrint("BOdy: " + input);
    String[] dishes = input.split("<tr>");
    for (String dish : dishes) {
      if (cnt++ != 0)
      {
        localPrint("DISH: " + dish);
        parseDish(dish);
        parseInfo(dish);
        parseOptions(dish, extraOptions);
        
        this.dishInfo = "";
      }
    }
  }
  
  void parseInfo(String input)
  {
    String[] info = input.split("<dd>");
    String additionalInfo = null;
    if (info.length == 2)
    {
      additionalInfo = info[1].split("</dd>")[0].trim();
      localPrint("ADD: " + additionalInfo);
      this.dishDesc = additionalInfo;
    }
  }
  
  void parseDish(String input)
  {
    localPrint("DISHES: " + input);
    localPrint("IS NULL: " + this.dishPrefix);
    if (this.dishPrefix == null)
    {
      String[] dishNamesPre = input.split("<dt>");
      if (dishNamesPre.length != 1)
      {
        String dishName = dishNamesPre[1].split("</dt>")[0].trim();
        localPrint("DISH name: " + dishName);
        this.dishName = dishName;
      }
    }
    else
    {
      String dishName = input.split("<th>")[1].split("</th>")[0].trim();
      localPrint("DISH name: " + this.dishPrefix + dishName);
      this.dishName = (this.dishPrefix + dishName);
    }
  }
  
  void parseOptions(String input, Integer extraOptions)
  {
    Integer version = Integer.valueOf(0);
    Integer localInteger1 = extraOptions;Integer localInteger2 = extraOptions = Integer.valueOf(extraOptions.intValue() - 1);Integer optionsLeft = localInteger1;
    String[] options = input.split("<td>");
    
    int cnt = 0;
    for (String option : options)
    {
      Integer localInteger3 = optionsLeft;Integer localInteger4 = optionsLeft = Integer.valueOf(optionsLeft.intValue() - 1);
      if (localInteger3.intValue() == 0) {
        break;
      }
      if (cnt++ != 0)
      {
        String[] optValues = option.split("</td>");
        if (optValues.length == 0)
        {
          localInteger4 = version;Integer localInteger5 = version = Integer.valueOf(version.intValue() + 1);
        }
        else
        {
          String optValue = option.split("</td>")[0].trim();
          localPrint("Option: " + optValue);
          try
          {
            String dotPrice = optValue.replace(",", ".");
            this.dishPrice = Double.valueOf(Double.parseDouble(dotPrice));
            
            HashMap<String, HashMap<String, Object>> mGroup = (HashMap)this.menu.remove(this.groupName);
            if (mGroup == null) {
              mGroup = new HashMap();
            }
            HashMap<String, Object> mDish = new HashMap();
            mDish.put("price", this.dishPrice);
            mDish.put("desc", this.dishDesc);
            mDish.put("info", this.dishInfo);
            localPrint("VER: " + version);
            if (this.groupName.toLowerCase().equals("pizza"))
            {
              if (this.dishPrefix == null)
              {
                localPrintBool("Added nam: " + this.dishName + this.size[version.intValue()], Boolean.valueOf(true));
                mGroup.put(this.dishName + this.size[version.intValue()], mDish);
              }
              else
              {
                localPrintBool("Added nam: " + this.dishName + this.addSize[version.intValue()], Boolean.valueOf(true));
                mGroup.put(this.dishName + this.addSize[version.intValue()], mDish);
              }
            }
            else
            {
              localPrintBool("Added nam: " + this.dishName, Boolean.valueOf(true));
              mGroup.put(this.dishName, mDish);
            }
            this.menu.put(this.groupName, mGroup);
            
            localPrintBool("------desc: " + this.dishDesc, Boolean.valueOf(true));
            localPrintBool("------info: " + this.dishInfo, Boolean.valueOf(true));
            localPrintBool("-----price: " + this.dishPrice, Boolean.valueOf(true));
            localPrintBool("-----group: " + this.groupName, Boolean.valueOf(true));
            
            this.dishInfo = "";
            
            Integer localInteger6 = version;Integer localInteger7 = version = Integer.valueOf(version.intValue() + 1);
          }
          catch (Exception e)
          {
            Integer localInteger6;
            this.dishInfo = (this.dishInfo + optValue + " ");
          }
        }
      }
    }
    this.dishDesc = "";
  }
  
  public void parse()
  {
    this.menu = new HashMap();
    URLConnectionReader con = new URLConnectionReader();
    try
    {
      con.initPage(this.url, "UTF-8");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    splitIntoMainCategories(con.getSiteHtml());
  }
  
  private void localPrintBool(String info, Boolean really)
  {
    if (really.booleanValue()) {
      System.out.println(info);
    }
  }
  
  private void localPrint(String info)
  {
    localPrintBool(info, Boolean.valueOf(false));
  }
  
  public HashMap<String, HashMap<String, HashMap<String, Object>>> getMenu()
  {
    return this.menu;
  }
}
