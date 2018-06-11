package com.cctrace.dao.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cctrace.dao.LwAlarmMapper;
import com.cctrace.entity.LwAlarm;
import com.cctrace.entity.Transformation;
import com.cctrace.entity.Transformationnew;

@Repository(value = "lwAlarmMapper")
public class LwAlarmMapperImpl implements LwAlarmMapper {

	@Resource
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public LwAlarm selectLwAlarmByAlarm_num(Integer alarm_num) {
		return sqlSession.selectOne(
				"com.cctrace.dao.LwAlarmMapper.selectLwAlarmByAlarm_num",
				alarm_num);
	}

	@Override
	public Transformation selectTransformationByAlarmNumber(String alarmNumber) {
		return sqlSession
				.selectOne(
						"com.cctrace.dao.TransformationMapper.selectTransformationByAlarmNumber",
						alarmNumber);
	}

	@Override
	public Transformationnew selectTransformationnewByAlarmNumber(
			String alarmNumber) {
		return sqlSession
				.selectOne(
						"com.cctrace.dao.TransformationMapper.selectTransformationNewByAlarmNumber",
						alarmNumber);
	}

}
