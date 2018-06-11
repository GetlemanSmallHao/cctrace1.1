package com.cctrace.dao;

import org.springframework.stereotype.Controller;

import com.cctrace.entity.LwAlarm;
import com.cctrace.entity.Transformation;
import com.cctrace.entity.Transformationnew;


@Controller
public interface LwAlarmMapper {
	
	LwAlarm selectLwAlarmByAlarm_num(Integer alarm_num);
	Transformation selectTransformationByAlarmNumber(String alarmNumber);
	Transformationnew selectTransformationnewByAlarmNumber(String alarmNumber);
	
}
