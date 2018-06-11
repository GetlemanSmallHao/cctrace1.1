package com.cctrace.apiController;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.web.bind.annotation.RequestMapping;

import com.cctrace.utils.DateUtil;

//
//@Controller
//@RequestMapping("/apiUser")

public class MyTest {
	//
	// @Resource(name="daoService")
	// private DaoService daoService;
	// public void setDaoService(DaoService daoService) {
	// this.daoService = daoService;
	// }
	//

	// /**
	// * 详细班列信息
	// *
	// */
	// @RequestMapping("/showBanLieTest")
	// @ResponseBody
	// public QueryVo showBanLieTest (HttpServletRequest request)
	// throws IOException {
	// List<QueryVo> queryVos = daoService.showAllBanLieByCompanyId(1);
	// QueryVo qVo = new QueryVo();
	//
	// for(int i = 0; i < queryVos.size(); i++){
	// if(queryVos.get(i).getBindTable().getContainerId().equals("con000010")){
	// qVo = queryVos.get(i);
	// System.out.println(queryVos.get(i).getContainer().getContainerId());
	// }
	// }
	// return qVo;
	// }
	public static void main(String[] args) throws ParseException {

		long startLongTime = DateUtil.getLongFromStr("2017-10-17 20:58:12",
				"yyyy-MM-dd HH:mm:ss");
//		long endLongTime = DateUtil.getLongFromStr("2017-10-13 18:16:55",
//				"yyyy-MM-dd HH:mm:ss");
//		System.out.println(endLongTime-startLongTime);
		System.out.println(startLongTime);
		/*
		System.out.println("-----------------------");
		Date now = new Date();
		long longNow = DateUtil.getLongFromDate(now);
		long beginTime = longNow - (30 * 24 * 60 * 60 * 1000);
		System.out.println(beginTime);*/

		/*
		 * String url =
		 * "http://localhost:8080/cctrace1.1/pc/rail/findHadRailName.do"; //
		 * boolean b = url.contains("/pc/"); // System.out.println(b);
		 */
		// String temp = "-60";
		// System.out.println((temp.split("-")[1]));

		/*Date selectTime1 = DateUtil.getDateFromStr("2017-03-01 14:25:00",
				"yyyy-MM-dd HH:mm:ss");
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(selectTime1);
		calendar.add(calendar.DATE, -1);
		selectTime1 = calendar.getTime();
		String timeBefore24hour = DateUtil.getDateStr(selectTime1,
				"yyyy-MM-dd HH:mm:ss");
		System.out.println(timeBefore24hour);*/
	/*	Integer i = 0%15==0?0:1;
				System.out.println(i);*/

	}
}
