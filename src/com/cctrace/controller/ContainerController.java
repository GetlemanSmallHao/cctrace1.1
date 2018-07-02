package com.cctrace.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cctrace.entity.BindTable;
import com.cctrace.entity.Ccdata;
import com.cctrace.entity.Ccdata1;
import com.cctrace.entity.CommandStore;
import com.cctrace.entity.Container;
import com.cctrace.entity.OurCcdata;
import com.cctrace.entity.OurCcdata1;
import com.cctrace.entity.User;
import com.cctrace.service.DaoService;
import com.cctrace.utils.ConstantCode;
import com.cctrace.utils.DateUtil;
import com.cctrace.utils.JsonResult;
import com.cctrace.utils.ValidateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;

@Controller
@RequestMapping(value = "pc/Container")
// 冷藏箱表 冷藏箱操作，展示所有信息
public class ContainerController {

	@Resource
	private DaoService daoService;

	@RequestMapping(value = "/registNewContainer")
	public void registNewContainer(Container container) {
		try {
			Date now = new Date();
			String dateStr = DateUtil.getDateStr(now, "yyyy-MM-dd HH:mm:ss");
			long longDate = DateUtil.getLongFromDate(now);
			container.setRegistTime(dateStr);
			container.setRegistLongTime(longDate);
			System.out.println(container);
			// 此处返回的是试图
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("异常");
		}
	}

	/*
	*//**
	 * 冷藏箱查询
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping("findContainers") public String
	 * findContainers(HttpServletRequest request,ModelMap map){
	 * 
	 * User user=(User)request.getSession().getAttribute("user");
	 * List<Container> containers=
	 * daoService.getContainersByCompanyId(user.getCompanyId()); for(Container
	 * container:containers){ System.out.println(container); } List<OurCcdata>
	 * ourCcdatas= daoService.getOurAllLocationBycompanyId(user.getCompanyId());
	 * for(OurCcdata ourCcdata:ourCcdatas){ System.out.println(ourCcdata); }
	 * map.addAttribute("containers", containers);
	 * map.addAttribute("ourCcdatas", ourCcdatas); return "boxManage.jsp"; }
	 */

	/**
	 * 冷藏箱查询
	 * 
	 * @return
	 */
	@RequestMapping("findContainers")
	public String findContainers(HttpServletRequest request, ModelMap mmap) {
		try {
			List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
			User user = (User) request.getSession().getAttribute("user");
			OurCcdata1 ourCcdata = null;
			Ccdata1 ccdata = null;
			String valueOfTemp = "";
			String valueOfRemoteSwiMac = "";
			String valueOfRefRunMode = "";
			List<Container> containers = daoService.getContainersByCompanyId(user
					.getCompanyId());
			for (Container container : containers) {
				String containerId = container.getContainerId();
				ourCcdata = daoService.selectOurCcdataBycontainerId1(containerId);
				ccdata = daoService.selectCcdataByContainerId1(containerId);
				//查温度的值
				if(ccdata.getTempSet() != null){
					valueOfTemp = ""+ccdata.getTempSet();
				}else{
					valueOfTemp = "";
				}
				//查工作模式
				if(StringUtil.isNotEmpty(ccdata.getRefRunMode())){
					valueOfRefRunMode = ccdata.getRefRunMode();
				}else{
					valueOfRefRunMode = "";
				}
				//查开关机
				if(StringUtil.isNotEmpty(ccdata.getRefSwiState())){
					valueOfRemoteSwiMac = ccdata.getRefSwiState();
				}else{
					valueOfRemoteSwiMac = "";
				}
				
				System.out.println(container);
				System.out.println(ourCcdata);
				System.out.println(ccdata);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("container", container);
				map.put("ccdata", ccdata);
				map.put("ourCcdata", ourCcdata);
				map.put("setTemp", valueOfTemp);
				map.put("remoteSwiMac", valueOfRemoteSwiMac);
				map.put("refRunMode", valueOfRefRunMode);

				maps.add(map);
			}
			mmap.addAttribute("maps", maps);
			/* map.addAttribute("ourCcdatas", ourCcdatas); */
			return "boxManage.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("冷藏箱查询异常");
		}
		return null;
	}

