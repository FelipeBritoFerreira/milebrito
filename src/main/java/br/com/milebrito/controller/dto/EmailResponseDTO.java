package br.com.milebrito.controller.dto;

public class EmailResponseDTO {
	
	private boolean erro;
	private String resposta;
	
	public boolean isErro() {
		return erro;
	}
	public void setErro(boolean erro) {
		this.erro = erro;
	}
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	

}
