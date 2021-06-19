package com.moviepur.servies.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviepur.entitys.FilmSeries;
import com.moviepur.exception.MoviepurException;
import com.moviepur.repository.FilmSeriesRepository;
import com.moviepur.servies.AllService;
import com.moviepur.servies.BannerService;
import com.moviepur.servies.FilmSeriesService;
import com.moviepur.servies.PrimeryKeySeqService;

@Service
public class FilmSeriesServiceImpl implements FilmSeriesService{

	@Autowired
	private FilmSeriesRepository filmSeriesRepository;
	
	@Autowired
	private AllService movieLiteService;
	
	@Autowired
	private PrimeryKeySeqService primeryKeySeqService;
	
	@Autowired
	private BannerService bannerService;
	
	@Override
	public List<FilmSeries> getAll() {
		return filmSeriesRepository.getAll();
	}

	@Override
	public FilmSeries getById(int id) throws MoviepurException {
		return filmSeriesRepository.getById(id).orElseThrow(() -> new MoviepurException(404,"Film Series Not Found"));
	}

	@Override
	public FilmSeries add(FilmSeries filmSeries) {
		filmSeries.setId(primeryKeySeqService.getCurrentPostion("FILMSERIESTABLE"));
		 filmSeries =	filmSeriesRepository.save(filmSeries);
		 bannerService.bannerUpdate(true, filmSeries.getImageUrl());
		 return filmSeries;
	}

	@Override
	public String addAll(List<FilmSeries> list) {
		 filmSeriesRepository.saveAll(list);
		 return "successfull add all";
	}

	@Override
	public FilmSeries addMovie(int filmSeriesId, int movieId) throws MoviepurException {
			FilmSeries filmSeries =	getById(filmSeriesId);
			filmSeries.getMovies().add(movieLiteService.getById(movieId));
			return filmSeriesRepository.save(filmSeries);
	}

	@Override
	public FilmSeries removeMovie(int filmSeriesId, int movieId) throws MoviepurException {
		FilmSeries filmSeries =	getById(filmSeriesId);
		filmSeries.getMovies().removeIf(x -> x.getId() == movieId );
		return filmSeriesRepository.save(filmSeries);
	}

	@Override
	public String deleteById(int movieId) throws MoviepurException {
		filmSeriesRepository.delete(getById(movieId));
		return "delete successfull";
	}

}
