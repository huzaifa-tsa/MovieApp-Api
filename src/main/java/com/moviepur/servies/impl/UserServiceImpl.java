package com.moviepur.servies.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.moviepur.entitys.User;
import com.moviepur.exception.MoviepurException;
import com.moviepur.repository.UserRepository;
import com.moviepur.servies.AllService;
import com.moviepur.servies.PrimeryKeySeqService;
import com.moviepur.servies.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AllService allService;
	
	@Autowired
	private PrimeryKeySeqService primeryKeySeqService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<User> getAllUser() {
		return userRepository.getAllUser();
	}

	@Override
	public User getByToken(String token) throws MoviepurException {
		return userRepository.getByToken(token).orElseThrow(() -> new MoviepurException(404,"User Not Found"));
	}

	@Override
	public boolean userPresent(String token) {
		return userRepository.checkUserIsPresent(token) ;
	}
	
	@Override
	public User addUser(String token)  {
		try {
			return getByToken(token);
		}catch (MoviepurException e) {
			return userRepository.save(new User(primeryKeySeqService.getCurrentPostion("USERTABLE"), token,passwordEncoder.encode(token),LocalDate.now(),LocalDate.now()));
		}
		
	}

	@Override
	public User addMovieUserFavouriteList(String token, int movieId) throws MoviepurException {
		User user = getByToken(token);
		user.setUpdateDate(LocalDate.now());
    	 user.getLikesMovie().add(allService.getById(movieId));
		return userRepository.save(user);
	}

	@Override
	public User removeMovieUserFavouriteList(String token, int movieId) throws MoviepurException {
		User user = getByToken(token);
		user.setUpdateDate(LocalDate.now());
		user.getLikesMovie().removeIf(x -> x.getId() == movieId);
		return userRepository.save(user);
	}

	@Override
	public List<Map<String, Object>> getAllUserNotUpdate(int months) {
		 return userRepository.getAllUserNotUpdateLastMonths(LocalDate.now().minusMonths(months));
	}

	@Override
	public String deleteUser(String token) throws MoviepurException {
		 userRepository.delete(getByToken(token));
		 return "success";
	}

	@Override
	public String addAllUser(List<User> user) {
		userRepository.saveAll(user);
		return "success";
	}

	@Override
	public boolean checkUserLikeOrNot(String token, int movieId) {
		return userRepository.checkUserLikeOrNot(token,movieId);
	}

}
