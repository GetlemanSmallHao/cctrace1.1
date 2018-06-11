package com.cctrace.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cctrace.dao.OurCcdataMapper1;
import com.cctrace.entity.DevAndTimeDiff;
import com.cctrace.entity.OurCcdata1;

@Repository(value = "ourccdataMapper1")
public class OurCcdataMapperImpl1 implements OurCcdataMapper1 {

	@Resource
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public OurCcdata1 selectOurCcdataById(Integer id) {
		OurCcdata1 ourccdata = sqlSession.selectOne(
				"com.cctrace.dao.OurCcdataMapper1.selectOurCcdataById", id);
		return ourccdata;
	}

	@Override
	public OurCcdata1 selectOurLastCcdataByCompanyIdAndContainerIdLikely(
			Map<String, Object> map) {
		OurCcdata1 ourccdata = sqlSession
				.selectOne(
						"com.cctrace.dao.OurCcdataMapper1.selectOurLastCcdataByCompanyIdAndContainerIdLikely",
						map);
		return ourccdata;
	}

	@Override
	public List<OurCcdata1> selectOurCcdatasBycompanyId(Integer companyId) {
		List<OurCcdata1> ourccdatas = sqlSession.selectList(
				"com.cctrace.dao.OurCcdataMapper1.selectOurCcdatasBycompanyId",
				companyId);
		return ourccdatas;
	}

	@Override
	public List<Map<String, Object>> selectOurMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceId(
			Integer companyId) {
		List<Map<String, Object>> maps = sqlSession
				.selectList(
						"com.cctrace.dao.OurCcdataMapper1.selectOurMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceId",
						companyId);
		return maps;
	}

	@Override
	public List<Map<String, Object>> selectOurMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceIdLikely(
			Map<String, Object> map) {
		List<Map<String, Object>> maps = sqlSession
				.selectList(
						"com.cctrace.dao.OurCcdataMapper1.selectOurMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceIdLikely",
						map);
		System.out.println("weer" + maps);
		return maps;
	}

	@Override
	public OurCcdata1 selectOurLastCcdataByGpsLongTimeAndDeviceMap(
			Map<String, Object> map) {
		OurCcdata1 ourccdata = sqlSession
				.selectOne(
						"com.cctrace.dao.OurCcdataMapper1.selectOurLastCcdataByGpsLongTimeAndDeviceMap",
						map);
		return ourccdata;
	}

	@Override
	public OurCcdata1 selectOurLastCcdataByGpsLongTimeAndDeviceMapLikely(
			Map<String, Object> maps) {
		OurCcdata1 ourccdata = sqlSession
				.selectOne(
						"com.cctrace.dao.OurCcdataMapper1.selectOurLastCcdataByGpsLongTimeAndDeviceMapLikely",
						maps);
		return ourccdata;
	}

	@Override
	public OurCcdata1 selectOurLastCcdataByContainerId(String containerId) {
		OurCcdata1 ourccdata = sqlSession
				.selectOne(
						"com.cctrace.dao.OurCcdataMapper1.selectLastOurCcdataByContainerId",
						containerId);
		return ourccdata;
	}

	@Override
	public List<OurCcdata1> selectOurCcdatasByContainerIdBetweenTowTime(
			Map<String, Object> map) {
		List<OurCcdata1> ourccdatas = sqlSession
				.selectList(
						"com.cctrace.dao.OurCcdataMapper1.selectOurCcdatasByContainerIdBetweenTowTime",
						map);
		return ourccdatas;
	}

	@Override
	public List<OurCcdata1> selectOurCcdatasByDeviceIdBetweenTwoTime(
			DevAndTimeDiff datf) {
		List<OurCcdata1> ourccdatas = sqlSession
				.selectList(
						"com.cctrace.dao.OurCcdataMapper1.selectOurCcdatasByDeviceIdBetweenTwoTime",
						datf);
		return ourccdatas;
	}

	@Override
	public int deleteOurCcdataById(Integer id) {
		int delete = sqlSession.delete(
				"com.cctrace.dao.OurCcdataMapper1.deleteOurCcdataById", id);
		return delete;
	}

	@Override
	public int deleteOurCcdatasByContainerId(String containerId) {
		int delete = sqlSession
				.delete("com.cctrace.dao.OurCcdataMapper1.deleteOurCcdatasByContainerId",
						containerId);
		return delete;
	}

	@Override
	public int insertOurCcdata(OurCcdata1 ourccdata) {
		int insert = sqlSession.insert(
				"com.cctrace.dao.OurCcdataMapper1.insertOurCcdata", ourccdata);
		return insert;
	}

	@Override
	public int updateOurCcdataById(OurCcdata1 ourccdata) {
		int update = sqlSession.update(
				"com.cctrace.dao.OurCcdataMapper1.updateOurCcdataById",
				ourccdata);
		return update;
	}

