package com.moviepur.controller;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	
	// Get All With Gener
	@GetMapping("/genre")
	public List<MovieLite> getByGenre(@RequestBody Set<String> genre) {
		return mainService.getByGenre(genre);
	}

	// Get Only Some Movie and web series those Have Same Name
	@GetMapping("/name")
	public List<MovieLite> getByName(@RequestParam("name") String name) {
		return mainService.getByName(name);
	}

	// Get By Language
	@GetMapping("/language")
	public List<MovieLite> getByLanguage(@RequestParam("language") String language) {
		return mainService.getByLanguage(language);
	}

	// Get rating
	@GetMapping("/rating")
	public List<MovieLite> getBetweenRating(@RequestParam("name") String name,@RequestParam("min") int min, @RequestParam("max") int max) {
		return mainService.getBetweenRating(name,(float) min, (float) max);
	}
	
	@GetMapping("/releasedate")
	public List<MovieLite> getByReleaseYear(@RequestParam("startdate") int startDate,@RequestParam("enddate") int endDate) {
		return mainService.getByReleaseYear(startDate,endDate);
	}
}
