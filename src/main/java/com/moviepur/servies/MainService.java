package com.moviepur.servies;

import java.time.LocalDate;
import java.util.List;

import com.moviepur.entitys.FirebaseClass;
import com.moviepur.entitys.Movie;
import com.moviepur.entitys.PrimeryKeySeq;
import com.moviepur.entitys.SeriesDownloadLink;
import com.moviepur.entitys.Type;
import com.moviepur.exception.MoviepurException;

public interface MainService {

	public List<Movie> getAllMovie();

	public String saveAllMovie(List<Movie> movielist);

	public Movie getById(int movieid) throws MoviepurException;

	public boolean exsist(String name, LocalDate date , Type type);
	
	public Movie addMovie(Movie movie) throws MoviepurException;

	public Movie updateMovie(int id, Movie movie) throws MoviepurException;

	public Movie updateMovieSome(int id, Movie movie) throws MoviepurException;
	
	public Movie updateMovieDownloadLinks(int id, String downloadLink) throws MoviepurException ;

	public Movie updateSeriesDownloadLinks(int id, List<SeriesDownloadLink> seriesDownloadLinks) throws MoviepurException ;
	
	public String delete(int movieId, String password)throws MoviepurException;

	public String downloadJson(String password) throws MoviepurException;

	public String saveAllPrimeryKeq(List<PrimeryKeySeq> list);

	public Object getForSite(String name);

	
	//========Firebase=========

	public FirebaseClass getFirebaseClass() throws MoviepurException;
	
	public FirebaseClass saveFirebaseClass(FirebaseClass firebaseClass);
	
	public boolean sendFirebaseMessage(String title,String body, String image) ;

}
