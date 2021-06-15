package com.moviepur.entitys;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="filmseries")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class FilmSeries {

	@Id
	private int id;
	@Column(name="name", length=500)
	private String name;		
	@Column(name="image_url", length=500)
	private String imageUrl;
	
	@ManyToMany
	private Set<MovieLite> movies = new LinkedHashSet<>();
}
