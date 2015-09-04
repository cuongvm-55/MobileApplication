package com.luvsoft.entities;

import com.luvsoft.utils.DatabaseTags;
import com.mongodb.DBObject;

public class User extends AbstractEntity{
	private String id;
	private String username;
	private String password;
	private String age;
	
	
	public User(){
		id = "";
		username = "";
		password = "";
		age = "";
	}
	
	public User(DBObject dbobject){
		id = dbobject.get(DatabaseTags.TAG_ID).toString();
		username = dbobject.get(DatabaseTags.TAG_USERNAME).toString();
		password = dbobject.get(DatabaseTags.TAG_PASSWORD).toString();
		age = dbobject.get(DatabaseTags.TAG_AGE).toString();
	}

	@Override
	public void setObject(DBObject dbobject){
		id = dbobject.get(DatabaseTags.TAG_ID).toString();
		username = dbobject.get(DatabaseTags.TAG_USERNAME).toString();
		password = dbobject.get(DatabaseTags.TAG_PASSWORD).toString();
		age = dbobject.get(DatabaseTags.TAG_AGE).toString();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", age=" + age + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
