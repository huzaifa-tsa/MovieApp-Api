package com.moviepur.servies.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviepur.entitys.RequestAndFeedback;
import com.moviepur.repository.RequestAndFeedbackRepository;
import com.moviepur.servies.PrimeryKeySeqService;
import com.moviepur.servies.RequestAndFeedbackSerive;

@Service
public  class RequestAndFeedbackSeriveImpl implements RequestAndFeedbackSerive {

	@Autowired
	private RequestAndFeedbackRepository feedbackRepository;

	@Autowired
	private PrimeryKeySeqService primeryKeySeqService;
	
	@Override
	public List<RequestAndFeedback> getAll() {
		return feedbackRepository.getAll();
	}

	@Override
	public RequestAndFeedback saveRequestAndFeedback(boolean request, String message) {
		return feedbackRepository.save(new RequestAndFeedback(primeryKeySeqService.getCurrentPostion("FEEDBACKTABLE"),request,message,LocalDate.now()));
	}

	@Override
	public List<RequestAndFeedback> getByRequest(boolean request){
		return feedbackRepository.getByFeedbackAndRequest(request);
	}

	@Override
	public String deleteRequestAndFeedback(int id) {
		feedbackRepository.deleteById(id);
		return "success";
	}

	@Override
	public List<RequestAndFeedback> getByData(boolean status,LocalDate start, LocalDate end) {
		return feedbackRepository.getByDate(status,start,end.plusDays(1));
	}

}
