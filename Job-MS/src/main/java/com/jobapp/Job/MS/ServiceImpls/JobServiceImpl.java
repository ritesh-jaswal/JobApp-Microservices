package com.jobapp.Job.MS.ServiceImpls;


import com.jobapp.Job.MS.Models.Job;
import com.jobapp.Job.MS.Repositories.JobRepo;
import com.jobapp.Job.MS.Services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService
{
    @Autowired
    private JobRepo jobRepo;

    @Override
    public List<Job> findAll()
    {
        return jobRepo.findAll();
    }

    @Override
    public void createJob(Job job)
    {
        jobRepo.save(job);
    }

    @Override
    public Job getJobById(Long id)
    {
       return jobRepo.findById(id).orElse(null);
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
}
