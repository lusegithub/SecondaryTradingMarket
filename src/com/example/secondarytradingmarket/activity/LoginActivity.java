package com.example.secondarytradingmarket.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.secondarytradingmarket.R;
import com.example.secondarytradingmarket.entity.User;
import com.example.secondarytradingmarket.entity.Userget;
import com.example.secondarytradingmarket.httpUtil.HttpUtil;
import com.google.gson.Gson;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity{
	 
	private TextView registerBtn;
	private Button loginBtn;
	private EditText usernameEdt,passwordEdt;
	private JSONObject jsonData;
	private String check="",data;
	private User user;
	private Userget user1;
    private String username,password,address;
    public static final int SHOW_RESPONSE = 0;
    public static final int GET_NICKNAME = 1;
    private Handler handler = new Handler() {
   	 public void handleMessage(Message msg) {
   	 switch (msg.what) {
   	 case SHOW_RESPONSE:
   	      check =(String)msg.obj;
   	      if(check.equals("false")){
					AlertDialog.Builder dialog=new AlertDialog.Builder(LoginActivity.this);
					dialog.setTitle("消息");
					dialog.setMessage("登录失败，用户名或密码错误");
					dialog.setCancelable(false);
					dialog.setPositiveButton("确定", new DialogInterface.
					OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
						}
					});
					dialog.show();
				}
				else if(check.equals("true"))
				{
					user=new User(username,password);
					User.user=user;
					getUserNickname();
					Intent intent=new Intent(LoginActivity.this,MainActivity.class);
					startActivity(intent);
					finish();
				}
   	 			}
   	 }
   };
   private Handler handler1 = new Handler() {
	   	 public void handleMessage(Message msg) {
	   	 switch (msg.what) {
	   	 case GET_NICKNAME:
	   	      		data=(String)msg.obj;
	   	      		Gson gson = new Gson();
	   	      		user1 = gson.fromJson(data, Userget.class);
	   	      		user=new User(username,user1.getNickname(),password);
	   	      		User.user=user;
	   	 		}
	   	 }
	   };
	
	protected void onCreate(Bundle savedInstanceState){
   	  super.onCreate(savedInstanceState);
   	  requestWindowFeature(Window.FEATURE_NO_TITLE);
   	  setContentView(R.layout.activity_login);
   	  
   	  usernameEdt=(EditText)findViewById(R.id.login_userEdt);
   	  passwordEdt=(EditText)findViewById(R.id.login_passwordEdt);
   	  registerBtn=(TextView)findViewById(R.id.registerBtn);
   	  registerBtn.setOnClickListener(new OnClickListener(){
   		  public void onClick(View v){
   			  Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
   			  startActivity(intent);
   			  finish();
   		  }
   	  });
   	  
   	  loginBtn=(Button)findViewById(R.id.loginBtn);
   	  loginBtn.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			username=usernameEdt.getText().toString();
			password=passwordEdt.getText().toString();
			jsonData=new JSONObject();
			if(username.equals("")||password.equals("")){
				Toast.makeText(LoginActivity.this,"输入用户名或密码", Toast.LENGTH_SHORT).show();
			}
			else{
			try {
				jsonData.put("username", username);
				jsonData.put("password", password);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			address = HttpUtil.URL+"/LoginServlet";
			new Thread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					String response=HttpUtil.sendHttpURLByPost(address,jsonData.toString());
					Message message = new Message();
					message.what = SHOW_RESPONSE;
					message.obj = response;
					handler.sendMessage(message);
				}
				
			}).start();
			}
		}
   	  });
   }
	private void getUserNickname(){
		address = HttpUtil.URL+"/DataServlet?username="+User.user.getUserid();
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String response=HttpUtil.getData(address);
				Message message = new Message();
				message.what = GET_NICKNAME;
				message.obj = response;
				handler1.sendMessage(message);
			}
			
		}).start();
	}
}