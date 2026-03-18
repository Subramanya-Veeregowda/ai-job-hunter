package com.subramanyav.ai_job_hunter.service;

import com.subramanyav.ai_job_hunter.model.Job;
import org.springframework.stereotype.Service;


@Service
public class AIService {

    public String analyze(Job job) {
        return "Score:70, Likelihood:MEDIUM";
    }
}