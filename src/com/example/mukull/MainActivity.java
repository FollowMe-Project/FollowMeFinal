package com.example.mukull;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText fn,username;
	EditText ln;
	EditText email;
	EditText mn;
	EditText password;
	Button su;
	Button si;
	
	JsonParser jp= new JsonParser();
	//private Object username;
	private static String connection_url="http://10.0.2.2/android_connection/connect.php";
	private static final String Tag_success="successfully logged in";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        username =(EditText)findViewById(R.id.field_fn);
        fn =(EditText)findViewById(R.id.field_ln);
        username =(EditText)findViewById(R.id.field_fn);
        password=(EditText)findViewById(R.id.field_ln);
        email =(EditText)findViewById(R.id.field_email);
        mn =(EditText)findViewById(R.id.field_mn);
        si=(Button)findViewById(R.id.btn1);
        
        si.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				new connect().execute();
				
				
				
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    class connect extends AsyncTask<String, String, String>
    {

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			
			String HOST=username.getText().toString();
			String USER=password.getText().toString();
			
			List<NameValuePair> params=new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("Username/Email", HOST));
			params.add(new BasicNameValuePair("Password", USER));
			
			JSONObject json = jp.makeHttpRequest(connection_url, "POST", params);
			
			try{
				int success = json.getInt(Tag_success);
				if(success == 1){
					Toast.makeText(getApplicationContext(),"logged in :) ", Toast.LENGTH_LONG).show();
				}else
				{
					Toast.makeText(getApplicationContext(),"not logged in :) ", Toast.LENGTH_LONG).show();
				}
					
			}catch(JSONException e){
				e.printStackTrace();
			}
			
			return null;
		}
    }
}
