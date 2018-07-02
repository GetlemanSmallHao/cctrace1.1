package com.cctrace.socketServers.delData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cctrace.entity.Alert;
import com.cctrace.entity.Ccdata1;
import com.cctrace.entity.CommandStore;
import com.cctrace.entity.Container;
import com.cctrace.entity.ErrorData;
import com.cctrace.service.DaoService;
import com.cctrace.socketServers.util.DealDateData;

public class DealTXData {

	public DaoService daoService;

	public DealTXData(DaoService daoService) {
		this.daoService = daoService;
	}

	public int dealTXData(String txData, String sevNum) {
		try {
			Long currentLongTime = System.currentTimeMillis();
			DealDateData dealDateData = new DealDateData();
			String alertStrFormatNowDate = dealDateData
					.getStringDate(currentLongTime);
			ErrorData errorData = new ErrorData();
			errorData.setContent(txData);
			errorData.setReceiveTime(alertStrFormatNowDate);
			errorData.setReceiveLongTime("" + currentLongTime);
			errorData.setType(sevNum);
			daoService.insertErrorData(errorData);
			int returndata = 0;
			return returndata;
		} catch (Exception e) {
			System.out.println("接受数据异常！");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public String commandStore(String data, String sevNum) {

		try {
			Container cn = daoService.selectContainerByDeviceId(sevNum);
			if (cn == null) {
				return null;
			}
			String containerId = cn.getContainerId();
			CommandStore cs = daoService.selectCommandStoreDesc(containerId);
			Ccdata1 cc = daoService.selectLastCcdataByContainerId1(containerId);
			String command = cs.getCommand();
			String display = cs.getDisplay();
			String value = cs.getValue();
			String status = cs.getStatus();
			if (cs == null) {
				return null;
			}
			String data1 = data.substring(2, 4);
			String data2 = data.substring(4, 6);

			if(status != "Y"){
				if (data1.equals("CD") && data2.equals("00")
						&& command.equals("remoteSwiMac")) {
					cc.setRefSwiState(value);
					daoService.updateCcdataById1(cc);
				} else if (data1.equals("CE") && data2.equals("00")
						&& command.equals("refRunMode")) {
					cc.setRefRunMode(value);
					daoService.updateCcdataById1(cc);

				} else if (data1.equals("CE") && data2.equals("00")
						&& command.equals("temSet")) {
					Double val = Double.parseDouble(value);
					cc.setTempSet(val);
					daoService.updateCcdataById1(cc);
				} else if (data1.equals("CE") && data2.equals("00")
						&& command.equals("clearAlert")) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("containerId", cn.getContainerId());
					map.put("startTime", System.currentTimeMillis()
							- (24 * 60 * 60 * 1000));
					map.put("endTime", System.currentTimeMillis());
					List<Alert> listA = daoService.selectAllAlertOnByContainerId(map);
					if (listA != null) {
						for (int i = 0; i < listA.size(); i++) {
							listA.get(i).setReaded("yes");
							daoService.updateAlertById(listA.get(i));
						}
					}

				}

				if (data2.equals("00")) {
					cs.setStatus("Y");
					cs.setDisplay(value);
					daoService.updateCommandStoreById(cs);
				} else {
					if (cs.getStatus().equals("Y")) {

					} else {
						cs.setStatus("N");
						// cs.setDisplay(value);
						daoService.updateCommandStoreById(cs);
					}

				}
			}
			return null;
		} catch (NumberFormatException e) {
			System.out.println("控制命令处理异常!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public String communicationMethod(String content) {
		try {
			String sevNum = content.substring(0, 15);
			Ccdata1 cc1 = daoService.selectCcdataByDeviceId1(sevNum);
			if (cc1 != null) {
				String refSwiState = cc1.getRefSwiState();
				Long currentLongTime = System.currentTimeMillis();
				DealDateData dealDateData = new DealDateData();
				String strFormatNowDate = dealDateData
						.getStringDate(currentLongTime);
				cc1.setReceiveLongTime(currentLongTime);
				cc1.setReceiveTime(strFormatNowDate);
				daoService.updateCcdataById1(cc1);
				return refSwiState;
			}
			return null;
		} catch (Exception e) {
			System.out.println("心跳处理失败!");
			e.printStackTrace();
		}
		return null;

	}

	public Map<String, String> communicationMethod1(String content) {
		try {
			String sevNum = content.substring(0, 15);
			Ccdata1 cc1 = daoService.selectCcdataByDeviceId1(sevNum);
			Map<String, String> map = new HashMap<String, String>();
			if (cc1 != null) {
				String refSwiState = cc1.getRefSwiState();
				String chillerType = cc1.getChillerType();
				Long currentLongTime = System.currentTimeMillis();
				DealDateData dealDateData = new DealDateData();
				String strFormatNowDate = dealDateData
						.getStringDate(currentLongTime);
				cc1.setReceiveLongTime(currentLongTime);
				cc1.setReceiveTime(strFormatNowDate);
				daoService.updateCcdataById1(cc1);
				if (refSwiState == null) {
					refSwiState = "off";
				}
				map.put("chillerType", chillerType);
				map.put("refSwiState", refSwiState);

				return map;
			}
			return null;
		} catch (Exception e) {
			System.out.println("心跳处理失败!");
			e.printStackTrace();
		}
		return null;

	}

}
