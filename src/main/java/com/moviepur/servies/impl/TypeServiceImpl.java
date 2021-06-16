package com.moviepur.servies.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviepur.entitys.MovieLite;
import com.moviepur.repository.MovieLiteRepository;
import com.moviepur.servies.TypeService;

@Service
public class TypeServiceImpl implements TypeService {

	@Autowired
	private MovieLiteRepository movieLiteRepository;
	
	@Override
	public List<MovieLite> getByType(String type) {
		return movieLiteRepository.findAllByType(type.toLowerCase());
	}

	@Override
	public List<MovieLite> getByLatestReleaseDate(String type, String industryName, int limit) {
		return movieLiteRepository.getByLatestReleaseDateByType(type.toLowerCase(), industryName, limit);
	}

	@Override
	public List<MovieLite> getMostLikeMovie(String type) {
		return movieLiteRepository.getMostLikeMovieByType(type.toLowerCase());
	}

}
