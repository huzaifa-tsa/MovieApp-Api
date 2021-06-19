package com.moviepur.entitys;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="banner")
@Getter
@Setter
@ToString
public class Banner {

	@Id
	private int id;
	private String image1;
	private String image2;
	private String image3;
	
}
