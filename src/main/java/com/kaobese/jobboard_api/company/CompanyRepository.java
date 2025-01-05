package com.kaobese.jobboard_api.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company getCompanyById(Long companyId);
}
