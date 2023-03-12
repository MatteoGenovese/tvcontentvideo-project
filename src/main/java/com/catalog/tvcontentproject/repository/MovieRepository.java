package com.catalog.tvcontentproject.repository;

import com.catalog.tvcontentproject.pojo.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {


}
