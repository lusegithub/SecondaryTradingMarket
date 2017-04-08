package com.example.secondarytradingmarket.entity;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

public class supplyorbuy {
	public supplyorbuy(){
		
	}
	public supplyorbuy(int identity, String userid, String descriptions, String type, Date thetime, File[] image) {
		super();
		this.identity = identity;
		this.userid = userid;
		this.descriptions = descriptions;
		this.type = type;
		this.thetime = thetime;
		this.image = image;
	}
	public supplyorbuy(String userid, String descriptions,String nickname) {
		super();
		this.userid = userid;
		this.descriptions = descriptions;
		this.nickname=nickname;
	}
	private int identity;
	private String userid;
	private String descriptions;
	private String type;
	private Date thetime;
	private File[] image;
	private InputStream[] input;
	private OutputStream[] output;
	private int count;
	private String nickname;
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getIdentity() {
		return identity;
	}
	public void setIdentity(int identity) {
		this.identity = identity;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getThetime() {
		return thetime;
	}
	public void setThetime(Date thetime) {
		this.thetime = thetime;
	}
	public File[] getImage() {
		return image;
	}
	public void setImage(File[] image) {
		this.image = image;
	}
	public InputStream[] getInput() {
		return input;
	}
	public void setInput(InputStream[] input) {
		this.input = input;
	}
	public OutputStream[] getOutput() {
		return output;
	}
	public void setOutput(OutputStream[] output) {
		this.output = output;
	}
	public void setInput(InputStream out,int i){
		this.input[i]=out;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
