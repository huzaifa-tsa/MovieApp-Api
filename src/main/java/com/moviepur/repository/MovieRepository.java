package com.moviepur.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.moviepur.entitys.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM movie")
	List<Movie> getAll();
	
	@Query(nativeQuery = true, value = "SELECT * FROM movie WHERE id = :movieId")
	public Optional<Movie> getById(@Param("movieId") int movieId);

	@Query(nativeQuery = true, value = "SELECT id,name,type,release_date FROM movie ORDER BY id DESC ")
	public List<Map<String, Object>> getForSiteWithoutName();

	@Query(nativeQuery = true, value = "SELECT id,name,type,release_date FROM movie where LOWER(name) LIKE %:name% ORDER BY id DESC")
	public List<Map<String, Object>> getForSiteWithName(@Param("name") String name);

}
