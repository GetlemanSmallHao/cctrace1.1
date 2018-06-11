package com.cctrace.socketServers.dataHandle;

import java.util.Date;
import java.util.List;

import com.cctrace.entity.Alert;
import com.cctrace.entity.Geomessage;
import com.cctrace.entity.OurCcdata;
import com.cctrace.entity.OurCcdata1;
import com.cctrace.entity.Rail;
import com.cctrace.service.DaoService;
import com.cctrace.socketServers.util.DealDateData;
import com.cctrace.utils.ChangeRealLocation;
import com.cctrace.utils.DateUtil;
import com.cctrace.utils.DistanceAndPositioinUtil;

public class OurData {

	public DaoService daoService;

	public OurData(DaoService daoService) {
		this.daoService = daoService;
	}

	public String ourCcdata(String data) {
		try {
			DealDateData ddd = new DealDateData();
			String[] datas = data.split("\\s+");
			Integer leng = datas.length;
			String deviceId = data.split("\\s+")[0];// 设备号
			OurCcdata1 ocd1 = daoService.selectOurCcdataByDeviceId1(deviceId);
			if (ocd1 == null) {
				return null;
			}

			String containerId = ocd1.getContainerId();
			OurCcdata ocd = new OurCcdata();
			ocd.setContainerId(containerId);
			ocd.setCompanyId(1);
			ocd.setDeviceId(deviceId);

			Long currentLongTime = System.currentTimeMillis();
			if (containerId.equals("CICU9863502")) {
				int ac = 0;
				System.out.println(ac);
			}

			DealDateData dealDateData = new DealDateData();
			String strFormatNowDate = dealDateData
					.getStringDate(currentLongTime);
			ocd.setNowLongTime(currentLongTime);
			ocd.setNowTime(strFormatNowDate);
			ocd1.setNowLongTime(currentLongTime);
			ocd1.setNowTime(strFormatNowDate);

			/*
			 * String dateTime=data.split("\\s+")[2];//gps接收时间 String
			 * dateTime1=dateTime.split("\\,")[0];//去除时差的日期时间 int
			 * zone=Integer.parseInt(dateTime.split("\\,")[1])/4;//需要增加的时差 long
			 * longDateTime=System.currentTimeMillis();//2017/04/10
			 * 09:02:24转为时间戳 long longZone=zone*3600000;//时差对应的毫秒数，1h=3600000ms
			 * long realLongTime=longDateTime+longZone;
			 * ocd.setReceiveLongTime(realLongTime); String
			 * realTime=ddd.getStringDate(realLongTime);
			 * ocd.setReceiveTime(realTime);
			 */

			String gps = data.split("\\s+")[3];
			String[] gpsData = gps.split("\\,");
			Integer gpsLeng = gpsData.length;
			if (gpsLeng == 4) {
				String lat = gps.split("\\,")[0]; // 3444.40000//经度
				if (!lat.equals("000000000")) {
					String latZS = lat.split("\\.")[0]; // 3443
					String latXS = lat.split("\\.")[1];// 27555
					String latZS0 = latZS.substring(0, latZS.length() - 2);// 34
					String latZS1 = latZS.substring(latZS.length() - 2,
							latZS.length());// 43
					String hm = latZS1 + "." + latXS;// 43.27555
					double xs = Double.parseDouble(hm) / 60;
					double realLat = Double.parseDouble(latZS0) + xs;
					lat = "" + realLat;
					ocd.setLat(realLat);
					ocd1.setLat(realLat);
				} else {

				}

				String lon = gps.split("\\,")[2];// 11348.15000//纬度
				if (!lon.equals("0000000000")) {
					String lonZS = lon.split("\\.")[0]; // 11347
					String lonXS = lon.split("\\.")[1];// 31969
					String lonZS0 = lonZS.substring(0, lonZS.length() - 2);// 113
					String lonZS1 = lonZS.substring(lonZS.length() - 2,
							lonZS.length());// 47
					String hm = lonZS1 + "." + lonXS;// 47.31969
					double xs = Double.parseDouble(hm) / 60;
					double realLon = Double.parseDouble(lonZS0) + xs;
					lon = "" + realLon;
					ocd.setLon(realLon);
					ocd1.setLon(realLon);
				} else {

				}
				Double oilLevel = Double.parseDouble(data.split("\\s+")[4]);// 油位
				ocd.setOilLevel(oilLevel);
				ocd1.setOilLevel(oilLevel);
				Double boxHum = Double.parseDouble(data.split("\\s+")[5]);// 箱内湿度
				ocd.setBoxHum(boxHum);
				ocd1.setBoxHum(boxHum);
				Double tailBoxTemp = Double.parseDouble(data.split("\\s+")[6]);// 尾部箱温
				ocd.setTailBoxTemp(tailBoxTemp);
				ocd1.setTailBoxTemp(tailBoxTemp);
				Double oilTemp = Double.parseDouble(data.split("\\s+")[7]);// 油温温度
				ocd.setOilTemp(oilTemp);
				ocd1.setOilTemp(oilTemp);
				Double gpsPower = Double.parseDouble(data.split("\\s+")[8]);// GPS电量
				ocd.setGpsPower(gpsPower);
				ocd1.setGpsPower(gpsPower);
				if (leng > 9) {
					try {
						String backDoorState = data.split("\\s+")[9];
						if ("0".equals(backDoorState)) {
							backDoorState = "close";
						} else if ("1".equals(backDoorState)) {
							backDoorState = "open";
						}
						ocd.setBackDoorState(backDoorState);
						ocd1.setBackDoorState(backDoorState);
					} catch (Exception e) {
						System.out.println("自研数据门开关判断异常！");
						e.printStackTrace();
					}
				}
				GpsPosition gpsPosition = new GpsPosition();
				String position = gpsPosition.getLocationMsg(lat, lon);
				List<Rail> rails = null;
				rails = daoService.getRailsByCompanyId(1);
				Rail nowRail = null;
				// 判断该点的位置
				try {
					if (rails != null && rails.size() != 0) {
						boolean flag = true;
						double minlen = 0;
						Integer railId = null;
						Integer previousRialId = ocd1.getRailId();
						for (Rail rail : rails) {
							double lon1 = rail.getRailLon();
							double lan1 = rail.getRailLat();
							double lon2 = Double.parseDouble(lon);
							double lan2 = Double.parseDouble(lat);

							List<Double> listLonLan = ChangeRealLocation
									.SendGET(lan2, lon2);
							lan2 = listLonLan.get(0);
							lon2 = listLonLan.get(1);

							double ten = DistanceAndPositioinUtil.getDistance(
									lon1, lan1, lon2, lan2);
							if (ten < rail.getRadius()) {
								if (flag) {
									minlen = ten;
									flag = false;
									position = rail.getRailName();
									railId = rail.getId();
									nowRail = rail;
								} else {
									if (ten < minlen)
										;
									minlen = ten;
									position = rail.getRailName();
									railId = rail.getId();
									nowRail = rail;
								}
							}
						}

						// 进入围栏
						if (railId != null && nowRail != null) {
							ocd.setRailId(railId);
							ocd1.setRailId(railId);
							Geomessage geo = new Geomessage();
							Geomessage geo1 = new Geomessage();
							// 之前没有在任何围栏内
							if (previousRialId == null || previousRialId == -1) {
								geo.setAlertTime(DateUtil
										.getNowDateStringByAlert());
								Date date = new Date();
								geo.setAlertLongTime(date.getTime() + "");
								geo.setAlertType("地理围栏");
								geo.setContainerId(ocd1.getContainerId());
								geo.setAlertContent("进入"
										+ nowRail.getRailName());
								geo.setLat(Double.parseDouble(lat));
								geo.setLon(Double.parseDouble(lon));
								geo.setGpsPosition(nowRail.getRailName());
								geo.setReaded("no");
								geo.setCompanyId(ocd1.getCompanyId());
								daoService.insertGeoMessage(geo);
							} else {
								// 之前在某围栏内
								if (previousRialId.toString().equals(
										railId.toString())) {

								} else {
									geo.setAlertTime(DateUtil
											.getNowDateStringByAlert());
									Date date = new Date();
									geo.setAlertLongTime(date.getTime() + "");
									geo.setAlertType("地理围栏");
									geo.setContainerId(ocd1.getContainerId());
									geo.setAlertContent("进入"
											+ nowRail.getRailName());
									geo.setLat(Double.parseDouble(lat));
									geo.setLon(Double.parseDouble(lon));
									geo.setCompanyId(ocd1.getCompanyId());
									geo.setReaded("no");
									Rail preRail = daoService
											.getRailById(previousRialId);

									if (preRail == null) {

									} else {
										geo1.setAlertTime(DateUtil
												.getNowDateStringByAlert());
										Date date1 = new Date();
										geo1.setAlertLongTime(date1.getTime()
												+ "");
										geo1.setAlertType("地理围栏");
										geo1.setContainerId(ocd1
												.getContainerId());
										geo1.setAlertContent("驶出"
												+ preRail.getRailName());
										geo1.setLat(Double.parseDouble(lat));
										geo1.setLon(Double.parseDouble(lon));
										geo1.setReaded("no");
										geo1.setGpsPosition(nowRail
												.getRailName());
										geo1.setCompanyId(ocd1.getCompanyId());
										daoService.insertGeoMessage(geo1);
									}
									geo.setGpsPosition(nowRail.getRailName());
									daoService.insertGeoMessage(geo);
								}
							}
							position = nowRail.getRailName();
						} else {
							// 没有进入围栏
							Alert alert1 = new Alert();
							Geomessage geo1 = new Geomessage();
							if (previousRialId == null) {
							} else {
								if (previousRialId == -1) {
								} else {
									Rail preRail = daoService
											.getRailById(previousRialId);
									if (preRail == null) {

									} else {
										geo1.setAlertTime(DateUtil
												.getNowDateStringByAlert());
										Date date1 = new Date();
										geo1.setAlertLongTime(date1.getTime()
												+ "");
										geo1.setAlertType("地理围栏");
										geo1.setContainerId(ocd1
												.getContainerId());
										geo1.setAlertContent("驶出"
												+ preRail.getRailName());
										geo1.setLat(Double.parseDouble(lat));
										geo1.setLon(Double.parseDouble(lon));
										geo1.setGpsPosition(position);
										geo1.setReaded("no");
										geo1.setCompanyId(ocd1.getCompanyId());
										ocd1.setRailId(-1);
										daoService.insertAlert(alert1);
									}

								}
							}
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				if (position != null) {
					ocd.setGpsPosition(position);
					ocd1.setGpsPosition(position);
				}
			}

			daoService.insertOurCcdata(ocd);
			daoService.updateOurCcdataById1(ocd1);

			return null;
		} catch (Exception e) {
			System.out.println("自研数据解析异常");
			e.printStackTrace();
		}
		return null;

	}
}
