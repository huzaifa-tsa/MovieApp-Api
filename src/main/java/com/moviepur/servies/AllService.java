package com.moviepur.servies;

import java.util.List;
import java.util.Set;

import com.moviepur.entitys.MovieLite;
import com.moviepur.exception.MoviepurException;

public interface AllService {

	public List<MovieLite> getAllMovieLite();

	public List<MovieLite> getByType(String type);

	public List<MovieLite> getByGenre(String genre);

	public List<MovieLite> getBetweenRating(String name,float min, float max);

	public List<MovieLite> getByName(String movieName);

	public List<MovieLite> getByLanguage(String language);

	public List<MovieLite> getByReleaseYear(int startDate, int endDate);

	public Set<String> getAllGenres();

	public Set<String> getAllLanguages();

	public MovieLite getById(int movieId) throws MoviepurException;

	public List<MovieLite> getByLatestReleaseDate(String industryName , int limit);

	public List<MovieLite> getByIndustryName(String industryName);

	public List<MovieLite> getByGenreAndIndustryName(String genre, String industryName);

	public List<Object> getFormatedDateForAndroid();

	public List<MovieLite> getRandomMovie();

	public List<MovieLite> getMostLikeMovie();

	public List<MovieLite> getByPerson(int i, String name);

}
