package com.catalog.tvcontentproject.controller;




import com.catalog.tvcontentproject.dto.JsonParametersDTO;
import com.catalog.tvcontentproject.dto.VideoDTO;
import com.catalog.tvcontentproject.pojo.Movie;
import com.catalog.tvcontentproject.service.MovieService;
import com.catalog.tvcontentproject.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController //Controller + ResponseBody
@RequestMapping("/api/movies")
@CrossOrigin("*")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private VideoService<Movie> videoService;

//    public MovieController(MovieService movieService) {
//        super();
//        this.movieService = movieService;
//    }

    public void saluta(){
        System.out.println("Sono il controller");
    }

    @PostMapping
    public List<VideoDTO> findMovies(){
        return movieService.returnAllTheVideoList();
    }

    @PostMapping("/content")
    public VideoDTO findMovieById(@RequestBody String id){
        return movieService.returnVideo(id);
    }

    @PostMapping("/create")
    public void saveMovie(@RequestBody Movie movie){
        movieService.saveMovie(movie);
    }

    @PostMapping("/delete")
    public void deleteById(@RequestBody String id){
        movieService.deleteMovieById(id);
    }

    @PostMapping(value ="/search/title", consumes = APPLICATION_JSON_VALUE)
    public List<VideoDTO> searchMoviesByTitle(@RequestBody JsonParametersDTO movieParameters){
        return movieService.returnVideoListSearchedByTitle(movieParameters);
    }

    @PostMapping(value = "/search/titleandgenre", consumes = APPLICATION_JSON_VALUE)
    public List<VideoDTO> searchMoviesByTitleAndGenre(@RequestBody JsonParametersDTO movieParameters){
        return movieService.returnVideoListSearchedByTitleAndGenre(movieParameters);
    }

}
