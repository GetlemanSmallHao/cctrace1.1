package com.cctrace.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cctrace.dao.ContainerMapper;
import com.cctrace.entity.Container;
import com.cctrace.entity.OurCcdata;
import com.cctrace.entity.User;
import com.cctrace.service.DaoService;
import com.cctrace.utils.ConstantCode;
import com.cctrace.utils.JsonResult;
import com.github.pagehelper.util.StringUtil;

@Controller
@RequestMapping(value = "/pc")
public class BatteryController {

	@Resource(name = "daoService")
	private DaoService daoService;
	@Autowired
	private ContainerMapper container;

	@RequestMapping("/updateContainerSet")
	@ResponseBody
	public JsonResult<Object> findBindtableWithJson(HttpServletRequest req) {
		try {
			User user = (User) req.getSession().getAttribute("user");
			if (user.getRole().equalsIgnoreCase("common")) {
				int flag = ConstantCode.ERROR;
				String mess = "您的权限不够";
				return new JsonResult<Object>(flag, mess, null);
			} else {
				int flag = ConstantCode.ERROR;
				String mess = "修改传感器参数值失败！";
				String type = req.getParameter("type");
				String containerId = req.getParameter("containerId");
				Container ct = daoService.getContainerBycontarinId(containerId);
				if (type == null || ct == null) {
					return new JsonResult<Object>(flag, mess, null);
				} else if (type.equals("battery")) {// 设定冷机电瓶电压最低值1
					String minRefBatVol = req.getParameter("minRefBatVol");
					System.out.println("小：" + minRefBatVol);
					String maxRefBatVol = req.getParameter("maxRefBatVol");
					System.out.println("大：" + maxRefBatVol);
					if (StringUtil.isEmpty(minRefBatVol)
							|| StringUtil.isEmpty(maxRefBatVol)) {
						mess = "设定冷机电压值为空！";
						return new JsonResult<Object>(flag, mess, null);
					}
					ct.setMinRefBatVol(Double.parseDouble(minRefBatVol));
					ct.setMaxRefBatVol(Double.parseDouble(maxRefBatVol));
					int con = container.updateContainerById(ct);
					if (con > 0) {
						flag = ConstantCode.SUCCESS;
						mess = "设定冷机电瓶电压成功！";
						System.out.println(container);
					}
					return new JsonResult<Object>(flag, mess, null);

				} else if (type.equals("backWindTemp")) {// 设定冷藏箱回风温度2
					String minBackWindTemp = req.getParameter("minBackWindTemp");
					String maxBackWindTemp = req.getParameter("maxBackWindTemp");
					if (StringUtil.isEmpty(minBackWindTemp)
							|| StringUtil.isEmpty(maxBackWindTemp)) {
						mess = "设定冷机回风温度值为空！";
						return new JsonResult<Object>(flag, mess, null);
					}
					ct.setMinBackWindTemp(Double.parseDouble(minBackWindTemp));
					ct.setMaxBackWindTemp(Double.parseDouble(maxBackWindTemp));
					int con = container.updateContainerById(ct);
					if (con > 0) {
						flag = ConstantCode.SUCCESS;
						mess = "设定冷机回风温度成功！";
					}
					return new JsonResult<Object>(flag, mess, null);

				} else if (type.equals("tankHum")) {// 箱内湿度设置 3
					String minTankHum = req.getParameter("minTankHum");
					String maxTankHum = req.getParameter("maxTankHum");
					if (StringUtil.isEmpty(minTankHum)
							|| StringUtil.isEmpty(maxTankHum)) {
						mess = "箱内湿度设置值为空！";
						return new JsonResult<Object>(flag, mess, null);
					}
					ct.setMinTankHum(Double.parseDouble(minTankHum));
					ct.setMaxTankHum(Double.parseDouble(maxTankHum));
					int con = container.updateContainerById(ct);
					if (con > 0) {
						flag = ConstantCode.SUCCESS;
						mess = "箱内湿度设置成功！";
					}
					return new JsonResult<Object>(flag, mess, null);

				} else if (type.equals("oilLevel")) {// 油箱油位设置4

					String minOilLevel = req.getParameter("minOilLevel");
					String maxOilLevel = req.getParameter("maxOilLevel");
					if (StringUtil.isEmpty(minOilLevel)
							|| StringUtil.isEmpty(maxOilLevel)) {
						mess = "油箱油位设置值为空！";
						return new JsonResult<Object>(flag, mess, null);
					}
					ct.setMinOilLevel(Double.parseDouble(minOilLevel));
					ct.setMaxOilLevel(Double.parseDouble(maxOilLevel));
					int con = container.updateContainerById(ct);
					if (con > 0) {
						flag = ConstantCode.SUCCESS;
						mess = "油箱油位设置成功！";
					}
					return new JsonResult<Object>(flag, mess, null);

				} else if (type.equals("gpsBatVol")) {// gps电压设置5

					String minGpsBatVol = req.getParameter("minGpsBatVol");
					if (StringUtil.isEmpty(minGpsBatVol)) {
						mess = "gps电压设置值为空！";
						return new JsonResult<Object>(flag, mess, null);
					}
					ct.setMinGpsBatVol(Double.parseDouble(minGpsBatVol));
					int con = container.updateContainerById(ct);
					if (con > 0) {
						flag = ConstantCode.SUCCESS;
						mess = "gps电压设置成功！";
					}
					return new JsonResult<Object>(flag, mess, null);

				} else if (type.equals("enviTemp")) {// 环境温度设置6

					String minEnviTemp = req.getParameter("minEnviTemp");
					String maxEnviTemp = req.getParameter("maxEnviTemp");
					if (StringUtil.isEmpty(minEnviTemp)
							|| StringUtil.isEmpty(maxEnviTemp)) {
						mess = "环境温度设置值为空！";
						return new JsonResult<Object>(flag, mess, null);
					}
					ct.setMinEnviTemp(Double.parseDouble(minEnviTemp));
					ct.setMaxEnviTemp(Double.parseDouble(maxEnviTemp));
					int con = container.updateContainerById(ct);
					if (con > 0) {
						flag = ConstantCode.SUCCESS;
						mess = "环境温度设置成功！";
					}
					return new JsonResult<Object>(flag, mess, null);

				} else if (type.equals("chuWindTemp")) {// 出风温度设置7

					String minChuWindTemp = req.getParameter("minChuWindTemp");
					String maxChuWindTemp = req.getParameter("maxChuWindTemp");
					if (StringUtil.isEmpty(minChuWindTemp)
							|| StringUtil.isEmpty(maxChuWindTemp)) {
						mess = "出风温度设置值为空！";
						return new JsonResult<Object>(flag, mess, null);
					}
					ct.setMinChuWindTemp(Double.parseDouble(minChuWindTemp));
					ct.setMaxChuWindTemp(Double.parseDouble(maxChuWindTemp));
					int con = container.updateContainerById(ct);
					if (con > 0) {
						flag = ConstantCode.SUCCESS;
						mess = "出风温度设置成功！";
					}
					return new JsonResult<Object>(flag, mess, null);

				} else if (type.equals("cenBoxTemp")) {// 中部箱温设置8

					String minCenBoxTemp = req.getParameter("minCenBoxTemp");
					String maxCenBoxTemp = req.getParameter("maxCenBoxTemp");
					if (StringUtil.isEmpty(minCenBoxTemp)
							|| StringUtil.isEmpty(maxCenBoxTemp)) {
						mess = "中部箱温设置值为空！";
						return new JsonResult<Object>(flag, mess, null);
					}
					ct.setMinCenBoxTemp(Double.parseDouble(minCenBoxTemp));
					ct.setMaxCenBoxTemp(Double.parseDouble(maxCenBoxTemp));
					int con = container.updateContainerById(ct);
					if (con > 0) {
						flag = ConstantCode.SUCCESS;
						mess = "中部箱温设置成功！";
					}
					return new JsonResult<Object>(flag, mess, null);

				} else if (type.equals("tailBoxTemp")) {// 尾部箱温设置9

					String minTailBoxTemp = req.getParameter("minTailBoxTemp");
					String maxTailBoxTemp = req.getParameter("maxTailBoxTemp");
					if (StringUtil.isEmpty(minTailBoxTemp)
							|| StringUtil.isEmpty(maxTailBoxTemp)) {
						mess = "尾部箱温设置值为空！";
						return new JsonResult<Object>(flag, mess, null);
					}
					ct.setMinTailBoxTemp(Double.parseDouble(minTailBoxTemp));
					ct.setMaxTailBoxTemp(Double.parseDouble(maxTailBoxTemp));
					int con = container.updateContainerById(ct);
					if (con > 0) {
						flag = ConstantCode.SUCCESS;
						mess = "箱内湿度设置成功！";
					}
					return new JsonResult<Object>(flag, mess, null);

				}

				return new JsonResult<Object>(flag, mess, null);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("异常");
		}
		return null;

	}

	/**
	 * 设置自研设备的数据表中的值
	 * 
	 */
	@RequestMapping("/updateOurCcdata")
	@ResponseBody
	public JsonResult<Object> updateOurCcdata(HttpServletRequest req) {
		try {
			User user = (User) req.getSession().getAttribute("user");
			if (user.getRole().equalsIgnoreCase("common")) {
				int flag = ConstantCode.ERROR;
				String mess = "您的权限不够";
				return new JsonResult<Object>(flag, mess, null);
			} else {
				int flag = ConstantCode.ERROR;
				String mess = "修改传感器参数值失败！";
				String type = req.getParameter("type");
				String containerId = req.getParameter("containerId");
				OurCcdata ourCcdata = daoService
						.getOurLastCcdataByContainerId(containerId);

				// Container ct=daoService.getContainerBycontarinId(containerId);
				if (type == null || ourCcdata == null) {
					return new JsonResult<Object>(flag, mess, null);

				} else if (type.equals("oilTemp")) {// 设定油箱温度
					String minoilTemp = req.getParameter("minoilTemp");
					String maxoilTemp = req.getParameter("maxoilTemp");
					if (StringUtil.isEmpty(minoilTemp)
							|| StringUtil.isEmpty(maxoilTemp)) {
						mess = "设定油箱温度值为空！";
						return new JsonResult<Object>(flag, mess, null);
					}
					ourCcdata.setMinoilTemp(Double.parseDouble(minoilTemp));
					ourCcdata.setMaxoilTemp(Double.parseDouble(maxoilTemp));
					// ct.setMaxBackWindTemp(Double.parseDouble(maxBackWindTemp));
					int n = daoService.updateOurCcdataById(ourCcdata);
					if (n > 0) {
						flag = ConstantCode.SUCCESS;
						mess = "设定油箱温度成功！";
					}
					return new JsonResult<Object>(flag, mess, null);

				} else if (type.equals("boxHum")) {// 箱内湿度设置 3
					String minboxHum = req.getParameter("minboxHum");
					String maxboxHum = req.getParameter("maxboxHum");
					if (StringUtil.isEmpty(minboxHum)
							|| StringUtil.isEmpty(maxboxHum)) {
						mess = "箱内湿度设置值为空！";
						return new JsonResult<Object>(flag, mess, null);
					}
					ourCcdata.setMinboxHum(Double.parseDouble(minboxHum));
					ourCcdata.setMaxboxHum(Double.parseDouble(maxboxHum));
					int n1 = daoService.updateOurCcdataById(ourCcdata);
					if (n1 > 0) {
						flag = ConstantCode.SUCCESS;
						mess = "箱内湿度设置成功！";
					}
					return new JsonResult<Object>(flag, mess, null);

				} else if (type.equals("oilLevel")) {// 油箱油位设置4

					String minoilLevel = req.getParameter("minoilLevel");
					String maxoilLevel = req.getParameter("maxoilLevel");
					if (StringUtil.isEmpty(minoilLevel)
							|| StringUtil.isEmpty(maxoilLevel)) {
						mess = "油位设置值为空！";
						return new JsonResult<Object>(flag, mess, null);
					}
					ourCcdata.setMinoilLevel(Double.parseDouble(minoilLevel));
					ourCcdata.setMaxoilLevel(Double.parseDouble(maxoilLevel));
					int n2 = daoService.updateOurCcdataById(ourCcdata);
					if (n2 > 0) {
						flag = ConstantCode.SUCCESS;
						mess = "油箱油位设置成功！";
					}
					return new JsonResult<Object>(flag, mess, null);

				} else if (type.equals("gpsPower")) {// gps电压设置5

					String mingpsPower = req.getParameter("mingpsPower");
					String maxgpsPower = req.getParameter("maxgpsPower");
					if (StringUtil.isEmpty(mingpsPower)
							|| StringUtil.isEmpty(maxgpsPower)) {
						mess = "gps电量设置值为空！";
						return new JsonResult<Object>(flag, mess, null);
					}
					ourCcdata.setMingpsPower(Double.parseDouble(mingpsPower));
					ourCcdata.setMaxgpsPower(Double.parseDouble(maxgpsPower));
					int n3 = daoService.updateOurCcdataById(ourCcdata);
					if (n3 > 0) {
						flag = ConstantCode.SUCCESS;
						mess = "gps电量设置成功！";
					}
					return new JsonResult<Object>(flag, mess, null);
				} else if (type.equals("backDoorState")) {// 设定后门开关

					String backDoorState = req.getParameter("backDoorState");
					System.out.println(backDoorState);
					if (StringUtil.isEmpty(backDoorState)) {
						mess = "设定后门开关状态为空！";
						return new JsonResult<Object>(flag, mess, null);
					}
					ourCcdata.setBackDoorState(backDoorState);
					// ct.setMaxBackWindTemp(Double.parseDouble(maxBackWindTemp));
					int n = daoService.updateOurCcdataById(ourCcdata);
					if (n > 0) {
						flag = ConstantCode.SUCCESS;
						mess = "设定后门开关状态成功！";
					}
					return new JsonResult<Object>(flag, mess, null);
				} else if (type.equals("tailBoxTemp")) {// 尾部箱温设置9

					String mintailBoxTemp = req.getParameter("mintailBoxTemp");
					String maxtailBoxTemp = req.getParameter("maxtailBoxTemp");
					if (StringUtil.isEmpty(mintailBoxTemp)
							|| StringUtil.isEmpty(maxtailBoxTemp)) {
						mess = "箱尾温度设置值为空！";
						return new JsonResult<Object>(flag, mess, null);
					}
					ourCcdata.setMintailBoxTemp(Double.parseDouble(mintailBoxTemp));
					ourCcdata.setMaxtailBoxTemp(Double.parseDouble(maxtailBoxTemp));
					int n4 = daoService.updateOurCcdataById(ourCcdata);
					if (n4 > 0) {
						flag = ConstantCode.SUCCESS;
						mess = "箱尾温度设置成功！";
					}
					return new JsonResult<Object>(flag, mess, null);

				}

				return new JsonResult<Object>(flag, mess, null);

			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("设置数据值异常");
		}
		return null;
	}

}