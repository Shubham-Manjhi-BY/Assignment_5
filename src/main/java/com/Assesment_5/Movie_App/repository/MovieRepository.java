package com.Assesment_5.Movie_App.repository;

import com.Assesment_5.Movie_App.model.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {
}