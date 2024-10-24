package com.springboot.JobApp.company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.JobApp.job.Job;
import com.springboot.JobApp.review.Review;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;

    @JsonIgnore
//   The output is simpler and avoids any potential for infinite loops.
//   helping you avoid issues like circular references

    @OneToMany(mappedBy = "company")
    private List<Job> jobs;   // ensure that one company have many jobs

    @OneToMany(mappedBy = "company")
    private List<Review> reviews;   // ensure that one company have many reviews

    public Company(){

    }

    public Company(long id, String name, String description, List<Job> jobs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.jobs = jobs;
    }

    public long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
