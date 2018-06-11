package com.cctrace.controller.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cctrace.entity.Alert;
import com.cctrace.entity.Ccdata;
import com.cctrace.entity.Company;
import com.cctrace.entity.Container;
import com.cctrace.entity.OurCcdata;
import com.cctrace.entity.User;
import com.cctrace.service.DaoService;
import com.cctrace.utils.DateUtil;

@Controller
@RequestMapping(value = "/test")
public class TestController {

	@Resource(name = "daoService")
	private DaoService daoService;

	public void setDaoService(DaoService daoService) {
		this.daoService = daoService;
	}

	@RequestMapping(value = "/registNewCompany")
	@ResponseBody
	public void registNewCompany(HttpServletRequest request, Company company) {
		int regist = daoService.registNewCompany(company);
		if (regist == 1) {
			System.out.println("注册成功");
		} else {
			System.out.println("注册失败");
		}
	}

	@RequestMapping(value = "/modifyCompanyInfoById")
	@ResponseBody
	public void modifyCompanyInfoById(Company company) {
		int modify = daoService.modifyCompanyInfoById(company);
		if (modify == 1) {
			System.out.println("修改成功");
		} else {
			System.out.println("修改失败");
		}
	}

	@RequestMapping(value = "/registNewUser")
	@ResponseBody
	public void registNewUser(User user) {
		int regist = daoService.registNewUser(user);
		if (regist == 1) {
			System.out.println("修改成功");
		} else {
			System.out.println("修改失败");
		}
	}

	@RequestMapping(value = "/userLogin")
	@ResponseBody
	public void userLogin(String username, String password) {
		Map map = new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);
		User user = daoService.getUserByUsernameAndPassword(map);
		System.out.println(user);
	}

	@RequestMapping(value = "/modifyUserInfo")
	@ResponseBody
	public void modifyUserInfoById(User user) {
		int modify = daoService.modifyUserInfoById(user);
		if (modify == 1) {
			System.out.println("用户信息修改成功。");
		} else {
			System.out.println("用户信息修改失败。");
		}
	}

	@RequestMapping(value = "/registNewContainer")
	@ResponseBody
	public void registContainer(Container container) {
		String registTime = container.getRegistTime();
		container.setRegistLongTime(DateUtil.getLongFromStr(registTime,
				"yyyy-MM-dd HH:mm:ss"));
		int regist = daoService.registNewContainer(container);
		if (regist == 1) {
			System.out.println("新冷藏箱注册成功。");
		} else {
			System.out.println("新冷藏箱注册失败。");
		}
	}

	@RequestMapping(value = "/modifyContainerById")
	@ResponseBody
	public void modifyContainerById(Container container) {
		int modify = daoService.modifyContainerById(container);
		if (modify == 1) {
			System.out.println("冷藏箱信息修改成功。");
		} else {
			System.out.println("冷藏箱信息修改失败。");
		}
	}

	@RequestMapping(value = "/addOneNewAlert")
	public void addNewAlert(Alert alert, String deviceId) {
		int add = daoService.addOneNewAlert(alert);
		if (add == 1) {
			System.out.println("告警信息添加成功。");
		} else {
			System.out.println("告警信息添加失败。");
		}
	}

	@RequestMapping(value = "/addNewCcdata")
	@ResponseBody
	public String addNewCcdata(HttpServletRequest request, Ccdata ccdata) {
		String deviceId = ccdata.getDeviceId();
		String conId = daoService.getContainerIdByDeviceId(deviceId);
		ccdata.setContainerId(conId);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Integer companyId = user.getCompanyId();
		ccdata.setCompanyId(companyId);
		String receiveTime = ccdata.getReceiveTime();
		long receivLongTime = DateUtil.getLongFromStr(receiveTime,
				"yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String nowTime = DateUtil.getDateStr(now, "yyyy-MM-dd HH:mm:ss");
		long nowLongTime = DateUtil.getLongFromDate(now);

		ccdata.setReceiveLongTime(receivLongTime);
		ccdata.setNowTime(nowTime);
		ccdata.setNowLongTime(nowLongTime);

		int add = daoService.addOneNewCcdata(ccdata);
		String message = "";
		if (add == 1) {
			message += "{\"message\":\"success\"}";
		} else {
			message += "{\"message\":\"failed\"}";
		}
		return message;
	}
	
	/*@RequestMapping(value = "/addNewOurCcdata")
	@ResponseBody
	public String addNewOurCcdata(HttpServletRequest request, OurCcdata ourccdata) {
		String deviceId = ourccdata.getDeviceId();
		Long receiveLongTime = DateUtil.getLongFromStr(ourccdata.getReceiveTime(), "yyyy-MM-dd HH:mm:ss");
		String conId = daoService.getContainerIdByDeviceId(deviceId);
		ourccdata.setContainerId(conId);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Integer companyId = user.getCompanyId();
		ourccdata.setCompanyId(companyId);
//		long gpsLongTime = DateUtil.getLongFromStr(gpsTime,
//				"yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String nowTime = DateUtil.getDateStr(now, "yyyy-MM-dd HH:mm:ss");
		long nowLongTime = DateUtil.getLongFromDate(now);
		
		ourccdata.setReceiveLongTime(receiveLongTime);
		ourccdata.setNowTime(nowTime);
		ourccdata.setNowLongTime(nowLongTime);

		int add = daoService.addOurOneNewCcdata(ourccdata);
		String message = "";
		if (add == 1) {
			message += "{\"message\":\"success\"}";
		} else {
			message += "{\"message\":\"failed\"}";
		}
		return message;
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/addNewAlert")
	public String addNewAlert(Alert alert) {
		Date now = new Date();
		alert.setAlertTime(DateUtil.getDateStr(now,"yyyy-MM-dd HH:mm:ss"));
		alert.setAlertLongTime(DateUtil.getLongFromDate(now));
		alert.setReaded("no");
		System.out.println(alert);
		int add = daoService.addOneNewAlert(alert);
		if (add == 1) {
			System.out.println("新警告添加成功");
		}
		return null;
	}
	@RequestMapping(value="/getCountOfNoReadedALertsByCompanyId")
	@ResponseBody
	public Integer getCountOfNoReadedAlertsByCompanyId(HttpServletRequest request){
        String companyIdStr = request.getParameter("companyId");
		int companyId = Integer.parseInt(companyIdStr);
		Integer count = daoService.getCountOfNoReadedAlertsByCompanyId(companyId);
		return count;
	}

	@RequestMapping(value="/testLoadAlertId")
	@ResponseBody
	public String testLoadAlertId(HttpServletRequest request){
		String alertIdStr = request.getParameter("alertId");
		int alertId = Integer.parseInt(alertIdStr);
		System.out.println(alertId);
		return "{\"messgae\":\"success\"}";
	}
}
