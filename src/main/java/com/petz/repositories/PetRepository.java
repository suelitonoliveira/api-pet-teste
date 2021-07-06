package com.petz.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.petz.domain.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

	@Transactional(readOnly = true)
	public List<Pet> findAllByOrderByNome();
	
	
}
