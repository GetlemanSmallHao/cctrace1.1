package com.cctrace.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cctrace.dao.GeomessageMapper;
import com.cctrace.entity.Geomessage;
@Repository(value = "geomessageMapper")
public class GeomessageMapperImpl implements GeomessageMapper{

	@Resource
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Geomessage record) {
		return sqlSession.selectOne("com.cctrace.entity.Geomessage.insert",record);
	}

	@Override
	public int insertSelective(Geomessage record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Geomessage selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Geomessage record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Geomessage record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer getCountOfNoReadedGeomessageBeforeDays(Map<String, Object> map) {
		
		return sqlSession.selectOne("com.cctrace.entity.Geomessage.getCountOfNoReadedGeomessageBeforeDays",map);
	}
	@Override
	public List<Geomessage> getAlertsByCompanyIdInOneDays(Map<String, Object> map) {
		return sqlSession.selectList("com.cctrace.entity.Geomessage.getAlertsByCompanyIdInOneDays",map);
	}
	/*
	@Override
	public List<Geomessage> selectShowGeomessageInTwoTime(Map<String, Object> map) {
		return sqlSession.selectList("com.cctrace.entity.Geomessage.selectShowGeomessageInTwoTime",map);
	}
	*/
	@Override
	public List<Geomessage> showGeomessagetsInTwoTimeAndContainerId(Map<String, Object> map) {
		return sqlSession.selectList("com.cctrace.entity.Geomessage.showGeomessagetsInTwoTimeAndContainerId",map);
	}
	@Override
	public Integer modifyAlertReadStateByGeomessagetId(int alertId) {
		return sqlSession.update("com.cctrace.entity.Geomessage.modifyAlertReadStateByGeomessagetId",alertId);
	}
	@Override
	public Geomessage getGeoMessageById(int alertId) {
		return sqlSession.selectOne("com.cctrace.entity.Geomessage.getGeoMessageById", new Integer(alertId));
	}
}
