package com.catalog.tvcontentproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

//
@Getter
@Setter
@AllArgsConstructor
public class VideoDTO {
	@Field("Title")
	private String title;

	@Field("Year")
	private String year;

	@Field("Rated")
	private String rated;

	@Field("Released")
	private String released;





}
