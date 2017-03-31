package br.com.milebrito.beans;

public enum TipoTituloSubTitulo {
	
	
	BATIZADO_JOSE("20160827_batizado_jose","José","Batizado"),
	BATIZADO_HEITOR("20160821_batizado_heitor","Heitor","Batizado"),
	FLAVIA_ARRAIA("flavia_arraia","Arraiá da flávia","Aniversário"),
	YSI("ysi","YSI","Festa infantil"),
	VALENTINA_NEW_BORN("valentina","Valentina","New born"),
	ARTHUR_PARTO_HUMANIZADO("nascimento_arthur","Arthur","Parto humanizado"),
	KEILLA_ENSAIO_GESTANTE("keilla_machado_gravidez","Keilla & Rodrigo","Ensaio gestante"),
	BIA_E_GABI("biaegabi","Bia e Gabi", "Ensaio família"),
	LUCAS_SMASH_THE_CAKE("smash_the_cake_lucas","Lucas","Smash the cake"),
	BENTO_1_ANO("bento_1_ano", "Bento", "1 ano"),
	SMASH_THE_CAKE_BENTO("bento_smash_the_cake", "Bento", "Smash the cake"),
	DIEGO_PRADO_DIVULGACAO("diego_prado","Diego Prado","Divulgação profissional"),
	FAMILIA_PRADO_BARBOSA("familia_prado_barbosa","Um dia na vida da família","Prado Barbosa"),
	LUCAS_1_ANO("lucas_1_ano","Lucas","1 ano"),
	MARCIO_KRAUSS("marcio_krauss","Marcio Krauss","Ensaio família")
	
	;
	
	
	TipoTituloSubTitulo(String nomeGaleria, String titulo, String subtitulo) {
		this.nomeGaleria = nomeGaleria;
		this.titulo = titulo;
		this.subtitulo = subtitulo;
		
	}
	
	
	
	private String nomeGaleria;
	private String titulo;
	private String subtitulo;
	
	public String getNomeGaleria() {
		return nomeGaleria;
	}
	public void setNomeGaleria(String nomeGaleria) {
		this.nomeGaleria = nomeGaleria;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getSubtitulo() {
		return subtitulo;
	}
	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}
	
	
	
	
	public static TipoTituloSubTitulo porNomeGaleria(String nome) {
		
		for (TipoTituloSubTitulo tipo : TipoTituloSubTitulo.values()) {
			
			if (tipo.getNomeGaleria().equals(nome)){
				return tipo;
			}
		}
		
		return null;
		
	}
	
	

}
