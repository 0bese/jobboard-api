package com.kaobese.jobboard_api.job;

import java.util.List;

public interface JobService {
    List<Job> getAllJobs();

    Job getJobById(Long id);

    void createJob(Job job);

    Boolean deleteJobById(Long id);

    Boolean updateJobById(Long id, Job job);
}
