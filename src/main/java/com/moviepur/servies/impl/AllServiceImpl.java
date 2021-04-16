package com.moviepur.servies.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviepur.entitys.MovieLite;
import com.moviepur.repository.MovieLiteRepository;
import com.moviepur.servies.AllService;

@Service
public class AllServiceImpl implements AllService {
	
	@Autowired
	private MovieLiteRepository movieLiteRepository;
	
	@Override
	public List<MovieLite> getAllMovieLite() {
		 return movieLiteRepository.findAllByDesc();
	}

	@Override
	public List<MovieLite> getByType(String type) {
		return movieLiteRepository.findAllByType(type);
	}

	@Override
	public List<MovieLite> getBetweenRating(String name,float min, float max) {
		return movieLiteRepository.getAcouradingToRating(name,min, max);
	}

	@Override
	public List<MovieLite> getByLanguage(String language) {
		return movieLiteRepository.findAllByLanguage(language);
	}

	@Override
	public List<MovieLite> getByReleaseYear(int startDate, int endDate) {
		return movieLiteRepository.findAllByReleaseYear(startDate+"-01-01",endDate+"-01-01");
	}
	
	@Override
	public List<MovieLite> getByGenre(Set<String> genre) {
		return movieLiteRepository. findAllByGenreDesc(genre); 
	}

	@Override
	public List<MovieLite> getByName(String movieName) {
		/*
		List<MovieLite> getByNameDatabase = movieLiteRepository.findAllByName(movieName);
		if(getByNameDatabase !=null && getByNameDatabase.size()>0)
			return getByNameDatabase;
		
		
		List<MovieLite> same_name_movie = new ArrayList<MovieLite>();
		movieName = movieName.toLowerCase();
		String[] nameLike = movieName.split(" ");

		for (MovieLite m : movieLiteRepository.findAll()) {

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
		*/
		return movieLiteRepository.findAllByNameLikeIgnoreCase(movieName);
	}

}
