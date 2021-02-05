package com.moviepur.entitys;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="movie")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String name, image_url, description;
	private float rating;
	@Column(nullable = false)
	private boolean movieOrNot;

	@ElementCollection
	@CollectionTable(name = "download_link", joinColumns = @JoinColumn(name = "id"))
	private List<String> download_link = new ArrayList<String>(10);

	@ElementCollection
	@CollectionTable(name = "genre", joinColumns = @JoinColumn(name = "id"))
	private Set<String> genre = new HashSet<String>(5,0.5f);

	@ElementCollection
	@CollectionTable(name = "language", joinColumns = @JoinColumn(name = "id"))
	private Set<String> language = new HashSet<String>(2,0.5f);

	public Movie() {
		super();
	}

}
