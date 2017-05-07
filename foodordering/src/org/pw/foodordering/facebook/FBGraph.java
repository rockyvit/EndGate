package org.pw.foodordering.facebook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class FBGraph {
	private String accessToken;

	public FBGraph(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getFBGraph() {
		String graph = null;
		try {

			String g = "https://graph.facebook.com/me?" + accessToken;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
			System.out.println(graph);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		return graph;
	}

	public Map getGraphData(String fbGraph) {
		Map fbProfile = new HashMap();
		try {
			JSONObject json = new JSONObject(fbGraph);
			String si = json.toString();
			fbProfile.put("id", json.getString("id"));
			
			String n=((JSONObject) fbProfile).getString("id"); 
			System.out.println("id is ---"+n);
			fbProfile.put("first_name", json.getString("first_name"));
			String p=((JSONObject) fbProfile).getString("first_name");
			/*if (json.has("email"))
			   fbProfile.put("email", json.getString("email"));
			String em=((JSONObject)fbProfile).getString("email");
			if (json.has("gender"))
				fbProfile.put("gender", json.getString("gender"));
			    String ge=((JSONObject)fbProfile).getString("gender");
			
			try{  
				Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodordering",
			        "root", "Welcome1");
			
			String query = "update users set email = ? ,first_name = ? ";
			PreparedStatement ps=con.prepareStatement(query);  
					//ps.setString(1,n);
					//ps.setString(2,p); 
					ps.setString(5,em);
					ps.setString(2,ge);
			      
			ResultSet rs=ps.executeQuery();  
			rs.next(); */ 
			         
			}catch(Exception e){System.out.println(e);} 
	/*} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in parsing FB graph data. " + e);
		}*/
		return fbProfile;
	
   
	
     
		}
}