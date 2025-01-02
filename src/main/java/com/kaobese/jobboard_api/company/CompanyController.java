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
}
/*
* POST /companies/{companyID}/reviews
* GET /companies/{companyID}/reviews
* GET /companies/{companyID}/reviews/{reviewID}
* PUT /companies/{companyID}/reviews/{reviewID}
* DELETE /companies/{companyID}/reviews/{reviewID}
*/