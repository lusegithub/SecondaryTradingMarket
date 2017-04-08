package com.example.secondarytradingmarket.activity;


import com.example.secondarytradingmarket.R;
import com.example.secondarytradingmarket.entity.User;
import com.example.secondarytradingmarket.entity.Userget;
import com.example.secondarytradingmarket.httpUtil.HttpUtil;
import com.google.gson.Gson;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

public class DataActivity extends Activity{
	private TextView data_username,data_nickname,data_sex,data_school,data_department,data_major,data_qqnumber,data_phonenumber
	,data_weixin,dataExitBtn;
    private String address,data;
    private Userget user;
    public static final int SHOW_RESPONSE = 0;
    private Handler handler = new Handler() {
   	 public void handleMessage(Message msg) {
   	 switch (msg.what) {
   	 case SHOW_RESPONSE:
   	      		data=(String)msg.obj;
   	      		Gson gson = new Gson();
   	      		user = gson.fromJson(data, Userget.class);
   	      		data_username.setText(user.getUsername());
   	      		data_nickname.setText(user.getNickname());
   	      		data_sex.setText(user.getSex());
   	      		data_school.setText(user.getSchool());
   	      		data_department.setText(user.getDepartment());
   	      		data_major.setText(user.getMajor());
   	      		data_qqnumber.setText(user.getQq());
   	      		data_phonenumber.setText(user.getPhone());
   	      		data_weixin.setText(user.getWeixin());
   	 		}
   	 }
   };
	
	protected void onCreate(Bundle savedInstanceState){
	   	  super.onCreate(savedInstanceState);
	   	  requestWindowFeature(Window.FEATURE_NO_TITLE);
	   	  setContentView(R.layout.activity_data);
	   	  
	   	data_username=(TextView)findViewById(R.id.data_username);
	   	data_nickname=(TextView)findViewById(R.id.data_nickname);
	   	data_sex=(TextView)findViewById(R.id.data_sex);
	   	data_school=(TextView)findViewById(R.id.data_school);
	   	data_department=(TextView)findViewById(R.id.data_department);
	   	data_major=(TextView)findViewById(R.id.data_major);
	   	data_qqnumber=(TextView)findViewById(R.id.data_qqnumber);
	   	data_phonenumber=(TextView)findViewById(R.id.data_phonenumber);
	   	data_weixin=(TextView)findViewById(R.id.data_weixin);
	   	dataExitBtn=(TextView)findViewById(R.id.dataExitBtn);
	   	
	   	dataExitBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
	   		
	   	});
	   	
	   	address = HttpUtil.URL+"/DataServlet?username="+User.user.getUserid();
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String response=HttpUtil.getData(address);
				Message message = new Message();
				message.what = SHOW_RESPONSE;
				message.obj = response;
				handler.sendMessage(message);
			}
			
		}).start();
	}

}
