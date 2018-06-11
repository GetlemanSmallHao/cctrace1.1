package com.cctrace.dao;

import java.util.List;

import com.cctrace.entity.BindTable;

public interface BindTableMapper {
	
	List<BindTable> selectAllBindTables();
	
	List<BindTable> selectBindTablesByCompanyId(Integer companyId);
	
	BindTable selectBindTableById(Integer id);
	
	BindTable selectBindTableByContainerId(String containerId);
	
	int deleteBindTableById(Integer id);
	
	int deleteBindTableByContainerId(String containerId);
	
	int insertBindTable(BindTable bindTable);
	
 	int updateBindTableById(BindTable bindTable);
 	
 	int updateBindTableByYardId(Integer yardId);
 	
 	int updateBindTableByTheNextStation(Integer theNextStationId);
 	
	List<BindTable> selectBindTableByYardId(Integer yardId);
 	
	List<BindTable> selectBindTableByTheNextStation(Integer theNextStationId);
	
	//模糊查询
	List<BindTable> selectContainersLikey(String containerId);
	
	List<BindTable> selectBindTableByCompanyAndRemarkDESC(Integer companyId);
	
	List<BindTable> selectBindTableByCompanyAndRemarkASC(Integer companyId);
	
	
}
