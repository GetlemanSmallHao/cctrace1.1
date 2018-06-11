package com.cctrace.dao;

import java.util.List;
import java.util.Map;

import com.cctrace.entity.Alert;
import com.cctrace.entity.CommandStore;
import com.cctrace.entity.ErrorData;

public interface AlertMapper {

	List<Alert> selectAllAlerts();

	Integer selectMaxAlertId();

	List<Alert> selectAlertsByCompanyId(Integer companyId);

	List<Alert> selectAlertsByCompanyIdAndDays(Map<String, Object> map);

	Alert selectAlertById(Integer id);

	List<Alert> selectAlertsByContainderId(String containerId);

	List<Alert> selectAlertsByCompanyIdInFourDays(Map<String, Object> map);

	List<Alert> selectAlertsByCompanyIdAndContainerIdLikely(
			Map<String, Object> map);

	List<Alert> selectAlertsByCompanyIdAndAlertTypeLikely(
			Map<String, Object> map);

	List<Alert> selectAlertsByCompanyIdAndContainerIdAndAlertTypeLikely(
			Map<String, Object> map);

	List<Alert> selectLastAlertsTimeByCompanyId(Integer companyId);

	Integer selectMaxAlertIdByCompanyId(Integer companyId);

	Integer selectCountNoReadedAlertsByCompanyId(Integer companyId);

	Integer selectCountNoReadedAlertsByCompanyIdAndbeginTime(
			Map<String, Object> map);

	Integer selectCountNoReadedLastAlertsByCompanyId(Integer companyId);

	int updateAlertReadStateToYesByAlertId(Integer alertId);

	int deletAlertById(Integer id);

	int insertAlert(Alert alert);

	int updateAlertById(Alert alert);

	int updateAlertTimeById(Alert alertId);

	// 查看班列是否预警
	List<Alert> selectIfAlertBytrainId(String containerId);

	int insertErrorData(ErrorData errorData);

	public ErrorData selectErrorDataByDeviceId(String deviceId);

	List<Alert> selectContainersWithAlert();

	Integer selectCountNoReadedBeforeSomeDays(Map<String, Object> map);

	// 控制指令存储
	public CommandStore selectCommandStoreDesc(String containerId);

	// 查看最新一条开关机指令
	public CommandStore selectCommandStoreSwitchDescOne(String containerId);

	// 查看最新一条运行模式
	public CommandStore selectCommandStoreRunModeDescOne(String containerId);

	// 根据指令查询最新一条
	public CommandStore selectCommandStoreCommandDescOne(Map<String, Object> map);
	// 根据map查看最新一条运行模式   ***
	public CommandStore selectCommandStoreByMap(Map<String, Object> map);

	int insertCommandStore(CommandStore commandStore);

	int updateCommandStoreById(CommandStore commandStore);

	public List<Alert> selectContainersLikeyInAlert(String containerId);

	// 查询数据库中上次指令设定的值
	public CommandStore selectSetCommand(Map<String, Object> map);

	// 查询两端时间内的严重告警信息
	public List<Alert> selectNewAlertInTwoTime(Map<String, Object> map);

	// 查询两段时间内的信息
	public List<Alert> selectShowAlertInTwoTime(Map<String, Object> map);

	Integer selectAlertInBeforeSeconds(Map<String, Object> map);

	// 没有指令查一天
	List<Alert> selectAlertGroupInOneDay(Map<String, Object> map);

	// 有指令，查指令发出之后
	List<Alert> selectAlertAfterCommand(Map<String, Object> map);

	List<String> selectAlertWithContainer(Integer companyId);

	// 根据设备号、预警号和24小时内查询最新一条预警
	public Alert selectAlertOnByContainerId(Map<String, Object> map);

	// 根据设备号、预警号和24小时内查询所有预警
	public List<Alert> selectAllAlertOnByContainerId(Map<String, Object> map);

	// 一天内根据公司id查看未查看的预警的设备号
	public List<Alert> selectAllAlertsByCompanyId(Map<String, Object> map);

}
