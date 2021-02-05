package com.moviepur.dao;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.moviepur.entitys.Movie;
import com.moviepur.entitys.MovieLite;

public interface MovieDAO extends JpaRepository<Movie, Integer> {
	
	@Query("Select m FROM Movie m WHERE m.id=?1 ")
	public Movie getOne(@Param("id") int id);
	
	@Query("SELECT new com.moviepur.entitys.MovieLite(m.id, m.name, m.image_url) FROM Movie m ORDER BY m.id DESC")
	public List<MovieLite> getAll();
	
	@Query("SELECT new com.moviepur.entitys.MovieLite(m.id, m.name, m.image_url) FROM Movie m WHERE m.movieOrNot=?1 ORDER BY m.id DESC ")
	public List<MovieLite> getType(boolean type);
	
	@Query("SELECT DISTINCT new com.moviepur.entitys.MovieLite(m.id, m.name, m.image_url) FROM Movie m JOIN m.genre g WHERE g IN :genre  ORDER BY m.id DESC ")
	public List<MovieLite> getGenre(@Param("genre") Set<String> genre);
	
	@Query("SELECT new com.moviepur.entitys.MovieLite(m.id, m.name, m.image_url) FROM Movie m WHERE m.rating BETWEEN :min AND :max ORDER BY m.rating DESC ")
	public List<MovieLite> getAcouradingToRating(@Param("min") float min, @Param("max")  float max);
	
	@Query("SELECT new com.moviepur.entitys.MovieLite(m.id, m.name, m.image_url) FROM Movie m  JOIN m.language l WHERE l IN :language  ORDER BY m.id DESC")
	public List<MovieLite> getByLanguage(@Param("language") String language);
	
}
