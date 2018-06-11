package com.cctrace.socketServers.carryHandle;

import java.util.HashMap;
import java.util.Map;

import com.cctrace.entity.Alert;
import com.cctrace.entity.Ccdata;
import com.cctrace.entity.Ccdata1;
import com.cctrace.entity.CommandStore;
import com.cctrace.entity.OurCcdata1;
import com.cctrace.entity.Transformation;
import com.cctrace.entity.Transformationnew;
import com.cctrace.service.DaoService;
import com.cctrace.socketServers.util.DealDateData;
import com.github.pagehelper.util.StringUtil;

public class CarrierDataStorage {

	private DaoService daoService;

	public CarrierDataStorage(DaoService daoService) {
		this.daoService = daoService;
	}

	public String DataStorage(String data, String sevNum) {
		// data="";

		try {

			Ccdata1 cc1 = daoService.selectCcdataByDeviceId1(sevNum);
			if (cc1 == null) {
				return null;
			}
			Integer dataLeng = data.split("\\s+").length;
			String checkS = data.split("\\s+")[5];
			Integer checkLeng = Integer.parseInt(checkS, 16) + 7;
			if (checkLeng != dataLeng) {
				return null;
			}
			cc1.setRefSwiState("on");

			String commandId = data.split("\\s+")[2];
			if (commandId.equals("81")) {
				
				try {
					Map<String,Object> mapCS=new HashMap<String,Object>();
					Long csTime=System.currentTimeMillis()-190000;
					mapCS.put("value1", cc1.getContainerId());
					mapCS.put("value2", csTime);
					CommandStore cs=daoService.selectCommandStoreByMap(mapCS);
					if(cs != null){
						cs.setStatus("Y");
						daoService.updateCommandStoreById(cs);
						
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				Ccdata cc = new Ccdata();

				Long currentLongTime = System.currentTimeMillis();
				DealDateData dealDateData = new DealDateData();
				String strFormatNowDate = dealDateData
						.getStringDate(currentLongTime);
				cc1.setNowLongTime(currentLongTime);
				cc1.setNowTime(strFormatNowDate);
				cc.setNowLongTime(currentLongTime);
				cc.setNowTime(strFormatNowDate);
				cc1.setWorkMode("无状态");
				cc.setDeviceId(cc1.getDeviceId());
				cc.setChillerType(cc1.getChillerType());
				cc.setCompanyId(cc1.getCompanyId());
				cc.setContainerId(cc1.getContainerId());
				String hexData = data.split("\\s+")[5];
				int decData = Integer.parseInt(hexData, 16);
				String overallStatus = data.split("\\s+")[6];

				if (overallStatus.equals("00")) {
					// 报警停机
				} else if (overallStatus.equals("01")) {
					// 启动/停止循环
				} else if (overallStatus.equals("02")) {
					// 睡眠模式
				} else if (overallStatus.equals("03")) {
					// 运行或启动
				} else if (overallStatus.equals("04")) {
					// PC模式
				} else if (overallStatus.equals("05")) {
					// 配置模式
				} else if (overallStatus.equals("06")) {
					// 数据记录器模式
				} else if (overallStatus.equals("07")) {
					// 闲置
				}
				if (decData >= 2) {
					String modeOperation = data.split("\\s+")[7];
					if (modeOperation.equals("00")) {// 由于未启用而关闭了
						cc.setWorkMode("引擎关闭");// 引擎关闭
						cc1.setWorkMode("引擎关闭");
					} else if (modeOperation.equals("01")) {// 加热
						cc.setWorkMode("冷机加热");// 冷机加热
						cc1.setWorkMode("冷机加热");
					} else if (modeOperation.equals("02")) {// 制冷
						cc.setWorkMode("冷机制冷");// 冷机制冷
						cc1.setWorkMode("冷机制冷");
					} else if (modeOperation.equals("03")) {// 空
						cc.setWorkMode("箱温维持");// null
						cc1.setWorkMode("箱温维持");// null

					} else if (modeOperation.equals("04")) {// 除霜
						cc.setWorkMode("除霜");// 除霜
						cc1.setWorkMode("除霜");
					} else if (modeOperation.equals("FF")) {// 未启用
						cc.setWorkMode("未启用");// 未启用
						cc1.setWorkMode("未启用");
					}
				}
				if (decData >= 5) {
					String engineStatus = data.split("\\s+")[10];
					String es = "00000000"
							+ Integer.toBinaryString((Integer.parseInt(
									engineStatus, 16)));
					String es8 = es.substring(es.length() - 8, es.length());
					String a01 = es8.substring(7, 8);
					String a12 = es8.substring(6, 7);
					String a23 = es8.substring(5, 6);
					if (a01.equals("0")) {// 发动机
						cc.setPowerType("发动机");
						cc1.setPowerType("发动机");
					} else {// 备用机
						cc.setPowerType("备用机");
						cc1.setPowerType("备用机");
					}
					if (a12.equals("0")) {// 电机启停运转模式
						cc.setRefRunMode("auto");
						cc1.setRefRunMode("auto");
					} else {// 电机连续运转模式
						cc.setRefRunMode("continuous");
						cc1.setRefRunMode("continuous");
					}
					if (a23.equals("0")) {// 电机运转速度--低速
						cc.setOperationMode("发电机低速运行");
						cc1.setOperationMode("发电机低速运行");
					} else {// 电机运转速度--高速
						cc.setOperationMode("发电机高速运行");
						cc1.setOperationMode("发电机高速运行");
					}
				}
				if (decData >= 6) {
					String alarmStatus = data.split("\\s+")[11];
					if (alarmStatus.equals("00")) {// 没有警报

					} else if (alarmStatus.equals("01")) {// 报警停机

					} else if (alarmStatus.equals("02")) {// 非关断报警中

					}
				}
				daoService.insertCcdata(cc);
				daoService.updateCcdataById1(cc1);
			} else if (commandId.equals("82")) {// 预警解析
				//判断是否为新版本冷箱
				if("1".equals(cc1.getCommunicationState())){
					//新版冷机----->新版冷机
					int alarmNums = Integer.parseInt(data.split("\\s+")[5]);
					if (dataLeng == (alarmNums + 7)) {
						for (int i = 0; i < alarmNums; i++) {
							String alarmNum = data.split("\\s+")[6 + i];
							Integer alarm = Integer.parseInt(alarmNum, 16);// 预警值
							Transformationnew tft = daoService
									.selectTransformationnewByAlarmNumber("" + alarm);
							OurCcdata1 ocd1 = daoService
									.selectOurCcdataByDeviceId1(sevNum);
							Long currentLongTime = System.currentTimeMillis();
							DealDateData dealDateData = new DealDateData();
							String alertStrFormatNowDate = dealDateData
									.GetNomalDate1(currentLongTime);
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("containerId", cc1.getContainerId());
							map.put("startTime", System.currentTimeMillis()
									- (24 * 60 * 60 * 1000));
							map.put("endTime", System.currentTimeMillis());
							map.put("alarm_num", alarm);
							if (tft != null
									&& ocd1 != null
									&& daoService.selectAlertOnByContainerId(map) == null) {
								Alert alert = new Alert();
								alert.setAlertContent(tft.getAlarmMessage2());
								alert.setContainerId(cc1.getContainerId());
								alert.setAlertLongTime(currentLongTime);
								alert.setAlertTime(alertStrFormatNowDate);
								alert.setLat(ocd1.getLat());
								alert.setLon(ocd1.getLon());
								alert.setReaded("no");
								alert.setAlertType("Carrier");
								alert.setAlarm_num(alarm);
								alert.setGpsPosition(ocd1.getGpsPosition());
								alert.setCompanyId(cc1.getCompanyId());
								daoService.insertAlert(alert);
							}
						}
					}
					
					
				}else{
					//老板冷机
					int alarmNums = Integer.parseInt(data.split("\\s+")[5]);
					if (dataLeng == (alarmNums + 7)) {
						for (int i = 0; i < alarmNums; i++) {
							String alarmNum = data.split("\\s+")[6 + i];
							Integer alarm = Integer.parseInt(alarmNum, 16);// 预警值
							Transformation tft = daoService
									.selectTransformationByAlarmNumber("" + alarm);
							OurCcdata1 ocd1 = daoService
									.selectOurCcdataByDeviceId1(sevNum);
							Long currentLongTime = System.currentTimeMillis();
							DealDateData dealDateData = new DealDateData();
							String alertStrFormatNowDate = dealDateData
									.GetNomalDate1(currentLongTime);
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("containerId", cc1.getContainerId());
							map.put("startTime", System.currentTimeMillis()
									- (24 * 60 * 60 * 1000));
							map.put("endTime", System.currentTimeMillis());
							map.put("alarm_num", alarm);
							if (tft != null
									&& ocd1 != null
									&& daoService.selectAlertOnByContainerId(map) == null) {
								Alert alert = new Alert();
								alert.setAlertContent(tft.getAlarmMessage2());
								alert.setContainerId(cc1.getContainerId());
								alert.setAlertLongTime(currentLongTime);
								alert.setAlertTime(alertStrFormatNowDate);
								alert.setLat(ocd1.getLat());
								alert.setLon(ocd1.getLon());
								alert.setReaded("no");
								alert.setAlertType("Carrier");
								alert.setAlarm_num(alarm);
								alert.setGpsPosition(ocd1.getGpsPosition());
								alert.setCompanyId(cc1.getCompanyId());
								daoService.insertAlert(alert);
							}
						}
					}
				}
			} else if (commandId.equals("87")) {// 设定室内温度值解析
				Ccdata cc = daoService.selectCcdataBycontainerId(cc1
						.getContainerId());
				if (cc == null) {
					return null;
				}
				String hexData = data.split("\\s+")[5];
				int decData = Integer.parseInt(hexData, 16);
				String getData1 = data.split("\\s+")[6];
				String getData2 = data.split("\\s+")[7];
				String getData0 = getData2 + getData1;
				String getData21 = getData2.substring(0, 1);
				String setpoint1;
				String setpoint2;
				String setpoint3;
				if (getData0.equals("FF7F")) {

				} else {
					if (getData21.equals("F")) {
						int data2 = 65536;
						int data1 = Integer.parseInt(getData0, 16);
						String getpoint1 = "" + (data1 - data2) / 32.0;
						String etem1 = getpoint1.split("\\.")[0];
						String etem2 = getpoint1.split("\\.")[1];
						setpoint1 = etem1 + "." + etem2.substring(0, 1);// 设定温度
						if(Double.parseDouble(setpoint1)>30 || Double.parseDouble(setpoint1)<-30){
							setpoint1=""+cc1.getTempSet();
						}
						cc.setTempSet(Double.parseDouble(setpoint1));
						cc1.setTempSet(Double.parseDouble(setpoint1));
					} else {
						int data1 = Integer.parseInt(getData0, 16);
						String getpoint1 = "" + (data1) / 32.0;
						String etem1 = getpoint1.split("\\.")[0];
						String etem2 = getpoint1.split("\\.")[1];
						setpoint1 = etem1 + "." + etem2.substring(0, 1);// 设定温度
						if(Double.parseDouble(setpoint1)>30 || Double.parseDouble(setpoint1)<-30){
							setpoint1=""+cc1.getTempSet();
						}
						cc.setTempSet(Double.parseDouble(setpoint1));
						cc1.setTempSet(Double.parseDouble(setpoint1));
					}
					daoService.updateCcdataById(cc);
					daoService.updateCcdataById1(cc1);
				}

				if (decData >= 4) {
					String getData3 = data.split("\\s+")[8];
					String getData4 = data.split("\\s+")[9];
					String getData = getData4 + getData3;
					String getData41 = getData4.substring(0, 1);
					if (getData.equals("FF7F")) {

					} else {
						if (getData41.equals("F")) {
							int data2 = 65536;
							int data1 = Integer.parseInt(getData, 16);
							String getpoint1 = "" + (data1 - data2) / 32.0;
							String etem1 = getpoint1.split("\\.")[0];
							String etem2 = getpoint1.split("\\.")[1];
							setpoint1 = etem1 + "." + etem2.substring(0, 1);// 设定温度
						} else {
							int data1 = Integer.parseInt(getData, 16);
							String getpoint1 = "" + (data1) / 32.0;
							String etem1 = getpoint1.split("\\.")[0];
							String etem2 = getpoint1.split("\\.")[1];
							setpoint2 = etem1 + "." + etem2.substring(0, 1);// 设定温度

						}
					}
				}
				if (decData >= 6) {
					String getData5 = data.split("\\s+")[10];
					String getData6 = data.split("\\s+")[11];
					String getData = getData6 + getData5;
					String getData61 = getData6.substring(0, 1);
					if (getData.equals("FF7F")) {

					} else {
						if (getData61.equals("F")) {
							int data2 = 65536;
							int data1 = Integer.parseInt(getData, 16);
							String getpoint1 = "" + (data1 - data2) / 32.0;
							String etem1 = getpoint1.split("\\.")[0];
							String etem2 = getpoint1.split("\\.")[1];
							setpoint1 = etem1 + "." + etem2.substring(0, 1);// 设定温度
						} else {
							int data1 = Integer.parseInt(getData, 16);
							String getpoint1 = "" + (data1) / 32.0;
							String etem1 = getpoint1.split("\\.")[0];
							String etem2 = getpoint1.split("\\.")[1];
							setpoint3 = etem1 + "." + etem2.substring(0, 1);// 设定温度

						}
					}
				}

			} else if (commandId.equals("88")) {// 温度传感器 环境温度、回风温度、供给温度（supply）

				Ccdata cc = daoService.selectCcdataBycontainerId(cc1
						.getContainerId());
				if (cc == null) {
					return null;
				}
				String hexData = data.split("\\s+")[5];
				int decData = Integer.parseInt(hexData, 16);
				String getData1 = data.split("\\s+")[6];
				String getData2 = data.split("\\s+")[7];
				String getData0 = getData2 + getData1;
				String getData21 = getData2.substring(0, 1);
				String setpoint1 = null;// 环境温度
				String setpoint2 = null;// 回风温度
				String setpoint3 = null;// 出风温度
				if (getData0.equals("FF7F")) {

				} else {
					if (getData21.equals("F")) {
						int data2 = 65536;
						int data1 = Integer.parseInt(getData0, 16);
						String getpoint1 = "" + (data1 - data2) / 32.0;
						String etem1 = getpoint1.split("\\.")[0];
						String etem2 = getpoint1.split("\\.")[1];
						setpoint1 = etem1 + "." + etem2.substring(0, 1);// 环境温度
						if(Double.parseDouble(setpoint1)>50 || Double.parseDouble(setpoint1)<-50){
							setpoint1=""+cc1.getEnviTemp();
						}
						cc.setEnviTemp(Double.parseDouble(setpoint1));
						cc1.setEnviTemp(Double.parseDouble(setpoint1));
					} else {
						int data1 = Integer.parseInt(getData0, 16);
						String getpoint1 = "" + (data1) / 32.0;
						String etem1 = getpoint1.split("\\.")[0];
						String etem2 = getpoint1.split("\\.")[1];
						setpoint1 = etem1 + "." + etem2.substring(0, 1);// 环境温度
						if(Double.parseDouble(setpoint1)>50 || Double.parseDouble(setpoint1)<-50){
							setpoint1=""+cc1.getEnviTemp();
						}
						cc.setEnviTemp(Double.parseDouble(setpoint1));
						cc1.setEnviTemp(Double.parseDouble(setpoint1));
					}
				}

				if (decData >= 4) {
					String getData3 = data.split("\\s+")[8];
					String getData4 = data.split("\\s+")[9];
					String getData = getData4 + getData3;
					String getData41 = getData4.substring(0, 1);
					if (getData.equals("FF7F")) {

					} else {
						if (getData41.equals("F")) {
							int data2 = 65536;
							int data1 = Integer.parseInt(getData, 16);
							String getpoint1 = "" + (data1 - data2) / 32.0;
							String etem1 = getpoint1.split("\\.")[0];
							String etem2 = getpoint1.split("\\.")[1];
							setpoint2 = etem1 + "." + etem2.substring(0, 1);// 回风温度
							if(Double.parseDouble(setpoint2)>50 || Double.parseDouble(setpoint2)<-50){
								setpoint2=""+cc1.getBackWindTemp();
							}
							cc.setBackWindTemp(Double.parseDouble(setpoint2));
							cc1.setBackWindTemp(Double.parseDouble(setpoint2));
						} else {
							int data1 = Integer.parseInt(getData, 16);
							String getpoint1 = "" + (data1) / 32.0;
							String etem1 = getpoint1.split("\\.")[0];
							String etem2 = getpoint1.split("\\.")[1];
							setpoint2 = etem1 + "." + etem2.substring(0, 1);// 回风温度
							if(Double.parseDouble(setpoint2)>50 || Double.parseDouble(setpoint2)<-50){
								setpoint2=""+cc1.getBackWindTemp();
							}
							cc.setBackWindTemp(Double.parseDouble(setpoint2));
							cc1.setBackWindTemp(Double.parseDouble(setpoint2));
						}
					}
				}
				if (decData >= 6) {
					String getData5 = data.split("\\s+")[10];
					String getData6 = data.split("\\s+")[11];
					String getData = getData6 + getData5;
					String getData61 = getData6.substring(0, 1);
					if (getData.equals("FF7F")) {

					} else {
						if (getData61.equals("F")) {
							int data2 = 65536;
							int data1 = Integer.parseInt(getData, 16);
							String getpoint1 = "" + (data1 - data2) / 32.0;
							String etem1 = getpoint1.split("\\.")[0];
							String etem2 = getpoint1.split("\\.")[1];
							setpoint3 = etem1 + "." + etem2.substring(0, 1);// 出风温度
							if(Double.parseDouble(setpoint3)>50 || Double.parseDouble(setpoint3)<-50){
								setpoint3=""+cc1.getChuWindTemp();
							}
							cc.setChuWindTemp(Double.parseDouble(setpoint3));
							cc1.setChuWindTemp(Double.parseDouble(setpoint3));
						} else {
							int data1 = Integer.parseInt(getData, 16);
							String getpoint1 = "" + (data1) / 32.0;
							String etem1 = getpoint1.split("\\.")[0];
							String etem2 = getpoint1.split("\\.")[1];
							setpoint3 = etem1 + "." + etem2.substring(0, 1);// 出风温度
							if(Double.parseDouble(setpoint3)>50 || Double.parseDouble(setpoint3)<-50){
								setpoint3=""+cc1.getChuWindTemp();
							}
							cc.setChuWindTemp(Double.parseDouble(setpoint3));
							cc1.setChuWindTemp(Double.parseDouble(setpoint3));
						}
					}
				}
				daoService.updateCcdataById(cc);
				daoService.updateCcdataById1(cc1);
			} else if (commandId.equals("8A")) {// 发动机传感器
				Ccdata cc = daoService.selectCcdataBycontainerId(cc1
						.getContainerId());
				if (cc == null) {
					return null;
				}
				String hexData = data.split("\\s+")[5];
				int decData = Integer.parseInt(hexData, 16);
				String getData1 = data.split("\\s+")[6];
				String getData2 = data.split("\\s+")[7];
				String getData0 = getData2 + getData1;
				int data1 = Integer.parseInt(getData0, 16);
				String getpoint1 = "" + data1 / 32.0;
				String etem1 = getpoint1.split("\\.")[0];
				String etem2 = getpoint1.split("\\.")[1];
				String batteryVol = etem1 + "." + etem2.substring(0, 1);// 设定电压
				if (batteryVol != null) {
					if(Double.parseDouble(batteryVol)>20 || Double.parseDouble(batteryVol)<0){
						batteryVol=""+cc1.getRefBatVol();
					}
					cc.setRefBatVol(Double.parseDouble(batteryVol));
					cc1.setRefBatVol(Double.parseDouble(batteryVol));
				}
				if (decData >= 3) {
					String fuelLevel = data.split("\\s+")[8];
					if (fuelLevel.equals("FF")) {// 油量无

					} else {//

					}
				}
				if (decData >= 4) {
					String doorSwitch = data.split("\\s+")[9];
					if (doorSwitch.equals("FF")) {// 门开关无

					} else if (doorSwitch.equals("00")) {// 门关
						cc.setBackDoorState("门关");
						cc1.setBackDoorState("门关");
					} else if (doorSwitch.equals("01")) {// 门开
						cc.setBackDoorState("门开");
						cc1.setBackDoorState("门开");
					}
				}
				if (decData >= 5) {
					String remoteSwitch = data.split("\\s+")[10];
					if (remoteSwitch.equals("FF")) {// 遥控开关-无

					} else if (remoteSwitch.equals("00")) {// 遥控开关-关

					} else if (remoteSwitch.equals("01")) {// 遥控开关-开

					}
				}
				daoService.updateCcdataById(cc);
				daoService.updateCcdataById1(cc1);
			} else if (commandId.equals("8B")) {// 标准电度表
				Ccdata cc = daoService.selectCcdataBycontainerId(cc1
						.getContainerId());
				if (cc == null) {
					return null;
				}
				String hexData = data.split("\\s+")[5];
				int decData = Integer.parseInt(hexData, 16);
				String totalEngine = null;// 电机运转总时间
				String totalStandby = null;// 总待机时间
				String totalSwitch = null;// 总开关时间
				String totalEngine1 = data.split("\\s+")[6];
				String totalEngine2 = data.split("\\s+")[7];
				String totalEngine3 = data.split("\\s+")[8];
				String totalEngine4 = data.split("\\s+")[9];
				if (totalEngine1.equals("00")) {
					totalEngine = "0";
				} else {
					totalEngine = totalEngine1;
					if (totalEngine2.equals("00")) {

					} else {
						totalEngine = totalEngine1 + totalEngine2;
						if (totalEngine3.equals("00")) {

						} else {
							totalEngine = totalEngine1 + totalEngine2
									+ totalEngine3;
							if (totalEngine4.equals("00")) {

							} else {
								totalEngine = totalEngine1 + totalEngine2
										+ totalEngine3 + totalEngine4;
							}
						}
					}

				}

				if (decData >= 8) {
					String totalStandby1 = data.split("\\s+")[10];
					String totalStandby2 = data.split("\\s+")[11];
					String totalStandby3 = data.split("\\s+")[12];
					String totalStandby4 = data.split("\\s+")[13];
					if (totalStandby1.equals("00")) {
						totalStandby = "0";
					} else {
						totalStandby = totalStandby1;
						if (totalStandby2.equals("00")) {

						} else {
							totalStandby = totalStandby1 + totalStandby2;
							if (totalStandby3.equals("00")) {

							} else {
								totalStandby = totalStandby1 + totalStandby2
										+ totalStandby3;
								if (totalStandby4.equals("00")) {

								} else {
									totalStandby = totalStandby1
											+ totalStandby2 + totalStandby3
											+ totalStandby4;
								}
							}
						}

					}
				}

				if (decData >= 12) {
					String totalSwitch1 = data.split("\\s+")[14];
					String totalSwitch2 = data.split("\\s+")[15];
					String totalSwitch3 = data.split("\\s+")[16];
					String totalSwitch4 = data.split("\\s+")[17];
					if (totalSwitch1.equals("00")) {
						totalSwitch = "0";
					} else {
						totalSwitch = totalSwitch1;
						if (totalSwitch2.equals("00")) {

						} else {
							totalSwitch = totalSwitch1 + totalSwitch2;
							if (totalSwitch3.equals("00")) {

							} else {
								totalSwitch = totalSwitch1 + totalSwitch2
										+ totalSwitch3;
								if (totalSwitch4.equals("00")) {

								} else {
									totalSwitch = totalSwitch1 + totalSwitch2
											+ totalSwitch3 + totalSwitch4;
								}
							}
						}
					}
				}
				if (StringUtil.isNotEmpty(totalEngine)) {
					Double engineTime = (double) Integer.parseInt(totalEngine,
							16);
					cc.setEngRunTime(engineTime);
					cc1.setEngRunTime(engineTime);
				}
				if (StringUtil.isNotEmpty(totalStandby)) {
					Integer standbyTime = Integer.parseInt(totalStandby, 16);
				}
				if (StringUtil.isNotEmpty(totalSwitch)) {
					Integer switchTime = Integer.parseInt(totalSwitch, 16);
				}
				daoService.updateCcdataById(cc);
				daoService.updateCcdataById1(cc1);
			}

			return null;

		} catch (Exception e) {
			System.out.println("凯里数据解析异常!");
			e.printStackTrace();
		}
		return sevNum;

	}
}
