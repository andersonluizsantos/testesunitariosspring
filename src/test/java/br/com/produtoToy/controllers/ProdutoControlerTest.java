package br.com.produtotoy.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProdutoControlerTest {

	// Como agora vamos simular a chamada do Postman, precisamos fazer uma
	// configuração específica
	@Autowired
	public WebApplicationContext contexto;

	private MockMvc mvc;

	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.contexto).build();
	}

	@Test
	public void t01estaRequisicaoIdSucesso() throws Exception {
		String url = "/produtos/1";
		this.mvc.perform(get(url)).andExpect(status().isOk())
				.andExpect(jsonPath("descricao", equalTo("Sandalia havainas")));
	}

	@Test
	public void t02estaRequisicaoIdFalha() throws Exception {
		String url = "/produtos/3";
		this.mvc.perform(get(url)).andExpect(status().isNotFound());
	}

	@Test
	public void t03estaRequisicaoDescricaoSucesso() throws Exception {
		String url = "/produtos/like/Havainas";
		this.mvc.perform(get(url)).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].descricao", is("Sandalia havainas")));
	}

	@Test
	public void t04estaRequisicaoDescricaoFalha() throws Exception {
		String url = "/produtos/like/havianasss";
		this.mvc.perform(get(url)).andExpect(status().isNotFound());
	}

	@Test
	public void t05estaRequisicaoPostSucesso() throws Exception {
		String url = "/produtos";
		String content = "{\"descricao\": \"Brinquedo\"}";
		this.mvc.perform(post(url).content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
		// .andExpect(header().string("location", is));
	}

	@Test
	public void t06estaRequisicaoDeleteSucesso() throws Exception {
		String url = "/produtos/1";
		this.mvc.perform(delete(url)).andExpect(status().isOk()).andExpect(jsonPath("$", is(1)));
	}

	@Test
	public void t07estaRequisicaoDeleteFalha() throws Exception {
		String url = "/produtos/99";
		this.mvc.perform(delete(url)).andExpect(status().isNotFound());
	}

	@Test
	public void t08estaRequisicaoHygor() throws Exception {
		String url = "/produtos/respostaT9/1";
		this.mvc.perform(get(url)).andExpect(status().isOk());
	}

}
