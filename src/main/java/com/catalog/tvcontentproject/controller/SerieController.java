package com.catalog.tvcontentproject.controller;

import com.catalog.tvcontentproject.dto.JsonParametersDTO;
import com.catalog.tvcontentproject.dto.VideoDTO;
import com.catalog.tvcontentproject.pojo.Serie;
import com.catalog.tvcontentproject.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/series")
@CrossOrigin("*")
public class SerieController {

	@Autowired
	private SerieService serieService;

	@PostMapping
	public List<VideoDTO> findMovies(){
		return serieService.returnAllTheVideoList();
	}

	@PostMapping("/content")
	public VideoDTO findMovieById(@RequestBody String id){
		return serieService.returnVideo(id);
	}

	@PostMapping("/create")
	public void saveMovie(@RequestBody Serie serie){
		serieService.saveSerie(serie);
	}

	@PostMapping("/delete")
	public void deleteById(@RequestBody String id){
		serieService.deleteSerieById(id);
	}

	@PostMapping(value ="/search/title", consumes = APPLICATION_JSON_VALUE)
	public List<VideoDTO> searchMoviesByTitle(@RequestBody JsonParametersDTO movieParameters){
		return serieService.returnVideoListSearchedByTitle(movieParameters);
	}

	@PostMapping(value = "/search/titleandgenre", consumes = APPLICATION_JSON_VALUE)
	public List<VideoDTO> searchMoviesByTitleAndGenre(@RequestBody JsonParametersDTO movieParameters){
		return serieService.returnVideoListSearchedByTitleAndGenre(movieParameters);
	}
}
