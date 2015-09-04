package com.luvsoft.facades;

import java.util.List;

import com.luvsoft.entities.Episode;
import com.luvsoft.utils.DatabaseTags;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class EpisodeFacade extends AbstractFacade {
    @Override
    public String getCollectionName() {
        return DatabaseTags.COLLECTION_NAME_EPISODE;
    }

    @Override
    public Episode mapObject(DBObject dbobject) {
        Episode episode = new Episode(dbobject);
        return episode;
    }

    public boolean getEpisodeByMovieId(String id, List<Episode> ret) {
        try {
        	BasicDBObject query = new BasicDBObject(DatabaseTags.TAG_MOVIE_ID, id );
			return findByQuery(query, ret);
		} catch (Exception e) {
			return false;
		}
    }
}
