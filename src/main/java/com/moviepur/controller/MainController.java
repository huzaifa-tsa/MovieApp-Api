package com.moviepur.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.moviepur.entitys.Movie;
import com.moviepur.exception.MoviepurException;
import com.moviepur.servies.MainService;

@RestController
@CrossOrigin
@RequestMapping("/main")
public class MainController {

	@Autowired
	MainService mainService;

	@GetMapping
	public List<Movie> getAllMovie() {
		return mainService.getAllMovie();
	}

	@PostMapping
	public String saveAll(@RequestBody List<Movie> movielist) {
		return mainService.saveAllMovie(movielist);
	}

	@PostMapping("/get/{movieid}")
	public Movie getOne(@PathVariable int movieid) throws MoviepurException {
		return mainService.getById(movieid);
	}

	@PostMapping("/add")
	public Movie addOneMovie(@RequestBody Movie movie) throws MoviepurException {
		return mainService.add_Movie(movie);
	}

	@PutMapping("update")
	public Movie updateMovie(@RequestParam("id") int id, @RequestBody Movie movie) throws MoviepurException {
		return mainService.update_Movie(id, movie);
	}

	@PutMapping("update/download")
	public Movie updateDowloadLink(@RequestParam("id") int id, @RequestBody Map<String, String> download_Links)
			throws MoviepurException {
		return mainService.update_Downloads_Links(id, download_Links);
	}

	@DeleteMapping("/deletewithpermission/{movieId}/{password}")
	public String deleteMovie(@PathVariable int movieId, @PathVariable String password) throws MoviepurException {
		return mainService.delete(movieId, password);
	}

}
