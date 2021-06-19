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

	@Query(nativeQuery = true, value = "SELECT id,name,image_url, release_date,type FROM movie ORDER BY id DESC")
	public List<MovieLite> findAllByDesc();

	@Query(nativeQuery = true, value = "SELECT id,name,image_url, release_date,type FROM movie WHERE LOWER(type)=:type ORDER BY id DESC ")
	public List<MovieLite> findAllByType(@Param("type") String type);

	@Query(nativeQuery = true, value = "SELECT id, name, image_url,  release_date,type FROM movie WHERE rotten_tomatoes BETWEEN :min and :max  ORDER BY rotten_tomatoes DESC")
	public List<MovieLite> getRottenToRating(@Param("min") float min, @Param("max") float max);

	@Query(nativeQuery = true, value = "SELECT id, name, image_url,  release_date,type FROM movie WHERE imdb BETWEEN :min and :max  ORDER BY imdb  DESC")
	public List<MovieLite> getIMDbToRating(@Param("min") float min, @Param("max") float max);

	@Query(nativeQuery = true, value = "SELECT id, name, image_url,  release_date,type FROM movie WHERE moviepur BETWEEN :min and :max  ORDER BY moviepur DESC")
	public List<MovieLite> getMoviepurToRating(@Param("min") float min, @Param("max") float max);

	
	@Query(nativeQuery = true, value = "SELECT id,name,image_url,release_date,type FROM movie WHERE LOWER(language)  LIKE %:language% ORDER BY id DESC")
	public List<MovieLite> findAllByLanguage(@Param("language") String language);

	@Query(nativeQuery = true, value = "SELECT id,name,image_url,release_date,type FROM movie WHERE release_date  BETWEEN :startDate AND :endDate ORDER BY release_date DESC")
	public List<MovieLite> findAllByReleaseYear(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

	@Query(nativeQuery = true, value = "SELECT id,name,image_url,release_date,type FROM movie WHERE LOWER(genre) LIKE %:genre% ORDER BY id DESC")
	public List<MovieLite> findAllByGenreDesc(@Param("genre") String genre);
	
	@Query(nativeQuery = true, value = "SELECT array_to_string(array(SELECT genre FROM movie),', ');")
	public String getAllGenres();

	@Query(nativeQuery = true, value = "SELECT array_to_string(array(SELECT language FROM movie),', ');")
	public String getAllLanguages();
	
	@Query(nativeQuery = true, value = "SELECT id,name,image_url,release_date,type FROM movie where LOWER(name) LIKE %:name%")
	public List<MovieLite> getAllByNameContaines(@Param("name") String name);

	@Query(nativeQuery = true, value = "SELECT id,name,image_url,release_date,type FROM movie WHERE id = :movieId")
	public Optional<MovieLite> getById(@Param("movieId") int movieId);

	//======Latest Relsed==================
	@Query(nativeQuery = true, value = "SELECT id,name,image_url,release_date,type FROM movie WHERE industry_name = :industryName ORDER BY release_date DESC LIMIT :limit")
	public List<MovieLite> getByLatestReleaseDate(@Param("industryName") String industryName,@Param("limit") int limit);

	@Query(nativeQuery = true, value = "SELECT id,name,image_url,release_date,type FROM movie WHERE industry_name = :industryName And LOWER(type) = :type ORDER BY release_date DESC LIMIT :limit")
	public List<MovieLite> getByLatestReleaseDateByType(@Param("type")String type, @Param("industryName") String industryName,@Param("limit") int limit);

	//===================
	
	@Query(nativeQuery = true, value = "SELECT id,name,image_url,release_date,type FROM movie WHERE industry_name = :industryName")
	public List<MovieLite> getByIndustryName(@Param("industryName") String industryName);

	@Query(nativeQuery = true, value = "SELECT id,name,image_url,release_date,type FROM movie WHERE LOWER(genre) LIKE %:genre% And industry_name = :industryName ORDER BY id DESC" )
	public List<MovieLite> getByGenreAndIndustryName(@Param("genre") String genre,@Param("industryName")  String industryName);

	@Query(nativeQuery = true, value = "SELECT DISTINCT industry_name,type  FROM movie WHERE LOWER(genre) LIKE %:genre%" )
	public Set<String> getAllIndustryByGenre(@Param("genre") String genre);
	
	@Query(nativeQuery = true, value = "SELECT id,name,image_url,release_date,type  FROM movie ORDER BY  RANDOM( )  LIMIT 3" )
	public List<MovieLite> getRandomMovie();

	//============Most LIked=========
	@Query(nativeQuery = true, value = "SELECT id,name,image_url,release_date,type  FROM movie AS m RIGHT JOIN myuser_likes_movie AS u ON m.id = u.likes_movie_id  GROUP BY m.id  ORDER BY COUNT(u.likes_movie_id)  DESC LIMIT 10" )
	public List<MovieLite> getMostLikeMovie();

	@Query(nativeQuery = true, value = "SELECT id,name,image_url,release_date,type  FROM movie AS m RIGHT JOIN myuser_likes_movie AS u ON m.id = u.likes_movie_id WHERE LOWER(m.type) = :type   GROUP BY m.id  ORDER BY COUNT(u.likes_movie_id)  DESC LIMIT 10" )
	public List<MovieLite> getMostLikeMovieByType(@Param("type") String type);

	//=======================
	
	@Query(nativeQuery = true, value = "SELECT id,name,image_url,release_date,type FROM movie where LOWER(directors) LIKE %:name%")
	public List<MovieLite> getByDirecterName(@Param("name") String name);

	@Query(nativeQuery = true, value = "SELECT id,name,image_url,release_date,type FROM movie where LOWER(writers) LIKE %:name%")
	public List<MovieLite> getByWriterName(@Param("name") String name);

	@Query(nativeQuery = true, value = "SELECT id,name,image_url,release_date,type FROM movie where LOWER(stars) LIKE %:name%")
	public List<MovieLite> getByStarName(@Param("name") String name);

	@Query(nativeQuery = true, value = "SELECT id,name,image_url, release_date,type FROM movie ORDER BY id DESC LIMIT 10")
	public List<MovieLite> getLatestAdd();




}
