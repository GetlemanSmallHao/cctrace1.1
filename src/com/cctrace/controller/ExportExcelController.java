package com.cctrace.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cctrace.entity.Alert;
import com.cctrace.entity.BindTable;
import com.cctrace.entity.Ccdata;
import com.cctrace.entity.Ccdata1;
import com.cctrace.entity.CommandStore;
import com.cctrace.entity.Container;
import com.cctrace.entity.Msg;
import com.cctrace.entity.OurCcdata;
import com.cctrace.entity.OurCcdata1;
import com.cctrace.entity.User;
import com.cctrace.service.DaoService;
import com.cctrace.utils.ConstantCode;
import com.cctrace.utils.DateUtil;
import com.cctrace.utils.JsonResult;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;

/**
 * 测试POI 报表导出
 * 
 * @author wang-pc
 * 
 */
@Controller
@RequestMapping(value = "/pc/ExportExcel")
// 报表导出
public class ExportExcelController {

	@Resource
	DaoService daoService; 

	/**
	 * 导出预警报表
	 * @param response 
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/getAlertExportExcel")
	public void getAlertExportExcel(HttpServletResponse response,HttpServletRequest request,String containerId,String startTime,String endTime) throws UnsupportedEncodingException {
		response.setContentType("application/binary;charset=UTF-8");
		String fileName = new String((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).getBytes(),"UTF-8");
		fileName = "Alert "+fileName;
		response.setHeader("Content-disposition", "attachment; filename="+fileName+".xls");
		//ServletOutputStream out = response.getOutputStream();
		
		String[] titles = {"冷藏箱编号","报警开始时间","报警编号","报警类型","报警内容","报警时维度","报警时经度","报警位置","读取状态"};
		
		if((containerId==null||"".equals(containerId))&&(startTime==null||"".equals(startTime)||(endTime==null||"".equals(endTime)))){
			//默认查询
			Integer companyId = getCompanyIdOfCurrentUser(request);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("companyId", companyId);
			Date now = new Date();
			long longNow = DateUtil.getLongFromDate(now);
			long beginTime = longNow - (1 * 24 * 60 * 60 * 1000);
			map.put("beginTime", beginTime);
			List<Alert> alerts = daoService.getAlertsByCompanyIdInFourDays(map);
			Workbook alertWorkBook = getAlertWorkBook(titles,alerts);
			ServletOutputStream out;
			try {
				out = response.getOutputStream();
				alertWorkBook.write(out);
				out.flush();
			} catch (IOException e) {
				System.out.println("报表输出错误");
				e.printStackTrace();
			}
		}else if((startTime!=null&&(!"".equals(startTime))&&(endTime!=null&&(!"".equals(endTime))))){
			//精确查询(按照箱号 开始时间 结束时间)
			List<Alert> alerts = null;
			Map<String, Object> map = new HashMap<String, Object>();
			long longStart = DateUtil.getLongFromStr(startTime,
					"yyyy-MM-dd HH:mm:ss");
			long longEnd = DateUtil.getLongFromStr(endTime, "yyyy-MM-dd HH:mm:ss");
			map.put("containerId", containerId);
			map.put("startTime", longStart);
			map.put("endTime", longEnd);
			if (daoService.selectShowAlertInTwoTime(map).size() > 0) {
				alerts = daoService.selectShowAlertInTwoTime(map);
			}
			Workbook alertWorkBook = getAlertWorkBook(titles,alerts);
			ServletOutputStream out;
			try {
				out = response.getOutputStream();
				alertWorkBook.write(out);
				out.flush();
			} catch (IOException e) {
				System.out.println("报表输出错误");
				e.printStackTrace();
			}
		}
	}
	
	public Workbook getAlertWorkBook(String[] titles,List<Alert> alerts) { 
		Workbook wb = new HSSFWorkbook();
		Sheet createSheet = wb.createSheet("告警");
		Row firstRow = createSheet.createRow(0);
		CellStyle style = wb.createCellStyle();  
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());  
        style.setFillPattern(CellStyle.SOLID_FOREGROUND); 
		//填充标题行数据,并设置样式
		for (int i = 0; i < titles.length; i++) {
			Cell cell = firstRow.createCell(i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(style);
		}
		//填充数据行数据
		for(int i = 0; i < alerts.size(); i++){
			Alert alert = alerts.get(i);
			Row createRow = createSheet.createRow(i+1);
			createRow.createCell(0).setCellValue(alert.getContainerId()==null?"":alert.getContainerId());
			createRow.createCell(1).setCellValue(alert.getAlertTime()==null?"":alert.getAlertTime());
			
			if(alert.getAlarm_num()==null){
				createRow.createCell(2).setCellValue("");
			}else{
				createRow.createCell(2).setCellValue(alert.getAlarm_num());
			}
			createRow.createCell(3).setCellValue(alert.getAlertType()==null?"":alert.getAlertType());
			createRow.createCell(4).setCellValue(alert.getAlertContent()==null?"":alert.getAlertContent());
			if(alert.getLat()==null){
				createRow.createCell(5).setCellValue("");
			}else{
				createRow.createCell(5).setCellValue(alert.getLat());
			}
			if(alert.getLon()==null){
				createRow.createCell(6).setCellValue("");
			}else{
				createRow.createCell(6).setCellValue(alert.getLon());
			}
			createRow.createCell(7).setCellValue(alert.getGpsPosition()==null?"":alert.getGpsPosition());
			if("no".equals(alert.getReaded())){
				createRow.createCell(8).setCellValue("未读");
			}else{
				createRow.createCell(8).setCellValue("已读");
			}
		}
		//统一设置列宽 sheet.setColumnWidth(0, 252*width+323);
		createSheet.setColumnWidth(0, 252*12+323);
		createSheet.setColumnWidth(1, 252*18+323);
		createSheet.setColumnWidth(2, 252*18+323);
		createSheet.setColumnWidth(3, 252*18+323);
		createSheet.setColumnWidth(4, 252*18+323);
		createSheet.setColumnWidth(5, 252*45+323);
		createSheet.setColumnWidth(6, 252*12+323);
		createSheet.setColumnWidth(7, 252*12+323);
		createSheet.setColumnWidth(8, 252*18+323);
		return wb;
	}
	@ResponseBody
	@RequestMapping(value = "/getExportExcel")
	public JsonResult<Ccdata> getExportExcel(HttpServletResponse response) {

		String mess = "导出成功";
		int flag = ConstantCode.ERROR;
		Ccdata ccdata = null;

		try {
			response.setContentType("application/binary;charset=UTF-8");
			ServletOutputStream out = response.getOutputStream();
			String fileName = new String(
					(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
							.getBytes(),
					"UTF-8");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName + ".xls");
			String[] titles = { "用户编号", "用户姓名", "用户密码", "用户年龄" };

			try {
				// 第一步，创建一个workbook，对应一个Excel文件
				HSSFWorkbook workbook = new HSSFWorkbook();
				// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
				HSSFSheet hssfSheet = workbook.createSheet("sheet1");
				// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
				HSSFRow hssfRow = hssfSheet.createRow(0);
				// 第四步，创建单元格，并设置值表头 设置表头居中
				HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
				// 居中样式
				hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

				HSSFCell hssfCell = null;
				for (int i = 0; i < titles.length; i++) {
					hssfCell = hssfRow.createCell(i);// 列索引从0开始
					hssfCell.setCellValue(titles[i]);// 列名1
					hssfCell.setCellStyle(hssfCellStyle);// 列居中显示
				}
				// 第七步，将文件输出到客户端浏览器
				try {
					workbook.write(out);
					out.flush();
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
					return new JsonResult<Ccdata>(flag, mess, ccdata);
				}
			} catch (Exception e) {
				e.printStackTrace();
				mess = "导出信息失败！";
				return new JsonResult<Ccdata>(flag, mess, ccdata);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mess = "导出信息失败！";
			return new JsonResult<Ccdata>(flag, mess, ccdata);

		}
		flag = ConstantCode.SUCCESS;
		return new JsonResult<Ccdata>(flag, mess, ccdata);

	}

	/**
	 * 导入jackson包（1）。 导出全部数据
	 * 
	 * @param pn
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findSensorAllWithJson")
	public Msg findSensorAllWithJson(HttpServletRequest request,
			HttpServletResponse response, String containerId, String startTime,
			String endTime) {

		long startLongTime = DateUtil.getLongFromStr(startTime,
				"yyyy-MM-dd HH:mm:ss");
		long endLongTime = DateUtil.getLongFromStr(endTime,
				"yyyy-MM-dd HH:mm:ss");
		Map<String, Object> mapp = new HashMap<String, Object>();
		mapp.put("containerId", containerId);
		mapp.put("start", startLongTime);
		mapp.put("end", endLongTime);
		/* PageHelper.startPage(pn, 10); */
		List<Ccdata> ccdatas = daoService
				.selectCcdatasByContainerIdBetweenTowTimeASC(mapp);
		PageInfo page = new PageInfo(ccdatas, 5);
		List<OurCcdata> ourCcdatas = daoService
				.getOurCcdatasByContainerIdBetweenTowTime(mapp);
		BindTable bindTable = null;
		Container container = null;
		bindTable = daoService.getBindTableByContainerId(containerId);
		container = daoService.getContainerBycontarinId(containerId);
		try {
			response.setContentType("application/binary;charset=UTF-8");
			ServletOutputStream out = response.getOutputStream();
			String fileName = new String((new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(new Date())).getBytes(),
					"UTF-8");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName + ".xls");
			String[] titles = { "冷藏箱编号", "接收数据时间", "冷机电瓶电压", "回风温度", "冷机状态",
					"环境温度", "出风温度", "设定温度", "冷机运行模式", "车辆运行时长", "发动机运行时长" };
			HSSFWorkbook workbook = getWorkBook(container, ccdatas, titles);
			workbook.write(out);
			out.flush();
			out.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Msg.success().add("maps", ccdatas);
	}

	public HSSFWorkbook getWorkBook(Container container, List<Ccdata> ccdatas,
			String[] titles) {

		// 第一步，创建一个workbook，对应一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet hssfSheet = workbook.createSheet("sheet1");
		hssfSheet.setColumnWidth(0, 15 * 256);
		hssfSheet.setColumnWidth(1, 20 * 256);
		hssfSheet.setColumnWidth(2, 18 * 256);
		hssfSheet.setColumnWidth(3, 10 * 256);
		hssfSheet.setColumnWidth(4, 10 * 256);
		hssfSheet.setColumnWidth(5, 10 * 256);
		hssfSheet.setColumnWidth(6, 10 * 256);
		hssfSheet.setColumnWidth(7, 10 * 256);
		hssfSheet.setColumnWidth(8, 18 * 256);
		hssfSheet.setColumnWidth(9, 18 * 256);
		hssfSheet.setColumnWidth(10, 18 * 256);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow hssfRow = hssfSheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
		// 居中样式
		hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCell hssfCell = null;
		for (int i = 0; i < titles.length; i++) {
			hssfCell = hssfRow.createCell(i);// 列索引从0开始
			hssfCell.setCellValue(titles[i]);// 列名1
			hssfCell.setCellStyle(hssfCellStyle);// 列居中显示
		}

		for (int i = 0; i < ccdatas.size(); i++) {
			System.out.println("1111" + ccdatas.get(i).getNowTime());
			HSSFRow rowContent = hssfSheet.createRow(i + 1);

			// 单元格
			HSSFCell cell0 = rowContent.createCell(0);
			if (ccdatas.get(i).getContainerId() != null) {
				cell0.setCellValue(ccdatas.get(i).getContainerId());
				cell0.setCellStyle(hssfCellStyle);
			} else {
				cell0.setCellValue("");
			}
			// 时间
			HSSFCell cell1 = rowContent.createCell(1);
			if (ccdatas.get(i).getNowTime() != null) {
				cell1.setCellValue(ccdatas.get(i).getNowTime());
				cell1.setCellStyle(hssfCellStyle);
			} else {
				cell1.setCellValue("");
			}
			// 电压
			HSSFCell cell2 = rowContent.createCell(2);
			if (ccdatas.get(i).getRefBatVol() != null
					&& ccdatas.get(i).getRefBatVol() > 15) {
				cell2.setCellValue(15);
				cell2.setCellStyle(hssfCellStyle);
			} else if (ccdatas.get(i).getRefBatVol() != null
					&& ccdatas.get(i).getRefBatVol() < 0) {
				cell2.setCellValue(0);
				cell2.setCellStyle(hssfCellStyle);
			} else if (ccdatas.get(i).getRefBatVol() != null) {
				cell2.setCellValue(ccdatas.get(i).getRefBatVol());
				cell2.setCellStyle(hssfCellStyle);
			} else {
				cell2.setCellValue("");
			}

			// 回风
			HSSFCell cell3 = rowContent.createCell(3);
			if (ccdatas.get(i).getBackWindTemp() != null) {
				cell3.setCellValue(ccdatas.get(i).getBackWindTemp());
				cell3.setCellStyle(hssfCellStyle);
			} else {
				cell3.setCellValue("");
			}

			// 冷机状态
			HSSFCell cell4 = rowContent.createCell(4);
			if (ccdatas.get(i).getWorkMode() != null) {
				cell4.setCellValue(ccdatas.get(i).getWorkMode());
				cell4.setCellStyle(hssfCellStyle);
			} else {
				cell4.setCellValue("");
			}
			// 环境
			HSSFCell cell5 = rowContent.createCell(5);
			if (ccdatas.get(i).getEnviTemp() != null) {
				cell5.setCellValue(ccdatas.get(i).getEnviTemp());
				cell5.setCellStyle(hssfCellStyle);
			} else {
				cell5.setCellValue("");
			}
			// 出风

			HSSFCell cell6 = rowContent.createCell(6);
			if (ccdatas.get(i).getChuWindTemp() != null) {
				cell6.setCellValue(ccdatas.get(i).getChuWindTemp());
				cell6.setCellStyle(hssfCellStyle);
			} else {
				cell6.setCellValue("");
			}
			// 设定温度
			HSSFCell cell7 = rowContent.createCell(7);
			if (ccdatas.get(i).getTempSet() != null) {
				cell7.setCellValue(ccdatas.get(i).getTempSet());
				cell7.setCellStyle(hssfCellStyle);
			} else {
				cell7.setCellValue("");
			}
			// 冷机运行模式
			HSSFCell cell8 = rowContent.createCell(8);
			if (ccdatas.get(i).getRefRunMode() != null) {
				cell8.setCellValue(ccdatas.get(i).getRefRunMode());
				cell8.setCellStyle(hssfCellStyle);
			} else {
				cell8.setCellValue("");
			}
			// 车辆时长
			HSSFCell cell9 = rowContent.createCell(9);
			if (ccdatas.get(i).getVecRunTime() != null) {
				cell9.setCellValue(ccdatas.get(i).getVecRunTime());
				cell9.setCellStyle(hssfCellStyle);
			} else {
				cell9.setCellValue("");
			}
			// 发动机时长

			HSSFCell cell10 = rowContent.createCell(10);
			if (ccdatas.get(i).getEngRunTime() != null) {
				cell10.setCellValue(ccdatas.get(i).getEngRunTime());
				cell10.setCellStyle(hssfCellStyle);
			} else {
				cell10.setCellValue("");
			}
		}

		return workbook;
	}

	/**
	 * 导出主页数据
	 * 
	 * @param pn
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findBindTableJsonExcel")
	public Msg findBindTableJsonExcel(HttpServletRequest request,
			HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		BindTable bindTable = null;
		Container container = null;
		OurCcdata1 ourCcdata = null;
		Integer count = null;
		String refSwiState = "";
		List<Ccdata1> ccdatas = daoService.selectCcdatasBycompanyId1(user
				.getCompanyId());
		Collections.sort(ccdatas, new MapComparator());
		for (Ccdata1 ccdata1 : ccdatas) {
			bindTable = daoService.getBindTableByContainerId(ccdata1
					.getContainerId());
			container = daoService.getContainerBycontarinId(ccdata1
					.getContainerId());
			ourCcdata = daoService.selectOurCcdataBycontainerId1(ccdata1
					.getContainerId());
			Map<String, Object> mapAlert = new HashMap<String, Object>();
			Date date = new Date();
			long start = DateUtil.getLongFromDate(date);
			long end = start - 1 * 60 * 1000;
			mapAlert.put("containerId", ccdata1.getContainerId());
			mapAlert.put("startTime", end);
			mapAlert.put("endTime", start);
			count = daoService.selectAlertInBeforeSeconds(mapAlert);
		}
		try {
			response.setContentType("application/binary;charset=UTF-8");
			ServletOutputStream out = response.getOutputStream();
			String fileName = new String((new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(new Date())).getBytes(),
					"UTF-8");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName + ".xls");
			String[] titles = { "冷藏箱编号", "GPS接收时间","地理位置","冷机告警", "备注", "货物类型", "油位", "设定温度",
					"回风温度", "冷机电瓶电压", "GPS电量", "开关机状态", "通讯状态", "设备编号", "系统时间",
					"冷机类型", "下货站", "场站" };
			HSSFWorkbook workbook = getWorkBookIndex(container, ccdatas,
					bindTable, ourCcdata, count, titles);
			workbook.write(out);
			out.flush();
			out.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Msg.success().add("maps", ccdatas);
	}

	public HSSFWorkbook getWorkBookIndex(Container container,
			List<Ccdata1> ccdatas, BindTable bindTable, OurCcdata1 ourCcdata,
			Integer count, String[] titles) {

		// 第一步，创建一个workbook，对应一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet hssfSheet = workbook.createSheet("sheet1");
		hssfSheet.setColumnWidth(0, 15 * 256);
		hssfSheet.setColumnWidth(1, 20 * 256);
		hssfSheet.setColumnWidth(2, 25 * 256);
		hssfSheet.setColumnWidth(3, 10 * 256);
		hssfSheet.setColumnWidth(4, 15 * 256);
		hssfSheet.setColumnWidth(5, 10 * 256);
		hssfSheet.setColumnWidth(6, 10 * 256);
		hssfSheet.setColumnWidth(7, 10 * 256);
		hssfSheet.setColumnWidth(8, 10 * 256);
		hssfSheet.setColumnWidth(9, 12 * 256);
		hssfSheet.setColumnWidth(13, 18 * 256);
		hssfSheet.setColumnWidth(14, 18 * 256);
		hssfSheet.setColumnWidth(15, 15 * 256);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow hssfRow = hssfSheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
		// 居中样式
		hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCell hssfCell = null;
		for (int i = 0; i < titles.length; i++) {
			hssfCell = hssfRow.createCell(i);// 列索引从0开始
			hssfCell.setCellValue(titles[i]);// 列名1
			hssfCell.setCellStyle(hssfCellStyle);// 列居中显示
		}

		for (int i = 0; i < ccdatas.size(); i++) {
			System.out.println("1111" + ccdatas.get(i).getNowTime());
			HSSFRow rowContent = hssfSheet.createRow(i + 1);
			
			
			Boolean boo = true;
			if("off".equals(ccdatas.get(i).getRefSwiState())){
				boo = false;
			}
			//创建文字置灰的样式
			HSSFFont font = workbook.createFont();
			font.setColor(HSSFColor.GREY_25_PERCENT.index);
			
			
			// 冷藏箱编号
			HSSFCell cell0 = rowContent.createCell(0);
			if (ccdatas.get(i).getContainerId() != null) {
				cell0.setCellValue(ccdatas.get(i).getContainerId());
				cell0.setCellStyle(hssfCellStyle);
			} else {
				cell0.setCellValue("");
			}
			
			ourCcdata = daoService.selectOurCcdataBycontainerId1(ccdatas.get(i)
					.getContainerId());
			
			// GPS时间
			HSSFCell cell1 = rowContent.createCell(1);
			if (ourCcdata.getNowTime() != null) {
				cell1.setCellValue(ourCcdata.getNowTime());
				cell1.setCellStyle(hssfCellStyle);
			} else {
				cell1.setCellValue("");
			}
			
			//地理位置
			HSSFCell cell2 = rowContent.createCell(2);
			if (ourCcdata.getGpsPosition() != null) {
				cell2.setCellValue(ourCcdata.getGpsPosition());
				cell2.setCellStyle(hssfCellStyle);
			} else {
				cell2.setCellValue("");
			}
			
			// 冷机告警
			HSSFCell cell3 = rowContent.createCell(3);
			// 开关机状态
			if (StringUtil.isNotEmpty(ccdatas.get(i).getRefSwiState())) {

				String refSwiState = ccdatas.get(i).getRefSwiState();
				// 显示冷机是否报警
				if (refSwiState.equals("on") && count > 0) {
					cell3.setCellValue("是");
					cell3.setCellStyle(hssfCellStyle);
				} else if (refSwiState.equals("on") && (count <= 0)) {
					cell3.setCellValue("不");
					cell3.setCellStyle(hssfCellStyle);
				} else {
					cell3.setCellValue("");
				}
			}
			// 备注
			HSSFCell cell4 = rowContent.createCell(4);
			bindTable = daoService.getBindTableByContainerId(ccdatas.get(i)
					.getContainerId());
			if (bindTable.getRemark() != null) {
				cell4.setCellValue(bindTable.getRemark());
				cell4.setCellStyle(hssfCellStyle);
			} else {
				cell4.setCellValue("");
			}
			// 货物类型
			HSSFCell cell5 = rowContent.createCell(5);
			if (bindTable.getCarGoType() != null) {
				cell5.setCellValue(bindTable.getCarGoType());
				cell5.setCellStyle(hssfCellStyle);
			} else {
				cell5.setCellValue("");
			}

			// 油位
			HSSFCell cell6 = rowContent.createCell(6);
			if (ourCcdata.getOilLevel() != null) {
				cell6.setCellValue(ourCcdata.getOilLevel());
				cell6.setCellStyle(hssfCellStyle);
			} else {
				cell6.setCellValue("");
			}
			// 设定温度
			HSSFCell cell7 = rowContent.createCell(7);
			if (ccdatas.get(i).getTempSet() != null) {
				cell7.setCellValue(ccdatas.get(i).getTempSet());
				cell7.setCellStyle(hssfCellStyle);
			} else {
				cell7.setCellValue("");
			}
			if(!boo){
				HSSFCellStyle sst = workbook.createCellStyle();
				sst.cloneStyleFrom(hssfCellStyle);
				sst.setFont(font);
				cell7.setCellStyle(sst);
			}
			// 回风温度
			HSSFCell cell8 = rowContent.createCell(8);
			if (ccdatas.get(i).getBackWindTemp() != null) {
				cell8.setCellValue(ccdatas.get(i).getBackWindTemp());
				cell8.setCellStyle(hssfCellStyle);
			} else {
				cell8.setCellValue("");
			}
			if(!boo){
				HSSFCellStyle sst = workbook.createCellStyle();
				sst.cloneStyleFrom(hssfCellStyle);
				sst.setFont(font);
				cell8.setCellStyle(sst);
			}
			// 冷机电压
			HSSFCell cell9 = rowContent.createCell(9);
			/*
			 * if (ccdatas.get(i).getRefBatVol() != null) {
			 * cell19.setCellValue(ccdatas.get(i).getRefBatVol());
			 * cell19.setCellStyle(hssfCellStyle); } else {
			 * cell19.setCellValue(""); }
			 */
			if (ccdatas.get(i).getRefBatVol() != null
					&& ccdatas.get(i).getRefBatVol() > 15) {
				cell9.setCellValue(15);
				cell9.setCellStyle(hssfCellStyle);
			} else if (ccdatas.get(i).getRefBatVol() != null
					&& ccdatas.get(i).getRefBatVol() < 0) {
				cell9.setCellValue(0);
				cell9.setCellStyle(hssfCellStyle);
			} else if (ccdatas.get(i).getRefBatVol() != null) {
				cell9.setCellValue(ccdatas.get(i).getRefBatVol());
				cell9.setCellStyle(hssfCellStyle);
			} else {
				cell9.setCellValue("");
			}
			if(!boo){
				HSSFCellStyle sst = workbook.createCellStyle();
				sst.cloneStyleFrom(hssfCellStyle);
				sst.setFont(font);
				cell9.setCellStyle(sst);
			}

			// GPS电量
			HSSFCell cell10 = rowContent.createCell(10);
			/*
			 * if (ourCcdata.getGpsPower() != null) {
			 * cell10.setCellValue(ourCcdata.getGpsPower());
			 * cell10.setCellStyle(hssfCellStyle); } else {
			 * cell10.setCellValue(""); }
			 */
			if (ourCcdata.getGpsPower() != null && ourCcdata.getGpsPower() > 15) {
				cell10.setCellValue(15);
				cell10.setCellStyle(hssfCellStyle);
			} else if (ourCcdata.getGpsPower() != null
					&& ourCcdata.getGpsPower() < 0) {
				cell10.setCellValue(0);
				cell10.setCellStyle(hssfCellStyle);
			} else if (ourCcdata.getGpsPower() != null) {
				cell10.setCellValue(ourCcdata.getGpsPower());
				cell10.setCellStyle(hssfCellStyle);
			} else {
				cell10.setCellValue("");
			}

			// 开关机状态
			HSSFCell cell11 = rowContent.createCell(11);
			if (ccdatas.get(i).getRefSwiState() != null) {
				cell11.setCellValue(ccdatas.get(i).getRefSwiState());
				cell11.setCellStyle(hssfCellStyle);
			} else {
				cell11.setCellValue("");
			}
			// 通讯状态
			HSSFCell cell12 = rowContent.createCell(12);
			if (ccdatas.get(i).getReceiveLongTime() != null) {
				long longTimeGet = ccdatas.get(i).getReceiveLongTime();
				Date nowDate = DateUtil.getNowDate();
				long longTimeNow = DateUtil.getLongFromDate(nowDate);
				if ((longTimeNow - longTimeGet) > (2 * 60000)) {
					cell12.setCellValue("断开");
					cell12.setCellStyle(hssfCellStyle);
				} else {
					cell12.setCellValue("连接");
					cell12.setCellStyle(hssfCellStyle);
				}
			} else {
				cell12.setCellValue("");
			}

			// 设备编号
			HSSFCell cell13 = rowContent.createCell(13);
			if (ccdatas.get(i).getDeviceId() != null) {
				cell13.setCellValue(ccdatas.get(i).getDeviceId());
				cell13.setCellStyle(hssfCellStyle);
			} else {
				cell13.setCellValue("");
			}
			// 系统时间
			HSSFCell cell14 = rowContent.createCell(14);
			if (ccdatas.get(i).getNowTime() != null) {
				cell14.setCellValue(ccdatas.get(i).getNowTime());
				cell14.setCellStyle(hssfCellStyle);
			} else {
				cell14.setCellValue("");
			}

			// 冷机类型
			HSSFCell cell15 = rowContent.createCell(15);
			if (ccdatas.get(i).getChillerType() != null) {
				cell15.setCellValue(ccdatas.get(i).getChillerType());
				cell15.setCellStyle(hssfCellStyle);
			} else {
				cell15.setCellValue("");
			}

			// 下货站
			HSSFCell cell16 = rowContent.createCell(16);
			if (bindTable.getTheNextStationId() != null) {
				cell16.setCellValue(daoService.getTheNextStationById(
						bindTable.getTheNextStationId()).getStationName());
				cell16.setCellStyle(hssfCellStyle);
			} else {
				cell16.setCellValue("");
			}
			// 场站
			HSSFCell cell17 = rowContent.createCell(17);
			if (bindTable.getYardId() != null) {
				cell17.setCellValue(daoService.getYardbyId(
						bindTable.getYardId()).getYardName());
				cell17.setCellStyle(hssfCellStyle);
			} else {
				cell17.setCellValue("");
			}

		}

		return workbook;
	}

	/**
	 * 
	 * 导出自研设备的信息表
	 */

	@ResponseBody
	@RequestMapping("/findOurCcdataallAllWithJson")
	public Msg findOurCcdataallAllWithJson(HttpServletRequest request,
			HttpServletResponse response, String containerId, String startTime,
			String endTime) {

		long startLongTime = DateUtil.getLongFromStr(startTime,
				"yyyy-MM-dd HH:mm:ss");
		long endLongTime = DateUtil.getLongFromStr(endTime,
				"yyyy-MM-dd HH:mm:ss");
		Map<String, Object> mapp = new HashMap<String, Object>();
		mapp.put("containerId", containerId);
		mapp.put("start", startLongTime);
		mapp.put("end", endLongTime);
		List<OurCcdata> ourCcdatas = daoService
				.selectOurCcdatasByContainerIdBetweenTowTimeASC(mapp);
		try {
			response.setContentType("application/binary;charset=UTF-8");
			ServletOutputStream out = response.getOutputStream();
			String fileName = new String((new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(new Date())).getBytes(),
					"UTF-8");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName + ".xls");
			String[] titles = { "冷藏箱编号", "接收数据时间", "箱内湿度", "箱尾温度", "gps电量",
					"油箱油位", "后门状态 ", "经度", "纬度", "地理位置" };
			HSSFWorkbook workbook = getWorkBookOurCcdata(ourCcdatas, titles);
			workbook.write(out);
			out.flush();
			out.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Msg.success().add("maps", ourCcdatas);
	}

	public HSSFWorkbook getWorkBookOurCcdata(List<OurCcdata> ourccdatas,
			String[] titles) {

		// 第一步，创建一个workbook，对应一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet hssfSheet = workbook.createSheet("sheet1");
		hssfSheet.setColumnWidth(0, 15 * 256);
		hssfSheet.setColumnWidth(1, 18 * 256);
		hssfSheet.setColumnWidth(9, 45 * 256);
		/*
		 * hssfSheet.setColumnWidth(2, 18 * 256); hssfSheet.setColumnWidth(3, 10
		 * * 256); hssfSheet.setColumnWidth(4, 10 * 256);
		 * hssfSheet.setColumnWidth(5, 10 * 256); hssfSheet.setColumnWidth(6, 10
		 * * 256); hssfSheet.setColumnWidth(7, 10 * 256);
		 * hssfSheet.setColumnWidth(8, 18 * 256); hssfSheet.setColumnWidth(9, 10
		 * * 256); hssfSheet.setColumnWidth(10, 10 * 256);
		 * hssfSheet.setColumnWidth(11, 10 * 256); hssfSheet.setColumnWidth(12,
		 * 10 * 256); hssfSheet.setColumnWidth(13, 10 * 256);
		 * hssfSheet.setColumnWidth(14, 10 * 256); hssfSheet.setColumnWidth(15,
		 * 16 * 256); hssfSheet.setColumnWidth(16, 16 * 256);
		 */
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow hssfRow = hssfSheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
		// 居中样式
		hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCell hssfCell = null;
		for (int i = 0; i < titles.length; i++) {
			hssfCell = hssfRow.createCell(i);// 列索引从0开始
			hssfCell.setCellValue(titles[i]);// 列名1
			hssfCell.setCellStyle(hssfCellStyle);// 列居中显示
		}

		for (int i = 0; i < ourccdatas.size(); i++) {
			System.out.println("1111" + ourccdatas.get(i).getNowTime());
			HSSFRow rowContent = hssfSheet.createRow(i + 1);

			// 单元格
			HSSFCell cell0 = rowContent.createCell(0);
			if (ourccdatas.get(i).getContainerId() != null) {
				cell0.setCellValue(ourccdatas.get(i).getContainerId());
			} else {
				cell0.setCellValue("");
			}
			// 时间
			HSSFCell cell1 = rowContent.createCell(1);
			if (ourccdatas.get(i).getNowTime() != null) {
				cell1.setCellValue(ourccdatas.get(i).getNowTime());
			} else {
				cell1.setCellValue("");
			}
			// 油箱温度
			// HSSFCell cell2 = rowContent.createCell(2);
			// if (ourccdatas.get(i).getOilTemp() != null) {
			// cell2.setCellValue(ourccdatas.get(i).getOilTemp());
			// } else {
			// cell2.setCellValue("");
			// }

			// 箱内湿度
			HSSFCell cell2 = rowContent.createCell(2);
			if (ourccdatas.get(i).getBoxHum() != null) {
				cell2.setCellValue(ourccdatas.get(i).getBoxHum());
			} else {
				cell2.setCellValue("");
			}

			// 箱尾温度
			HSSFCell cell3 = rowContent.createCell(3);
			if (ourccdatas.get(i).getTailBoxTemp() != null) {
				cell3.setCellValue(ourccdatas.get(i).getTailBoxTemp());
			} else {
				cell3.setCellValue("");
			}

			// gps电量
			HSSFCell cell4 = rowContent.createCell(4);
			if (ourccdatas.get(i).getGpsPower() != null
					&& ourccdatas.get(i).getGpsPower() > 15) {
				cell4.setCellValue(15);
			} else if (ourccdatas.get(i).getGpsPower() != null
					&& ourccdatas.get(i).getGpsPower() < 0) {
				cell4.setCellValue(0);
			} else if (ourccdatas.get(i).getGpsPower() != null) {
				cell4.setCellValue(ourccdatas.get(i).getGpsPower());
			} else {
				cell4.setCellValue("");
			}
			// 油箱油位
			HSSFCell cell5 = rowContent.createCell(5);

			if (ourccdatas.get(i).getOilLevel() != null) {
				cell5.setCellValue(ourccdatas.get(i).getOilLevel());
			} else {
				cell5.setCellValue("");
			}
			// 后门状态
			HSSFCell cell6 = rowContent.createCell(6);
			if (ourccdatas.get(i).getBackDoorState() != null) {
				cell6.setCellValue(ourccdatas.get(i).getBackDoorState());
			} else {
				cell6.setCellValue("");
			}
			// 精度
			HSSFCell cell7 = rowContent.createCell(7);
			if (ourccdatas.get(i).getLon() != null) {
				cell7.setCellValue(ourccdatas.get(i).getLon());
			} else {
				cell7.setCellValue("");
			}
			// 纬度
			HSSFCell cell8 = rowContent.createCell(8);
			if (ourccdatas.get(i).getLat() != null) {
				cell8.setCellValue(ourccdatas.get(i).getLat());
			} else {
				cell8.setCellValue("");
			}
			// 地理位置
			HSSFCell cell9 = rowContent.createCell(9);
			if (ourccdatas.get(i).getGpsPosition() != null) {
				cell9.setCellValue(ourccdatas.get(i).getGpsPosition());
				cell9.setCellStyle(hssfCellStyle);
			} else {
				cell9.setCellValue("");
			}

		}

		return workbook;
	}

	/**
	 * 导入jackson包（2）。 导出回风温度
	 * 
	 * @param pn
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findSensorBackWindTempWithJson")
	public Msg findSensorBackWindTempWithJson(HttpServletRequest request,
			HttpServletResponse response, String containerId, String startTime,
			String endTime) {

		long startLongTime = DateUtil.getLongFromStr(startTime,
				"yyyy-MM-dd HH:mm:ss");
		long endLongTime = DateUtil.getLongFromStr(endTime,
				"yyyy-MM-dd HH:mm:ss");
		Map<String, Object> mapp = new HashMap<String, Object>();
		mapp.put("containerId", containerId);
		mapp.put("start", startLongTime);
		mapp.put("end", endLongTime);
		List<Ccdata> ccdatas = daoService
				.getCcdatasByContainerIdBetweenTowTime(mapp);

		BindTable bindTable = null;
		Container container = null;
		bindTable = daoService.getBindTableByContainerId(containerId);
		container = daoService.getContainerBycontarinId(containerId);

		try {
			response.setContentType("application/binary;charset=UTF-8");
			ServletOutputStream out = response.getOutputStream();
			String fileName = new String((new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(new Date())).getBytes(),
					"UTF-8");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName + ".xls");
			String[] titles = { "冷藏箱编号", "接收数据时间", "回风温度" };
			HSSFWorkbook workbook = getWorkBookBackWindTemp(ccdatas, titles);
			workbook.write(out);
			out.flush();
			out.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Msg.success().add("maps", ccdatas);
	}

	public HSSFWorkbook getWorkBookBackWindTemp(List<Ccdata> ccdatas,
			String[] titles) {

		// 第一步，创建一个workbook，对应一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet hssfSheet = workbook.createSheet("sheet1");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow hssfRow = hssfSheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
		// 居中样式
		hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCell hssfCell = null;
		for (int i = 0; i < titles.length; i++) {
			hssfCell = hssfRow.createCell(i);// 列索引从0开始
			hssfCell.setCellValue(titles[i]);// 列名1
			hssfCell.setCellStyle(hssfCellStyle);// 列居中显示
		}

		for (int i = 0; i < ccdatas.size(); i++) {

			HSSFRow rowContent = hssfSheet.createRow(i + 1);

			// 单元格
			HSSFCell cell0 = rowContent.createCell(0);
			if (ccdatas.get(i).getContainerId() != null) {
				cell0.setCellValue(ccdatas.get(i).getContainerId());
			} else {
				cell0.setCellValue("");
			}

			HSSFCell cell1 = rowContent.createCell(1);
			if (ccdatas.get(i).getNowTime() != null) {
				cell1.setCellValue(ccdatas.get(i).getNowTime());
			} else {
				cell1.setCellValue("");
			}

			HSSFCell cell2 = rowContent.createCell(2);
			if (ccdatas.get(i).getBackWindTemp() != null) {
				cell2.setCellValue(ccdatas.get(i).getBackWindTemp());
			} else {
				cell2.setCellValue("");
			}

		}

		return workbook;
	}

	/**
	 * 导出日志
	 * 
	 * @param pn
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findExcelLog")
	public Msg findExcelLog(HttpServletRequest request,
			HttpServletResponse response, String containerId, String startTime,
			String endTime) {

		long startLongTime = DateUtil.getLongFromStr(startTime,
				"yyyy-MM-dd HH:mm:ss");
		long endLongTime = DateUtil.getLongFromStr(endTime,
				"yyyy-MM-dd HH:mm:ss");
		Map<String, Object> mapp = new HashMap<String, Object>();
		mapp.put("containerId", containerId);
		mapp.put("start", startLongTime);
		mapp.put("end", endLongTime);
		List<CommandStore> commandStores = null;
		User user = (User) request.getSession().getAttribute("user");
		mapp.put("companyId", user.getCompanyId());
		if (daoService.selectLogShowInTwoTimesWithContainerIdLike(mapp) != null) {
			commandStores = daoService
					.selectLogShowInTwoTimesWithContainerIdLike(mapp);
		}
		try {
			response.setContentType("application/binary;charset=UTF-8");
			ServletOutputStream out = response.getOutputStream();
			String fileName = new String((new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(new Date())).getBytes(),
					"UTF-8");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName + ".xls");
			String[] titles = { "操作者", "操作的冷藏箱", "操作时间", "操作内容", "设定值", "执行结果" };
			HSSFWorkbook workbook = getWorkExcelBookLog(commandStores, titles);
			workbook.write(out);
			out.flush();
			out.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Msg.success().add("maps", commandStores);
	}

	public HSSFWorkbook getWorkExcelBookLog(List<CommandStore> commandStores,
			String[] titles) {

		// 第一步，创建一个workbook，对应一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet hssfSheet = workbook.createSheet("sheet1");
		hssfSheet.setColumnWidth(0, 15 * 256);
		hssfSheet.setColumnWidth(1, 20 * 256);
		hssfSheet.setColumnWidth(2, 18 * 256);
		hssfSheet.setColumnWidth(3, 12 * 256);
		hssfSheet.setColumnWidth(4, 10 * 256);
		hssfSheet.setColumnWidth(5, 10 * 256);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow hssfRow = hssfSheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
		// 居中样式
		hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCell hssfCell = null;
		for (int i = 0; i < titles.length; i++) {
			hssfCell = hssfRow.createCell(i);// 列索引从0开始
			hssfCell.setCellValue(titles[i]);// 列名1
			hssfCell.setCellStyle(hssfCellStyle);// 列居中显示
		}

		for (int i = 0; i < commandStores.size(); i++) {

			HSSFRow rowContent = hssfSheet.createRow(i + 1);

			// 操做的人
			HSSFCell cell0 = rowContent.createCell(0);
			if (commandStores.get(i).getUserName() != null) {
				cell0.setCellValue(commandStores.get(i).getUserName());
				cell0.setCellStyle(hssfCellStyle);
			} else {
				cell0.setCellValue("");
			}

			HSSFCell cell1 = rowContent.createCell(1);
			if (commandStores.get(i).getContainerId() != null) {
				cell1.setCellValue(commandStores.get(i).getContainerId());
				cell1.setCellStyle(hssfCellStyle);
			} else {
				cell1.setCellValue("");
			}

			HSSFCell cell2 = rowContent.createCell(2);
			if (commandStores.get(i).getTime() != null) {
				cell2.setCellValue(commandStores.get(i).getTime());
				cell2.setCellStyle(hssfCellStyle);
			} else {
				cell2.setCellValue("");
			}

			HSSFCell cell3 = rowContent.createCell(3);
			if (commandStores.get(i).getCommand() != null) {
				if (commandStores.get(i).getCommand().equals("remoteSwiMac")) {
					cell3.setCellValue("远程开关机");
					cell3.setCellStyle(hssfCellStyle);
				} else if (commandStores.get(i).getCommand()
						.equals("refRunMode")) {
					cell3.setCellValue("冷机运行模式");
					cell3.setCellStyle(hssfCellStyle);
				} else if (commandStores.get(i).getCommand().equals("bootDef")) {
					cell3.setCellValue("启动除霜");
					cell3.setCellStyle(hssfCellStyle);
				} else if (commandStores.get(i).getCommand().equals("temSet")) {
					cell3.setCellValue("设定温度");
					cell3.setCellStyle(hssfCellStyle);
				} else if (commandStores.get(i).getCommand()
						.equals("selfCheck")) {
					cell3.setCellValue("自检");
					cell3.setCellStyle(hssfCellStyle);
				} else if (commandStores.get(i).getCommand()
						.equals("clearAlert"))
					cell3.setCellValue("清除告警");
				cell3.setCellStyle(hssfCellStyle);
			} else {
				cell3.setCellValue("");
			}

			HSSFCell cell4 = rowContent.createCell(4);
			if (commandStores.get(i).getValue() != null) {
				cell4.setCellValue(commandStores.get(i).getValue());
			} else {
				cell4.setCellValue("");
			}

			HSSFCell cell5 = rowContent.createCell(5);
			if (commandStores.get(i).getStatus() != null) {
				if (commandStores.get(i).getStatus().equals("Y")) {
					cell5.setCellValue("成功");
				} else {
					cell5.setCellValue("失败");
				}
			} else {
				cell5.setCellValue("");
			}

		}

		return workbook;
	}

	/**
	 * 导入jackson包（3）。 导出冷机电瓶电压
	 * 
	 * @param pn
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findSensorRefBatVolWithJson")
	public Msg findSensorRefBatVolWithJson(HttpServletRequest request,
			HttpServletResponse response, String containerId, String startTime,
			String endTime) {

		long startLongTime = DateUtil.getLongFromStr(startTime,
				"yyyy-MM-dd HH:mm:ss");
		long endLongTime = DateUtil.getLongFromStr(endTime,
				"yyyy-MM-dd HH:mm:ss");
		Map<String, Object> mapp = new HashMap<String, Object>();
		mapp.put("containerId", containerId);
		mapp.put("start", startLongTime);
		mapp.put("end", endLongTime);
		List<Ccdata> ccdatas = daoService
				.getCcdatasByContainerIdBetweenTowTime(mapp);

		BindTable bindTable = null;
		Container container = null;
		bindTable = daoService.getBindTableByContainerId(containerId);
		container = daoService.getContainerBycontarinId(containerId);

		try {
			response.setContentType("application/binary;charset=UTF-8");
			ServletOutputStream out = response.getOutputStream();
			String fileName = new String((new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(new Date())).getBytes(),
					"UTF-8");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName + ".xls");
			String[] titles = { "冷藏箱编号", "接收数据时间", "冷机电瓶电压" };
			HSSFWorkbook workbook = getWorkBookRefBatVol(ccdatas, titles);
			workbook.write(out);
			out.flush();
			out.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Msg.success().add("maps", ccdatas);
	}

	public HSSFWorkbook getWorkBookRefBatVol(List<Ccdata> ccdatas,
			String[] titles) {

		// 第一步，创建一个workbook，对应一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet hssfSheet = workbook.createSheet("sheet1");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow hssfRow = hssfSheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
		// 居中样式
		hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCell hssfCell = null;
		for (int i = 0; i < titles.length; i++) {
			hssfCell = hssfRow.createCell(i);// 列索引从0开始
			hssfCell.setCellValue(titles[i]);// 列名1
			hssfCell.setCellStyle(hssfCellStyle);// 列居中显示
		}

		for (int i = 0; i < ccdatas.size(); i++) {

			HSSFRow rowContent = hssfSheet.createRow(i + 1);

			// 单元格
			HSSFCell cell0 = rowContent.createCell(0);
			if (ccdatas.get(i).getContainerId() != null) {
				cell0.setCellValue(ccdatas.get(i).getContainerId());
			} else {
				cell0.setCellValue("");
			}

			HSSFCell cell1 = rowContent.createCell(1);
			if (ccdatas.get(i).getNowTime() != null) {
				cell1.setCellValue(ccdatas.get(i).getNowTime());
			} else {
				cell1.setCellValue("");
			}

			HSSFCell cell2 = rowContent.createCell(2);
			if (ccdatas.get(i).getRefBatVol() != null) {
				cell2.setCellValue(ccdatas.get(i).getRefBatVol());
			} else {
				cell2.setCellValue("");
			}

		}

		return workbook;
	}

	/**
	 * 导入jackson包（4）。 导出环境温度
	 * 
	 * @param pn
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findSensorEnviTempWithJson")
	public Msg findSensorEnviTempWithJson(HttpServletRequest request,
			HttpServletResponse response, String containerId, String startTime,
			String endTime) {

		long startLongTime = DateUtil.getLongFromStr(startTime,
				"yyyy-MM-dd HH:mm:ss");
		long endLongTime = DateUtil.getLongFromStr(endTime,
				"yyyy-MM-dd HH:mm:ss");
		Map<String, Object> mapp = new HashMap<String, Object>();
		mapp.put("containerId", containerId);
		mapp.put("start", startLongTime);
		mapp.put("end", endLongTime);
		List<Ccdata> ccdatas = daoService
				.getCcdatasByContainerIdBetweenTowTime(mapp);

		BindTable bindTable = null;
		Container container = null;
		bindTable = daoService.getBindTableByContainerId(containerId);
		container = daoService.getContainerBycontarinId(containerId);

		try {
			response.setContentType("application/binary;charset=UTF-8");
			ServletOutputStream out = response.getOutputStream();
			String fileName = new String((new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(new Date())).getBytes(),
					"UTF-8");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName + ".xls");
			String[] titles = { "冷藏箱编号", "接收数据时间", "环境温度" };
			HSSFWorkbook workbook = getWorkBookEnviTemp(ccdatas, titles);
			workbook.write(out);
			out.flush();
			out.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Msg.success().add("maps", ccdatas);
	}

	public HSSFWorkbook getWorkBookEnviTemp(List<Ccdata> ccdatas,
			String[] titles) {

		// 第一步，创建一个workbook，对应一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet hssfSheet = workbook.createSheet("sheet1");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow hssfRow = hssfSheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
		// 居中样式
		hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCell hssfCell = null;
		for (int i = 0; i < titles.length; i++) {
			hssfCell = hssfRow.createCell(i);// 列索引从0开始
			hssfCell.setCellValue(titles[i]);// 列名1
			hssfCell.setCellStyle(hssfCellStyle);// 列居中显示
		}

		for (int i = 0; i < ccdatas.size(); i++) {

			HSSFRow rowContent = hssfSheet.createRow(i + 1);

			// 单元格
			HSSFCell cell0 = rowContent.createCell(0);
			if (ccdatas.get(i).getContainerId() != null) {
				cell0.setCellValue(ccdatas.get(i).getContainerId());
			} else {
				cell0.setCellValue("");
			}

			HSSFCell cell1 = rowContent.createCell(1);
			if (ccdatas.get(i).getNowTime() != null) {
				cell1.setCellValue(ccdatas.get(i).getNowTime());
			} else {
				cell1.setCellValue("");
			}

			HSSFCell cell2 = rowContent.createCell(2);
			if (ccdatas.get(i).getEnviTemp() != null) {
				cell2.setCellValue(ccdatas.get(i).getEnviTemp());
			} else {
				cell2.setCellValue("");
			}

		}

		return workbook;
	}
	private User getCurrentUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		return user;
	}
	private Integer getCompanyIdOfCurrentUser(HttpServletRequest request) {
		User user = getCurrentUser(request);
		Integer companyId = user.getCompanyId();
		return companyId;
	}
}
