package com.jobapp.Job.MS.Services;

import com.jobapp.Job.MS.Models.Job;

import java.util.List;

public interface JobService
{
    List<Job> findAll();
    void createJob(Job job);
    Job getJobById(Long id);
    boolean deleteJob(Long id);
    boolean updateJobById(Long id, Job job);
}
