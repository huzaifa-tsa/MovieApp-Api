package com.moviepur.entitys;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="movie")
@Getter
@Setter
@ToString
public class Movie {
	
	@Id
	private int id;

	@Column(name="name", length=500)
	private String name;

	@Column(name="image_url", length=500)
	private String image_url;
	
	@Column(name="description", length=3000)
	private String description;
	
	private LocalDate releaseDate;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Type type;

	@Enumerated(EnumType.STRING)
	private IndustriesName industryName;
	
	private String runTime;

	private String directors;
	private String writers;
	
	
	private float rottenTomatoes;
	private float imdb;
	private float moviepur;
	
	private String movieDownloadLink;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<SeriesDownloadLink> seriesDownloadLinks;
	
	@Column(name="stars", length=2000)
	private String stars;
		
	@ElementCollection
	@CollectionTable(name = "genre", joinColumns = @JoinColumn(name = "id"))
	private Set<String> genre ;

	@ElementCollection
	@CollectionTable(name = "language", joinColumns = @JoinColumn(name = "id"))
	private Set<String> language ;

	@ElementCollection
	@CollectionTable(name = "otherImages", joinColumns = @JoinColumn(name = "id"))
	private Set<String> otherImages;

}
