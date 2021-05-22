package com.moviepur.entitys;

import java.time.LocalDate;
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
@Table(name="myuser")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

	@Id
	private int id;
	
	private String token;	
	private String password;
	private LocalDate createdDate;
	private LocalDate updateDate;
	
	@ManyToMany
	private Set<MovieLite> likesMovie = new LinkedHashSet<>();

	public User(int id, String token, String password, LocalDate createdDate) {
		super();
		this.id = id;
		this.token = token;
		this.password = password;
		this.createdDate = createdDate;
	}

}
