package com.luvsoft.facades;

import java.util.List;

import org.bson.types.ObjectId;

import com.luvsoft.entities.Episode;
import com.luvsoft.utils.DatabaseTags;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class EpisodeFacade extends AbstractFacade {
    @Override
    public String getCollectionName() {
        return DatabaseTags.EPISODE_COLLECTION_NAME;
    }

    @Override
    public Episode mapObject(DBObject dbobject) {
        Episode episode = new Episode(dbobject);
        return episode;
    }

    public boolean getEpisodeByMovieId(String id, List<Episode> ret) {
        try {
        	BasicDBObject query = new BasicDBObject();
            ObjectId obj = new ObjectId(id);
        	query.put(DatabaseTags.TAG_MOVIE_ID, obj );
			return findByQuery(query, ret);
		} catch (Exception e) {
			return false;
		}
    }
}
