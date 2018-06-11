package com.cctrace.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cctrace.entity.Ccdata;
import com.cctrace.service.DaoService;
import com.cctrace.utils.DateUtil;
import com.cctrace.utils.Series;

@Controller
@RequestMapping(value = "/pc")
public class PictureController {

	@Resource
	private DaoService daoService;


	@RequestMapping("/showPicture")
	@ResponseBody
	public void showPicture(HttpServletRequest request,
			HttpServletResponse response, ModelMap mmp,
			@RequestParam String containerId, @RequestParam String startTime,
			@RequestParam String endTime, @RequestParam String chooseTemp)
			throws ParseException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		long start = DateUtil.getLongFromStr(startTime, "yyyy-MM-dd HH:mm:ss");
		long end = DateUtil.getLongFromStr(endTime, "yyyy-MM-dd HH:mm:ss");
		map.put("start", start);
		map.put("end", end);
		map.put("containerId", containerId);
		List<Ccdata> ccdatas = null;
		JSONObject json = new JSONObject();
		if (daoService.getCcdatasByContainerIdBetweenTowTime(map) != null
				&& daoService.getCcdatasByContainerIdBetweenTowTime(map).size() > 0) {
			ccdatas = daoService.getCcdatasByContainerIdBetweenTowTime(map);
		}
		 //1.分类赋值(横坐标)
        List<String> category=new ArrayList<String>();
        //3.主要数据获取  (//主体内容 )
        List<Series> series = new ArrayList<Series>(); 
        List<Double> serisData=new ArrayList<Double>();   

		switch (chooseTemp) {
		// 设置温度
		case "setTemp":
			for (Ccdata ccdata : ccdatas) {
				category.add(ccdata.getNowTime());
				serisData.add(ccdata.getTempSet());
			}
			break;
		// 环境温度
		case "enviTemp":
			for (Ccdata ccdata : ccdatas) {
				// 获得x轴
				 category.add(ccdata.getNowTime());
				 serisData.add(ccdata.getEnviTemp());
			}
			break;
		// 回风温度
		case "backWindTemp":
			for (Ccdata ccdata : ccdatas) {
				category.add(ccdata.getNowTime());
				serisData.add(ccdata.getBackWindTemp());
			}
			break;
		}

		series.add(new Series("温度", "line", serisData));
        json.put("category", category); //这个是横坐标
        //2.图例动态赋值  
        List<String> legend = Arrays.asList("压力"); 
        json.put("legend", legend);
        json.put("series", series);
        System.out.println(series);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json.toString());

		/*
		 * json.xAxis = xAxis; json.yAxis = yAxis; json.tooltip = tooltip;
		 * json.legend = legend; json.series = series;
		 */
	}
}
