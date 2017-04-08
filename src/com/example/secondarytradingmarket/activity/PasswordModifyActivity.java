package com.example.secondarytradingmarket.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.secondarytradingmarket.R;
import com.example.secondarytradingmarket.entity.User;
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
import android.widget.TextView;
import android.widget.Toast;

public class PasswordModifyActivity extends Activity{
	private TextView passwordModifyExit;
	private Button conModifyPassBtn;
	private TextView ansEdt,oldpasswordEdt,newpasswordEdt1,newpasswordEdt2;
	private String ans,oldpassword,newpassword1,newpassword2,username;
	
	private JSONObject jsonData;
	private String check="",data;
    private String address;
    public static final int SHOW_RESPONSE = 0;
    private Handler handler = new Handler() {
   	 public void handleMessage(Message msg) {
   	 switch (msg.what) {
   	 case SHOW_RESPONSE:
   	      check =(String)msg.obj;
   	      if(check.equals("false")){
					AlertDialog.Builder dialog=new AlertDialog.Builder(PasswordModifyActivity.this);
					dialog.setTitle("消息");
					dialog.setMessage("密码或答案填写错误，请重新填写");
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
					AlertDialog.Builder dialog=new AlertDialog.Builder(PasswordModifyActivity.this);
					dialog.setTitle("消息");
					dialog.setMessage("密码修改成功");
					dialog.setCancelable(false);
					dialog.setPositiveButton("确定", new DialogInterface.
					OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
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
	   	setContentView(R.layout.password_modify);
	   	
	   	passwordModifyExit=(TextView)findViewById(R.id.passwordModifyExit);
	   	conModifyPassBtn=(Button)findViewById(R.id.conModifyPassBtn);
	   	ansEdt=(TextView)findViewById(R.id.modify_security_ansEdt);
	   	oldpasswordEdt=(TextView)findViewById(R.id.modifyOldpasswordEdt);
	   	newpasswordEdt1=(TextView)findViewById(R.id.modifyNewpasswordEdt);
	   	newpasswordEdt2=(TextView)findViewById(R.id.modifyNewpasswordEdt2);
	   	
	   	passwordModifyExit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
	   		
	   	});
	   	
	   	conModifyPassBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ans=ansEdt.getText().toString();
				oldpassword=oldpasswordEdt.getText().toString();
				newpassword1=newpasswordEdt1.getText().toString();
				newpassword2=newpasswordEdt2.getText().toString();
				username=User.user.getUserid();
				jsonData=new JSONObject();
				if(!newpassword1.equals(newpassword2)){
					Toast.makeText(PasswordModifyActivity.this,"两次新密码输入不一致，请重新输入", Toast.LENGTH_SHORT).show();
				}
				else{
				try {
					jsonData.put("username", username);
					jsonData.put("ans", ans);
					jsonData.put("oldpassword", oldpassword);
					jsonData.put("newpassword",newpassword2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				address = HttpUtil.URL+"/PasswordModifyServlet";
				try {
					data=URLEncoder.encode(jsonData.toString(),"UTF-8");
					//data=URLDecoder.decode(jsonData.toString(),"UTF-8");
					Log.d("test", data.toString());
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String response=HttpUtil.sendHttpURLByPost(address,data);
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
