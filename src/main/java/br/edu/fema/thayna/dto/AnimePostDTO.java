package br.edu.fema.thayna.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AnimePostDTO {
	@NotBlank(message = "Anime name cannot be null, empty or blank")
	private String name;
}
