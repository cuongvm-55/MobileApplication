package com.luvsoft.controllers;

import java.util.ArrayList;
import java.util.List;

import com.luvsoft.entities.Favorite;
import com.luvsoft.entities.History;
import com.luvsoft.entities.Movie;
import com.luvsoft.entities.User;
import com.luvsoft.facades.FavoriteFacade;
import com.luvsoft.facades.HistoryFacade;
import com.luvsoft.facades.MovieFacade;
import com.luvsoft.facades.UserFacade;

enum AccountingError{
	ACCOUNTING_NO_ERROR,
	ACCOUNTING_USER_ALREADY_DEFINED,
	ACCOUNTING_USERNAME_OR_PASSWORD_INCORRECT
}

public class AccountingController{	
	/*
	 * Return true if username's already defined
	 */
	private boolean isUserDefined(String username){
		UserFacade userFacade = new UserFacade();
		return userFacade.getUser(username);
	}
	
	/*
	 * Register a user
	 */
	public boolean register(User user){
		if( isUserDefined(user.getUsername()) ){
			System.out.println("Username: " + user.getUsername() + " is already exist");
			return false;
		}
		
		// Save
		UserFacade userFacade = new UserFacade();
		if( !userFacade.saveUser(user) )
		{
			System.out.println("Save user fail...");
		}
		return true;
	}
	
	/*
	 * Login
	 */
	public boolean login(User user){
		UserFacade userFacade = new UserFacade();
		System.out.println("Login, user: " + user.getUsername() + ",pass: " +  user.getPassword());
		boolean getUserOk = userFacade.getUser(user.getUsername(), user.getPassword());
		System.out.println("getUser: " + getUserOk);
		return getUserOk;
	}
	
	/*
	 * Get favorite movies of a userId
	 */
	public boolean getFavoriteMovies(String userId, List<Movie> movieList){
		System.out.println("Get Favorite movies, userId: " + userId);
		FavoriteFacade favoriteFacade = new FavoriteFacade();
		List<Favorite> favorites = new ArrayList<Favorite>();
		if( favoriteFacade.getFavoritesForUserId(userId, favorites) )
		{
			MovieFacade movieFacade = new MovieFacade();
			for(int i = 0; i < favorites.size(); i++ )
			{
				Movie movie = new Movie();
				if( movieFacade.findById(favorites.get(i).getMovieId(), movie) )
				{
					movieList.add(movie);
				}
				else
				{
					System.out.println("get movieId " + favorites.get(i).getMovieId() + " fail!");
				}
				
			}
		}
		else
		{
			System.out.println("getHistoricsForUserId fail!");
			
		}
		return true;
	}
	
	/*
	 * Get historic movies of a userId
	 */
	public boolean getHistoricMovies(String userId, List<Movie> movieList){
		System.out.println("Get Historic movies, userId: " + userId);
		HistoryFacade historyFacade = new HistoryFacade();
		List<History> historics = new ArrayList<History>();
		if( historyFacade.getHistoricsForUserId(userId, historics) )
		{
			historics.toString();
			MovieFacade movieFacade = new MovieFacade();
			for(int i = 0; i < historics.size(); i++ )
			{
				Movie movie = new Movie();
				if( movieFacade.findById(historics.get(i).getMovieId(), movie) )
				{
					movieList.add(movie);
				}
				else
				{
					System.out.println("get movieId " + historics.get(i).getMovieId() + " fail!");
				}
				
			}
		}
		else
		{
			System.out.println("getHistoricsForUserId fail!");
			
		}
		return true;
	}
	
	/*
	 * Add a history
	 */
	public boolean addHistory(History history){
		HistoryFacade historyFacade = new HistoryFacade();
		return historyFacade.saveHistory(history);
	}
	
	/*
	 * Add a favorite
	 */
	public boolean addFavorite(Favorite favorite){
		FavoriteFacade favoriteFacade = new FavoriteFacade();
		return favoriteFacade.saveFavorite(favorite);
	}
}
