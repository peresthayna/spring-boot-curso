package br.edu.fema.thayna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.edu.fema.thayna.domain.Anime;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootApplication
public class CursoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringApplication.class, args);
		ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}", Anime.class, 2);
		log.info(entity);
		
		Anime anime = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class, 2);
		log.info(anime);
	}

}
