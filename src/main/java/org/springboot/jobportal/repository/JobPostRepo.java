package org.springboot.jobportal.repository;

import org.springboot.jobportal.model.JobPost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostRepo extends MongoRepository<JobPost,String>
{

}
