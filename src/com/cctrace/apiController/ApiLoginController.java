package com.cctrace.apiController;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cctrace.entity.User;
import com.cctrace.service.DaoService;
import com.cctrace.utils.ConstantCode;
import com.cctrace.utils.DataUtil;
import com.cctrace.utils.DateUtil;
import com.cctrace.utils.JsonResult;
import com.github.pagehelper.util.StringUtil;
import com.google.zxing.common.StringUtils;

@Controller
@RequestMapping("/apiUser")
public class ApiLoginController {

	@Resource(name="daoService") 
	private DaoService daoService;
	
	@ResponseBody
	@RequestMapping("/login")
	public  void apiLogin(HttpServletRequest req, HttpServletResponse res,
			@RequestParam String username,@RequestParam String password) throws IOException{
		String mess = "";
		int flag = ConstantCode.ERROR;
		Map<String,String> map=new HashMap<String, String>();
		JSONObject jo=new JSONObject();
		map.put("username", username);
		map.put("password", DataUtil.md5(password));
		String lastLoginTime = DateUtil.getDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		User user = daoService.getUserByUsernameAndPassword(map);
		if(StringUtil.isEmpty(username)){
			mess="用户名不能为空，请重新登录";
		}
		else if(StringUtil.isEmpty(password)){
			mess="密码不能为空，请重新登录";
		}
		else if (user == null) {
			mess="用户不存在，请重新登录";
		}else if(user!=null){
			String spassword=user.getPassword();
			if(spassword.equals(DataUtil.md5(password))){
				mess = "登录成功";
				flag = ConstantCode.SUCCESS;
				user.setLastLoginTime(lastLoginTime);
				int n = daoService.modifyUserInfoById(user);
			}else{
				mess="密码错误，请重新登录";
			}
			
		}
		/*//验证码验证
		HttpSession session = request.getSession();
		String sessionVerifyCode = (String)session.getAttribute("verifyCode");
		if(!verifyCode.equalsIgnoreCase(sessionVerifyCode)){
			mess = "验证码有误";
			flag = ConstantCode.ERROR;
			
			
		}*/
		jo.put("flag", flag);
		jo.put("mess", mess);
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(jo.toString());
		res.getWriter().flush();
		res.getWriter().close();
	}
	
	
}
