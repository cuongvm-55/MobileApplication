package com.luvsoft.entities;

import com.luvsoft.utils.DatabaseTags;
import com.mongodb.DBObject;

public class Episode extends AbstractEntity {
    private String id;
    private int order;
    private String resource;
    private String link;
    private String quality;
    private String movieId;

    public Episode() {

    }

    public Episode(DBObject dbobject) {
    	setObject(dbobject);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movie_id) {
        this.movieId = movie_id;
    }

	@Override
	public String toString() {
		return "Episode [id=" + id + ", order=" + order + ", resource="
				+ resource + ", link=" + link + ", quality=" + quality
				+ ", movieId=" + movieId + "]";
	}

	@Override
	public void setObject(DBObject dbobject) {
		id = dbobject.get(DatabaseTags.TAG_ID).toString();
        order = Integer.valueOf(dbobject.get(DatabaseTags.TAG_ORDER).toString());
        resource = dbobject.get(DatabaseTags.TAG_RESOURCE).toString();
        link = dbobject.get(DatabaseTags.TAG_LINK).toString();
        quality = dbobject.get(DatabaseTags.TAG_QUALITY).toString();
        movieId = dbobject.get(DatabaseTags.TAG_MOVIE_ID).toString();
	}
	
}
