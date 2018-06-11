package com.cctrace.dao.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cctrace.dao.CompanyMapper;
import com.cctrace.entity.Company;

@Repository(value="companyMapper")
public class CompanyMapperImpl implements CompanyMapper {

	@Resource
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int insertCompany(Company company) {
		int insert = sqlSession.insert("com.cctrace.dao.CompanyMapper.insertCompany",company);
		return insert;
	}
	
	@Override
	public Company selectCompanyById(Integer id) {
		Company company  = sqlSession.selectOne("com.cctrace.dao.CompanyMapper.selectCompanyById",id);
		return company;
	}

	@Override
	public Company selectCompanyByCompanyName(String companyName) {
		Company company = sqlSession.selectOne("com.cctrace.dao.CompanyMapper.selectCompanyByCompanyName",companyName);
		return company;
	}

	@Override
	public Company selectCompanyByCompanyPhone(String companyPhone) {
		Company company = sqlSession.selectOne("com.cctrace.dao.CompanyMapper.selectCompanyByCompanyPhone", companyPhone);
		return company;
	}

	@Override
	public int deleteCompanyByCompanyName(String companyName) {
		int delete = sqlSession.delete("com.cctrace.dao.CompanyMapper.deleteCompanyByCompanyName", companyName);
		return delete;
	}

	@Override
	public int deleteCompanyByCompanyPhone(String companyPhone) {
		int delete = sqlSession.delete("com.cctrace.dao.CompanyMapper.deleteCompanyByCompanyPhone", companyPhone);
		return delete;
	}

	@Override
	public int deleteCompanyById(Integer id) {
		int delete = sqlSession.delete("com.cctrace.dao.CompanyMapper.deleteCompanyById",id);
		return delete;
	}

	@Override
	public int updateCompanyById(Company company) {
		int update = sqlSession.update("com.cctrace.dao.CompanyMapper.updateCompanyById",company);
		return update;
	}
	
}
