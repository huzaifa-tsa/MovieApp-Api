package com.moviepur.servies.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviepur.entitys.PrimeryKeySeq;
import com.moviepur.exception.MoviepurException;
import com.moviepur.repository.PrimeryKeySeqRepository;
import com.moviepur.servies.PrimeryKeySeqService;

@Service
public class PrimeryKeySeqServiceImpl implements PrimeryKeySeqService {

	@Autowired
	private PrimeryKeySeqRepository primeryKeySeqRepository;
	
	private PrimeryKeySeq getByTableName(String tableName) throws MoviepurException {
		return primeryKeySeqRepository.getByTableName(tableName).orElseThrow(() ->new MoviepurException(404, "table not found"));
	}
	
	@Override
	public int getCurrentPostion(String tableName) {
		PrimeryKeySeq primeryKeySeq;
		try {
			primeryKeySeq = getByTableName(tableName);
		}catch (Exception e) {
			primeryKeySeq=new PrimeryKeySeq();
			primeryKeySeq.setTableName(tableName);
			primeryKeySeq.setCurrentPosition(1);
			primeryKeySeq.setId(countData());
		}

		primeryKeySeq.setCurrentPosition(primeryKeySeq.getCurrentPosition()+1);
		primeryKeySeqRepository.save(primeryKeySeq);
		return primeryKeySeq.getCurrentPosition()-1;
	}

	private int countData() {
		return primeryKeySeqRepository.getCount()+1;
	}

	@Override
	public List<PrimeryKeySeq> getAll() {
		return primeryKeySeqRepository.getAll();
	}

	@Override
	public String saveAll(List<PrimeryKeySeq> list) {
		primeryKeySeqRepository.saveAll(list);
		return "success";
	}
	
}