	/**
	 * 冷藏箱模糊查询
	 * 
	 * @return
	 */
	@RequestMapping("findContainersLikey")
	public String findContainersLikey(HttpServletRequest request,
			String containerId, ModelMap mmap) {
		try {
			List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
			OurCcdata ourCcdata = null;
			Ccdata ccdata = null;
			List<Container> containers = daoService
					.selectContainersLikeyInPCContainer(containerId);
			for (Container container : containers) {
				String containerId1 = container.getContainerId();
				ourCcdata = daoService.getOurLastCcdataByContainerId(containerId1);
				ccdata = daoService.getLastCcdataByContainerId(containerId1);
				System.out.println(container);
				System.out.println(ourCcdata);
				System.out.println(ccdata);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("container", container);
				map.put("ccdata", ccdata);
				map.put("ourCcdata", ourCcdata);

				maps.add(map);
			}
			mmap.addAttribute("maps", maps);
			return "showContainersLikeyInContainer.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("冷藏箱模糊查询异常");
		}
		return null;
	}

	/**
	 * 添加冷藏箱
	 */
	@ResponseBody
	@RequestMapping(value = "/containerRegist")
	public JsonResult<Container> containerRegist(HttpServletRequest request,
			HttpServletResponse response, Container container,
			String containerId) {
		try {
			String mess = "";
			int flag = ConstantCode.ERROR;

			String registTime = DateUtil.getDateStr(new Date(),
					"yyyy-MM-dd HH:mm:ss");
			long registLongTime = DateUtil.getLongFromDate(new Date());
			System.out.println(containerId);
			System.out.println(container);
			// 1.非空
			if (!ValidateUtil.isValid(container.getContainerId())) {
				mess = "冷藏箱编号不能为空！";
				return new JsonResult<Container>(flag, mess, container);
			}
			if (!ValidateUtil.isValid(container.getDeviceId())) {
				mess = "设备编号不能为空！";
				return new JsonResult<Container>(flag, mess, container);
			}
			if (!ValidateUtil.isValid(container.getChillerType())) {
				mess = "冷机类型不能为空！";
				return new JsonResult<Container>(flag, mess, container);
			} else {
				User user = (User) request.getSession().getAttribute("user");
				Container containerBycontarinId = daoService
						.getContainerBycontarinId(container.getContainerId());

				if (containerBycontarinId != null) {
					mess = "冷藏箱已存在";
				} else {
					container.setCompanyId(user.getCompanyId());
					container.setRegistTime(registTime);
					container.setRegistLongTime(registLongTime);
					System.out.println(container);
					mess = "注册成功";
					flag = ConstantCode.SUCCESS;
					int n1 = daoService.registNewContainer(container);
					System.out.println("n1" + n1);
					// 添加ccdata
					Ccdata ccdata = new Ccdata();
					ccdata.setContainerId(container.getContainerId());
					ccdata.setDeviceId(container.getDeviceId());
					ccdata.setChillerType(container.getChillerType());
					ccdata.setCompanyId(container.getCompanyId());
					System.out.println("33" + ccdata);
					ccdata.setNowTime(registTime);
					ccdata.setNowLongTime(registLongTime);
					ccdata.setReceiveTime(registTime);
					ccdata.setReceiveLongTime(registLongTime);
					daoService.addOneNewCcdata(ccdata);

					// 添加ourccdata
					OurCcdata ourccdata = new OurCcdata();
					ourccdata.setContainerId(container.getContainerId());
					ourccdata.setDeviceId(container.getDeviceId());
					ourccdata.setCompanyId(container.getCompanyId());
					// System.out.println("33"+ccdata);
					ourccdata.setNowTime(registTime);
					ourccdata.setNowLongTime(registLongTime);
					ourccdata.setReceiveTime(registTime);
					ourccdata.setReceiveLongTime(registLongTime);
					daoService.addOurOneNewCcdata(ourccdata);
					// 添加bindtable
					BindTable bindtable = new BindTable();
					bindtable.setOperator(user.getUsername());
					bindtable.setContainerId(container.getContainerId());
					bindtable.setDeviceId(container.getDeviceId());
					bindtable.setBindTime(registTime);
					bindtable.setBindLongTime(registLongTime);
					bindtable.setCompanyId(container.getCompanyId());
					daoService.registNewBindTable(bindtable);
					//添加展示用的ccdata1的数据
					Ccdata1 ccdata1 = new Ccdata1();
					ccdata1.setContainerId(container.getContainerId());
					ccdata1.setDeviceId(container.getDeviceId());
					ccdata1.setChillerType(container.getChillerType());
					ccdata1.setCompanyId(container.getCompanyId());
					// 添加展示用的ourccdata1
					OurCcdata1 ourccdata1 = new OurCcdata1();
					ourccdata1.setContainerId(container.getContainerId());
					ourccdata1.setDeviceId(container.getDeviceId());
					ourccdata1.setCompanyId(container.getCompanyId());
					// System.out.println("33"+ccdata);
					ourccdata1.setNowTime(registTime);
					ourccdata1.setNowLongTime(registLongTime);
					ourccdata1.setReceiveTime(registTime);
					ourccdata1.setReceiveLongTime(registLongTime);
					daoService.insertOurCcdata1(ourccdata1);
					
					System.out.println("33" + ccdata1);
					ccdata1.setNowTime(registTime);
					ccdata1.setNowLongTime(registLongTime);
					ccdata1.setReceiveTime(registTime);
					ccdata1.setReceiveLongTime(registLongTime);
					daoService.insertCcdata1(ccdata1);
				}
			}

			return new JsonResult<Container>(flag, mess, container);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("添加冷藏箱异常");
		}
		return null;
	}

