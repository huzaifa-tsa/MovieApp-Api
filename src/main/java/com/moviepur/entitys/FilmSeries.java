package com.moviepur.entitys;

import java.util.LinkedHashSet;
import java.util.Set;

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
	private String name;	
	private String imageUrl;
	
	@ManyToMany
	private Set<MovieLite> movies = new LinkedHashSet<>();
}
