package com.jobapp.Job.MS.FeignClients;

import com.jobapp.Job.MS.Models.External.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Company-MS")
public interface CompanyFeignClient
{
    @GetMapping("/companies/{id}")
    Company getCompany(@PathVariable("id") Long id);
}
