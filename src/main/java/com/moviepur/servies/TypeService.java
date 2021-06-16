package com.moviepur.servies;

import java.util.List;

import com.moviepur.entitys.MovieLite;

public interface TypeService {

	public List<MovieLite> getByType(String type);

	public List<MovieLite> getByLatestReleaseDate(String type, String industryName , int limit);

	public List<MovieLite> getMostLikeMovie(String type);

}
