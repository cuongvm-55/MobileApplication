package com.luvsoft.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.luvsoft.entities.Episode;
import com.luvsoft.entities.Movie;
import com.luvsoft.facades.EpisodeFacade;
import com.luvsoft.facades.MovieFacade;

// URL: http://localhost:8080/WEBAPP_REST_PROJECT/rest/movieservice/service_name
@Path("/movieservice")
public class MovieService {

	/*
	 * Get an Episode list by a movieId
	 */
    @GET
    @Path("/episodeList/{movieId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEpisodeListByMovieId(@PathParam("movieId") String movieId) throws JSONException {

        /*
         * MovieController movieController = new MovieController();
         * ArrayList<Movie> list = movieController.findAll();
         */
        EpisodeFacade episodeFacade = new EpisodeFacade();
        List<Episode> list = new ArrayList<Episode> ();
        JSONArray ja = new JSONArray();
        if( !episodeFacade.getEpisodeByMovieId(movieId, list) )
        {
        	JSONObject errJsonobject = new JSONObject();
        	errJsonobject.put("ERROR", "No corresponding episode list...");
            ja.put(errJsonobject);
        	System.out.printf("Get episode list fail, Movie Id: %s", movieId);
        }
        else
        {
	        for (Episode movie : list) {
	            JSONObject jsonobject = new JSONObject();
	            jsonobject.put("ID", movie.getId());
	            jsonobject.put("NAME", movie.getResource());
	            ja.put(jsonobject);
	        }
        }
        return Response.status(200).entity(ja.toString()).build();
    }

    /*
	 * Get an Episode by its Id
	 */
    @GET
    @Path("/episode/{episodeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEpisode(@PathParam("episodeId") String episodeId) throws JSONException {
        EpisodeFacade episodeFacade = new EpisodeFacade();
        Episode episode = new Episode();
        JSONArray ja = new JSONArray();
        if( !episodeFacade.findById(episodeId, episode) )
        {
        	System.out.printf("Get episode fail, Episode Id: %s", episodeId);
        	JSONObject errJsonobject = new JSONObject();
        	errJsonobject.put("ERROR", "Episode not found...");
            ja.put(errJsonobject);
        }
        else{
	        JSONObject jsonobject = new JSONObject();
	        jsonobject.put("ID", episode.getId());
	        jsonobject.put("NAME", episode.getResource());
	        ja.put(jsonobject);
	        }
        return Response.status(200).entity(ja.toString()).build();
    }
    
    @GET
    @Path("/movieList")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getMovieList() throws JSONException {
    	
        MovieFacade movieFacade = new MovieFacade();
        List<Movie> list = new ArrayList<Movie> ();
        JSONArray ja = new JSONArray();
        if( !movieFacade.findAll(list) )
        {
        	JSONObject errJsonobject = new JSONObject();
        	errJsonobject.put("ERROR", "No corresponding episode list...");
            ja.put(errJsonobject);
        }
        else
        {
	        for (Movie movie : list) {
	            JSONObject jsonobject = new JSONObject();
	            jsonobject.put("ID", movie.getId());
	            jsonobject.put("NAME", movie.getName());
	            jsonobject.put("DIRECTOR", movie.getDirector());
	            jsonobject.put("ACTOR", movie.getActor());
	            jsonobject.put("YEAR", movie.getYear());
	            jsonobject.put("STATUS", movie.getStatus());
	            jsonobject.put("THUMBNAIL", movie.getThumbnail());
	            jsonobject.put("DESCRIPTION", movie.getDescription());
	            jsonobject.put("NUMBEROFLIKE", movie.getNumberOfLike());
	            jsonobject.put("REPORT", movie.getReport());
	            ja.put(jsonobject);
	        }
        }
        return Response.status(200).entity(ja.toString()).build();
    }
}
