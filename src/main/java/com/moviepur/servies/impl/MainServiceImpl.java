package com.moviepur.servies.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviepur.entitys.Movie;
import com.moviepur.entitys.PrimeryKeySeq;
import com.moviepur.exception.MoviepurException;
import com.moviepur.repository.MovieRepository;
import com.moviepur.servies.FilmSeriesService;
import com.moviepur.servies.MainService;
import com.moviepur.servies.PrimeryKeySeqService;
import com.moviepur.servies.UserService;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;
	
	@Autowired
	private PrimeryKeySeqService primeryKeySeqService;
	
	@Autowired
	private FilmSeriesService filmSeriesService;

	private static final String ADMINPASSWORD = "$2a$10$YST7qlq5oKVTSZv9/tSlrOaNnUy1Wc./dzmeC1Ung6XSnDx1bvij6";

	@Override
	public List<Movie> getAllMovie() {
		return movieRepository.getAll();
	}

	@Override
	public String saveAllMovie(List<Movie> movielist) {
		try {
			movieRepository.saveAll(movielist);
			return "Success";
		} catch (Exception e) {
			return "Failde";
		}
	}

	@Override
	public Movie getById(int movieId) throws MoviepurException {
		return movieRepository.getById(movieId).orElseThrow(() -> new MoviepurException(404, "Movie Not Found"));
	}

	@Override
	public Movie addMovie(Movie movie) throws MoviepurException {
		try {
			movie.setId(primeryKeySeqService.getCurrentPostion("MOVIETABLE"));
			return movieRepository.save(movie);
		} catch (Exception e) {
			throw new MoviepurException(500, "Internal Server Error");
		}
	}

	@Override
	public Movie updateMovie(int id, Movie movieEdit) throws MoviepurException {
		Movie movie = getById(id);
		movieEdit.setId(movie.getId());
		return movieRepository.save(movieEdit);
	}

	@Override
	public Movie updateMovieSome(int id, Movie movieEdit) throws MoviepurException {
		Movie movie = getById(id);
		movieEdit.setId(movie.getId());
		movieEdit.setDownload_link(movie.getDownload_link());
		return movieRepository.save(movieEdit);
	}

	@Override
	public Movie updateDownloadsLinks(int id, Map<String, String> downloadLinks) throws MoviepurException {
		Movie movie = getById(id);
		movie.setDownload_link(downloadLinks);
		return movieRepository.save(movie);
	}

	@Override
	public String delete(int movieId, String password) throws MoviepurException {
		if (passwordEncoder.matches(password, ADMINPASSWORD)) {
			Movie movie = getById(movieId);
			movieRepository.delete(movie);
			return "success";
		}
		throw new MoviepurException(401, "password is wrong");
	}

	@Override
	public String downloadJson(String password) throws MoviepurException {
		if (passwordEncoder.matches(password, ADMINPASSWORD)) {
			try {
				ObjectMapper json = new ObjectMapper();
				return json.writeValueAsString(movieRepository.getAll())+"\n\n\n\n\n\n"
						   +json.writeValueAsString(userService.getAllUser())+"\n\n\n\n\n\n"
				           +json.writeValueAsString(filmSeriesService.getAll())+"\n\n\n\n\n\n"
				           +json.writeValueAsString(primeryKeySeqService.getAll());
			} catch (JsonProcessingException e) {
				return "failed";
			}
		}
		throw new MoviepurException(401, "password is wrong");
	}

	@Override
	public String saveAllPrimeryKeq(List<PrimeryKeySeq> list) {
		return primeryKeySeqService.saveAll(list);
	}

}
