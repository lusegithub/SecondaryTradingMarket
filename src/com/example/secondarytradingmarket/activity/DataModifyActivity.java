package com.example.secondarytradingmarket.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONObject;

import com.example.secondarytradingmarket.R;
import com.example.secondarytradingmarket.entity.User;
import com.example.secondarytradingmarket.httpUtil.HttpUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class DataModifyActivity extends Activity{

	private TextView exitBtn;
	private Button confmodBtn;
	private EditText datamodnickEdt,datamodschEdt,datamoddepEdt,datamodmajEdt,datamodphoneEdt,datamodqqEdt,datamodweixEdt;
	private String username,sex,datamodnick,datamodsch,datamoddep,datamodmaj,datamodphone,datamodqq,datamodweix;
	private RadioButton chooseBtn;
	private RadioGroup radio;
	private int radioButtonId;
	
	private JSONObject jsonData;
	private String check="",data;
    private String address;
    public static final int SHOW_RESPONSE = 0;
    private Handler handler = new Handler() {
   	 public void handleMessage(Message msg) {
   	 switch (msg.what) {
   	 case SHOW_RESPONSE:
   	      check =(String)msg.obj;
   	      if(check.equals("true"))
				{
					AlertDialog.Builder dialog=new AlertDialog.Builder(DataModifyActivity.this);
					dialog.setTitle("消息");
					dialog.setMessage("信息修改成功,请退出账号并重新登录");
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
	   	  setContentView(R.layout.data_modify);
	   	  
	   	  
	   	  datamodnickEdt=(EditText)findViewById(R.id.datamodnickEdt);
	   	  datamodschEdt=(EditText)findViewById(R.id.datamodschEdt);
	   	  datamoddepEdt=(EditText)findViewById(R.id.datamoddepEdt);
	   	  datamodmajEdt=(EditText)findViewById(R.id.datamodmajEdt);
	   	  datamodphoneEdt=(EditText)findViewById(R.id.datamodphoneEdt);
	   	  datamodqqEdt=(EditText)findViewById(R.id.datamodqqEdt);
	   	  datamodweixEdt=(EditText)findViewById(R.id.datamodweixEdt);
	   	  radio=(RadioGroup)findViewById(R.id.dataModSexGro);
	   	  
	   	  exitBtn=(TextView)findViewById(R.id.data_modify_ExitBtn);
	   	  confmodBtn=(Button)findViewById(R.id.confmodBtn);
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
				sex=chooseBtn.getText().toString();
			}
			
	   	  });
	   	  confmodBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				datamodnick=datamodnickEdt.getText().toString();
				datamodsch=datamodschEdt.getText().toString();
				datamoddep=datamoddepEdt.getText().toString();
				datamodmaj=datamodmajEdt.getText().toString();
				datamodphone=datamodphoneEdt.getText().toString();
				datamodqq=datamodqqEdt.getText().toString();
				datamodweix=datamodweixEdt.getText().toString();
				username=User.user.getUserid();
			
				if(datamodnick.equals("")||datamodsch.equals("")||datamoddep.equals("")||datamodmaj.equals("")||datamodphone.equals("")||datamodqq.equals("")||datamodweix.equals("")){
					Toast.makeText(DataModifyActivity.this,"请把信息填完整", Toast.LENGTH_SHORT).show();
				}else {
				jsonData=new JSONObject();
				try {
					jsonData.put("nickname", datamodnick);
					jsonData.put("sex", sex);
					jsonData.put("school", datamodsch);
					jsonData.put("department", datamoddep);
					jsonData.put("major",datamodmaj);
					jsonData.put("phone",datamodphone);
					jsonData.put("qq",datamodqq);
					jsonData.put("weixin",datamodweix);
					jsonData.put("username", username);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				address = HttpUtil.URL+"/DataModifyServlet";
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
