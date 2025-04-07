package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.Api;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Repository.JobPostRepository;
import com.example.lab10.Service.JobPostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jobpost")
public class JobPostController {

    public final JobPostRepository jobPostRepository;
    private final JobPostService jobPostService;

    public JobPostController(JobPostRepository jobPostRepository, JobPostService jobPostService) {
        this.jobPostRepository = jobPostRepository;
        this.jobPostService = jobPostService;
    }
    @PostMapping("/add")
    public ResponseEntity addjob(@RequestBody @Valid JobPost jobPost, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        jobPostService.addjob(jobPost);
        return ResponseEntity.status(200).body(new Api("Job posted successfully"));
    }
    @GetMapping("/get")
    public ResponseEntity getalljobs(){
        return ResponseEntity.status(200).body(jobPostService.getalljobs());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updatejob(@PathVariable int id,@Valid @RequestBody JobPost jobPost,Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        Boolean updated=jobPostService.updatejob(id, jobPost);
        if (updated){
            return ResponseEntity.status(200).body(new Api("job updated"));
        }else return ResponseEntity.status(400).body(new Api("job not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletejob(@PathVariable int id){
        Boolean deleted=jobPostService.deletejob(id);
        if (deleted){
            return ResponseEntity.status(200).body(new Api("deleted successfully"));
        }else return ResponseEntity.status(400).body(new Api("job not found"));
    }

}
