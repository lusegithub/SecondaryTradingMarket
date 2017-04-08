package com.example.secondarytradingmarket.activity;

import com.example.secondarytradingmarket.R;
import com.example.secondarytradingmarket.fragment.*;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.*;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;


public class MainActivity extends FragmentActivity{
       
	private Button homepageBtn,messageBtn,personalBtn;
	private FragmentManager manager;
	private FragmentTransaction transaction;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
	   	  super.onCreate(savedInstanceState);
	   	  requestWindowFeature(Window.FEATURE_NO_TITLE);
	   	  setContentView(R.layout.main);
	   	  
	   homepageBtn=(Button)findViewById(R.id.homepageBtn);
	   messageBtn=(Button)findViewById(R.id.buyBtn);
	   personalBtn=(Button)findViewById(R.id.personalBtn);
	   
	   SaleFragment salefragment=new SaleFragment();
	   BuyFragment buyfragment=new BuyFragment();
	   PersonalFragment personalFragment=new PersonalFragment();
	   
	   manager = getSupportFragmentManager();  
	   
	   transaction = manager.beginTransaction();  
	   transaction.add(R.id.mainFrameLayout_container,salefragment,"sale");
	   transaction.add(R.id.mainFrameLayout_container,buyfragment,"buy");
	   transaction.add(R.id.mainFrameLayout_container,personalFragment,"personal");
	   transaction.commit();
	   
	   transaction = manager.beginTransaction();
	   transaction.hide(buyfragment);
	   transaction.hide(personalFragment);
	   transaction.commit();
	   
	   
	   homepageBtn.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			transaction = manager.beginTransaction();
			SaleFragment fragment1=(SaleFragment)manager.findFragmentByTag("sale");
			PersonalFragment fragment2=(PersonalFragment)manager.findFragmentByTag("personal");
			BuyFragment fragment3=(BuyFragment)manager.findFragmentByTag("buy");
			transaction.hide(fragment3);
			transaction.hide(fragment2);
			transaction.show(fragment1);
			transaction.commit();
		}
		   
	   });
	   
	   messageBtn.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			transaction = manager.beginTransaction();
			SaleFragment fragment1=(SaleFragment)manager.findFragmentByTag("sale");
			PersonalFragment fragment2=(PersonalFragment)manager.findFragmentByTag("personal");
			BuyFragment fragment3=(BuyFragment)manager.findFragmentByTag("buy");
			transaction.hide(fragment2);
			transaction.hide(fragment1);
			transaction.show(fragment3);
			transaction.commit();
		}
		   
	   });
	   
	   personalBtn.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			transaction = manager.beginTransaction();
			SaleFragment fragment1=(SaleFragment)manager.findFragmentByTag("sale");
			PersonalFragment fragment2=(PersonalFragment)manager.findFragmentByTag("personal");
			BuyFragment fragment3=(BuyFragment)manager.findFragmentByTag("buy");
			transaction.hide(fragment3);
			transaction.hide(fragment1);
			transaction.show(fragment2);
		    transaction.commit();
		}
		   
	   });
	}
}
