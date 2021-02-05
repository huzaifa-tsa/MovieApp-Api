package com.moviepur.servies;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moviepur.dao.MovieDAO;
import com.moviepur.entitys.Movie;
import com.moviepur.entitys.MovieLite;

@Service
public class MovieServiceClass implements MovieServiceInterface {

	@Autowired
	private MovieDAO movieDao;

	public MovieServiceClass() { }

	@Override
	public List<MovieLite> getAll() {
		return movieDao.getAll();
	}

	@Override
	public Movie getOne(int id) {
		return movieDao.getOne(id);
	}


	
	@Override
	public List<MovieLite> getMovie_vs_Series(boolean type) {
		return movieDao.getType(type);
	}


	@Override
	public List<MovieLite> getBetweenRating(float min, float max) {
		return movieDao.getAcouradingToRating(min, max);
	}
	
	@Override
	public List<MovieLite> getByLanguage(String language) {
		return movieDao.getByLanguage(language);
	}
	
	@Override
	public List<MovieLite> getType_Movie_And_Series(Set<String> genre) {
		return movieDao.getGenre(genre);
	}

	
	
	@Override
	public List<MovieLite> getSameName(String movieName) {
		List<MovieLite> same_name_movie = new ArrayList<MovieLite>();

		movieName = movieName.toLowerCase();
		String[] nameLike = movieName.split(" ");

		for (MovieLite m : movieDao.getAll()) {

			String mName = (m.getName()).toLowerCase();
			if (mName.equals(movieName)) {
				same_name_movie.add(0, m);
				continue;
			} else if (mName.startsWith(movieName)) {
				same_name_movie.add(0, m);
				continue;
			} else if (mName.endsWith(movieName)) {
				same_name_movie.add(0, m);
				continue;
			}

			for (String n : nameLike) {
				if (n.length() > 1) {
					 if (mName.contains(n)) {
						 same_name_movie.add(m);
						break;
					}
				}
			}
		}
		return same_name_movie;
	}

	
	
	@Override
	public boolean add_And_Update_Movie(Movie movie) {

		try {
			movieDao.save(movie);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	
	@Override
	public void delete(int id) {
		Movie movie = movieDao.getOne(id);
		movieDao.delete(movie);

	}

}
