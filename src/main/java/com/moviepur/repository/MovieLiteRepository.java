package com.moviepur.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.moviepur.entitys.MovieLite;

@Repository
public interface MovieLiteRepository extends JpaRepository<MovieLite, Integer> {

	@Query(nativeQuery = true, value = "SELECT id,name,image_url FROM movie ORDER BY id DESC")
	public List<MovieLite> findAllByDesc();

	@Query(nativeQuery = true, value = "SELECT id,name,image_url FROM movie WHERE type=:type ORDER BY id DESC ")
	public List<MovieLite> findAllByType(@Param("type") String type);

	@Query(nativeQuery = true, value = "SELECT id,name,image_url FROM movie WHERE id IN (SELECT DISTINCT id FROM rating WHERE value BETWEEN :min AND :max AND NAME = :name ORDER BY value DESC )")
	public List<MovieLite> getAcouradingToRating(@Param("name") String name, @Param("min") float min,
			@Param("max") float max);

	@Query(nativeQuery = true, value = "SELECT id,name,image_url FROM movie WHERE id IN (SELECT id FROM language WHERE language=:language) ORDER BY id DESC")
	public List<MovieLite> findAllByLanguage(@Param("language") String language);

	@Query(nativeQuery = true, value = "SELECT id,name,image_url FROM movie WHERE release_date >= :startDate and release_date <= :endDate ORDER BY release_date DESC")
	public List<MovieLite> findAllByReleaseYear(@Param("startDate") String startDate, @Param("endDate") String endDate);

	@Query(nativeQuery = true, value = "SELECT id,name,image_url FROM movie WHERE id IN (SELECT DISTINCT id FROM genre WHERE genre = :genre) ORDER BY id DESC")
	public List<MovieLite> findAllByGenreDesc(@Param("genre") String genre);

	public List<MovieLite> findAllByNameLikeIgnoreCase(String movieName);
	
	@Query(nativeQuery = true, value = "SELECT  DISTINCT genre  FROM  genre")
	public Set<String> getAllGenres();

	@Query(nativeQuery = true, value = "SELECT  DISTINCT language  FROM  language")
	public Set<String> getAllLanguages();
	
	@Query(nativeQuery = true, value = "SELECT id,name,image_url FROM movie where LOWER(name) LIKE %:name%")
	public List<MovieLite> getAllByNameContaines(@Param("name") String name);
	
	
	

}
