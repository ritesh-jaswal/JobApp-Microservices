package com.jobapp.Company.MS.Services;



import com.jobapp.Company.MS.MessageQueue.Dto.ReviewMessage;
import com.jobapp.Company.MS.Models.Company;

import java.util.List;

public interface CompanyService
{
    List<Company> getAllCompanies();
    boolean updateCompany(Long id,Company company);
    void createCompany(Company company);
    Company getCompanyById(Long id);
    boolean deleteCompany(Long id);

    public void updateCompanyRating(ReviewMessage reviewMessage);
}
