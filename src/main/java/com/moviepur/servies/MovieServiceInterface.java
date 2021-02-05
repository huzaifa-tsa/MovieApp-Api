package com.moviepur.servies;

import java.util.List;
import java.util.Set;
import com.moviepur.entitys.Movie;
import com.moviepur.entitys.MovieLite;

public interface MovieServiceInterface {

		public Movie getOne(int id);
	
		public List<MovieLite> getAll();

		public List<MovieLite> getMovie_vs_Series(boolean type);
		
		public List<MovieLite> getType_Movie_And_Series(Set<String> genre);

		public List<MovieLite> getBetweenRating(float min, float max);
		
		public List<MovieLite> getSameName(String movieName);

		public List<MovieLite> getByLanguage(String language);

		public boolean add_And_Update_Movie(Movie movie);

		public void delete(int id);




}
