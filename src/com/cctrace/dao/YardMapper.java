package com.cctrace.dao;

import java.util.List;
import java.util.Map;

import com.cctrace.entity.Yard;

public interface YardMapper {
	List<Yard> selectAllYardsByCompanyId(Integer companyId);
	
	Yard selectYardByYardNameAndCompanyId(Map<String,Object> map);
	
	Yard selectYardById(Integer id);
	
	Yard selectYardByYardName(String yardName);
	
	int insertNewYard(Yard yard);

	int deleteYardById(Integer id);
	
	int deleteYardByYardName(String yardName);
	
	int updateYardById(Yard yard);

	int deleteYardByYardNameAndCompanyId(Yard yard);


}
