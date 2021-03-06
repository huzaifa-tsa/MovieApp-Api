package com.moviepur.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviepur.entitys.Feedback;
import com.moviepur.entitys.RequestAndFeedback;
import com.moviepur.servies.RequestAndFeedbackSerive;

@RestController
@RequestMapping("/requset")
@CrossOrigin
public class RequestAndFeedbackController {

	@Autowired
	private RequestAndFeedbackSerive requestAndFeedbackSerive;
	
	@GetMapping
	public List<RequestAndFeedback> getAll() {
		return requestAndFeedbackSerive.getAll();			
	}
	
	@GetMapping("/{type}")
	public List<RequestAndFeedback> getAllByType(@PathVariable Feedback type) {
		return requestAndFeedbackSerive.getByRequest(type);			
	}
	
	@GetMapping("{status}/{start}/{end}")
	public List<RequestAndFeedback> getAllByDate(@PathVariable Feedback status, @PathVariable String start, @PathVariable String end) {
		return requestAndFeedbackSerive.getByData(status,LocalDate.parse(start), LocalDate.parse(end));			
	}
	
	@PostMapping("/{status}/{message}")
	public RequestAndFeedback save(@PathVariable Feedback status, @PathVariable String message) {
		return requestAndFeedbackSerive.saveRequestAndFeedback(status, message);
	}
	
	@DeleteMapping("/{id}")
	public String save(@PathVariable int id) {
		return requestAndFeedbackSerive.deleteRequestAndFeedback(id);
	}
}
