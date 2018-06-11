package com.cctrace.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cctrace.dao.AlertMapper;
import com.cctrace.entity.Alert;
import com.cctrace.entity.CommandStore;
import com.cctrace.entity.ErrorData;

@Repository(value = "alertMapper")
public class AlertMapperImpl implements AlertMapper {

	@Resource
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<Alert> selectAllAlerts() {
		List<Alert> alerts = sqlSession
				.selectList("com.cctrace.dao.AlertMapper.selectAllAlerts");
		return alerts;
	}

	@Override
	public Integer selectMaxAlertId() {
		Integer maxId = sqlSession
				.selectOne("com.cctrace.dao.AlertMapper.selectMaxAlertId");
		return maxId;
	}

	@Override
	public List<Alert> selectAlertsByCompanyId(Integer companyId) {
		List<Alert> alerts = sqlSession.selectList(
				"com.cctrace.dao.AlertMapper.selectAlertsByCompanyId",
				companyId);
		return alerts;
	}

	@Override
	public List<Alert> selectAlertsByCompanyIdAndDays(Map<String, Object> map) {
		List<Alert> alerts = sqlSession.selectList(
				"com.cctrace.dao.AlertMapper.selectAlertsByCompanyIdAndDays",
				map);
		return alerts;
	}

	@Override
	public Alert selectAlertById(Integer id) {
		Alert alert = sqlSession.selectOne(
				"com.cctrace.dao.AlertMapper.selectAlertById", id);
		return alert;
	}

	@Override
	public List<Alert> selectAlertsByContainderId(String containerId) {
		List<Alert> alerts = sqlSession.selectList(
				"com.cctrace.dao.AlertMapper.selectAlertsByContainderId",
				containerId);
		return alerts;
	}

	// 该方法虽然叫做获取四天内的告警信息，但是我们不是
	@Override
	public List<Alert> selectAlertsByCompanyIdInFourDays(Map<String, Object> map) {
		List<Alert> alerts = sqlSession
				.selectList(
						"com.cctrace.dao.AlertMapper.selectAlertsByCompanyIdInFourDays",
						map);
		return alerts;
	}

	@Override
	public List<Alert> selectAlertsByCompanyIdAndContainerIdLikely(
			Map<String, Object> map) {
		List<Alert> alerts = sqlSession
				.selectList(
						"com.cctrace.dao.AlertMapper.selectAlertsByCompanyIdAndContainerIdLikely",
						map);
		return alerts;
	}

	@Override
	public List<Alert> selectAlertsByCompanyIdAndAlertTypeLikely(
			Map<String, Object> map) {
		List<Alert> alerts = sqlSession
				.selectList(
						"com.cctrace.dao.AlertMapper.selectAlertsByCompanyIdAndAlertTypeLikely",
						map);
		return alerts;
	}

	@Override
	public List<Alert> selectAlertsByCompanyIdAndContainerIdAndAlertTypeLikely(
			Map<String, Object> map) {
		List<Alert> alerts = sqlSession
				.selectList(
						"com.cctrace.dao.AlertMapper.selectAlertsByCompanyIdAndContainerIdAndAlertTypeLikely",
						map);
		return alerts;
	}

	@Override
	public Integer selectMaxAlertIdByCompanyId(Integer companyId) {
		Integer maxAlertId = sqlSession.selectOne(
				"com.cctrace.dao.AlertMapper.selectMaxAlertIdByCompanyId",
				companyId);
		return maxAlertId;
	}

	@Override
	public Integer selectCountNoReadedAlertsByCompanyIdAndbeginTime(
			Map<String, Object> map) {
		Integer countNoReaded = sqlSession
				.selectOne(
						"com.cctrace.dao.AlertMapper.selectCountNoReadedAlertsByCompanyIdAndbeginTime",
						map);
		return countNoReaded;
	}

	@Override
	public Integer selectCountNoReadedLastAlertsByCompanyId(Integer companyId) {
		Integer countNoReaded = sqlSession
				.selectOne(
						"com.cctrace.dao.AlertMapper.selectCountNoReadedLastAlertsByCompanyId",
						companyId);
		return countNoReaded;
	}

	@Override
	public Integer selectCountNoReadedAlertsByCompanyId(Integer companyId) {
		Integer countNoReaded = sqlSession
				.selectOne(
						"com.cctrace.dao.AlertMapper.selectCountNoReadedAlertsByCompanyId",
						companyId);
		return countNoReaded;
	}

	@Override
	public int updateAlertReadStateToYesByAlertId(Integer alertId) {
		int update = sqlSession
				.update("com.cctrace.dao.AlertMapper.updateAlertReadStateToYesByAlertId",
						alertId);
		return update;
	}

	@Override
	public int deletAlertById(Integer id) {
		int delete = sqlSession.delete(
				"com.cctrace.dao.AlertMapper.deletAlertById", id);
		return delete;
	}

	@Override
	public int insertAlert(Alert alert) {
		int insert = sqlSession.insert(
				"com.cctrace.dao.AlertMapper.insertAlert", alert);
		return insert;
	}

	@Override
	public int updateAlertById(Alert alert) {
		int update = sqlSession.update(
				"com.cctrace.dao.AlertMapper.updateAlertById", alert);
		return update;
	}

	/**
	 * 新增
	 */
	@Override
	public List<Alert> selectIfAlertBytrainId(String containerId) {
		List<Alert> alerts = sqlSession.selectList(
				"com.cctrace.dao.AlertMapper.selectIfAlertBytrainId",
				containerId);
		return alerts;
	}

	@Override
	public int insertErrorData(ErrorData errorData) {
		return sqlSession.insert(
				"com.cctrace.dao.ErrorDataMapper.insertErrorData", errorData);
	}

