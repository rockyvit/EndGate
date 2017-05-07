package org.pw.foodordering.facebook;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MainMenu extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String code="";
	private Log logger = LogFactory.getLog(MainMenu.class.getName());

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {		
		code = req.getParameter("code");
		if (code == null || code.equals("")) {
			throw new RuntimeException(
					"ERROR: Didn't get code parameter in callback.");
		}
		FBConnection fbConnection = new FBConnection();
		String accessToken = fbConnection.getAccessToken(code);

		FBGraph fbGraph = new FBGraph(accessToken);
		String graph = fbGraph.getFBGraph();
		Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
		ServletOutputStream out = res.getOutputStream();
		
		logger.debug("name is :" + fbProfileData.get("first_name"));
		System.out.println("name is :" + fbProfileData.get("first_name"));
		//logger.debug("email is :" + fbProfileData.get("email"));
		//logger.debug("gender is :" + fbProfileData.get("gender"));
		out.println("<h1>Facebook Login using Java</h1>");
		out.println("<h2>Application Main Menu</h2>");
		out.println("<div>Welcome "+fbProfileData.get("first_name"));
		//out.println("<div>Your Email: "+fbProfileData.get("email"));
	//	out.println("<div>You are "+fbProfileData.get("gender"));		
	}
      public static void main(String[]args){
    	  MainMenu m=new MainMenu();
      }
}
