package com.cctrace.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cctrace.dao.LogMapper;
import com.cctrace.entity.CommandStore;
import com.cctrace.entity.Log;

@Repository(value="logMapper")
public class LogMapperImpl implements LogMapper{

	@Resource
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<Log> selectAllLogsByOperator(String operator) {
		List<Log> logs = sqlSession.selectList("com.cctrace.dao.LogMapper.selectAllLogsByOperator", operator);
		return logs;
	}

	@Override
	public List<Log> selectLogsByOperatorBetweenTowTimes(Map<String, Object> map) {
		List<Log> logs = sqlSession.selectList("com.cctrace.dao.LogMapper.selectLogsWithOinfoByBetweenTowTimes", map);
		return logs;
	}

	@Override
	public Log selectLogById(Integer id) {
		Log log = sqlSession.selectOne("com.cctrace.dao.LogMapper.selectLogById",id);
		return log;
	}

	@Override
	public int deleteLogById(Integer id) {
		int delete = sqlSession.delete("com.cctrace.dao.LogMapper.deleteLogById",id);
		return delete;
	}

	@Override
	public int insertLog(Log log) {
		int insert = sqlSession.insert("com.cctrace.dao.LogMapper.insertLog",log);
		return insert;
	}

	@Override
	public int updateLogById(Log log) {
		int update = sqlSession.update("com.cctrace.dao.LogMapper.updateLogById",log);
		return update;
	}

	@Override
	public List<CommandStore> selectCommandForLogShowInTwoTimes(
			Map<String, Object> map) {
		List<CommandStore> commandStores = null;
		commandStores = sqlSession.selectList("com.cctrace.dao.CommandStoreMapper.selectCommandForLogShowInTwoTimes", map);
		return commandStores;
	}
	
	@Override
	public List<CommandStore> selectLogShowInTwoTimesWithContainerIdLike(
			Map<String, Object> map) {
		List<CommandStore> commandStores = null;
		commandStores = sqlSession.selectList("com.cctrace.dao.CommandStoreMapper.selectLogShowInTwoTimesWithContainerIdLike", map);
		return commandStores;
	}
	
}
