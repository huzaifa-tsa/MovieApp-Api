package com.moviepur.entitys;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name ="series_download_link ")
@Getter
@Setter
public class SeriesDownloadLink {
	
	private int partId;
	private String partName;
	@Id
	private String partLink;
	private String partRunTime;
	private String partImage;

}
