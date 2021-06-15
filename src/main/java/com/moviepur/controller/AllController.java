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
	private AllService allService;

	@GetMapping
	public List<MovieLite> welcome() {
		return allService.getAllMovieLite();
	}
	
	@GetMapping("/getAllGenres")
	public Set<String> getAllGenres(){
		return allService.getAllGenres();
	}
	
	@GetMapping("/getAllLanguages")
	public Set<String> getAllLanguages(){
		return allService.getAllLanguages();
	}
	
	@GetMapping("/getFormatedDateForAndroid")
	public List<Object> getFormatedDateForAndroid(){
		return allService.getFormatedDateForAndroid();
	}
	
	// Get All With Gener
	@GetMapping("/genre/{genre}")
	public List<MovieLite> getByGenre(@PathVariable("genre") String genre) {
		return allService.getByGenre(genre);
	}

	// Get Only Some Movie and web series those Have Same Name
	@GetMapping("/name/{name}")
	public List<MovieLite> getByName(@PathVariable("name") String name) {
		return allService.getByName(name);
	}

	// Get By Language
	@GetMapping("/language/{language}")
	public List<MovieLite> getByLanguage(@PathVariable("language") String language) {
		return allService.getByLanguage(language);
	}

	// Get rating
	@GetMapping("/rating/{raterName}/{min}/{max}")
	public List<MovieLite> getBetweenRating(@PathVariable("raterName") String raterName ,@PathVariable("min") int min,@PathVariable("max") int max) {
		return allService.getBetweenRating(raterName,(float) min, (float) max);
	}
	
	@GetMapping("/releaseDate/{startYear}/{endYear}")
	public List<MovieLite> getByReleaseYear(@PathVariable("startYear") int startYear,@PathVariable("endYear") int endYear) {
		return allService.getByReleaseYear(startYear,endYear);
	}
	
	@GetMapping("/getByLatestReleaseDate/{industryName}/{limit}")
	public List<MovieLite> getByLatestReleaseDate(@PathVariable String industryName, @PathVariable int limit){
		return allService.getByLatestReleaseDate(industryName, limit);
	}
	
	@GetMapping("/getByIndustryName/{industryName}")
	public List<MovieLite> getByIndustryName(@PathVariable String  industryName){
		return allService.getByIndustryName(industryName);
	}
	
	@GetMapping("/getByGenreAndIndustryName/{genre}/{industryName}")
	public List<MovieLite> getByGenreAndIndustryName(@PathVariable String genre, @PathVariable String  industryName){
		return allService.getByGenreAndIndustryName(genre,industryName);
	}
	
	@GetMapping("/getRandomMovie")
	public List<MovieLite> getRandomMovie(){
		return allService.getRandomMovie();
	}
	
	@GetMapping("/getMostLikeMovie")
	public List<MovieLite> getMostLikeMovie(){
		return allService.getMostLikeMovie();
	}
	
	@GetMapping("/getByDirectors/{name}")
	public List<MovieLite> getByDirectors(@PathVariable String name){
		return allService.getByPerson(1,name);
	}
	
	@GetMapping("/getByWriter/{name}")
	public List<MovieLite> getByWriter(@PathVariable String name){
		return allService.getByPerson(2,name);
	}
	
	@GetMapping("/getByStar/{name}")
	public List<MovieLite> getByStar(@PathVariable String name){
		return allService.getByPerson(3,name);
	}
}
