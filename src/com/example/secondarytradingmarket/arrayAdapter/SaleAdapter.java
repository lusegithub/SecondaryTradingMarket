package com.example.secondarytradingmarket.arrayAdapter;

import java.util.ArrayList;
import java.util.List;

import com.example.secondarytradingmarket.R;
import com.example.secondarytradingmarket.activity.OtherUserDataActivity;
import com.example.secondarytradingmarket.entity.supplyorbuy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SaleAdapter  extends BaseAdapter{

	private int resourceId;
	private String username;
	private TextView nicknameText,descriptionText;
	private List<supplyorbuy> objects;
	private LayoutInflater inflater;
	Context context;
	
	public SaleAdapter(Context context, int resource, List<supplyorbuy> objects) {
		
		super();
		this.objects=objects;
		inflater=LayoutInflater.from(context);
		resourceId=resource;
		this.context=context;
		// TODO Auto-generated constructor stub
	}
    @SuppressLint("ViewHolder")
	
    public int getCount() {
		int count = 0;
		if (objects!= null) {
			count = objects.size();
		}
		return count;
	}
    public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
    @SuppressLint("ViewHolder")
	public View getView(int position, View convertView, ViewGroup parent) {
          convertView=inflater.inflate(resourceId, null);
          nicknameText=(TextView)convertView.findViewById(R.id.sale_item_nicknameText);
  		  descriptionText=(TextView)convertView.findViewById(R.id.sale_item_descriptionText);
  		  
  		  onclick(position);
  		  return convertView;
    }
	public void onclick(final int p) {
		// TODO Auto-generated method stub
		final ArrayList<supplyorbuy> supplyorbuyList=new ArrayList<supplyorbuy>();
		final ArrayList<TextView> nicknameTextList=new ArrayList<TextView>();
		final ArrayList<TextView> descriptionTex=new ArrayList<TextView>();
        for (int i = 0; i <=getItemId(p); i++){
        	nicknameTextList.add(nicknameText);
        	descriptionTex.add(descriptionText);
        	supplyorbuyList.add(getItem(p));
        }
        nicknameTextList.get(p).setText(supplyorbuyList.get(p).getNickname());
        nicknameTextList.get(p).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent=new Intent(context,OtherUserDataActivity.class);
				Bundle bundle=new Bundle();
				bundle.putString("username", supplyorbuyList.get(p).getUserid());
				intent.putExtra("bundle", bundle);
				context.startActivity(intent);
			}
        	
        });
        descriptionTex.get(p).setText(supplyorbuyList.get(p).getDescriptions());
        
	}
	@Override
	public supplyorbuy getItem(int position) {
		// TODO Auto-generated method stub
		return objects.get(position);
	}
    
}
