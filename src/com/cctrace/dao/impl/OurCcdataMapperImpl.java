package com.cctrace.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cctrace.dao.CcdataMapper;
import com.cctrace.dao.OurCcdataMapper;
import com.cctrace.entity.Ccdata;
import com.cctrace.entity.Container;
import com.cctrace.entity.DevAndTimeDiff;
import com.cctrace.entity.OurCcdata;

@Repository(value="ourccdataMapper")
public class OurCcdataMapperImpl implements OurCcdataMapper {

	@Resource
	private SqlSession sqlSession;


	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public OurCcdata selectOurCcdataById(Integer id) {
		OurCcdata ourccdata = sqlSession.selectOne("com.cctrace.dao.OurCcdataMapper.selectOurCcdataById",id);
		return ourccdata;
	}
	
	@Override
	public OurCcdata selectOurLastCcdataByCompanyIdAndContainerIdLikely(
			Map<String, Object> map) {
		OurCcdata ourccdata = sqlSession.selectOne("com.cctrace.dao.OurCcdataMapper.selectOurLastCcdataByCompanyIdAndContainerIdLikely", map);
		return ourccdata;
	}

	@Override
	public List<OurCcdata> selectOurCcdatasBycompanyId(Integer companyId) {
		List<OurCcdata> ourccdatas = sqlSession.selectList("com.cctrace.dao.OurCcdataMapper.selectOurCcdatasBycompanyId",companyId);
		return ourccdatas;
	}

	@Override
	public List<Map<String, Object>> selectOurMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceId(
			Integer companyId) {
		List<Map<String,Object>> maps = sqlSession.selectList("com.cctrace.dao.OurCcdataMapper.selectOurMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceId", companyId);
		return maps;
	}

	@Override
	public List<Map<String, Object>> selectOurMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceIdLikely(
			Map<String, Object> map) {
		List<Map<String,Object>> maps = sqlSession.selectList("com.cctrace.dao.OurCcdataMapper.selectOurMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceIdLikely", map);
		System.out.println("weer"+maps);
		return maps;
	}


	

	@Override
	public OurCcdata selectOurLastCcdataByGpsLongTimeAndDeviceMap(
			Map<String, Object> map) {
		OurCcdata ourccdata = sqlSession.selectOne("com.cctrace.dao.OurCcdataMapper.selectOurLastCcdataByGpsLongTimeAndDeviceMap",map);
		return ourccdata;
	}
	
	@Override
	public OurCcdata selectOurLastCcdataByGpsLongTimeAndDeviceMapLikely(
			Map<String, Object> maps) {
		OurCcdata ourccdata = sqlSession.selectOne("com.cctrace.dao.OurCcdataMapper.selectOurLastCcdataByGpsLongTimeAndDeviceMapLikely",maps);
		return ourccdata;
	}

	@Override
	public OurCcdata selectOurLastCcdataByContainerId(String containerId) {
		OurCcdata ourccdata = sqlSession.selectOne("com.cctrace.dao.OurCcdataMapper.selectLastOurCcdataByContainerId",containerId);
		return ourccdata;
	}
	
	@Override
	public List<OurCcdata> selectOurCcdatasByContainerIdBetweenTowTime(
			Map<String, Object> map) {
		List<OurCcdata> ourccdatas = sqlSession.selectList("com.cctrace.dao.OurCcdataMapper.selectOurCcdatasByContainerIdBetweenTowTime",map);
		return ourccdatas;
	}
	
	@Override
	public List<OurCcdata> selectOurCcdatasByContainerIdBetweenTowTimeASC(
			Map<String, Object> map) {
		List<OurCcdata> ourccdatas = sqlSession.selectList("com.cctrace.dao.OurCcdataMapper.selectOurCcdatasByContainerIdBetweenTowTimeASC",map);
		return ourccdatas;
	}



	@Override
	public List<OurCcdata> selectOurCcdatasByDeviceIdBetweenTwoTime(
			DevAndTimeDiff datf) {
		List<OurCcdata> ourccdatas = sqlSession.selectList("com.cctrace.dao.OurCcdataMapper.selectOurCcdatasByDeviceIdBetweenTwoTime",datf);
		return ourccdatas;
	}

	@Override
	public int deleteOurCcdataById(Integer id) {
		int delete = sqlSession.delete("com.cctrace.dao.OurCcdataMapper.deleteOurCcdataById",id);
		return delete;
	}

	@Override
	public int deleteOurCcdatasByContainerId(String containerId) {
		int delete = sqlSession.delete("com.cctrace.dao.OurCcdataMapper.deleteOurCcdatasByContainerId",containerId);
		return delete;
	}

	@Override
	public int insertOurCcdata(OurCcdata ourccdata) {
		int insert = sqlSession.insert("com.cctrace.dao.OurCcdataMapper.insertOurCcdata", ourccdata);
		return insert;
	}

	@Override
	public int updateOurCcdataById(OurCcdata ourccdata) {
		int update = sqlSession.update("com.cctrace.dao.OurCcdataMapper.updateOurCcdataById", ourccdata);
		return update;
	}

	@Override
	public OurCcdata getOurLastLocationBycontainerId(String containerId) {
		OurCcdata ourccdata = sqlSession.selectOne("com.cctrace.dao.OurCcdataMapper.getOurLastLocationBycontainerId", containerId);
		return ourccdata;
	}

	@Override
	public List<OurCcdata> selectOurRecordsBetweenInTwoTimeBycontainerId(Map map) {
		List<OurCcdata> records = sqlSession.selectList("com.cctrace.dao.OurCcdataMapper.selectOurRecordsBetweenInTwoTimeBycontainerId", map);
		return records;
	}

	@Override
	public List<OurCcdata> getOurAllLocationBycompanyId(Integer companyId) {
		List<OurCcdata> ourccdatas = sqlSession.selectList("com.cctrace.dao.OurCcdataMapper.getOurAllLocationBycompanyId", companyId);
		return ourccdatas;
	}

	@Override
	public List <OurCcdata> selectOurCcdatasLonAndLat(String containerId) {
		List<OurCcdata> ourccdata = sqlSession.selectList("com.cctrace.dao.OurCcdataMapper.selectOurCcdatasLonAndLat", containerId);
		return ourccdata;
	}


	
	@Override
	public OurCcdata selectOurCcdataBycontainerId(String containerId) {
		OurCcdata ourccdata = sqlSession.selectOne("com.cctrace.dao.OurCcdataMapper.selectOurCcdataBycontainerId", containerId);
		return ourccdata;
	}

	@Override
	public int updateOurCcdataBycontainerId(OurCcdata ourccdata) {
		int n = sqlSession.update("com.cctrace.dao.OurCcdataMapper.updateOurCcdataBycontainerId", ourccdata);
		return n;
	}

	@Override
	public OurCcdata selectOurHardWareBycontainerId(String containerId) {
		OurCcdata ourccdata = sqlSession.selectOne("com.cctrace.dao.OurCcdataMapper.selectOurHardWareBycontainerId", containerId);
		return ourccdata;
	}

	@Override
	public OurCcdata selectOurCcdataByDeviceId(String deviceId) {
		return sqlSession.selectOne("com.cctrace.dao.OurCcdataMapper.selectOurCcdataByDeviceId", deviceId);
	}






	
}
