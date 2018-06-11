package com.cctrace.dao;

import java.util.List;
import java.util.Map;

import com.cctrace.entity.User;

public interface UserMapper {
	List<User> selectAllUsersByCompanyId(Integer companyId);

	User selectUserByUsernameAndPassword(Map<String, String> map);

	User selectUserByUsername(String username);

	User selectUserByPhone(String phone);

	int deleteUserById(Integer id);

	int deleteUserByUsername(String username);

	int insertUser(User user);

	int updateUserByUsername(User user);

	int updateUserById(User user);
	
	public List<User> selectUserslikeyInCompany(Map<String, Object> map);
	
	public List<User> showLimitUserLikey(Map<String, Object> map);

	List<User> selectLimitUser(Map<String, Object> map);

	List<User> selectUsersLikey(String username);

}
