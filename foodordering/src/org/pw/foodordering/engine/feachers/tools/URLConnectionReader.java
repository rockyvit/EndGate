package org.pw.foodordering.engine.feachers.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class URLConnectionReader
{
  private StringBuilder siteHtml = new StringBuilder();
  
  public void initPage(String site, String encoding)
    throws Exception
  {
    URL restaurant = new URL(site);
    URLConnection rc = restaurant.openConnection();
    BufferedReader in = new BufferedReader(new InputStreamReader(rc.getInputStream(), Charset.forName(encoding)));
    
    BufferedReader br = new BufferedReader(in);
    String inputLine;
    while ((inputLine = br.readLine()) != null) {
      this.siteHtml.append(inputLine);
    }
    in.close();
  }
  
  public void reset()
  {
    this.siteHtml = new StringBuilder();
  }
  
  public String getSiteHtml()
  {
    return this.siteHtml.toString();
  }
}
