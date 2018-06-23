package com.cctrace.service;

import java.util.List;
import java.util.Map;

import com.cctrace.entity.Alert;
import com.cctrace.entity.BindTable;
import com.cctrace.entity.Ccdata;
import com.cctrace.entity.Ccdata1;
import com.cctrace.entity.CommandStore;
import com.cctrace.entity.Company;
import com.cctrace.entity.Container;
import com.cctrace.entity.ErrorData;
import com.cctrace.entity.Geomessage;
import com.cctrace.entity.Log;
import com.cctrace.entity.LwAlarm;
import com.cctrace.entity.OurCcdata;
import com.cctrace.entity.OurCcdata1;
import com.cctrace.entity.QueryVo;
import com.cctrace.entity.Rail;
import com.cctrace.entity.TheNextStation;
import com.cctrace.entity.Transformation;
import com.cctrace.entity.Transformationnew;
import com.cctrace.entity.User;
import com.cctrace.entity.Yard;

public interface DaoService {

	/**
	 * Company表相关的服务
	 * 
	 * @param id
	 * @return
	 */
	int registNewCompany(Company company);

	Company getCompanyById(Integer id);

	Company getCompanyByCompanyName(String companyName);

	int modifyCompanyInfoById(Company company);

	int insertErrorData(ErrorData errorData);

	public ErrorData selectErrorDataByDeviceId(String deviceId);

	/**
	 * Rail表相关的服务
	 */
	List<Rail> getRailsByCompanyId(Integer companyId);

	Rail getRailById(Integer id);

	Rail getRailByRailName(String railName);

	Rail getRailByRailNameAndCompanyId(Map map);

	int removeRailByRailName(String railName);

	int modifyRailByRailId(Rail rail);

	int addNewRail(Rail rail);

	/**
	 * User表相关的服务
	 */
	int registNewUser(User user);

	List<User> getUsersByCompanyId(Integer companyId);

	List<User> getUsersByCompanyName(String companyName);

	User getUserByUsernameAndPassword(Map<String, String> map);

	User getUserByUsername(String username);

	User getUserByPhone(String phone);

	int modifyUserInfoById(User user);

	public List<User> selectUserslikeyInCompany(Map<String, Object> map);

	int removeUserById(int id);

	public List<User> selectLimitUser(Map<String, Object> map);

	public List<User> selectUsersLikey(String username);

	public List<User> showLimitUserLikey(Map<String, Object> map);

	/**
	 * Ccdata表相关的服务
	 */
	List<Map<String, Object>> getMaxGpsLongTimeAndDeviceIdByCompanyId(
			Integer companyId);

	int removeCcdatasByContainerId(String containerId);

	public Ccdata getLastCcdataByGpsLongTimeAndDeviceMap(Map map);

	public Ccdata getLastCcdataByContainerId(String containerId);

	public List<Ccdata> getLastCcdatasByGpsLongTimeAndDeviceMap(
			List<Map<String, Object>> maps);

	public List<Ccdata> getLastCcdatasByGpsLongTimeAndDeviceMapLikely(
			Map<String, Object> maps);

	public Ccdata getLastLocationBycontainerId(String containerId);

	public List<Ccdata> selectRecordsBetweenInTwoTimeBycontainerId(Map map);

	public List<Ccdata> selectCcdatasByContainerIdBetweenTowTimeASC(
			Map<String, Object> map);

	public List<Ccdata> getAllLocationBycompanyId(Integer companyId);

	// 查询看对应的设备号 的经纬度是否存在
	public List<Ccdata> selectCcdatasLonAndLat(String containerId);

	// 根据companyId的值获取对应的最新的位置记录
	public List<Ccdata> getLastCcdatasByGpsLongTimeAndDeviceMap(
			Integer companyId);

	// 根据冷藏箱设备号查询
	public Ccdata selectCcdataByDeviceId(String deviceId);

	public List<Ccdata> getCcdatasByContainerIdBetweenTowTime(
			Map<String, Object> map);

	int addOneNewCcdata(Ccdata ccdata);

	int insertCcdata(Ccdata ccdata);

	public int updateCcdataById(Ccdata ccdata);

