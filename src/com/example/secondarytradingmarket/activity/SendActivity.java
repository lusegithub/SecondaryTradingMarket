package com.example.secondarytradingmarket.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONObject;

import com.example.secondarytradingmarket.R;
import com.example.secondarytradingmarket.entity.User;
import com.example.secondarytradingmarket.httpUtil.HttpUtil;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.view.Window;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class SendActivity extends Activity{

	private String username,description,type="";
	private EditText send_descriptition;
	private TextView sendBtn,exitBtn;
	private RadioButton chooseBtn;
	private RadioGroup radio;
	private int radioButtonId;
	private JSONObject jsonData;
	private String address,data;
	protected void onCreate(Bundle savedInstanceState){
	   	  super.onCreate(savedInstanceState);
	   	  requestWindowFeature(Window.FEATURE_NO_TITLE);
	   	  setContentView(R.layout.send);
	   	  
	   	  send_descriptition=(EditText)findViewById(R.id.send_descriptition);
	   	  radio=(RadioGroup)findViewById(R.id.send_sendradioGro);
	   	  sendBtn=(TextView)findViewById(R.id.send);
	   	  exitBtn=(TextView)findViewById(R.id.exit);
	   	  
	   	  exitBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
	   		  
	   	  });
	   	  radio.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				radioButtonId=group.getCheckedRadioButtonId();
				chooseBtn=(RadioButton)findViewById(radioButtonId);
				type=chooseBtn.getText().toString();
			}
			
	   	  });
	   	  sendBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				  description=send_descriptition.getText().toString();
			   	  username=User.user.getUserid();
			   	if(type.equals("")){
					Toast.makeText(SendActivity.this,"请选择出售或求购", Toast.LENGTH_SHORT).show();
					return;
				}
			   	  jsonData=new JSONObject();
			   	  try {
					jsonData.put("description", description);
					jsonData.put("type", type);
					jsonData.put("username", username);
					jsonData.put("nickname",User.user.getNickname());
			   	  } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			   	  }
			   	  address = HttpUtil.URL+"/SendServlet";
			   	try {
					data=URLEncoder.encode(jsonData.toString(),"UTF-8");
					//data=URLDecoder.decode(jsonData.toString(),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						HttpUtil.sendHttpURLByPost(address,data);
					}
					
				}).start();
				finish();
			}
	   		  
	   	  });
	   	 
	}
}
