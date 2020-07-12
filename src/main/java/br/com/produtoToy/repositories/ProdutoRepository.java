package br.com.produtoToy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.produtoToy.domains.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	//Aqui já temos todos os métodos do CRUD
	
	// criando uma consulta específica
	// select p from produto p where p.descricao like '%%'
	public List<Produto> findByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);
	
}
