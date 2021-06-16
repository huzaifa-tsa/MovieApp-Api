package com.moviepur.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviepur.entitys.MovieLite;
import com.moviepur.servies.TypeService;

@RestController
@RequestMapping("/type")
@CrossOrigin
public class TypeController {

	@Autowired
	private TypeService typeService;
	
	@GetMapping("/{type}")
	public List<MovieLite> getMovie(@PathVariable String type) {
		return typeService.getByType(type);
	}

	@GetMapping("/getByLatestReleaseDate/{type}/{industryName}/{limit}")
	public List<MovieLite> getByLatestReleaseDate(@PathVariable String type,@PathVariable String industryName, @PathVariable int limit){
		return typeService.getByLatestReleaseDate(type, industryName, limit);
	}
	
	@GetMapping("/getMostLike/{type}")
	public List<MovieLite> getMostLike(@PathVariable String type){
		return typeService.getMostLikeMovie(type);
	}
	
	
}
