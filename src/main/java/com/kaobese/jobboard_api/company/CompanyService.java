package com.kaobese.jobboard_api.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    Company getCompanyById(Long id);
    boolean createCompany(Company company);
    boolean updateCompany(Company company, Long id);
    boolean deleteCompanyById(Long id);
}
