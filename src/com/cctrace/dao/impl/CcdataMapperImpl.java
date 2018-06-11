package com.cctrace.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cctrace.dao.CcdataMapper;
import com.cctrace.entity.Ccdata;
import com.cctrace.entity.Container;
import com.cctrace.entity.DevAndTimeDiff;

@Repository(value="ccdataMapper")
public class CcdataMapperImpl implements CcdataMapper {

	@Resource
	private SqlSession sqlSession;


	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public Ccdata selectCcdataById(Integer id) {
		Ccdata ccdata = sqlSession.selectOne("com.cctrace.dao.CcdataMapper.selectCcdataById",id);
		return ccdata;
	}
	
	@Override
	public Ccdata selectLastCcdataByCompanyIdAndContainerIdLikely(
			Map<String, Object> map) {
		Ccdata ccdata = sqlSession.selectOne("com.cctrace.dao.CcdataMapper.selectLastCcdataByCompanyIdAndContainerIdLikely", map);
		return ccdata;
	}

	@Override
	public List<Ccdata> selectCcdatasBycompanyId(Integer companyId) {
		List<Ccdata> ccdatas = sqlSession.selectList("com.cctrace.dao.CcdataMapper.selectCcdatasBycompanyId",companyId);
		return ccdatas;
	}

	@Override
	public List<Map<String, Object>> selectMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceId(
			Integer companyId) {
		List<Map<String,Object>> maps = sqlSession.selectList("com.cctrace.dao.CcdataMapper.selectMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceId", companyId);
		return maps;
	}

	@Override
	public List<Map<String, Object>> selectMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceIdLikely(
			Map<String, Object> map) {
		List<Map<String,Object>> maps = sqlSession.selectList("com.cctrace.dao.CcdataMapper.selectMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceIdLikely", map);
		System.out.println("weer"+maps);
		return maps;
	}


	

	@Override
	public Ccdata selectLastCcdataByGpsLongTimeAndDeviceMap(
			Map<String, Object> map) {
		Ccdata ccdata = sqlSession.selectOne("com.cctrace.dao.CcdataMapper.selectLastCcdataByGpsLongTimeAndDeviceMap",map);
		return ccdata;
	}
	
	@Override
	public Ccdata selectLastCcdataByGpsLongTimeAndDeviceMapLikely(
			Map<String, Object> maps) {
		Ccdata ccdata = sqlSession.selectOne("com.cctrace.dao.CcdataMapper.selectLastCcdataByGpsLongTimeAndDeviceMapLikely",maps);
		return ccdata;
	}

	@Override
	public Ccdata selectLastCcdataByContainerId(String containerId) {
		Ccdata ccdata = sqlSession.selectOne("com.cctrace.dao.CcdataMapper.selectLastCcdataByContainerId",containerId);
		return ccdata;
	}
	
	@Override
	public List<Ccdata> selectCcdatasByContainerIdBetweenTowTime(
			Map<String, Object> map) {
		List<Ccdata> ccdatas = sqlSession.selectList("com.cctrace.dao.CcdataMapper.selectCcdatasByContainerIdBetweenTowTime",map);
		return ccdatas;
	}
	
	@Override
	public List<Ccdata> selectCcdatasByContainerIdBetweenTowTimeASC(
			Map<String, Object> map) {
		List<Ccdata> ccdatas = sqlSession.selectList("com.cctrace.dao.CcdataMapper.selectCcdatasByContainerIdBetweenTowTimeASC",map);
		return ccdatas;
	}



	@Override
	public List<Ccdata> selectCcdatasByDeviceIdBetweenTwoTime(
			DevAndTimeDiff datf) {
		List<Ccdata> ccdatas = sqlSession.selectList("com.cctrace.dao.CcdataMapper.selectCcdatasByDeviceIdBetweenTwoTime",datf);
		return ccdatas;
	}

