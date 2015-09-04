package com.luvsoft.entities;

import com.luvsoft.utils.DatabaseTags;
import com.mongodb.DBObject;

public class History extends AbstractEntity{
	private String id;
	private String userId;
	private String movieId;
	
	public History(){
		
	}
	
	public History(DBObject dbobject){
		id = dbobject.get(DatabaseTags.TAG_ID).toString();
		userId = dbobject.get(DatabaseTags.TAG_USER_ID).toString();
		movieId = dbobject.get(DatabaseTags.TAG_MOVIE_ID).toString();
	}

	@Override
	public void setObject(DBObject dbobject){
		id = dbobject.get(DatabaseTags.TAG_ID).toString();
		userId = dbobject.get(DatabaseTags.TAG_USER_ID).toString();
		movieId = dbobject.get(DatabaseTags.TAG_MOVIE_ID).toString();
	}
	
	@Override
	public String toString() {
		return "History [id=" + id + ", userId=" + userId + ", movieId="
				+ movieId + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

}
