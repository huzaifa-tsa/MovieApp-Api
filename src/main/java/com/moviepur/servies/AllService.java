package com.moviepur.servies;

import java.util.List;
import java.util.Set;

import com.moviepur.entitys.MovieLite;

public interface AllService {

	public List<MovieLite> getAllMovieLite();

	public List<MovieLite> getByType(String type);

	public List<MovieLite> getByGenre(Set<String> genre);

	public List<MovieLite> getBetweenRating(String name,float min, float max);

	public List<MovieLite> getByName(String movieName);

	public List<MovieLite> getByLanguage(String language);

	public List<MovieLite> getByReleaseYear(int startDate, int endDate);

}
