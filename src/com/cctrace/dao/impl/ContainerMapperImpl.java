package com.cctrace.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cctrace.dao.ContainerMapper;
import com.cctrace.entity.Container;

@Repository(value = "containerMapper")
public class ContainerMapperImpl implements ContainerMapper {

	@Resource
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<Container> selectContainersNoBinded() {
		List<Container> containers = sqlSession
				.selectList("com.cctrace.dao.ContainerMapper.selectContainersNoBinded");
		return containers;
	}

	@Override
	public List<Container> selectContainersByCompanyId(Integer companyId) {
		List<Container> containers = sqlSession.selectList(
				"com.cctrace.dao.ContainerMapper.selectContainersByCompanyId",
				companyId);
		return containers;
	}

	@Override
	public List<Container> selectContainersByCompanyIdAndContainerIdLikeLy(
			Map<String, Object> map) {
		List<Container> containers = sqlSession
				.selectList(
						"com.cctrace.dao.ContainerMapper.selectContainersByCompanyIdAndContainerIdLikeLy",
						map);
		return containers;
	}

	@Override
	public String selectContainerIdByDeviceId(String deviceId) {
		String conId = sqlSession.selectOne(
				"com.cctrace.dao.ContainerMapper.selectContainerIdByDeviceId",
				deviceId);
		return conId;
	}

	@Override
	public Container selecContainerByContainerId(String containerId) {
		Container container = sqlSession.selectOne(
				"com.cctrace.dao.ContainerMapper.selecContainerByContainerId",
				containerId);
		return container;
	}

	@Override
	public Container selectContainerByDeviceId(String deviceId) {
		Container container = sqlSession.selectOne(
				"com.cctrace.dao.ContainerMapper.selectContainerByDeviceId",
				deviceId);
		return container;
	}

	@Override
	public int deleteContainerById(Integer id) {
		int delete = sqlSession.delete(
				"com.cctrace.dao.ContainerMapper.deleteContainerById", id);
		return delete;
	}

	@Override
	public int deleteContainerByContainerId(String containerId) {
		int delete = sqlSession.delete(
				"com.cctrace.dao.ContainerMapper.deleteContainerByContainerId",
				containerId);
		return delete;
	}

	@Override
	public int updateContainerById(Container container) {
		int update = sqlSession.update(
				"com.cctrace.dao.ContainerMapper.updateContainerById",
				container);
		return update;
	}

	@Override
	public int insertContainer(Container container) {
		int insert = sqlSession.insert(
				"com.cctrace.dao.ContainerMapper.insertContainer", container);
		return insert;
	}

	@Override
	public int updateContainerByContainerId(Container container) {
		int n = sqlSession.update(
				"com.cctrace.dao.ContainerMapper.updateContainerByContainerId",
				container);
		return n;
	}

	@Override
	public List<Container> selectContainersLikeyInHard(String containerId) {
		List<Container> containers = sqlSession.selectList(
				"com.cctrace.dao.ContainerMapper.selectContainersLikeyInHard",
				containerId);
		return containers;
	}

	@Override
	public List<Container> selectContainersLikeyInPCContainer(String containerId) {
		List<Container> containers = sqlSession
				.selectList(
						"com.cctrace.dao.ContainerMapper.selectContainersLikeyInPCContainer",
						containerId);
		return containers;

	}
	
	@Override
	public List<Container> selectContainersLikeyInPCContainerWithCompany(
			Map<String, Object> map) {
		List<Container> containers = sqlSession.selectList("com.cctrace.dao.ContainerMapper.selectContainersLikeyInPCContainerWithCompany", map);
		return containers;
	}

}
