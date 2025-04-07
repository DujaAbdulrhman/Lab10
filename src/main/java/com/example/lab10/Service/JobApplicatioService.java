package com.example.lab10.Service;


import com.example.lab10.Model.JobApplication;
import com.example.lab10.Repository.JobApplicationRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicatioService {

    private final JobApplicationRepository jobApplicationRepository;

    public JobApplicatioService(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public boolean addApplication(@Valid JobApplication jobApplication){
        jobApplicationRepository.save(jobApplication);
        return true;
    }

    public List<JobApplication> getallApplication(){
        return jobApplicationRepository.findAll();
    }

    public boolean deleteApplocation(int id){
        JobApplication oldApplication=jobApplicationRepository.getReferenceById(id);

        if (oldApplication.getId()==id){
            jobApplicationRepository.delete(oldApplication);
            return true;
        }
        return false;



    }
}
