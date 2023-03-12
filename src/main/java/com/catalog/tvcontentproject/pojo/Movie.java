package com.catalog.tvcontentproject.pojo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("movie")
@Data
public class Movie extends Video {

    @Field("Runtime")
    @NotNull
    private String runtime;


}
