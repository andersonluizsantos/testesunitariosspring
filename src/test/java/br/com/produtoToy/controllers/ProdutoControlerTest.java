package br.com.produtoToy.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProdutoControlerTest {

	//Como agora vamos simular a chamada do Postman, precisamos fazer uma configuração específica
	@Autowired
	public WebApplicationContext contexto;
	
	private MockMvc mvc;
	
	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.contexto).build();
	}
	
	@Test
	public void testaRequisicaoIdSucesso() throws Exception {
		String url = "/produtos/1";
		this.mvc.perform(get(url))
			.andExpect(status().isOk())
			.andExpect(jsonPath("descricao", equalTo("Sandalia havainas")));
	}
	
	@Test
	public void testaRequisicaoIdFalha() throws Exception {
		String url = "/produtos/3";
		this.mvc.perform(get(url))
			.andExpect(status().isNotFound());
	}
}
