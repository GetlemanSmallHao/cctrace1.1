package com.cctrace.dao;

import com.cctrace.entity.Company;

public interface CompanyMapper {
	int insertCompany(Company company);
	
	Company selectCompanyById(Integer id);
	
	Company selectCompanyByCompanyName(String companyName);

	Company selectCompanyByCompanyPhone(String companyPhone);
	
	int deleteCompanyByCompanyName(String companyName);
	
	int deleteCompanyByCompanyPhone(String companyPhone);
	
	int deleteCompanyById(Integer id);
	
	int updateCompanyById(Company company);
}
