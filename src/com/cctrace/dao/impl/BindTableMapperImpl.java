package com.cctrace.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cctrace.dao.BindTableMapper;
import com.cctrace.entity.BindTable;

@Repository(value = "bindTableMapper")
public class BindTableMapperImpl implements BindTableMapper {

	@Resource
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<BindTable> selectAllBindTables() {
		List<BindTable> bindTables = sqlSession.selectList("com.cctrace.dao.BindTableMapper.selectAllBindTables");
		return bindTables;
	}

	@Override
	public List<BindTable> selectBindTablesByCompanyId(Integer companyId) {
		List<BindTable> bindTables = sqlSession.selectList("com.cctrace.dao.BindTableMapper.selectBindTablesByCompanyId", companyId);
		return bindTables;
	}

	@Override
	public BindTable selectBindTableById(Integer id) {
		BindTable bindTable = sqlSession.selectOne("com.cctrace.dao.BindTableMapper.selectBindTableById",id);
		return bindTable;
	}

	@Override
	public BindTable selectBindTableByContainerId(String containerId) {
		BindTable bindTable = sqlSession.selectOne("com.cctrace.dao.BindTableMapper.selectBindTableByContainerId",containerId);
		return bindTable;
	}

	@Override
	public int deleteBindTableById(Integer id) {
		int delete = sqlSession.delete("com.cctrace.dao.BindTableMapper.deleteBindTableById",id);
		return delete;
	}

	@Override
	public int deleteBindTableByContainerId(String containerId) {
		int delete = sqlSession.delete("com.cctrace.dao.BindTableMapper.deleteBindTableByContainerId",containerId);	
		return delete;
	}

	@Override
	public int insertBindTable(BindTable bindTable) {
		int insert = sqlSession.insert("com.cctrace.dao.BindTableMapper.insertBindTable",bindTable);
		return insert;
	}

	@Override
	public int updateBindTableById(BindTable bindTable) {
		int update = sqlSession.update("com.cctrace.dao.BindTableMapper.updateBindTableById",bindTable);
		return update;
	}

	@Override
	public int updateBindTableByYardId(Integer yardId) {
		int update = sqlSession.update("com.cctrace.dao.BindTableMapper.updateBindTableByYardId", yardId);
		return update;
	}

	@Override
	public int updateBindTableByTheNextStation(Integer theNextStationId) {
		int update = sqlSession.update("com.cctrace.dao.BindTableMapper.updateBindTableByTheNextStation", theNextStationId);
		return update;
	}

	@Override
	public List<BindTable> selectBindTableByYardId(Integer yardId) {
		List<BindTable> bindTables = sqlSession.selectList("com.cctrace.dao.BindTableMapper.selectBindTableByYardId", yardId);
		return bindTables;
	}

	@Override
	public List<BindTable> selectBindTableByTheNextStation(
			Integer theNextStationId) {
		List<BindTable> bindTables = sqlSession.selectList("com.cctrace.dao.BindTableMapper.selectBindTableByTheNextStation", theNextStationId);
		return bindTables;
	}

	@Override
	public List<BindTable> selectContainersLikey(String containerId) {
		List<BindTable> bindTables = sqlSession.selectList("com.cctrace.dao.BindTableMapper.selectContainersLikey", containerId);
		return bindTables;
	}

	@Override
	public List<BindTable> selectBindTableByCompanyAndRemarkDESC(
			Integer companyId) {
		List<BindTable> bindTables = sqlSession.selectList("com.cctrace.dao.BindTableMapper.selectBindTableByCompanyAndRemarkDESC", companyId);
		return bindTables;
	}

	@Override
	public List<BindTable> selectBindTableByCompanyAndRemarkASC(
			Integer companyId) {
		List<BindTable> bindTables = sqlSession.selectList("com.cctrace.dao.BindTableMapper.selectBindTableByCompanyAndRemarkASC", companyId);
		return bindTables;
	}
	

}
