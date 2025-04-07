package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.Api;
import com.example.lab10.Model.JobApplication;
import com.example.lab10.Service.JobApplicatioService;
import jakarta.validation.Valid;
import org.springframework.boot.system.ApplicationPid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/applications")
public class JobApplicationController {

    private final JobApplicatioService jobApplicatioService;

    public JobApplicationController(JobApplicatioService jobApplicatioService) {
        this.jobApplicatioService = jobApplicatioService;
    }

    @PostMapping("/add")
    public ResponseEntity addApplication(@RequestBody @Valid JobApplication jobApplication , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        jobApplicatioService.addApplication(jobApplication);
        return ResponseEntity.status(200).body(new Api("Application added successfully"));
    }

    @GetMapping("/get")
    public ResponseEntity getall(){
        return ResponseEntity.status(200).body(jobApplicatioService.getallApplication());
    }

    public ResponseEntity deleteApplication(@PathVariable int id){
        Boolean deleted=jobApplicatioService.deleteApplocation(id);
        if (jobApplicatioService.getallApplication().contains(id)){
            if (deleted){
                return ResponseEntity.status(200).body(new Api("deleted successfully"));
            }
        }else {
        return ResponseEntity.status(400).body("not found");}
        return ResponseEntity.status(400).body(new Api("somthing went wrong"));

    }


}