	/**
	 * 删除冷藏箱
	 */
	@ResponseBody
	@RequestMapping(value = "/containerDelete")
	public JsonResult<Container> containerDelete(HttpServletRequest request,
			Container container) {

		try {
			String mess = "删除成功";
			int flag = ConstantCode.SUCCESS;
			// 删除container
			daoService.removeContainerByContainerId(container.getContainerId());
			// 删除ccdata
			daoService.removeCcdatasByContainerId(container.getContainerId());
			// 删除ourccdata
			daoService.removeOurCcdatasByContainerId(container.getContainerId());
			// 删除bindtable
			daoService.removeBindTableByContainerId(container.getContainerId());
			// 删除展示用的新表
			daoService.deleteCcdatasByContainerId1(container.getContainerId());
			// 删除展示的ourccdata1
			daoService.deleteOurCcdatasByContainerId1(container.getContainerId());
			
			return new JsonResult<Container>(flag, mess, container);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("删除冷藏箱异常");
		}
		return null;
	}

	/**
	 * 更新冷场箱数据
	 */
	@ResponseBody
	@RequestMapping(value = "/containerUpdate")
	public JsonResult<Container> containerUpdate(ModelMap model,
			HttpServletRequest req, Container container) {

		try {
			String mess = "";
			int flag = ConstantCode.SUCCESS;
			System.out.println("2234" + container);

			int n = daoService.modifyContainerById(container);
			System.out.println(n);
			if (n > 0) {
				mess = "修改成功";
			} else {
				mess = "修改失败";
				flag = ConstantCode.ERROR;
			}

			return new JsonResult<Container>(flag, mess, container);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("更新冷藏箱数据异常");
		}
		return null;
	}

}
