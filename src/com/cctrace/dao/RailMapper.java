package com.cctrace.dao;

import java.util.List;
import java.util.Map;

import com.cctrace.entity.Rail;

public interface RailMapper {
	
	List<Rail> selectAllRailsByCompanyId(Integer companyId);
	
	Rail selectRailByRailName(String railName);
	
	Rail selectRailByRailNameAndCompanyId(Map map);
	
	Rail selectRailByRailId(Integer id);
	
	int deleteRailByRailName(String railName);
	
	int deleteRailById(Integer id);
	
	int insertRail(Rail rail);
	
	int updateRailById(Rail rail);

	
	
}
