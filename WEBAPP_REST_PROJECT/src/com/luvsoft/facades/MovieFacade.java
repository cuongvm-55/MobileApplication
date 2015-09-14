package com.luvsoft.facades;

import java.util.ArrayList;
import java.util.List;

import com.luvsoft.entities.Movie;
import com.luvsoft.utils.DatabaseTags;
import com.luvsoft.utils.MongoDBConnection;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MovieFacade extends AbstractFacade {
	private static final int PAGE_SIZE = 12;
	
    @Override
    public String getCollectionName() {
        return DatabaseTags.COLLECTION_NAME_MOVIE;
    }

    @Override
    public Movie mapObject(DBObject dbobject) {
        Movie movie = new Movie(dbobject);
        return movie;
    }
    
    public boolean getMovieListByPagination(int pageNumber, List<Movie> retList) {
    	MongoDBConnection dbConnection = MongoDBConnection.getInstance();
    	if( dbConnection.getDB() == null )
    	{
    		System.err.printf("Cannot get Database...");
        	return false;		
    	}
    	
    	DBCollection collection = dbConnection.getDB().getCollection(getCollectionName());
        if( collection == null )
        {
        	System.err.printf("Collection is NULL: %s", getCollectionName());
        	return false;
        }
        if( retList == null )
        {
        	retList = new ArrayList<Movie>();
        }
        try{	
        	DBCursor cursor = collection.find().skip((pageNumber - 1) * PAGE_SIZE).limit(PAGE_SIZE);
	        while (cursor.hasNext()) {
	        	retList.add(mapObject(cursor.next()));
	        }
	        return true;
        }catch(Exception e)
        {
        	return false;
        }
    }
}
