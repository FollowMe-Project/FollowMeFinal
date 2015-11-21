package com.example.mukull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;
import android.util.Log;

public class JsonParser {

	static InputStream IS=null;
	static JSONObject JB=null;
	static String json="";
	//this is a constructor
	public JsonParser()
	{
		
	}
	
	public JSONObject makeHttpRequest(String url,String method,List<NameValuePair> params)
	{
		try{
			//check for request method
			if(method=="POST"){
				DefaultHttpClient httpClient=new DefaultHttpClient();
				HttpPost httpPost =new HttpPost(url);
				httpPost.setEntity(new UrlEncodedFormEntity(params));
				
			}
			else if(method=="GET")
			{
				DefaultHttpClient httpClient=new DefaultHttpClient();
				String paramString = URLEncodedUtils.format(params,"utf-8");
				url+="?" + paramString;
				HttpGet httpGet=new HttpGet(url);
				
				HttpResponse httpResponse=httpClient.execute(httpGet);
				HttpEntity httpEntity=httpResponse.getEntity();
				IS=httpEntity.getContent();
			}
		   }catch(UnsupportedEncodingException e)
		   {
			   e.printStackTrace();
			   
		   }catch(ClientProtocolException e)
		   {
			   e.printStackTrace();
		   }catch(IOException e)
		   {
			   e.printStackTrace();
		   }
		
		try{
			BufferedReader reader=new BufferedReader(new InputStreamReader(IS,"iso-8859"),8);
			StringBuilder sb=new StringBuilder();
			String line=null;
			while((line=reader.readLine())!=null)
			{
				sb.append(line + "\n");
				
			}
			IS.close();
			json=sb.toString();
		}catch(Exception e)
		{
			Log.e("Buffer Error","Conversion Error"+e.toString());
		}
		try{
			JB=new JSONObject(json);
		}catch(JSONException e){
			Log.e("JSON parser",e.toString());
		}
		return JB;
		
	}
}
