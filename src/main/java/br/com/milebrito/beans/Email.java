package br.com.milebrito.beans;

public class Email {
	
	private String nomeRemetente;
	private String enderecoEmailRemetente;
	private String textoMensagem;
	
	
	public Email(){}
	
	public Email(String nomeRemetente, String enderecoEmailRemetente, String textoMensagem) {
		super();
		this.nomeRemetente = nomeRemetente;
		this.enderecoEmailRemetente = enderecoEmailRemetente;
		this.textoMensagem = textoMensagem;
	}
	public String getNomeRemetente() {
		return nomeRemetente;
	}
	public void setNomeRemetente(String nomeRemetente) {
		this.nomeRemetente = nomeRemetente;
	}
	public String getEnderecoEmailRemetente() {
		return enderecoEmailRemetente;
	}
	public void setEnderecoEmailRemetente(String enderecoEmailRemetente) {
		this.enderecoEmailRemetente = enderecoEmailRemetente;
	}
	public String getTextoMensagem() {
		return textoMensagem;
	}
	public void setTextoMensagem(String textoMensagem) {
		this.textoMensagem = textoMensagem;
	}
	

}
