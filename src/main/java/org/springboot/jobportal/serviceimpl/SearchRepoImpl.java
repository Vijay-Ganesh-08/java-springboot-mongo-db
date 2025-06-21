package org.springboot.jobportal.serviceimpl;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springboot.jobportal.model.JobPost;
import org.springboot.jobportal.repository.SearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchRepoImpl implements SearchRepo {


    /*
     * Below Code implementation is being generated with help of Index, Aggregation
     * from Mongo Atlas/Compass and EXPORT TO LANGUAGE
     *
     * Document should be implemented from BSON Class
     * MongoClient and MongoConvertor are implemented through SpringBoot
     * Removing Limit below would give all the results
     */
    @Autowired
    MongoClient mongoClient;

    @Autowired
    MongoConverter mongoConverter;

    @Override
    public List<JobPost> findByText(String searchString) {
        final List<JobPost> jobPosts = new ArrayList<>();
        MongoDatabase database = mongoClient.getDatabase("JobPortal");
        MongoCollection<Document> collection = database.getCollection("JobListing");

        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("text",
                        new Document("query", searchString)
                        .append("path", Arrays.asList("techs", "desc", "profile")))),
                        new Document("$sort",
                        new Document("exp", 1L)),
                        new Document("$limit", 5L)));

        result.forEach(document -> {jobPosts.add(mongoConverter.read(JobPost.class, document));});

        return jobPosts;
    }
}
