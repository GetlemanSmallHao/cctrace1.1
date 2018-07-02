package com.cctrace.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cctrace.entity.User;
import com.cctrace.service.DaoService;
import com.cctrace.utils.ConstantCode;
import com.cctrace.utils.DataUtil;
import com.cctrace.utils.DateUtil;
import com.cctrace.utils.JsonResult;
import com.cctrace.utils.ValidateUtil;

@Controller
@RequestMapping(value = "/user")
// 用户管理
public class UserController {

	@Resource(name = "daoService")
	private DaoService daoService;

	@RequestMapping(value = "/registNewUser")
	@ResponseBody
	public String registNewUser(User user) {
		try {
			System.out.println(user);
			String message = "";
			int regist = daoService.registNewUser(user);
			if (1 == regist) {
				message += "{\"code\":\"200\"}";
			} else {
				message += "{\"code\":\"400\"}";
			}
			return message;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("异常");
		}
		return null;
	}

	/**
	 * 用户登录
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/userLogin")
	public JsonResult<User> userLogin(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String username,
			@RequestParam String password, @RequestParam String ifCheck) {
		try {
			String mess = "";
			int flag = ConstantCode.ERROR;
			Map<String, String> map = new HashMap<String, String>();
			map.put("username", username);
			map.put("password", DataUtil.md5(password));
			String lastLoginTime = DateUtil.getDateStr(new Date(),
					"yyyy-MM-dd HH:mm:ss");
			User user = daoService.getUserByUsernameAndPassword(map);

			if (user == null) {
				mess = "输入信息有误，请重新登录";
			} else {
				user.setLastLoginTime(lastLoginTime);
				user.setPassword(DataUtil.md5(password));
				int modify = daoService.modifyUserInfoById(user);
				System.out.println(modify);
				mess = "登录成功";
				System.out.println(ifCheck);
				Cookie nameCookie = new Cookie("username", username);
				Cookie pswCookie = new Cookie("password", password);
				// 设置Cookie的父路径
				nameCookie.setPath(request.getContextPath() + "/");
				pswCookie.setPath(request.getContextPath() + "/");
				// 获取是否保存Cookie
				if (ifCheck.equals("no") || ifCheck == null) {// 不保存Cookie
					// Cookies
					nameCookie.setMaxAge(0);
					pswCookie.setMaxAge(0);
				} else {// 保存Cookie的时间长度，单位为秒
					nameCookie.setMaxAge(7 * 24 * 60 * 60);
					pswCookie.setMaxAge(7 * 24 * 60 * 60);
				}
				// 加入Cookie到响应头
				response.addCookie(nameCookie);
				response.addCookie(pswCookie);

				// 建立会话
				request.getSession().setAttribute("user", user);
				flag = ConstantCode.SUCCESS;
			}
			// 验证码验证

			return new JsonResult<User>(flag, mess, user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("用户登录异常");
		}
		return null;
	}

	/*	*//**
	 * 用户登录
	 * 
	 * @return
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/userLogin") public JsonResult<User>
	 * userLogin(HttpServletRequest request, HttpServletResponse response,
	 * 
	 * @RequestParam String username,
	 * 
	 * @RequestParam String password, @RequestParam String verifyCode,
	 * 
	 * @RequestParam String ifCheck) { System.out.println(verifyCode); String
	 * mess = ""; int flag = ConstantCode.ERROR; Map<String, String> map = new
	 * HashMap<String, String>(); map.put("username", username);
	 * map.put("password", DataUtil.md5(password)); String lastLoginTime =
	 * DateUtil.getDateStr(new Date(), "yyyy-MM-dd HH:mm:ss"); User user =
	 * daoService.getUserByUsernameAndPassword(map);
	 * 
	 * if (user == null) { mess = "输入信息有误，请重新登录"; } else {
	 * user.setLastLoginTime(lastLoginTime);
	 * user.setPassword(DataUtil.md5(password)); int modify =
	 * daoService.modifyUserInfoById(user); System.out.println(modify); mess =
	 * "登录成功"; Cookie nameCookie = null; Cookie pswCookie = null; //获取是否保存Cookie
	 * if(ifCheck.equals("no") || ifCheck == null){//不保存Cookie //Cookies
	 * nameCookie.setMaxAge(0); pswCookie.setMaxAge(0);
	 * }else{//保存Cookie的时间长度，单位为秒 nameCookie=new Cookie("username",username);
	 * pswCookie=new Cookie("password",password); //设置Cookie的父路径
	 * nameCookie.setPath(request.getContextPath()+"/");
	 * pswCookie.setPath(request.getContextPath()+"/");
	 * nameCookie.setMaxAge(7*24*60*60); pswCookie.setMaxAge(7*24*60*60); }
	 * //加入Cookie到响应头 response.addCookie(nameCookie);
	 * response.addCookie(pswCookie);
	 * 
	 * 
	 * // 建立会话 request.getSession().setAttribute("user", user); flag =
	 * ConstantCode.SUCCESS; } // 验证码验证 HttpSession session =
	 * request.getSession(); String sessionVerifyCode = (String)
	 * session.getAttribute("verifyCode"); if
	 * (!verifyCode.equalsIgnoreCase(sessionVerifyCode)) { mess = "验证码有误"; flag
	 * = ConstantCode.ERROR;
	 * 
	 * } return new JsonResult<User>(flag, mess, user); }
	 */
	/**
	 * 用户注册
	 */
	@ResponseBody
	@RequestMapping(value = "/userRegist")
	public JsonResult<User> userRegist(HttpServletRequest request,
			HttpServletResponse response, User user,
			@RequestParam(value = "passwordc") String passwordc) {
		try {
			String mess = "";
			int flag = ConstantCode.ERROR;
			Map<String, String> map = new HashMap<String, String>();
			map.put("username", user.getUsername());
			map.put("password", DataUtil.md5(user.getPassword()));
			String lastLoginTime = DateUtil.getDateStr(new Date(),
					"yyyy-MM-dd HH:mm:ss");
			System.out.println(map);
			// 1.非空
			if (!ValidateUtil.isValid(user.getUsername())) {
				mess = "用户名不能为空！";
				return new JsonResult<User>(flag, mess, user);
			}
			if (!ValidateUtil.isValid(user.getPassword())) {
				mess = "密码不能为空！";
				return new JsonResult<User>(flag, mess, user);
			}
			if (!ValidateUtil.isValid(passwordc)) {
				mess = "确认密码不能为空！";
				return new JsonResult<User>(flag, mess, user);
			}
			if (!ValidateUtil.isValid(user.getPhone())) {
				mess = "电话不能为空！";
				return new JsonResult<User>(flag, mess, user);
			}

			// 2.密码一致性
			if (!user.getPassword().equals(passwordc)) {
				mess = "密码不一致！";
				return new JsonResult<User>(flag, mess, user);

			}

			// 3.用户名占用

			if (daoService.getUserByUsername(user.getUsername()) != null) {
				mess = "用户名已占用,请重新输入!";
				return new JsonResult<User>(flag, mess, user);
			} else {
				// 电话已被占用
				if (daoService.getUserByPhone(user.getPhone()) != null) {
					mess = "电话已被占用!";
					return new JsonResult<User>(flag, mess, user);
				} else {
					User getUser = (User) request.getSession().getAttribute("user");
					user.setAddPerson(getUser.getUsername());
					user.setCompanyId(getUser.getCompanyId());
					user.setCompanyName(getUser.getCompanyName());
					user.setLastLoginTime(lastLoginTime);
					// MD5加密
					user.setPassword(DataUtil.md5(user.getPassword()));
					mess = "注册成功";
					flag = ConstantCode.SUCCESS;
					daoService.registNewUser(user);
				}
			}

			return new JsonResult<User>(flag, mess, user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("用户注册异常");
		}
		return null;
	}

	/**
	 * 用户查询
	 * 
	 * @return
	 */
	@RequestMapping("/findUser")
	public String findUser(HttpServletRequest request, ModelMap map) {

		try {
			List<User> userList = daoService.getUsersByCompanyId(((User) request
					.getSession().getAttribute("user")).getCompanyId());
			map.addAttribute("userList", userList);

			return "user.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("用户查询异常");
		}
		return null;

	}

	/**
	 * 用户模糊查询
	 * 
	 */
	@RequestMapping("/selectUserLikey")
	public String selectUserLikey(HttpServletRequest request, String username,
			ModelMap mapp) {
		try {
			List<User> userListLikey = daoService.selectUsersLikey(username);
			mapp.addAttribute("userListLikey", userListLikey);
			return "showSelectUseLikey.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("用户模糊查询异常");
		}
		return null;
	}

	/**
	 * 用户更新（电话）
	 */
	@ResponseBody
	@RequestMapping(value = "/userUpdateWithPhone")
	public JsonResult<User> userUpdatewithPhone(HttpServletRequest request,
			HttpServletResponse response, User user, String username, String id) {

		try {
			user.setId(Integer.parseInt(id));
			user.setUsername(username);

			String mess = "";
			int flag = ConstantCode.ERROR;
			String lastLoginTime = DateUtil.getDateStr(new Date(),
					"yyyy-MM-dd HH:mm:ss");

			// 1.非空
			if (!ValidateUtil.isValid(user.getPhone())) {
				mess = "电话不能为空！";
				return new JsonResult<User>(flag, mess, user);
			}
			// 2.电话占用
			if (daoService.getUserByPhone(user.getPhone()) != null) {
				mess = "已经存在";
				return new JsonResult<User>(flag, mess, user);
			} else {

				daoService.getUserByUsername(username).setLastLoginTime(
						lastLoginTime);
				daoService.getUserByUsername(username).setPhone(user.getPhone());
				daoService.modifyUserInfoById(daoService
						.getUserByUsername(username));
				mess = "修改成功";
				System.out.println(user);
				flag = ConstantCode.SUCCESS;
			}
			return new JsonResult<User>(flag, mess, user);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("用户电话更新异常");
		}
		return null;
	}

	/**
	 * 用户更新（密码）
	 */
	@ResponseBody
	@RequestMapping(value = "/userUpdateWithPassword")
	public JsonResult<User> userUpdatewithPassword(HttpServletRequest request,
			HttpServletResponse response, User user, String username,
			String password, String passwordc) {
		try {
			String mess = "";
			int flag = ConstantCode.ERROR;
			String lastLoginTime = DateUtil.getDateStr(new Date(),
					"yyyy-MM-dd HH:mm:ss");
			// 1.非空
			if (!ValidateUtil.isValid(password)) {
				mess = "密码不能为空！";
				return new JsonResult<User>(flag, mess, user);
			}
			if (!ValidateUtil.isValid(passwordc)) {
				mess = "确认密码不能为空！";
				return new JsonResult<User>(flag, mess, user);
			}
			// 2.密码一致性
			if (!password.equals(passwordc)) {
				mess = "密码不一致！";
				return new JsonResult<User>(flag, mess, user);

			} else {

				daoService.getUserByUsername(username).setLastLoginTime(
						lastLoginTime);
				daoService.getUserByUsername(username).setPassword(
						DataUtil.md5(password));
				daoService.modifyUserInfoById(daoService
						.getUserByUsername(username));
				mess = "修改成功";
				System.out.println(user);
				flag = ConstantCode.SUCCESS;
			}
			return new JsonResult<User>(flag, mess, user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("用户密码更新异常");
		}
		return null;
	}

	/**
	 * 用户更新（角色）
	 */
	@ResponseBody
	@RequestMapping(value = "/userUpdateWithRole")
	public JsonResult<User> userUpdatewithRole(HttpServletRequest request,
			HttpServletResponse response, User user, String username,
			String role) {

		try {
			System.out.println("+++++" + role);
			String mess = "";
			int flag = ConstantCode.ERROR;
			String lastLoginTime = DateUtil.getDateStr(new Date(),
					"yyyy-MM-dd HH:mm:ss");
			// 1.非空
			if (!ValidateUtil.isValid(role)) {
				mess = "角色不能为空！";
				return new JsonResult<User>(flag, mess, user);
			} else {
				if ("1".equals(role)) {
					role = "admin";
				} else {
					role = "common";
				}
				daoService.getUserByUsername(username).setLastLoginTime(
						lastLoginTime);
				daoService.getUserByUsername(username).setRole(role);
				daoService.modifyUserInfoById(daoService
						.getUserByUsername(username));
				mess = "修改成功";
				System.out.println(user);
				flag = ConstantCode.SUCCESS;
			}
			return new JsonResult<User>(flag, mess, user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("用户角色更新异常");
		}
		return null;
	}

	/**
	 * 删除用户
	 */
	@ResponseBody
	@RequestMapping(value = "/userDelete")
	public JsonResult<User> xhzDelete(HttpServletRequest request, User user,
			String id) {

		try {
			String mess = "删除成功";
			int flag = ConstantCode.SUCCESS;
			user = (User) request.getSession().getAttribute("user");
			user.setCompanyId(user.getCompanyId());
			// 删除user
			daoService.removeUserById(Integer.parseInt(id));
			return new JsonResult<User>(flag, mess, user);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("删除用户异常");
		}
		return null;
	}

	/**
	 * 注销
	 * 
	 * @return
	 */
	@RequestMapping("/loginout")
	public String loginout(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().invalidate();
		return "login.jsp";
	}

}
