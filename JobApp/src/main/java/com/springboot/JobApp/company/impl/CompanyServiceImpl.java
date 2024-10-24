package com.springboot.JobApp.company.impl;
import com.springboot.JobApp.company.Company;
import com.springboot.JobApp.company.CompanyRepository;
import com.springboot.JobApp.company.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    private long nextId = 1L;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void createCompany(Company company) {
        company.setId(nextId++);
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(long id) {
        try{
            companyRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public Company getCompanyById(long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateCompany(@PathVariable long id, @RequestBody Company updatedCompany) {
        Company company = companyRepository.findById(id).orElse(null);
        if(company != null){
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
            company.setJobs(updatedCompany.getJobs());
            companyRepository.save(company);
            return true;
        }
        return false;
    }
}
