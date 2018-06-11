package com.cctrace.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cctrace.dao.RailMapper;
import com.cctrace.entity.Rail;

@Repository(value="railMapper")
public class RailMapperImpl implements RailMapper {

	@Resource
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<Rail> selectAllRailsByCompanyId(Integer companyId) {
		List<Rail> rails = sqlSession.selectList("com.cctrace.dao.RailMapper.selectAllRailsByCompanyId",companyId);
		return rails;
	}

	@Override
	public Rail selectRailByRailName(String railName) {
		Rail rail = sqlSession.selectOne("com.cctrace.dao.RailMapper.selectRailByRailName",railName);
		return rail;
	}
	
	@Override
	public Rail selectRailByRailNameAndCompanyId(Map map) {
		Rail rail = sqlSession.selectOne("com.cctrace.dao.RailMapper.selectRailByRailNameAndCompanyId",map);
		return rail;

	}
	
	@Override
	public Rail selectRailByRailId(Integer id) {
		Rail rail = sqlSession.selectOne("com.cctrace.dao.RailMapper.selectRailByRailId",id);
		return rail;
	}

	@Override
	public int deleteRailByRailName(String railName) {
		int delete = sqlSession.delete("com.cctrace.dao.RailMapper.deleteRailByRailName",railName);
		return delete;
	}

	@Override
	public int deleteRailById(Integer id) {
		int delete = sqlSession.delete("com.cctrace.dao.RailMapper.deleteRailById",id);
		return delete;
	}

	@Override
	public int insertRail(Rail rail) {
		int insert = sqlSession.insert("com.cctrace.dao.RailMapper.insertRail",rail);
		return insert;
	}

	@Override
	public int updateRailById(Rail rail) {
		int update = sqlSession.update("com.cctrace.dao.RailMapper.updateRailById",rail);
		return update;
	}


}
