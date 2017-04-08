package com.example.secondarytradingmarket.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.secondarytradingmarket.R;
import com.example.secondarytradingmarket.arrayAdapter.SaleAdapter;
import com.example.secondarytradingmarket.entity.User;
import com.example.secondarytradingmarket.entity.Userget;
import com.example.secondarytradingmarket.entity.supplyorbuy;
import com.example.secondarytradingmarket.httpUtil.HttpUtil;
import com.example.secondarytradingmarket.mine.MyListView;
import com.example.secondarytradingmarket.mine.MyListView.OnRefreshListener;
import com.google.gson.Gson;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SaleFragment extends Fragment{
 
	private View view;
	private List<supplyorbuy> salelist=new ArrayList<supplyorbuy>();
	
	private SaleAdapter adapter;
	private MyListView listView;
	private String address,dataxx;
	public static final int SHOW_RESPONSE = 0;
    private Handler handler = new Handler() {
   	 public void handleMessage(Message msg) {
   	 switch (msg.what) {
   	 case SHOW_RESPONSE:
   		 		salelist.clear();
   	      		dataxx=(String)msg.obj;
   	      		try {
   	      				JSONArray jsonArray = new JSONArray(dataxx);
   	      				for (int i = 0; i < jsonArray.length(); i++) {
   	      					JSONObject jsonObject = jsonArray.getJSONObject(i);
   	      					String username = jsonObject.getString("username");
   	      					String description = jsonObject.getString("description");
   	      					String nickname=jsonObject.getString("nickname");
	      					supplyorbuy supply=new supplyorbuy(username, description,nickname);
	      					salelist.add(supply);
   	      				}
   	      			} catch (Exception e) {
   	      				e.printStackTrace();
   	      			};
   	 	}
   	 }
   };
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
	        view= inflater.inflate(R.layout.sale,container,false);
	        return view;
	    } 
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		initSale();
		adapter=new SaleAdapter(getActivity(),R.layout.sale_item,salelist);
		listView=(MyListView)view.findViewById(R.id.sale_listview);
		listView.setAdapter(adapter);
		listView.setonRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				new AsyncTask<Void, Void, Void>() {
					protected Void doInBackground(Void... params) {
						try {
							initSale();
							Thread.sleep(1000);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
					
					@Override
					protected void onPostExecute(Void result) {
						adapter.notifyDataSetChanged();
						listView.onRefreshComplete();
					}
				}.execute(null, null, null);
			}
		});
		
	}
	private void initSale(){
		address = HttpUtil.URL+"/ReturnSupplyServlet?get=true";
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String response=HttpUtil.getSupply(address);
				Message message = new Message();
				message.what = SHOW_RESPONSE;
				message.obj = response;
				handler.sendMessage(message);
			}
			
		}).start();
	}
}