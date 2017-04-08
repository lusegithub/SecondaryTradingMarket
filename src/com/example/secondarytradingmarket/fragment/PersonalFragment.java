package com.example.secondarytradingmarket.fragment;

import java.util.HashMap;
import java.util.Map;

import com.example.secondarytradingmarket.R;
import com.example.secondarytradingmarket.activity.DataActivity;
import com.example.secondarytradingmarket.activity.DataModifyActivity;
import com.example.secondarytradingmarket.activity.LoginActivity;
import com.example.secondarytradingmarket.activity.PasswordModifyActivity;
import com.example.secondarytradingmarket.activity.SendActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class PersonalFragment extends Fragment{

	private View view;
	private Button dataBtn,sendBtn,exitCurrAccBtn,password_modify,data_modify,collect;
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
	        view=inflater.inflate(R.layout.personal,container,false); 
	        return view;
	    }
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		dataBtn=(Button)view.findViewById(R.id.data);
		sendBtn=(Button)view.findViewById(R.id.sendBtn);
		exitCurrAccBtn=(Button)view.findViewById(R.id.exitCuraccBtn);
		password_modify=(Button)view.findViewById(R.id.password_modify);
		data_modify=(Button)view.findViewById(R.id.data_modify);
		
		dataBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),DataActivity.class);
				startActivity(intent);
			}
			
		});
		
		sendBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),SendActivity.class);
				startActivity(intent);
			}
			
		});
		
		exitCurrAccBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),LoginActivity.class);
				startActivity(intent);
				getActivity().finish();
			}
			
		});
		
		password_modify.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),PasswordModifyActivity.class);
				startActivity(intent);
			}
			
		});
		
		data_modify.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),DataModifyActivity.class);
				startActivity(intent);
			}
			
		});
	}
}
