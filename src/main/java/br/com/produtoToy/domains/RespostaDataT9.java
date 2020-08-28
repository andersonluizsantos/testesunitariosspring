package br.com.produtotoy.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespostaDataT9 {

	@JsonProperty("data")
	private RespostaT9 data;
	
	public RespostaT9 getData(){
		return data;
	}
	
	public void setData(RespostaT9 data) {
		this.data = data;
	}
}
