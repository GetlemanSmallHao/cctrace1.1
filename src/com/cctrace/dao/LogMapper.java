package com.cctrace.dao;

import java.util.List;
import java.util.Map;

import com.cctrace.entity.CommandStore;
import com.cctrace.entity.Log;

public interface LogMapper {

	List<Log> selectAllLogsByOperator(String operator);
	
	List<Log> selectLogsByOperatorBetweenTowTimes(Map<String,Object> map);

	Log selectLogById(Integer id);

	int deleteLogById(Integer id);

	int insertLog(Log log);

	int updateLogById(Log log);
	
	/**
	 * 暂时没有启用aop
	 * 查询的是指令表中的command_Store
	 * 
	 */
	List<CommandStore> selectCommandForLogShowInTwoTimes(Map<String, Object> map);
	List<CommandStore> selectLogShowInTwoTimesWithContainerIdLike(Map<String, Object> map);
}
