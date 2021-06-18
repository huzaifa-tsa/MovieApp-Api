package com.moviepur.servies;

import java.time.LocalDate;
import java.util.List;

import com.moviepur.entitys.Feedback;
import com.moviepur.entitys.RequestAndFeedback;

public interface RequestAndFeedbackSerive {

	public List<RequestAndFeedback> getAll();
	
	public List<RequestAndFeedback> getByData(Feedback feedback,LocalDate start , LocalDate end);
	
	public RequestAndFeedback saveRequestAndFeedback(Feedback feedback, String message);
	
	public List<RequestAndFeedback> getByRequest(Feedback feedback) ;
	
	public String deleteRequestAndFeedback(int id) ;

}
