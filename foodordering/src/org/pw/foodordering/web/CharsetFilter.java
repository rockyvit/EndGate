package org.pw.foodordering.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharsetFilter
  implements Filter
{
  private String encoding;
  
  public void init(FilterConfig config)
    throws ServletException
  {
    this.encoding = config.getInitParameter("requestEncoding");
    if (this.encoding == null) {
      this.encoding = "UTF-8";
    }
  }
  
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
    throws IOException, ServletException
  {
    if (null == request.getCharacterEncoding()) {
      request.setCharacterEncoding(this.encoding);
    }
    response.setContentType("text/html; charset=UTF-8");
    response.setCharacterEncoding("UTF-8");
    
    next.doFilter(request, response);
  }
  
  public void destroy() {}
}