	public Ccdata selectCcdataBycontainerId(String containerId);

	public int updateCcdataBycontainerId(Ccdata ccdata);

	public Ccdata selectHardWareBycontainerId(String containerId);

	public List<Ccdata> selectSensorLikeyByContainerId(String containerId);

	public List<Ccdata> selectMaxNowLongTimeIndex(Integer companyId);

	/**
	 * Ccdata1表相关的服务
	 */

	public int updateCcdataBycontainerId1(Ccdata1 cc);

	public int updateCcdataById1(Ccdata1 cc);

	public int insertCcdata1(Ccdata1 cc);

	public int deleteCcdatasByContainerId1(String containerId);

	public int deleteCcdataById1(String id);

	public Ccdata1 selectCcdataByDeviceId1(String deviceId);

	public Ccdata1 selectLastCcdataByContainerId1(String containerId);

	public List<Ccdata1> selectCcdatasBycompanyId1(Integer companyId);

	public Ccdata1 selectCcdataById1(Integer id);

	public Ccdata1 selectCcdataByContainerId1(String containerId);

	public List<Ccdata1> selectIndexContainerIdLikey(Map<String, Object> map);

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

	/**
	 * Container表相关的服务
	 */
	int registNewContainer(Container container);

	public List<Container> selectContainersLikeyInPCContainerWithCompany(
			Map<String, Object> map);

	int modifyContainerById(Container container);

	int removeContainerById(int id);

	int removeContainerByContainerId(String containerId);

	String getContainerIdByDeviceId(String deviceId);

	Container getContainerBycontarinId(String containerId);

	Container getContainerByDeviceId(String deviceId);

	List<Container> getContainersByCompanyIdAndContainerIdLikely(
			Map<String, Object> map);

	List<Container> getContainersByCompanyId(Integer companyId);

	List<Container> getContainersNoBindedByCompanyId(Integer companyId);

	int updateContainerByContainerId(Container container);

	public List<Container> selectContainersLikeyInHard(String containerId);

	public List<Container> selectContainersLikeyInPCContainer(String containerId);

	Container selectContainerByDeviceId(String deviceId);

	/**
	 * BindTable表相关的服务
	 */
	Integer registNewBindTable(BindTable bindTable);

	int modifyBindTableById(BindTable bindTable);

	int removeBindTableByContainerId(String containerId);

	List<BindTable> getAllBindTables();

	List<BindTable> getBindTablesByCompanyId(Integer companyId);

	BindTable getBindTableByContainerId(String containerId);

	int updateBindTableByYardId(Integer yardId);

	int updateBindTableByTheNextStation(Integer theNextStationId);

	List<BindTable> selectBindTableByYardId(Integer yardId);

	List<BindTable> selectBindTableByTheNextStation(Integer theNextStationId);

	public List<BindTable> selectContainersLikey(String containerId);

	public List<BindTable> selectBindTableByCompanyAndRemarkDESC(
			Integer companyId);

	public List<BindTable> selectBindTableByCompanyAndRemarkASC(
			Integer companyId);

	/**
	 * Log表相关的服务
	 */
	int logOneNewLog(Log log);

	List<Log> getLogsByOperator(String operator);

	List<Log> getLogsByOperatorBetweenTwoTime(Map<String, Object> map);

	int deleteLogById(Integer id);

	int modifyLogById(Log log);

	// 暂时用查询代替
	public List<CommandStore> selectCommandForLogShowInTwoTimes(
			Map<String, Object> map);

	// 暂时用查询代替
	public List<CommandStore> selectLogShowInTwoTimesWithContainerIdLike(
			Map<String, Object> map);

	/**
	 * Alert表相关的服务
	 */
	List<Alert> selectAllAlerts();

	Integer getMaxAlertId();

	Alert getAlertById(Integer id);

	List<Alert> selectAltersByCompanyId(Integer companyId);

	List<Alert> getAlertsByCompanyIdAndDays(Map<String, Object> map);

	int addOneNewAlert(Alert alert);

	List<Alert> getAlertsByCompanyIdInFourDays(Map<String, Object> map);

	List<Alert> getAlertsByCompanyIdAndContainerIdLikely(Map<String, Object> map);

