package org.springboot.jobportal.repository;

import org.springboot.jobportal.model.JobPost;

import java.util.List;

public interface SearchRepo {

    List<JobPost> findByText(String searchString);

}
