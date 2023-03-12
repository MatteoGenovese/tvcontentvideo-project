package com.catalog.tvcontentproject.pojo;

import com.catalog.tvcontentproject.dto.VideoDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Video {
	@Id
	private String id;

	@Field("Title")
	@NotNull
	private String title;

	@Field("Year")
	@NotNull
	private String year;

	@Field("Rated")
	@NotNull
	private String rated;

	@Field("Released")
	@NotNull
	private String released;

	@Field("Genre")
	@NotNull
	private List<String> genreList;

	public  String getGenreString(){

		String genreToString = "";
		for (String genre: getGenreList()){
			genreToString+= genre+" ";
		}

		return genreToString;
	}

	public static List<VideoDTO> convertVideoListToVideoDTOList(List<? extends Video> videoList){
		List<VideoDTO> response=new ArrayList<>();
		for (Video video : videoList){
			response.add(new VideoDTO(
					video.getTitle(),
					video.getYear(),
					video.getRated(),
					video.getReleased()
			));
		}
		return response;
	}
	public static VideoDTO convertVideoToVideoDTO(Video video){
		VideoDTO response = new VideoDTO(
				video.getTitle(),
				video.getYear(),
				video.getRated(),
				video.getReleased()
		);
		return response;
	}

}
