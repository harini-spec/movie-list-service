package io.MovieListApp.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.MovieListApp.model.MovieData;
import io.MovieListApp.service.MovieService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"http://localhost:3000", "https://movie-list-application-two.vercel.app/"})
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/movies")
	public List<MovieData> getMovies(){
		return movieService.getAllMovies();
	}
	
	@PostMapping("/movies")
	public void addMovie(@RequestBody MovieData movie) {
		movieService.addMovie(movie);
	}
	
	@GetMapping("/movies/{id}")
	public Optional<MovieData> getMovieById(@PathVariable String id) {
		return movieService.getMovieById(id);
	}
	
	@GetMapping("/movies/search/{name}")
	public List<MovieData> searchMovieByName(@PathVariable String name){
		return movieService.getMovieByName(name);
	}
	
	@PutMapping("/movies/{id}")
	public ResponseEntity<MovieData> updateMovie(@PathVariable String id, @RequestBody MovieData newMovieData){
		return movieService.updateMovieDetails(id, newMovieData);
	}
	
	@DeleteMapping("/movies/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteMovie(@PathVariable String id){
		return movieService.deleteMovie(id);
	}

	@GetMapping("/movies/count/{language}")
	public int countMoviesByLanguage(@PathVariable String language){
		return movieService.countMoviesByLanguage(language);
	}
	
}
