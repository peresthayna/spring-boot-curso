package br.edu.fema.thayna.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.fema.thayna.domain.Anime;
import br.edu.fema.thayna.dto.AnimePostDTO;
import br.edu.fema.thayna.dto.AnimePutDTO;
import br.edu.fema.thayna.exception.BadRequestException;
import br.edu.fema.thayna.repository.AnimeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimeService {
	private final AnimeRepository animeRepository;
	
	public Page<Anime> listAll(Pageable pageable) {
		return this.animeRepository.findAll(pageable);
	}
	
	public List<Anime> findByName(String name) {
		return this.animeRepository.findByName(name);
	}
	
	public Anime findById(Long id) {
		return this.animeRepository.findById(id)
				.orElseThrow(() -> new BadRequestException("Anime not found"));
	}

	@Transactional
	public Anime save(AnimePostDTO animeDTO) {
		return this.animeRepository.save(Anime.builder().name(animeDTO.getName()).build());
	}

	public void delete(Long id) {
		this.animeRepository.delete(findById(id));
	}

	public void replace(AnimePutDTO animeDTO) {
		this.animeRepository.save(Anime.builder().name(animeDTO.getName()).id(findById(animeDTO.getId()).getId()).build());
	}
}
