//package com.cctrace.apiController;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.json.JSONObject;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.cctrace.entity.Ccdata;
//import com.cctrace.entity.Container;
//import com.cctrace.entity.OurCcdata;
//import com.cctrace.entity.QueryVo;
//import com.cctrace.entity.User;
//import com.cctrace.service.DaoService;
//import com.cctrace.utils.BaseResult;
//import com.cctrace.utils.ConstantCode;
//import com.cctrace.utils.DateUtil;
//import com.github.pagehelper.util.StringUtil;
//
///**
// * 传感器信息
// * 
// * @author
// * 
// */
//@Controller
//@RequestMapping(value = "/apiUser")
//public class ApiSensorController {
//
//	@Resource(name = "daoService")
//	private DaoService daoService;
//
//	/**
//	 * 
//	 * @param username
//	 * @return
//	 */
//	@RequestMapping("/showSensor")
//	@ResponseBody
//	public List<String> showSensor(HttpServletRequest request,
//			@RequestParam String username) {
//
//		User user = daoService.getUserByUsername(username);
//		List<String> listId = new ArrayList<String>();
//		List<Container> containers = null;
//		if (daoService.getContainersByCompanyId(user.getCompanyId()) != null) {
//			containers = daoService.getContainersByCompanyId(user
//					.getCompanyId());
//			for (int i = 0; i < containers.size(); i++) {
//
//				listId.add(i, containers.get(i).getContainerId());
//			}
//		}
//
//		return listId;
//	}
//
//	// /**
//	// *
//	// * @param String containerId,String startTime,String endTime
//	// * @return
//	// */
//	// @RequestMapping("/findSensorTwoTime")
//	// @ResponseBody
//	// public List<Ccdata> findSensorTwoTime(HttpServletRequest request,
//	// @RequestParam String containerId,
//	// @RequestParam String startTime, @RequestParam String endTime) {
//	//
//	// long startLongTime =
//	// DateUtil.getLongFromStr(startTime,"yyyy-MM-dd HH:mm:ss");
//	// long endLongTime =
//	// DateUtil.getLongFromStr(endTime,"yyyy-MM-dd HH:mm:ss");
//	//
//	// Map<String,Object> map = new HashMap<String,Object>();
//	// map.put("containerId", containerId);
//	// map.put("start", startLongTime);
//	// map.put("end", endLongTime);
//	// List<Ccdata> ccdatas = null;
//	// if(daoService.getCcdatasByContainerIdBetweenTowTime(map) != null){
//	// ccdatas = daoService.getCcdatasByContainerIdBetweenTowTime(map);
//	// }
//	// return ccdatas;
//
//	/**
//	 * 传感器设置的显示页面
//	 * 
//	 * @param request
//	 * @param username
//	 * @return
//	 */
//	@RequestMapping("/sensorSetting")
//	@ResponseBody
//	public BaseResult sensorSetting(HttpServletRequest request,
//			@RequestParam String username, @RequestParam String containerId,
//			@RequestParam String type, @RequestParam String max1,
//			@RequestParam String min1) {
//		BaseResult result = new BaseResult();
//		String mess = "";
//		int flag = ConstantCode.ERROR;
//
//		User user = daoService.getUserByUsername(username);
//		String role = user.getRole();
//		if (role.equals("common")) {
//			mess = "您的权限不够，无法设置";
//			result.setFlag(flag);
//			result.setMessage(mess);
//			return result;
//		} else {
//			if (StringUtil.isEmpty(type) || StringUtil.isEmpty(max1)
//					|| StringUtil.isEmpty(min1)) {
//				mess = "输入的设置信息不能是空";
//				result.setMessage(mess);
//				result.setFlag(flag);
//				return result;
//			} else {
//				double min = Double.parseDouble(min1);
//				double max = Double.parseDouble(max1);
//				if (min >= max) {
//					mess = "输入的数据不符合要求";
//					result.setMessage(mess);
//					result.setFlag(flag);
//					return result;
//				} else {
//					Container container = daoService
//							.getContainerBycontarinId(containerId);
//					OurCcdata ourCcdata = daoService
//							.getOurLastCcdataByContainerId(containerId);
//					switch (type) {
//
//					case "RefBatVol":
//						System.out.println(min);
//						System.out.println(max);
//						container.setMinRefBatVol(min);
//						container.setMaxRefBatVol(max);
//						int n = daoService
//								.updateContainerByContainerId(container);
//						if (n > 0) {
//							mess = "冷机电瓶电压设置成功";
//							flag = ConstantCode.SUCCESS;
//							result.setMessage(mess);
//							result.setFlag(flag);
//						} else {
//							mess = "冷机电瓶电压设置失败";
//							result.setMessage(mess);
//							result.setFlag(flag);
//						}
//						break;
//					case "BackWindTemp":
//						container.setMinBackWindTemp(min);
//						container.setMaxBackWindTemp(max);
//						int n1 = daoService
//								.updateContainerByContainerId(container);
//						if (n1 > 0) {
//							mess = "回风温度设置成功";
//							flag = ConstantCode.SUCCESS;
//							result.setMessage(mess);
//							result.setFlag(flag);
//						} else {
//							mess = "回风温度设置失败";
//							result.setMessage(mess);
//							result.setFlag(flag);
//						}
//						break;
//					// 环境温度
//					case "EnviTemp":
//						container.setMinEnviTemp(min);
//						container.setMaxEnviTemp(max);
//						int n2 = daoService
//								.updateContainerByContainerId(container);
//						if (n2 > 0) {
//							mess = "环境温度设置成功";
//							flag = ConstantCode.SUCCESS;
//							result.setMessage(mess);
//							result.setFlag(flag);
//						} else {
//							mess = "环境温度设置失败";
//							result.setMessage(mess);
//							result.setFlag(flag);
//						}
//						break;
//					// 出风温度
//					case "ChuWindTemp":
//						container.setMinChuWindTemp(min);
//						container.setMaxChuWindTemp(max);
//						int n3 = daoService
//								.updateContainerByContainerId(container);
//						if (n3 > 0) {
//							mess = "出风温度设置成功";
//							flag = ConstantCode.SUCCESS;
//							result.setMessage(mess);
//							result.setFlag(flag);
//						} else {
//							mess = "出风温度设置失败";
//							result.setMessage(mess);
//							result.setFlag(flag);
//						}
//						break;
//					// 油箱温度
//					case "oilTemp":
//						ourCcdata.setMinoilTemp(min);
//						ourCcdata.setMaxoilTemp(max);
//						int n4 = daoService
//								.updateOurCcdataBycontainerId(ourCcdata);
//						if (n4 > 0) {
//							mess = "油箱温度设置成功";
//							flag = ConstantCode.SUCCESS;
//							result.setMessage(mess);
//							result.setFlag(flag);
//						} else {
//							mess = "油箱温度设置失败";
//							result.setMessage(mess);
//							result.setFlag(flag);
//						}
//						break;
//					// 箱内湿度
//					case "boxHum":
//						ourCcdata.setMinboxHum(min);
//						ourCcdata.setMaxboxHum(max);
//						int n5 = daoService
//								.updateOurCcdataBycontainerId(ourCcdata);
//						if (n5 > 0) {
//							mess = "箱内湿度设置成功";
//							flag = ConstantCode.SUCCESS;
//							result.setMessage(mess);
//							result.setFlag(flag);
//						} else {
//							mess = "箱内湿度设置失败";
//							result.setMessage(mess);
//							result.setFlag(flag);
//						}
//						break;
//					// 箱尾温度
//					case "TailBoxTemp":
//						ourCcdata.setMintailBoxTemp(min);
//						ourCcdata.setMaxtailBoxTemp(max);
//						int n6 = daoService
//								.updateOurCcdataBycontainerId(ourCcdata);
//						if (n6 > 0) {
//							mess = "箱尾温度设置成功";
//							flag = ConstantCode.SUCCESS;
//							result.setMessage(mess);
//							result.setFlag(flag);
//						} else {
//							mess = "箱尾温度设置失败";
//							result.setMessage(mess);
//							result.setFlag(flag);
//						}
//						break;
//					case "GpsPower":
//						ourCcdata.setMingpsPower(min);
//						ourCcdata.setMaxgpsPower(max);
//						int n7 = daoService
//								.updateOurCcdataBycontainerId(ourCcdata);
//						if (n7 > 0) {
//							mess = "gps电量设置成功";
//							flag = ConstantCode.SUCCESS;
//							result.setMessage(mess);
//							result.setFlag(flag);
//						} else {
//							mess = "gps电量设置失败";
//							result.setMessage(mess);
//							result.setFlag(flag);
//						}
//						break;
//
//					case "oilLevel":
//						ourCcdata.setMinoilLevel(min);
//						ourCcdata.setMaxoilLevel(max);
//						int n8 = daoService
//								.updateOurCcdataBycontainerId(ourCcdata);
//						if (n8 > 0) {
//							mess = "油箱油位设置成功";
//							flag = ConstantCode.SUCCESS;
//							result.setMessage(mess);
//							result.setFlag(flag);
//						} else {
//							mess = "油箱油位设置失败";
//							result.setMessage(mess);
//							result.setFlag(flag);
//						}
//						break;
//
//					}
//
//				}
//
//				return result;
//
//			}
//		}
//
//	}
//
//	/**
//	 * 设定后门开关
//	 * 
//	 */
//	/*
//	 * @RequestMapping("/setBackDoor")
//	 * 
//	 * @ResponseBody public BaseResult setBackDoor(HttpServletRequest
//	 * request,@RequestParam String username, @RequestParam String
//	 * containerId,@RequestParam String state) throws IOException{ String role =
//	 * daoService.getUserByUsername(username).getRole(); BaseResult result = new
//	 * BaseResult(); String mess = ""; int flag = ConstantCode.ERROR; if
//	 * (role.equals("common")){ mess = "您的权限不够，无法进行设置"; result.setFlag(flag);
//	 * result.setMessage(mess); }else{
//	 * if(daoService.selectCcdataBycontainerId(containerId) != null){ Ccdata
//	 * ccdata = daoService.selectCcdataBycontainerId(containerId);
//	 * if(StringUtil.isNotEmpty(state)){ ccdata.setBackDoorState(state);
//	 * System.out.println(ccdata.getBackDoorState());
//	 * System.out.println(ccdata); int n =
//	 * daoService.updateCcdataBycontainerId(ccdata); System.out.println(n); if
//	 * (n > 0){ mess = "设置成功"; flag = ConstantCode.SUCCESS;
//	 * result.setFlag(flag); result.setMessage(mess); }else{ mess = "设置失败";
//	 * result.setFlag(flag); result.setMessage(mess); } }else{ mess =
//	 * "您没有输入设置内容"; result.setFlag(flag); result.setMessage(mess); } }else{ mess
//	 * = "您操作的对象不存在"; result.setFlag(flag); result.setMessage(mess); } } return
//	 * result; }
//	 */
//
//	/**
//	 * 传感器报告
//	 * 
//	 * @param String
//	 *            containerId,String startTime,String endTime
//	 * @return
//	 */
//	@RequestMapping("/findSensorTwoTime")
//	@ResponseBody
//	public List<QueryVo> findSensorTwoTime(HttpServletRequest request,
//			@RequestParam String containerId, @RequestParam String startTime,
//			@RequestParam String endTime) {
//
//		long startLongTime = DateUtil.getLongFromStr(startTime,
//				"yyyy-MM-dd HH:mm:ss");
//		long endLongTime = DateUtil.getLongFromStr(endTime,
//				"yyyy-MM-dd HH:mm:ss");
//
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("containerId", containerId);
//		map.put("start", startLongTime);
//		map.put("end", endLongTime);
//		List<QueryVo> queryVos = null;
//		if (daoService.showSensorBetweenTwoTimeInfo(map) != null) {
//			queryVos = daoService.showSensorBetweenTwoTimeInfo(map);
//			for (int i = 0; i < queryVos.size(); i++) {
//				if ((Double) queryVos.get(i).getCcdata().getRefBatVol() == null) {
//					queryVos.get(i).getCcdata().setRefBatVol(0.0);
//				}
//				// ///////////////////////////
//				if (StringUtil
//						.isEmpty(queryVos.get(i).getCcdata().getNowTime())) {
//					queryVos.get(i).getCcdata().setNowTime("");
//				}
//				if ((Double) queryVos.get(i).getCcdata().getBackWindTemp() == null) {
//					queryVos.get(i).getCcdata().setBackWindTemp(0.0);
//				}
//				if ((Double) queryVos.get(i).getCcdata().getEnviTemp() == null) {
//					queryVos.get(i).getCcdata().setEnviTemp(0.0);
//				}
//
//				if ((Double) queryVos.get(i).getCcdata().getChuWindTemp() == null) {
//					queryVos.get(i).getCcdata().setChuWindTemp(0.0);
//				}
//				// ///////////////////////
//				if (StringUtil.isEmpty(queryVos.get(i).getCcdata()
//						.getRefRunMode())) {
//					queryVos.get(i).getCcdata().setRefRunMode("");
//				}
//				if (StringUtil.isEmpty(queryVos.get(i).getCcdata()
//						.getRefSwiState())) {
//					queryVos.get(i).getCcdata().setRefSwiState("");
//				}
//				if ((Double) queryVos.get(i).getContainer().getSetTemp() == null) {
//					queryVos.get(i).getContainer().setSetTemp(0.0);
//				}
//				if ((Double) queryVos.get(i).getOurccdata().getBoxHum() == null) {
//					queryVos.get(i).getOurccdata().setBoxHum(0.0);
//				}
//				if ((Double) queryVos.get(i).getOurccdata().getOilTemp() == null) {
//					queryVos.get(i).getOurccdata().setOilTemp(0.0);
//				}
//				if ((Double) queryVos.get(i).getOurccdata().getTailBoxTemp() == null) {
//					queryVos.get(i).getOurccdata().setTailBoxTemp(0.0);
//				}
//				if ((Double) queryVos.get(i).getOurccdata().getGpsPower() == null) {
//					queryVos.get(i).getOurccdata().setGpsPower(0.0);
//				}
//				if ((Double) queryVos.get(i).getOurccdata().getOilLevel() == null) {
//					queryVos.get(i).getOurccdata().setOilLevel(0.0);
//				}
//				if (StringUtil.isEmpty(queryVos.get(i).getOurccdata()
//						.getBackDoorState())) {
//					queryVos.get(i).getOurccdata().setBackDoorState("");
//				}
//				if ((Double) queryVos.get(i).getCcdata().getEngRunTime() == null) {
//					queryVos.get(i).getCcdata().setEngRunTime(0.0);
//				}
//				if ((Double) queryVos.get(i).getCcdata().getVecRunTime() == null) {
//					queryVos.get(i).getCcdata().setVecRunTime(0.0);
//				}
//			}
//		}
//		return queryVos;
//	}
//
//	/**
//	 * 显示传感器的设置信息,最大最小等信息，用于设置后的展示
//	 * 
//	 * 
//	 */
//
//	@RequestMapping("/showSensorSetting")
//	@ResponseBody
//	public QueryVo showSensorSetting(HttpServletRequest request,
//			@RequestParam String containerId) throws IOException {
//		QueryVo queryVo = null;
//		if (daoService.getContainerBycontarinId(containerId) != null) {
//			queryVo = daoService.showSensorSetting(containerId);
//			// //////////////////////////////////
//			if ((Double) queryVo.getContainer().getMinRefBatVol() == null) {
//				queryVo.getContainer().setMinRefBatVol(0.0);
//			}
//			if ((Double) queryVo.getContainer().getMaxRefBatVol() == null) {
//				queryVo.getContainer().setMaxRefBatVol(0.0);
//			}
//			// ////////////////////////////////
//			if ((Double) queryVo.getContainer().getMinBackWindTemp() == null) {
//				queryVo.getContainer().setMinBackWindTemp(0.0);
//			}
//			if ((Double) queryVo.getContainer().getMaxBackWindTemp() == null) {
//				queryVo.getContainer().setMaxBackWindTemp(0.0);
//			}
//			// ///////////////////////////////
//			if ((Double) queryVo.getContainer().getMinEnviTemp() == null) {
//				queryVo.getContainer().setMinEnviTemp(0.0);
//			}
//			if ((Double) queryVo.getContainer().getMaxEnviTemp() == null) {
//				queryVo.getContainer().setMaxEnviTemp(0.0);
//			}
//			// //////////////////////////////
//			if ((Double) queryVo.getContainer().getMinChuWindTemp() == null) {
//				queryVo.getContainer().setMinChuWindTemp(0.0);
//			}
//			if ((Double) queryVo.getContainer().getMaxChuWindTemp() == null) {
//				queryVo.getContainer().setMaxChuWindTemp(0.0);
//			}
//			// /////////////////////////////
//			if ((Double) queryVo.getOurccdata().getMinoilTemp() == null) {
//				queryVo.getOurccdata().setMinoilTemp(0.0);
//			}
//			if ((Double) queryVo.getOurccdata().getMaxoilTemp() == null) {
//				queryVo.getOurccdata().setMaxoilTemp(0.0);
//			}
//			// ////////////////////////////
//			if ((Double) queryVo.getOurccdata().getMinboxHum() == null) {
//				queryVo.getOurccdata().setMinboxHum(0.0);
//			}
//			if ((Double) queryVo.getOurccdata().getMaxboxHum() == null) {
//				queryVo.getOurccdata().setMaxboxHum(0.0);
//			}
//			// ///////////////////////////
//			if ((Double) queryVo.getOurccdata().getMintailBoxTemp() == null) {
//				queryVo.getOurccdata().setMintailBoxTemp(0.0);
//			}
//			if ((Double) queryVo.getOurccdata().getMaxtailBoxTemp() == null) {
//				queryVo.getOurccdata().setMaxtailBoxTemp(0.0);
//			}
//			// ////////////////////////////
//			if ((Double) queryVo.getOurccdata().getMingpsPower() == null) {
//				queryVo.getOurccdata().setMingpsPower(0.0);
//			}
//			if ((Double) queryVo.getOurccdata().getMaxgpsPower() == null) {
//				queryVo.getOurccdata().setMaxgpsPower(0.0);
//			}
//			// //////////////////////////
//			if ((Double) queryVo.getOurccdata().getMinoilLevel() == null) {
//				queryVo.getOurccdata().setMinoilLevel(0.0);
//			}
//			if ((Double) queryVo.getOurccdata().getMaxoilLevel() == null) {
//				queryVo.getOurccdata().setMaxoilLevel(0.0);
//			}
//			// ////////////////////////////
//			if (StringUtil.isEmpty(queryVo.getOurccdata().getBackDoorState())) {
//				queryVo.getOurccdata().setBackDoorState("");
//			}
//		}
//		return queryVo;
//
//	}
//
//	/**
//	 * 模糊查询传感器
//	 * 
//	 * @throws IOException
//	 */
//	@RequestMapping("/slectSensorLike")
//	@ResponseBody
//	public void slectSensorLike(HttpServletRequest request,
//			HttpServletResponse response, @RequestParam String containerId)
//			throws IOException {
//		String mess = "此传感器不存在";
//		int flag = ConstantCode.ERROR;
//		JSONObject jsonObject = new JSONObject();
//		List<Ccdata> ccdatas = null;
//		if (daoService.selectSensorLikeyByContainerId(containerId) != null) {
//			ccdatas = daoService.selectSensorLikeyByContainerId(containerId);
//			mess = "查询成功";
//			flag = ConstantCode.SUCCESS;
//		}
//		jsonObject.put("flag", flag);
//		jsonObject.put("message", mess);
//		jsonObject.put("ccdatas", ccdatas);
//		response.setCharacterEncoding("UTF-8");
//		response.getWriter().write(jsonObject.toString());
//		response.getWriter().flush();
//		response.getWriter().close();
//
//	}
// }
