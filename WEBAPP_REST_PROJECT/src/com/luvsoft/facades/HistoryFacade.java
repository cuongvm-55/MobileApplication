package com.luvsoft.facades;

import java.util.List;

import com.luvsoft.entities.History;
import com.luvsoft.entities.Movie;
import com.luvsoft.utils.DatabaseTags;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class HistoryFacade extends AbstractFacade{
	@Override
	public String getCollectionName() {
        return DatabaseTags.COLLECTION_NAME_HISTORY;
    }

    @Override
    public History mapObject(DBObject dbobject) {
    	History history = new History(dbobject);
        return history;
    }
    
    /*
     * Get historic movies of a userId
     */
	public boolean getHistoricMovies(String userId, List<Movie> movieList){
		try{
        	BasicDBObject query = new BasicDBObject(DatabaseTags.TAG_USER_ID, userId);  
        	return findByQuery(query, movieList);
        }catch(Exception e)
        {
        	e.printStackTrace();
        	return false;
        }
	}
	
	public boolean saveHistory(History history){
    	try{
    		BasicDBObject query = new BasicDBObject(DatabaseTags.TAG_USER_ID, history.getUserId())
    	                  .append(DatabaseTags.TAG_MOVIE_ID, history.getMovieId());
    		return save(query);
    	}catch(Exception e)
    	{
    		return false;
    	}
    }
}
