package com.example.secondarytradingmarket.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.secondarytradingmarket.R;
import com.example.secondarytradingmarket.arrayAdapter.BuyAdapter;
import com.example.secondarytradingmarket.entity.supplyorbuy;
import com.example.secondarytradingmarket.httpUtil.HttpUtil;
import com.example.secondarytradingmarket.mine.MyListView;
import com.example.secondarytradingmarket.mine.MyListView.OnRefreshListener;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BuyFragment extends Fragment{
	
	private View view;
	private List<supplyorbuy> buylist=new ArrayList<supplyorbuy>();
	private MyListView listView;
	private BuyAdapter adapter;
	private String address,dataxx;
	public static final int SHOW_RESPONSE = 0;
    private Handler handler = new Handler() {
   	 public void handleMessage(Message msg) {
   	 switch (msg.what) {
   	 case SHOW_RESPONSE:
   		 		buylist.clear();
   	      		dataxx=(String)msg.obj;
   	      		try {
   	      				JSONArray jsonArray = new JSONArray(dataxx);
   	      				for (int i = 0; i < jsonArray.length(); i++) {
   	      					JSONObject jsonObject = jsonArray.getJSONObject(i);
   	      					String username = jsonObject.getString("username");
   	      					String description = jsonObject.getString("description");
   	      					String nickname=jsonObject.getString("nickname");
	      					supplyorbuy buy=new supplyorbuy(username, description,nickname);
	      					buylist.add(buy);
   	      				}
   	      			} catch (Exception e) {
   	      				e.printStackTrace();
   	      			};
   	 	}
   	 }
   };
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
        view=inflater.inflate(R.layout.buy,container,false);
        return view;
    }
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
		initBuy();
		adapter=new BuyAdapter(getActivity(),R.layout.buy_item,buylist);
		listView=(MyListView)view.findViewById(R.id.buy_listview);
		listView.setAdapter(adapter);
		listView.setonRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				new AsyncTask<Void, Void, Void>() {
					protected Void doInBackground(Void... params) {
						try {
							initBuy();
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
	
	private void initBuy(){
		address = HttpUtil.URL+"/ReturnBuyServlet?get=true";
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
