package com.cctrace.socketServers.delData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cctrace.entity.Alert;
import com.cctrace.entity.Ccdata1;
import com.cctrace.entity.CommandStore;
import com.cctrace.entity.Container;
import com.cctrace.service.DaoService;

public class DealCarrierData {
	public DaoService daoService;

	public DealCarrierData(DaoService daoService) {
		this.daoService = daoService;
	}

	/**
	 * 控制指令返回数据的处理工作
	 * 
	 * @param data
	 * @param sevNum
	 * @return
	 */
	public String commandStore(String data, String sevNum) {
		try {
			Container cn = daoService.selectContainerByDeviceId(sevNum);

			String containerId = cn.getContainerId();
			CommandStore cs = daoService.selectCommandStoreDesc(containerId);
			Ccdata1 cc = daoService.selectLastCcdataByContainerId1(containerId);
			String command = cs.getCommand();
			String value = cs.getValue();
			String status = cs.getStatus();
			// 过滤无效数据
			if (cs == null || cn == null) {
				return null;
			}

			// 过滤数据前导0问题，提取有效数据部分
			String realData = getRealReturnData(data);
			realData = removeSSS(realData);
			String data1 = realData.substring(4, 6);
			String data2 = "";
			
			if(status != "Y"){
			
				// 冷机运行模式返回
				if ("A1".equals(data1) && command.equals("refRunMode")) {
					cc.setRefRunMode(value);
					int assa = daoService.updateCcdataById1(cc);
					data2 = realData.substring(realData.length() - 4,
							realData.length() - 2);
				}// 如果是温度设定
				else if (data1.equals("A0") && command.equals("temSet")) {
					Double val = Double.parseDouble(value);
					cc.setTempSet(val);
					daoService.updateCcdataById1(cc);
					data2 = realData.substring(realData.length() - 8,
							realData.length() - 6);
				}// 如果是新风门设定
				else if (data1.equals("AB") && command.equals("refXFSwiState")) {
					cc.setRefXFSwiState(value);
					daoService.updateCcdataById1(cc);
					data2 = realData.substring(realData.length() - 6,
							realData.length() - 4);
				}// 如果是清除警告
				else if (data1.equals("A4") && command.equals("clearAlert")) {
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
					data2 = realData.substring(realData.length() - 4,
							realData.length() - 2);

				}
				else {
					data2 = realData.substring(realData.length() - 4,
							realData.length() - 2);
				}
				// 判断返回数据的控制类型，更新Ccdata字段的值
				if (!"".equals(data2)) {
					if (data2.equals("00")) {
						cs.setStatus("Y");
						cs.setDisplay(value);
						daoService.updateCommandStoreById(cs);
					} else {
						cs.setStatus("N");
						cs.setDisplay(value);
						daoService.updateCommandStoreById(cs);
					}
				}
			}
				

			return null;
		} catch (NumberFormatException e) {
			System.out.println("控制指令返回数据的处理工作！");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 去掉前导00字节的方法
	public static String getRealReturnData(String param) {
		try {
			int i = 0;
			int numReg = 0;
			while (i < param.length() / 2 - 1) {
				String str = param.substring(i, i + 2);
				if ("00".equals(str)) {
					numReg++;
				} else {
					break;
				}
				i = i + 2;
			}
			if (numReg == 0) {
				return param;
			} else {
				return param.substring(numReg * 2, param.length());
			}
		} catch (Exception e) {
			System.out.println("去掉前导00字节的方法！");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 去掉中间空格的方法
	public static String removeSSS(String data) {
		try {
			String returnData = data.trim();
			returnData = returnData.replaceAll("\\s", "");
			return returnData;
		} catch (Exception e) {
			System.out.println("去掉中间空格的方法!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 添加中间空格的方法
	public static String addSSS(String data) {
		try {
			String[] split = data.split("\\s+");
			String trim = data.trim();
			if (trim.contains("\\s+")) {
				return data;
			}
			StringBuffer buffer = new StringBuffer();
			int length = trim.length();
			int halfLength = trim.length() / 2;

			return null;
		} catch (Exception e) {
			System.out.println("添加中间空格的方法!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
