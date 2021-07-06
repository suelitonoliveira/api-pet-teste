package com.petz.service;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petz.domain.Cidade;
import com.petz.domain.Cliente;
import com.petz.domain.Endereco;
import com.petz.domain.Pet;
import com.petz.enums.TipoPet;
import com.petz.repositories.CidadeRepository;
import com.petz.repositories.ClienteRepository;
import com.petz.repositories.EnderecoRepository;
import com.petz.repositories.PetRepository;

@Service
public class DBService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public void instanciaBaseDeDados() {

		Cidade cidade1 = new Cidade(null, "São Paulo");
		Cidade cidade2 = new Cidade(null, "Brasília");
		Cidade cidade3 = new Cidade(null, "Goiânia");

		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

		Cliente cli1 = new Cliente(null, "Maria", "maria@gmail.com");
		cli1.getTelefones().addAll(Arrays.asList("619999999", "119989854"));

		Cliente cli2 = new Cliente(null, "Jose", "jose@gmail.com");
		cli2.getTelefones().addAll(Arrays.asList("619999999", "119989854"));

		Cliente cli3 = new Cliente(null, "João", "joao@gmail.com");
		cli3.getTelefones().addAll(Arrays.asList("619999999", "119989854"));

		Endereco end1 = new Endereco(null, "lagradouro", "numero", "complemento", "bairro", "cep", cli3, cidade3);
		Endereco end2 = new Endereco(null, "lagradouro", "numero", "complemento", "bairro", "cep", cli1, cidade1);
		Endereco end3 = new Endereco(null, "lagradouro", "numero", "complemento", "bairro", "cep", cli2, cidade2);

		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3));

		Pet pet1 = new Pet(null, "Totó", LocalDate.now(), TipoPet.CACHORRO, cli1);
		Pet pet2 = new Pet(null, "Cristal", LocalDate.now(), TipoPet.GATO, cli2);
		Pet pet3 = new Pet(null, "Azeitona", LocalDate.now(), TipoPet.PEIXE, cli3);

		petRepository.saveAll(Arrays.asList(pet1, pet2, pet3));

	}

}
