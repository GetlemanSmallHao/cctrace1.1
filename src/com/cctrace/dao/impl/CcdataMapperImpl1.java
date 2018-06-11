package com.cctrace.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cctrace.dao.CcdataMapper1;
import com.cctrace.entity.Ccdata1;

@Repository(value = "ccdataMapper1")
public class CcdataMapperImpl1 implements CcdataMapper1 {

	@Resource
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public Ccdata1 selectCcdataById1(Integer id) {
		Ccdata1 ccdata = sqlSession.selectOne(
				"com.cctrace.dao.CcdataMapper1.selectCcdataById1", id);
		return ccdata;
	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1(Integer companyId) {

		List<Ccdata1> ccdata1s = sqlSession.selectList(
				"com.cctrace.dao.CcdataMapper1.selectCcdatasBycompanyId1",
				companyId);
		return ccdata1s;

	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndCIdDESC(Integer companyId) {

		List<Ccdata1> ccdata1s = sqlSession
				.selectList(
						"com.cctrace.dao.CcdataMapper1.selectCcdatasBycompanyId1AndCIdDESC",
						companyId);
		return ccdata1s;

	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndCIdASC(Integer companyId) {

		List<Ccdata1> ccdata1s = sqlSession
				.selectList(
						"com.cctrace.dao.CcdataMapper1.selectCcdatasBycompanyId1AndCIdASC",
						companyId);
		return ccdata1s;

	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndDIdDESC(Integer companyId) {

		List<Ccdata1> ccdata1s = sqlSession
				.selectList(
						"com.cctrace.dao.CcdataMapper1.selectCcdatasBycompanyId1AndDIdDESC",
						companyId);
		return ccdata1s;

	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndDIdASC(Integer companyId) {

		List<Ccdata1> ccdata1s = sqlSession
				.selectList(
						"com.cctrace.dao.CcdataMapper1.selectCcdatasBycompanyId1AndDIdASC",
						companyId);
		return ccdata1s;

	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndNowTimeDESC(
			Integer companyId) {

		List<Ccdata1> ccdata1s = sqlSession
				.selectList(
						"com.cctrace.dao.CcdataMapper1.selectCcdatasBycompanyId1AndNowTimeDESC",
						companyId);
		return ccdata1s;

	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndNowTimeASC(
			Integer companyId) {

		List<Ccdata1> ccdata1s = sqlSession
				.selectList(
						"com.cctrace.dao.CcdataMapper1.selectCcdatasBycompanyId1AndNowTimeASC",
						companyId);
		return ccdata1s;

	}

	@Override
	public int deleteCcdatasByContainerId1(String containerId) {
		int delete = sqlSession.delete(
				"com.cctrace.dao.CcdataMapper1.deleteCcdatasByContainerId1",
				containerId);
		return delete;
	}

	@Override
	public int insertCcdata1(Ccdata1 ccdata) {
		int insert = sqlSession.insert(
				"com.cctrace.dao.CcdataMapper1.insertCcdata1", ccdata);
		return insert;
	}

	@Override
	public int updateCcdataById1(Ccdata1 ccdata) {
		int update = sqlSession.update(
				"com.cctrace.dao.CcdataMapper1.updateCcdataById1", ccdata);
		return update;
	}

	@Override
	public int updateCcdataBycontainerId1(Ccdata1 ccdata) {
		int n = sqlSession.update(
				"com.cctrace.dao.CcdataMapper1.updateCcdataBycontainerId1",
				ccdata);
		return n;
	}

	@Override
	public Ccdata1 selectCcdataByDeviceId1(String deviceId) {
		return sqlSession.selectOne(
				"com.cctrace.dao.CcdataMapper1.selectCcdataByDeviceId1",
				deviceId);
	}

	@Override
	public int deleteCcdataById1(String id) {
		return sqlSession.delete(
				"com.cctrace.dao.CcdataMapper1.selectCcdataByDeviceId1", id);
	}

	@Override
	public Ccdata1 selectLastCcdataByContainerId1(String containerId) {
		return sqlSession.selectOne(
				"com.cctrace.dao.CcdataMapper1.selectLastCcdataByContainerId1",
				containerId);
	}

	@Override
	public Ccdata1 selectCcdataByContainerId1(String containerId) {
		Ccdata1 ccdata1 = sqlSession.selectOne(
				"com.cctrace.dao.CcdataMapper1.selectCcdataByContainerId1",
				containerId);
		return ccdata1;
	}

	@Override
	public List<Ccdata1> selectIndexContainerIdLikey(Map<String, Object> map) {
		List<Ccdata1> ccdata1s = sqlSession.selectList(
				"com.cctrace.dao.CcdataMapper1.selectIndexContainerIdLikey",
				map);
		return ccdata1s;
	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndSetTempDESC(
			Integer companyId) {
		List<Ccdata1> ccdata1s = sqlSession
				.selectList(
						"com.cctrace.dao.CcdataMapper1.selectCcdatasBycompanyId1AndSetTempDESC",
						companyId);
		return ccdata1s;
	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndSetTempASC(
			Integer companyId) {
		List<Ccdata1> ccdata1s = sqlSession
				.selectList(
						"com.cctrace.dao.CcdataMapper1.selectCcdatasBycompanyId1AndSetTempASC",
						companyId);
		return ccdata1s;
	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndBackWindASC(
			Integer companyId) {
		List<Ccdata1> ccdata1s = sqlSession
				.selectList(
						"com.cctrace.dao.CcdataMapper1.selectCcdatasBycompanyId1AndBackWindASC",
						companyId);
		return ccdata1s;
	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndBackWindDESC(
			Integer companyId) {
		List<Ccdata1> ccdata1s = sqlSession
				.selectList(
						"com.cctrace.dao.CcdataMapper1.selectCcdatasBycompanyId1AndBackWindDESC",
						companyId);
		return ccdata1s;
	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndRefBatVolASC(
			Integer companyId) {
		List<Ccdata1> ccdata1s = sqlSession
				.selectList(
						"com.cctrace.dao.CcdataMapper1.selectCcdatasBycompanyId1AndRefBatVolASC",
						companyId);
		return ccdata1s;
	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndRefBatVolDESC(
			Integer companyId) {
		List<Ccdata1> ccdata1s = sqlSession
				.selectList(
						"com.cctrace.dao.CcdataMapper1.selectCcdatasBycompanyId1AndRefBatVolDESC",
						companyId);
		return ccdata1s;
	}

	@Override
	public List<Ccdata1> selectCcdataLcxModelDescBycompanyId1(Integer companyId) {
		List<Ccdata1> ccdata1s = sqlSession
				.selectList(
						"com.cctrace.dao.CcdataMapper1.selectCcdataLcxModelDescBycompanyId1",
						companyId);
		return ccdata1s;
	}

	@Override
	public List<Ccdata1> selectCcdataLcxModelAscBycompanyId1(Integer companyId) {
		List<Ccdata1> ccdata1s = sqlSession
				.selectList(
						"com.cctrace.dao.CcdataMapper1.selectCcdataLcxModelAscBycompanyId1",
						companyId);
		return ccdata1s;
	}

}
