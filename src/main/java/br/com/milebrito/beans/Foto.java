package br.com.milebrito.beans;

public class Foto {
	
	private String url;
	private String nomeArquivo;
	private TipoFoto tipoFoto;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public TipoFoto getTipoFoto() {
		return tipoFoto;
	}
	public void setTipoFoto(TipoFoto tipoFoto) {
		this.tipoFoto = tipoFoto;
	}
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	
}
