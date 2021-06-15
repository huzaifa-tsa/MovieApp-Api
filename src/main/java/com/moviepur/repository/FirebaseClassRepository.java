package com.moviepur.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.moviepur.entitys.FirebaseClass;

@Repository
public interface FirebaseClassRepository extends JpaRepository<FirebaseClass, Integer> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM firebaseclass WHERE id = 1")
	public Optional<FirebaseClass> getFirebaseClass();
}
