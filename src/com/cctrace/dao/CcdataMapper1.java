package com.cctrace.dao;

import java.util.List;
import java.util.Map;

import com.cctrace.entity.Ccdata1;

public interface CcdataMapper1 {

	public int updateCcdataBycontainerId1(Ccdata1 cc);

	public int updateCcdataById1(Ccdata1 cc);

	public int insertCcdata1(Ccdata1 cc);

	public int deleteCcdatasByContainerId1(String containerId);

	public int deleteCcdataById1(String id);

	public Ccdata1 selectCcdataByDeviceId1(String deviceId);

	public Ccdata1 selectCcdataByContainerId1(String containerId);

	public Ccdata1 selectLastCcdataByContainerId1(String containerId);

	public List<Ccdata1> selectCcdatasBycompanyId1(Integer companyId);

	public List<Ccdata1> selectCcdatasBycompanyId1AndCIdASC(Integer companyId);

	public List<Ccdata1> selectCcdatasBycompanyId1AndCIdDESC(Integer companyId);

	public List<Ccdata1> selectCcdatasBycompanyId1AndDIdASC(Integer companyId);

	public List<Ccdata1> selectCcdatasBycompanyId1AndDIdDESC(Integer companyId);

	public List<Ccdata1> selectCcdatasBycompanyId1AndNowTimeASC(
			Integer companyId);

	public List<Ccdata1> selectCcdatasBycompanyId1AndNowTimeDESC(
			Integer companyId);

	public List<Ccdata1> selectCcdatasBycompanyId1AndSetTempDESC(
			Integer companyId);

	public List<Ccdata1> selectCcdatasBycompanyId1AndSetTempASC(
			Integer companyId);

	public List<Ccdata1> selectCcdatasBycompanyId1AndBackWindASC(
			Integer companyId);

	public List<Ccdata1> selectCcdatasBycompanyId1AndBackWindDESC(
			Integer companyId);

	public List<Ccdata1> selectCcdatasBycompanyId1AndRefBatVolASC(
			Integer companyId);

	public List<Ccdata1> selectCcdatasBycompanyId1AndRefBatVolDESC(
			Integer companyId);

	public Ccdata1 selectCcdataById1(Integer id);

	public List<Ccdata1> selectIndexContainerIdLikey(Map<String, Object> map);

	// 根据通讯时间倒序查询
	public List<Ccdata1> selectCcdataLcxModelDescBycompanyId1(Integer companyId);

	// 根据通讯时间正序查询
	public List<Ccdata1> selectCcdataLcxModelAscBycompanyId1(Integer companyId);

}
