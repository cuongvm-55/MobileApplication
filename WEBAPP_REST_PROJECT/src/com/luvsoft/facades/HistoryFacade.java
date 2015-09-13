package com.luvsoft.facades;

import java.util.List;

import org.bson.types.ObjectId;

import com.luvsoft.entities.History;
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
     * Get History lists of a userId
     */
	public boolean getHistoricsForUserId(String userId, List<History> historics){
		try{
        	BasicDBObject query = new BasicDBObject(DatabaseTags.TAG_USER_ID, new ObjectId(userId));
        	return findByQuery(query, historics);
        }catch(Exception e)
        {
        	e.printStackTrace();
        	return false;
        }
	}
	
	public boolean saveHistory(History history){
    	try{
    		BasicDBObject query = new BasicDBObject(DatabaseTags.TAG_USER_ID, new ObjectId(history.getUserId()))
    	                  .append(DatabaseTags.TAG_USER_MOVIE_ID, new ObjectId(history.getMovieId()));
    		return save(query);
    	}catch(Exception e)
    	{
    		return false;
    	}
    }
}
