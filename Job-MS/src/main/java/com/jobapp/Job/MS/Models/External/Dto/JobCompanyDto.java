package com.jobapp.Job.MS.Models.External.Dto;

import com.jobapp.Job.MS.Models.External.Company;
import com.jobapp.Job.MS.Models.Job;

public class JobCompanyDto
{
    private Job job;
    private Company company;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
