package br.edu.fema.thayna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fema.thayna.domain.Anime;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
	List<Anime> findByName(String name);
}
