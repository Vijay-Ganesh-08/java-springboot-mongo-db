package org.springboot.jobportal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "JobListing")
public class JobPost {

    private String profile;
    private String desc;
    private int exp;
    private String[] techs;

}
