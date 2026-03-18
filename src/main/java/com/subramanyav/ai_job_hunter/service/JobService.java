package com.subramanyav.ai_job_hunter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subramanyav.ai_job_hunter.model.Job;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.*;

@Service
public class JobService {

    @Value("${apify.token}")
    private String token;

    @Value("${apify.dataset}")
    private String dataset;

    public List<Job> fetchJobs() {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://api.apify.com/v2/datasets/"
                + dataset +
                "/items?token=" +
                token;

        String response = restTemplate.getForObject(url, String.class);

        try {
            ObjectMapper mapper = new ObjectMapper();
            Job[] jobs = mapper.readValue(response, Job[].class);
            return Arrays.asList(jobs);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}