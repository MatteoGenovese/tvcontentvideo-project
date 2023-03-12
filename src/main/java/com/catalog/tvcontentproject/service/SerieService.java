package com.catalog.tvcontentproject.service;

import com.catalog.tvcontentproject.dto.JsonParametersDTO;
import com.catalog.tvcontentproject.dto.VideoDTO;
import com.catalog.tvcontentproject.pojo.Serie;
import com.catalog.tvcontentproject.pojo.Video;
import com.catalog.tvcontentproject.repository.SerieRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SerieService {


	@Autowired
	private SerieRepository serieRepository;

	//

	public void addAllMongoDBKeys()
	{
		log.info("adding MongoDB keys");
		boolean addedNewKeys=false;

		for (Serie serie : findAllSerieFromDb()){
			if ( serie.getId()==null)
			{
				serie.setId(new ObjectId().toString());
				serieRepository.save( serie );
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
	public List<VideoDTO> returnAllTheVideoList(){
		log.info("finding all the series");
		List<Serie> serieList = findAllSerieFromDb();
		if (!serieList.isEmpty()) {
			log.info("found the serie list");
			return Video.convertVideoListToVideoDTOList(serieList);
		}
		else {
			log.info("no serie list found");
			return null;
		}
	}
	public List<Serie> findAllSerieFromDb(){
		return serieRepository.findAll();
	}


	public VideoDTO returnVideo(String id){
		Serie serie=findSerieById(id);
		return Video.convertVideoToVideoDTO(serie);
	}
	public Serie findSerieById(String id){
		if (serieRepository.findById(id).isPresent()) {
			Serie serie = serieRepository.findById(id).get();
			log.info("serie found");
			return serie;
		}else {
			log.info("no serie found");
			return new Serie();
		}
	}


	public void saveSerie(Serie serie){
		serie.setId(new ObjectId().toString());
		serieRepository.save(serie);
		log.info("serie saved in db");
	}


	public void deleteSerieById(String id) {

		if (serieRepository.findById(id).isPresent()) {
			serieRepository.deleteById(id);
			log.info("serie found and deleted");
		}
		else
			log.info("no serie found");

	}



	public  List<VideoDTO> returnVideoListSearchedByTitle(JsonParametersDTO serieParameters) {

		List<VideoDTO> serieFilteredDTO=new ArrayList<>();
		log.info("searching serie by title");
		List<Serie> serieListFromDbFilteredByTitleDAO=findAllSerieFromDb()
				.stream()
				.filter( serie ->  serie.getTitle().toLowerCase().contains(serieParameters.getTitle().toLowerCase()))
				.collect(Collectors.toList());
		if (!serieListFromDbFilteredByTitleDAO.isEmpty())
			log.info("the search produced a serie list");
		else
			log.info("the search didn't produce a serie list");
		serieFilteredDTO = Video.convertVideoListToVideoDTOList(serieListFromDbFilteredByTitleDAO);
		return serieFilteredDTO;

	}

	public  List<VideoDTO> returnVideoListSearchedByTitleAndGenre(JsonParametersDTO serieParameters) {

		List<VideoDTO> serieFilteredDTO=new ArrayList<>();
		List<Serie> serieListFromDbFilteredByTitleDAO=findAllSerieFromDb()
				.stream()
				.filter( serie ->  serie.getTitle().toLowerCase().contains(serieParameters.getTitle().toLowerCase())
						&& serie.getGenreString().toLowerCase().contains(serieParameters.getGenre().toLowerCase())
				).collect(Collectors.toList());
		if (!serieListFromDbFilteredByTitleDAO.isEmpty())
			log.info("the search produced a serie list");
		else
			log.info("the search didn't produce a serie list");
		serieFilteredDTO = Video.convertVideoListToVideoDTOList(serieListFromDbFilteredByTitleDAO);

		return serieFilteredDTO;

	}
}
