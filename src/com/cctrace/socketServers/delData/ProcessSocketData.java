package com.cctrace.socketServers.delData;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.cctrace.entity.Ccdata1;
import com.cctrace.entity.CommandStore;
import com.cctrace.entity.ErrorData;
import com.cctrace.entity.OfflineOrder;
import com.cctrace.service.DaoService;
import com.cctrace.socketServers.carryHandle.CarrierDataStorage;
import com.cctrace.socketServers.carryHandle.CarryTemset;
import com.cctrace.socketServers.dataHandle.Command;
import com.cctrace.socketServers.dataHandle.DataAnalysis;
import com.cctrace.socketServers.dataHandle.DataHandle;
import com.cctrace.socketServers.dataHandle.OurData;
import com.cctrace.socketServers.dataHandle.TempSet;
import com.cctrace.socketServers.dataHandle.TotalResolution;
import com.cctrace.socketServers.util.DealDateData;
import com.cctrace.socketServers.util.Journal;
import com.cctrace.utils.ConstantCode;
import com.cctrace.utils.OfflineOrderUtil;
import com.github.pagehelper.util.StringUtil;

public class ProcessSocketData implements Runnable {
	Socket socket;
	private static DaoService daoService;
	private static Map<String, Socket> map = new HashMap<String, Socket>();
	private InputStream in = null;
	private static InputStream is = null;

	public ProcessSocketData(Socket client) {
		this.socket = client;
	}

	private static DaoService getDao() {
		if (daoService == null) {
			daoService = SpringFactory.getBean("daoService");
		}
		return daoService;
	}

