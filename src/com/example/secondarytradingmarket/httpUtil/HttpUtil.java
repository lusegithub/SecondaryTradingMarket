package com.example.secondarytradingmarket.httpUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import android.util.Log;

public class HttpUtil {
	public static final String URL="http://192.168.1.101:8080/SecondaryTradingMarketServer";
	public static String sendHttpURLByGet(String address) {

        HttpURLConnection connection = null;
        try {
            URL url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.connect();
            InputStream in = connection.getInputStream();
            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return new String(response);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
	public static String sendHttpURLByPost(String address,String jsonData){
		HttpURLConnection connection = null;
		DataOutputStream out= null;
        try {
            URL url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(8000);
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setDoOutput(true);
            connection.connect();
            out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(jsonData);
            out.flush();
            out.close();
            InputStream in = connection.getInputStream();
            Log.d("httputil", String.valueOf(connection.getResponseCode()));
            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return new String(response);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
	}
	public static String getData(String address) {

        HttpURLConnection connection = null;
        try {
            URL url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.connect();
            StringBuffer buffer = new StringBuffer();
            String line = null;
            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
            	BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8")); 
                //BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }
                String result=URLDecoder.decode(buffer.toString(),"UTF-8");
                return result;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
	public static String getSupply(String address) {

        HttpURLConnection connection = null;
        try {
            URL url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.connect();
            StringBuffer buffer = new StringBuffer();
            String line = null;
            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
            	BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8")); 
                //BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }
                String result=URLDecoder.decode(buffer.toString(),"UTF-8");
                return result;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
	
}
