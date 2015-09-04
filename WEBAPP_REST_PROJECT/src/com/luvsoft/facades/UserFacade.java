package com.luvsoft.facades;

import java.util.ArrayList;
import java.util.List;

import com.luvsoft.entities.User;
import com.luvsoft.utils.DatabaseTags;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class UserFacade extends AbstractFacade{
	@Override
	public String getCollectionName() {
        return DatabaseTags.COLLECTION_NAME_USER;
    }

    @Override
    public User mapObject(DBObject dbobject) {
        User user = new User(dbobject);
        return user;
    }
	 
    /*
     * Example:
     * query = new BasicDBObject("j", new BasicDBObject("$ne", 3))
        .append("k", new BasicDBObject("$gt", 10));
        j != 3 && k > 10
     */
    public boolean getUser(String username, String password){
        try{
        	BasicDBObject query = new BasicDBObject(DatabaseTags.TAG_USERNAME, username);
        	query.append(DatabaseTags.TAG_PASSWORD, password);
        	System.out.println("query: " + query.toString());
        	List<User> userList = new ArrayList<User>();
        	return findByQuery(query, userList) && (userList.size() > 0);
        }catch(Exception e)
        {
        	return false;
        }
    }
    
    public boolean getUser(String username){
    	try{
    		BasicDBObject query = new BasicDBObject(DatabaseTags.TAG_USERNAME, username);
    		List<User> userList = new ArrayList<User>();
        	return findByQuery(query, userList) && (userList.size() > 0);
    	}catch(Exception e){
    		return false;
    	}
    }
    
    public boolean saveUser(User user){
    	try{
    		BasicDBObject query = new BasicDBObject(DatabaseTags.TAG_USERNAME, user.getUsername())
    	                  .append(DatabaseTags.TAG_PASSWORD, user.getPassword())
    	                  .append(DatabaseTags.TAG_AGE, user.getAge());
    		return save(query);
    	}catch(Exception e)
    	{
    		return false;
    	}
    }
    
}
