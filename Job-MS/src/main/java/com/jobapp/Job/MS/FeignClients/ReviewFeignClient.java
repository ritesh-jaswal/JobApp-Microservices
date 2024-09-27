package com.jobapp.Job.MS.FeignClients;

import com.jobapp.Job.MS.Models.External.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "Review-MS")
public interface ReviewFeignClient
{
    @GetMapping("/reviews")
    List<Review> getReviews(@RequestParam("companyId") Long companyId);
}
