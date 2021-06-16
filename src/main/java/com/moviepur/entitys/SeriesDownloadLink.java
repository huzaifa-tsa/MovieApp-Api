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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((partLink == null) ? 0 : partLink.hashCode());
		result = prime * result + ((partName == null) ? 0 : partName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SeriesDownloadLink other = (SeriesDownloadLink) obj;
		if (partLink == null) {
			if (other.partLink != null)
				return false;
		} else if (!partLink.equals(other.partLink))
			return false;
		if (partName == null) {
			if (other.partName != null)
				return false;
		} else if (!partName.equals(other.partName))
			return false;
		return true;
	}
	
}
