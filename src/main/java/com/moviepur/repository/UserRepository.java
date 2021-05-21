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

	@Query(nativeQuery = true, value = "SELECT * FROM user ORDER BY id DESC")
	public List<User> getAllUser();

	@Query(nativeQuery = true, value = "SELECT * FROM user WHERE token = :token")
	public Optional<User> getByToken(String token);

	@Query(nativeQuery = true, value = "SELECT EXISTS(SELECT * from user WHERE token = :token)")
	public int checkUserIsPresent(String token);

	@Query(nativeQuery = true, value = "SELECT id , token, created_date, update_date FROM user WHERE update_date <= :date ORDER BY update_date DESC")
	public List<Map<String, Object>> getAllUserNotUpdateLastMonths(@Param("date") LocalDate minusMonths);
		
}
