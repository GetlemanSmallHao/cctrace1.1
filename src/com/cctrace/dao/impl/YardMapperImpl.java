package com.cctrace.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cctrace.dao.YardMapper;
import com.cctrace.entity.Yard;

@Repository(value = "yardMapper")
public class YardMapperImpl implements YardMapper {

	@Resource
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<Yard> selectAllYardsByCompanyId(Integer companyId) {
		List<Yard> yards = sqlSession.selectList(
				"com.cctrace.dao.YardMapper.selectAllYardsByCompanyId",
				companyId);
		return yards;
	}

	@Override
	public Yard selectYardByYardNameAndCompanyId(Map<String,Object> map) {
		Yard yard = sqlSession.selectOne("com.cctrace.dao.YardMapper.selectYardByYardNameAndCompanyId", map);
		return yard;
	}

	@Override
	public Yard selectYardById(Integer id) {
		Yard yard = sqlSession.selectOne("com.cctrace.dao.YardMapper.selectYardById",id);
		return yard;
	}

	@Override
	public Yard selectYardByYardName(String yardName) {
		Yard yard = sqlSession.selectOne(
				"com.cctrace.dao.YardMapper.selectYardByYardName", yardName);
		return yard;
	}

	@Override
	public int insertNewYard(Yard yard) {
		int insert = sqlSession.insert(
				"com.cctrace.dao.YardMapper.insertNewYard", yard);
		return insert;
	}

	@Override
	public int deleteYardById(Integer id) {
		int delete = sqlSession.delete(
				"com.cctrace.dao.YardMapper.deleteYardById", id);
		return delete;
	}

	@Override
	public int deleteYardByYardName(String yardName) {
		int delete = sqlSession.delete(
				"com.cctrace.dao.YardMapper.deleteYardByYardName", yardName);
		return delete;
	}
	
	@Override
	public int deleteYardByYardNameAndCompanyId(Yard yard) {
		int delete = sqlSession.delete("com.cctrace.dao.YardMapper.deleteYardBycompanyIdAndStationName", yard);
		return delete;
	}

	@Override
	public int updateYardById(Yard yard) {
		int update = sqlSession.update(
				"com.cctrace.dao.YardMapper.updateYardById", yard);
		return update;
	}

}
