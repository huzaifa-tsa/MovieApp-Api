package com.moviepur.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.moviepur.entitys.MovieLite;
import com.moviepur.servies.AllService;

@RestController
@RequestMapping("/movie")
@CrossOrigin
public class MovieController {

	@Autowired
	private AllService allService;
	
	@GetMapping
	public List<MovieLite> getMovie() {
		return allService.getByType("Movie");
	}
	
}
