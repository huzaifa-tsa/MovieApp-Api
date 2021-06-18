package com.moviepur.entitys;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="feedback")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestAndFeedback {

	@Id
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Feedback feedback;
	
	@Column(name="message", length=2000)
	private String message;
	private LocalDate createdDate;
	
}