	public void run() {

		try {
			OutputStream os = socket.getOutputStream();
			in = socket.getInputStream();
			System.out.println("socket:" + socket);
			//Journal.writeSocket(socket);
			List<String> list = new Vector<String>();

			byte[] buf = new byte[512];
			int num = 0;

			if ((num = in.read(buf)) != -1) {
				String dataString = new String(buf, 0, num, "utf-8");
				StringBuffer sb = new StringBuffer();
				sb.append(dataString);
				String content = sb.toString();
				String sevNum = content.split("\\s+")[0];
				map.put(sevNum, socket);
			}
			while ((num = in.read(buf)) != -1) {
				DealTXData dealTXData = new DealTXData(getDao());
				DataAnalysis dataAnalysis = new DataAnalysis(getDao());
				DataHandle dataHandle = new DataHandle(getDao());
				OurData ourData = new OurData(getDao());
				DealCarrierData dealCarrierData = new DealCarrierData(getDao());
				TotalResolution totalResolution = new TotalResolution(getDao());
				CarrierDataStorage cds = new CarrierDataStorage(getDao());

				System.out.println("socket:" + socket);
				String dataString = new String(buf, 0, num, "utf-8");
				StringBuffer sb = new StringBuffer();
				System.out.println(dataString);
				sb.append(dataString);
				String content = sb.toString();

				String[] res = content.split("\n");
				Integer leng = res.length;
				if (leng == 2) {
					content = res[1];
				}

				Integer cl = content.length();
				String[] conts = content.split("\\s+");
				Integer conl = conts.length;

				String sevNum = content.split("\\s+")[0];
				if (conl > 1) {
					dealTXData.dealTXData(content, sevNum);// 将接收到的数据存放到数据库中

				}
				if (cl < 18) {
					//	Journal.writeEtcFile(content);
					Map<String, String> dtd = dealTXData
							.communicationMethod1(content);
					if (dtd == null) {
						os.flush();
					} else {
						String refSwiState = dtd.get("refSwiState");
						String chillerType = dtd.get("chillerType");
						if (refSwiState == null || chillerType == null) {
							os.flush();
						} else if (refSwiState.equals("on")) {
							sendRequest(socket, sevNum);
							os.flush();
						} else {
							requestData(socket, sevNum);
							os.flush();
						}
					}
				} else if (conl > 1) {
					Integer sevNuml = sevNum.length();
					Map<String, String> getValMap = totalResolution.totalData(
							sevNum, content);
					String getVal = null;
					String dataAll = null;
					String chillerType = null;
					if (getValMap == null) {
						os.flush();
					} else {
						getVal = getValMap.get("putVal");
						dataAll = getValMap.get("dataAll");
						chillerType = getValMap.get("chillerType");
					}
					if (getValMap == null || chillerType == null) {
						os.flush();
					} else if (chillerType.equals("thermoking")
							&& sevNuml == 15) {
						if (getVal.equals("dataAnalysis")) {// 冷机多数据存储
							dataAnalysis.dataProcess(dataAll, sevNum);
						} else if (getVal.equals("dataHandle")) {// 冷机发送（电瓶电压、环境温度、车辆运行时长、发动机运行时长、预警信息）存储
							dataHandle.handleData(dataAll, sevNum);
						} else if (getVal.equals("ourData")) {// 研发数据（经纬度，油温等）接收存储
							ourData.ourCcdata(dataAll);
						} else if (getVal.equals("cs")) {// 发送请求指令返回数据存储
							dealTXData.commandStore(dataAll, sevNum);
						}
					} else if (chillerType.equals("carrier") && sevNuml == 15) {
						if (("dataHandle").equals(getVal)) {
							cds.DataStorage(dataAll, sevNum);
						} else if (getVal.equals("ourData")) {// 研发数据（经纬度，油温等）接收存储
							ourData.ourCcdata(dataAll);
						} else if (getVal.equals("cs")) {
							dealCarrierData.commandStore(dataAll, sevNum);
						}
					}
				}

				os.flush();
			}
		} catch (SocketTimeoutException e) {
			System.out.println("连接超时，关闭连接!");
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException ex) {
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
	}

	public static Map<String, Object> dataResponse(Map<String, String> mapSet) {
		try {
			String dev = mapSet.get("dev");// 设备号
			String conId = mapSet.get("num");// 设备号
			String type = mapSet.get("type");// 设定类型：command冷藏箱的控制指令，temp温度设定
			String data = mapSet.get("data");// 参数：如开机，关机，温度的度数等
			String chillerType = mapSet.get("chillerType");
			Socket socket1 = map.get(dev);
			String message = "请求失败，稍后重试！";
			Map<String, Object> pmap = new HashMap<String, Object>();
			int flag = ConstantCode.ERROR;
			/*
			 * if (socket1 == null) { pmap.put("object", null); pmap.put("msg",
			 * message); pmap.put("flag", "" + flag); return pmap; }
			 */
			String returnVal = null;
			Map returnMap = new HashMap();
			if (type.equals("command")) {
				Command cmd = new Command();
				returnMap = cmd.method(data, dev, conId, chillerType);
				if (returnMap != null) {
					returnVal = (String) returnMap.get("commandSet");
				}
			}
			if (type.equals("temp")) {
				if ("thermoking".equals(chillerType)) {
					TempSet tem = new TempSet();
					returnMap = tem.tempSet(data, dev, conId);
				} else if ("carrier".equals(chillerType)) {
					CarryTemset carryTemSet = new CarryTemset();
					returnMap = carryTemSet.tempSet(data, dev, conId);
				}
				if (returnMap != null) {
					returnVal = (String) returnMap.get("key");
				}
			}
			if (type.equals("cfm")) {
				if ("carrier".equals(chillerType)) {
					CarryTemset carryTemSet = new CarryTemset();
					returnMap = carryTemSet.cfmSet(data, dev, conId);
				} else if ("thermoking".equals(chillerType)) {
					message="请求失败，该机型不支持该方式！";
					returnMap = null;
				}
				if (returnMap != null) {
					returnVal = (String) returnMap.get("key");
				}
			}
			if (socket1 != null && StringUtil.isNotEmpty(returnVal)) {
				try {
					OutputStream os = socket1.getOutputStream();
					is = socket1.getInputStream();
					os.write(returnVal.getBytes());
					os.flush();
					message = "设定成功！";
					flag = ConstantCode.SUCCESS;
				} catch (SocketTimeoutException e) {
					System.out.println("连接超时，关闭连接");
					message = "信号断开，稍后重试！";
					flag = 1;
					e.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			} else {
				flag = 1;
				message = "信号断开，稍后重试！";
			}
			if (returnMap != null && flag == 0) {
				pmap.put("object", returnMap.get("object"));
			} else {
				if (returnMap != null) {
					pmap.put("object", returnMap.get("object"));
				} else {
					pmap.put("object", null);
				}
			}
			pmap.put("returnVal", returnVal);
			pmap.put("msg", message);
			pmap.put("flag", "" + flag);
			return pmap;
		} catch (Exception e) {
			System.out.println("发送指令异常！");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String requestData(final Socket socket1, final String sevNum) {

		try {
			Ccdata1 cc1 = daoService.selectCcdataByDeviceId1(sevNum);
			if (cc1 == null) {
				return null;
			}
			final String chillerType = cc1.getChillerType();
			long timeInterval = 1000;
			Runnable runnable = new Runnable() {
				public void run() {
					List<String> list = new ArrayList<String>();

					if ("thermoking".equals(chillerType)) {
						list.add(sevNum + " 04 9300C8A5");// 冷王测试开关机指令 C8 数据总解
					} else if ("carrier".equals(chillerType)) {
						list.add(CarryTemset.getSendData(
								CarryTemset.overallUnitStatus, sevNum));// 开利测试开关机指令
																		// 请求操作状态;
					}

					if (socket1 != null && list.size() > 0) {
						try {
							OutputStream os = socket1.getOutputStream();
							in = socket1.getInputStream();
							os.write(list.get(0).getBytes());

							/*
							 * CommandStore cs = new CommandStore();
							 * 
							 * Long currentLongTime = System.currentTimeMillis();
							 * DealDateData dealDateData = new DealDateData();
							 * String strFormatNowDate = dealDateData
							 * .getStringDate(currentLongTime);
							 * cs.setContent(list.get(0)); cs.setLongTime("" +
							 * currentLongTime); cs.setTime(strFormatNowDate);
							 * cs.setType("0"); daoService.insertCommandStore(cs);
							 */
							System.out.println("list" + 0 + ":" + list.get(0));
							os.flush();
							Thread.sleep(8000);
						} catch (SocketTimeoutException e) {
							System.out.println("连接超时，关闭连接");
							e.printStackTrace();
						} catch (IOException ex) {
							ex.printStackTrace();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
			Thread thread = new Thread(runnable);
			thread.start();
			// thread.interrupt();

			return sevNum;
		} catch (Exception e) {
			System.out.println("关机异常请求！");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String sendRequest(final Socket socket1, final String sevNum) {

		try {
			Ccdata1 cc1 = daoService.selectCcdataByDeviceId1(sevNum);
			ErrorData ed = daoService.selectErrorDataByDeviceId(sevNum);
			if (cc1 == null) {
				return null;
			}
			long receiveLongTime = 0;
			if (ed != null) {
				receiveLongTime = Long.parseLong(ed.getReceiveLongTime());
			}
			if (StringUtil.isNotEmpty(ed.getReceiveLongTime())) {
				Long timeDifference = (System.currentTimeMillis() - receiveLongTime) / 56000;
				if (timeDifference > 2) {
					cc1.setRefSwiState("off");
					daoService.updateCcdataById1(cc1);
					try {
						Map<String, Object> mapCS = new HashMap<String, Object>();
						Long csTime = System.currentTimeMillis() - 190000;
						mapCS.put("value1", cc1.getContainerId());
						mapCS.put("value2", csTime);
						CommandStore cs = daoService.selectCommandStoreByMap(mapCS);
						if (cs != null) {
							cs.setStatus("Y");
							daoService.updateCommandStoreById(cs);

						}

					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			final String chillerType = cc1.getChillerType();
			long timeInterval = 1000;
			Runnable runnable = new Runnable() {
				public void run() {
					String commandStringByDevId = null;
					OfflineOrder offO = null;
					try {
						commandStringByDevId = OfflineOrderUtil
								.getCommandStringByDevId(sevNum);
						offO = OfflineOrderUtil.getOffLineOrderByDev(sevNum);
						OfflineOrderUtil.deleteCommandStringByDevId(sevNum);
					} catch (Exception e) {
						commandStringByDevId = null;
						offO = null;
					}
					List<String> list = new ArrayList<String>();
					if ("thermoking".equals(chillerType)) {
						if (commandStringByDevId != null) {
							list.add(commandStringByDevId);// 缓存命令
						}
						list.add(sevNum + " 04 9300C8A5");// 数据总解
						list.add(sevNum + " 04 9300A8C5");// 电瓶电压
						list.add(sevNum + " 04 9300ABC2");// 环境温度
						list.add(sevNum + " 04 9300F677");// 车辆运行时长
						list.add(sevNum + " 04 9300F776");// 发动机运行时长
						list.add(sevNum + " 04 9300CF9E");// 预警信息值
					} else if ("carrier".equals(chillerType)) {
						if (commandStringByDevId != null) {
							list.add(commandStringByDevId);// 缓存命令
						}
						list.add(CarryTemset.getSendData(
								CarryTemset.overallUnitStatus, sevNum));// 请求操作状态
						list.add(CarryTemset.getSendData(
								CarryTemset.activeAlarmQueue, sevNum));// 请求报警数据
						list.add(CarryTemset.getSendData(CarryTemset.setTemValue,
								sevNum));// 请求设置温度值

						list.add(CarryTemset.getSendData(CarryTemset.realTem,
								sevNum));// 请求温度传感器

						list.add(CarryTemset.getSendData(CarryTemset.engineSensors,
								sevNum));// 请求发动机传感器，电压，油量，门开关，远程开关1，远程开关2
						list.add(CarryTemset.getSendData(
								CarryTemset.standardHourMeters, sevNum));// 标准小时计量器（发动机，备用机，启动）
					}

					if (socket1 != null && list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							try {
								OutputStream os = socket1.getOutputStream();
								in = socket1.getInputStream();
								os.write(list.get(i).getBytes());

								if (i == 0 && offO != null) {
									CommandStore cs = new CommandStore();
									Long currentLongTime = System
											.currentTimeMillis();
									DealDateData dealDateData = new DealDateData();
									String strFormatNowDate = dealDateData
											.getStringDate(currentLongTime);
									cs.setContent(commandStringByDevId);
									cs.setTime(strFormatNowDate);
									cs.setLongTime("" + currentLongTime);
									cs.setTime("1");
									cs.setCommand(offO.getType());
									cs.setContainerId(offO.getContainerId());
									cs.setValue(offO.getData());
									cs.setUserName(offO.getUserName());
									cs.setStatus("N");
									daoService.insertCommandStore(cs);
								}
								/*
								 * CommandStore cs = new CommandStore();
								 * 
								 * Long currentLongTime =
								 * System.currentTimeMillis(); DealDateData
								 * dealDateData = new DealDateData(); String
								 * strFormatNowDate = dealDateData
								 * .getStringDate(currentLongTime);
								 * cs.setContent(list.get(i)); cs.setLongTime("" +
								 * currentLongTime); cs.setTime(strFormatNowDate);
								 * cs.setType("0");
								 * daoService.insertCommandStore(cs);
								 */

								System.out.println("list" + i + ":" + list.get(i));
								os.flush();
								Thread.sleep(8000);
							} catch (SocketTimeoutException e) {
								System.out.println("连接超时，关闭连接");
								e.printStackTrace();
							} catch (IOException ex) {
								ex.printStackTrace();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			};
			Thread thread = new Thread(runnable);
			thread.start();
			// thread.interrupt();

			return sevNum;
		} catch (NumberFormatException e) {
			System.out.println("开机指令请求异常!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
