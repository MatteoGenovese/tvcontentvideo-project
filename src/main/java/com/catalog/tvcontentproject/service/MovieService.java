package com.catalog.tvcontentproject.service;

import com.catalog.tvcontentproject.dto.JsonParametersDTO;
import com.catalog.tvcontentproject.dto.VideoDTO;
import com.catalog.tvcontentproject.pojo.Movie;
import com.catalog.tvcontentproject.pojo.Video;
import com.catalog.tvcontentproject.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MovieService implements Videoable {


    @Autowired
    private MovieRepository movieRepository;


//    public MovieService(MongoMovieRepository movieRepository) {
//        this.movieRepository = movieRepository;
//    }

//    public void saluta(){
//        System.out.println("Sono il service");
//    }
    public void addAllMongoDBKeys()
    {

        log.info("adding MongoDB keys");
        boolean addedNewKeys=false;

        for (Movie movie : findAllMoviesFromDb()){
            if ( movie.getId()==null)
            {
                movie.setId(new ObjectId().toString());
                movieRepository.save( movie );
                addedNewKeys=true;
            }
        }
        if(addedNewKeys){
            log.info("added new MongoDB keys");
        }
        else {
            log.info("no new MongoDB keys added");
        }

    }
    public List<Movie> findAllMoviesFromDb(){
        return movieRepository.findAll();
    }
    public List<VideoDTO> returnAllTheVideoList(){
        log.info("finding all the movies");
        List<Movie> movieList = findAllMoviesFromDb();
        if (!movieList.isEmpty()) {
            log.info("found the movie list");
            return Video.convertVideoListToVideoDTOList(movieList);
        }
        else {
            log.info("no movie list found");
            return null;
        }
    }



    public VideoDTO returnVideo(String id){
        Movie movie=returnMovieById(id);
        return Video.convertVideoToVideoDTO(movie);
    }

    public Optional<Movie> findMovieById(String id)
    {
            return movieRepository.findById(id);
    }


    public Movie returnMovieById(String id){
        if (findMovieById(id).isPresent()) {
            Movie movie = findMovieById(id).get();
            log.info("movie found");
            return movie;
        }else {
            log.info("no movie found");
            return new Movie();
        }
    }


    public void saveMovie(Movie movie){
        movie.setId(new ObjectId().toString());
        movieRepository.save(movie);
        log.info("movie saved in db");
    }


    public void deleteMovieById(String id) {

        if (movieRepository.findById(id).isPresent()) {
            movieRepository.deleteById(id);
            log.info("movie found and deleted");
        }
        else
            log.info("no movie found");

    }



    public  List<VideoDTO> returnVideoListSearchedByTitle(JsonParametersDTO movieParameters) {

        List<VideoDTO> moviesFilteredDTO=new ArrayList<>();
        log.info("searching movie by title");
        List<Movie> moviesFromDbFilteredByTitleDAO=findAllMoviesFromDb()
                .stream()
                .filter( movie ->  movie.getTitle().toLowerCase().contains(movieParameters.getTitle().toLowerCase()))
                .collect(Collectors.toList());
        if (!moviesFromDbFilteredByTitleDAO.isEmpty())
            log.info("the search produced a movie list");
        else
            log.info("the search didn't produce a movie list");
        moviesFilteredDTO = Video.convertVideoListToVideoDTOList(moviesFromDbFilteredByTitleDAO);
        return moviesFilteredDTO;

    }

    public  List<VideoDTO> returnVideoListSearchedByTitleAndGenre(JsonParametersDTO movieParameters) {

        List<VideoDTO> moviesFilteredDTO=new ArrayList<>();
        List<Movie> moviesFromDbFilteredByTitleDAO=findAllMoviesFromDb()
                .stream()
                .filter( movie ->  movie.getTitle().toLowerCase().contains(movieParameters.getTitle().toLowerCase())
                        && movie.getGenreString().toLowerCase().contains(movieParameters.getGenre().toLowerCase())
                ).collect(Collectors.toList());
        if (!moviesFromDbFilteredByTitleDAO.isEmpty())
            log.info("the search produced a movie list");
        else
            log.info("the search didn't produce a movie list");

        moviesFilteredDTO = Video.convertVideoListToVideoDTOList(moviesFromDbFilteredByTitleDAO);

        return moviesFilteredDTO;

    }
}
