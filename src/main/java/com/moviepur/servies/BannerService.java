package com.moviepur.servies;

import com.moviepur.entitys.Banner;
import com.moviepur.exception.MoviepurException;

public interface BannerService {

	public Object getBanner() throws MoviepurException ;
	
	public Banner save(Banner banner);
	
	public void bannerUpdate(boolean postion, String imageLink); 
}
