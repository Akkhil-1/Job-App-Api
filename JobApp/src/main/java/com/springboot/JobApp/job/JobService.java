package com.springboot.JobApp.job;
import java.util.*;


// To promote loose coupling
public interface JobService {

    List <Job> findAll();

    void createJob(Job job);

    Job getJobById(long id);

    boolean deleteJobById(long id);

    boolean updateJobById(long id , Job updatedJob);
}
