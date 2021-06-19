package com.moviepur.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.moviepur.entitys.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {


	@Query(nativeQuery = true, value = "SELECT * FROM banner Where id = 1")
	public Optional<Banner> getById();

}
