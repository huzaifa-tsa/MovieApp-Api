package com.moviepur.entitys;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="movie")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Movie {
	
	@Id
	private int id;
	
	private String name, image_url;
	
	@Column(name="CONTENT", length=2000)
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
	private String stars;
		
	@ElementCollection
	@MapKeyColumn(name="name",length =50 )
    @Column(name="value")
	@CollectionTable(name="rating",joinColumns = @JoinColumn(name="id"))
	private Map<String, Float> rating = new LinkedHashMap<>(3,0.1f);

	@ElementCollection(fetch = FetchType.LAZY)
	@MapKeyColumn(name="name" , length = 50)
    @Column(name="link",length = 500)
	@CollectionTable(name = "download_link", joinColumns = @JoinColumn(name = "id"))
	private Map<String,String> download_link = new LinkedHashMap<>(5, 0.25f);

	@ElementCollection
	@CollectionTable(name = "genre", joinColumns = @JoinColumn(name = "id"))
	private Set<String> genre = new HashSet<>(5,0.5f);

	@ElementCollection
	@CollectionTable(name = "language", joinColumns = @JoinColumn(name = "id"))
	private Set<String> language = new HashSet<>(2,0.5f);

	@ElementCollection
	@CollectionTable(name = "otherImages", joinColumns = @JoinColumn(name = "id"))
	private Set<String> otherImages = new HashSet<>(3,0.5f);

}