	List<Alert> getAlertsByCompanyIdAndAlertTypeLikely(Map<String, Object> map);

	List<Alert> getAlertsByCompanyIdAndContainerIdAndAlertTypeLikely(
			Map<String, Object> map);

	Integer modifyAlertReadStateToYesByAlertId(Integer alertId);

	Integer getCountOfNoReadedAlertsByCompanyIdAndbeginTime(
			Map<String, Object> map);

	Integer getCountOfNoReadedAlertsByCompanyId(Integer companyId);

	Integer getMaxAlertIdByCompanyId(Integer companyId);

	int removeAlertById(Integer id);

	int modifyAlertById(Alert alert);

	public List<Alert> selectContainersWithAlert();

	public Integer selectCountNoReadedLastAlertsByCompanyId(Integer companyId);

	public Integer selectCountNoReadedBeforeSomeDays(Map<String, Object> map);

	// 查看班列是否预警
	List<Alert> selectIfAlertBytrainId(String containerId);

	List<Alert> selectAlertsByContainderId(String containerId);

	int insertAlert(Alert alert);

	public List<Alert> selectContainersLikeyInAlert(String containerId);

	List<Alert> selectLastAlertsTimeByCompanyId(Integer companyId);

	public List<Alert> selectNewAlertInTwoTime(Map<String, Object> map);

	public List<Alert> selectShowAlertInTwoTime(Map<String, Object> map);
	/**
	 * 新增模块
	 * @param map
	 * @return
	 */
	public List<Alert> selectShowReaded(Map<String, Object> map);
	
	public List<Alert> selectShowBuMenM(Map<String, Object> map);
	
	public List<Alert> selectShowAlarmNum(Map<String, Object> map);

	public Integer selectAlertInBeforeSeconds(Map<String, Object> map);

	// 没有指令查一天

	public List<Alert> selectAlertGroupInOneDay(Map<String, Object> map);

	// 有指令，查指令发出之后
	public List<Alert> selectAlertAfterCommand(Map<String, Object> map);

	public List<String> selectAlertWithContainer(Integer companyId);

	/**
	 * Yard表相关的服务
	 */
	int registNewYard(Yard yard);

	List<Yard> getAllYarsByCompanyId(Integer companyId);

	Yard getYardByYardNameAndCompanyId(Map<String, Object> map);

	Yard getYardByYardName(String yardName);

	Yard getYardbyId(Integer yardId);

	int modifyYardById(Yard yard);

	int remvoeYardById(Integer id);

	int removeYardByYardNameAndCompanyId(Yard yard);

	/**
	 * TheNextSta tion表相关的服务
	 */
	int registNewTheNextStation(TheNextStation theNextStation);

	List<TheNextStation> getAllTheNextStationsByCompanyId(Integer companyId);

	TheNextStation getTheNextStationById(Integer id);

	TheNextStation getTheNextStationBycompanyIdAndStationName(
			Map<String, Object> map);

	int modifyTheNextStationById(TheNextStation theNextStation);

	int removeTheNextStationByCompanyIdAndStationName(TheNextStation station);

	// 新写的包装类，为了展示多张表联合查询的结果
	public QueryVo getInforBycontainerId(String containerId);

	// 展示堆场信息
	public List<QueryVo> showYardInformation(Integer companyId);

	// 显示下货站信息
	public List<QueryVo> showStationInformation(Integer companyId);

	// 显示冷藏箱信息
	public List<QueryVo> showAllContainerByUsername(Integer companyId);

	// 显示班列信息
	public List<QueryVo> showAllBanLieByCompanyId(Integer companyId);

	// 班列详情
	public QueryVo showBanLieInfo(String containerId);

	// 冷藏箱详情
	public QueryVo showContainerinforBycontainerId(String containerId);

	// 没有预警信息班列详情
	public QueryVo showBanLieInfoNoAlert(String containerId);

	// 按时间展示的传感器详情
	public List<QueryVo> showSensorBetweenTwoTimeInfo(Map<String, Object> map);

	// 显示传感器设置信息
	public QueryVo showSensorSetting(String containerId);

	// 显示我的信息
	public QueryVo showMyInfo(String username);

