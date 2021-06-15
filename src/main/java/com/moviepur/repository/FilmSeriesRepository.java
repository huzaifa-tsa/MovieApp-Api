package com.moviepur.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.moviepur.entitys.FilmSeries;

@Repository
public interface FilmSeriesRepository extends JpaRepository<FilmSeries, Integer> {

		@Query(nativeQuery = true, value = "SELECT * FROM filmseries ORDER BY id DESC")
		public List<FilmSeries> getAll();

		@Query(nativeQuery = true, value = "SELECT * FROM filmSeries WHERE id = :id")
		public Optional<FilmSeries> getById(@Param("id") int id);

}
