package com.jobapp.Job.MS.Services;

import com.jobapp.Job.MS.Models.External.Dto.JobDto;
import com.jobapp.Job.MS.Models.Job;

import java.util.List;

public interface JobService
{
    List<JobDto> findAll();
    void createJob(Job job);
    JobDto getJobById(Long id);
    boolean deleteJob(Long id);
    boolean updateJobById(Long id, Job job);
}
