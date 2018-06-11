package com.cctrace.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cctrace.dao.CcdataMapper;
import com.cctrace.dao.QueryVoMapper;
import com.cctrace.entity.Ccdata;
import com.cctrace.entity.Container;
import com.cctrace.entity.DevAndTimeDiff;
import com.cctrace.entity.QueryVo;

@Repository(value="queryVoMapper")
public class QueryVoMapperImpl implements QueryVoMapper {

	@Resource
	private SqlSession sqlSession;


	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	
	@Override
	public QueryVo getInforBycontainerId(String containerId) {
		QueryVo queryVo = sqlSession.selectOne("com.cctrace.dao.QueryVoMapper.getInforBycontainerId", containerId);
		return queryVo;
	}


	@Override
	public List<QueryVo> showYardInformation(Integer companyId) {
		List<QueryVo> queryVos = sqlSession.selectList("com.cctrace.dao.QueryVoMapper.showYardInformation", companyId);
		
		return queryVos;
	}


	@Override
	public List<QueryVo> showStationInformation(Integer companyId) {
		List<QueryVo> queryVos = sqlSession.selectList("com.cctrace.dao.QueryVoMapper.showStationInformation", companyId);
		
		return queryVos;
	}


	@Override
	public List<QueryVo> showAllContainerByUsername(Integer companyId) {
		
		List<QueryVo> queryVos = sqlSession.selectList("com.cctrace.dao.QueryVoMapper.showAllContainerByUsername", companyId);
		
		return queryVos;
	}


	@Override
	public List<QueryVo> showAllBanLieByCompanyId(Integer companyId) {
		List<QueryVo> queryVos = sqlSession.selectList("com.cctrace.dao.QueryVoMapper.showAllBanLieByCompanyId", companyId);
		
		return queryVos;
	}


	@Override
	public QueryVo showBanLieInfo(String containerId) {
		QueryVo queryVo = sqlSession.selectOne("com.cctrace.dao.QueryVoMapper.showBanLieInfo", containerId);
		return queryVo;
	}


	@Override
	public QueryVo showContainerinforBycontainerId(String containerId) {
		QueryVo queryVo = sqlSession.selectOne("com.cctrace.dao.QueryVoMapper.showContainerinforBycontainerId", containerId);
		return queryVo;
	}


	@Override
	public QueryVo showBanLieInfoNoAlert(String containerId) {
		QueryVo queryVo = sqlSession.selectOne("com.cctrace.dao.QueryVoMapper.showBanLieInfoNoAlert", containerId);
		return queryVo;
	}


	@Override
	public List<QueryVo> showSensorBetweenTwoTimeInfo(Map<String, Object> map) {
		List<QueryVo> queryVos = sqlSession.selectList("com.cctrace.dao.QueryVoMapper.showSensorBetweenTwoTimeInfo", map);
		return queryVos;
	}


	@Override
	public QueryVo showSensorSetting(String containerId) {
		QueryVo queryVo = sqlSession.selectOne("com.cctrace.dao.QueryVoMapper.showSensorSetting", containerId);
		return queryVo;
	}


	@Override
	public QueryVo showMyInfo(String username) {
		QueryVo queryVo = sqlSession.selectOne("com.cctrace.dao.QueryVoMapper.showMyInfo", username);
		return queryVo;
	}
	
	
	
}
