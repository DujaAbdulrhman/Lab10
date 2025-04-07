package com.example.lab10.Service;

import com.example.lab10.Model.JobPost;
import com.example.lab10.Repository.JobPostRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPostService {

    public final JobPostRepository jobPostRepository;

    public JobPostService(JobPostRepository jobPostRepository) {
        this.jobPostRepository = jobPostRepository;
    }

    //add new job
    public Boolean addjob(@Valid JobPost jobPost){
        jobPostRepository.save(jobPost);
        return true;
    }

    //get all jobs posted
    public List<JobPost> getalljobs(){
        return  jobPostRepository.findAll();
    }

    //update job posted

    public boolean updatejob(int id,@Valid JobPost jobPost){
        JobPost oldjob=jobPostRepository.getReferenceById(id);
        if (oldjob==null){
            return false;
        }
        oldjob.setTitle(jobPost.getTitle());
        oldjob.setDescription(jobPost.getDescription());
        oldjob.setPostingDate(jobPost.getPostingDate());

        jobPostRepository.save(oldjob);
        return true;
    }

    //delete job posted
    public Boolean deletejob(int id){
        JobPost oldjob=jobPostRepository.getReferenceById(id);
        if (oldjob==null){
            return false;
        }
        jobPostRepository.delete(oldjob);
        return true;
    }

}
