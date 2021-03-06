package com.moviepur.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviepur.entitys.FirebaseClass;
import com.moviepur.entitys.Movie;
import com.moviepur.entitys.PrimeryKeySeq;
import com.moviepur.entitys.SeriesDownloadLink;
import com.moviepur.entitys.Type;
import com.moviepur.exception.MoviepurException;
import com.moviepur.servies.MainService;

@RestController
@CrossOrigin
@RequestMapping("/main")
public class MainController {

	@Autowired
	private  MainService mainService;

	@GetMapping
	public List<Movie> getAllMovie() {
		return mainService.getAllMovie();
	}
	
	@GetMapping("exsist/{name}/{type}/{date}")
	public boolean exsist(@PathVariable String name, @PathVariable Type type,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date ) {
		return mainService.exsist(name, date, type);
	}
	
	@PostMapping
	public String saveAll(@RequestBody List<Movie> movielist) {
		return mainService.saveAllMovie(movielist);
	}

	@PostMapping("/get/{movieid}")
	public Movie getOne(@PathVariable int movieid) throws MoviepurException {
		return mainService.getById(movieid);
	}

	@PostMapping("/add")
	public Movie addOneMovie(@RequestBody Movie movie) throws MoviepurException {
		return mainService.addMovie(movie);
	}

	@PutMapping("update/{movieId}")
	public Movie updateMovie(@PathVariable("movieId") int id, @RequestBody Movie movie) throws MoviepurException {
		return mainService.updateMovie(id, movie);
	}
	
	@PutMapping("updateSomeData/{movieId}")
	public Movie updateMovieSome(@PathVariable("movieId") int id, @RequestBody Movie movie) throws MoviepurException {
		return mainService.updateMovieSome(id, movie);
	}


	@PutMapping("update/downloadMovieLink/{movieId}/{movieDownloadLink}")
	public Movie updateMovieDowloadLink(@PathVariable("movieId") int id, @PathVariable String movieDownloadLink) throws MoviepurException {
		return mainService.updateMovieDownloadLinks(id, movieDownloadLink);
	}
	
	@PutMapping("update/downloadSeriesLink/{movieId}")
	public Movie updateSeriesDowloadLink(@PathVariable("movieId") int id, @RequestBody List<SeriesDownloadLink> downloadLinks)
			throws MoviepurException {
		return mainService.updateSeriesDownloadLinks(id, downloadLinks);
	}
	
	@DeleteMapping("/deletewithpermission/{movieId}/{password}")
	public String deleteMovie(@PathVariable int movieId, @PathVariable String password) throws MoviepurException {
		return mainService.delete(movieId, password);
	}

	@GetMapping("/downloadJson/{password}")
	public ResponseEntity<Object> downloadJson(@PathVariable String password) throws MoviepurException {
		try {
			byte[] customerJsonBytes = mainService.downloadJson(password).getBytes();
			
			return ResponseEntity
					.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=DATA_"+LocalDateTime.now()+".json")
					.contentType(MediaType.APPLICATION_JSON)
					.contentLength(customerJsonBytes.length)
					.body(customerJsonBytes);
		}catch (Exception e) {
			throw new MoviepurException(409,e.getLocalizedMessage());
		}        
	}
	
	@GetMapping("getForSite/{name}")
	public ResponseEntity<Object> getForSite(@PathVariable String name) {
		 CacheControl cacheControl = CacheControl.maxAge(60, TimeUnit.SECONDS)
			      .noTransform()
			      .mustRevalidate();
			    return ResponseEntity.ok()
			      .cacheControl(cacheControl)
			      .body(mainService.getForSite(name));
	}
	
	@PostMapping("/saveAllPrimery")
	public String saveAllPrimeryKeq(@RequestBody List<PrimeryKeySeq> list) {
		return mainService.saveAllPrimeryKeq(list);
	}
	
	
	//==========Firebase Class==================
	@GetMapping("firebase")
	public FirebaseClass getFirebaseClass() throws MoviepurException {
		return mainService.getFirebaseClass();
	}
	
	@PostMapping("firebase")
	public FirebaseClass saveFirebaseClass(@RequestBody FirebaseClass firebaseClass) {
		return mainService.saveFirebaseClass(firebaseClass);
	}
	
	@PostMapping("sendNotification/{title}/{body}/{image}")
	public boolean sendFirebaseMessage(@PathVariable String title, @PathVariable String body, @PathVariable String image) {
		return mainService.sendFirebaseMessage(title, body, image);
	}
	
	
}
