package com.kaobese.jobboard_api.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getAllJobs(){
      return new ResponseEntity<>(jobService.getAllJobs(), HttpStatus.OK);
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobByID(@PathVariable Long id){
        Job job = jobService.getJobById(id);
        if(job != null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        Boolean deleted = jobService.deleteJobById(id);
        if(deleted)
            return new ResponseEntity<>("Job successfully deleted", HttpStatus.OK);
        return new ResponseEntity<>("Something went wrong!!", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job job){
        Boolean updated = jobService.updateJobById(id, job);
        if(updated)
            return new ResponseEntity<>("Job successfully updated", HttpStatus.OK);
        return new ResponseEntity<>("Something went wrong!!", HttpStatus.NOT_FOUND);
    }


}
