package com.moviepur.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviepur.entitys.FilmSeries;
import com.moviepur.exception.MoviepurException;
import com.moviepur.servies.FilmSeriesService;

@RestController
@RequestMapping("/filmSeries")
@CrossOrigin
public class FilmSeriesController {

	@Autowired
	private FilmSeriesService filmSeriesService;
	
	@GetMapping
	public List<FilmSeries> getAll() {
		return filmSeriesService.getAll();
	}
	
	@GetMapping("/{id}")
	public FilmSeries getById(@PathVariable int id) throws MoviepurException {
		return filmSeriesService.getById(id);
	}
	
	@PostMapping
	public FilmSeries addFilmSeries(@RequestBody FilmSeries filmSeries) {
		return filmSeriesService.add(filmSeries);
	}
	
	@PostMapping("/addAll")
	public String addAllFilmSeries(@RequestBody List<FilmSeries> filmSeries) {
		return filmSeriesService.addAll(filmSeries);
	}
	
	@PutMapping("addMovie/{id}/{movieId}")
	public FilmSeries addLikesMovie(@PathVariable int id,@PathVariable int movieId) throws MoviepurException {
		return filmSeriesService.addMovie(id,movieId);
	}
	
	@DeleteMapping("removeMovie/{id}/{movieId}")
	public FilmSeries removeMovie(@PathVariable int id,@PathVariable int movieId) throws MoviepurException {
		return filmSeriesService.removeMovie(id,movieId);
	}
	
	@DeleteMapping("/{id}")
	public String deleteFilmSeries(@PathVariable int id) throws MoviepurException  {
		return filmSeriesService.deleteById(id);
	}
}
