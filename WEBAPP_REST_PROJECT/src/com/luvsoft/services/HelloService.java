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
import com.luvsoft.facades.EpisodeFacade;

// URL: http://localhost:8080/WEBAPP_REST_PROJECT/rest/demoservice/service_name
@Path("/demoservice")
public class HelloService {

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
}
