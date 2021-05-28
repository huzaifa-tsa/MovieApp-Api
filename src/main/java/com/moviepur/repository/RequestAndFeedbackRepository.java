package com.moviepur.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.moviepur.entitys.RequestAndFeedback;

@Repository
public interface RequestAndFeedbackRepository extends JpaRepository<RequestAndFeedback, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM feedback ORDER BY id DESC")
	List<RequestAndFeedback> getAll();

	@Query(nativeQuery = true, value = "SELECT * FROM feedback WHERE feedback = :status ORDER BY id DESC")
	List<RequestAndFeedback> getByFeedbackAndRequest(@Param("status") boolean request);

	@Query(nativeQuery = true, value = "SELECT * FROM feedback WHERE created_date  BETWEEN :startDate AND :endDate AND feedback =:status ORDER BY created_date DESC")
	List<RequestAndFeedback> getByDate(@Param("status") boolean status,@Param("startDate") LocalDate start,@Param("endDate") LocalDate plusDays);

}
