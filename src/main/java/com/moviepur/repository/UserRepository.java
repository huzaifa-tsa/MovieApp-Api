package com.moviepur.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.moviepur.entitys.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	@Query(nativeQuery = true, value = "SELECT * FROM myuser ")
	public List<User> getAllUser();

	@Query(nativeQuery = true, value = "SELECT * FROM myuser WHERE token = :token")
	public Optional<User> getByToken(String token);

	@Query(nativeQuery = true, value = "SELECT EXISTS(SELECT * from myuser WHERE token = :token)")
	public boolean checkUserIsPresent(String token);

	@Query(nativeQuery = true, value = "SELECT id , token, created_date, update_date FROM myuser WHERE update_date <= :date ORDER BY update_date DESC")
	public List<Map<String, Object>> getAllUserNotUpdateLastMonths(@Param("date") LocalDate minusMonths);

	@Query(nativeQuery = true, value = "select Exists (Select * from myuser as m Left Join myuser_likes_movie as l on m.id = l.user_id where m.token = :token and likes_movie_id = :movieId);")
	public boolean checkUserLikeOrNot(@Param("token") String token, @Param("movieId") int movieId);

	@Query(nativeQuery = true, value = "SELECT token FROM myuser ")
	public List<String> getAllUserToken();
		
}
