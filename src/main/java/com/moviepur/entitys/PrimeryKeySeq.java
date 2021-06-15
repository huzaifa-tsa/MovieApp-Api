package com.moviepur.entitys;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="my_database_seq")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PrimeryKeySeq {

	@Id
	private int id;
	private String tableName;
	private int currentPosition;  
	
}
