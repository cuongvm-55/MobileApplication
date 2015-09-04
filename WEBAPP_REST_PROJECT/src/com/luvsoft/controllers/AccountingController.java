package com.luvsoft.controllers;

import java.util.ArrayList;
import java.util.List;

import com.luvsoft.entities.Favorite;
import com.luvsoft.entities.Movie;
import com.luvsoft.entities.User;
import com.luvsoft.facades.FavoriteFacade;
import com.luvsoft.facades.HistoryFacade;
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
	public AccountingError register(User user){
		if( isUserDefined(user.getUsername()) ){
			return AccountingError.ACCOUNTING_USER_ALREADY_DEFINED;
		}
		
		// Save
		UserFacade userFacade = new UserFacade();
		userFacade.saveUser(user);
		return AccountingError.ACCOUNTING_NO_ERROR;
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
	
	public boolean getFavoriteMovies(String userId, List<Movie> movieList){
		System.out.println("Get favorite movies, userId: " + userId);
		FavoriteFacade favoriteFacade = new FavoriteFacade();
		List<Favorite> favorites = new ArrayList<Favorite>();
		if( favoriteFacade.getFavoritesForUserId(userId, favorites) ){
			// get movie list from favorites
			favorites.toString();
		}
		return true;
		
	}
	
	public boolean getHistoricMovies(String userId, List<Movie> movieList){
		System.out.println("Get Historic movies, userId: " + userId);
		HistoryFacade historyFacade = new HistoryFacade();
		return historyFacade.getHistoricMovies(userId, movieList);
	}
	
}
