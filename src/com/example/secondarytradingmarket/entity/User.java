package com.example.secondarytradingmarket.entity;

public class User {
	public static User user;
	public User(){
		
	}
	public User(String userid,String password){
		super();
		this.userid=userid;
		this.userpassword=password;
	}
	public User(String userid, String nickname, String userpassword) {
		super();
		this.userid = userid;
		this.nickname = nickname;
		this.userpassword = userpassword;
	}
	public User(String username, String nickname, String sex, String school, String faculty, String speciality,
			String phone, String qQ, String weixin) {
		super();
		this.username = username;
		this.nickname = nickname;
		this.sex = sex;
		this.school = school;
		this.faculty = faculty;
		this.speciality = speciality;
		this.phone = phone;
		QQ = qQ;
		this.weixin = weixin;
	}
	public User(String userid, String userpassword, String question, String answer) {
		super();
		this.userid = userid;
		this.userpassword = userpassword;
		this.question = question;
		this.answer = answer;
	}
	public User(String userid, String username, String nickname, String userpassword, String question, String answer,
			String sex, String school, String faculty, String speciality, String phone, String qQ, String weixin) {
		super();
		this.userid = userid;
		this.username = username;
		this.nickname = nickname;
		this.userpassword = userpassword;
		this.question = question;
		this.answer = answer;
		this.sex = sex;
		this.school = school;
		this.faculty = faculty;
		this.speciality = speciality;
		this.phone = phone;
		QQ = qQ;
		this.weixin = weixin;
	}
	private String userid;
	private String username;
	private String nickname;
	private String userpassword;
	private String question;
	private String answer;
	private String sex;
	private String school;
	private String faculty;
	private String speciality;
	private String phone;
	private String QQ;
	private String weixin;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
}
