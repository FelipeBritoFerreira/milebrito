package br.com.milebrito.beans;

import java.util.List;

public class Galeria {
	
	private String nomeGaleria;
	private List<Foto> fotos;
	private String titulo;
	private Foto fotoCapa;
	private String subTitulo;
	private Foto fotoContracapa;
	
	
	public Galeria() {}
	
	public Galeria(String nomeGaleria, String titulo, String subTitulo) {
		this.nomeGaleria = nomeGaleria;
		this.titulo = titulo;
		this.subTitulo = subTitulo;
	}
	
	
	public String getNomeGaleria() {
		return nomeGaleria;
	}
	public void setNomeGaleria(String nomeGaleria) {
		this.nomeGaleria = nomeGaleria;
	}
	public List<Foto> getFotos() {
		return fotos;
	}
	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getSubTitulo() {
		return subTitulo;
	}
	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}
	public Foto getFotoCapa() {
		return fotoCapa;
	}
	public void setFotoCapa(Foto fotoCapa) {
		this.fotoCapa = fotoCapa;
	}
	public Foto getFotoContracapa() {
		return fotoContracapa;
	}
	public void setFotoContracapa(Foto fotoContracapa) {
		this.fotoContracapa = fotoContracapa;
	}
	
	
}
