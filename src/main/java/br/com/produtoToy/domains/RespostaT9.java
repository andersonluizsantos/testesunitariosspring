package br.com.produtotoy.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespostaT9 {

	@JsonProperty("id_proposta")
	private long id_proposta;
	
	@JsonProperty("id_meio_formalizacao")
    private Integer id_meio_formalizacao;
	
	@JsonProperty("descricao_meio_formalizacao")
    private String descricao_meio_formalizacao;
	
	@JsonProperty("tipo_meio_formalizacao")
    private String tipo_meio_formalizacao;
	
	@JsonProperty("numero_operacao")
    private Integer numero_operacao;
	
	public long getId_proposta() {
		return id_proposta;
	}
	public void setId_proposta(long id_proposta) {
		this.id_proposta = id_proposta;
	}
	public Integer getId_meio_formalizacao() {
		return id_meio_formalizacao;
	}
	public void setId_meio_formalizacao(Integer id_meio_formalizacao) {
		this.id_meio_formalizacao = id_meio_formalizacao;
	}
	public String getDescricao_meio_formalizacao() {
		return descricao_meio_formalizacao;
	}
	public void setDescricao_meio_formalizacao(String descricao_meio_formalizacao) {
		this.descricao_meio_formalizacao = descricao_meio_formalizacao;
	}
	public String getTipo_meio_formalizacao() {
		return tipo_meio_formalizacao;
	}
	public void setTipo_meio_formalizacao(String tipo_meio_formalizacao) {
		this.tipo_meio_formalizacao = tipo_meio_formalizacao;
	}
	public Integer getNumero_operacao() {
		return numero_operacao;
	}
	public void setNumero_operacao(Integer numero_operacao) {
		this.numero_operacao = numero_operacao;
	}
    
}
