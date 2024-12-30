package com.kaobese.jobboard_api.job.impl;

import com.kaobese.jobboard_api.job.Job;
import com.kaobese.jobboard_api.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    List<Job> jobs = new ArrayList<>();
    @Override
    public List<Job> getAllJobs() {
        return jobs;
    }

    @Override
    public Job getJobById(Long id) {
        for(Job job: jobs){
            if(job.getId().equals(id)){
                return job;
            }
        }
        return null;
    }

    @Override
    public void createJob(Job job) {
        jobs.add(job);
    }

    @Override
    public Boolean deleteJobById(Long id) {
        Job job = getJobById(id);
        if(job != null){
            jobs.remove(job);
            return true;
        }
        return false;

    }

    @Override
    public Boolean updateJobById(Long id, Job job) {
        for(Job jb: jobs){
            if(jb.getId().equals(id)){
                jb.setTitle(job.getTitle());
                jb.setDescription(job.getDescription());
                jb.setMinSalary(job.getMinSalary());
                jb.setMaxSalary(job.getMaxSalary());
                jb.setLocation(job.getLocation());
                return true;
            }
        }
        return false;
    }
}
