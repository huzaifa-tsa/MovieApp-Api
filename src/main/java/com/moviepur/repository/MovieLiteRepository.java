package com.moviepur.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

	@Query(nativeQuery = true, value = "SELECT m.id,m.name,m.image_url,r.value FROM movie as m LEFT JOIN rating as r ON m.id = r.id WHERE LOWER(r.name) =:name  AND r.value BETWEEN :min and :max  GROUP BY m.id,r.value ORDER BY r.value DESC")
	public List<MovieLite> getAcouradingToRating(@Param("name") String name, @Param("min") float min,
			@Param("max") float max);

	@Query(nativeQuery = true, value = "SELECT id,name,image_url FROM movie WHERE id IN (SELECT id FROM language WHERE LOWER(language)=:language) ORDER BY id DESC")
	public List<MovieLite> findAllByLanguage(@Param("language") String language);

	@Query(nativeQuery = true, value = "SELECT id,name,image_url FROM movie WHERE release_date  BETWEEN :startDate AND :endDate ORDER BY release_date DESC")
	public List<MovieLite> findAllByReleaseYear(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

	@Query(nativeQuery = true, value = "SELECT id,name,image_url FROM movie WHERE id IN (SELECT DISTINCT id FROM genre WHERE LOWER(genre) = :genre) ORDER BY id DESC")
	public List<MovieLite> findAllByGenreDesc(@Param("genre") String genre);
	
	@Query(nativeQuery = true, value = "SELECT  DISTINCT genre  FROM  genre")
	public Set<String> getAllGenres();

	@Query(nativeQuery = true, value = "SELECT  DISTINCT language  FROM  language")
	public Set<String> getAllLanguages();
	
	@Query(nativeQuery = true, value = "SELECT id,name,image_url FROM movie where LOWER(name) LIKE %:name%")
	public List<MovieLite> getAllByNameContaines(@Param("name") String name);

	@Query(nativeQuery = true, value = "SELECT id,name,image_url FROM movie WHERE id = :movieId")
	public Optional<MovieLite> getById(@Param("movieId") int movieId);

	@Query(nativeQuery = true, value = "SELECT id,name,image_url FROM movie ORDER BY release_date DESC LIMIT 5")
	public List<MovieLite> getByLatestReleaseDate();

	@Query(nativeQuery = true, value = "SELECT id,name,image_url FROM movie WHERE industry_name = :industryName")
	public List<MovieLite> getByIndustryName(@Param("industryName") String industryName);

	@Query(nativeQuery = true, value = "SELECT id,name,image_url FROM movie WHERE id IN (SELECT DISTINCT id FROM genre WHERE LOWER(genre) = :genre) And industry_name = :industryName ORDER BY id DESC" )
	public List<MovieLite> getByGenreAndIndustryName(@Param("genre") String genre,@Param("industryName")  String industryName);

	@Query(nativeQuery = true, value = "SELECT DISTINCT industry_name  FROM movie WHERE id IN (SELECT DISTINCT id FROM genre WHERE LOWER(genre) = :genre)" )
	public List<String> getAllIndustryByGenre(@Param("genre") String genre);
	
	@Query(nativeQuery = true, value = "SELECT id,name,image_url  FROM movie ORDER BY RAND ( )  LIMIT 3" )
	public List<MovieLite> getRandomMovie();

	@Query(nativeQuery = true, value = "SELECT id,name,image_url  FROM movie AS m RIGHT JOIN user_likes_movie AS u ON m.id = u.likes_movie_id  GROUP BY u.likes_movie_id  ORDER BY COUNT(u.likes_movie_id)  DESC LIMIT 5" )
	public List<MovieLite> getMostLikeMovie();

	@Query(nativeQuery = true, value = "SELECT id,name,image_url FROM movie where LOWER(directorys) LIKE %:name%")
	public List<MovieLite> getByDirecterName(@Param("name") String name);

	@Query(nativeQuery = true, value = "SELECT id,name,image_url FROM movie where LOWER(writers) LIKE %:name%")
	public List<MovieLite> getByWriterName(@Param("name") String name);

	@Query(nativeQuery = true, value = "SELECT id,name,image_url FROM movie where LOWER(stars) LIKE %:name%")
	public List<MovieLite> getByStarName(@Param("name") String name);

}
