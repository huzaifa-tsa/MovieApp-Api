package com.moviepur.servies;

import java.time.LocalDate;
import java.util.List;

import com.moviepur.entitys.RequestAndFeedback;

public interface RequestAndFeedbackSerive {

	public List<RequestAndFeedback> getAll();
	
	public List<RequestAndFeedback> getByData(boolean status,LocalDate start , LocalDate end);
	
	public RequestAndFeedback saveRequestAndFeedback(boolean feedback, String message);
	
	public List<RequestAndFeedback> getByRequest(boolean feedback) ;
	
	public String deleteRequestAndFeedback(int id) ;

}
