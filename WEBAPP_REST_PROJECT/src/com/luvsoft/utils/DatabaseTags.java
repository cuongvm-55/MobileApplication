package com.luvsoft.utils;

public class DatabaseTags {
	
	// Database URLs
	public static final String MONGOCONNECTION_MONGOLAB = "mongodb://test:test@ds041992.mongolab.com:41992/test_database";
    public static final String MONGOCONNECTION_LOCALHOST = "mongodb://localhost:27017/moviedb";
	
    // Tags
	public static final String TAG_ID = "_id";
	public static final String TAG_NAME = "Name";
	
	// Movie
	public static final String COLLECTION_NAME_MOVIE = "Movie";

	public static final String TAG_DIRECTOR = "Director";
	public static final String TAG_ACTOR = "Actor";
	public static final String TAG_YEAR = "Year";
	public static final String TAG_STATUS = "Status";
	public static final String TAG_THUMBNAIL = "Thumbnail";
	public static final String TAG_DESCRIPTION = "Description";
	
	// Episode
	public static final String COLLECTION_NAME_EPISODE = "Episode";
	
	public static final String TAG_ORDER = "Order";
	public static final String TAG_RESOURCE = "Resource";
	public static final String TAG_LINK = "Link";
	public static final String TAG_QUALITY = "Quality";
	public static final String TAG_MOVIE_ID = "Movie_id";
	
	// User
	public static final String COLLECTION_NAME_USER = "User";
	public static final String TAG_USERNAME = "Username";
	public static final String TAG_PASSWORD = "Password";
	public static final String TAG_AGE = "Age";
	public static final String TAG_USER_ID = "User_id";
	
	// History
	public static final String COLLECTION_NAME_HISTORY = "History";
	
	// Favorite
	public static final String COLLECTION_NAME_FAVORITE = "Favorite";
	
}
