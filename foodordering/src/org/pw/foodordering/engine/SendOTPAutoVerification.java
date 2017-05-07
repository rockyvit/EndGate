package org.pw.foodordering.engine;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;


/*
* This code is based on jersey-client library.
* For gradle based project use compile 'com.sun.jersey:jersey-client:1.18.4'
* You can also download the jar and add it to you project.
* */
public class SendOTPAutoVerification {

    private static String otp;
    private static String mobile="7760686052";
    private static String newotp="4561";
    //Base URL
    public static String baseUrl = "htts://control.msg91.com/a/sendotp.php?authkey=138624AyUiH3NUP58f675f0&mobile="
    		+ mobile
    		+ "&message=Your%20OTP%20is%20"
    		+ newotp
    		+ "&sender=FCCL12&otp="
    		+newotp;
    //public static String baseUrl = "http://54.169.180.68:8080/SendOTP-API";

    // Your application key
    public static String applicationKey ="138624AyUiH3NUP58f675f0";
    /*
    * This function is used to send OTP message on mobile number
    * */
    public static void generateOTP(String country_code,String phone){
        try {
        	System.out.println("we are in sendOTP");
            Client client = Client.create();
            String Url  = baseUrl;
            WebResource webResource = client.resource(Url);
            System.out.println("WebResource");
            System.out.println(webResource);

            HashMap<String, String> requestBodyMap = new HashMap();
            requestBodyMap.put("country_code",country_code);
            requestBodyMap.put("phone",phone);
           // requestBodyMap.put("getGeneratedOTP","true");
            JSONObject requestBodyJsonObject = new JSONObject(requestBodyMap);
            String input = requestBodyJsonObject.toString();

            ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                    .header("application-Key", applicationKey)
                    .post(ClientResponse.class, input);
            response.bufferEntity();
            String output = response.getEntity(String.class);
            
            
            System.out.println("Request: "+output);
            //fetch your oneTimePassword and save it to session or db
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * This function is used to send OTP message on mobile number
    * */
    public static void verifyOTP(String phone, String phone_secret_key){
        try {
            //fetch your oneTimePassword from session or db
            //and compare it with the OTP sent from clien
            Client client = Client.create();
            String Url  = baseUrl+"/verifyOTP";
            WebResource webResource = client.resource(Url);

            HashMap<String, String> requestBodyMap = new HashMap();
            //requestBodyMap.put("countryCode",countryCode);
            requestBodyMap.put("phone",phone);
            requestBodyMap.put("email_secret_key",phone_secret_key);
            JSONObject requestBodyJsonObject = new JSONObject(requestBodyMap);
            String input = requestBodyJsonObject.toString();

            ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                    .header("application-Key", applicationKey)
                    .post(ClientResponse.class, input);
            response.bufferEntity();
            String output = response.getEntity(String.class);
            System.out.println("Response is invaild: "+output);
            JSONObject jsonObj = new JSONObject(output);
            JSONObject responseJson = jsonObj.getJSONObject("response");
            String status = responseJson.getString("code");

            if("NUMBER_VERIFIED_SUCCESSFULLY".equalsIgnoreCase(status)){
                System.out.println("Verified");
            }
            else{
                System.out.println("Invalid code");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}