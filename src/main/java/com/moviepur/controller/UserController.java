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
import org.springframework.web.bind.annotation.RestController;

import com.moviepur.entitys.User;
import com.moviepur.exception.MoviepurException;
import com.moviepur.servies.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> getAll() {
		return userService.getAllUser();
	}
	
	@GetMapping("/{token}")
	public User getByToken(@PathVariable String token) throws MoviepurException {
		return userService.getByToken(token);
	}
	
	@GetMapping("exists/{token}")
	public boolean existsByToken(@PathVariable String token) {
		return userService.userPresent(token);
	}
	
	@GetMapping("getAllUserNotUpdate/{months}")
	public List<Map<String, Object>> getAllUserNotUpdate(@PathVariable int months) {
		return userService.getAllUserNotUpdate(months) ;
	}
	
	@PostMapping("/{token}")
	public User addUser(@PathVariable String token) throws MoviepurException {
		return userService.addUser(token);
	}
	
	@PostMapping
	public String addAllUser(@RequestBody List<User> user) throws MoviepurException {
		return userService.addAllUser(user);
	}
	
	@PutMapping("addLikesMovie/{token}/{movieId}")
	public User addLikesMovie(@PathVariable String token,@PathVariable int movieId) throws MoviepurException {
		return userService.addMovieUserFavouriteList(token,movieId);
	}
	
	@DeleteMapping("removeLikesMovie/{token}/{movieId}")
	public User removeLikesMovie(@PathVariable String token,@PathVariable int movieId) throws MoviepurException {
		return userService.removeMovieUserFavouriteList(token,movieId);
	}
	
	@DeleteMapping("/{token}")
	public String deleteUser(@PathVariable String token) throws MoviepurException  {
		return userService.deleteUser(token);
	}
}
