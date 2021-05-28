package com.moviepur.servies;

import java.util.List;
import java.util.Map;

import com.moviepur.entitys.User;
import com.moviepur.exception.MoviepurException;

public interface UserService {

	public List<User> getAllUser();
	
	public User addUser(String token) throws MoviepurException;
	
	public User getByToken(String token) throws MoviepurException;
	
	public boolean userPresent(String token);
	
	public User addMovieUserFavouriteList(String token, int movieId) throws MoviepurException;
	
	public User removeMovieUserFavouriteList(String token, int movieId) throws MoviepurException;

	public List<Map<String, Object>> getAllUserNotUpdate(int months);

	public String deleteUser(String token) throws MoviepurException;

	public String addAllUser(List<User> user);
	
	public boolean checkUserLikeOrNot(String token, int movieId);

	public List<String> getAllUserToken();
	
}
