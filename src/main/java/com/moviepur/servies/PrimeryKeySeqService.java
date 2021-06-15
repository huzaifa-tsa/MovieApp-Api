package com.moviepur.servies;

import java.util.List;

import com.moviepur.entitys.PrimeryKeySeq;

public interface PrimeryKeySeqService {
	
	public int getCurrentPostion(String tableName);

	public List<PrimeryKeySeq> getAll();
	
	public String saveAll(List<PrimeryKeySeq> list);
}
