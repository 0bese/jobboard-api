package com.kaobese.jobboard_api.company.impl;

import com.kaobese.jobboard_api.company.Company;
import com.kaobese.jobboard_api.company.CompanyRepository;
import com.kaobese.jobboard_api.company.CompanyService;
import com.kaobese.jobboard_api.job.Job;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyServiceImpl implements CompanyService {

    CompanyRepository companyRepo;

    public CompanyServiceImpl(CompanyRepository companyRepo) {
        this.companyRepo = companyRepo;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepo.findById(id).orElse(null);
    }

    @Override
    public boolean createCompany(Company company) {
        try{
        companyRepo.save(company);
        return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        try{
            if (!companyRepo.existsById(id)) {
                return false;
            }

            Company existingCompany = companyRepo.findById(id).orElseThrow();

            existingCompany.setName(company.getName());
            existingCompany.setDescription(company.getDescription());
            existingCompany.setJobs(company.getJobs());
            existingCompany.setReviews(company.getReviews());
            companyRepo.save(existingCompany);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        try{
            if (!companyRepo.existsById(id)) {
                return false;
            }

            companyRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
