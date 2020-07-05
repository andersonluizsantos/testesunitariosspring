package br.com.produtoToy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.produtoToy.domains.Produto;
import br.com.produtoToy.repositories.ProdutoRepository;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repo;

	@GetMapping(value = "{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Produto produto = repo.findOne(id);
		if (produto == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(repo.getOne(id));
		}
	}

}