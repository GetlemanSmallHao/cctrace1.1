package com.cctrace.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cctrace.dao.TheNextStationMapper;
import com.cctrace.entity.TheNextStation;

@Repository(value="theNextStationMapper")
public class TheNextStationMapperImpl implements TheNextStationMapper{

	@Resource
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<TheNextStation> selectAllTheNextStationsBycompanyId(
			Integer companyId) {
		List<TheNextStation> stations = sqlSession.selectList("com.cctrace.dao.TheNextStationMapper.selectAllTheNextStationsBycompanyId", companyId);
		return stations;
	}
	
	@Override
	public TheNextStation selectTheNextStationById(Integer id) {
		TheNextStation theNextStation  = sqlSession.selectOne("com.cctrace.dao.TheNextStationMapper.selectTheNextStationById",id);
		return theNextStation;
	}

	@Override
	public TheNextStation selectTheNextStationBycompanyIdAndStationName(
			Map<String, Object> map) {
		TheNextStation theNextStation = sqlSession.selectOne("com.cctrace.dao.TheNextStationMapper.selectTheNextStationBycompanyIdAndStationName", map);
		return theNextStation;
	}

	@Override
	public int insertTheNextStationBycompanyIdAndStationName(
			TheNextStation station) {
		int insert = sqlSession.insert("com.cctrace.dao.TheNextStationMapper.insertTheNextStationBycompanyIdAndStationName", station);
		return insert;
	}

	@Override
	public int updateTheNextStationById(TheNextStation station) {
		int update = sqlSession.update("com.cctrace.dao.TheNextStationMapper.updateTheNextStationById",station);
		return update;
	}

	@Override
	public int deleteTheNextStationBycompanyIdAndStationName(
			TheNextStation station) {
		int delete = sqlSession.delete("com.cctrace.dao.TheNextStationMapper.deleteTheNextStationBycompanyIdAndStationName", station);
		return delete;
	}
	
}
