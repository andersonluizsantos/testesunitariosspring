package br.com.produtotoy.controllers;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.gson.Gson;

import br.com.produtotoy.domains.Produto;
import br.com.produtotoy.domains.RespostaDataT9;
import br.com.produtotoy.repositories.ProdutoRepository;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repo;

	@GetMapping(value = "{id}")
	public ResponseEntity<Object> findById(@PathVariable Integer id) {
		Produto produto = repo.findOne(id);
		if (produto == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(repo.getOne(id));
		}
	}
	
	@GetMapping(value = "respostaT9/{id}")
	public ResponseEntity<Object> findByRespostaT9() {
		try {
			String json = String.join(" ",
			        Files.readAllLines(Paths.get("C:\\temp\\json\\Massa.json"), StandardCharsets.UTF_8));
			RespostaDataT9 respostaDataT9 = new Gson().fromJson(json, RespostaDataT9.class);
			return ResponseEntity.ok(respostaDataT9);
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return null;
	}

	@GetMapping(value = "like/{descricao}")
	public ResponseEntity<Object> findByDescricao(@PathVariable String descricao) {
		List<Produto> produtos = repo.findByDescricaoContainingIgnoreCase(descricao);
		if (produtos.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(produtos);
		}
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
		try {
			repo.delete(id);
			return ResponseEntity.ok(id);
		} catch (EmptyResultDataAccessException e) {
			// se retornar essa exceção específica
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping()
	public ResponseEntity<Object> salvarProduto(@RequestBody Produto produto) {
		Produto produtoSalvo = repo.save(produto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produtoSalvo.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

}
