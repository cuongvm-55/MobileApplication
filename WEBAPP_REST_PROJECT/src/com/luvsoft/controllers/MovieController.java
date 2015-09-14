package com.luvsoft.controllers;

import java.util.List;

import com.luvsoft.entities.Movie;
import com.luvsoft.facades.MovieFacade;

public class MovieController extends AbstractController {

	public boolean getMovieListByPagination(int pageNumber, List<Movie> retList) {
		MovieFacade movieFacade = new MovieFacade();
		if( movieFacade.getMovieListByPagination(pageNumber, retList) ) {
			return true;
		}
		return false;
	}
}
