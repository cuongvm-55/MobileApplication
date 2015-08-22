package com.luvsoft.facades;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.luvsoft.entities.AbstractEntity;
import com.luvsoft.utils.DatabaseTags;
import com.luvsoft.utils.MongoDBConnection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public abstract class AbstractFacade {	
    // ///////////////////////////////////////////////////////////
    // ! Function is used to get all document in a collection
    // !
    // ! @return ArrayList list of documents in this collection
    // ///////////////////////////////////////////////////////////
    @SuppressWarnings("unchecked")
	public <T extends AbstractEntity> boolean findAll(List<T> retList) {
        DBCollection collection = MongoDBConnection.getInstance().getDB().getCollection(getCollectionName());
        if( collection == null )
        {
        	System.out.printf("Collection is NULL: %s", getCollectionName());
        	return false;
        }
        DBCursor cursor = collection.find();
        List<T> list = new ArrayList<T>();
        while (cursor.hasNext()) {
            list.add((T) mapObject(cursor.next()));
        }
        retList = list;
        return true;
    }

    // ///////////////////////////////////////////////////////////
    // ! Function is used to get all document in a collection by Id
    // !
    // ! @return An Object in the collection who has the Id
    // ///////////////////////////////////////////////////////////
    @SuppressWarnings("unchecked")
	public <T extends AbstractEntity> boolean findById(String id, T retobject) {
        DBCollection collection = MongoDBConnection.getInstance().getDB().getCollection(getCollectionName());
        if( collection == null )
        {
        	System.out.printf("Collection is NULL: %s", getCollectionName());
        	return false;
        }
        BasicDBObject query = new BasicDBObject();
        query.put(DatabaseTags.TAG_ID, new ObjectId(id));
        DBObject dbobj = collection.findOne(query);
        if( dbobj != null )
        {
        	T object = (T) mapObject(dbobj);
        	retobject = object;
        	return true;
        }
        
        return false;
    }

    // ///////////////////////////////////////////////////////////
    // ! Function is used to get all document in a collection by a query
    // !
    // ! @return ArrayList list of documents in this collection
    // ///////////////////////////////////////////////////////////
    @SuppressWarnings("unchecked")
	public <T extends AbstractEntity> boolean findByQuery(BasicDBObject query, List<T> retList) {
    	MongoDBConnection dbConnection = MongoDBConnection.getInstance();
    	if( dbConnection.getDB() == null )
    	{
    		System.out.printf("Cannot get Database...");
        	return false;		
    	}
    	DBCollection collection = dbConnection.getDB().getCollection(getCollectionName());
        if( collection == null )
        {
        	System.out.printf("Collection is NULL: %s", getCollectionName());
        	return false;
        }
        DBCursor cursor = collection.find(query);
        List<T> list = new ArrayList<T>();
        while (cursor.hasNext()) {
            list.add((T) mapObject(cursor.next()));
        }
        retList = list;
        return true;
    }

    public abstract String getCollectionName();

    public abstract AbstractEntity mapObject(DBObject dbobject);
}
