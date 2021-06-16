package com.moviepur.servies.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidConfig.Priority;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.MulticastMessage;
import com.moviepur.entitys.FirebaseClass;
import com.moviepur.entitys.Movie;
import com.moviepur.entitys.PrimeryKeySeq;
import com.moviepur.entitys.SeriesDownloadLink;
import com.moviepur.entitys.Type;
import com.moviepur.exception.MoviepurException;
import com.moviepur.repository.FirebaseClassRepository;
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
	
	@Autowired
	private FirebaseClassRepository firebaseRepository;
	
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

	private boolean sendFirebaseMessage(String title,String body) {
		try {
			List<String> allTokens =userService.getAllUserToken();
			
			int loop = allTokens.size()/500;
			for(int i=0;i<loop;i++)
			{
				MulticastMessage message = MulticastMessage.builder().putData("Title", title)
						.putData("notificationText", body)
						.setAndroidConfig(AndroidConfig.builder().setPriority(Priority.HIGH).build())
						.addAllTokens(allTokens.subList(0+(500*1), 499+(500*i))).build();
				FirebaseMessaging.getInstance().sendMulticast(message);
			}
			
			MulticastMessage message = MulticastMessage.builder().putData("Title", title)
					.putData("notificationText", body)
					.setAndroidConfig(AndroidConfig.builder().setPriority(Priority.HIGH).build())
					.addAllTokens(allTokens.subList(loop*500,allTokens.size())).build();
			FirebaseMessaging.getInstance().sendMulticast(message);
			return true;
		}catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return false;
		}
	}
	
	public boolean exsist(String name, LocalDate date , Type type) {
		return movieRepository.checkExists(name,date, type.toString());
	}
	
	@Override
	public Movie addMovie(Movie movie) throws MoviepurException {
		try {
			if(exsist(movie.getName(), movie.getReleaseDate(), movie.getType()))
				throw new MoviepurException(409, "alredy exists");
			movie.setId(primeryKeySeqService.getCurrentPostion("MOVIETABLE"));
			movie = movieRepository.save(movie);
			sendFirebaseMessage("New "+movie.getType()+" Add", "New "+movie.getType()+" Add On Moviepur.\n"+movie.getName()+" is the "+movie.getIndustryName()+" "+movie.getType()+".");
			return movie;
		} catch (Exception e) {
			throw new MoviepurException(500, e.getLocalizedMessage());
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
		movieEdit.setMovieDownloadLink(movie.getMovieDownloadLink());
		movieEdit.setSeriesDownloadLinks(movie.getSeriesDownloadLinks());
		return movieRepository.save(movieEdit);
	}

	@Override
	public Movie updateMovieDownloadLinks(int id, String downloadLink) throws MoviepurException {
		Movie movie = getById(id);
		try {
			if(movie.getType() == Type.Series)
				throw new MoviepurException("This Is Series Not A Movie");
			movie.setMovieDownloadLink(downloadLink);
			movie = movieRepository.save(movie);
			sendFirebaseMessage("Update "+movie.getName()+" Download Link", movie.getName()+" Download Link Update.\n Go And Enjoy.");
			return movie;
		}catch (Exception e) {
			throw new MoviepurException(404,"problem\n"+e.getLocalizedMessage());
		}
	}

	@Override
	public Movie updateSeriesDownloadLinks(int id, List<SeriesDownloadLink> seriesDownloadLinks)
			throws MoviepurException {
		Movie movie = getById(id);
		try {
			if(movie.getType() == Type.Movie)
				throw new MoviepurException("This Is Movie Not A Series");
			movie.setSeriesDownloadLinks(seriesDownloadLinks);
			movie = movieRepository.save(movie);
			sendFirebaseMessage("Update "+movie.getName()+" Download Link", movie.getName()+" Download Link Update.\n Go And Enjoy.");
			return movie;
		}catch (Exception e) {
			throw new MoviepurException(404,"problem\n"+e.getLocalizedMessage());
		}
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
				           +json.writeValueAsString(primeryKeySeqService.getAll())+"\n\n\n\n\n\n"
				           +json.writeValueAsString(firebaseRepository.findAll())+"\n\n\n\n\n\n";
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

	@Override
	public Object getForSite(String name) {
		if(name.equals("Moviepur"))
			return movieRepository.getForSiteWithoutName();
		else
			return movieRepository.getForSiteWithName(name);
	}

	
	//===================Firebase Class==========
	
	@Override
	public FirebaseClass getFirebaseClass() throws MoviepurException {
		return firebaseRepository.getFirebaseClass().orElseThrow(() -> new MoviepurException(404, "Firebase Not found"));
	}

	@Override
	public FirebaseClass saveFirebaseClass(FirebaseClass firebaseClass) {
		firebaseClass.setId(1);
		return firebaseRepository.save(firebaseClass);
	}


}
