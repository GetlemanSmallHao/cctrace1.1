package com.cctrace.dao;

import java.util.List;
import java.util.Map;

import com.cctrace.entity.TheNextStation;

public interface TheNextStationMapper {
	
	List<TheNextStation> selectAllTheNextStationsBycompanyId(Integer companyId);
	
	TheNextStation selectTheNextStationById(Integer id);
	
	TheNextStation selectTheNextStationBycompanyIdAndStationName(Map<String,Object> map);
	
	int insertTheNextStationBycompanyIdAndStationName(TheNextStation station);
	
	int updateTheNextStationById(TheNextStation station);
	
	int deleteTheNextStationBycompanyIdAndStationName(TheNextStation station);
	
}
