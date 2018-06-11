/*package com.cctrace.apiController;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.deser.ValueInstantiators.Base;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.alibaba.fastjson.JSONObject;
import com.cctrace.entity.BindTable;
import com.cctrace.entity.Ccdata;
import com.cctrace.entity.Container;
import com.cctrace.entity.QueryVo;
import com.cctrace.entity.Rail;
import com.cctrace.entity.User;
import com.cctrace.service.DaoService;
import com.cctrace.utils.BaseResult;
import com.cctrace.utils.ConstantCode;
import com.cctrace.utils.DataUtil;
import com.cctrace.utils.DateUtil;
import com.cctrace.utils.JsonResult;
import com.cctrace.utils.JsonUtils;
import com.cctrace.utils.ValidateUtil;
import com.github.pagehelper.util.StringUtil;

@Controller
@RequestMapping("/apiUser")
public class ApiUserController {

	@Resource(name = "daoService")
	private DaoService daoService;

	public void setDaoService(DaoService daoService) {
		this.daoService = daoService;
	}

	
	
	@RequestMapping("/showUser")
	@ResponseBody
	public void showUser(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam String username) throws IOException {
		User user = daoService.getUserByUsername(username);
		List<User> users = null;
		User user1 = null;
		JSONObject jsonObject = new JSONObject();
		if (user != null) {
			String role = user.getRole();
			if (role.equals("root")) {
				if (daoService.selectLimitUser(username) != null) {
					users = daoService.selectLimitUser(username);
					for (int i = 0; i < users.size(); i++) {
						if (StringUtil.isEmpty(users.get(i).getPhone())) {
							users.get(i).setPhone("");
						}
						if (StringUtil.isEmpty(users.get(i).getLastLoginTime())) {
							users.get(i).setLastLoginTime("");
						}
					}
					jsonObject.put("users", users);
				}

			} else if (role.equals("admin")) {
				if (daoService.selectLimitUser(username) != null) {
					users = daoService.selectLimitUser(username);
					for (int i = 0; i < users.size(); i++) {
						if (StringUtil.isEmpty(users.get(i).getPhone())) {
							users.get(i).setPhone("");
						}
						if (StringUtil.isEmpty(users.get(i).getLastLoginTime())) {
							users.get(i).setLastLoginTime("");
						}
					}
					jsonObject.put("users", users);
				}
			} else if (role.equals("common")) {
				user = daoService.getUserByUsername(username);
				jsonObject.put("users", user1);
			}
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonObject.toString());
			response.getWriter().flush();
			response.getWriter().close();
		}

	}
	
	
 *//**
 * 增加新用户
 * 
 * @param request
 * @return
 * @throws IOException
 */
