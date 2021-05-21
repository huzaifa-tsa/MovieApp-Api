package com.moviepur.servies.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviepur.entitys.MovieLite;
import com.moviepur.exception.MoviepurException;
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
	public List<MovieLite> getBetweenRating(String raterName,float min, float max) {
		return movieLiteRepository.getAcouradingToRating(raterName.toLowerCase(),min, max+1);
	}

	@Override
	public List<MovieLite> getByLanguage(String language) {
		return movieLiteRepository.findAllByLanguage(language.toLowerCase());
	}

	@Override
	public List<MovieLite> getByReleaseYear(int startDate, int endDate) {
		return movieLiteRepository.findAllByReleaseYear(LocalDate.parse(startDate+"-01-01"),LocalDate.parse((endDate+1)+"-01-02"));
	}
	
	@Override
	public List<MovieLite> getByGenre(String genre) {
		return movieLiteRepository. findAllByGenreDesc(genre.toLowerCase()); 
	}

	@Override
	public List<MovieLite> getByName(String movieName) {
		return movieLiteRepository.getAllByNameContaines(movieName.toLowerCase());
	}

	@Override
	public Set<String> getAllGenres() {
		return movieLiteRepository.getAllGenres();
	}

	@Override
	public Set<String> getAllLanguages() {
		return movieLiteRepository.getAllLanguages();
	}

	@Override
	public MovieLite getById(int movieId) throws MoviepurException {
		 return	movieLiteRepository.getById(movieId).orElseThrow(() -> new MoviepurException(404,"Movie Not Found"));
	}

	@Override
	public List<MovieLite> getByLatestReleaseDate() {
		return movieLiteRepository.getByLatestReleaseDate();
	}

	@Override
	public List<MovieLite> getByIndustryName(String industryName) {
		return movieLiteRepository.getByIndustryName(industryName);
	}

	@Override
	public List<MovieLite> getByGenreAndIndustryName(String genre, String industryName) {
		return movieLiteRepository.getByGenreAndIndustryName(genre.toLowerCase(),industryName);
	}

	@Override
	public List<Object> getFormatedDateForAndroid() {
		System.out.println(movieLiteRepository.getAllIndustryByGenre("action"));
		Set<String> genres =	getAllGenres();
		List<Object> result = new LinkedList<>();
		genres.forEach(x -> {
			Map<String, Object> map = new HashMap<>(2);
			map.put("title", x);
			map.put("data", movieLiteRepository.getAllIndustryByGenre(x));
			result.add(map);
		});
		return result;
	}

	@Override
	public List<MovieLite> getRandomMovie() {
		return movieLiteRepository.getRandomMovie();
	}

	@Override
	public List<MovieLite> getMostLikeMovie() {
		return movieLiteRepository.getMostLikeMovie();
	}

	@Override
	public List<MovieLite> getByPerson(int i, String name) {
		name = name.toLowerCase();
		if(i==1)
			return movieLiteRepository.getByDirecterName(name);
		else if(i==2)
			return movieLiteRepository.getByWriterName(name);
		else
			return movieLiteRepository.getByStarName(name);
		}

}
