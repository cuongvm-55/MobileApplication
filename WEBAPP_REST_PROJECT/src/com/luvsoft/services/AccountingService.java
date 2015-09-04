package com.luvsoft.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.luvsoft.controllers.AccountingController;
import com.luvsoft.entities.Movie;
import com.luvsoft.entities.User;

//URL: http://localhost:8080/WEBAPP_REST_PROJECT/rest/accountingservice/service_name
@Path("/accountingservice")
public class AccountingService {
	
	// login
	@GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@QueryParam("username") String username, @QueryParam("password") String password) throws JSONException {
        AccountingController accountingController = new AccountingController();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        boolean loginOk = accountingController.login(user);
        JSONArray ja = new JSONArray();
        //for (Episode movie : list) {
            JSONObject jsonobject = new JSONObject();
            jsonobject.put("STATUS",loginOk);
            ja.put(jsonobject);
        //}
        return Response.status(200).entity(ja.toString()).build();
    }
	
	// gets history movies
	@GET
    @Path("/historicmovies/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHistoricMovies(@PathParam("userid") String userId) throws JSONException {
		AccountingController accountingController = new AccountingController();
		JSONArray ja = new JSONArray();
        List<Movie> movieList = new ArrayList<Movie>();
        accountingController.getHistoricMovies(userId, movieList);
        for (Movie movie : movieList) {
            JSONObject jsonobject = new JSONObject();
            jsonobject.put("ID", movie.getId());
            ja.put(jsonobject);
        }
        return Response.status(200).entity(ja.toString()).build();
    }
	
	// gets favorite movies
	@GET
    @Path("/favoritemovies/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFavoriteMovies(@PathParam("userid") String userId) throws JSONException {
		AccountingController accountingController = new AccountingController();
		JSONArray ja = new JSONArray();
        List<Movie> movieList = new ArrayList<Movie>();
        accountingController.getFavoriteMovies(userId, movieList);
       
        for (Movie movie : movieList) {
            JSONObject jsonobject = new JSONObject();
            jsonobject.put("ID", movie.getId());
            ja.put(jsonobject);
        }
	    return Response.status(200).entity(ja.toString()).build();
    }
	
}
