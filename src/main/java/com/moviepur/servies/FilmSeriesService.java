package com.moviepur.servies;

import java.util.List;

import com.moviepur.entitys.FilmSeries;
import com.moviepur.exception.MoviepurException;

public interface FilmSeriesService {

	public List<FilmSeries> getAll();
	
	public FilmSeries getById(int id) throws MoviepurException;
	
	public FilmSeries add(FilmSeries filmSeries);
	
	public String addAll(List<FilmSeries> list);
	
	public FilmSeries addMovie(int filmSeriesId, int movieId) throws MoviepurException;
	
	public FilmSeries removeMovie(int filmSeriesId, int movieId) throws MoviepurException;
	
	public String deleteById(int id) throws MoviepurException;
}
