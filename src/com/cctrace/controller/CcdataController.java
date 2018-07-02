package com.cctrace.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cctrace.entity.Ccdata;
import com.cctrace.service.DaoService;
import com.cctrace.utils.DateUtil;

@Controller
@RequestMapping(value = "/pc/Ccdata")
// 冷藏箱返回数据表
public class CcdataController {
	@Autowired
	DaoService daoService;

	/**
	 * 新增冷藏箱数据
	 */

	@RequestMapping(value = "/addCcdata")
	public String addCcdata(HttpServletRequest request,
			HttpServletResponse response, Ccdata ccdata) {
		try {
			// User user=(User)request.getSession().getAttribute("user");
			// ccdata.setCompanyId(user.getCompanyId());
			long receiveLongTime = DateUtil.getLongFromStr(ccdata.getReceiveTime(),
					"yyyy-MM-dd HH:mm:ss");
			long nowLongTime = DateUtil.getLongFromDate(DateUtil.getNowDate());
			ccdata.setNowLongTime(nowLongTime);
			ccdata.setReceiveLongTime(receiveLongTime);

			daoService.addOneNewCcdata(ccdata);

			return "addCcdata.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("新增冷藏箱数据异常");
		}
		return null;
	}

}
