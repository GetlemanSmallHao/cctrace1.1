package com.cctrace.apiController;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.cctrace.entity.User;
import com.cctrace.service.DaoService;
import com.cctrace.utils.DataUtil;

@Controller
public class Temporary {
	
	@Resource(name="daoService") 
	private DaoService daoService;
	public  User temp(){
		
		Map<String,String> map=new HashMap<String, String>();
		map.put("username", "lilei");
		map.put("password", "123456");
		User user = daoService.getUserByUsernameAndPassword(map);
		
		return user;
		
	}
	
}
