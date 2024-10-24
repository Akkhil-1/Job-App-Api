package com.springboot.JobApp.job;
import com.springboot.JobApp.company.Company;
import jakarta.persistence.*;

@Entity
//@Table(name = "job_table")
public class Job {
    @Id
//  automatically managed by jpa , u don't have to generate values for id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;

    @ManyToOne
    private Company company;


//  Accessing and modifying these variables by public getter and setter method

    public Job(){
//      REQUIREMENT FOR JPA TO HAVE A DEFAULT NO ARGUMENT CONSTRUCTOR , IT USES REFLECTION TO CREATE ENTITIES
//      WITHOUT THIS JPA IS UNABLE TO INVOKE ANY ENTITY OBJECT
    }
    public Job(long id, String title, String description, String minSalary, String maxSalary, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.location = location;
    }

//   We use getters and setters to safely access and update the private variables of
//   a class from outside the class,keeping the data secure and allowing control over how it's changed.4
    //  it cant be accessed by any external class , so it maintains data security
    //  so outer class cant change this directly

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
