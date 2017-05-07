package org.pw.foodordering.engine;

import java.text.SimpleDateFormat;
import org.springframework.mail.javamail.MimeMessagePreparator;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pw.foodordering.elements.db.Order;
import org.pw.foodordering.elements.db.OrderElement;
import org.pw.foodordering.elements.db.User;
import org.pw.foodordering.interfaces.FoodMailServerInterface;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class FoodMailServer
  implements FoodMailServerInterface
{
  private JavaMailSender mailSender;
 
 
  private Log logger = LogFactory.getLog(getClass());
  private String myAddress = null;
  
  public void setMailSender(JavaMailSender mailSender)
  {
    this.mailSender = mailSender;
  }
  
  public void setMyAddress(String addr)
  {
    this.myAddress = addr;
  }
  
  public void sendTestMessage()
  {
    MimeMessage mimeMessage = this.mailSender.createMimeMessage();
    try
    {
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
      
  //helper.setTo("kumar.diwakar1082@gmail.com");
      //helper.setFrom("pw.foodordering@gmail.com");
      helper.setFrom("info@foodclock.in");
      helper.setSubject("Activation link from FoodOrdering");
      
      helper.setText("<html><body style=\"background-color: #699b45;color: black;align: center;text-align: center;\"><br/><br/><br/><h3>Thank you for registering in FO Service !</h3><br/><br/>From now on you can order food entering main page and logging in.<br/><br/>To finish registration process plese click the following link:<br/><br/><a href=\"http://" + this.myAddress + "\" style=\"color: #083808\">http://" + this.myAddress + "</a>" + "<br/><br/><br/>" + "Have a tasty meal !<br/>" + "<br/>" + "<div style=\"align: left; text-align: left;color: #44523a\">" + "Food Ordering Service 2017<br/>" + "</div>" + "</body>" + "</html>", true);
      
      this.mailSender.send(mimeMessage);
    }
    catch (MessagingException e)
    {
      this.logger.debug(e.getStackTrace());
    }
  }
  
public void sendActivationMessage(String email, String activationCode)
  {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    try
    {
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
      System.out.println("email configured is :===:" + email);
      helper.setTo(email);
     // helper.setFrom("pw.foodordering@gmail.com");
      helper.setFrom("info@foodclock.in");
      helper.setSubject("Activation link from FoodOrdering");
      
    helper.setText("<html><body style=\"color: black;align: center;text-align: center;\"><table style=\"border:1px solid black;width:600px;height:400px;background-color:#F0FFE3;\"><TR><TD><div style=\"color:#085B1E;font-weight:bold;text-align:center;\"><h3>Thank you for registering in FO Service !</h3><p style=\"font-size:12px;color:#336B1A;\">To finish the registration process please click the following link:</p><br/><a href=\"http://" + this.myAddress + ":8068/foodordering/secure/register.do?activation=" + activationCode + "\" style=\"color: #083808\">" + "Activate my account!</a><br/><br/><br/><br/>" + "<span style=\"font-size:10px;\">" + "If you cannot activate using above link copy the address below to your browser." + "<br/><br/>" + "http://" + this.myAddress + ":8068/foodordering/secure/register.do?activation=" + activationCode + "</span>" + "</div>" + "<br/><br/>" + "<div style=\"align: left; text-align: left;color:#C9D5BE;font-size:12px;\">" + "Food Ordering Service 2017-2018<br/>" + "</div>" + "</TD></TR></table>" + "</body>" + "</html>", true);
      //helper.setText("Hi Welcome in Foddclock!!!");
     System.out.println("actually mime message is :::==::" + mimeMessage.toString());
     // mailSender.send(mimeMessage);
     
    this.mailSender.send(mimeMessage);
    
    
     
     
    }
    catch (MessagingException e)
    {
      this.logger.debug(e.getStackTrace());
    }
  }
 
  

  
  public void sendOrderListToOrdersLeader(User user, User leader, Collection<Order> orders)
    throws MessagingException
  {
    MimeMessage mimeMessage = this.mailSender.createMimeMessage();
    
    Boolean isLeader = Boolean.valueOf(false);
    if (user.getLogin_id() == leader.getLogin_id()) {
      isLeader = Boolean.valueOf(true);
    }
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
    System.out.println("get email value is :--:" +user.getLogin_id());
    helper.setTo(user.getLogin_id());
    //helper.setFrom("pw.foodordering@gmail.com");
    helper.setFrom("info@foodclock.in");
    if (isLeader.booleanValue()) {
      helper.setSubject("Order summary - you are the LEADER!");
    } else {
      helper.setSubject("Order summary");
    }
    String msg = "";
    Order order;
    msg = msg + "<html><body style=\"background-color:#F0FFE3;color: black;align: center;text-align: center;\"><table style=\"border:0px solid black;width:600px;height:400px;\"><TR><TD><div style=\"color:#085B1E;font-weight:bold;text-align:center;\"><h3>Thank you for ordering in FO Service !</h3>";
    if (isLeader.booleanValue()) {
      msg = msg + "<p style=\"font-size:12px;color:#C83535;\">You are selected to be the <span style=\"font-size:16px;color:#5C1E1E;\">leader</span> of this order.<br/> This means that you are responsible for calling to the restaurant and ordering all things that are listed below.</p>";
    } else {
      msg = msg + "<p style=\"font-size:12px;color:#25AD03;\">Just wait. The person responsible for making the order is: " + leader.getLogin_id() + ".</p>";
    }
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    msg = msg + "<p style=\"text-align:left;\">Order: (" + formatter.format(((Order)((List)orders).get(0)).getOrderingDate()) + ")</p>" + "<table class=\"border:1px solid black;border-collapse:collapse;background-color:#E6F4D9;\">" + "<tr><td style=\"border:1px solid black;font-size:12px;font-weight:bold;color:#000000;border-bottom-width:2px;\">Login_id</td>" + "<td style=\"border:1px solid black;font-size:12px;font-weight:bold;color:#000000;border-bottom-width:2px;\">Dish name</td>" + "<td style=\"border:1px solid black;font-size:12px;font-weight:bold;color:#000000;border-bottom-width:2px;\">Price</td>" + "<td style=\"border:1px solid black;font-size:12px;font-weight:bold;color:#000000;border-bottom-width:2px;\">Remarks</td></tr>";
    
    Double ordersSum = Double.valueOf(0.0D);
    for (Iterator i$ = orders.iterator(); i$.hasNext();)
    {
      order = (Order)i$.next();
      for (OrderElement orderElements : order.getOrderElements())
      {
        msg = msg + "<tr><td style=\"border:1px solid black;font-size:12px;color:#6D6D6D;\">" + order.getUser().getLogin_id() + "</td>" + "<td style=\"border:1px solid black;font-size:15px;padding:2px 10px 2px 10px;color:#0E1D00;font-style:italic;\">" + orderElements.getDish().getDetails().getName() + "</td>" + "<td style=\"border:1px solid black;font-size:12px;color:#6D6D6D;\">" + orderElements.getDish().getPrice() + "</td>" + "<td style=\"border:1px solid black;font-size:13px;padding:2px 10px 2px 10px;color:#0E1D00;font-style:italic;\">";
        if (orderElements.getAdditionalInfo() != null) {
          msg = msg + orderElements.getAdditionalInfo();
        }
        msg = msg + "</td></tr>";
        
        ordersSum = Double.valueOf(ordersSum.doubleValue() + orderElements.getDish().getPrice().doubleValue());
      }
    }
    //Order order;
    msg = msg + "<tr><td></td><td></td><td style=\"border:1px solid black;font-size:15px;padding:2px 10px 2px 10px;color:#0E1D00;font-style:italic;\">" + ordersSum + "</td><td></td></tr>";
    
    msg = msg + "</table><br/><p style=\"text-align:left;\">Restaurant:</p><table style=\"border:1px solid black;border-collapse:collapse;background-color:#E6F4D9;\"><tr><td>name:</td><td style=\"border:1px solid black;border-collapse:collapse;background-color:#E6F4D9;font-weight:bold;text-align:center;font-size:14px;\">" + ((Order)((List)orders).get(0)).getRestaurant().getName() + "</td></tr>" + "<tr><td>info:</td><td style=\"border:1px solid black;font-size:15px;padding:2px 10px 2px 10px;color:#0E1D00;font-style:italic;\">" + ((Order)((List)orders).get(0)).getRestaurant().getDescription() + "</td></tr>" + "</table>" + "</div>" + "<br/><br/>" + "<div style=\"align: left; text-align: left;color:#C9D5BE;font-size:12px;\">" + "Food Ordering Service 2009-2010<br/>" + "</div>" + "</TD></TR></table>" + "</body>" + "</html>";
    
    helper.setText(msg, true);
    
    this.mailSender.send(mimeMessage);
  }
  
  public void sendTest()
  {
    MimeMessage mimeMessage = this.mailSender.createMimeMessage();
    try
    {
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
      
      helper.setTo("kumar.diwakar1082@gmail.com");
     // helper.setFrom("pw.foodordering@gmail.com");
      helper.setFrom("info@foodclock.in");
      helper.setSubject("Order summary");
      
      String msg = "";
      msg = msg + "<html><body style=\"background-color:#F0FFE3;color: black;align: center;text-align: center;\"><table style=\"border:0px solid black;width:600px;height:400px;\"><TR><TD><div style=\"color:#085B1E;font-weight:bold;text-align:center;\"><h3>Thank you for ordering in FO Service !</h3><p style=\"font-size:12px;color:#C83535;\">You are selected to be the <span style=\"font-size:16px;color:#5C1E1E;\">leader</span> of this order.<br/> This means that you are responsible for calling to restaurant and ordering all things that are listed below.</p><br/><p style=\"text-align:left;\">Orders:</p><table class=\"border:1px solid black;border-collapse:collapse;background-color:#E6F4D9;\"><tr><td style=\"border:1px solid black;font-size:12px;font-weight:bold;color:#000000;border-bottom-width:2px;\">Login_id</td><td style=\"border:1px solid black;font-size:12px;font-weight:bold;color:#000000;border-bottom-width:2px;\">Dish name</td><td style=\"border:1px solid black;font-size:12px;font-weight:bold;color:#000000;border-bottom-width:2px;\">Price</td><td style=\"border:1px solid black;font-size:12px;font-weight:bold;color:#000000;border-bottom-width:2px;\">Remarks</td></tr><tr><td style=\"border:1px solid black;font-size:12px;color:#6D6D6D;\">Pio Pio - pitre</td><td style=\"border:1px solid black;font-size:15px;padding:2px 10px 2px 10px;color:#0E1D00;font-style:italic;\">Gulasz strogonow</td><td style=\"border:1px solid black;font-size:12px;color:#6D6D6D;\">12.20</td><td style=\"border:1px solid black;font-size:13px;padding:2px 10px 2px 10px;color:#0E1D00;font-style:italic;\">dobrze wypieczony</td></tr><tr><td style=\"border:1px solid black;font-size:12px;color:#6D6D6D;\">Pio Pio - pitre</td><td style=\"border:1px solid black;font-size:15px;padding:2px 10px 2px 10px;color:#0E1D00;font-style:italic;\">Gulasz strogonow</td><td style=\"border:1px solid black;font-size:12px;color:#6D6D6D;\">12.20</td><td style=\"border:1px solid black;font-size:13px;padding:2px 10px 2px 10px;color:#0E1D00;font-style:italic;\">dobrze wypieczony</td></tr>\t\t</table><br/><p style=\"text-align:left;\">Restaurant:</p><table style=\"border:1px solid black;border-collapse:collapse;background-color:#E6F4D9;\"><tr><td>name:</td><td style=\"border:1px solid black;border-collapse:collapse;background-color:#E6F4D9;font-weight:bold;text-align:center;font-size:14px;\">Uciecha</td></tr><tr><td>info:</td><td style=\"border:1px solid black;font-size:15px;padding:2px 10px 2px 10px;color:#0E1D00;font-style:italic;\">Katowice<br/>al. Rozdzieńskiego 91<br/><br/>Otwarte: 8:00-16:00<br/>Tel: 123-456-754</td></tr></table></div><br/><br/><div style=\"align: left; text-align: left;color:#C9D5BE;font-size:12px;\">Food Ordering Service 2009-2010<br/></div></TD></TR></table></body></html>";
      
      helper.setText(msg, true);
      this.mailSender.send(mimeMessage);
    }
    catch (MessagingException e)
    {
      this.logger.debug(e.getStackTrace());
    }
  }
}
