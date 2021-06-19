package com.moviepur.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviepur.entitys.Banner;
import com.moviepur.exception.MoviepurException;
import com.moviepur.servies.BannerService;


@RestController
@RequestMapping("/banner")
@CrossOrigin
public class BannerController {

	@Autowired
	private BannerService bannerService; 
	
	@GetMapping
	public Banner getBanner() throws MoviepurException {
		return bannerService.getBanner();
	}
	
	@PostMapping
	public Banner save(@RequestBody Banner banner) {
		return bannerService.save(banner);
	}
}