	@Override
	public ErrorData selectErrorDataByDeviceId(String deviceId) {
		return sqlSession.selectOne(
				"com.cctrace.dao.ErrorDataMapper.selectErrorDataByDeviceId",
				deviceId);
	}

	@Override
	public List<Alert> selectContainersWithAlert() {
		List<Alert> alerts = sqlSession
				.selectList("com.cctrace.dao.AlertMapper.selectContainersWithAlert");
		return alerts;
	}

	@Override
	public List<Alert> selectContainersLikeyInAlert(String containerId) {
		List<Alert> alerts = sqlSession.selectList(
				"com.cctrace.dao.AlertMapper.selectContainersLikeyInAlert",
				containerId);
		return alerts;
	}

	@Override
	public List<Alert> selectLastAlertsTimeByCompanyId(Integer companyId) {
		List<Alert> alerts = sqlSession.selectList(
				"com.cctrace.dao.AlertMapper.selectLastAlertsTimeByCompanyId",
				companyId);
		return alerts;
	}

	@Override
	public List<Alert> selectNewAlertInTwoTime(Map<String, Object> map) {
		List<Alert> alerts = sqlSession.selectList(
				"com.cctrace.dao.AlertMapper.selectNewAlertInTwoTime", map);
		return alerts;
	}

	@Override
	public List<String> selectAlertWithContainer(Integer companyId) {
		List<String> list = sqlSession.selectList(
				"com.cctrace.dao.AlertMapper.selectAlertWithContainer",
				companyId);
		return list;
	}

	@Override
	public Integer selectCountNoReadedBeforeSomeDays(Map<String, Object> map) {
		Integer count = sqlSession
				.selectOne(
						"com.cctrace.dao.AlertMapper.selectCountNoReadedBeforeSomeDays",
						map);
		return count;
	}

	@Override
	public List<Alert> selectShowAlertInTwoTime(Map<String, Object> map) {
		List<Alert> alerts = sqlSession.selectList(
				"com.cctrace.dao.AlertMapper.selectShowAlertInTwoTime", map);
		return alerts;
	}

	@Override
	public Integer selectAlertInBeforeSeconds(Map<String, Object> map) {
		Integer count = sqlSession.selectOne(
				"com.cctrace.dao.AlertMapper.selectAlertInBeforeSeconds", map);
		return count;
	}

	@Override
	public List<Alert> selectAlertGroupInOneDay(Map<String, Object> map) {
		List<Alert> alerts = sqlSession.selectList(
				"com.cctrace.dao.AlertMapper.selectAlertGroupInOneDay", map);
		return alerts;
	}

	@Override
	public List<Alert> selectAlertAfterCommand(Map<String, Object> map) {
		List<Alert> alerts = sqlSession.selectList(
				"com.cctrace.dao.AlertMapper.selectAlertAfterCommand", map);
		return alerts;
	}

	/**
	 * 指令
	 * 
	 */
	@Override
	public CommandStore selectCommandStoreDesc(String containerId) {
		return sqlSession.selectOne(
				"com.cctrace.dao.CommandStoreMapper.selectCommandStoreDesc",
				containerId);
	}

	@Override
	public int insertCommandStore(CommandStore commandStore) {
		return sqlSession.insert(
				"com.cctrace.dao.CommandStoreMapper.insertCommandStore",
				commandStore);
	}

	@Override
	public int updateCommandStoreById(CommandStore commandStore) {
		return sqlSession.update(
				"com.cctrace.dao.CommandStoreMapper.updateCommandStoreById",
				commandStore);
	}

	@Override
	public CommandStore selectSetCommand(Map<String, Object> map) {
		CommandStore commandStore = sqlSession.selectOne(
				"com.cctrace.dao.CommandStoreMapper.selectSetTemp", map);
		return commandStore;
	}

	@Override
	public CommandStore selectCommandStoreSwitchDescOne(String containerId) {
		return sqlSession
				.selectOne(
						"com.cctrace.dao.CommandStoreMapper.selectCommandStoreSwitchDescOne",
						containerId);
	}

	@Override
	public CommandStore selectCommandStoreRunModeDescOne(String containerId) {
		return sqlSession
				.selectOne(
						"com.cctrace.dao.CommandStoreMapper.selectCommandStoreRunModeDescOne",
						containerId);
	}

	@Override
	public CommandStore selectCommandStoreCommandDescOne(Map<String, Object> map) {
		return sqlSession
				.selectOne(
						"com.cctrace.dao.CommandStoreMapper.selectCommandStoreCommandDescOne",
						map);
	}

	// 根据设备号、预警号和24小时内查询最新一条预警
	@Override
	public Alert selectAlertOnByContainerId(Map<String, Object> map) {
		return sqlSession.selectOne(
				"com.cctrace.dao.AlertMapper.selectAlertOnByContainerId", map);
	}

	// 根据设备号、预警号和24小时内查询所有预警
	@Override
	public List<Alert> selectAllAlertOnByContainerId(Map<String, Object> map) {
		return sqlSession.selectList(
				"com.cctrace.dao.AlertMapper.selectAllAlertOnByContainerId",
				map);
	}

	@Override
	public int updateAlertTimeById(Alert alertId) {
		return sqlSession.update(
				"com.cctrace.dao.AlertMapper.updateAlertTimeById", alertId);
	}

	@Override
	public List<Alert> selectAllAlertsByCompanyId(Map<String, Object> map) {
		return sqlSession.selectList(
				"com.cctrace.dao.AlertMapper.selectAllAlertsByCompanyId", map);
	}

	@Override
	public CommandStore selectCommandStoreByMap(Map<String, Object> map) {
		return sqlSession
				.selectOne(
						"com.cctrace.dao.CommandStoreMapper.selectCommandStoreByMap",
						map);
	}

}
