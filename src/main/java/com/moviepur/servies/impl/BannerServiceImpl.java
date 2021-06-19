package com.moviepur.servies.impl;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
	public Object getBanner() throws MoviepurException {
		Banner banner = bannerRepository.getById().orElseThrow(() -> new MoviepurException(404, "Banner Not Found") );
		List<Object> result =  new LinkedList<>();
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("id",1);
		map.put("image", banner.getImage1());
		result.add(map);
		map = new LinkedHashMap<>();
		map.put("id",2);
		map.put("image", banner.getImage2());
		result.add(map);
		map = new LinkedHashMap<>();
		map.put("id",3);
		map.put("image", banner.getImage3());
		result.add(map);
		return result;
	}

	@Override
	public Banner save(Banner banner) {
		banner.setId(1);
		return bannerRepository.save(banner);
	}

	@Override
	public void bannerUpdate(boolean position, String imageLink) {
		try {
		Banner banner =	bannerRepository.getById().orElseThrow(() -> new MoviepurException(404, "Banner Not Found") );
		if(position)
			banner.setImage2(imageLink);
		else
			banner.setImage3(imageLink);
		bannerRepository.save(banner); }
		catch (Exception e) { }
	}

}
