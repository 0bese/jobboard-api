package com.kaobese.jobboard_api.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getPathVariable(){
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Company> getCompany(@PathVariable Long companyId){
        Company found = companyService.getCompanyById(companyId);
        if(found != null)
            return new ResponseEntity<>(found, HttpStatus.OK);
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        boolean isCreated = companyService.createCompany(company);
        if(isCreated)
            return new ResponseEntity<>("Company created successfully", HttpStatus.CREATED);
        return new ResponseEntity<>("Error creating company", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<String> updateCompany(@RequestBody Company company, @PathVariable Long companyId){
        boolean isUpdated = companyService.updateCompany(company, companyId);
        if(isUpdated)
            return new ResponseEntity<>("Company updated successfully", HttpStatus.OK);
        return new ResponseEntity<>("Error updating company", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long companyId){
        boolean deleted = companyService.deleteCompanyById(companyId);
        if(deleted)
            return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
        return new ResponseEntity<>("Error deleting company", HttpStatus.NOT_FOUND);
    }
}
/*
* POST /companies/{companyID}
* GET /companies/{companyID}
* GET /companies/{companyID}
* PUT /companies/{companyID}
* DELETE /companies/{companyID}
*/