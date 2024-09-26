package com.jobapp.Job.MS.Mappers;

import com.jobapp.Job.MS.Models.External.Company;
import com.jobapp.Job.MS.Models.External.Dto.JobDto;
import com.jobapp.Job.MS.Models.External.Review;
import com.jobapp.Job.MS.Models.Job;

import java.util.List;

public class JobMapper
{
    public static JobDto mapToJobCompanyDto(Job job, Company company, List<Review> reviews)
    {
        JobDto dto = new JobDto();

        dto.setId(job.getId());
        dto.setTitle(job.getTitle());
        dto.setDescription(job.getDescription());
        dto.setLocation(job.getLocation());
        dto.setMinSalary(job.getMinSalary());
        dto.setMaxSalary(job.getMaxSalary());
        dto.setCompany(company);
        dto.setReview(reviews);

        return dto;
    }
}
