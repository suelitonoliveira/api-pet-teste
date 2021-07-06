package com.petz.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.petz.domain.Cidade;
import com.petz.repositories.CidadeRepository;
import com.petz.service.exceptions.DataIntegrityException;
import com.petz.service.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {
	

	@Autowired
	private CidadeRepository repo;

	public Cidade find(Integer id) {
		Optional<Cidade> obj = repo.findById(id);

		if (obj.isEmpty()) {
			throw new ObjectNotFoundException(
					"Objeto não encontrato! ID: " + id + ", Tipo: " + Cidade.class.getName());
		}

		return obj.orElseThrow(() -> new ObjectNotFoundException("Not found"));
	}

	@Transactional
	public Cidade insert(Cidade obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	@Transactional
	public Cidade update(Cidade obj) {
		try {
			find(obj.getId());
			return repo.save(obj);
		} catch (Exception e) {
			throw new ObjectNotFoundException("Not Found");
		}

	}

	@Transactional
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Exclusão não permitida, itens vinculados");
		}

	}

	public List<Cidade> findAll() {
		return repo.findAllByOrderByNome();
	}

	public Page<Cidade> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

}
