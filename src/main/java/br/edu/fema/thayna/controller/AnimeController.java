package br.edu.fema.thayna.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fema.thayna.domain.Anime;
import br.edu.fema.thayna.dto.AnimePostDTO;
import br.edu.fema.thayna.dto.AnimePutDTO;
import br.edu.fema.thayna.service.AnimeService;
import br.edu.fema.thayna.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {
	private final DateUtil dateUtil;
	private final AnimeService animeService;
	
	@GetMapping
	public ResponseEntity<Page<Anime>> animeList(Pageable pageable) {
		log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
		return ResponseEntity.ok(this.animeService.listAll(pageable));
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Anime> findById(@PathVariable Long id) {
		return ResponseEntity.ok(this.animeService.findById(id));
	}
	
	@GetMapping(path = "/find")
	public ResponseEntity<List<Anime>> findByName(@RequestParam String name) {
		return ResponseEntity.ok(this.animeService.findByName(name));
	}
	
	@PostMapping
	public ResponseEntity<Anime> save(@RequestBody AnimePostDTO anime) {
		return new ResponseEntity<>(this.animeService.save(anime), HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.animeService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping
	public ResponseEntity<Void> replace(@RequestBody AnimePutDTO anime) {
		this.animeService.replace(anime);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
