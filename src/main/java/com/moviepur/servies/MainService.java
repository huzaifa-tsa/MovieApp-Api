package com.moviepur.servies;

import java.util.List;
import java.util.Map;

import com.moviepur.entitys.Movie;
import com.moviepur.exception.MoviepurException;

public interface MainService {

	public List<Movie> getAllMovie();

	public String saveAllMovie(List<Movie> movielist);

	public Movie getById(int movieid) throws MoviepurException;

	public Movie addMovie(Movie movie) throws MoviepurException;

	public Movie updateMovie(int id, Movie movie) throws MoviepurException;

	public Movie updateMovieSome(int id, Movie movie) throws MoviepurException;
	
	public Movie updateDownloadsLinks(int id, Map<String, String> downloadLinks) throws MoviepurException;

	public String delete(int movieId, String password)throws MoviepurException;

	public String downloadJson(String password) throws MoviepurException;

}
