package com.moviepur.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.moviepur.entitys.MovieLite;
import com.moviepur.servies.MovieServiceInterface;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private MovieServiceInterface movieService;
	
	@GetMapping("")
	public List<MovieLite> getMovie() {
		return movieService.getMovie_vs_Series(true);
	}
	
}
