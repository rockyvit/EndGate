package org.pw.foodordering.facebook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FBConnection {
	public static final String FB_APP_ID = "607617302696201";
	public static final String FB_APP_SECRET = "cec44ce6b5ce541ea83d9497b614a065";
	public static final String REDIRECT_URI = "http://www.foodclock.in";

	static String accessToken = "";
	private Log logger = LogFactory.getLog(FBGraph.class.getName());
	
	public String getFBAuthUrl() {
		String fbLoginUrl = "";
		try {
			fbLoginUrl = "http://www.facebook.com/dialog/oauth?" + "client_id="
					+ FBConnection.FB_APP_ID + "&redirect_uri="
					+ URLEncoder.encode(FBConnection.REDIRECT_URI, "UTF-8")
					+ "&scope=email";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//logger.debug("we are here to tetst");
		return fbLoginUrl;
	}

	public String getFBGraphUrl(String code) {
		logger.debug("we are here");
		String fbGraphUrl = "";
		try {
			fbGraphUrl = "http://graph.facebook.com/oauth/access_token?"
					+ "client_id=" + FBConnection.FB_APP_ID + "&redirect_uri="
					+ URLEncoder.encode(FBConnection.REDIRECT_URI, "UTF-8")
					+ "&client_secret=" + FB_APP_SECRET + "&code=" + code;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return fbGraphUrl;
	}

	public String getAccessToken(String code) {
		if ("".equals(accessToken)) {
			URL fbGraphURL;
			try {
				fbGraphURL = new URL(getFBGraphUrl(code));
			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new RuntimeException("Invalid code received " + e);
			}
			URLConnection fbConnection;
			StringBuffer b = null;
			try {
				fbConnection = fbGraphURL.openConnection();
				BufferedReader in;
				in = new BufferedReader(new InputStreamReader(
						fbConnection.getInputStream()));
				String inputLine;
				b = new StringBuffer();
				while ((inputLine = in.readLine()) != null)
					b.append(inputLine + "\n");
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Unable to connect with Facebook "
						+ e);
			}

			accessToken = b.toString();
			if (accessToken.startsWith("{")) {
				throw new RuntimeException("ERROR: Access Token Invalid: "
						+ accessToken);
			}
		}
		return accessToken;
	}
}
