package com.cctrace.dao;

import java.util.List;
import java.util.Map;

import com.cctrace.entity.Alert;
import com.cctrace.entity.Geomessage;

public interface GeomessageMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Geomessage record);

    int insertSelective(Geomessage record);

    Geomessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Geomessage record);

    int updateByPrimaryKey(Geomessage record);

	Integer getCountOfNoReadedGeomessageBeforeDays(Map<String, Object> map);
	
	List<Geomessage> getAlertsByCompanyIdInOneDays(Map<String, Object> map);
	/*
	List<Geomessage> selectShowGeomessageInTwoTime(Map<String, Object> map);
*/
	List<Geomessage> showGeomessagetsInTwoTimeAndContainerId(Map<String, Object> map);

	Integer modifyAlertReadStateByGeomessagetId(int alertId);

	Geomessage getGeoMessageById(int alertId);
}