	/**
	 * 使用新的ccdata表
	 * 
	 */
	/*
	 * OurCcdata selectOurCcdataById(Integer id);
	 * 
	 * OurCcdata selectOurCcdataBycontainerId(String containerId);
	 * 
	 * List<OurCcdata> selectOurCcdatasBycompanyId(Integer companyId);
	 * 
	 * // 根据companyId查询到一个map，存放的是时间和deviceId List<Map<String, Object>>
	 * selectOurMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceId(Integer
	 * companyId);
	 * 
	 * List<Map<String, Object>>
	 * selectOurMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceIdLikely( Map<String,
	 * Object> map);
	 * 
	 * OurCcdata selectOurLastCcdataByGpsLongTimeAndDeviceMap(Map<String,
	 * Object> map);
	 * 
	 * OurCcdata selectOurLastCcdataByContainerId(String containerId);
	 * 
	 * OurCcdata
	 * selectOurLastCcdataByCompanyIdAndContainerIdLikely(Map<String,Object>
	 * map);
	 * 
	 * List<OurCcdata>
	 * selectOurCcdatasByContainerIdBetweenTowTime(Map<String,Object> map);
	 * 
	 * List<OurCcdata> selectOurCcdatasByDeviceIdBetweenTwoTime(DevAndTimeDiff
	 * datf);
	 * 
	 * int deleteOurCcdataById(Integer id);
	 * 
	 * int deleteOurCcdatasByContainerId(String containerId);
	 * 
	 * int insertOurCcdata(OurCcdata ourccdata);
	 * 
	 * int updateOurCcdataById(OurCcdata ourccdata);
	 * 
	 * OurCcdata selectOurLastCcdataByGpsLongTimeAndDeviceMapLikely( Map<String,
	 * Object> map);
	 * 
	 * //地图专辑 OurCcdata getOurLastLocationBycontainerId(String containerId);
	 * 
	 * List<OurCcdata> selectOurRecordsBetweenInTwoTimeBycontainerId(Map map);
	 * //地图上显示所有的设备信息 List<OurCcdata> getOurAllLocationBycompanyId(Integer
	 * companyId); //查询看对应的设备号 的经纬度是否存在 List<OurCcdata>
	 * selectOurCcdatasLonAndLat(String containerId);
	 * 
	 * public int updateOurCcdataBycontainerId(OurCcdata ourccdata); //查询单个的硬件信息
	 * public OurCcdata selectOurHardWareBycontainerId(String containerId);
	 */
	OurCcdata selectOurCcdataByDeviceId(String deviceId);

	List<Map<String, Object>> getOurMaxGpsLongTimeAndDeviceIdByCompanyId(
			Integer companyId);

	int removeOurCcdatasByContainerId(String containerId);

	public OurCcdata getOurLastCcdataByGpsLongTimeAndDeviceMap(Map map);

	public OurCcdata getOurLastCcdataByContainerId(String containerId);

	public List<OurCcdata> getOurLastCcdatasByGpsLongTimeAndDeviceMap(
			List<Map<String, Object>> maps);

	public List<OurCcdata> getOurLastCcdatasByGpsLongTimeAndDeviceMapLikely(
			Map<String, Object> maps);

	public OurCcdata getOurLastLocationBycontainerId(String containerId);

	public List<OurCcdata> selectOurRecordsBetweenInTwoTimeBycontainerId(Map map);

	public List<OurCcdata> getOurAllLocationBycompanyId(Integer companyId);

	// 查询看对应的设备号 的经纬度是否存在
	public List<OurCcdata> selectOurCcdatasLonAndLat(String containerId);

	// 根据companyId的值获取对应的最新的位置记录
	public List<OurCcdata> getOurLastCcdatasByGpsLongTimeAndDeviceMap(
			Integer companyId);

	public List<OurCcdata> getOurCcdatasByContainerIdBetweenTowTime(
			Map<String, Object> map);

	public List<OurCcdata> selectOurCcdatasByContainerIdBetweenTowTimeASC(
			Map<String, Object> map);

	int addOurOneNewCcdata(OurCcdata ourccdata);

	public int updateOurCcdataById(OurCcdata ourccdata);

