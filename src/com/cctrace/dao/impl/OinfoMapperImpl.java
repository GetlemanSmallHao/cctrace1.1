package com.cctrace.dao.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cctrace.dao.OinfoMapper;
import com.cctrace.entity.Log;
import com.cctrace.entity.Oinfo;
@Repository(value="oinfoMapper")
public class OinfoMapperImpl implements OinfoMapper{
	@Resource
	private SqlSession sqlSession;

	@Override
	public Oinfo selectOinfoByOinfoContent(String oinfoContent) {
		Oinfo oinfo = sqlSession.selectOne("com.cctrace.dao.OinfoMapper.selectOinfoByOinfoContent", oinfoContent);
		return oinfo;
	}

	

}
