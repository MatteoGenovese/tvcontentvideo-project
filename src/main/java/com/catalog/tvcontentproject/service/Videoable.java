package com.catalog.tvcontentproject.service;

import com.catalog.tvcontentproject.dto.JsonParametersDTO;
import com.catalog.tvcontentproject.dto.VideoDTO;

import java.util.List;

public interface Videoable {
	public List<VideoDTO> returnAllTheVideoList();
	public VideoDTO returnVideo(String id);
	public  List<VideoDTO> returnVideoListSearchedByTitle(JsonParametersDTO movieParameters);
	public  List<VideoDTO> returnVideoListSearchedByTitleAndGenre(JsonParametersDTO movieParameters);

}
