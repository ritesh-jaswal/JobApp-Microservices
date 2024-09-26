package com.jobapp.Job.MS.ServiceImpls;


import com.jobapp.Job.MS.Models.External.Dto.JobCompanyDto;
import com.jobapp.Job.MS.Models.Job;
import com.jobapp.Job.MS.Models.External.Company;
import com.jobapp.Job.MS.Repositories.JobRepo;
import com.jobapp.Job.MS.Services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService
{
    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<JobCompanyDto> findAll()
    {
        List<Job> jobs = jobRepo.findAll();

        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void createJob(Job job)
    {
        jobRepo.save(job);
    }

    @Override
    public JobCompanyDto getJobById(Long id)
    {
       Job job = jobRepo.findById(id).orElse(null);
       return convertToDto(job);
    }

    @Override
    public boolean deleteJob(Long id)
    {
        try
        {
            jobRepo.deleteById(id);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean updateJobById(Long id, Job job)
    {
        Optional<Job> optionalJob = jobRepo.findById(id);
        if(optionalJob.isPresent())
        {
            Job j = optionalJob.get();
            j.setTitle(job.getTitle());
            j.setDescription(job.getDescription());
            j.setMinSalary(job.getMinSalary());
            j.setMaxSalary(job.getMaxSalary());
            j.setLocation(job.getLocation());

            jobRepo.save(j);
            return true;
        }
        return false;
    }

    private JobCompanyDto convertToDto(Job job)
    {
        JobCompanyDto jobCompanyDto = new JobCompanyDto();
        jobCompanyDto.setJob(job);

        Company company = restTemplate.getForObject("http://Company-MS:8083/companies/"+job.getCompanyId(),Company.class);
        jobCompanyDto.setCompany(company);

        return jobCompanyDto;
    }
}
