package com.luvsoft.facades;

import java.util.List;

import org.bson.types.ObjectId;

import com.luvsoft.entities.Favorite;
import com.luvsoft.utils.DatabaseTags;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class FavoriteFacade extends AbstractFacade{
	@Override
	public String getCollectionName() {
        return DatabaseTags.COLLECTION_NAME_FAVORITE;
    }

    @Override
    public Favorite mapObject(DBObject dbobject) {
    	Favorite favorite = new Favorite(dbobject);
        return favorite;
    }
    
    /*
     * Get favorite lists of a userId
     */
	public boolean getFavoritesForUserId(String userId, List<Favorite> favorites){
		try{
        	BasicDBObject query = new BasicDBObject(DatabaseTags.TAG_USER_ID, new ObjectId(userId));
        	return findByQuery(query, favorites);
        }catch(Exception e)
        {
        	e.printStackTrace();
        	return false;
        }
	}
	
	public boolean saveFavorite(Favorite favorite){
    	try{
    		BasicDBObject query = new BasicDBObject(DatabaseTags.TAG_USER_ID, new ObjectId(favorite.getUserId()))
    	                  .append(DatabaseTags.TAG_USER_MOVIE_ID, new ObjectId(favorite.getMovieId()));
    		return save(query);
    	}catch(Exception e)
    	{
    		return false;
    	}
    }
}
