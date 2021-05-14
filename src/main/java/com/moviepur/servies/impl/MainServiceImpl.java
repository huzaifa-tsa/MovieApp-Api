package com.moviepur.servies.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviepur.entitys.Movie;
import com.moviepur.exception.MoviepurException;
import com.moviepur.repository.MovieRepository;
import com.moviepur.servies.MainService;
@Service
public class MainServiceImpl implements MainService {

	@Autowired
	private	MovieRepository movieRepository;

	@Override
	public List<Movie> getAllMovie() {
		return movieRepository.findAll();
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
	public Movie getById(int movieid) throws MoviepurException {
		Optional<Movie> optional = movieRepository.findById(movieid);
		if (optional.isPresent())
			return optional.get();
		throw new MoviepurException(404, "Movie Not Found");
	}

	@Override
	public Movie add_Movie(Movie movie) throws MoviepurException {
		try {
				return movieRepository.save(movie);
		} catch (Exception e) {
			throw new MoviepurException(500, "Internal Server Error");
		}
	}

	@Override
	public Movie update_Movie(int id, Movie movieEdit) throws MoviepurException {
			Movie movie = getById(id);
			movieEdit.setId(movie.getId());
			movieEdit.setDownload_link(movie.getDownload_link());
			return movieRepository.save(movieEdit);
	}

	@Override
	public Movie update_Downloads_Links(int id, Map<String, String> download_Links) throws MoviepurException {
		Movie movie = getById(id);
		movie.setDownload_link(download_Links);
		return movieRepository.save(movie);
	}

	@Override
	public String delete(int movieId, String password) throws MoviepurException {
		if (password.equals("moviepur$$143")) {
			Movie movie = getById(movieId);
			movieRepository.delete(movie);
			return "success";
		}
		throw new MoviepurException(401,"password is wrong");
	}

}