/*
@RequestMapping("/addUser")
@ResponseBody
public BaseResult addUser(HttpServletRequest request) throws IOException {

BaseResult result = new BaseResult();
int flag = ConstantCode.ERROR;
String mess = "";

String username = request.getParameter("username");
String newUsername = request.getParameter("newUsername");
String password = request.getParameter("password");
String confirmPassword = request.getParameter("confirmPassword");
String phone = request.getParameter("phone");
String role = request.getParameter("role");
// if(username == null || password == null ||
// !confirmPassword.equals(password) || role == null||
// daoService.getUserByUsername(username) == null){
// System.out.println(username);
// System.out.println(password);
// System.out.println(confirmPassword);
// System.out.println(role);
// mess = "输入条件不符合";
// result.setFlag(flag);
// result.setMessage(mess);
// return result;
// }
if (daoService.getUserByUsername(newUsername) != null) {
	mess = "该用户名已存在";
	result.setFlag(flag);
	result.setMessage(mess);
	return result;
} else {
	if (StringUtil.isEmpty(newUsername) || StringUtil.isEmpty(password)
			|| StringUtil.isEmpty(confirmPassword)
			|| StringUtil.isEmpty(role) || StringUtil.isEmpty(phone)) {
		mess = "输入的字段不能为空";
		result.setFlag(flag);
		result.setMessage(mess);
		return result;
	} else {
		if (!confirmPassword.equals(password)) {
			mess = "两次输入的密码不一致";
			result.setFlag(flag);
			result.setMessage(mess);
			return result;
		} else {
			User user = daoService.getUserByUsername(username);
			User userNew = new User();
			userNew.setCompanyId(user.getCompanyId());
			userNew.setPassword(DataUtil.md5(password));
			userNew.setPhone(phone);
			userNew.setUsername(newUsername);
			userNew.setRole(role);
			userNew.setAddPerson(username);

			if (user.getRole().equals("root")) {
				if (!role.equals("root")) {
					int n = daoService.registNewUser(userNew);
					if (n > 0) {
						return BaseResult.success();
					} else {
						mess = "增加新用户失败";
						result.setFlag(flag);
						result.setMessage(mess);
						return result;
					}
				} else {
					mess = "增加用户权限过高";
					result.setFlag(flag);
					result.setMessage(mess);
					return result;
				}
			} else if (user.getRole().equals("admin")) {
				if (!role.equals("root") && !role.equals("admin")) {
					int n = daoService.registNewUser(userNew);
					if (n > 0) {
						return BaseResult.success();
					} else {
						mess = "增加新用户失败";
						result.setFlag(flag);
						result.setMessage(mess);
						return result;
					}
				} else {
					mess = "增加用户权限过高";
					result.setFlag(flag);
					result.setMessage(mess);
					return result;
				}
			} else {
				mess = "本用户没有权限增加新用户";
				result.setFlag(flag);
				result.setMessage(mess);
				return result;
			}

		}
	}
}
}

@RequestMapping("/showMyInfo")
@ResponseBody
public QueryVo showMyInfo(HttpServletRequest request,
	@RequestParam String username) throws IOException {
User user = daoService.getUserByUsername(username);
QueryVo queryVo = new QueryVo();
if (user != null) {
	if (daoService.showMyInfo(username) != null) {
		queryVo = daoService.showMyInfo(username);
		if (StringUtil.isEmpty(queryVo.getCompany().getCompanyName())) {
			queryVo.getCompany().setCompanyName("");
		}
		if (StringUtil.isEmpty(queryVo.getUser().getPhone())) {
			queryVo.getUser().setPhone("");
		}
		if (StringUtil.isEmpty(queryVo.getUser().getRole())) {
			queryVo.getUser().setRole("");
		}
	}
}
return queryVo;
}



 *//**
 * 修改个人信息，能修改电话或者是密码
 * 
 */
/*
 * @RequestMapping("/changeUserInfo")
 * 
 * @ResponseBody public BaseResult changeUserInfo(HttpServletRequest request,
 * 
 * @RequestParam String username, @RequestParam String passwordold,
 * 
 * @RequestParam String type, @RequestParam String content) { String mess =
 * "修改信息失败"; int flag = ConstantCode.ERROR; BaseResult result = new
 * BaseResult(); if (StringUtil.isEmpty(content)) { mess = "输入的内容不能为空"; } else {
 * if (daoService.getUserByUsername(username) != null) { Map<String, String> map
 * = new HashMap<>(); map.put("username", username); map.put("password",
 * DataUtil.md5(passwordold)); if (daoService.getUserByUsernameAndPassword(map)
 * != null) { User user = daoService.getUserByUsernameAndPassword(map); switch
 * (type) { case "changepassword": user.setPassword(DataUtil.md5(content)); int
 * n = daoService.modifyUserInfoById(user); if (n > 0) { mess = "修改密码成功"; flag =
 * ConstantCode.SUCCESS; } else { mess = "修改密码失败"; } break;
 * 
 * case "changephone": user.setPhone(content); int n1 =
 * daoService.modifyUserInfoById(user); if (n1 > 0) { mess = "修改电话成功"; flag =
 * ConstantCode.SUCCESS; } else { mess = "修改电话失败"; } break; }
 * 
 * } else { mess = "用户名或密码不正确"; } } else { mess = "修改的用户不存在"; } }
 * result.setFlag(flag); result.setMessage(mess); return result; } }
 */