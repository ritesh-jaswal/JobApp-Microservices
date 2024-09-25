package com.jobapp.Company.MS.ServiceImpls;


import com.jobapp.Company.MS.Models.Company;
import com.jobapp.Company.MS.Repositories.CompanyRepo;
import com.jobapp.Company.MS.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService
{
    @Autowired
    private CompanyRepo companyRepo;

    @Override
    public List<Company> getAllCompanies()
    {
        return companyRepo.findAll();
    }

    @Override
    public boolean updateCompany(Long id, Company company)
    {
        Optional<Company> companyOptional = companyRepo.findById(id);
        if(companyOptional.isPresent())
        {
            Company c = companyOptional.get();
            c.setName(company.getName());
            c.setDescription(company.getDescription());
            companyRepo.save(c);

            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company)
    {
        companyRepo.save(company);
    }

    @Override
    public Company getCompanyById(Long id)
    {
        return companyRepo.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompany(Long id)
    {
        try
        {
            companyRepo.deleteById(id);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
