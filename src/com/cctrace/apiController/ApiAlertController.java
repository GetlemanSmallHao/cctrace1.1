package com.cctrace.apiController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression;
import org.codehaus.jackson.map.deser.ValueInstantiators.Base;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.cctrace.entity.Alert;
import com.cctrace.entity.Container;
import com.cctrace.entity.User;
import com.cctrace.service.DaoService;
import com.cctrace.utils.BaseResult;
import com.cctrace.utils.ConstantCode;
import com.github.pagehelper.util.StringUtil;

@Controller
@RequestMapping("/apiUser")
public class ApiAlertController {

	@Resource(name = "daoService")
	private DaoService daoService;

	public void setDaoService(DaoService daoService) {
		this.daoService = daoService;
	}

	/**
	 * username
	 * 
	 */
	@RequestMapping("/showAlert")
	@ResponseBody
	public List<Alert> showAlert(HttpServletRequest request,
			@RequestParam String username) throws IOException {
		User user = daoService.getUserByUsername(username);
		List<Alert> alerts = null;
		if (user != null) {
			System.out.println(user.getCompanyId());
			if (daoService.selectAltersByCompanyId(user.getCompanyId()) != null) {
				alerts = daoService
						.selectAltersByCompanyId(user.getCompanyId());
				for (int i = 0; i < alerts.size(); i++) {
					if (StringUtil.isEmpty(alerts.get(i).getAlertTime())) {
						alerts.get(i).setAlertTime("");
					}
					if (StringUtil.isEmpty(alerts.get(i).getAlertType())) {
						alerts.get(i).setAlertType("");
					}
					if (StringUtil.isEmpty(alerts.get(i).getAlertContent())) {
						alerts.get(i).setAlertContent("");
					}
					if ((Double) alerts.get(i).getLat() == null) {
						alerts.get(i).setLat(34.741047);
					}
					if ((Double) alerts.get(i).getLon() == null) {
						alerts.get(i).setLon(113.790688);
					}
					if (StringUtil.isEmpty(alerts.get(i).getReaded())) {
						alerts.get(i).setReaded("");
					}
				}
			}

		}

		return alerts;
	}

	/**
	 * 显示有报警信息的冷藏箱列表
	 * 
	 */
	@RequestMapping("/showContainerWithAlert")
	@ResponseBody
	public List<String> showContainerWithAlert(HttpServletRequest request) {
		List<String> listcontainerId = new ArrayList<String>();
		List<Alert> alerts = daoService.selectContainersWithAlert();
		for (int i = 0; i < alerts.size(); i++) {
			String containerId = alerts.get(i).getContainerId();
			listcontainerId.add(i, containerId);
		}
		return listcontainerId;
	}

	/**
	 * 查询冷藏箱预警信息
	 * 
	 * @param request
	 * @param username
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/selectAlertByContainerId")
	@ResponseBody
	public List<Alert> selectAlertByContainerId(HttpServletRequest request,
			@RequestParam String containerId) throws IOException {

		List<Alert> alerts = null;
		if (daoService.selectAlertsByContainderId(containerId) != null) {
			alerts = daoService.selectAlertsByContainderId(containerId);
			for (int i = 0; i < alerts.size(); i++) {
				if (StringUtil.isEmpty(alerts.get(i).getAlertTime())) {
					alerts.get(i).setAlertTime("");
				}
				if (StringUtil.isEmpty(alerts.get(i).getAlertType())) {
					alerts.get(i).setAlertType("");
				}
				if (StringUtil.isEmpty(alerts.get(i).getAlertContent())) {
					alerts.get(i).setAlertContent("");
				}
				if ((Double) alerts.get(i).getLat() == null) {
					alerts.get(i).setLat(34.741047);
				}
				if ((Double) alerts.get(i).getLon() == null) {
					alerts.get(i).setLon(113.790688);
				}
				if (StringUtil.isEmpty(alerts.get(i).getReaded())) {
					alerts.get(i).setReaded("");
				}
			}
		}
		return alerts;
	}

	/**
	 * 信息已读过更新
	 */
	@RequestMapping("/updateAlertReaded")
	@ResponseBody
	public BaseResult updateAlertReaded(HttpServletRequest request,@RequestParam String id){
		String mess = "信息未查看";
		int flag = ConstantCode.ERROR;
		BaseResult result = new BaseResult();
		int id1 = Integer.parseInt(id);
		Alert alert = null;
		if(daoService.getAlertById(id1) != null){
			alert = daoService.getAlertById(id1);
			int n = daoService.modifyAlertReadStateToYesByAlertId(id1);
			if(n > 0){
				mess = "信息已查看";
				flag = ConstantCode.SUCCESS;
			}
		}
		result.setFlag(flag);
		result.setMessage(mess);
		return result;
	}

	/**
	 * 模糊查询有预警信息的冷藏箱列表在通知模块
	 */
	@RequestMapping("/selectContainersLikeyInAlert")
	@ResponseBody
	public List<Alert> selectContainersLikeyInAlert(HttpServletRequest request,
			@RequestParam String containerId) {
		List<Alert> alerts = null;
		if (daoService.selectContainersLikeyInAlert(containerId) != null) {
			alerts = daoService.selectContainersLikeyInAlert(containerId);
		}
		return alerts;
	}
}
