package com.catalog.tvcontentproject.repository;


import com.catalog.tvcontentproject.pojo.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends MongoRepository<Serie, String> {
}
