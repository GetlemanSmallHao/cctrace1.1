package com.cctrace.socketServers.dataHandle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cctrace.entity.Alert;
import com.cctrace.entity.Ccdata;
import com.cctrace.entity.Ccdata1;
import com.cctrace.entity.Container;
import com.cctrace.entity.LwAlarm;
import com.cctrace.entity.OurCcdata;
import com.cctrace.entity.OurCcdata1;
import com.cctrace.service.DaoService;

/**
 * 
 * @author Administrator <a href="http://baidu.com">百度</a>
 */

public class DataHandle {

	public DaoService daoService;

	public DataHandle(DaoService daoService) {
		this.daoService = daoService;
	}

	public void handleData(String data, String deviceId) {
		try {
			Double refBatVol = null;// 电瓶电压
			Double enviTemp = null;// 环境温度
			Double vecRunTime = null;// 车辆运行时长
			Double engRunTime = null;// 发动机运行时长
			Long currentLongTime = System.currentTimeMillis();
			Date nowTime = new Date(currentLongTime);
			SimpleDateFormat sdFormatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:dd");
			String alertStrFormatNowDate = sdFormatter.format(nowTime);
			String pId = data.split("\\s+")[1];
			Ccdata ccdata = daoService.selectCcdataByDeviceId(deviceId);
			OurCcdata1 ocd1 = daoService.selectOurCcdataByDeviceId1(deviceId);
			String position = null;
			if (ocd1 != null) {
				position = ocd1.getGpsPosition();
			}
			Container container = daoService
					.selectContainerByDeviceId(deviceId);
			Ccdata1 cc1 = daoService.selectCcdataByDeviceId1(deviceId);
			if (pId.equals("A8") && container != null && cc1 != null) {// 电瓶电压
				String containerId = container.getContainerId();
				String dp1 = data.split("\\s+")[2];
				String dp2 = data.split("\\s+")[3];
				if (dp1.equals("FF")) {
					dp1 = "";
				}
				String dp3 = dp1 + dp2;
				double dp0 = Integer.parseInt(dp3, 16);
				String dpS = "" + (double) (dp0 * 0.05) + "00";
				int dpS1 = Integer.parseInt(dpS.split("\\.")[0]);
				String dpS2 = dpS.split("\\.")[1];
				String dpS287 = dpS2.substring(0, 2);
				String dp = dpS1 + "." + dpS287;
				refBatVol = Double.parseDouble(dp);
				if(refBatVol>18 || refBatVol<0){
					refBatVol=cc1.getRefBatVol();
				}
				ccdata.setRefBatVol(refBatVol);
				cc1.setRefBatVol(refBatVol);
				Double minRefBatVol = container.getMinRefBatVol();
				Double maxRefBatVol = container.getMaxRefBatVol();
				if (minRefBatVol == null || maxRefBatVol == null) {

				} else {
					Double lat = ccdata.getLat();
					Double lon = ccdata.getLon();
					if (minRefBatVol >= refBatVol) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("containerId", container.getContainerId());
						map.put("startTime", System.currentTimeMillis()
								- (24 * 60 * 60 * 1000));
						map.put("endTime", System.currentTimeMillis());
						map.put("alarm_num", 10021);
						if (daoService.selectAlertOnByContainerId(map) == null) {
							Alert alert = new Alert();
							alert.setAlertLongTime(currentLongTime);
							alert.setAlertTime(alertStrFormatNowDate);
							alert.setReaded("no");
							alert.setLat(lat);
							alert.setLon(lon);
							alert.setContainerId(containerId);
							alert.setAlertType("冷机电压预警");
							alert.setAlertContent("实际冷机电压低于设置冷机电压");
							alert.setGpsPosition(position);
							alert.setAlarm_num(10021);
							alert.setCompanyId(cc1.getCompanyId());
//							daoService.insertAlert(alert);
						}

					} else if (maxRefBatVol <= refBatVol) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("containerId", container.getContainerId());
						map.put("startTime", System.currentTimeMillis()
								- (24 * 60 * 60 * 1000));
						map.put("endTime", System.currentTimeMillis());
						map.put("alarm_num", 10022);
						if (daoService.selectAlertOnByContainerId(map) == null) {
							Alert alert = new Alert();
							alert.setAlertLongTime(currentLongTime);
							alert.setAlertTime(alertStrFormatNowDate);
							alert.setReaded("no");
							alert.setLat(lat);
							alert.setLon(lon);
							alert.setContainerId(containerId);
							alert.setAlertType("冷机电压预警");
							alert.setAlertContent("实际冷机电压高于设置冷机电压");
							alert.setGpsPosition(position);
							alert.setAlarm_num(10022);
							alert.setCompanyId(cc1.getCompanyId());
//							daoService.insertAlert(alert);
						}
					}
				}
				cc1.setRefSwiState("on");
				daoService.updateCcdataById(ccdata);
				daoService.updateCcdataById1(cc1);
			} else if (pId.equals("AB") && container != null && ccdata != null
					&& cc1 != null) {// 环境温度
				String containerId = container.getContainerId();
				String et1 = data.split("\\s+")[2];
				String et11 = et1.substring(0, 1);
				String et2 = data.split("\\s+")[3];
				String et3 = et1 + et2;
				double et0 = Integer.parseInt(et3, 16);
				if (et11.equals("F")) {
					et0 = Integer.parseInt(et3, 16) - 65536;
				}

				String etS = "" + (((et0 * 0.25 - 32)) / 1.8) + "00";
				int etS1 = Integer.parseInt(etS.split("\\.")[0]);
				String etS2 = etS.split("\\.")[1];
				String etS287 = etS2.substring(0, 2);
				String et = etS1 + "." + etS287;
				enviTemp = Double.parseDouble(et);
				if(enviTemp>50 || enviTemp<-50){
					enviTemp=cc1.getEnviTemp();
				}
				
				ccdata.setEnviTemp(enviTemp);
				cc1.setEnviTemp(enviTemp);
				Double minEnviTemp = container.getMinEnviTemp();
				Double maxEnviTemp = container.getMaxEnviTemp();

				if (minEnviTemp == null || maxEnviTemp == null) {

				} else {
					Double lat = ccdata.getLat();
					Double lon = ccdata.getLon();
					if (minEnviTemp >= enviTemp) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("containerId", container.getContainerId());
						map.put("startTime", System.currentTimeMillis()
								- (24 * 60 * 60 * 1000));
						map.put("endTime", System.currentTimeMillis());
						map.put("alarm_num", 10031);
						if (daoService.selectAlertOnByContainerId(map) == null) {
							Alert alert = new Alert();
							alert.setAlertLongTime(currentLongTime);
							alert.setAlertTime(alertStrFormatNowDate);
							alert.setReaded("no");
							alert.setLat(lat);
							alert.setLon(lon);
							alert.setContainerId(containerId);
							alert.setAlertType("冷机环境温度预警");
							alert.setAlertContent("实际冷机环境温度低于设置冷机环境温度");
							alert.setGpsPosition(position);
							alert.setAlarm_num(10031);
							alert.setCompanyId(cc1.getCompanyId());
//							daoService.insertAlert(alert);
						}
					} else if (maxEnviTemp <= enviTemp) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("containerId", container.getContainerId());
						map.put("startTime", System.currentTimeMillis()
								- (24 * 60 * 60 * 1000));
						map.put("endTime", System.currentTimeMillis());
						map.put("alarm_num", 10032);
						if (daoService.selectAlertOnByContainerId(map) == null) {
							Alert alert = new Alert();
							alert.setAlertLongTime(currentLongTime);
							alert.setAlertTime(alertStrFormatNowDate);
							alert.setReaded("no");
							alert.setLat(lat);
							alert.setLon(lon);
							alert.setContainerId(containerId);
							alert.setAlertType("冷机环境温度预警");
							alert.setGpsPosition(position);
							alert.setAlertContent("实际冷机环境温度高于设置冷机环境温度");
							alert.setAlarm_num(10032);
							alert.setCompanyId(cc1.getCompanyId());
//							daoService.insertAlert(alert);
						}
					}
				}
				cc1.setRefSwiState("on");
				daoService.updateCcdataById(ccdata);
				daoService.updateCcdataById1(cc1);

			} else if (pId.equals("F6") && container != null && ccdata != null
					&& cc1 != null) {// 车辆运行时长
				String vt1 = data.split("\\s+")[2];
				String vt2 = data.split("\\s+")[3];
				String vt3 = data.split("\\s+")[4];
				String vt4 = data.split("\\s+")[5];
				if (vt1.equals("FF")) {
					vt1 = "";
				}
				if (vt2.equals("FF")) {
					vt2 = "";
				}
				if (vt3.equals("FF")) {
					vt3 = "";
				}
				if (vt4.equals("FF")) {
					vt4 = "";
				}
				String vt5 = vt1 + vt2 + vt3 + vt4;
				double vt0 = Integer.parseInt(vt5, 16);
				String vtS = "" + (double) (vt0 * 0.05) + "00";
				int vtS1 = Integer.parseInt(vtS.split("\\.")[0]);
				String vtS2 = vtS.split("\\.")[1];
				String vtS287 = vtS2.substring(0, 2);
				String vt = vtS1 + "." + vtS287;
				vecRunTime = Double.parseDouble(vt);
				ccdata.setVecRunTime(vecRunTime);
				cc1.setVecRunTime(vecRunTime);
				cc1.setRefSwiState("on");
				daoService.updateCcdataById(ccdata);
				daoService.updateCcdataById1(cc1);
			} else if (pId.equals("F7") && container != null && ccdata != null
					&& cc1 != null) {// 发动机运行时长

				String eg1 = data.split("\\s+")[2];
				String eg2 = data.split("\\s+")[3];
				String eg3 = data.split("\\s+")[4];
				String eg4 = data.split("\\s+")[5];
				if (eg1.equals("FF")) {
					eg1 = "";
				}
				if (eg2.equals("FF")) {
					eg2 = "";
				}
				if (eg3.equals("FF")) {
					eg3 = "";
				}
				if (eg4.equals("FF")) {
					eg4 = "";
				}
				String eg5 = eg1 + eg2 + eg3 + eg4;
				double eg0 = Integer.parseInt(eg5, 16);
				String egS = "" + (double) (eg0 * 0.05) + "00";
				int egS1 = Integer.parseInt(egS.split("\\.")[0]);
				String egS2 = egS.split("\\.")[1];
				String egS287 = egS2.substring(0, 2);
				String eg = egS1 + "." + egS287;
				engRunTime = Double.parseDouble(eg);
				ccdata.setEngRunTime(engRunTime);
				cc1.setEngRunTime(engRunTime);
				cc1.setRefSwiState("on");
				daoService.updateCcdataById(ccdata);
				daoService.updateCcdataById1(cc1);

			} else if (pId.equals("CF") && container != null) {// 预警信息
				int alert = 0;
				Alert at = new Alert();
				alert = Integer.parseInt(data.split("\\s+")[2]);
				OurCcdata ourCcdata = daoService
						.selectOurCcdataByDeviceId(deviceId);
				Double lat = null;
				Double lon = null;

				if (ourCcdata != null) {
					lat = ourCcdata.getLat();
					lon = ourCcdata.getLon();
				}

				if (alert == 0 || container == null) {

				} else {
					for (int i = 0; i < alert; i++) {
						String containerId = container.getContainerId();
						String a0 = data.split("\\s+")[2 * i + 3];
						int a = Integer.parseInt(a0, 16);
						String b0 = data.split("\\s+")[2 * i + 4];
						String bt = "00000000"
								+ Integer.toBinaryString(Integer.parseInt(b0,
										16));
						String b8 = bt.substring(bt.length() - 8, bt.length());
						String b87 = b8.substring(0, 2);
						int b = 0;
						if (b87.equals("00")) {
							b = 0;
						} else if (b87.equals("01")) {
							b = 1;
						} else if (b87.equals("10")) {
							b = 2;
						} else if (b87.equals("11")) {
							b = 3;
						}
						Integer ab = a + b * 256;
						LwAlarm lwAlarm = daoService
								.selectLwAlarmByAlarm_num(ab);
						if (lwAlarm != null) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("containerId", container.getContainerId());
							map.put("startTime", System.currentTimeMillis()
									- (24 * 60 * 60 * 1000));
							map.put("endTime", System.currentTimeMillis());
							map.put("alarm_num", ab);
							if (daoService.selectAlertOnByContainerId(map) == null) {
								at.setAlertContent(lwAlarm.getChi_con());
								at.setContainerId(containerId);
								at.setAlertLongTime(currentLongTime);
								at.setAlertTime(alertStrFormatNowDate);
								at.setLat(lat);
								at.setLon(lon);
								at.setReaded("no");
								at.setAlertType("TKalarm");
								at.setAlarm_num(ab);
								at.setGpsPosition(position);
								at.setCompanyId(cc1.getCompanyId());
								daoService.insertAlert(at);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("冷机数据解析异常！");
			e.printStackTrace();
		}

	}
}
