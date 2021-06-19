package com.moviepur.servies.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviepur.entitys.Banner;
import com.moviepur.exception.MoviepurException;
import com.moviepur.repository.BannerRepository;
import com.moviepur.servies.BannerService;

@Service
public class BannerServiceImpl implements BannerService {

	@Autowired
	private BannerRepository bannerRepository;
	
	@Override
	public Banner getBanner() throws MoviepurException {
		return bannerRepository.getById().orElseThrow(() -> new MoviepurException(404, "Banner Not Found") );
	}

	@Override
	public Banner save(Banner banner) {
		banner.setId(1);
		return bannerRepository.save(banner);
	}

	@Override
	public void bannerUpdate(boolean position, String imageLink) {
		try {
		Banner banner =	getBanner();
		if(position)
			banner.setImage2(imageLink);
		else
			banner.setImage3(imageLink);
		bannerRepository.save(banner); }
		catch (Exception e) { }
	}

}
