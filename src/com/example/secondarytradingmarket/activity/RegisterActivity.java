package com.example.secondarytradingmarket.activity;



import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.example.secondarytradingmarket.R;
import com.example.secondarytradingmarket.httpUtil.HttpUtil;

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
import android.widget.Toast;

public class RegisterActivity extends Activity{
    
	 private EditText register_usernameEdt,register_passwordEdt,register_passwordEdt2,security_que,security_ans;
	 private Button registerBtn;
     private String check="";
     private String ans,que,address;
     public static final int SHOW_RESPONSE = 0;
     private Handler handler = new Handler() {
    	 public void handleMessage(Message msg) {
    	 switch (msg.what) {
    	 case SHOW_RESPONSE:
    	      check =(String)msg.obj;
    	      if(check.equals("false")){
					AlertDialog.Builder dialog=new AlertDialog.Builder(RegisterActivity.this);
					dialog.setTitle("消息");
					dialog.setMessage("注册失败，用户名已使用");
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
				AlertDialog.Builder dialog=new AlertDialog.Builder(RegisterActivity.this);
				dialog.setTitle("消息");
				dialog.setMessage("注册成功");
				dialog.setCancelable(false);
				dialog.setPositiveButton("确定", new DialogInterface.
				OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
						startActivity(intent);
						finish();
					}
				});
				dialog.show();
				}
    	 	}
    	 }
    };
	 
	 protected void onCreate(Bundle savedInstanceState){
	   	  super.onCreate(savedInstanceState);
	   	  requestWindowFeature(Window.FEATURE_NO_TITLE);
	   	  setContentView(R.layout.activity_register);
	   	  
	   	register_usernameEdt=(EditText)findViewById(R.id.register_usernameEdt);
	   	register_passwordEdt=(EditText)findViewById(R.id.register_passwordEdt);
	   	register_passwordEdt2=(EditText)findViewById(R.id.register_passwordEdt2);
	   	security_que=(EditText)findViewById(R.id.register_security_que);
	   	security_ans=(EditText)findViewById(R.id.register_security_ans);
	   	registerBtn=(Button)findViewById(R.id.registerBtn2);
	   	
	   	registerBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String username=register_usernameEdt.getText().toString();
				String password=register_passwordEdt.getText().toString();
				String password2=register_passwordEdt2.getText().toString();
				que=security_que.getText().toString();
				ans=security_ans.getText().toString();
				
				
				if(username.equals("")||password.equals("")||password2.equals("")||que.equals("")||ans.equals("")){
					Toast.makeText(RegisterActivity.this,"输入完整信息", Toast.LENGTH_SHORT).show();
				}
				else if(!password.equals(password2)){
					Toast.makeText(RegisterActivity.this,"两次密码输入不正确", Toast.LENGTH_SHORT).show();
				}
				else
				{
					
					try 
					{
						que=URLEncoder.encode(que,"UTF-8");
						ans=URLEncoder.encode(ans,"UTF-8");
						Log.d("11", que);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					address = HttpUtil.URL+"/RegisterServlet?username="+username+"&password="+password+"&que="+que+"&ans="+ans;
					new Thread(new Runnable(){

						@Override
						public void run() {
							// TODO Auto-generated method stub
							String response=HttpUtil.sendHttpURLByGet(address);
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
}
