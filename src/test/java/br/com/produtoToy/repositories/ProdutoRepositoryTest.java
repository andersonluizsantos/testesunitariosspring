package br.com.produtoToy.repositories;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.produtoToy.domains.Produto;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class ProdutoRepositoryTest {
	
	@Autowired
	private ProdutoRepository repo;
	
	@Test
	public void voidTestaProdutoHavaina() {
		List<Produto> produtos = repo.findByDescricaoContainingIgnoreCase("hava"); 
		assertThat(produtos.size()).isEqualTo(1);
		assertThat(produtos.get(0).getDescricao().equals("Sandalia havainas"));		
	}
	
	@Test
	public void voidTestaTodosProdutos() {
		List<Produto> produtos = repo.findAll(); 
		assertThat(produtos.get(0).getId().equals(1));		
	}
	
	@Test
	public void voidTestaProdutoSapato() {
		List<Produto> produtos = repo.findByDescricaoContainingIgnoreCase("sapato"); 
		assertThat(produtos.size()).isEqualTo(0);
	}
	
	@Test
	public void voidIdHavaina() {
		Produto produto = repo.findOne(1); 
		assertThat(produto.getId()).isEqualTo(1);
	}
	
}
