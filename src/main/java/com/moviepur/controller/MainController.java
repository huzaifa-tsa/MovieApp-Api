package com.moviepur.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviepur.entitys.Movie;
import com.moviepur.exception.MoviepurException;
import com.moviepur.servies.MainService;

@RestController
@CrossOrigin
@RequestMapping("/main")
public class MainController {

	@Autowired
	private  MainService mainService;

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
		return mainService.addMovie(movie);
	}

	@PutMapping("update/{movieId}")
	public Movie updateMovie(@PathVariable("movieId") int id, @RequestBody Movie movie) throws MoviepurException {
		return mainService.updateMovie(id, movie);
	}
	
	@PutMapping("updateSomeData/{movieId}")
	public Movie updateMovieSome(@PathVariable("movieId") int id, @RequestBody Movie movie) throws MoviepurException {
		return mainService.updateMovieSome(id, movie);
	}

	@PutMapping("update/download/{movieId}")
	public Movie updateDowloadLink(@PathVariable("movieId") int id, @RequestBody Map<String, String> download_Links)
			throws MoviepurException {
		return mainService.updateDownloadsLinks(id, download_Links);
	}

	@DeleteMapping("/deletewithpermission/{movieId}/{password}")
	public String deleteMovie(@PathVariable int movieId, @PathVariable String password) throws MoviepurException {
		return mainService.delete(movieId, password);
	}

	@GetMapping("/downloadJson/{password}")
	public ResponseEntity<Object> downloadJson(@PathVariable String password) throws MoviepurException {
		try {
			byte[] customerJsonBytes = mainService.downloadJson(password).getBytes();
			
			return ResponseEntity
					.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=DATA_"+LocalDateTime.now()+".json")
					.contentType(MediaType.APPLICATION_JSON)
					.contentLength(customerJsonBytes.length)
					.body(customerJsonBytes);
		}catch (Exception e) {
			throw new MoviepurException(409,e.getLocalizedMessage());
		}
                
	}
}
