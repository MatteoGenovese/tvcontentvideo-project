package com.catalog.tvcontentproject;

import com.catalog.tvcontentproject.service.MovieService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //Configuration + EnableAutoConfiguration + ComponentScan
@Log
public class MovieProjectApplication implements CommandLineRunner {

//    @Autowired
//    MovieService movieService;

    public static void main(String[] args) {
//            config();
        SpringApplication.run(MovieProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        movieService.addAllMongoDBKeys();
    }

//    public static void config(){
//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//
//        Movie movie = context.getBean("movie", Movie.class);
//        movie.saluta();
//
//        MovieController movieController = context.getBean("movieController", MovieController.class);
//        movieController.saluta();
//
//        MovieService movieService = context.getBean("movieService", MovieService.class);
//        movieService.saluta();
//
//    }



}
