package com.springboot.JobApp.job;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends JpaRepository<Job , Long>{ //Entity type and primary key type
}
