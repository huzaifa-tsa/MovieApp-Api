package com.moviepur.servies;

import java.util.List;
import java.util.Map;

import com.moviepur.entitys.Movie;
import com.moviepur.exception.MoviepurException;

public interface MainService {

	public List<Movie> getAllMovie();

	public String saveAllMovie(List<Movie> movielist);

	public Movie getById(int movieid) throws MoviepurException;

	public Movie add_Movie(Movie movie) throws MoviepurException;

	public Movie update_Movie(int id, Movie movie) throws MoviepurException;

	public Movie update_Downloads_Links(int id, Map<String, String> download_Links) throws MoviepurException;

	public String delete(int movieId, String password)throws MoviepurException;
}
