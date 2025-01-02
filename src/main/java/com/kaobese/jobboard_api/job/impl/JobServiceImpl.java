package com.kaobese.jobboard_api.job.impl;

import com.kaobese.jobboard_api.job.Job;
import com.kaobese.jobboard_api.job.JobRepository;
import com.kaobese.jobboard_api.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    JobRepository repo;

    public JobServiceImpl(JobRepository repo) {
        this.repo = repo;
    }

    List<Job> jobs = new ArrayList<>();
    @Override
    public List<Job> getAllJobs() {
        return repo.findAll();
    }

    @Override
    public Job getJobById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void createJob(Job job) {
        repo.save(job);
    }

    @Override
    public Boolean deleteJobById(Long id) {
        try{
            repo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean updateJobById(Long id, Job job) {
        try{
            if (!repo.existsById(id)) {
                return false;
            }

            Job existingJob = repo.findById(id).orElseThrow();

            existingJob.setTitle(job.getTitle());
            existingJob.setDescription(job.getDescription());
            existingJob.setMinSalary(job.getMinSalary());
            existingJob.setMaxSalary(job.getMaxSalary());
            existingJob.setLocation(job.getLocation());
            repo.save(existingJob);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
