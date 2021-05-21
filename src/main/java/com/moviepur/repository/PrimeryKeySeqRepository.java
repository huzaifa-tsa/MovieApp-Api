package com.moviepur.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.moviepur.entitys.PrimeryKeySeq;

@Repository
public interface PrimeryKeySeqRepository extends JpaRepository<PrimeryKeySeq, Integer>{


	@Query(nativeQuery = true, value = "SELECT * FROM my_database_seq WHERE table_name = :tableName")
	public Optional<PrimeryKeySeq> getByTableName(@Param("tableName") String tableName);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM  my_database_seq")
	public int getCount();

}
