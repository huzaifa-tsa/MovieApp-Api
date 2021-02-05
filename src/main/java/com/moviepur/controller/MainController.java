package com.moviepur.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moviepur.entitys.Movie;
import com.moviepur.entitys.MovieLite;
import com.moviepur.servies.MovieServiceInterface;


@RestController
@RequestMapping("/")
public class MainController {

	@Autowired
	private MovieServiceInterface movieService;

	// Get All
	@GetMapping({ "/home", "/" })
	public List<MovieLite> welcome() {
		return movieService.getAll();
	}

	//Get Only One
	@PostMapping("/get/{movieid}")
	public Movie getOne(@PathVariable int movieid)
	{
		return movieService.getOne(movieid);
	}
	
	
	// Get All With Gener
	@GetMapping("/genre")
	public List<MovieLite> getGenre(@RequestParam("type") String type) {
		Set<String> genre = new HashSet<String>(Arrays.asList(type.split(",")));
		return movieService.getType_Movie_And_Series(genre);
	}

	
	// Get Only Some Movie and web series those Have Same Name
	@GetMapping("/list")
	public List<MovieLite> getSameNameMovie(@RequestParam("name") String name) {
		return movieService.getSameName(name);
	}

	// Get By Language
	@GetMapping("/language")
	public List<MovieLite> getlanguage(@RequestParam("language") String language) {
		return movieService.getByLanguage(language);
	}

	
	//Get rating 
	@GetMapping("/rating")
	public List<MovieLite> getBetweenRating(@RequestParam("min") int min,@RequestParam("max") int max)
	{
		return movieService.getBetweenRating((float)min,(float)max);
	}
	
	// Add and Update new Movie And Web Series
	@PostMapping({ "/add", "/update" })
	public String addOneMovie(@RequestBody Movie movie) {
		String massage;
		if (movieService.add_And_Update_Movie(movie))
			massage = "Succesfull Add  And Update  Movie";
		else
			massage = "Movie Not Add";

		return massage;
	}

	@DeleteMapping("/deletewithpermission/{movieid}")
	public ResponseEntity<HttpStatus> deleteMovie(@PathVariable int movieid) {
		try {
			movieService.delete(movieid);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.FAILED_DEPENDENCY);
		}
	}
}
