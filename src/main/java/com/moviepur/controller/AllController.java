package com.moviepur.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviepur.entitys.MovieLite;
import com.moviepur.servies.AllService;

@RestController
@RequestMapping("/all")
@CrossOrigin
public class AllController {

	@Autowired
	private AllService mainService;

	@GetMapping
	public List<MovieLite> welcome() {
		return mainService.getAllMovieLite();
	}
	
	@GetMapping("/getAllGenres")
	public Set<String> getAllGenres(){
		return mainService.getAllGenres();
	}
	
	@GetMapping("/getAllLanguages")
	public Set<String> getAllLanguages(){
		return mainService.getAllLanguages();
	}
	
	// Get All With Gener
	@GetMapping("/genre/{genre}")
	public List<MovieLite> getByGenre(@PathVariable("genre") String genre) {
		return mainService.getByGenre(genre);
	}

	// Get Only Some Movie and web series those Have Same Name
	@GetMapping("/name/{name}")
	public List<MovieLite> getByName(@PathVariable("name") String name) {
		return mainService.getByName(name);
	}

	// Get By Language
	@GetMapping("/language/{language}")
	public List<MovieLite> getByLanguage(@PathVariable("language") String language) {
		return mainService.getByLanguage(language);
	}

	// Get rating
	@GetMapping("/rating/{raterName}/{min}/{max}")
	public List<MovieLite> getBetweenRating(@PathVariable("raterName") String raterName ,@PathVariable("min") int min,@PathVariable("max") int max) {
		return mainService.getBetweenRating(raterName,(float) min, (float) max);
	}
	
	@GetMapping("/releaseDate/{startYear}/{endYear}")
	public List<MovieLite> getByReleaseYear(@PathVariable("startYear") int startYear,@PathVariable("endYear") int endYear) {
		return mainService.getByReleaseYear(startYear,endYear);
	}
}
