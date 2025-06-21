package org.springboot.jobportal.controller;


import lombok.extern.slf4j.Slf4j;
import org.springboot.jobportal.model.JobPost;
import org.springboot.jobportal.repository.JobPostRepo;
import org.springboot.jobportal.repository.SearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class JobPostController {

    @Autowired
    private JobPostRepo jobPostRepo;

    @Autowired
    private SearchRepo searchRepo;

    /*
     * @CrossOrigin Annotation needs to used at the Controller level
     * to accepts the request from UI
     */

    @GetMapping(path="/getAllPosts", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<JobPost> getAllJobPosts() {
        log.info("Getting all job posts");
        return jobPostRepo.findAll();
    }

    @GetMapping(path="/searchJobPost/{searchString}")
    public List<JobPost> searchJobPost(@PathVariable String searchString) {
        log.info("Getting job posts by search string {}", searchString);
        return searchRepo.findByText(searchString);
    }

    @PostMapping(path="/addJobPost")
    public JobPost addJobPost(@RequestBody JobPost jobPost) {
        log.info("Adding job post {}", jobPost);
        return jobPostRepo.save(jobPost);
    }

}
