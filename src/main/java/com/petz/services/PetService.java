package com.petz.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.petz.domain.Pet;
import com.petz.repositories.PetRepository;
import com.petz.service.exceptions.ObjectNotFoundException;

@Service
public class PetService {

	@Autowired
	private PetRepository repo;

	public Pet find(Integer id) {
		Optional<Pet> obj = repo.findById(id);

		if (obj.isEmpty()) {
			throw new ObjectNotFoundException(
					"Objeto não encontrato! ID: " + id + ", Tipo: " + Pet.class.getName());
		}

		return obj.orElseThrow(() -> new ObjectNotFoundException("Not found"));
	}

	public List<Pet> findAll() {
		return repo.findAllByOrderByNome();
	}

	@Transactional
	public Pet insert(Pet obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	@Transactional
	public Pet update(Pet obj) {
		find(obj.getId());
		return repo.save(obj);

	}

	@Transactional
	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);

	}

	public Page<Pet> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

}
