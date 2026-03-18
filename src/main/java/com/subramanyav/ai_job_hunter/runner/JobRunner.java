package com.subramanyav.ai_job_hunter.runner;

import com.subramanyav.ai_job_hunter.model.Job;
import com.subramanyav.ai_job_hunter.service.JobService;
import com.subramanyav.ai_job_hunter.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Component
public class JobRunner implements CommandLineRunner {

    @Autowired
    private JobService jobService;

    @Autowired
    private AIService aiService;

    @Override
    public void run(String... args) throws IOException {

        System.out.println("Fetching Jobs...");

        List<Job> jobs = jobService.fetchJobs();

        FileWriter writer = new FileWriter("jobs.csv", false); // false = overwrite true = new file

        // Header
        writer.append("Date,JobID,Title,Company,Location,Posted,Score,Likelihood,ApplyType,Link,Recruiter,Status\n");

        for (Job job : jobs) {

            String date = LocalDate.now().toString();
            String status = "NEW";
            String aiResult = aiService.analyze(job);

            // simple parsing (temporary)
            String score = aiResult.split(",")[0].split(":")[1];
            String likelihood = aiResult.split(",")[1].split(":")[1];
            String applyType = "MANUAL";
            String recruiter = "N/A";

            writer.append(date).append(",")
                    .append("'"+job.getId()).append(",")
                    .append(job.getTitle()).append(",")
                    .append(job.getCompanyName()).append(",")
                    .append(job.getLocation()).append(",")
                    .append(job.getPostedAt()).append(",")
                    .append(score).append(",")
                    .append(likelihood).append(",")
                    .append(applyType).append(",")
                    .append(job.getApplyLink()).append(",")
                    .append(recruiter).append(",")
                    .append(status).append("\n");
        }

        writer.flush();
        writer.close();

        System.out.println("CSV file created successfully!");
    }
}