	public OurCcdata selectOurCcdataBycontainerId(String containerId);

	public int updateOurCcdataBycontainerId(OurCcdata ourccdata);

	public OurCcdata selectOurCcdataHardWareBycontainerId(String containerId);

	int insertOurCcdata(OurCcdata ourccdata);

	/**
	 * 按照ourccdataMapper1表
	 */

	OurCcdata1 selectOurCcdataById1(Integer id);

	OurCcdata1 selectOurCcdataBycontainerId1(String containerId);

	List<OurCcdata1> selectOurCcdatasBycompanyId1(Integer companyId);

	OurCcdata1 selectOurLastCcdataByContainerId1(String containerId);

	OurCcdata1 selectOurCcdataByDeviceId1(String deviceId);

	int deleteOurCcdataById1(Integer id);

	int deleteOurCcdatasByContainerId1(String containerId);

	int insertOurCcdata1(OurCcdata1 ourccdata);

	int updateOurCcdataById1(OurCcdata1 ourccdata);

	public int updateOurCcdataBycontainerId1(OurCcdata1 ourccdata);

	int removeOurCcdatasByContainerId1(String containerId);

	OurCcdata1 getOurLastCcdataByContainerId1(String containerId);

	public List<OurCcdata1> selectOurCcdatas1BycompanyId1AndNowTimeASC(
			Integer companyId);

	public List<OurCcdata1> selectOurCcdatas1BycompanyId1AndNowTimeDESC(
			Integer companyId);

	public List<OurCcdata1> selectOurCcdatas1BycompanyId1AndGPSPowerASC(
			Integer companyId);

	public List<OurCcdata1> selectOurCcdatas1BycompanyId1AndGPSPowerDESC(
			Integer companyId);

	/**
	 * 系统告警内容
	 * 
	 * @param alarm_num
	 * @return
	 */
	LwAlarm selectLwAlarmByAlarm_num(Integer alarm_num);// 冷王预警查询

	Transformation selectTransformationByAlarmNumber(String alarmNumber);// 凯里预警查询
	
	Transformationnew selectTransformationnewByAlarmNumber(String string);// 新版预警查询

	/**
	 * 控制指令存储
	 * 
	 * @param containerId
	 * @return
	 */
	CommandStore selectCommandStoreDesc(String containerId);

	int insertCommandStore(CommandStore commandStore);

	int updateCommandStoreById(CommandStore commandStore);

	// 查看最新一条开关机指令
	public CommandStore selectCommandStoreSwitchDescOne(String containerId);

	// 查看最新一条运行模式
	public CommandStore selectCommandStoreRunModeDescOne(String containerId);

	// 根据指令查询最新一条
	public CommandStore selectCommandStoreCommandDescOne(Map<String, Object> map);

	public CommandStore selectSetCommand(Map<String, Object> map);
	
	// 根据map查看最新一条运行模式   ***
	public CommandStore selectCommandStoreByMap(Map<String, Object> map);

	// 根据设备号、预警号和24小时内查询最新一条预警
	public Alert selectAlertOnByContainerId(Map<String, Object> map);

	// 根据设备号、预警号和24小时内查询所有预警
	public List<Alert> selectAllAlertOnByContainerId(Map<String, Object> map);

	// 更新冷机预警值
	int updateAlertById(Alert alert);

	int updateAlertTimeById(Alert alt);
	
	
	//查询围栏通知的数量
	Integer getCountOfNoReadedGeomessageBeforeDays(Map<String, Object> map);
	//围栏点击查看明细页面
	List<Geomessage> getAlertsByCompanyIdInOneDays(Map<String, Object> map);
	/*
	//地理围栏查询两段时间内的地理围栏通知消息 selectShowGeomessageInTwoTime
	List<Geomessage> selectShowGeomessageInTwoTime(Map<String, Object> map);
	*/
	List<Geomessage> showGeomessagetsInTwoTimeAndContainerId(Map<String, Object> map);
	
	//更新消息是否已经读取
	Integer modifyAlertReadStateByGeomessagetId(int alertId);
	Geomessage getGeoMessageById(int alertId);
	//插入地理围栏
	int insertGeoMessage(Geomessage record);

	
	
}