	@Override
	public int deleteCcdataById(Integer id) {
		int delete = sqlSession.delete("com.cctrace.dao.CcdataMapper.deleteCcdataById",id);
		return delete;
	}

	@Override
	public int deleteCcdatasByContainerId(String containerId) {
		int delete = sqlSession.delete("com.cctrace.dao.CcdataMapper.deleteCcdatasByContainerId",containerId);
		return delete;
	}

	@Override
	public int insertCcdata(Ccdata ccdata) {
		int insert = sqlSession.insert("com.cctrace.dao.CcdataMapper.insertCcdata", ccdata);
		return insert;
	}

	@Override
	public int updateCcdataById(Ccdata ccdata) {
		int update = sqlSession.update("com.cctrace.dao.CcdataMapper.updateCcdataById", ccdata);
		return update;
	}

	@Override
	public Ccdata getLastLocationBycontainerId(String containerId) {
		Ccdata ccdata = sqlSession.selectOne("com.cctrace.dao.CcdataMapper.getLastLocationBycontainerId", containerId);
		return ccdata;
	}
	
	
	/**
	 * pc端少的方法,加上
	 */
	@Override
	public List<Ccdata> getLastCcdatasByGpsLongTimeAndDeviceMap(
			List<Map<String, Object>> maps) {
		List<Ccdata> ccdatas = sqlSession.selectList("com.cctrace.dao.CcdataMapper.selectLastCcdataByGpsLongTimeAndDeviceMap", maps);
		return ccdatas;
	}

	@Override
	public List<Ccdata> selectRecordsBetweenInTwoTimeBycontainerId(Map map) {
		List<Ccdata> records = sqlSession.selectList("com.cctrace.dao.CcdataMapper.selectRecordsBetweenInTwoTimeBycontainerId", map);
		return records;
	}

	@Override
	public List<Ccdata> getAllLocationBycompanyId(Integer companyId) {
		List<Ccdata> ccdatas = sqlSession.selectList("com.cctrace.dao.CcdataMapper.getAllLocationBycompanyId", companyId);
		return ccdatas;
	}

	@Override
	public List<Ccdata> selectCcdatasLonAndLat(String containerId) {
		List<Ccdata> ccdatas = sqlSession.selectList("com.cctrace.dao.CcdataMapper.selectCcdatasLonAndLat", containerId);
		return ccdatas;
	}
	//这个方法暂时不知道在何处调用,调用必出错
	@Override
	public Ccdata selectCcdataBycontainerId(String containerId) {
		Ccdata ccdata = sqlSession.selectOne("com.cctrace.dao.CcdataMapper.selectCcdataBycontainerId", containerId);
		return ccdata;
	}

	@Override
	public int updateCcdataBycontainerId(Ccdata ccdata) {
		int n = sqlSession.update("com.cctrace.dao.CcdataMapper.updateCcdataBycontainerId", ccdata);
		return n;
	}

	@Override
	public Ccdata selectHardWareBycontainerId(String containerId) {
		Ccdata ccdata = sqlSession.selectOne("com.cctrace.dao.CcdataMapper.selectHardWareBycontainerId", containerId);
		return ccdata;
	}

	@Override
	public List<Ccdata> selectSensorLikeyByContainerId(String containerId) {
		List<Ccdata> ccdatas = sqlSession.selectList("com.cctrace.dao.CcdataMapper.selectSensorLikey", containerId);
		return ccdatas;
	}

	@Override
	public Ccdata selectCcdataByDeviceId(String deviceId) {
		return sqlSession.selectOne("com.cctrace.dao.CcdataMapper.selectCcdataByDeviceId", deviceId);
	}


	/**
	 * 主页展示优化版
	 * 
	 */
	@Override
	public List<Ccdata> selectMaxNowLongTimeIndex(Integer companyId) {
		List<Ccdata> ccdatas = sqlSession.selectList("com.cctrace.dao.CcdataMapper.selectMaxNowLongTimeIndex", companyId);
		return ccdatas;
	}
	


	
}
