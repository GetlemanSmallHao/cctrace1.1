package com.cctrace.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cctrace.dao.AlertMapper;
import com.cctrace.dao.BindTableMapper;
import com.cctrace.dao.CcdataMapper;
import com.cctrace.dao.CcdataMapper1;
import com.cctrace.dao.CompanyMapper;
import com.cctrace.dao.ContainerMapper;
import com.cctrace.dao.GeomessageMapper;
import com.cctrace.dao.LogMapper;
import com.cctrace.dao.LwAlarmMapper;
import com.cctrace.dao.OinfoMapper;
import com.cctrace.dao.OurCcdataMapper;
import com.cctrace.dao.OurCcdataMapper1;
import com.cctrace.dao.QueryVoMapper;
import com.cctrace.dao.RailMapper;
import com.cctrace.dao.TheNextStationMapper;
import com.cctrace.dao.UserMapper;
import com.cctrace.dao.YardMapper;
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
import com.cctrace.service.DaoService;
import com.cctrace.utils.DateUtil;

public class DaoServiceImpl implements DaoService {

	private CompanyMapper companyMapper;
	private AlertMapper alertMapper;
	private BindTableMapper bindTableMapper;
	private CcdataMapper ccdataMapper;
	private ContainerMapper containerMapper;
	private RailMapper railMapper;
	private UserMapper userMapper;
	private LogMapper logMapper;
	private YardMapper yardMapper;
	private TheNextStationMapper theNextStationMapper;
	private OinfoMapper oinfoMapper;
	private GeomessageMapper geomessageMapper;
	// 新写的包装类
	private QueryVoMapper queryVoMapper;
	// 改动的新表
	private OurCcdataMapper ourccdataMapper;
	// 系统告警内容
	private LwAlarmMapper lwAlarmMapper;
	// ccdata1数据显示
	private CcdataMapper1 ccdataMapper1;
	// OurCcdataMapper1改动的新表
	private OurCcdataMapper1 ourccdataMapper1;

	public OurCcdataMapper1 getOurccdataMapper1() {
		return ourccdataMapper1;
	}

	public void setOurccdataMapper1(OurCcdataMapper1 ourccdataMapper1) {
		this.ourccdataMapper1 = ourccdataMapper1;
	}

	public LwAlarmMapper getLwAlarmMapper() {
		return lwAlarmMapper;
	}

	public void setLwAlarmMapper(LwAlarmMapper lwAlarmMapper) {
		this.lwAlarmMapper = lwAlarmMapper;
	}

	public CompanyMapper getCompanyMapper() {
		return companyMapper;
	}

	public AlertMapper getAlertMapper() {
		return alertMapper;
	}

	public BindTableMapper getBindTableMapper() {
		return bindTableMapper;
	}

	public CcdataMapper getCcdataMapper() {
		return ccdataMapper;
	}

	public ContainerMapper getContainerMapper() {
		return containerMapper;
	}

