package com.springboot.JobApp.company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> findAll(){
        return new ResponseEntity<>(companyService.getAllCompanies() , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("Company created successfully" , HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable long id , @RequestBody Company updateCompany){
        boolean updatedCompany = companyService.updateCompany(id , updateCompany);
        if(updatedCompany){
            return new ResponseEntity<>("Company successfully Edited " , HttpStatus.OK);
        }
        return new ResponseEntity<>("Company not found with this id" , HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable long id)
    {
        boolean deleteCompany = companyService.deleteCompany(id);
        if(deleteCompany)
        {
            return new ResponseEntity<>("Company Deleted" , HttpStatus.OK);
        }
        return new ResponseEntity<>("Company not found" , HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable long id){
        Company company = companyService.getCompanyById(id);
        if(company != null)
        {
            return new ResponseEntity<>(company , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
