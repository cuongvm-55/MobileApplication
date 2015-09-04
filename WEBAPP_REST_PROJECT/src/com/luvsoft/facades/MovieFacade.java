package com.luvsoft.facades;

import com.luvsoft.entities.Movie;
import com.luvsoft.utils.DatabaseTags;
import com.mongodb.DBObject;

public class MovieFacade extends AbstractFacade {
    @Override
    public String getCollectionName() {
        return DatabaseTags.COLLECTION_NAME_MOVIE;
    }

    @Override
    public Movie mapObject(DBObject dbobject) {
        Movie movie = new Movie(dbobject);
        return movie;
    }
}