	@Override
	public OurCcdata1 getOurLastLocationBycontainerId(String containerId) {
		OurCcdata1 ourccdata = sqlSession
				.selectOne(
						"com.cctrace.dao.OurCcdataMapper1.getOurLastLocationBycontainerId",
						containerId);
		return ourccdata;
	}

	@Override
	public List<OurCcdata1> selectOurRecordsBetweenInTwoTimeBycontainerId(
			Map map) {
		List<OurCcdata1> records = sqlSession
				.selectList(
						"com.cctrace.dao.OurCcdataMapper1.selectOurRecordsBetweenInTwoTimeBycontainerId",
						map);
		return records;
	}

	@Override
	public List<OurCcdata1> getOurAllLocationBycompanyId(Integer companyId) {
		List<OurCcdata1> ourccdatas = sqlSession
				.selectList(
						"com.cctrace.dao.OurCcdataMapper1.getOurAllLocationBycompanyId",
						companyId);
		return ourccdatas;
	}

	@Override
	public List<OurCcdata1> selectOurCcdatasLonAndLat(String containerId) {
		List<OurCcdata1> ourccdata = sqlSession.selectList(
				"com.cctrace.dao.OurCcdataMapper1.selectOurCcdatasLonAndLat",
				containerId);
		return ourccdata;
	}

	@Override
	public OurCcdata1 selectOurCcdataBycontainerId(String containerId) {
		OurCcdata1 ourccdata = sqlSession
				.selectOne(
						"com.cctrace.dao.OurCcdataMapper1.selectOurCcdataBycontainerId",
						containerId);
		return ourccdata;
	}

	@Override
	public int updateOurCcdataBycontainerId(OurCcdata1 ourccdata) {
		int n = sqlSession
				.update("com.cctrace.dao.OurCcdataMapper1.updateOurCcdataBycontainerId",
						ourccdata);
		return n;
	}

	@Override
	public OurCcdata1 selectOurHardWareBycontainerId(String containerId) {
		OurCcdata1 ourccdata = sqlSession
				.selectOne(
						"com.cctrace.dao.OurCcdataMapper1.selectOurHardWareBycontainerId",
						containerId);
		return ourccdata;
	}

	@Override
	public OurCcdata1 selectOurCcdataByDeviceId(String deviceId) {
		return sqlSession.selectOne(
				"com.cctrace.dao.OurCcdataMapper1.selectOurCcdataByDeviceId",
				deviceId);
	}

	@Override
	public List<OurCcdata1> selectOurCcdatas1BycompanyId1AndNowTimeASC(
			Integer companyId) {
		List<OurCcdata1> ourCcdata1s = sqlSession
				.selectList(
						"com.cctrace.dao.OurCcdataMapper1.selectOurCcdatas1BycompanyId1AndNowTimeASC",
						companyId);
		return ourCcdata1s;
	}

	@Override
	public List<OurCcdata1> selectOurCcdatas1BycompanyId1AndNowTimeDESC(
			Integer companyId) {
		List<OurCcdata1> ourCcdata1s = sqlSession
				.selectList(
						"com.cctrace.dao.OurCcdataMapper1.selectOurCcdatas1BycompanyId1AndNowTimeDESC",
						companyId);
		return ourCcdata1s;
	}

	@Override
	public List<OurCcdata1> selectOurCcdatas1BycompanyId1AndGPSPowerASC(
			Integer companyId) {
		List<OurCcdata1> ourCcdata1s = sqlSession
				.selectList(
						"com.cctrace.dao.OurCcdataMapper1.selectOurCcdatas1BycompanyId1AndGPSPowerASC",
						companyId);
		return ourCcdata1s;
	}

	@Override
	public List<OurCcdata1> selectOurCcdatas1BycompanyId1AndGPSPowerDESC(
			Integer companyId) {
		List<OurCcdata1> ourCcdata1s = sqlSession
				.selectList(
						"com.cctrace.dao.OurCcdataMapper1.selectOurCcdatas1BycompanyId1AndGPSPowerDESC",
						companyId);
		return ourCcdata1s;
	}

	/**
	 * 根据公司id降序查询地理位置
	 */
	@Override
	public List<OurCcdata1> selectOurCcdata1DescPositionByDeviceId(
			Integer companyId) {
		return sqlSession
				.selectList(
						"com.cctrace.dao.OurCcdataMapper1.selectOurCcdata1DescPositionByDeviceId",
						companyId);
	}

	/**
	 * 根据公司id升序查询地理位置
	 */
	@Override
	public List<OurCcdata1> selectOurCcdata1AscPositionByDeviceId(
			Integer companyId) {
		return sqlSession
				.selectList(
						"com.cctrace.dao.OurCcdataMapper1.selectOurCcdata1AscPositionByDeviceId",
						companyId);
	}

}
