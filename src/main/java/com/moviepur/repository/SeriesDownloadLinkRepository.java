package com.moviepur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moviepur.entitys.SeriesDownloadLink;

@Repository
public interface SeriesDownloadLinkRepository extends JpaRepository<SeriesDownloadLink, String>{

}
