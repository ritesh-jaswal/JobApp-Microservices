package com.jobapp.Company.MS.MessageQueue;

import com.jobapp.Company.MS.MessageQueue.Dto.ReviewMessage;
import com.jobapp.Company.MS.Services.CompanyService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer
{
    private final CompanyService companyService;

    public ReviewMessageConsumer(CompanyService companyService)
    {
        this.companyService = companyService;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage)
    {
        companyService.updateCompanyRating(reviewMessage);
    }
}
