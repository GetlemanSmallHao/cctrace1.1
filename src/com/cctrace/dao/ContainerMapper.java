package com.cctrace.dao;

import java.util.List;
import java.util.Map;

import com.cctrace.entity.Container;

public interface ContainerMapper {

	List<Container> selectContainersNoBinded();

	List<Container> selectContainersByCompanyId(Integer companyId);

	List<Container> selectContainersByCompanyIdAndContainerIdLikeLy(
			Map<String, Object> map);
	
	public List<Container> selectContainersLikeyInPCContainerWithCompany(
			Map<String, Object> map);

	String selectContainerIdByDeviceId(String deviceId);

	Container selecContainerByContainerId(String containerId);

	Container selectContainerByDeviceId(String deviceId);

	int deleteContainerById(Integer id);

	int deleteContainerByContainerId(String containerId);

	int updateContainerById(Container container);

	int insertContainer(Container container);

	int updateContainerByContainerId(Container container);

	public List<Container> selectContainersLikeyInHard(String containerId);

	public List<Container> selectContainersLikeyInPCContainer(String containerId);

}
