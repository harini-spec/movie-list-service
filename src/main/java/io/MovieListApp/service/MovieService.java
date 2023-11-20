package io.MovieListApp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import io.MovieListApp.exception.ResourceNotFound;
import io.MovieListApp.model.MovieData;
import io.MovieListApp.repository.MovieRepository;
import io.MovieListApp.repository.SearchRepository;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private SearchRepository searchRepository;
	
	public List<MovieData> getAllMovies(){
		return movieRepository.findAll();
	}
	
	public void addMovie(MovieData movieData) {
		movieRepository.save(movieData);
	}
	
	public List<MovieData> getMovieByName(String movie) {
		return searchRepository.findByName(movie);
	}

	public Optional<MovieData> getMovieById(String id) {
		return movieRepository.findById(id);
	}
	
	public ResponseEntity<MovieData> updateMovieDetails(String id, MovieData newMovieData){
		MovieData movieData = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Movie data not found"));
		movieData.setName(newMovieData.getName());
		movieData.setDirector(newMovieData.getDirector());
		movieData.setRelease_year(newMovieData.getRelease_year());
		movieData.setLanguage(newMovieData.getLanguage());
		movieData.setRating(newMovieData.getRating());
		MovieData updatedMovieData = movieRepository.save(movieData);
		return ResponseEntity.ok(updatedMovieData);
	}
	
	public ResponseEntity<Map<String, Boolean>> deleteMovie(String id){
		MovieData movieData = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Movie data not found"));
		movieRepository.delete(movieData);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	public int countMoviesByLanguage(String language){
		List<MovieData> movieData = searchRepository.findByLanguage(language);
		return movieData.size();
	}

}
