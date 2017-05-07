package org.pw.foodordering.controllers;
import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*; 
import org.pw.foodordering.elements.db.AllLocationCoordinate;;;

public class AllLoationCoordinateController extends HttpServlet{ 
		public void doPost(HttpServletRequest request, HttpServletResponse response)  
			            throws ServletException, IOException {  
			  
			response.setContentType("text/html");  
			PrintWriter out = response.getWriter();  
			
			String location_logitude = request.getParameter("location_logitude");
			String location_latitude = request.getParameter("location_latitude");
			String location_name = request.getParameter("location_name");
			
			try {
			       String connectionURL = "jdbc:mysql://localhost:3306/fctest4";
			       Connection connection = null; 
			       Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			       connection = DriverManager.getConnection(connectionURL, "root", "rahul");
			       
			       PreparedStatement pstatement = null;
			         // Load JBBC driver "com.mysql.jdbc.Driver"
			     Class.forName("com.mysql.jdbc.Driver").newInstance();
			         int updateQuery = 0;
					try {
			              connection = DriverManager.getConnection
			              (connectionURL, "root", "rahul");
			                            // sql query to insert values in the secified table.
			              String queryString = "INSERT INTO all_locations_coordinate(location_logitude,location_latitude,location_name) VALUES (?, ?, ?)";
			              	     
			              pstatement = connection.prepareStatement(queryString);
			              pstatement.setString(1, location_logitude);
						  pstatement.setString(2, location_latitude);
						  pstatement.setString(3, location_name);
						
			             updateQuery = pstatement.executeUpdate();
			              if (updateQuery == 1) {
			            	  out.println(" connect to batabase.");
			            	   
			            }
			              } catch (Exception ex) {
			            out.println("Unable to connect to batabase.");
			   
			               }
			            finally {
			                // close all the connections.
			                pstatement.close();
			                connection.close();
			            }
			            }catch(Exception ex){
			                out.println("Unable to connect to database"+ex);
			            }   
			              
				

}
}
