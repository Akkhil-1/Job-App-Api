package com.springboot.JobApp.company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();

    boolean updateCompany(long id , Company updatedCompany);

    void createCompany(Company company);

    boolean deleteCompany(long id);

    Company getCompanyById(long id);

}
