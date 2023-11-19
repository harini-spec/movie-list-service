package io.MovieListApp.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.MovieListApp.model.MovieData;

@Repository
public interface MovieRepository extends MongoRepository<MovieData, String>{
	
	List<MovieData> findByName(String name);
	
}
