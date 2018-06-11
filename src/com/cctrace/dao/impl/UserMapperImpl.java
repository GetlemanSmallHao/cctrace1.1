package com.cctrace.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cctrace.dao.UserMapper;
import com.cctrace.entity.User;

@Repository(value = "userMapper")
public class UserMapperImpl implements UserMapper {

	@Resource
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<User> selectAllUsersByCompanyId(Integer companyId) {
		List<User> users = sqlSession.selectList(
				"com.cctrace.dao.UserMapper.selectAllUsersByCompanyId",
				companyId);
		return users;
	}

	@Override
	public User selectUserByUsernameAndPassword(Map<String, String> map) {
		User user = sqlSession.selectOne(
				"com.cctrace.dao.UserMapper.selectUserByUsernameAndPassword",
				map);
		return user;
	}

	@Override
	public User selectUserByUsername(String username) {
		User user = sqlSession.selectOne(
				"com.cctrace.dao.UserMapper.selectUserByUsername", username);
		return user;
	}

	@Override
	public User selectUserByPhone(String phone) {
		User user = sqlSession.selectOne(
				"com.cctrace.dao.UserMapper.selectUserByPhone", phone);
		return user;
	}

	@Override
	public int deleteUserById(Integer id) {
		int delete = sqlSession.delete(
				"com.cctrace.dao.UserMapper.deleteUserById", id);
		return delete;
	}

	@Override
	public int deleteUserByUsername(String username) {
		int delete = sqlSession.delete(
				"com.cctrace.dao.UserMapper.deleteUserByUsername", username);
		return delete;
	}

	@Override
	public int insertUser(User user) {
		int insert = sqlSession.insert("com.cctrace.dao.UserMapper.insertUser",
				user);
		return insert;
	}

	@Override
	public int updateUserByUsername(User user) {
		int update = sqlSession.update(
				"com.cctrace.dao.UserMapper.updateUserByUsername", user);
		return update;
	}

	@Override
	public int updateUserById(User user) {
		int update = sqlSession.update(
				"com.cctrace.dao.UserMapper.updateUserById", user);
		return update;
	}

	@Override
	public List<User> selectLimitUser(Map<String, Object> map) {
		List<User> users = sqlSession.selectList(
				"com.cctrace.dao.UserMapper.showLimitUser", map);
		return users;
	}

	@Override
	public List<User> selectUsersLikey(String username) {
		List<User> users = sqlSession.selectList(
				"com.cctrace.dao.UserMapper.selectUserslikey", username);
		return users;
	}
	
	@Override
	public List<User> selectUserslikeyInCompany(Map<String, Object> map) {
		List<User> users = sqlSession.selectList("com.cctrace.dao.UserMapper.selectUserslikeyInCompany", map);
		return users;
	}

	@Override
	public List<User> showLimitUserLikey(Map<String, Object> map) {
		List<User> users = sqlSession.selectList("com.cctrace.dao.UserMapper.showLimitUserLikey", map);
		return users;
	}
	
	
}
