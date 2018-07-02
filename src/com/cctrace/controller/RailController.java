package com.cctrace.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cctrace.entity.Rail;
import com.cctrace.entity.User;
import com.cctrace.service.DaoService;



@Controller
@RequestMapping(value="/pc/rail")

//围栏控制 
public class RailController {
	
	@Resource
	private DaoService daoService;
	public DaoService getRailService() {
		return daoService;
	}
	public void setRailService(DaoService daoService) {
		this.daoService = daoService;
	}
	/**
	 * 插入围栏
	 * @param map
	 * @return
	 */
	@RequestMapping("/insertRail")
	public  String  insertRail(HttpServletRequest request,Rail rail,ModelMap map){
		try {
			User user = (User) request.getSession().getAttribute("user");
			
			rail.setCompanyId(user.getCompanyId());
			
			int n=0;
			int x=0;
			
			String railName=rail.getRailName();
			List<Rail> railList=daoService.getRailsByCompanyId(user.getCompanyId());
			if(railList.size()>0){
				for(int i=0;i<railList.size();i++){
					if(railList.get(i).getRailName().equals(railName)){
						System.out.println("have same railname");
						break;
					}else{
						x++;
					}
					
				}
				if(x>0){
					if(rail.getRailLat()!=null){
						 n=daoService.addNewRail(rail);
					}
					if(n>0){
						System.out.println("insert rail success");
						
					}
					map.addAttribute("rail", rail);
					return "inbox.jsp";
				}
			}else{
				 n=daoService.addNewRail(rail);
				 if(n>0){
						System.out.println("insert rail success");
						
					}
				 map.addAttribute("rail", rail);
					return "inbox.jsp";
			}
			
			return "rail/findHadRailName.do";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("插入围栏异常");
		}
		return null;

	}
	
	@RequestMapping("/findHadRailName")
	public  String  findHadRailName(HttpServletRequest request,ModelMap map){
		try {
			User user = (User) request.getSession().getAttribute("user");
			//Rail rail=new Rail();
			
			//rail.setCompanyId(user.getCompanyId());
			List<Rail> railList=daoService.getRailsByCompanyId(user.getCompanyId());
			map.addAttribute("railList", railList);
			return "rail.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("异常");
		}
		return null;

	}
	@RequestMapping()
	public String find(HttpSession session){
		Object attribute = session.getAttribute("loginUser");
		User user = (User)session.getAttribute("onLineUser");
		return null;
	}
	
	/**
	 *查看围栏 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/findRail")
	public  String  findRail(HttpServletRequest request,ModelMap map){
		try {
			User user = (User) request.getSession().getAttribute("user");
			Rail rail=new Rail();
			rail.setCompanyId(user.getCompanyId());
			List lats=new ArrayList();
			List lons=new ArrayList();
			List rs=new ArrayList();
			List<Rail> railList=daoService.getRailsByCompanyId(user.getCompanyId());
			for(int i=0;i<railList.size();i++){
				lats.add(i, railList.get(i).getRailLat());
				lons.add(i,railList.get(i).getRailLon());
				rs.add(i,railList.get(i).getRadius());
			}
			map.addAttribute("lats", lats);
			map.addAttribute("lons", lons);
			map.addAttribute("rs", rs);
			return "showInbox.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("查看围栏异常");
		}
		return null;
	}
	/**
	 *查看围栏 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/findRailOne")
	public  String  findRailOne(HttpServletRequest request,ModelMap map,String railName){
		try {
			//railName="上海";
			User user = (User) request.getSession().getAttribute("user");
			System.out.println("###"+railName);
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("railName", railName);
			map1.put("companyId", user.getCompanyId());
			
			Rail rail = daoService.getRailByRailNameAndCompanyId(map1);
			
			List lats=new ArrayList();
			List lons=new ArrayList();
			List rs=new ArrayList();
			lats.add(0,rail.getRailLat());
			lons.add(0,rail.getRailLon());
			rs.add(0, rail.getRadius());

			map.addAttribute("lats", lats);
			map.addAttribute("lons", lons);
			map.addAttribute("rs", rs);
			return "showInbox.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("查看围栏异常");
		}
		return null;
	}
	/**
	 * 删除围栏
	 * @param railName
	 * @return
	 */
	@RequestMapping("/deleteRail")
	public  String  deleteRail(HttpServletRequest request,ModelMap map,@RequestParam(value="railName") String railName){
		try {
			User user = (User) request.getSession().getAttribute("user");
			Rail rail=new Rail();
			rail.setCompanyId(user.getCompanyId());
			System.out.println(railName);
			rail.setRailName(railName);
			int n=daoService.removeRailByRailName(rail.getRailName());
			if(n>0){
				System.out.println("delete rail success");
			}
			return "rail/findHadRailName.do";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("删除围栏异常");
		}
		return null;

	}
	
	
	
}
