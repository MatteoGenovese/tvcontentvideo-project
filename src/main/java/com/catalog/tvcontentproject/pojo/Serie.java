package com.catalog.tvcontentproject.pojo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@Data
public class Serie extends Video{
	@Field("Seasons")
	@NotNull
	private String seasons;

}
