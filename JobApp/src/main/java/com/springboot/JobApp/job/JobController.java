package com.springboot.JobApp.job;
import com.springboot.JobApp.company.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/jobs")   // base url for every Mapping
public class JobController {

    private JobService jobService;
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
//  During runtime spring boot makes you available the job service object
    @GetMapping
    public ResponseEntity<List<Job>> findAll(){          // endpoint to return all jobs
        return new ResponseEntity<>(jobService.findAll() , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        Company company = job.getCompany();
        if(company != null){
            return new ResponseEntity<>("Job added Successfully " ,HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Company not Found" ,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable long id){
        Job job = jobService.getJobById(id);
        if(job != null){
            return new ResponseEntity<>(job , HttpStatus.OK);// creating a response with job object
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable long id){
        boolean delete = jobService.deleteJobById(id);
        if(delete)
        {
            return new ResponseEntity<>("Job deleted Successfully" , HttpStatus.OK);
        }
        return new ResponseEntity<>("Job not Found!" , HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
//    @RequestMapping(value = "/jobs/{id}" , method = RequestMethod.PUT)
    public ResponseEntity<String> updateJobById(@PathVariable long id , @RequestBody Job updatedJob){
        boolean updated = jobService.updateJobById(id , updatedJob);
        if(updated){
            return new ResponseEntity<>("Job updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job not found!" , HttpStatus.NOT_FOUND);
    }
}
/*

GET   /jobs  : to get all jobs
GET   /jobs/{id} : get a specific job by id
POST  /jobs : create a new job (request body should contains job details)
DELETE  /jobs/{id} : delete a specific job by id
PUT   /jobs/{id}  : update a specific job by id ( using request body )
GET   /jobs/{id}/company : get the company associated with specific job by id

*/
