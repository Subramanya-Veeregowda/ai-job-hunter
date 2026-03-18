package com.subramanyav.ai_job_hunter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Job {
    private String id;
    private String title;
    private String companyName;
    private String location;
    private String postedAt;
    @JsonProperty("url")
    private String applyLink;
    private String recruiterUrl;
    private String score;
    private String likelihood;
    private String applyType;
    private String recruiter;
}