	public RailMapper getRailMapper() {
		return railMapper;
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public LogMapper getLogMapper() {
		return logMapper;
	}

	public YardMapper getYardMapper() {
		return yardMapper;
	}

	public TheNextStationMapper getTheNextStationMapper() {
		return theNextStationMapper;
	}

	public QueryVoMapper getQueryVoMapper() {
		return queryVoMapper;
	}

	public OurCcdataMapper getOurccdataMapper() {
		return ourccdataMapper;
	}

	public void setOurccdataMapper(OurCcdataMapper ourccdataMapper) {
		this.ourccdataMapper = ourccdataMapper;
	}

	public void setQueryVoMapper(QueryVoMapper queryVoMapper) {
		this.queryVoMapper = queryVoMapper;
	}

	public void setAlertMapper(AlertMapper alertMapper) {
		this.alertMapper = alertMapper;
	}

	public void setBindTableMapper(BindTableMapper bindtableMapper) {
		this.bindTableMapper = bindtableMapper;
	}

	public void setCcdataMapper(CcdataMapper ccdataMapper) {
		this.ccdataMapper = ccdataMapper;
	}

	public void setCompanyMapper(CompanyMapper companyMapper) {
		this.companyMapper = companyMapper;
	}

	public void setContainerMapper(ContainerMapper containerMapper) {
		this.containerMapper = containerMapper;
	}

	public void setLogMapper(LogMapper logMapper) {
		this.logMapper = logMapper;
	}

	public void setRailMapper(RailMapper railMapper) {
		this.railMapper = railMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public void setTheNextStationMapper(
			TheNextStationMapper theNextStationMapper) {
		this.theNextStationMapper = theNextStationMapper;
	}

	public void setYardMapper(YardMapper yardMapper) {
		this.yardMapper = yardMapper;
	}

	public OinfoMapper getOinfoMapper() {
		return oinfoMapper;
	}

	public void setOinfoMapper(OinfoMapper oinfoMapper) {
		this.oinfoMapper = oinfoMapper;
	}

	public CcdataMapper1 getCcdataMapper1() {
		return ccdataMapper1;
	}

	public void setCcdataMapper1(CcdataMapper1 ccdataMapper1) {
		this.ccdataMapper1 = ccdataMapper1;
	}

	
	public GeomessageMapper getGeomessageMapper() {
		return geomessageMapper;
	}

	public void setGeomessageMapper(GeomessageMapper geomessageMapper) {
		this.geomessageMapper = geomessageMapper;
	}

	@Override
	public int registNewCompany(Company company) {
		int insert = companyMapper.insertCompany(company);
		return insert;
	}

	@Override
	public Company getCompanyById(Integer id) {
		Company company = companyMapper.selectCompanyById(id);
		return company;
	}

	@Override
	public Company getCompanyByCompanyName(String companyName) {
		Company company = companyMapper.selectCompanyByCompanyName(companyName);
		return company;
	}

	@Override
	public int modifyCompanyInfoById(Company company) {
		int update = companyMapper.updateCompanyById(company);
		return update;
	}

	@Override
	public List<Rail> getRailsByCompanyId(Integer companyId) {
		List<Rail> rails = railMapper.selectAllRailsByCompanyId(companyId);
		return rails;
	}

	@Override
	public Rail getRailById(Integer id) {
		Rail rail = railMapper.selectRailByRailId(id);
		return rail;
	}

	@Override
	public Rail getRailByRailName(String railName) {
		Rail rail = railMapper.selectRailByRailName(railName);
		return rail;
	}

	@Override
	public Rail getRailByRailNameAndCompanyId(Map map) {
		Rail rail = railMapper.selectRailByRailNameAndCompanyId(map);
		return rail;
	}

	@Override
	public int removeRailByRailName(String railName) {
		int delete = railMapper.deleteRailByRailName(railName);
		return delete;
	}

	@Override
	public List<Log> getLogsByOperator(String operator) {
		List<Log> logs = logMapper.selectAllLogsByOperator(operator);
		return logs;
	}

	/**
	 * 这里传递的参数为opeartor和一个长整型的开始时间和一个长整型的结束时间 具体格式请参照底层sql文件
	 */
	@Override
	public List<Log> getLogsByOperatorBetweenTwoTime(Map<String, Object> map) {
		List<Log> logs = logMapper.selectLogsByOperatorBetweenTowTimes(map);
		return logs;
	}

	@Override
	public int logOneNewLog(Log log) {
		int insert = logMapper.insertLog(log);
		return insert;
	}

	@Override
	public int deleteLogById(Integer id) {
		int delete = logMapper.deleteLogById(id);
		return delete;
	}

	@Override
	public int modifyLogById(Log log) {
		int update = logMapper.updateLogById(log);
		return update;
	}

	/**
	 * 暂时在日志里展示
	 */
	@Override
	public List<CommandStore> selectCommandForLogShowInTwoTimes(
			Map<String, Object> map) {
		List<CommandStore> commandStores = logMapper
				.selectCommandForLogShowInTwoTimes(map);
		return commandStores;
	}

	@Override
	public List<CommandStore> selectLogShowInTwoTimesWithContainerIdLike(
			Map<String, Object> map) {
		List<CommandStore> commandStores = logMapper
				.selectLogShowInTwoTimesWithContainerIdLike(map);
		return commandStores;
	}

	@Override
	public int modifyRailByRailId(Rail rail) {
		int update = railMapper.updateRailById(rail);
		return update;
	}

	@Override
	public int addNewRail(Rail rail) {
		int insert = railMapper.insertRail(rail);
		return insert;
	}

	@Override
	public int registNewUser(User user) {
		int insert = userMapper.insertUser(user);
		return insert;
	}

	@Override
	public List<User> getUsersByCompanyId(Integer companyId) {
		List<User> users = userMapper.selectAllUsersByCompanyId(companyId);
		return users;
	}

	@Override
	public List<User> getUsersByCompanyName(String companyName) {
		return null;
	}

	@Override
	public User getUserByUsernameAndPassword(Map<String, String> map) {
		User user = userMapper.selectUserByUsernameAndPassword(map);
		return user;
	}

	@Override
	public List<User> selectUserslikeyInCompany(Map<String, Object> map) {
		List<User> users = userMapper.selectUserslikeyInCompany(map);
		return users;
	}

	@Override
	public User getUserByUsername(String username) {
		User user = userMapper.selectUserByUsername(username);
		return user;
	}

	@Override
	public User getUserByPhone(String phone) {
		User uesr = userMapper.selectUserByPhone(phone);
		return uesr;
	}

	@Override
	public int modifyUserInfoById(User user) {
		int update = userMapper.updateUserById(user);
		return update;
	}

	@Override
	public int removeUserById(int id) {
		int delete = userMapper.deleteUserById(id);
		return delete;
	}

	@Override
	public List<Map<String, Object>> getMaxGpsLongTimeAndDeviceIdByCompanyId(
			Integer companyId) {
		List<Map<String, Object>> maps = ccdataMapper
				.selectMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceId(companyId);
		return maps;
	}

	@Override
	public int removeCcdatasByContainerId(String containerId) {
		int delete = ccdataMapper.deleteCcdatasByContainerId(containerId);
		return delete;
	}

	@Override
	public Ccdata getLastCcdataByGpsLongTimeAndDeviceMap(Map map) {
		Ccdata ccdata = ccdataMapper
				.selectLastCcdataByGpsLongTimeAndDeviceMap(map);
		return ccdata;
	}

	@Override
	public Ccdata selectCcdataByDeviceId(String deviceId) {
		return ccdataMapper.selectCcdataByDeviceId(deviceId);
	}

	@Override
	public Ccdata getLastCcdataByContainerId(String containerId) {
		Ccdata ccdata = ccdataMapper.selectLastCcdataByContainerId(containerId);
		return ccdata;
	}

	@Override
	public List<Ccdata> getLastCcdatasByGpsLongTimeAndDeviceMap(
			List<Map<String, Object>> maps) {
		List<Ccdata> ccdatas = new ArrayList<Ccdata>();
		for (Map<String, Object> map : maps) {
			Ccdata ccdata = getLastCcdataByGpsLongTimeAndDeviceMap(map);
			ccdatas.add(ccdata);
		}
		return ccdatas;
	}

	// 模糊查询 @Override
	public List<Ccdata> getLastCcdatasByGpsLongTimeAndDeviceMapLikely(
			Map<String, Object> map) {
		System.out.println("VVVV" + map.get("containerId"));
		System.out.println("VVVV" + map.get("companyId"));
		List<Map<String, Object>> maps = getMaxGpsLongTimeAndDeviceIdByCompanyIdLikely(map);
		List<Ccdata> ccdatas = getLastCcdatasByGpsLongTimeAndDeviceMapLikely(maps);
		return ccdatas;
	}

	private List<Ccdata> getLastCcdatasByGpsLongTimeAndDeviceMapLikely(
			List<Map<String, Object>> maps) {
		List<Ccdata> ccdatas = new ArrayList<Ccdata>();
		for (Map<String, Object> map : maps) {
			Ccdata ccdata = getLastCcdataByGpsLongTimeAndDeviceMapLikely(map);
			ccdatas.add(ccdata);
		}
		return ccdatas;
	}

	@Override
	public List<Ccdata> selectMaxNowLongTimeIndex(Integer companyId) {
		List<Ccdata> ccdatas = ccdataMapper
				.selectMaxNowLongTimeIndex(companyId);
		return ccdatas;
	}

	private Ccdata getLastCcdataByGpsLongTimeAndDeviceMapLikely(
			Map<String, Object> map) {
		Ccdata ccdata = ccdataMapper
				.selectLastCcdataByGpsLongTimeAndDeviceMapLikely(map);
		return ccdata;
	}

	private List<Map<String, Object>> getMaxGpsLongTimeAndDeviceIdByCompanyIdLikely(
			Map<String, Object> map) {
		List<Map<String, Object>> maps = ccdataMapper
				.selectMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceIdLikely(map);
		return maps;
	}

	// 根据companyId获取当前公司的ccdata表内的相关数据(最新一条记录)
	@Override
	public List<Ccdata> getLastCcdatasByGpsLongTimeAndDeviceMap(
			Integer companyId) {
		List<Map<String, Object>> maps = getMaxGpsLongTimeAndDeviceIdByCompanyId(companyId);
		List<Ccdata> ccdatas = getLastCcdatasByGpsLongTimeAndDeviceMap(maps);
		return ccdatas;
	}

	// 请查看底层sql实现方式传递参数
	@Override
	public List<Ccdata> getCcdatasByContainerIdBetweenTowTime(
			Map<String, Object> map) {
		List<Ccdata> ccdatas = ccdataMapper
				.selectCcdatasByContainerIdBetweenTowTime(map);
		return ccdatas;
	}

	@Override
	public int addOneNewCcdata(Ccdata ccdata) {
		int insert = ccdataMapper.insertCcdata(ccdata);
		return insert;

	}

	@Override
	public int insertCcdata(Ccdata ccdata) {
		return ccdataMapper.insertCcdata(ccdata);

	}

	/**
	 * ccdata1自研数据页面显示数据
	 */

	@Override
	public int updateCcdataBycontainerId1(Ccdata1 cc) {
		return ccdataMapper1.updateCcdataBycontainerId1(cc);
	}

	@Override
	public int updateCcdataById1(Ccdata1 cc) {
		return ccdataMapper1.updateCcdataById1(cc);
	}

	@Override
	public int insertCcdata1(Ccdata1 cc) {
		return ccdataMapper1.insertCcdata1(cc);
	}

	@Override
	public int deleteCcdatasByContainerId1(String containerId) {
		return ccdataMapper1.deleteCcdatasByContainerId1(containerId);
	}

	@Override
	public int deleteCcdataById1(String id) {
		return ccdataMapper1.deleteCcdataById1(id);
	}

	@Override
	public Ccdata1 selectCcdataByDeviceId1(String deviceId) {
		return ccdataMapper1.selectCcdataByDeviceId1(deviceId);
	}

	@Override
	public Ccdata1 selectLastCcdataByContainerId1(String containerId) {
		return ccdataMapper1.selectLastCcdataByContainerId1(containerId);
	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1(Integer companyId) {
		return ccdataMapper1.selectCcdatasBycompanyId1(companyId);
	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndCIdASC(Integer companyId) {
		return ccdataMapper1.selectCcdatasBycompanyId1AndCIdASC(companyId);
	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndCIdDESC(Integer companyId) {
		return ccdataMapper1.selectCcdatasBycompanyId1AndCIdDESC(companyId);
	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndDIdASC(Integer companyId) {
		return ccdataMapper1.selectCcdatasBycompanyId1AndDIdASC(companyId);
	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndDIdDESC(Integer companyId) {
		return ccdataMapper1.selectCcdatasBycompanyId1AndDIdDESC(companyId);
	}

	@Override
	public Ccdata1 selectCcdataById1(Integer id) {
		return ccdataMapper1.selectCcdataById1(id);
	}

	@Override
	public Ccdata1 selectCcdataByContainerId1(String containerId) {
		return ccdataMapper1.selectCcdataByContainerId1(containerId);
	}

	@Override
	public List<Container> getContainersByCompanyId(Integer companyId) {
		List<Container> containers = containerMapper
				.selectContainersByCompanyId(companyId);
		return containers;
	}

	@Override
	public List<Container> getContainersNoBindedByCompanyId(Integer companyId) {
		List<Container> containers = containerMapper
				.selectContainersByCompanyId(companyId);
		return containers;
	}

	@Override
	public int registNewContainer(Container container) {
		int insert = containerMapper.insertContainer(container);
		return insert;
	}

	@Override
	public int modifyContainerById(Container container) {
		int update = containerMapper.updateContainerById(container);
		return update;
	}

	@Override
	public int removeContainerByContainerId(String containerId) {
		int delete = containerMapper.deleteContainerByContainerId(containerId);
		return delete;
	}

	@Override
	public String getContainerIdByDeviceId(String deviceId) {
		String conId = containerMapper.selectContainerIdByDeviceId(deviceId);
		return conId;
	}

	@Override
	public List<Container> selectContainersLikeyInPCContainerWithCompany(
			Map<String, Object> map) {
		List<Container> containers = containerMapper
				.selectContainersLikeyInPCContainerWithCompany(map);
		return containers;
	}

	@Override
	public int removeContainerById(int id) {
		int delete = containerMapper.deleteContainerById(id);
		return delete;
	}

	@Override
	public Container getContainerBycontarinId(String containerId) {
		Container container = containerMapper
				.selecContainerByContainerId(containerId);
		return container;
	}

	@Override
	public Container getContainerByDeviceId(String deviceId) {
		Container container = containerMapper
				.selectContainerByDeviceId(deviceId);
		return container;
	}

	// 调用本方法时传递的参数类型为Map接口的实现类，需要注意的是我们传递的参数为两个
	// 一个是companyId，一个是containerId,这里用到的是模糊查询
	@Override
	public List<Container> getContainersByCompanyIdAndContainerIdLikely(
			Map<String, Object> map) {
		List<Container> containers = containerMapper
				.selectContainersByCompanyIdAndContainerIdLikeLy(map);
		return containers;
	}

	@Override
	public Integer registNewBindTable(BindTable bindTable) {
		int insert = bindTableMapper.insertBindTable(bindTable);
		return insert;
	}

	@Override
	public int modifyBindTableById(BindTable bindTable) {
		int update = bindTableMapper.updateBindTableById(bindTable);
		return update;
	}

	@Override
	public int removeBindTableByContainerId(String containerId) {
		int delete = bindTableMapper.deleteBindTableByContainerId(containerId);
		return delete;
	}

	@Override
	public List<BindTable> getAllBindTables() {
		List<BindTable> bindTables = bindTableMapper.selectAllBindTables();
		return bindTables;
	}

	@Override
	public List<BindTable> getBindTablesByCompanyId(Integer companyId) {
		List<BindTable> bindTables = bindTableMapper
				.selectBindTablesByCompanyId(companyId);
		return bindTables;
	}

	@Override
	public BindTable getBindTableByContainerId(String containerId) {
		BindTable bindTable = bindTableMapper
				.selectBindTableByContainerId(containerId);
		return bindTable;
	}

	@Override
	public List<BindTable> selectBindTableByCompanyAndRemarkDESC(
			Integer companyId) {
		List<BindTable> bindTables = bindTableMapper
				.selectBindTableByCompanyAndRemarkDESC(companyId);
		return bindTables;
	}

	@Override
	public List<BindTable> selectBindTableByCompanyAndRemarkASC(
			Integer companyId) {
		List<BindTable> bindTables = bindTableMapper
				.selectBindTableByCompanyAndRemarkASC(companyId);
		return bindTables;

	}

	@Override
	public List<Alert> selectAllAlerts() {
		List<Alert> alerts = alertMapper.selectAllAlerts();
		return alerts;
	}

	@Override
	public Integer getMaxAlertId() {
		Integer maxAlertId = alertMapper.selectMaxAlertId();
		return maxAlertId;
	}

	@Override
	public Alert getAlertById(Integer id) {
		Alert alert = alertMapper.selectAlertById(id);
		return alert;
	}

	@Override
	public List<Alert> selectAltersByCompanyId(Integer companyId) {
		List<Alert> alerts = alertMapper.selectAlertsByCompanyId(companyId);
		return alerts;
	}

	/**
	 * 这个方法传递的参数是一个map，这个map中的的键分别是companyId和一个整数额天数
	 */
	@Override
	public List<Alert> getAlertsByCompanyIdAndDays(Map<String, Object> map) {
		Integer days = (Integer) map.get("days");
		Date now = DateUtil.getNowDate();
		double longTime = (double) DateUtil.getLongFromDate(now);
		double longTimejian = days * 68400000;
		longTime = longTime - longTimejian;
		map.put("longTime", longTime);
		List<Alert> alerts = alertMapper.selectAlertsByCompanyIdAndDays(map);
		return alerts;
	}

	@Override
	public int addOneNewAlert(Alert alert) {
		int insert = alertMapper.insertAlert(alert);
		return insert;
	}

	@Override
	public List<Alert> getAlertsByCompanyIdInFourDays(Map<String, Object> map) {
		List<Alert> alerts = alertMapper.selectAlertsByCompanyIdInFourDays(map);
		return alerts;
	}

	@Override
	public List<Alert> getAlertsByCompanyIdAndContainerIdLikely(
			Map<String, Object> map) {
		List<Alert> alerts = alertMapper
				.selectAlertsByCompanyIdAndContainerIdLikely(map);
		return alerts;
	}

	@Override
	public List<Alert> getAlertsByCompanyIdAndAlertTypeLikely(
			Map<String, Object> map) {
		List<Alert> alerts = alertMapper
				.selectAlertsByCompanyIdAndAlertTypeLikely(map);
		return alerts;
	}

	@Override
	public List<Alert> getAlertsByCompanyIdAndContainerIdAndAlertTypeLikely(
			Map<String, Object> map) {
		List<Alert> alerts = alertMapper
				.selectAlertsByCompanyIdAndContainerIdAndAlertTypeLikely(map);
		return alerts;
	}

	@Override
	public Integer modifyAlertReadStateToYesByAlertId(Integer alertId) {
		int update = alertMapper.updateAlertReadStateToYesByAlertId(alertId);
		return update;
	}

	@Override
	public Integer getCountOfNoReadedAlertsByCompanyId(Integer companyId) {
		Integer count = alertMapper
				.selectCountNoReadedAlertsByCompanyId(companyId);
		return count;
	}

	@Override
	public Integer getCountOfNoReadedAlertsByCompanyIdAndbeginTime(
			Map<String, Object> map) {
		Integer count = alertMapper
				.selectCountNoReadedAlertsByCompanyIdAndbeginTime(map);
		return count;
	}

	@Override
	public Integer selectCountNoReadedLastAlertsByCompanyId(Integer companyId) {
		Integer count = alertMapper
				.selectCountNoReadedLastAlertsByCompanyId(companyId);
		return count;
	}

	@Override
	public Integer selectCountNoReadedBeforeSomeDays(Map<String, Object> map) {
		Integer count = alertMapper.selectCountNoReadedBeforeSomeDays(map);
		return count;
	}

	@Override
	public Integer getMaxAlertIdByCompanyId(Integer companyId) {
		Integer maxAlertId = alertMapper.selectMaxAlertIdByCompanyId(companyId);
		return maxAlertId;
	}

	@Override
	public int removeAlertById(Integer id) {
		int delete = alertMapper.deletAlertById(id);
		return delete;
	}

	@Override
	public int modifyAlertById(Alert alert) {
		int update = alertMapper.updateAlertById(alert);
		return update;
	}

	@Override
	public int insertAlert(Alert alert) {
		return alertMapper.insertAlert(alert);
	}

	@Override
	public List<Alert> selectLastAlertsTimeByCompanyId(Integer companyId) {
		List<Alert> alerts = alertMapper
				.selectLastAlertsTimeByCompanyId(companyId);
		return alerts;
	}

	@Override
	public int registNewYard(Yard yard) {
		int insert = yardMapper.insertNewYard(yard);
		return insert;
	}

	@Override
	public List<Yard> getAllYarsByCompanyId(Integer companyId) {
		List<Yard> yards = yardMapper.selectAllYardsByCompanyId(companyId);
		return yards;
	}

	@Override
	public Yard getYardByYardNameAndCompanyId(Map<String, Object> map) {
		Yard yard = yardMapper.selectYardByYardNameAndCompanyId(map);
		return yard;
	}

	@Override
	public Yard getYardByYardName(String yardName) {
		Yard yard = yardMapper.selectYardByYardName(yardName);
		return yard;
	}

	@Override
	public int modifyYardById(Yard yard) {
		int update = yardMapper.updateYardById(yard);
		return update;
	}

	@Override
	public int remvoeYardById(Integer id) {
		int delete = yardMapper.deleteYardById(id);
		return delete;
	}

	@Override
	public int removeYardByYardNameAndCompanyId(Yard yard) {
		int delete = yardMapper.deleteYardByYardNameAndCompanyId(yard);
		return delete;
	}

	@Override
	public int registNewTheNextStation(TheNextStation station) {
		int insert = theNextStationMapper
				.insertTheNextStationBycompanyIdAndStationName(station);
		return insert;
	}

	@Override
	public List<TheNextStation> getAllTheNextStationsByCompanyId(
			Integer companyId) {
		List<TheNextStation> stations = theNextStationMapper
				.selectAllTheNextStationsBycompanyId(companyId);
		return stations;
	}

	@Override
	public TheNextStation getTheNextStationById(Integer id) {
		TheNextStation theNextStation = theNextStationMapper
				.selectTheNextStationById(id);
		return theNextStation;
	}

	@Override
	public TheNextStation getTheNextStationBycompanyIdAndStationName(
			Map<String, Object> map) {
		TheNextStation theNextStation = theNextStationMapper
				.selectTheNextStationBycompanyIdAndStationName(map);
		return theNextStation;
	}

	@Override
	public int modifyTheNextStationById(TheNextStation station) {
		int update = theNextStationMapper.updateTheNextStationById(station);
		return update;
	}

	/**
	 * 根据公司名和下货站名称删除对应的下货站信息
	 */
	@Override
	public int removeTheNextStationByCompanyIdAndStationName(
			TheNextStation station) {
		int delete = theNextStationMapper
				.deleteTheNextStationBycompanyIdAndStationName(station);
		return delete;
	}

	@Override
	public Yard getYardbyId(Integer yardId) {

		Yard yard = yardMapper.selectYardById(yardId);
		return yard;
	}

	// @Override
	// public Oinfo getOinfoByOinfoContent(String oinfoContent) {
	// Oinfo oinfo =oinfoMapper.selectOinfoByOinfoContent(oinfoContent);
	// return oinfo;
	// }
	@Override
	public QueryVo getInforBycontainerId(String containerId) {
		QueryVo queryVo = queryVoMapper.getInforBycontainerId(containerId);
		return queryVo;
	}

	public static Map<String, Object> setTimeBeforeNow(Map<String, Object> map,
			Integer i) {
		Date now = DateUtil.getNowDate();
		map.put("startDate", DateUtil.getServeralDaysBeforeNowTime(now, i));
		map.put("endDate", now);
		return map;
	}

	@Override
	public List<QueryVo> showYardInformation(Integer companyId) {
		List<QueryVo> queryVos = queryVoMapper.showYardInformation(companyId);
		return queryVos;
	}

	@Override
	public List<QueryVo> showStationInformation(Integer companyId) {
		List<QueryVo> queryVos = queryVoMapper
				.showStationInformation(companyId);
		return queryVos;
	}

	@Override
	public List<QueryVo> showAllContainerByUsername(Integer companyId) {
		List<QueryVo> queryVos = queryVoMapper
				.showAllContainerByUsername(companyId);
		return queryVos;
	}

	@Override
	public List<QueryVo> showAllBanLieByCompanyId(Integer companyId) {
		List<QueryVo> queryVos = queryVoMapper
				.showAllBanLieByCompanyId(companyId);
		return queryVos;
	}

	@Override
	public QueryVo showBanLieInfo(String containerId) {
		QueryVo queryVo = queryVoMapper.showBanLieInfo(containerId);
		return queryVo;
	}

	@Override
	public QueryVo showContainerinforBycontainerId(String containerId) {
		QueryVo queryVo = queryVoMapper
				.showContainerinforBycontainerId(containerId);
		return queryVo;
	}

	/**
	 * new add
	 */
	@Override
	public List<Alert> selectIfAlertBytrainId(String containerId) {
		List<Alert> alerts = alertMapper.selectIfAlertBytrainId(containerId);
		return alerts;
	}

	@Override
	public QueryVo showBanLieInfoNoAlert(String containerId) {
		QueryVo queryVos = queryVoMapper.showBanLieInfoNoAlert(containerId);
		return queryVos;
	}

	@Override
	public List<QueryVo> showSensorBetweenTwoTimeInfo(Map<String, Object> map) {
		List<QueryVo> queryVos = queryVoMapper
				.showSensorBetweenTwoTimeInfo(map);
		return queryVos;
	}

	@Override
	public int updateContainerByContainerId(Container container) {
		int n = containerMapper.updateContainerByContainerId(container);
		return n;
	}

	@Override
	public List<Ccdata> selectRecordsBetweenInTwoTimeBycontainerId(Map map) {
		List<Ccdata> records = ccdataMapper
				.selectRecordsBetweenInTwoTimeBycontainerId(map);
		return records;
	}

	@Override
	public Ccdata getLastLocationBycontainerId(String containerId) {
		Ccdata ccdata = ccdataMapper.getLastLocationBycontainerId(containerId);
		return ccdata;
	}

	@Override
	public List<Ccdata> getAllLocationBycompanyId(Integer companyId) {
		List<Ccdata> ccdatas = ccdataMapper
				.getAllLocationBycompanyId(companyId);
		return ccdatas;
	}

	@Override
	public List<Ccdata> selectCcdatasLonAndLat(String containerId) {
		List<Ccdata> ccdatas = ccdataMapper.selectCcdatasLonAndLat(containerId);
		return ccdatas;
	}

	@Override
	public int updateCcdataById(Ccdata ccdata) {
		int n = ccdataMapper.updateCcdataById(ccdata);
		return n;
	}

	@Override
	public Ccdata selectCcdataBycontainerId(String containerId) {
		Ccdata ccdata = ccdataMapper.selectCcdataBycontainerId(containerId);
		return ccdata;
	}

	@Override
	public int updateCcdataBycontainerId(Ccdata ccdata) {
		int n = ccdataMapper.updateCcdataBycontainerId(ccdata);
		return n;
	}

	@Override
	public Ccdata selectHardWareBycontainerId(String containerId) {
		Ccdata ccdata = ccdataMapper.selectHardWareBycontainerId(containerId);
		return ccdata;
	}

	@Override
	public QueryVo showSensorSetting(String containerId) {
		QueryVo queryVo = queryVoMapper.showSensorSetting(containerId);
		return queryVo;
	}

	@Override
	public int updateBindTableByYardId(Integer yardId) {
		int n = bindTableMapper.updateBindTableByYardId(yardId);
		return n;
	}

	@Override
	public int updateBindTableByTheNextStation(Integer theNextStationId) {
		int n = bindTableMapper
				.updateBindTableByTheNextStation(theNextStationId);
		return n;
	}

	@Override
	public List<BindTable> selectBindTableByYardId(Integer yardId) {
		List<BindTable> bindTables = bindTableMapper
				.selectBindTableByYardId(yardId);
		return bindTables;
	}

	@Override
	public List<BindTable> selectBindTableByTheNextStation(
			Integer theNextStationId) {
		List<BindTable> bindTables = bindTableMapper
				.selectBindTableByTheNextStation(theNextStationId);
		return bindTables;
	}

	@Override
	public QueryVo showMyInfo(String username) {
		QueryVo queryVo = queryVoMapper.showMyInfo(username);
		return queryVo;
	}

	@Override
	public List<Alert> selectAlertsByContainderId(String containerId) {
		List<Alert> alerts = alertMapper
				.selectAlertsByContainderId(containerId);
		return alerts;
	}

	@Override
	public Integer selectAlertInBeforeSeconds(Map<String, Object> map) {
		Integer count = alertMapper.selectAlertInBeforeSeconds(map);
		return count;
	}

	/**
	 * 按照ccdata表复原的方法
	 */

	@Override
	public OurCcdata selectOurCcdataByDeviceId(String deviceId) {
		return ourccdataMapper.selectOurCcdataByDeviceId(deviceId);
	}

	@Override
	public List<Map<String, Object>> getOurMaxGpsLongTimeAndDeviceIdByCompanyId(
			Integer companyId) {
		List<Map<String, Object>> maps = ourccdataMapper
				.selectOurMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceId(companyId);
		return maps;
	}

	@Override
	public int removeOurCcdatasByContainerId(String containerId) {
		int delete = ourccdataMapper.deleteOurCcdatasByContainerId(containerId);
		return delete;
	}

	@Override
	public OurCcdata getOurLastCcdataByGpsLongTimeAndDeviceMap(Map map) {
		OurCcdata ourccdata = ourccdataMapper
				.selectOurLastCcdataByGpsLongTimeAndDeviceMap(map);
		return ourccdata;
	}

	@Override
	public OurCcdata getOurLastCcdataByContainerId(String containerId) {
		OurCcdata ourccdata = ourccdataMapper
				.selectOurLastCcdataByContainerId(containerId);
		return ourccdata;
	}

	@Override
	public List<OurCcdata> getOurLastCcdatasByGpsLongTimeAndDeviceMap(
			List<Map<String, Object>> maps) {
		List<OurCcdata> ourccdatas = new ArrayList<OurCcdata>();
		for (Map<String, Object> map : maps) {
			OurCcdata ourccdata = getOurLastCcdataByGpsLongTimeAndDeviceMap(map);
			ourccdatas.add(ourccdata);
		}
		return ourccdatas;
	}

	// 模糊查询 @Override
	public List<OurCcdata> getOurLastCcdatasByGpsLongTimeAndDeviceMapLikely(
			Map<String, Object> map) {
		System.out.println("VVVV" + map.get("containerId"));
		System.out.println("VVVV" + map.get("companyId"));
		List<Map<String, Object>> maps = getOurMaxGpsLongTimeAndDeviceIdByCompanyIdLikely(map);
		List<OurCcdata> ourccdatas = getOurLastCcdatasByGpsLongTimeAndDeviceMapLikely(maps);
		return ourccdatas;
	}

	private List<OurCcdata> getOurLastCcdatasByGpsLongTimeAndDeviceMapLikely(
			List<Map<String, Object>> maps) {
		List<OurCcdata> ourccdatas = new ArrayList<OurCcdata>();
		for (Map<String, Object> map : maps) {
			OurCcdata ourccdata = getOurLastCcdataByGpsLongTimeAndDeviceMapLikely(map);
			ourccdatas.add(ourccdata);
		}
		return ourccdatas;
	}

	private OurCcdata getOurLastCcdataByGpsLongTimeAndDeviceMapLikely(
			Map<String, Object> map) {
		OurCcdata ourccdata = ourccdataMapper
				.selectOurLastCcdataByGpsLongTimeAndDeviceMapLikely(map);
		return ourccdata;
	}

	private List<Map<String, Object>> getOurMaxGpsLongTimeAndDeviceIdByCompanyIdLikely(
			Map<String, Object> map) {
		List<Map<String, Object>> maps = ourccdataMapper
				.selectOurMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceIdLikely(map);
		return maps;
	}

	// 根据companyId获取当前公司的ccdata表内的相关数据(最新一条记录)
	@Override
	public List<OurCcdata> getOurLastCcdatasByGpsLongTimeAndDeviceMap(
			Integer companyId) {
		List<Map<String, Object>> maps = getOurMaxGpsLongTimeAndDeviceIdByCompanyId(companyId);
		List<OurCcdata> ourccdatas = getOurLastCcdatasByGpsLongTimeAndDeviceMap(maps);
		return ourccdatas;
	}

	// 请查看底层sql实现方式传递参数
	@Override
	public List<OurCcdata> getOurCcdatasByContainerIdBetweenTowTime(
			Map<String, Object> map) {
		List<OurCcdata> ourccdatas = ourccdataMapper
				.selectOurCcdatasByContainerIdBetweenTowTime(map);
		return ourccdatas;
	}

	@Override
	public int addOurOneNewCcdata(OurCcdata ourccdata) {
		int insert = ourccdataMapper.insertOurCcdata(ourccdata);
		return insert;

	}

	@Override
	public List<OurCcdata> selectOurRecordsBetweenInTwoTimeBycontainerId(Map map) {
		List<OurCcdata> records = ourccdataMapper
				.selectOurRecordsBetweenInTwoTimeBycontainerId(map);
		return records;
	}

	@Override
	public OurCcdata getOurLastLocationBycontainerId(String containerId) {
		OurCcdata ourccdata = ourccdataMapper
				.getOurLastLocationBycontainerId(containerId);
		return ourccdata;
	}

	@Override
	public List<OurCcdata> getOurAllLocationBycompanyId(Integer companyId) {
		List<OurCcdata> ourccdatas = ourccdataMapper
				.getOurAllLocationBycompanyId(companyId);
		return ourccdatas;
	}

	@Override
	public List<OurCcdata> selectOurCcdatasLonAndLat(String containerId) {
		List<OurCcdata> ourccdatas = ourccdataMapper
				.selectOurCcdatasLonAndLat(containerId);
		return ourccdatas;
	}

	@Override
	public int updateOurCcdataById(OurCcdata ourccdata) {
		int n = ourccdataMapper.updateOurCcdataById(ourccdata);
		return n;
	}

	@Override
	public OurCcdata selectOurCcdataBycontainerId(String containerId) {
		OurCcdata ourccdata = ourccdataMapper
				.selectOurCcdataBycontainerId(containerId);
		return ourccdata;
	}

	@Override
	public int updateOurCcdataBycontainerId(OurCcdata ourccdata) {
		int n = ourccdataMapper.updateOurCcdataBycontainerId(ourccdata);
		return n;
	}

	@Override
	public OurCcdata selectOurCcdataHardWareBycontainerId(String containerId) {
		OurCcdata ourccdata = ourccdataMapper
				.selectOurHardWareBycontainerId(containerId);
		return ourccdata;
	}

	@Override
	public int insertOurCcdata(OurCcdata ourccdata) {
		return ourccdataMapper.insertOurCcdata(ourccdata);
	}

	@Override
	public int insertErrorData(ErrorData errorData) {
		return alertMapper.insertErrorData(errorData);
	}

	@Override
	public ErrorData selectErrorDataByDeviceId(String deviceId) {
		return alertMapper.selectErrorDataByDeviceId(deviceId);
	}

	@Override
	public LwAlarm selectLwAlarmByAlarm_num(Integer alarm_num) {
		return lwAlarmMapper.selectLwAlarmByAlarm_num(alarm_num);
	}

	@Override
	public Transformation selectTransformationByAlarmNumber(String alarmNumber) {
		return lwAlarmMapper.selectTransformationByAlarmNumber(alarmNumber);
	}
	
	@Override
	public Transformationnew selectTransformationnewByAlarmNumber(String alarmNumber) {
		return lwAlarmMapper.selectTransformationnewByAlarmNumber(alarmNumber);
	}

	@Override
	public List<Alert> selectContainersWithAlert() {
		List<Alert> alerts = alertMapper.selectContainersWithAlert();
		return alerts;
	}

	// 控制指令存储
	@Override
	public CommandStore selectCommandStoreDesc(String containerId) {
		return alertMapper.selectCommandStoreDesc(containerId);
	}

	@Override
	public int insertCommandStore(CommandStore commandStore) {
		return alertMapper.insertCommandStore(commandStore);
	}

	@Override
	public int updateCommandStoreById(CommandStore commandStore) {
		return alertMapper.updateCommandStoreById(commandStore);
	}

	@Override
	public List<User> selectLimitUser(Map<String, Object> map) {
		List<User> users = userMapper.selectLimitUser(map);
		return users;
	}

	@Override
	public List<Ccdata> selectSensorLikeyByContainerId(String containerId) {
		List<Ccdata> ccdatas = ccdataMapper
				.selectSensorLikeyByContainerId(containerId);
		return ccdatas;
	}

	@Override
	public List<BindTable> selectContainersLikey(String containerId) {
		List<BindTable> bindTables = bindTableMapper
				.selectContainersLikey(containerId);
		return bindTables;
	}

	@Override
	public List<Container> selectContainersLikeyInHard(String containerId) {
		List<Container> containers = containerMapper
				.selectContainersLikeyInHard(containerId);
		return containers;
	}

	@Override
	public Container selectContainerByDeviceId(String deviceId) {
		return containerMapper.selectContainerByDeviceId(deviceId);
	}

	@Override
	public List<Alert> selectContainersLikeyInAlert(String containerId) {
		List<Alert> alerts = alertMapper
				.selectContainersLikeyInAlert(containerId);
		return alerts;
	}

	@Override
	public List<User> selectUsersLikey(String username) {
		List<User> users = userMapper.selectUsersLikey(username);
		return users;
	}

	@Override
	public List<Container> selectContainersLikeyInPCContainer(String containerId) {
		List<Container> containers = containerMapper
				.selectContainersLikeyInPCContainer(containerId);
		return containers;
	}

	@Override
	public CommandStore selectSetCommand(Map<String, Object> map) {
		CommandStore commandStore = alertMapper.selectSetCommand(map);
		return commandStore;
	}

	@Override
	public CommandStore selectCommandStoreSwitchDescOne(String containerId) {
		return alertMapper.selectCommandStoreSwitchDescOne(containerId);
	}

	@Override
	public CommandStore selectCommandStoreRunModeDescOne(String containerId) {
		return alertMapper.selectCommandStoreRunModeDescOne(containerId);
	}

	@Override
	public CommandStore selectCommandStoreCommandDescOne(Map<String, Object> map) {
		return alertMapper.selectCommandStoreCommandDescOne(map);
	}

	@Override
	public List<Alert> selectNewAlertInTwoTime(Map<String, Object> map) {
		List<Alert> alerts = alertMapper.selectNewAlertInTwoTime(map);
		return alerts;
	}

	@Override
	public List<Alert> selectShowAlertInTwoTime(Map<String, Object> map) {
		List<Alert> alerts = alertMapper.selectShowAlertInTwoTime(map);
		return alerts;
	}
	
	/**
	 * 新增模块
	 */
	@Override
	public List<Alert> selectShowReaded(Map<String, Object> map) {
		List<Alert> alerts = alertMapper.selectShowReaded(map);
		return alerts;
	}
	
	@Override
	public List<Alert> selectShowBuMenM(Map<String, Object> map) {
		List<Alert> alerts = alertMapper.selectShowBuMenM(map);
		return alerts;
	}
	
	@Override
	public List<Alert> selectShowAlarmNum(Map<String, Object> map) {
		List<Alert> alerts = alertMapper.selectShowAlarmNum(map);
		return alerts;
	}

	/**
	 * OurCcdata1表
	 */
	@Override
	public OurCcdata1 selectOurCcdataById1(Integer id) {
		return ourccdataMapper1.selectOurCcdataById(id);
	}

	@Override
	public OurCcdata1 selectOurCcdataBycontainerId1(String containerId) {
		return ourccdataMapper1.selectOurCcdataBycontainerId(containerId);
	}

	@Override
	public List<OurCcdata1> selectOurCcdatasBycompanyId1(Integer companyId) {
		return ourccdataMapper1.selectOurCcdatasBycompanyId(companyId);
	}

	@Override
	public OurCcdata1 selectOurLastCcdataByContainerId1(String containerId) {
		return ourccdataMapper1.selectOurLastCcdataByContainerId(containerId);
	}

	@Override
	public OurCcdata1 selectOurCcdataByDeviceId1(String deviceId) {
		return ourccdataMapper1.selectOurCcdataByDeviceId(deviceId);
	}

	@Override
	public int deleteOurCcdataById1(Integer id) {
		return ourccdataMapper1.deleteOurCcdataById(id);
	}

	@Override
	public int deleteOurCcdatasByContainerId1(String containerId) {
		return ourccdataMapper1.deleteOurCcdatasByContainerId(containerId);
	}

	@Override
	public int insertOurCcdata1(OurCcdata1 ourccdata) {
		return ourccdataMapper1.insertOurCcdata(ourccdata);
	}

	@Override
	public int updateOurCcdataById1(OurCcdata1 ourccdata) {
		return ourccdataMapper1.updateOurCcdataById(ourccdata);
	}

	@Override
	public int updateOurCcdataBycontainerId1(OurCcdata1 ourccdata) {
		return ourccdataMapper1.updateOurCcdataBycontainerId(ourccdata);
	}

	@Override
	public int removeOurCcdatasByContainerId1(String containerId) {
		return ourccdataMapper1.deleteOurCcdatasByContainerId(containerId);
	}

	@Override
	public OurCcdata1 getOurLastCcdataByContainerId1(String containerId) {
		return ourccdataMapper1.selectOurLastCcdataByContainerId(containerId);
	}

	@Override
	public List<Alert> selectAlertGroupInOneDay(Map<String, Object> map) {
		List<Alert> alerts = alertMapper.selectAlertGroupInOneDay(map);
		return alerts;
	}

	@Override
	public List<Alert> selectAlertAfterCommand(Map<String, Object> map) {
		List<Alert> alerts = alertMapper.selectAlertAfterCommand(map);
		return alerts;
	}

	@Override
	public List<String> selectAlertWithContainer(Integer companyId) {
		List<String> list = alertMapper.selectAlertWithContainer(companyId);
		return list;
	}

	@Override
	public List<Ccdata1> selectIndexContainerIdLikey(Map<String, Object> map) {
		List<Ccdata1> ccdata1s = ccdataMapper1.selectIndexContainerIdLikey(map);
		return ccdata1s;
	}

	@Override
	public List<Ccdata> selectCcdatasByContainerIdBetweenTowTimeASC(
			Map<String, Object> map) {
		List<Ccdata> ccdatas = ccdataMapper
				.selectCcdatasByContainerIdBetweenTowTimeASC(map);
		return ccdatas;

	}

	@Override
	public List<OurCcdata> selectOurCcdatasByContainerIdBetweenTowTimeASC(
			Map<String, Object> map) {
		List<OurCcdata> ourCcdatas = ourccdataMapper
				.selectOurCcdatasByContainerIdBetweenTowTimeASC(map);
		return ourCcdatas;
	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndNowTimeASC(
			Integer companyId) {
		List<Ccdata1> ccdata1s = ccdataMapper1
				.selectCcdatasBycompanyId1AndNowTimeASC(companyId);
		return ccdata1s;
	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndNowTimeDESC(
			Integer companyId) {
		List<Ccdata1> ccdata1s = ccdataMapper1
				.selectCcdatasBycompanyId1AndNowTimeDESC(companyId);
		return ccdata1s;
	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndSetTempDESC(
			Integer companyId) {
		List<Ccdata1> ccdata1s = ccdataMapper1
				.selectCcdatasBycompanyId1AndSetTempDESC(companyId);
		return ccdata1s;
	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndSetTempASC(
			Integer companyId) {
		List<Ccdata1> ccdata1s = ccdataMapper1
				.selectCcdatasBycompanyId1AndSetTempASC(companyId);
		return ccdata1s;
	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndBackWindASC(
			Integer companyId) {
		List<Ccdata1> ccdata1s = ccdataMapper1
				.selectCcdatasBycompanyId1AndBackWindASC(companyId);
		return ccdata1s;
	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndBackWindDESC(
			Integer companyId) {
		List<Ccdata1> ccdata1s = ccdataMapper1
				.selectCcdatasBycompanyId1AndBackWindDESC(companyId);
		return ccdata1s;
	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndRefBatVolASC(
			Integer companyId) {
		List<Ccdata1> ccdata1s = ccdataMapper1
				.selectCcdatasBycompanyId1AndRefBatVolASC(companyId);
		return ccdata1s;
	}

	@Override
	public List<Ccdata1> selectCcdatasBycompanyId1AndRefBatVolDESC(
			Integer companyId) {
		List<Ccdata1> ccdata1s = ccdataMapper1
				.selectCcdatasBycompanyId1AndRefBatVolDESC(companyId);
		return ccdata1s;
	}

	@Override
	public List<OurCcdata1> selectOurCcdatas1BycompanyId1AndNowTimeASC(
			Integer companyId) {
		List<OurCcdata1> ourCcdata1s = ourccdataMapper1
				.selectOurCcdatas1BycompanyId1AndNowTimeASC(companyId);
		return ourCcdata1s;
	}

	@Override
	public List<OurCcdata1> selectOurCcdatas1BycompanyId1AndNowTimeDESC(
			Integer companyId) {
		List<OurCcdata1> ourCcdata1s = ourccdataMapper1
				.selectOurCcdatas1BycompanyId1AndNowTimeDESC(companyId);
		return ourCcdata1s;
	}

	@Override
	public List<OurCcdata1> selectOurCcdatas1BycompanyId1AndGPSPowerASC(
			Integer companyId) {
		List<OurCcdata1> ourCcdata1s = ourccdataMapper1
				.selectOurCcdatas1BycompanyId1AndGPSPowerASC(companyId);
		return ourCcdata1s;
	}

	@Override
	public List<OurCcdata1> selectOurCcdatas1BycompanyId1AndGPSPowerDESC(
			Integer companyId) {
		List<OurCcdata1> ourCcdata1s = ourccdataMapper1
				.selectOurCcdatas1BycompanyId1AndGPSPowerDESC(companyId);
		return ourCcdata1s;
	}

	@Override
	public List<User> showLimitUserLikey(Map<String, Object> map) {
		List<User> users = userMapper.showLimitUserLikey(map);
		return users;
	}

	// 根据设备号、预警号和24小时内查询最新一条预警
	@Override
	public Alert selectAlertOnByContainerId(Map<String, Object> map) {
		return alertMapper.selectAlertOnByContainerId(map);
	}

	// 根据设备号、预警号和24小时内查询所有预警
	@Override
	public List<Alert> selectAllAlertOnByContainerId(Map<String, Object> map) {
		return alertMapper.selectAllAlertOnByContainerId(map);
	}

	// 更新冷机预警值
	@Override
	public int updateAlertById(Alert alert) {
		return alertMapper.updateAlertById(alert);
	}

	@Override
	public int updateAlertTimeById(Alert alertId) {
		return alertMapper.updateAlertTimeById(alertId);
	}

	//一天内没查看的围栏通知数量
	@Override
	public Integer getCountOfNoReadedGeomessageBeforeDays(Map<String, Object> map) {
		return geomessageMapper.getCountOfNoReadedGeomessageBeforeDays(map);
	}
	
	//围栏点击查看明细页面
	@Override
	public List<Geomessage> getAlertsByCompanyIdInOneDays(Map<String, Object> map) {
		return geomessageMapper.getAlertsByCompanyIdInOneDays(map);
	}
/*
	@Override
	public List<Geomessage> selectShowGeomessageInTwoTime(Map<String, Object> map) {
		return geomessageMapper.selectShowGeomessageInTwoTime(map);
	}
*/
	@Override
	public List<Geomessage> showGeomessagetsInTwoTimeAndContainerId(Map<String, Object> map) {
		return geomessageMapper.showGeomessagetsInTwoTimeAndContainerId(map);
	}

	@Override
	public Integer modifyAlertReadStateByGeomessagetId(int alertId) {
		return geomessageMapper.modifyAlertReadStateByGeomessagetId(alertId);
	}

	@Override
	public Geomessage getGeoMessageById(int alertId) {
		return geomessageMapper.getGeoMessageById(alertId);
	}

	@Override
	public int insertGeoMessage(Geomessage record) {
		return geomessageMapper.insert(record);
	}

	@Override
	public CommandStore selectCommandStoreByMap(Map<String, Object> map) {
		return alertMapper.selectCommandStoreByMap(map);
	}

	@Override
	public List<CommandStore> selectLogShowInTwoTimesWithCommandTypeLike(
			Map<String, Object> map) {
		List<CommandStore> commandStores = logMapper
				.selectLogShowInTwoTimesWithCommandTypeLike(map);
		return commandStores;
	}
	

}
